package app.cn.qtt.bm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.databinding.types.URI.MalformedURIException;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.dao.TSendSmsBufferDAO;
import app.cn.qtt.bm.dao.TSendSmsHistoryDAO;
import app.cn.qtt.bm.message.model.Result;
import app.cn.qtt.bm.message.model.Sms;
import app.cn.qtt.bm.message.service.IConfigureService;
import app.cn.qtt.bm.message.service.IMessageService;
import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.model.TSendSmsBufferExample;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;
import app.cn.qtt.bm.service.ISmsService;
import app.cn.qtt.bm.service.pojo.SmsRequestBean;
import app.cn.qtt.bm.service.pojo.SmsResponseBean;

public class SmsServiceImpl implements ISmsService{
	private CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());
	/**
	 * 短信发送数据访问对象
	 */
	TSendSmsBufferDAO sendSmsBufferDAO;
	/**
	 * 短信发送历史数据访问对象
	 */
	TSendSmsHistoryDAO sendSmsHistoryDAO;
	/**
	 * 短信发送service
	 */
	IMessageService messageService;

	@Override
	public String doSend(CSendSmsRequest request) {
		String smsSendUrl = CacheConstants.getParamValueByName(Constants.SMS_SEND_URL);
		String smsSpId = request.getSpID();
		String smsSpPassword = CacheConstants.getParamValueByName(Constants.DEVICE_PASSWORD);
		String smsSpServiceId = request.getSpServiceID();
		String sendAddress=request.getRecipient();
		String senderName=request.getSenderAddress();
		
		if(senderName.indexOf("10658099")<0){
			senderName="10658099"+senderName;
		}
		
		String cpCode=request.getCpCode();
		final Map<String, String> map = new HashMap<String, String>();
		map.put("sms_send_url", smsSendUrl);
		map.put("sms_sp_id", smsSpId);
		map.put("sms_sp_password", smsSpPassword);
		map.put("sms_sp_service_id", smsSpServiceId);
		map.put("cp_code", cpCode);
		IConfigureService configureService = new IConfigureService() {
			public String getValueFrom(String key) {
				return map.get(key);
			}
		};
		
		messageService.setConfigureService(configureService);
		Sms sms = new Sms();
		sms.setFa(request.getSender());//发送人
		sms.setOa(request.getSender());//发送人
		
		sms.setSenderName(senderName);
		sms.addAddress(sendAddress);
		sms.setCreateTime(new Date());
		sms.setMessage(request.getSmsSentContent());
		
		gLogger.debug("fa:"+sms.getFa());
		gLogger.debug("oa:"+sms.getOa());
		gLogger.debug("senderName:"+sms.getSenderName());
		try {
			gLogger.debug("address:"+sms.getAddresses());
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gLogger.debug("message:"+sms.getMessage());
	
		Result result = messageService.sendSms(sms);
		System.out.println(result.toString());
		
		return result.toString();
	}

	@Override
	public SmsResponseBean insertBatchSendSmsBuffer(SmsRequestBean requestBean) {
		SmsResponseBean responseBean = new SmsResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendSmsBufferList()==null||requestBean.getSendSmsBufferList().size()==0){
				throw new Exception("短信发送列表为空");
			}
			sendSmsBufferDAO.batchInsert(requestBean.getSendSmsBufferList());
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public SmsResponseBean updateSmsBufferStatus(SmsRequestBean requestBean) {
		SmsResponseBean responseBean = new SmsResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendSmsBuffer() != null){
				sendSmsBufferDAO.updateByPrimaryKeySelective(requestBean.getSendSmsBuffer());
			}else{
				throw new Exception("SendSmsBuffer为空");
			}
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public SmsResponseBean insertSendSmsHistory(SmsRequestBean requestBean) {
		SmsResponseBean responseBean = new SmsResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendSmsHistory() != null){
				sendSmsHistoryDAO.insert(requestBean.getSendSmsHistory());
			}else{
				throw new Exception("SendSmsHistory对象为空");
			}
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public SmsResponseBean selectSmsBuffer(SmsRequestBean requestBean) {
		SmsResponseBean responseBean = new SmsResponseBean();
		TSendSmsBufferExample example = new TSendSmsBufferExample();
		List<TSendSmsBuffer> resultList = null;
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendSmsBufferExample() !=null){
				example = requestBean.getSendSmsBufferExample();
			}
			resultList = sendSmsBufferDAO.selectByExample(example);
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	/**
	 * @return the sendSmsBufferDAO
	 */
	public TSendSmsBufferDAO getSendSmsBufferDAO() {
		return sendSmsBufferDAO;
	}

	/**
	 * @param sendSmsBufferDAO the sendSmsBufferDAO to set
	 */
	public void setSendSmsBufferDAO(TSendSmsBufferDAO sendSmsBufferDAO) {
		this.sendSmsBufferDAO = sendSmsBufferDAO;
	}

	/**
	 * @return the sendSmsHistoryDAO
	 */
	public TSendSmsHistoryDAO getSendSmsHistoryDAO() {
		return sendSmsHistoryDAO;
	}

	/**
	 * @param sendSmsHistoryDAO the sendSmsHistoryDAO to set
	 */
	public void setSendSmsHistoryDAO(TSendSmsHistoryDAO sendSmsHistoryDAO) {
		this.sendSmsHistoryDAO = sendSmsHistoryDAO;
	}

	/**
	 * @return the messageService
	 */
	public IMessageService getMessageService() {
		return messageService;
	}

	/**
	 * @param messageService the messageService to set
	 */
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

}
