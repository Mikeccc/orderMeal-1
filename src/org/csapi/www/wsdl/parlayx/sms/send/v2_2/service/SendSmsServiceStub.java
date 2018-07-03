
/**
 * SendSmsServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
        package org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

        

        /*
        *  SendSmsServiceStub java implementation
        */

        
        public class SendSmsServiceStub extends org.apache.axis2.client.Stub
        {
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return Long.toString(System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("SendSmsService" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[4];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface", "sendSmsLogo"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface", "sendSmsRingtone"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[1]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface", "getSmsDeliveryStatus"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[2]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface", "sendSms"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[3]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","PolicyException"),"org.csapi.www.schema.parlayx.common.v2_1.PolicyExceptionE");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException");
              faultMessageMap.put( new javax.xml.namespace.QName("http://www.csapi.org/schema/parlayx/common/v2_1","ServiceException"),"org.csapi.www.schema.parlayx.common.v2_1.ServiceExceptionE");
           


    }

    /**
      *Constructor that takes in a configContext
      */

    public SendSmsServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public SendSmsServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        configurationContext = _serviceClient.getServiceContext().getConfigurationContext();

        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
    
    }

    /**
     * Default Constructor
     */
    public SendSmsServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://localhost:9080/SendSmsService/services/SendSms" );
                
    }

    /**
     * Default Constructor
     */
    public SendSmsServiceStub() throws org.apache.axis2.AxisFault {
        
                    this("http://localhost:9080/SendSmsService/services/SendSms" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public SendSmsServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * 
                     * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#sendSmsLogo
                     * @param sendSmsLogo48
                    
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException : 
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException : 
                     */

                    

                            public  org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE sendSmsLogo(

                            org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE sendSmsLogo48)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("\"\"");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendSmsLogo48,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "sendSmsLogo")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex;
                        }
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#startsendSmsLogo
                    * @param sendSmsLogo48
                
                */
                public  void startsendSmsLogo(

                 org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE sendSmsLogo48,

                  final org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("\"\"");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendSmsLogo48,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "sendSmsLogo")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultsendSmsLogo(
                                        (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorsendSmsLogo(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
														callback.receiveErrorsendSmsLogo((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex);
											            return;
										            }
										            
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
														callback.receiveErrorsendSmsLogo((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorsendSmsLogo(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsLogo(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsLogo(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsLogo(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsLogo(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsLogo(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsLogo(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsLogo(f);
                                            }
									    } else {
										    callback.receiveErrorsendSmsLogo(f);
									    }
									} else {
									    callback.receiveErrorsendSmsLogo(f);
									}
								} else {
								    callback.receiveErrorsendSmsLogo(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorsendSmsLogo(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#sendSmsRingtone
                     * @param sendSmsRingtone50
                    
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException : 
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException : 
                     */

                    

                            public  org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE sendSmsRingtone(

                            org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE sendSmsRingtone50)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
              _operationClient.getOptions().setAction("\"\"");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendSmsRingtone50,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "sendSmsRingtone")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex;
                        }
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#startsendSmsRingtone
                    * @param sendSmsRingtone50
                
                */
                public  void startsendSmsRingtone(

                 org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE sendSmsRingtone50,

                  final org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
             _operationClient.getOptions().setAction("\"\"");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendSmsRingtone50,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "sendSmsRingtone")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultsendSmsRingtone(
                                        (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorsendSmsRingtone(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
														callback.receiveErrorsendSmsRingtone((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex);
											            return;
										            }
										            
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
														callback.receiveErrorsendSmsRingtone((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorsendSmsRingtone(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsRingtone(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsRingtone(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsRingtone(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsRingtone(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsRingtone(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsRingtone(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSmsRingtone(f);
                                            }
									    } else {
										    callback.receiveErrorsendSmsRingtone(f);
									    }
									} else {
									    callback.receiveErrorsendSmsRingtone(f);
									}
								} else {
								    callback.receiveErrorsendSmsRingtone(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorsendSmsRingtone(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[1].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[1].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#getSmsDeliveryStatus
                     * @param getSmsDeliveryStatus52
                    
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException : 
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException : 
                     */

                    

                            public  org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE getSmsDeliveryStatus(

                            org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE getSmsDeliveryStatus52)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
              _operationClient.getOptions().setAction("\"\"");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    getSmsDeliveryStatus52,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "getSmsDeliveryStatus")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex;
                        }
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#startgetSmsDeliveryStatus
                    * @param getSmsDeliveryStatus52
                
                */
                public  void startgetSmsDeliveryStatus(

                 org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE getSmsDeliveryStatus52,

                  final org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
             _operationClient.getOptions().setAction("\"\"");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    getSmsDeliveryStatus52,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "getSmsDeliveryStatus")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultgetSmsDeliveryStatus(
                                        (org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorgetSmsDeliveryStatus(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
														callback.receiveErrorgetSmsDeliveryStatus((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex);
											            return;
										            }
										            
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
														callback.receiveErrorgetSmsDeliveryStatus((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorgetSmsDeliveryStatus(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetSmsDeliveryStatus(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetSmsDeliveryStatus(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetSmsDeliveryStatus(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetSmsDeliveryStatus(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetSmsDeliveryStatus(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetSmsDeliveryStatus(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorgetSmsDeliveryStatus(f);
                                            }
									    } else {
										    callback.receiveErrorgetSmsDeliveryStatus(f);
									    }
									} else {
									    callback.receiveErrorgetSmsDeliveryStatus(f);
									}
								} else {
								    callback.receiveErrorgetSmsDeliveryStatus(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorgetSmsDeliveryStatus(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[2].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[2].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * 
                     * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#sendSms
                     * @param sendSms54
                    
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException : 
                     * @throws org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException : 
                     */

                    

                            public  org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE sendSms(

                            org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE sendSms54)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException
                        ,org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
              _operationClient.getOptions().setAction("\"\"");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendSms54,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "sendSms")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        System.out.println("====================="+f.getMessage());
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        System.out.println(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex;
                        }
                        
                        if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
                          throw (org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * 
                * @see org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsService#startsendSms
                    * @param sendSms54
                
                */
                public  void startsendSms(

                 org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE sendSms54,

                  final org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
             _operationClient.getOptions().setAction("\"\"");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    sendSms54,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.csapi.org/wsdl/parlayx/sms/send/v2_2/interface",
                                                    "sendSms")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultsendSms(
                                        (org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorsendSms(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException){
														callback.receiveErrorsendSms((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException)ex);
											            return;
										            }
										            
													if (ex instanceof org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException){
														callback.receiveErrorsendSms((org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorsendSms(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSms(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSms(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSms(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSms(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSms(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSms(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorsendSms(f);
                                            }
									    } else {
										    callback.receiveErrorsendSms(f);
									    }
									} else {
									    callback.receiveErrorsendSms(f);
									}
								} else {
								    callback.receiveErrorsendSms(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorsendSms(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[3].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[3].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

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

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://localhost:9080/SendSmsService/services/SendSms
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
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneE.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoE.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusE.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             


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



    
   }
   