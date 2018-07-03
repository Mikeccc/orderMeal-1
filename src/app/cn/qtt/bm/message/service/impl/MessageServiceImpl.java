/*************************************************************
 * Title: MessageServiceImpl.java
 * Description: 
 * Author: Huang Shaobin
 * Email: huangshaobin@qtt.cn
 * CreateTime: Jul 3, 2012 5:21:27 PM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.message.service.impl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.databinding.types.URI.MalformedURIException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSms;
import org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsE;
import org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponse;
import org.csapi.www.schema.parlayx.sms.send.v2_2.local.SendSmsResponseE;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.PolicyException;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.SendSmsServiceStub;
import org.csapi.www.wsdl.parlayx.sms.send.v2_2.service.ServiceException;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.message.model.Mms;
import app.cn.qtt.bm.message.model.Result;
import app.cn.qtt.bm.message.model.Sms;
import app.cn.qtt.bm.message.service.IConfigureService;
import app.cn.qtt.bm.message.service.IMessageService;
import app.cn.qtt.bm.message.toolkit.Axis2Helper;
import app.cn.qtt.bm.message.toolkit.MessageHelper;

/**
 * 
 */
public class MessageServiceImpl implements IMessageService {

	private final Log log = LogFactory.getLog(getClass());

	private final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	private IConfigureService configureService;

	public void afterPropertiesSet() throws Exception {
	}

	public IConfigureService getConfigureService() {
		return configureService;
	}

	protected Result getSuccessResultFrom(String message) {
		return new Result("000000", message);
	}

	/**
	 * @param mms
	 * @return
	 * @see cn.qtt.core.message.service.IMessageService#sendMms(cn.qtt.core.message.model.Mms)
	 */
	public Result sendMms(Mms mms) {
		return null;
	}

	/**
	 * @param sms
	 * @return
	 * @see cn.qtt.core.message.service.IMessageService#sendSms(cn.qtt.core.message.model.Sms)
	 */
	public Result sendSms(Sms sms) {
		SendSmsServiceStub stub = null;
		ServiceClient serviceClient = null;
		Result result=null;
		try {
			String sendUrl = CacheConstants.getParamValueByName(Constants.SMS_SEND_URL);
			stub = new SendSmsServiceStub(sendUrl);
			serviceClient = stub._getServiceClient();
			final String spId = CacheConstants.getParamValueByName(Constants.SMS_SP_ID);
			final String spPassword = CacheConstants.getParamValueByName(Constants.DEVICE_PASSWORD);
			final String timestamp = dateFormat.format(sms.getCreateTime());
			final String spServiceId = CacheConstants.getParamValueByName(Constants.SMS_SP_SERVICE_ID);
			final String cpCode = "";
			final String oaStr = sms.getOa();
			final String faStr = sms.getFa();
			final String tokenStr = null;
			
			SOAPHeaderBlock headerBlock = getSOAPHeaderBlockFrom(spId, spPassword, timestamp, spServiceId, oaStr, faStr, tokenStr,cpCode);
			
			serviceClient.addHeader(headerBlock);
			SendSms sendSms = new SendSms();
			
			sendSms.setAddresses(sms.getAddresses());
			sendSms.setMessage(sms.getMessage());
			
			sendSms.setSenderName(sms.getSenderName());
			
			SendSmsE sendSmsE = new SendSmsE();
			sendSmsE.setSendSms(sendSms);
			SendSmsResponseE sendSmsResponseE = stub.sendSms(sendSmsE);
			SendSmsResponse sendSmsResponse = sendSmsResponseE.getSendSmsResponse();
			String resultStr = sendSmsResponse.getResult();
			if (StringUtils.isNotBlank(resultStr)) {
				result= getSuccessResultFrom(resultStr);
			}
		}
		catch (AxisFault e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (PolicyException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (ServiceException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (MalformedURIException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		finally {
			Axis2Helper.cleanupTransport(serviceClient, log);
			Axis2Helper.cleanup(serviceClient, log);
			Axis2Helper.cleanup(stub, log);
		}
		return result;
	}

	private SOAPHeaderBlock getSOAPHeaderBlockFrom(String spId, String spPassword, String timestamp, String spServiceId, String oaStr, String faStr,
			String tokenStr,String cpCode) {
		return MessageHelper.createSoapHeaderFrom(spId, spPassword, timestamp, spServiceId, oaStr, faStr, tokenStr,cpCode);
	}

	public void setConfigureService(IConfigureService configureService) {
		this.configureService = configureService;
	}
	/**
	 * 添加子节点
	 * 
	 * @param parent
	 * @param children
	 */
	public static void addChildren(SOAPHeaderBlock parent, SOAPHeaderBlock[] children) {
		if (parent != null && children != null && children.length > 0) {
			for (SOAPHeaderBlock child : children) {
				if (child != null) {
					parent.addChild(child);
				}
			}
		}
	}
}
