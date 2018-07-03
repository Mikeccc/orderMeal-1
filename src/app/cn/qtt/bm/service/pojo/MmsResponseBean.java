/**
 * 
 */
package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.TSendMmsBuffer;
import app.cn.qtt.bm.model.TSendMmsBufferExample;
import app.cn.qtt.bm.model.TSendMmsHistory;
import app.cn.qtt.bm.model.TSendMmsHistoryExample;

/**
 * @author GeYanmeng
 * @Description 彩信发送响应对象
 * @date 2013-6-14 上午9:17:56
 * @type MmsResponseBean
 * @project BespeakMeal
 */
public class MmsResponseBean extends ResponseBean {

	List<TSendMmsBuffer> sendMmsBufferList;
	
	TSendMmsBuffer sendMmsBuffer;
	TSendMmsBufferExample sendMmsBufferExample;
	
	TSendMmsHistory sendMmsHistory;
	TSendMmsHistoryExample sendMmsHistoryExample;
	
	/**
	 * @return the sendMmsBufferList
	 */
	public List<TSendMmsBuffer> getSendMmsBufferList() {
		return sendMmsBufferList;
	}
	/**
	 * @param sendMmsBufferList the sendMmsBufferList to set
	 */
	public void setSendMmsBufferList(List<TSendMmsBuffer> sendMmsBufferList) {
		this.sendMmsBufferList = sendMmsBufferList;
	}
	/**
	 * @return the sendMmsBuffer
	 */
	public TSendMmsBuffer getSendMmsBuffer() {
		return sendMmsBuffer;
	}
	/**
	 * @param sendMmsBuffer the sendMmsBuffer to set
	 */
	public void setSendMmsBuffer(TSendMmsBuffer sendMmsBuffer) {
		this.sendMmsBuffer = sendMmsBuffer;
	}
	/**
	 * @return the sendMmsBufferExample
	 */
	public TSendMmsBufferExample getSendMmsBufferExample() {
		return sendMmsBufferExample;
	}
	/**
	 * @param sendMmsBufferExample the sendMmsBufferExample to set
	 */
	public void setSendMmsBufferExample(TSendMmsBufferExample sendMmsBufferExample) {
		this.sendMmsBufferExample = sendMmsBufferExample;
	}
	/**
	 * @return the sendMmsHistory
	 */
	public TSendMmsHistory getSendMmsHistory() {
		return sendMmsHistory;
	}
	/**
	 * @param sendMmsHistory the sendMmsHistory to set
	 */
	public void setSendMmsHistory(TSendMmsHistory sendMmsHistory) {
		this.sendMmsHistory = sendMmsHistory;
	}
	/**
	 * @return the sendMmsHistoryExample
	 */
	public TSendMmsHistoryExample getSendMmsHistoryExample() {
		return sendMmsHistoryExample;
	}
	/**
	 * @param sendMmsHistoryExample the sendMmsHistoryExample to set
	 */
	public void setSendMmsHistoryExample(
			TSendMmsHistoryExample sendMmsHistoryExample) {
		this.sendMmsHistoryExample = sendMmsHistoryExample;
	}
	
}
