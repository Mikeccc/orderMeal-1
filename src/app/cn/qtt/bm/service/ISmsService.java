package app.cn.qtt.bm.service;

import java.util.List;

import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;
import app.cn.qtt.bm.service.pojo.SmsRequestBean;
import app.cn.qtt.bm.service.pojo.SmsResponseBean;

/**
 * 
 * @author GeYanmeng
 * @Description 短信发送接口
 * @date 2013-6-13 下午9:36:32
 * @type ISmsService
 * @project BespeakMeal
 */
public interface ISmsService {
	/**
	 * 发送短信
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public String doSend(CSendSmsRequest request);
	
	/**
	 * 批量插入发送buffer表
	 * @param requestBean
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public SmsResponseBean insertBatchSendSmsBuffer(SmsRequestBean requestBean);
	
	/**
	 * 修改短信buffer表状态
	 * @param requestBean
	 * @return SmsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public SmsResponseBean updateSmsBufferStatus(SmsRequestBean requestBean);
	
	/**
	 * 插入短信发送历史表
	 * @param requestBean
	 * @return SmsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public SmsResponseBean insertSendSmsHistory(SmsRequestBean requestBean);

	/**
	 * 查询短信buffer表内容
	 * @param requestBean
	 * @return SmsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-19
	 */
	public SmsResponseBean selectSmsBuffer(SmsRequestBean requestBean);
}
