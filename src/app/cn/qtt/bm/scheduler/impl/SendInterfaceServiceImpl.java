package app.cn.qtt.bm.scheduler.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.mail.MailSenderInfo;
import app.cn.qtt.bm.model.TSendEmailBuffer;
import app.cn.qtt.bm.model.TSendEmailHistory;
import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.model.TSendSmsHistory;
import app.cn.qtt.bm.scheduler.SendInterfaceService;
import app.cn.qtt.bm.scheduler.SendService;
import app.cn.qtt.bm.scheduler.pojo.CSendMmsRequest;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;
import app.cn.qtt.bm.service.IEmailService;
import app.cn.qtt.bm.service.IMmsService;
import app.cn.qtt.bm.service.ISmsService;
import app.cn.qtt.bm.service.pojo.EmailRequestBean;
import app.cn.qtt.bm.service.pojo.EmailResponseBean;
import app.cn.qtt.bm.service.pojo.SmsRequestBean;
import app.cn.qtt.bm.service.pojo.SmsResponseBean;

/***
 * 
 * @author LONGTENG.WEI
 * @Description 订餐系统短信、彩信、邮件下发接口服务 
 */
public class SendInterfaceServiceImpl implements SendInterfaceService{
	private CCrppLog4j gLogger = new CCrppLog4j(SendInterfaceServiceImpl.class.getName());
	private SendService sendService;
	private IMmsService mmsService;
	private ISmsService smsService;
	private IEmailService emailService;
	
	/**
	 * 单条记录彩信下发
	 * @param request
	 * @param params
	 * @param connector
	 * @throws Exception
	 */
	public void doSendMmsInterface(CSendMmsRequest request){
		sendService.sendMmsFrom(request); // 执行彩信发送任务
	}
	
	/**
	 * 发送短信
	 * @throws Exception 
	 */
	public void doSendSmsInterface(CSendSmsRequest request) throws Exception {
		final String result = sendService.sendSmsFrom(request); // 执行短信发送任务
		// 1.更新短信buffer表
		this.updateSendSmsBuffer(request, result);
		// 2.插入短信发送历史表
		this.insertSmsSendHistory(request, result);
	}
	
	/**
	 * 插入短信发送历史表
	 * @param request CSendSmsRequest
	 * @param result String 短信网关返回
	 * @author GeYanmeng
	 * @throws Exception 
	 * @date 2013-6-20
	 */
	private void insertSmsSendHistory(CSendSmsRequest request, String result) throws Exception {
		final String xFunctionName = "insertSmsSentHistory(CSendSmsRequest request, String result)";
		gLogger.begin(xFunctionName);
		gLogger.debug("短信网关返回流水号 -> " + result);
		final boolean success = StringUtils.isNotBlank(result) ? true : false; // 是否发送成功
		int historySendStatus;
		if (!success) { // 发送失败情况
			historySendStatus = Constants.SMS_HISTORY_STATUS_0;
		}else{
			historySendStatus = Constants.SMS_HISTORY_STATUS_1;
		}
		SmsRequestBean requestBean = new SmsRequestBean();
		
		TSendSmsHistory sendSmsHistory = new TSendSmsHistory();
		
		sendSmsHistory.setHistoryBufferSn(request.getBufferSn());
		sendSmsHistory.setHistorySmsSn(result);//短信发送成功流水号
		sendSmsHistory.setHistoryFaStr(request.getSender());
		sendSmsHistory.setHistoryOaStr(request.getSender());
		sendSmsHistory.setHistorySpId(request.getSpID());
		sendSmsHistory.setHistorySpPassword(CacheConstants.getParamValueByName(Constants.DEVICE_PASSWORD));
		sendSmsHistory.setHistorySpServiceId(request.getSpServiceID());
		sendSmsHistory.setHistorySenderName(request.getSender());
		sendSmsHistory.setHistoryMessage(request.getMmsText());
		sendSmsHistory.setHistoryReceiverMdn(request.getRecipient());
		sendSmsHistory.setHistorySourceAddr(request.getSenderAddress());
		sendSmsHistory.setHistoryCreateTime(new Date());
		sendSmsHistory.setHistorySendCount(0);//重发次数
		sendSmsHistory.setHistoryStatus(historySendStatus);
		
		requestBean.setSendSmsHistory(sendSmsHistory);
		
		SmsResponseBean responseBean = smsService.insertSendSmsHistory(requestBean);
		if(!responseBean.getResponseCode().equals("00")){
			throw new Exception(responseBean.getErrMsg(),responseBean.getException());
		}
	}

