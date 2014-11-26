/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ct.quickbooks.ws;
import org.xml.sax.InputSource;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Map;
import java.util.HashMap;
//import java.util.Set;
//import java.util.HashSet;

/**
 *
 * @author Consummate
 */
public class XMLtoTableMap {
    public String recordKey;
    public String recordKeyParent;    
    public Map<Integer, Map<String, Map<String, String>>> tableResult;
    public Integer counter;
    public XMLtoTableMap(String strRecordKey, String strRecordKeyParent){        
        tableResult = new HashMap<Integer, Map<String, Map<String, String>>>();// Row => Table => Column
        counter = 0;
        recordKey = strRecordKey;
        recordKeyParent = strRecordKeyParent;
    }
    
    public void createTableMap(String xmlStr){
        Document doc = xmlStringToDoc(xmlStr);
        printNode(doc, ""); 
    }
    
    public Document xmlStringToDoc(String xmlStr)
    {   
        Document doc = null;
        try {
            DOMParser parser = new DOMParser();
            parser.parse(new InputSource(new java.io.StringReader(xmlStr)));
            doc = parser.getDocument();
            //String message = doc.getDocumentElement().getTextContent();
            //System.out.println(message);
                 
        } catch (Exception th) {
           System.out.println(":::th="+ th.getMessage());
       } 
       return doc;       
    }
    
    private void printNode(Node node, String indent){
        switch (node.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("::Start Parent:::\n");
                // recurse on each child
                NodeList nodes = node.getChildNodes();
                //System.out.println("::Doc Node="+ node.getNodeName());
                if (nodes != null) {
                    for (int i=0; i<nodes.getLength(); i++) {
                        printNode(nodes.item(i), "");
                    }
                }
                break;

            case Node.ELEMENT_NODE:
                /*String name = node.getNodeName();
                System.out.println("::indent=" + indent + ":::::NodeName="+ name);
                System.out.print(indent + "<" + name);
                NamedNodeMap attributes = node.getAttributes();
                for (int i=0; i<attributes.getLength(); i++) {
                    Node current = attributes.item(i);
                    System.out.print(
                        " " + current.getNodeName() +
                        "=\"" + current.getNodeValue() +
                        "\"");
                }
                System.out.print(">");
                */

                // recurse on each child

                NodeList children = node.getChildNodes();                       
                if (children != null) {
                    //System.out.print("::Element Node="+ node.getNodeName() + ":::::children.getLength()="+ children.getLength() +"::::ParentNode="+node.getParentNode().getNodeName());
                    for (int i=0; i<children.getLength(); i++) {                            
                        printNode(children.item(i), node.getNodeName() + "");
                    }
                }
                //System.out.print("</" + name + ">");
                break;

            case Node.TEXT_NODE:
                String parentNodeName= node.getParentNode().getParentNode().getNodeName();
                String nodeName = indent;                
                //System.out.print("::Name="+ indent + "::value="+ node.getNodeValue()+"::ParentNode="+parentNodeName);
                checkNewRecord(nodeName, parentNodeName);
                if(node.getNodeValue() != null && node.getNodeValue().trim() != "")storeRecord(nodeName, node.getNodeValue(), parentNodeName);
                break;
        }
    }
    
    private void checkNewRecord(String nodeName, String parentNodeName){
        if(nodeName.equalsIgnoreCase(recordKey) && parentNodeName.equalsIgnoreCase(recordKeyParent)){
            counter = counter + 1;
            if(!tableResult.containsKey(counter))tableResult.put(counter, new HashMap<String, Map<String, String>>());            
        }
    }
    
    private void storeRecord(String nodeName, String nodeValue, String parentNodeName){        
        if(tableResult.containsKey(counter)){
            Map<String, Map<String, String>> tempTable= tableResult.get(counter);
            if(!tempTable.containsKey(parentNodeName))tempTable.put(parentNodeName, new HashMap<String, String>()); 
            Map<String, String> tempColumn = tempTable.get(parentNodeName);
            tempColumn.put(nodeName, nodeValue);
        }  
    }
}
