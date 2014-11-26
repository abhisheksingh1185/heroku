/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ct.quickbooks.ws;
import java.lang.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
/**
 *
 * @author Consummate
 */
@WebService()
public class CTSyncUserService {
    @WebMethod
    public String GetUserId(String user_id){
        return "";
    }  
    
    @WebMethod 
    public String User_Registration(String organization_id, String user_id, String access_token, String host_url, String SalesForceEnvironment, String quickbook_objects, String SyncMode)
    {
      return "";  
    }
   
}
