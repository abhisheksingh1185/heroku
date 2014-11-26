/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ct.quickbooks.ws;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Consummate
 */
public class UtilClass {
    public final static String CUSTOMER_REQUEST= "Customer" ;
    public final static String INVOICE_REQUEST= "Invoice" ;
        
    public static Map<String, String> qbCstmrBillAddCell = new HashMap<String, String>() {{
        put("City", "BillingCity");
        put("Addr1", "BillingCountry");
        put("PostalCode", "BillingPostalCode");
        put("Addr2", "BillingState");
        put("Addr3", "BillingStreet");
    }};
    
    public static Map<String, String> qbCstmrShipAddressCell = new HashMap<String, String>() {{
        put("City", "ShippingCity");
        put("Addr1", "ShippingCountry");
        put("PostalCode", "ShippingPostalCode");
        put("Addr2", "ShippingState");
        put("Addr3", "ShippingStreet");
    }};
    
    public static Map<String, String> qbCstmrSalesTaxCodeRefCell = new HashMap<String, String>() {{
        put("FullName", "CTQbSync__Qb_SalesTaxCode__c");
    }};
    
    public static Map<String, String> qbCstmrItemSalesTaxRefCell = new HashMap<String, String>() {{
        put("FullName", "CTQbSync__Qb_ItemSalesTaxRef__c");
    }};
    
    public static Map<String, String> qbCstmrCustomerTypeRefCell = new HashMap<String, String>() {{
        put("FullName", "Industry");
    }};
    
    public static Map<String, String> qbCstmrCustomerRetCell = new HashMap<String, String>() {{
        put("ListID", "CTQbSync__Qb_Customer_id__c");
        put("Name", "Name");
        put("AccountNumber", "AccountNumber");
        put("Phone", "Phone");
        put("CompanyName", "Site");
        put("Fax", "Fax");
        put("FirstName", "CTQbSync__Qb_FirstName__c");
        put("LastName", "CTQbSync__Qb_LastName__c");
        put("MiddleName", "CTQbSync__Qb_MiddleName__c");
        put("Email", "CTQbSync__Qb_Email__c");
        put("AltContact", "CTQbSync__Qb_AltContact__c");
        put("Balance", "CTQbSync__Qb_balance__c");
        put("TotalBalance", "CTQbSync__Qb_TotalBalance__c");
        put("FullName", "CTQbSync__Qb_FullName__c");
        put("IsActive", "CTQbSync__Qb_IsActive__c");
        put("Sublevel", "CTQbSync__Qb_Sublevel__c");
        put("TimeCreated", "CTQbSync__Qb_TimeCreated__c");
        put("TimeModified", "CTQbSync__Qb_TimeModified__c");
        put("JobStatus", "CTQbSync__Qb_customerJobStatus__c");
        put("JobStartDate", "CTQbSync__Qb_CustomerJobStartDate__c");
        put("JobEndDate", "CTQbSync__Qb_JobEndDate__c");
        put("JobProjectedEndDate", "CTQbSync__Qb_JobProjectedEndDate__c");
        put("JobDesc", "CTQbSync__Qb_JobDesc__c");
    }};
    
    public static Map<String, String> qbCstmrCreditCardInfoCell = new HashMap<String, String>() {{
        put("CreditCardNumber", "CTQbSync__Qb_CustomerCreditCardNumber__c");
        put("ExpirationMonth", "CTQbSync__Qb_CustomerExpirationMonth__c");
        put("ExpirationYear", "CTQbSync__Qb_CustomerCreditCardExpirationYear__c");
        put("CreditCardAddress", "CTQbSync__Qb_CustomerCreditCardAddress__c");
        put("CreditCardPostalCode", "CTQbSync__Qb_CustomerCardPostalCode__c");
        put("NameOnCard", "CTQbSync__Qb_NameOnCard__c");        
    }};
    
    public static Map<String, String> qbCstmrJobTypeRefCell = new HashMap<String, String>() {{
        put("FullName", "CTQbSync__Qb_CustomerJobType__c");
    }};
    
    public static Map<String, String> qbCstmrPreferredPaymentMethodRefCell = new HashMap<String, String>() {{
        put("FullName", "CTQbSync__Qb_CustomerPreferredPaymentMethod__c");
    }};
    
