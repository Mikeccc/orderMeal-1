
/**
 * SendSmsServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
        package org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

        /**
        *  SendSmsServiceMessageReceiverInOut message receiver
        */

        public class SendSmsServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        SendSmsServiceSkeleton skel = (SendSmsServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("sendSmsLogo".equals(methodName)){
                
                org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE sendSmsLogoResponse1 = null;
	                        org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE wrappedParam =
                                                             (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               sendSmsLogoResponse1 =
                                                   
                                                   
                                                         skel.sendSmsLogo(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), sendSmsLogoResponse1, false);
                                    } else 

            if("sendSmsRingtone".equals(methodName)){
                
                org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE sendSmsRingtoneResponse3 = null;
	                        org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE wrappedParam =
                                                             (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               sendSmsRingtoneResponse3 =
                                                   
                                                   
                                                         skel.sendSmsRingtone(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), sendSmsRingtoneResponse3, false);
                                    } else 

            if("getSmsDeliveryStatus".equals(methodName)){
                
                org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE getSmsDeliveryStatusResponse5 = null;
	                        org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE wrappedParam =
                                                             (org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getSmsDeliveryStatusResponse5 =
                                                   
                                                   
                                                         skel.getSmsDeliveryStatus(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getSmsDeliveryStatusResponse5, false);
                                    } else 

            if("sendSms".equals(methodName)){
                
                org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE sendSmsResponse7 = null;
	                        org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE wrappedParam =
                                                             (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               sendSmsResponse7 =
                                                   
                                                   
                                                         skel.sendSms(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), sendSmsResponse7, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        } catch (PolicyException e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"PolicyException");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
         catch (ServiceException e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"ServiceException");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
        
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE wrapsendSmsRingtone(){
                                org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE wrappedElement = new org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE wrapsendSmsLogo(){
                                org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE wrappedElement = new org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE wrapsendSms(){
                                org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE wrappedElement = new org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE wrapgetSmsDeliveryStatus(){
                                org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE wrappedElement = new org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.class.equals(type)){
                
                           return org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    