	/**
	 * 更新短信发送buffer表
	 * @param request
	 * @param result
	 * @author GeYanmeng
	 * @throws Exception 
	 * @date 2013-6-19
	 */
	private void updateSendSmsBuffer(CSendSmsRequest request, String result) throws Exception {
		final String xFunctionName = "updateSendSmsBuffer(CSendSmsRequest request, String result)";
		gLogger.begin(xFunctionName);
		gLogger.debug("短信网关返回流水号 -> " + result);
		final boolean success = StringUtils.isNotBlank(result) ? true : false; // 是否发送成功
		
		if (!success) { // 发送失败情况
			SmsRequestBean requestBean = new SmsRequestBean();
			
			TSendSmsBuffer sendSmsBuffer = new TSendSmsBuffer();
			sendSmsBuffer.setBufferId(request.getId());//主键
			sendSmsBuffer.setBufferModifyTime(new Date());
			sendSmsBuffer.setBufferStatus(Constants.SMS_STATUS_00);//发送未完成
			requestBean.setSendSmsBuffer(sendSmsBuffer);
			
			SmsResponseBean responseBean = smsService.updateSmsBufferStatus(requestBean);
			if(!responseBean.getResponseCode().equals("00")){
				throw new Exception(responseBean.getErrMsg(),responseBean.getException());
			}
		}else{
			SmsRequestBean requestBean = new SmsRequestBean();
			
			TSendSmsBuffer sendSmsBuffer = new TSendSmsBuffer();
			sendSmsBuffer.setBufferId(request.getId());//主键
			sendSmsBuffer.setBufferModifyTime(new Date());
			sendSmsBuffer.setBufferStatus(Constants.SMS_STATUS_04);//发送完成
			requestBean.setSendSmsBuffer(sendSmsBuffer);
			
			SmsResponseBean responseBean = smsService.updateSmsBufferStatus(requestBean);
			if(!responseBean.getResponseCode().equals("00")){
				throw new Exception(responseBean.getErrMsg(),responseBean.getException());
			}
		}
	}

