package app.cn.qtt.bm.scheduler.impl;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.mail.MailSenderInfo;
import app.cn.qtt.bm.scheduler.SendService;
import app.cn.qtt.bm.scheduler.pojo.CSendMmsRequest;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;
import app.cn.qtt.bm.service.IEmailService;
import app.cn.qtt.bm.service.IMmsService;
import app.cn.qtt.bm.service.ISmsService;

public class SendServiceImpl implements SendService {

	protected CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());

	private IMmsService mmsService;
	private ISmsService smsService;
	private IEmailService emailService;
	
	public String sendMmsFrom( CSendMmsRequest request) {
		this.gLogger.begin("sendMms");
		String result = null;
		try {
			result = mmsService.doSend(request);
		} catch (Exception e) {
			this.gLogger.exception("",e);
		} finally {
			this.gLogger.end("sendMms");
		}

		return result;
	}
	/**
	 * 发送短信 add by jjf
	 */
	public String sendSmsFrom(CSendSmsRequest smsRequest) {
		this.gLogger.begin("sendSms(发送短信)");
		String result = null;
		try {
			result = smsService.doSend(smsRequest);
		} catch (Exception e) {
			this.gLogger.exception("",e);
		} finally {
			this.gLogger.end("sendSms(发送短信)");
		}

		return result;
	}
	
	@Override
	public void sendEmailFrom(MailSenderInfo mail) throws Exception {
		this.gLogger.begin("sendSms(发送邮件)");
		try {
			emailService.doSend(mail);
		} catch (Exception e) {
			this.gLogger.exception("",e);
		} finally {
			this.gLogger.end("sendSms(发送邮件)");
		}
	}
	
	
	public IMmsService getMmsService() {
		return mmsService;
	}
	public void setMmsService(IMmsService mmsService) {
		this.mmsService = mmsService;
	}
	public ISmsService getSmsService() {
		return smsService;
	}
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