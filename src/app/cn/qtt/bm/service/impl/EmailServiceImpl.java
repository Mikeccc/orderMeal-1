/**
 * 
 */
package app.cn.qtt.bm.service.impl;

import java.util.List;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.dao.TSendEmailBufferDAO;
import app.cn.qtt.bm.dao.TSendEmailHistoryDAO;
import app.cn.qtt.bm.mail.MailSender;
import app.cn.qtt.bm.mail.MailSenderInfo;
import app.cn.qtt.bm.model.TSendEmailBuffer;
import app.cn.qtt.bm.model.TSendEmailBufferExample;
import app.cn.qtt.bm.service.IEmailService;
import app.cn.qtt.bm.service.pojo.EmailRequestBean;
import app.cn.qtt.bm.service.pojo.EmailResponseBean;

/**
 * @author GeYanmeng
 * @Description 邮件发送服务实现类
 * @date 2013-6-14 上午9:25:10
 * @type EmailServiceImpl
 * @project BespeakMeal
 */
public class EmailServiceImpl implements IEmailService {
	
	/**
	 * Email发送数据访问对象
	 */
	TSendEmailBufferDAO sendEmailBufferDAO;
	/**
	 * Email发送历史数据访问对象
	 */
	TSendEmailHistoryDAO sendEmailHistoryDAO;

	@Override
	public void doSend(MailSenderInfo mail) throws Exception {
		MailSender mailSender = new MailSender(mail.getFromAddress(),mail.getPassword());
		mailSender.send(mail.getToAddress(), mail);
	}

	@Override
	public EmailResponseBean insertBatchSendEmailBuffer(EmailRequestBean requestBean) {
		EmailResponseBean responseBean = new EmailResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendEmailBufferList()==null||requestBean.getSendEmailBufferList().size()==0){
				throw new Exception("邮件发送列表为空");
			}
			sendEmailBufferDAO.batchInsert(requestBean.getSendEmailBufferList());
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public EmailResponseBean updateEmailBufferStatus(EmailRequestBean requestBean) {
		EmailResponseBean responseBean = new EmailResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendEmailBuffer() != null){
				sendEmailBufferDAO.updateByPrimaryKeySelective(requestBean.getSendEmailBuffer());
			}else{
				throw new Exception("SendEmailBuffer为空");
			}
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public EmailResponseBean selectEmailBuffer(EmailRequestBean requestBean) {
		EmailResponseBean responseBean = new EmailResponseBean();
		TSendEmailBufferExample example = new TSendEmailBufferExample();
		List<TSendEmailBuffer> resultList = null;
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendEmailBufferExample() !=null){
				example = requestBean.getSendEmailBufferExample();
			}
			resultList = sendEmailBufferDAO.selectByExample(example);
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public EmailResponseBean insertSendEmailHistory(EmailRequestBean requestBean) {
		EmailResponseBean responseBean = new EmailResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendEmailHistory()==null){
				throw new Exception("SendEmailHistory为空");
			}
			sendEmailHistoryDAO.insert(requestBean.getSendEmailHistory());
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	public TSendEmailBufferDAO getSendEmailBufferDAO() {
		return sendEmailBufferDAO;
	}

	public void setSendEmailBufferDAO(TSendEmailBufferDAO sendEmailBufferDAO) {
		this.sendEmailBufferDAO = sendEmailBufferDAO;
	}

	/**
	 * @return the sendEmailHistoryDAO
	 */
	public TSendEmailHistoryDAO getSendEmailHistoryDAO() {
		return sendEmailHistoryDAO;
	}

	/**
	 * @param sendEmailHistoryDAO the sendEmailHistoryDAO to set
	 */
	public void setSendEmailHistoryDAO(TSendEmailHistoryDAO sendEmailHistoryDAO) {
		this.sendEmailHistoryDAO = sendEmailHistoryDAO;
	}

}
