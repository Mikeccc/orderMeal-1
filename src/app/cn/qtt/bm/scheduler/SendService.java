package app.cn.qtt.bm.scheduler;

import app.cn.qtt.bm.mail.MailSenderInfo;
import app.cn.qtt.bm.scheduler.pojo.CSendMmsRequest;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;

public interface SendService {

	/**
	 * 发送彩信
	 * @param mmsRequest
	 * @param params
	 * @return
	 */
	public String sendMmsFrom(CSendMmsRequest mmsRequest);
	/**
	 * 发送短信
	 * @param smsRequest
	 * @param params
	 * @return
	 */
	public String sendSmsFrom(CSendSmsRequest smsRequest);
	/**
	 * 发送邮件
	 * @param mail
	 * @param params
	 * @return String
	 * @throws Exception 
	 */
	public void sendEmailFrom(MailSenderInfo mail) throws Exception;
}
