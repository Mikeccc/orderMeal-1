
 /*
 * 文 件 名:  SmsMgr.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-6-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.manage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.utils.TxUUIDGenerator;
import app.cn.qtt.bm.manage.ISmsMgr;
import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.service.ISmsService;
import app.cn.qtt.bm.service.pojo.SmsRequestBean;
import app.cn.qtt.bm.service.pojo.SmsResponseBean;


 /**       
 * 项目名称：BespeakMeal    
 * 类名称：SmsMgr    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-6-27 下午2:32:35    
 * 修改人：linch   
 * 修改时间：2013-6-27 下午2:32:35    
 * 修改备注：    
 * @version       
 */

public class SmsMgr extends BaseService implements ISmsMgr {
	
	private ISmsService smsService;

	

	/**
	* 方法名称:      saveSmsByTemplate  
	* 方法描述:      根据模版保存待发送短信
	* @param template			模版
	* @param params				替换参数
	* @param receiverMdn		接收方号码
	* @param type				类别
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-6-27 下午4:55:39
	*/ 

	public void saveSmsByTemplate(String template, List<String> params ,String receiverMdn , String type)
			throws CMException, CMRollBackException {
		final String xFunctionName = "saveSmsByTemplate";
		gLogger.begin(xFunctionName);
		
		try {
			Assert.notNull(template);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("template is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "template");
		}
		
		try {
			Assert.notNull(params);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("params is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "params");
		}
		
		try {
			Assert.notNull(receiverMdn);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("receiverMdn is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "receiverMdn");
		}
		
		try {
			Assert.notNull(type);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("type is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "type");
		}

		
		try{
			//替换模版
			for(int i = 0 ; i < params.size() ; i++){
				String target = "{" + (i + 1) + "}";
				
				if(StringUtils.indexOf(template, target) < 0){
					throw new Exception("template or params error！");
				}
				template = template.replace(target, params.get(i));
			}
			
			gLogger.info("replace  message :" + template );
			
			SmsRequestBean requestBean = new SmsRequestBean();
			
			
			//保存对象
			TSendSmsBuffer buffer = new TSendSmsBuffer();
			buffer.setBufferMessage(template);
			buffer.setBufferSn(TxUUIDGenerator.getUUID());
			buffer.setBufferSenderName(CacheConstants.getParamValueByName(Constants.SMS_SENDER_NAME));
			buffer.setBufferSourceAddr(CacheConstants.getParamValueByName(Constants.SMS_SOURCE_ADDR));
			buffer.setBufferReceiverMdn(receiverMdn);
			buffer.setBufferStatus(Constants.EFFECTIVE_STATUS);
			buffer.setBufferCreateTime(new Date());
			buffer.setBufferModifyTime(new Date());
			buffer.setBufferType(type);
			
			List<TSendSmsBuffer> list = new ArrayList<TSendSmsBuffer>();
			list.add(buffer);
			
			requestBean.setSendSmsBufferList(list);
			SmsResponseBean responseBean = smsService.insertBatchSendSmsBuffer(requestBean);
			
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			
			
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
			
	}



	public ISmsService getSmsService() {
	
		return smsService;
	}



	public void setSmsService(ISmsService smsService) {
	
		this.smsService = smsService;
	}
	
	

}

