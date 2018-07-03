/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.TSendEmailBuffer;
import app.cn.qtt.bm.model.TSendEmailBufferExample;
import app.cn.qtt.bm.model.TSendEmailHistory;
import app.cn.qtt.bm.model.TSendEmailHistoryExample;

/**
 * @author GeYanmeng
 * @Description 邮件发送请求对象
 * @date 2013-6-14 上午9:21:30
 * @type EmailRequestBean
 * @project BespeakMeal
 */
public class EmailRequestBean extends RequestBean {
	List<TSendEmailBuffer> sendEmailBufferList;
	
	TSendEmailBuffer sendEmailBuffer;
	TSendEmailBufferExample sendEmailBufferExample;
	
	TSendEmailHistory sendEmailHistory;
	TSendEmailHistoryExample sendEmailHistoryExample;
	/**
	 * @return the sendEmailBufferList
	 */
	public List<TSendEmailBuffer> getSendEmailBufferList() {
		return sendEmailBufferList;
	}
	/**
	 * @param sendEmailBufferList the sendEmailBufferList to set
	 */
	public void setSendEmailBufferList(List<TSendEmailBuffer> sendEmailBufferList) {
		this.sendEmailBufferList = sendEmailBufferList;
	}
	/**
	 * @return the sendEmailBuffer
	 */
	public TSendEmailBuffer getSendEmailBuffer() {
		return sendEmailBuffer;
	}
	/**
	 * @param sendEmailBuffer the sendEmailBuffer to set
	 */
	public void setSendEmailBuffer(TSendEmailBuffer sendEmailBuffer) {
		this.sendEmailBuffer = sendEmailBuffer;
	}
	/**
	 * @return the sendEmailBufferExample
	 */
	public TSendEmailBufferExample getSendEmailBufferExample() {
		return sendEmailBufferExample;
	}
	/**
	 * @param sendEmailBufferExample the sendEmailBufferExample to set
	 */
	public void setSendEmailBufferExample(
			TSendEmailBufferExample sendEmailBufferExample) {
		this.sendEmailBufferExample = sendEmailBufferExample;
	}
//	/**TODO
//	 * @return the sendEmailHistory
//	 */
//	public TSendEmailHistory getSendEmailHistory() {
//		return sendEmailHistory;
//	}
//	/**
//	 * @param sendEmailHistory the sendEmailHistory to set
//	 */
//	public void setSendEmailHistory(TSendEmailHistory sendEmailHistory) {
//		this.sendEmailHistory = sendEmailHistory;
//	}
//	/**
//	 * @return the sendEmailHistoryExample
//	 */
//	public TSendEmailHistoryExample getSendEmailHistoryExample() {
//		return sendEmailHistoryExample;
//	}
//	/**
//	 * @param sendEmailHistoryExample the sendEmailHistoryExample to set
//	 */
//	public void setSendEmailHistoryExample(
//			TSendEmailHistoryExample sendEmailHistoryExample) {
//		this.sendEmailHistoryExample = sendEmailHistoryExample;
//	}
	/**
	 * @return the sendEmailHistory
	 */
	public TSendEmailHistory getSendEmailHistory() {
		return sendEmailHistory;
	}
	/**
	 * @param sendEmailHistory the sendEmailHistory to set
	 */
	public void setSendEmailHistory(TSendEmailHistory sendEmailHistory) {
		this.sendEmailHistory = sendEmailHistory;
	}
	/**
	 * @return the sendEmailHistoryExample
	 */
	public TSendEmailHistoryExample getSendEmailHistoryExample() {
		return sendEmailHistoryExample;
	}
	/**
	 * @param sendEmailHistoryExample the sendEmailHistoryExample to set
	 */
	public void setSendEmailHistoryExample(TSendEmailHistoryExample sendEmailHistoryExample) {
		this.sendEmailHistoryExample = sendEmailHistoryExample;
	}
	
}