    public static Map<String, String> qbCstmrPriceLevelRefCell = new HashMap<String, String>() {{
        put("FullName", "CTQbSync__Qb_PriceLevel__c");
    }};
    
    public static Map<String, Map<String, String>> qbCustomerTable = new HashMap<String, Map<String, String>>(){  
        {
            put("BillAddress", qbCstmrBillAddCell);
            put("ShipAddress", qbCstmrShipAddressCell);
            put("SalesTaxCodeRef", qbCstmrSalesTaxCodeRefCell);
            put("ItemSalesTaxRef", qbCstmrItemSalesTaxRefCell);
            put("CustomerRet", qbCstmrCustomerRetCell);
            put("CustomerTypeRef", qbCstmrCustomerTypeRefCell);
            put("CreditCardInfo", qbCstmrCreditCardInfoCell);
            put("JobTypeRef", qbCstmrJobTypeRefCell);
            put("PreferredPaymentMethodRef", qbCstmrPreferredPaymentMethodRefCell);
            put("PriceLevelRef", qbCstmrPriceLevelRefCell);
        }
    };
    
    /////INVOICE TABLE/////////////
    public static Map<String, String> qbInvcInvoiceRetCell = new HashMap<String, String>() {{        
        put("BalanceRemaining", "Amount");
        put("IsToBePrinted", "CTQbSync__Qb_IsToBePrinted__c");
        put("EditSequence", "CTQbSync__Qb_EditSequence__c");
        put("Subtotal", "CTQbSync__Qb_Subtotal__c");
        put("TxnID", "CTQbSync__Qb_Invoice_id__c");
        put("IsPending", "CTQbSync__Qb_IsPending__c");
        put("IsFinanceCharge", "CTQbSync__Qb_IsFinanceCharge__c");
        put("DueDate", "CloseDate");
        put("AppliedAmount", "CTQbSync__Qb_AppliedAmount__c");
        put("SalesTaxPercentage", "CTQbSync__Qb_SalesTaxPercentage__c");
        put("SalesTaxTotal", "CTQbSync__Qb_SalesTaxTotal__c");
        put("TxnNumber", "CTQbSync__TxnNumber__c");
        put("TxnDate", "CTQbSync__TxnDate__c");
        put("RefNumber", "CTQbSync__RefNumber__c");
        put("ShipDate", "CTQbSync__ShipDate__c"); 
        put("IsPaid", "CTQbSync__IsPaid__c");
    }};
    
    public static Map<String, String> qbInvcCustomerRefCell = new HashMap<String, String>() {{
        put("FullName", "Name");
        put("ListID", "CTQbSync__Qb_Customer_id__c");
    }};
    
    public static Map<String, String> qbInvcCustomerMsgRefCell = new HashMap<String, String>() {{
        put("FullName", "Description");        
    }};
    
    public static Map<String, String> qbInvcItemSalesTaxRefCell = new HashMap<String, String>() {{
        put("FullName", "StageName");        
    }};
    
    public static Map<String, Map<String, String>> qbInvoiceTable = new HashMap<String, Map<String, String>>(){  
        {            
            put("InvoiceRet", qbInvcInvoiceRetCell);
            put("CustomerRef", qbInvcCustomerRefCell);
            put("CustomerMsgRef", qbInvcCustomerMsgRefCell);
            put("ItemSalesTaxRef", qbInvcItemSalesTaxRefCell);
        }
    };
    
    public static Map<String, String> qbRequestXml = new HashMap<String, String>() {{
        put(CUSTOMER_REQUEST, "<?xml version=\"1.0\" encoding=\"utf-8\"?><?qbxml version=\"4.0\"?><QBXML><QBXMLMsgsRq onError=\"stopOnError\"><CustomerQueryRq requestID=\"1\"></CustomerQueryRq></QBXMLMsgsRq></QBXML>");
        put(INVOICE_REQUEST, "<?xml version=\"1.0\" encoding=\"utf-8\"?><?qbxml version=\"4.0\"?><QBXML><QBXMLMsgsRq onError=\"stopOnError\"><InvoiceQueryRq requestID=\"2\"></InvoiceQueryRq></QBXMLMsgsRq></QBXML>");
    }};
    
}
