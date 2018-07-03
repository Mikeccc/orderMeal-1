package app.cn.qtt.bm.scheduler;

import app.cn.qtt.bm.model.TSendEmailBuffer;
import app.cn.qtt.bm.scheduler.pojo.CSendMmsRequest;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;

public interface SendInterfaceService {

	/**
	 * 单条记录彩信下发接口
	 * @param request
	 * @param params
	 * @param connector
	 * @throws Exception
	 */
	public void doSendMmsInterface(CSendMmsRequest request);
	
	/**
	 * 发送短信
	 * @param request
	 * @param params
	 * @param connector
	 */
	public void doSendSmsInterface(CSendSmsRequest request) throws Exception;

	/**
	 * 发送邮件
	 * @param sendEmailBuffer
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	public void doSendEmailInterface(TSendEmailBuffer sendEmailBuffer);
}
