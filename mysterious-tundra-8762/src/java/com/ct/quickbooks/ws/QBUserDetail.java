/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ct.quickbooks.ws;
import java.util.Set;
import java.util.HashSet;
import javax.xml.bind.DatatypeConverter ;
import java.security.MessageDigest;

/**
 *
 * @author Consummate
 */
public class QBUserDetail {
    public static String uid;
    public static String sessionId;
    public static String serverName;
    public static String OrgType;
    public static String organizationId;
    public static String orgURL;      
    public static String xmlHeader ;
    public static String xmlFooter = "</soapenv:Body></soapenv:Envelope>"; 
    public static Integer requestSize;
    public static Set<String> SalesForceobjects;
    
    public static void findUserOrgDetail(String strUid)
    { 
        try {    
            //fetch Detail from DB using UID
            sessionId ="00DG0000000CJKv!ARoAQDqWT_cJqvzaMS.egBJsyOY90vQJas9lmg3tVBgp3lCwjHj5DDKPpxIzC2F1kTAqx6YWdbeDNjXkAzgzlslHzaEIc.zj";
            organizationId = "00DG0000000CJKv";
            OrgType = "login";
            serverName ="na11";
            // Get target URL
            orgURL = "https://"+serverName+".salesforce.com/services/Soap/u/25.0/"+ organizationId;
            SalesForceobjects = new HashSet<String>();
            SalesForceobjects.add("Customer");
            SalesForceobjects.add("Invoice");
            xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"                    
                    + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\""
                    + " xmlns:urn=\"urn:partner.soap.sforce.com\""
                    + " xmlns:urn1=\"urn:sobject.partner.soap.sforce.com\">"                    
                    + ">"
                      + "<soapenv:Header><urn:SessionHeader>"
                      + "<urn:sessionId>"+ sessionId +"</urn:sessionId>"
                      + "</urn:SessionHeader></soapenv:Header><soapenv:Body>";
            
                       
            String encrypted = EncryptDecryptWithDESUsingPassPhrase.encrypt("00DG0000000CJKv!ARoAQDqWT_cJqvzaMS.egBJsyOY90vQJas9lmg3tVBgp3lCwjHj5DDKPpxIzC2F1kTAqx6YWdbeDNjXkAzgzlslHzaEIc.zj");
            System.out.println("encrypted: " + encrypted);
            
            String decrypted = EncryptDecryptWithDESUsingPassPhrase.decrypt(encrypted);
            System.out.println("Decrypted: " + decrypted);
            
        } catch (Exception th) {
           System.out.println(":::th="+ th.getMessage());
       }               
    }    
    
}
