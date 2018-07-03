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
 * @Description 彩信发送请求对象
 * @date 2013-6-14 上午9:17:03
 * @type MmsRequestBean
 * @project BespeakMeal
 */
public class MmsRequestBean extends RequestBean {
	
	private List<TSendMmsBuffer> mmsBufferList;
	TSendMmsBufferExample sendMmsBufferExample;
	TSendMmsBuffer sendMmsBuffer;
	
	TSendMmsHistory sendMmsHistory;
	TSendMmsHistoryExample sendMmsHistoryExample;

	public List<TSendMmsBuffer> getMmsBufferList() {
		return mmsBufferList;
	}

	public void setMmsBufferList(List<TSendMmsBuffer> mmsBufferList) {
		this.mmsBufferList = mmsBufferList;
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
