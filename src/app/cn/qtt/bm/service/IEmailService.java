/**
 * 
 */
package app.cn.qtt.bm.service;

import app.cn.qtt.bm.mail.MailSenderInfo;
import app.cn.qtt.bm.service.pojo.EmailRequestBean;
import app.cn.qtt.bm.service.pojo.EmailResponseBean;

/**
 * @author GeYanmeng
 * @Description 邮件发送服务接口
 * @date 2013-6-14 上午9:23:51
 * @type IEmailService
 * @project BespeakMeal
 */
public interface IEmailService {
	/**
	 * 发送邮件
	 * @author GeYanmeng
	 * @throws Exception 
	 * @date 2013-6-14
	 */
	public void doSend(MailSenderInfo mail) throws Exception;
	
	/**
	 * 批量插入邮件发送buffer表
	 * @param requestBean
	 * @author GeYanmeng
	 * @date 2013-6-14
	 */
	public EmailResponseBean insertBatchSendEmailBuffer(EmailRequestBean requestBean);
	
	/**
	 * 修改邮件buffer表状态
	 * @param requestBean
	 * @return EmailResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-14
	 */
	public EmailResponseBean updateEmailBufferStatus(EmailRequestBean requestBean);
	
	/**
	 * 插入邮件发送历史表
	 * @param requestBean
	 * @return EmailResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-14
	 */
	public EmailResponseBean insertSendEmailHistory(EmailRequestBean requestBean);
	
	/**
	 * 查询邮件buffer表
	 * @param requestBean
	 * @return EmailResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	public EmailResponseBean selectEmailBuffer(EmailRequestBean requestBean);
	
}
