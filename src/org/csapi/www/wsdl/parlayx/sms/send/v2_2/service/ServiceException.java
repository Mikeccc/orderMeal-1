
/**
 * ServiceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

package org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

public class ServiceException extends java.lang.Exception{
    
    private org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE faultMessage;
    
    public ServiceException() {
        super("ServiceException");
    }
           
    public ServiceException(java.lang.String s) {
       super(s);
    }
    
    public ServiceException(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE msg){
       faultMessage = msg;
    }
    
    public org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    