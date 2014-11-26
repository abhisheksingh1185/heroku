package com.ct.quickbooks.ws;

import org.w3c.dom.Document;
import java.util.*;


import javax.jws.WebService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import java.util.Set;

/*
 * http://developer.intuit.com/qbsdk-current/doc/pdf/qbwc_proguide.pdf
 */
@WebService(endpointInterface = "com.ct.quickbooks.ws.QBWebConnectorSvcSoap")
public class ItemQueryRqSoapImpl implements QBWebConnectorSvcSoap {
        public static String requestFor;
	@Override
	public ArrayOfString authenticate(String strUserName, String strPassword) {
		ArrayOfString arr = new ArrayOfString();
		arr.string = new ArrayList<String>();
		arr.string.add("The first element is a token for the web connector�s session");
		arr.string.add(""); //To use the currently open company, specify an empty string
		System.out.println(":::strUserName="+ strUserName);
                QBUserDetail.uid = strUserName;
                QBUserDetail.findUserOrgDetail(QBUserDetail.uid);
                Object[] arrayView = QBUserDetail.SalesForceobjects.toArray();
                requestFor = (String)arrayView[0];
                System.out.println(":::authenticate-requestFor="+ requestFor);
                return arr;
	}

	@Override
	public String closeConnection(String ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String connectionError(String ticket, String hresult, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastError(String ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return A positive integer less than 100 represents the percentage of work completed. A value of 1 means one percent complete, a value of 100 means 100 percent complete--there is no more work. A negative value means an error has occurred and the Web Connector responds to this with a getLastError call. The negative value could be used as a custom error code.
	 */
	@Override
	public int receiveResponseXML(String ticket, 
                    String response,
                    String hresult, String message) {
            Integer requestId = 100;
            // TODO Auto-generated method stub
            //System.out.println(":::response="+ response);
            System.out.println(":::uid="+ QBUserDetail.uid);            
            //convert XML String to Reader 
            try{ 
                /*              
                if(requestFor.equalsIgnoreCase(UtilClass.INVOICE_REQUEST)){
                    System.out.println(":::requestNo="+ requestFor); 
                    XMLtoTableMap objTbl = new XMLtoTableMap("InvoiceQueryRs","QBXMLMsgsRs");
                    objTbl.createTableMap(response);
                    //System.out.println(":::objTbl.tableResult="+ objTbl.tableResult);  
                    SyncInvoiceFromCustomer(objTbl);   
                }else if(requestFor.equalsIgnoreCase(UtilClass.CUSTOMER_REQUEST)){
                    XMLtoTableMap objTbl = new XMLtoTableMap("CustomerQueryRs","QBXMLMsgsRs");
                    objTbl.createTableMap(response);
                    //System.out.println(":::objTbl.createTableMap="+ objTbl.createTableMap);                 
                    SyncAccountFromCustomer(objTbl);                    
                }
                */
                if(QBUserDetail.SalesForceobjects.size() == 1)requestId=100;
                else if(QBUserDetail.SalesForceobjects.size() > 1){
                    QBUserDetail.SalesForceobjects.remove(requestFor);
                    requestId=2; 
                    Object[] arrayView = QBUserDetail.SalesForceobjects.toArray();
                    requestFor = (String)arrayView[0];
                }
            }catch(Exception ex){
               System.out.println(":::ex="+ ex.getMessage());
            } 
            
            return requestId;
	}
        
        

        public void SyncAccountFromCustomer(XMLtoTableMap objTbl)
        {
            try {            
                String xmlInner1 = "<urn:upsert><urn:externalIDFieldName>CTQbSync__Qb_Customer_id__c</urn:externalIDFieldName>";
                
                /*String xmlInner3 = "<urn:sObjects>"
                                 + "<urn1:type>Account</urn1:type>"           
                                 + "<Name>AS</Name>"                                 
                                 + "<CTQbSync__Qb_Sublevel__c>0</CTQbSync__Qb_Sublevel__c><CTQbSync__Qb_TimeCreated__c>2014-11-17T17:51:40+05:30</CTQbSync__Qb_TimeCreated__c><CTQbSync__Qb_customerJobStatus__c>None</CTQbSync__Qb_customerJobStatus__c><Site>Consummate</Site><CTQbSync__Qb_LastName__c>S</CTQbSync__Qb_LastName__c><CTQbSync__Qb_TotalBalance__c>0.00</CTQbSync__Qb_TotalBalance__c><Name>Abhishek S</Name><CTQbSync__Qb_Email__c>a@a.a</CTQbSync__Qb_Email__c><CTQbSync__Qb_IsActive__c>true</CTQbSync__Qb_IsActive__c><CTQbSync__Qb_FullName__c>Abhishek S</CTQbSync__Qb_FullName__c><CTQbSync__Qb_FirstName__c>Abhishek</CTQbSync__Qb_FirstName__c><CTQbSync__Qb_Customer_id__c>80000001-1416226900</CTQbSync__Qb_Customer_id__c><CTQbSync__Qb_TimeModified__c>2014-11-17T17:51:40+05:30</CTQbSync__Qb_TimeModified__c><CTQbSync__Qb_balance__c>0.00</CTQbSync__Qb_balance__c>"
                             + "</urn:sObjects>";*/
                
                String xmlInner2 = "</urn:upsert>";
                String xmlRecords = "";
                for(Integer recNo : objTbl.tableResult.keySet()){
                    //System.out.println(":::tableResult-RecordNo="+ recNo); 
                    Map<String, Map<String, String>> tempTable= objTbl.tableResult.get(recNo); 
                    xmlRecords += "<urn:sObjects><urn1:type>Account</urn1:type>";
                    for(String tblName : UtilClass.qbCustomerTable.keySet()){
                        if(tempTable.containsKey(tblName)){
                            Map<String, String> tempColmnName = UtilClass.qbCustomerTable.get(tblName);   
                            Map<String, String> tempColmnvalue = tempTable.get(tblName);
                            for(String colName :tempColmnName.keySet()){
                                if(tempColmnvalue.containsKey(colName)){
                                    xmlRecords += ("<" + tempColmnName.get(colName) + ">" + tempColmnvalue.get(colName) + "</" + tempColmnName.get(colName) + ">");
                                }
                            }
                        }
                    }
                    xmlRecords += "</urn:sObjects>";
                    /*
                    for(String tblName : tempTable.keySet()){                        
                        Map<String, String> tempColmn = tempTable.get(tblName);
                        for(String col :tempColmn.keySet()){
                            System.out.println(":::Result-RecNo="+ recNo+ ":::tableName="+ tblName + ":::ColName="+ col + ":::ColValue="+ tempColmn.get(col)); 
                        }
                    }*/
                }                
                //System.out.println(":::xmlRecords="+ xmlRecords);
                
                String xmlInner = xmlInner1 + xmlRecords + xmlInner2;
                String xmlBody = QBUserDetail.xmlHeader + xmlInner + QBUserDetail.xmlFooter;  
                xmlBody = xmlBody.replace("<urn:sObjects></urn:sObjects>", "");
                System.out.println(":::xmlBody="+ xmlBody);
                upsertIntoSFDC(xmlBody);
                         
           } catch (Exception th) {
               System.out.println(":::th="+ th.getMessage());
           }            
        }
        
        public void SyncInvoiceFromCustomer(XMLtoTableMap objTbl)
        {
            try {            
                String xmlInner1 = "<urn:upsert><urn:externalIDFieldName>CTQbSync__Qb_Invoice_id__c</urn:externalIDFieldName>";
                
                /*String xmlInner3 = "<urn:sObjects>"
                                 + "<urn1:type>Account</urn1:type>"           
                                 + "<Name>AS</Name>"                                 
                                 + "<CTQbSync__Qb_Sublevel__c>0</CTQbSync__Qb_Sublevel__c><CTQbSync__Qb_TimeCreated__c>2014-11-17T17:51:40+05:30</CTQbSync__Qb_TimeCreated__c><CTQbSync__Qb_customerJobStatus__c>None</CTQbSync__Qb_customerJobStatus__c><Site>Consummate</Site><CTQbSync__Qb_LastName__c>S</CTQbSync__Qb_LastName__c><CTQbSync__Qb_TotalBalance__c>0.00</CTQbSync__Qb_TotalBalance__c><Name>Abhishek S</Name><CTQbSync__Qb_Email__c>a@a.a</CTQbSync__Qb_Email__c><CTQbSync__Qb_IsActive__c>true</CTQbSync__Qb_IsActive__c><CTQbSync__Qb_FullName__c>Abhishek S</CTQbSync__Qb_FullName__c><CTQbSync__Qb_FirstName__c>Abhishek</CTQbSync__Qb_FirstName__c><CTQbSync__Qb_Customer_id__c>80000001-1416226900</CTQbSync__Qb_Customer_id__c><CTQbSync__Qb_TimeModified__c>2014-11-17T17:51:40+05:30</CTQbSync__Qb_TimeModified__c><CTQbSync__Qb_balance__c>0.00</CTQbSync__Qb_balance__c>"
                             + "</urn:sObjects>";*/
                
                String xmlInner2 = "</urn:upsert>";
                String xmlRecords = "";
                for(Integer recNo : objTbl.tableResult.keySet()){
                    //System.out.println(":::tableResult-RecordNo="+ recNo); 
                    Map<String, Map<String, String>> tempTable= objTbl.tableResult.get(recNo); 
                    xmlRecords += "<urn:sObjects><urn1:type>Opportunity</urn1:type>";
                    for(String tblName : UtilClass.qbInvoiceTable.keySet()){
                        if(tempTable.containsKey(tblName)){
                            Map<String, String> tempColmnName = UtilClass.qbInvoiceTable.get(tblName);   
                            Map<String, String> tempColmnvalue = tempTable.get(tblName);
                            for(String colName :tempColmnName.keySet()){
                                if(tempColmnvalue.containsKey(colName)){
                                    xmlRecords += ("<" + tempColmnName.get(colName) + ">" + tempColmnvalue.get(colName) + "</" + tempColmnName.get(colName) + ">");
                                }
                            }
                        }
                    }
                    xmlRecords += "</urn:sObjects>";
                    /*
                    for(String tblName : tempTable.keySet()){                        
                        Map<String, String> tempColmn = tempTable.get(tblName);
                        for(String col :tempColmn.keySet()){
                            System.out.println(":::Result-RecNo="+ recNo+ ":::tableName="+ tblName + ":::ColName="+ col + ":::ColValue="+ tempColmn.get(col)); 
                        }
                    }*/
                }                
                //System.out.println(":::xmlRecords="+ xmlRecords);
                
                String xmlInner = xmlInner1 + xmlRecords + xmlInner2;
                String xmlBody = QBUserDetail.xmlHeader + xmlInner + QBUserDetail.xmlFooter;  
                xmlBody = xmlBody.replace("<urn:sObjects></urn:sObjects>", "");
                System.out.println(":::xmlBody="+ xmlBody);
                upsertIntoSFDC(xmlBody);
                         
           } catch (Exception th) {
               System.out.println(":::th="+ th.getMessage());
           }            
        }
        
        private void upsertIntoSFDC(String xmlBody){
            try{
                PostMethod  post = new PostMethod(QBUserDetail.orgURL);            
                //post.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; MS Web Services Client Protocol 1.1.4322.573)" );
                post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
                post.setRequestHeader("SOAPAction", QBUserDetail.orgURL);
                //post.setRequestHeader("Authorization", "OAuth "+ sessionId);                                    
                post.setRequestEntity(new StringRequestEntity(xmlBody,"application/xml","UTF-8"));

                HttpClient client = new HttpClient();
                int result=client.executeMethod(post);
                System.out.println(":::result="+ result);
                System.out.println(":::result-post="+ post.getResponseBodyAsString());                   
            } catch (Exception th) {
               System.out.println(":::th="+ th.getMessage());
           } 
        }
        
	@Override
	public String sendRequestXML(String ticket, String strHCPResponse,
			String strCompanyFileName, String qbXMLCountry, int qbXMLMajorVers,
			int qbXMLMinorVers) {	
                return UtilClass.qbRequestXml.get(requestFor);
	}
        
}
