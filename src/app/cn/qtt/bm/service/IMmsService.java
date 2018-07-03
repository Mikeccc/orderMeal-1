package app.cn.qtt.bm.service;

import app.cn.qtt.bm.scheduler.pojo.CSendMmsRequest;
import app.cn.qtt.bm.service.pojo.MmsRequestBean;
import app.cn.qtt.bm.service.pojo.MmsResponseBean;
/**
 * 
 * @author GeYanmeng
 * @Description 彩信发送接口
 * @date 2013-6-14 上午9:15:52
 * @type IMmsService
 * @project BespeakMeal
 */
public interface IMmsService {
	/**
	 * 发送彩信
	 * @author jjf
	 * @date 2013-6-9
	 */
	public String doSend(CSendMmsRequest request);
	
	/**
	 * 批量插入彩信发送缓冲表
	 * @param requestBean
	 * @return MmsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-14
	 */
	public MmsResponseBean insertBatchSendMmsBuffer(MmsRequestBean requestBean);
	
	/**
	 * 修改彩信buffer表状态
	 * @param requestBean
	 * @return MmsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-14
	 */
	public MmsResponseBean updateMmsBufferStatus(MmsRequestBean requestBean);
	
	/**
	 * 插入发送历史表
	 * @param requestBean
	 * @return MmsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-14
	 */
	public MmsResponseBean insertSendMmsHistory(MmsRequestBean requestBean);
	/**
	 * 查询发送buffer表
	 * @param requestBean
	 * @return
	 */
	public MmsResponseBean selectMmsBuffer(MmsRequestBean requestBean);
}