	@Override
	public void doSendEmailInterface(TSendEmailBuffer sendEmailBuffer) {
		
		MailSenderInfo mail = new MailSenderInfo();
		mail.setAttachFileNames(sendEmailBuffer.getBufferEmailAttachFiles().split(Constants.EMAIL_ATTACHFILES_SPLIT_CODE));
		mail.setContent(sendEmailBuffer.getBufferEmailContent());
		mail.setFromAddress(sendEmailBuffer.getBufferEmailFromAddress());
		mail.setMailServerHost(sendEmailBuffer.getBufferEmailSmtpHost());
		mail.setMailServerPort(sendEmailBuffer.getBufferEmailSmtpPort());
		mail.setPassword(sendEmailBuffer.getBufferEmailPassword());
		mail.setSubject(sendEmailBuffer.getBufferEmailSubject());
		mail.setToAddress(sendEmailBuffer.getBufferEmailToAddress());
		mail.setUserName(sendEmailBuffer.getBufferEmailUserName());
		mail.setValidate(sendEmailBuffer.getBufferEmailValidate());
		
		try{
			sendService.sendEmailFrom(mail); // 执行邮件发送任务
			gLogger.info("邮件发送成功");
			// 1.更新邮件buffer表(发送成功)
			this.updateSendEmailBuffer(sendEmailBuffer,Constants.EMAIL_STATUS_03);
			// 2.插入邮件发送历史表(发送成功)
			this.insertEmailSendHistory(sendEmailBuffer,Constants.EMAIL_HISTORY_STATUS_00);
		}catch(Exception e){
			gLogger.info("邮件发送失败："+e.getMessage());
			// 1.更新邮件buffer表(发送失败)
			try {
				this.updateSendEmailBuffer(sendEmailBuffer,Constants.EMAIL_STATUS_00);
			} catch (Exception e1) {
				gLogger.info("更新邮件buffer表失败："+e.getMessage());
				e1.printStackTrace();
			}
			// 2.插入邮件发送历史表(发送失败)
			try {
				this.insertEmailSendHistory(sendEmailBuffer,Constants.EMAIL_HISTORY_STATUS_01);
			} catch (Exception e1) {
				gLogger.info("插入邮件发送历史表失败："+e.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 更新邮件buffer表
	 * @param sendEmailBuffer
	 * @param status
	 * @author GeYanmeng
	 * @throws Exception 
	 * @date 2013-6-21
	 */
	private void updateSendEmailBuffer(TSendEmailBuffer sendEmailBuffer,String status) throws Exception {
		EmailRequestBean requestBean = new EmailRequestBean();
		sendEmailBuffer.setBufferEmailRunStartus(status);
		requestBean.setSendEmailBuffer(sendEmailBuffer);
		EmailResponseBean responseBean = emailService.updateEmailBufferStatus(requestBean);
		if(!responseBean.getResponseCode().equals("00")){
			throw new Exception(responseBean.getErrMsg(),responseBean.getException());
		}
	}
	
	/**
	 * 插入邮件发送历史表
	 * @param sendEmailBuffer
	 * @param status
	 * @author GeYanmeng
	 * @throws Exception 
	 * @date 2013-6-21
	 */
	private void insertEmailSendHistory(TSendEmailBuffer sendEmailBuffer,String status) throws Exception {
		EmailRequestBean requestBean = new EmailRequestBean();
		
		TSendEmailHistory sendEmailHistory = new TSendEmailHistory();
		sendEmailHistory.setBufferEmailCode(sendEmailBuffer.getBufferEmailCode());
		sendEmailHistory.setHistoryEmailFromAddress(sendEmailBuffer.getBufferEmailFromAddress());
		sendEmailHistory.setHistoryEmailToAddress(sendEmailBuffer.getBufferEmailToAddress());
		sendEmailHistory.setHistoryEmailValidate(sendEmailBuffer.getBufferEmailValidate());
		sendEmailHistory.setHistoryEmailUserName(sendEmailBuffer.getBufferEmailUserName());
		sendEmailHistory.setHistoryEmailPassword(sendEmailBuffer.getBufferEmailPassword());
		sendEmailHistory.setHistoryEmailSubject(sendEmailBuffer.getBufferEmailSubject());
		sendEmailHistory.setHistoryEmailContent(sendEmailBuffer.getBufferEmailContent());
		sendEmailHistory.setHistoryEmailAttachFiles(sendEmailBuffer.getBufferEmailAttachFiles());
		sendEmailHistory.setHistoryEmailSmtpHost(sendEmailBuffer.getBufferEmailSmtpHost());
		sendEmailHistory.setHistoryEmailSmtpPort(sendEmailBuffer.getBufferEmailSmtpPort());
		sendEmailHistory.setHistoryEmailStartus(status);
		sendEmailHistory.setHistoryEmailType(sendEmailBuffer.getBufferEmailType());
		sendEmailHistory.setCreateUserCode(sendEmailBuffer.getCreateUserCode());
		sendEmailHistory.setCreateTime(new Date());
		
		requestBean.setSendEmailHistory(sendEmailHistory);
		
		EmailResponseBean responseBean = emailService.insertSendEmailHistory(requestBean);
		
		if(!responseBean.getResponseCode().equals("00")){
			throw new Exception(responseBean.getErrMsg(),responseBean.getException());
		}
	}

	public SendService getSendService() {
		return sendService;
	}
	public void setSendService(SendService sendService) {
		this.sendService = sendService;
	}

	/**
	 * @return the mmsService
	 */
	public IMmsService getMmsService() {
		return mmsService;
	}

	/**
	 * @param mmsService the mmsService to set
	 */
	public void setMmsService(IMmsService mmsService) {
		this.mmsService = mmsService;
	}

	/**
	 * @return the smsService
	 */
	public ISmsService getSmsService() {
		return smsService;
	}

	/**
	 * @param smsService the smsService to set
	 */
	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}

	/**
	 * @return the emailService
	 */
	public IEmailService getEmailService() {
		return emailService;
	}

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(IEmailService emailService) {
		this.emailService = emailService;
	}
}
