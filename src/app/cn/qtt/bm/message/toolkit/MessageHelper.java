/*************************************************************
 * Title: MultimediaMessageHelper.java
 * Description: 
 * Author: 黄绍斌
 * Email: huangshaobin@qtt.cn
 * CreateTime: Feb 28, 2012 9:45:40 AM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.message.toolkit;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import cn.qtt.core.message.conts.MessageConstants;
import cn.qtt.core.toolkit.encrypt.Encryption;

/**
 * 
 */
public class MessageHelper {

	private static final OMFactory OM_FACTORY = OMAbstractFactory.getOMFactory();

	private static final SOAPFactory SOAP_FACTORY = OMAbstractFactory.getSOAP11Factory();

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

	/**
	 * @param omNamespace
	 * @param name
	 * @return
	 */
	public static SOAPHeaderBlock createSoapHeaderFrom(OMNamespace omNamespace, String name) {
		return SOAP_FACTORY.createSOAPHeaderBlock(name, omNamespace);
	}

	/**
	 * @param omNamespace
	 * @param name
	 * @param text
	 * @return
	 */
	public static SOAPHeaderBlock createSoapHeaderFrom(OMNamespace omNamespace, String name, String text) {
		SOAPHeaderBlock headerBlock = createSoapHeaderFrom(omNamespace, name);
		headerBlock.addChild(SOAP_FACTORY.createOMText(text));
		return headerBlock;
	}
	
	public static SOAPHeaderBlock createSOAPHeader4SMSFrom(String spId, String spPassword, String timestamp, String spServiceId, String oaStr, String faStr,
			String tokenStr) {
		OMNamespace omNamespace = OM_FACTORY.createOMNamespace(MessageConstants.SOAP_NAME_SPACE, Constants.SOAP_HEADER_PREFIX_4_SMS);
		SOAPHeaderBlock soapHeader = SOAP_FACTORY.createSOAPHeaderBlock(MessageConstants.SOAP_HEADER_REQUEST, omNamespace);
		// SPID的头部信息
		SOAPHeaderBlock soapSpID = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SP_ID, spId);
		// 密码的头部信息，使用MD5加密
		SOAPHeaderBlock soapSpPassword = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SP_PASSWORD, Encryption.encryptByMD5(spId + spPassword
				+ timestamp));
		// 时间戳的头部信息
		SOAPHeaderBlock soapTimestamp = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_TIMESTAMP, timestamp);
		// ServiceID的头部信息
		SOAPHeaderBlock soapServiceID = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SERVICE_ID, spServiceId);
		// OaStr的头部信息
		SOAPHeaderBlock soapOaStr = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_OA, oaStr);
		// FaStr的头部信息
		SOAPHeaderBlock soapFaStr = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_FA, faStr);
		// Token的头部信息
		SOAPHeaderBlock soapToken = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_TOKEN, tokenStr);
		// 
		soapHeader.setMustUnderstand(false);
		addChildren(soapHeader, new SOAPHeaderBlock[] { soapSpID, soapSpPassword, soapTimestamp, soapServiceID, soapOaStr, soapFaStr, soapToken });
		return soapHeader;
	}

	public static SOAPHeaderBlock createSoapHeaderFrom(String spId, String spPassword, String timestamp, String spServiceId, String oaStr, String faStr,
			String tokenStr,String cpCode) {
		OMNamespace omNamespace = OM_FACTORY.createOMNamespace(MessageConstants.SOAP_NAME_SPACE, MessageConstants.SOAP_HEADER_PREFIX);
		SOAPHeaderBlock soapHeader = SOAP_FACTORY.createSOAPHeaderBlock(MessageConstants.SOAP_HEADER_REQUEST, omNamespace);
		// SPID的头部信息
		SOAPHeaderBlock soapSpID = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SP_ID, spId);
		// 密码的头部信息，使用MD5加密
		SOAPHeaderBlock soapSpPassword = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SP_PASSWORD, Encryption.encryptByMD5(spId + spPassword
				+ timestamp));
		// 时间戳的头部信息
		SOAPHeaderBlock soapTimestamp = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_TIMESTAMP, timestamp);
		// ServiceID的头部信息
		SOAPHeaderBlock soapServiceID = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SERVICE_ID, spServiceId);
		// OaStr的头部信息
		SOAPHeaderBlock soapOaStr = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_OA, oaStr);
		// FaStr的头部信息
		SOAPHeaderBlock soapFaStr = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_FA, faStr);
		//SettleObject
		//SOAPHeaderBlock soapSettleObject = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SettleObject, tokenStr);
				
		// Token的头部信息
		SOAPHeaderBlock soapToken = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_TOKEN, tokenStr);
		// 
		soapHeader.setMustUnderstand(false);
		//CP 不为空的情况下
		if(StringUtils.isNotBlank(cpCode)){
		   // settleObject的头部信息 为CPCODE
		   SOAPHeaderBlock soapSettleObject = createSoapHeaderFrom(omNamespace, MessageConstants.SOAP_HEADER_SETTLE_OBJECT, cpCode);
		   addChildren(soapHeader, new SOAPHeaderBlock[] { soapSpID, soapSpPassword, soapTimestamp, soapServiceID, soapOaStr, soapFaStr, soapToken ,soapSettleObject });
		}else{
			addChildren(soapHeader, new SOAPHeaderBlock[] { soapSpID, soapSpPassword, soapTimestamp, soapServiceID, soapOaStr, soapFaStr, soapToken });
		}
		return soapHeader;
	}
}
