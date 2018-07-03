
/**
 * SendSmsServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

    package org.csapi.www.wsdl.parlayx.sms.send.v2_2.service;

    /**
     *  SendSmsServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SendSmsServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SendSmsServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SendSmsServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for sendSmsLogo method
            * override this method for handling normal response from sendSmsLogo operation
            */
           public void receiveResultsendSmsLogo(
                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsLogoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sendSmsLogo operation
           */
            public void receiveErrorsendSmsLogo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sendSmsRingtone method
            * override this method for handling normal response from sendSmsRingtone operation
            */
           public void receiveResultsendSmsRingtone(
                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsRingtoneResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sendSmsRingtone operation
           */
            public void receiveErrorsendSmsRingtone(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSmsDeliveryStatus method
            * override this method for handling normal response from getSmsDeliveryStatus operation
            */
           public void receiveResultgetSmsDeliveryStatus(
                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.GetSmsDeliveryStatusResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSmsDeliveryStatus operation
           */
            public void receiveErrorgetSmsDeliveryStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sendSms method
            * override this method for handling normal response from sendSms operation
            */
           public void receiveResultsendSms(
                    org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sendSms operation
           */
            public void receiveErrorsendSms(java.lang.Exception e) {
            }
                


    }
    