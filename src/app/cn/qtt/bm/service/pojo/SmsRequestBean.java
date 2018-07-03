/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.model.TSendSmsBufferExample;
import app.cn.qtt.bm.model.TSendSmsHistory;
import app.cn.qtt.bm.model.TSendSmsHistoryExample;

/**
 * @author GeYanmeng
 * @Description 短信发送请求对象
 * @date 2013-6-13 下午9:50:49
 * @type SmsRequestBean
 * @project BespeakMeal
 */
public class SmsRequestBean extends RequestBean {
	
	List<TSendSmsBuffer> sendSmsBufferList;
	
	TSendSmsBuffer sendSmsBuffer;
	TSendSmsBufferExample sendSmsBufferExample;
	
	TSendSmsHistory sendSmsHistory;
	TSendSmsHistoryExample sendSmsHistoryExample;
	
	/**
	 * @return the sendSmsBufferList
	 */
	public List<TSendSmsBuffer> getSendSmsBufferList() {
		return sendSmsBufferList;
	}

	/**
	 * @param sendSmsBufferList the sendSmsBufferList to set
	 */
	public void setSendSmsBufferList(List<TSendSmsBuffer> sendSmsBufferList) {
		this.sendSmsBufferList = sendSmsBufferList;
	}

	/**
	 * @return the sendSmsBuffer
	 */
	public TSendSmsBuffer getSendSmsBuffer() {
		return sendSmsBuffer;
	}

	/**
	 * @param sendSmsBuffer the sendSmsBuffer to set
	 */
	public void setSendSmsBuffer(TSendSmsBuffer sendSmsBuffer) {
		this.sendSmsBuffer = sendSmsBuffer;
	}

	/**
	 * @return the sendSmsBufferExample
	 */
	public TSendSmsBufferExample getSendSmsBufferExample() {
		return sendSmsBufferExample;
	}

	/**
	 * @param sendSmsBufferExample the sendSmsBufferExample to set
	 */
	public void setSendSmsBufferExample(TSendSmsBufferExample sendSmsBufferExample) {
		this.sendSmsBufferExample = sendSmsBufferExample;
	}

	/**
	 * @return the sendSmsHistory
	 */
	public TSendSmsHistory getSendSmsHistory() {
		return sendSmsHistory;
	}

	/**
	 * @param sendSmsHistory the sendSmsHistory to set
	 */
	public void setSendSmsHistory(TSendSmsHistory sendSmsHistory) {
		this.sendSmsHistory = sendSmsHistory;
	}

	/**
	 * @return the sendSmsHistoryExample
	 */
	public TSendSmsHistoryExample getSendSmsHistoryExample() {
		return sendSmsHistoryExample;
	}

	/**
	 * @param sendSmsHistoryExample the sendSmsHistoryExample to set
	 */
	public void setSendSmsHistoryExample(
			TSendSmsHistoryExample sendSmsHistoryExample) {
		this.sendSmsHistoryExample = sendSmsHistoryExample;
	}

}
