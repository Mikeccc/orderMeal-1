/**
 * 
 */
package app.cn.qtt.bm.scheduler.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.model.TSendSmsBufferExample;
import app.cn.qtt.bm.scheduler.SendInterfaceService;
import app.cn.qtt.bm.scheduler.SendScheduleService;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;
import app.cn.qtt.bm.service.ISmsService;
import app.cn.qtt.bm.service.pojo.SmsRequestBean;
import app.cn.qtt.bm.service.pojo.SmsResponseBean;

/**
 * @author Gabriel
 * @Description 密码重置短信下发调度任务服务
 * @date 2013-7-3 下午5:08:07
 * @type SmsPwdRstSendScheduleServiceImpl
 * @project BespeakMeal
 */
public class SmsPwdRstSendScheduleServiceImpl implements SendScheduleService {
	private CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());

	private SendInterfaceService sendInterfaceService;
	private ThreadPoolTaskExecutor smsPwdRstTaskExecutor;
	private ISmsService smsService;
	
	@Override
	public void processSendSchedule() {
		try {
			Date nowDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
			
			gLogger.info("密码重置短信下发任务开始");
			gLogger.info("系统现在时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
			this.sendBusiness();
			nowDate = new Date();
			calendar.setTime(nowDate);
			gLogger.info("本次密码重置短信发送任务结束时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
		} catch (Exception e) {
			gLogger.exception("processMmsSendSchedule", e);
		}
	}
	
	/***
	 * 处理密码重置短信发送JOB业务
	 * 
	 * @param connector
	 */
	private void sendBusiness() {
		final String xFunctionName = "sendBusines";
		gLogger.begin(xFunctionName);
		Long start = System.currentTimeMillis();
		try {
			gLogger.info("开始进入发送流程");
			start = System.currentTimeMillis();
			// 开始进入发送
			processSend();
			gLogger.debug("一次JOB业务处理耗时，耗时：【" + (System.currentTimeMillis() - start) + "】毫秒！");
		} catch (Exception e) {
			gLogger.error(e.getMessage());
			e.printStackTrace();
		}
		gLogger.end(xFunctionName);
	}
	
	/**
	 * 处理发送
	 */
	@SuppressWarnings("unchecked")
	protected void processSend() throws Exception {
		try {
			SmsRequestBean requestBean = new SmsRequestBean();
			TSendSmsBufferExample example = new TSendSmsBufferExample();
			example.createCriteria().andBufferStatusEqualTo(Constants.SMS_STATUS_01).andBufferTypeEqualTo(Constants.SMS_TYPE_01);
			requestBean.setSendSmsBufferExample(example);
			// 获取符合要的数据
			List<TSendSmsBuffer> smsBufferList = null;
			SmsResponseBean responseBean = smsService.selectSmsBuffer(requestBean);
			if(responseBean.getResponseCode().equals("00")){
				smsBufferList = (List<TSendSmsBuffer>) responseBean.getResultList();
			}else{
				throw responseBean.getException();
			}
			
			// 遍历未发送密码重置短信集合 BEGIN
			if (!CollectionUtils.isEmpty(smsBufferList)) {
				List<CSendSmsRequest> smsList = new ArrayList<CSendSmsRequest>();
				for (TSendSmsBuffer sendSmsBufferTemp : smsBufferList) {
					Long bufferId = sendSmsBufferTemp.getBufferId();
					//密码重置短信发送号码
					String mdn = sendSmsBufferTemp.getBufferSenderName();
					//接收号码
					String sendto = sendSmsBufferTemp.getBufferReceiverMdn();
					//密码重置短信发送类型
					String sendType = sendSmsBufferTemp.getBufferType();
					//密码重置短信来源地址
					String senderAddress = sendSmsBufferTemp.getBufferSourceAddr();
					String bufferMessage = sendSmsBufferTemp.getBufferMessage();
					// 封装密码重置短信对象CSendMmsRequest
					if (StringUtils.isNotBlank(mdn) && StringUtils.isNotBlank(sendto)) {
						CSendSmsRequest request = new CSendSmsRequest(); // 组装待发密码重置短信对象
						String bufferSn = sendSmsBufferTemp.getBufferSn();
						request.setBufferSn(bufferSn);
						request.setId(bufferId);
						request.setRecipient(sendto);
						request.setSender(mdn);
						request.setUserId(mdn);
						request.setMmsText(bufferMessage);
						request.setSenderAddress(senderAddress);
						request.setCpCode("");
						request.setSmsSentContent(bufferMessage);
						request.setMsgSendType(sendType);
						request.setSpID(CacheConstants.getParamValueByName(Constants.SMS_SP_ID));
						request.setSpServiceID(CacheConstants.getParamValueByName(Constants.SMS_SP_SERVICE_ID));
						
						smsList.add(request);
					}
				}
				// 调用密码重置短信下发接口
				if (!CollectionUtils.isEmpty(smsList)) {
					this.doSendSms(smsList);
					gLogger.debug("smsList == " + smsList);
				}
			}else{
				gLogger.info("smsList为空，结束发送短信");
			}
		} catch (Exception e) {
			gLogger.exception("processSend", e);
			throw e;
		}
	}

	/*
	 * 密码重置短信下发接口 add by jjf
	 */
	public void doSendSms(List<CSendSmsRequest> smsList) throws Exception {
		String xFunctionName = "doSendSms发送密码重置短信";
		for (final CSendSmsRequest request : smsList) {
			try {
				// 提交线程时间
				gLogger.debug("#进入线程池队列#：" + request.getBatchId() + ",SMSID:" + request.getSmsId());
				smsPwdRstTaskExecutor.execute(new Runnable() {
					public void run() {
						// 密码重置短信下发接口
						try {
							sendInterfaceService.doSendSmsInterface(request);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} catch (Exception e) {
				gLogger.exception(xFunctionName, e);
			}
		}
	}

	/**
	 * @return the sendInterfaceService
	 */
	public SendInterfaceService getSendInterfaceService() {
		return sendInterfaceService;
	}

	/**
	 * @param sendInterfaceService the sendInterfaceService to set
	 */
	public void setSendInterfaceService(SendInterfaceService sendInterfaceService) {
		this.sendInterfaceService = sendInterfaceService;
	}

	/**
	 * @return the smsService
	 */
	public ISmsService getSmsService() {
		return smsService;
	}

	/**
	 * @param smsService the smsService to set
	 */
	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}

	/**
	 * @return the smsPwdRstTaskExecutor
	 */
	public ThreadPoolTaskExecutor getSmsPwdRstTaskExecutor() {
		return smsPwdRstTaskExecutor;
	}

	/**
	 * @param smsPwdRstTaskExecutor the smsPwdRstTaskExecutor to set
	 */
	public void setSmsPwdRstTaskExecutor(ThreadPoolTaskExecutor smsPwdRstTaskExecutor) {
		this.smsPwdRstTaskExecutor = smsPwdRstTaskExecutor;
	}

}
