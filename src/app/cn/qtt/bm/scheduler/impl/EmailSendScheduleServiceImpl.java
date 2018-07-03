/**
 * 
 */
package app.cn.qtt.bm.scheduler.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.model.TSendEmailBuffer;
import app.cn.qtt.bm.model.TSendEmailBufferExample;
import app.cn.qtt.bm.model.TShopDailyInfo;
import app.cn.qtt.bm.model.TShopDailyInfoExample;
import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.scheduler.SendInterfaceService;
import app.cn.qtt.bm.scheduler.SendScheduleService;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.IEmailService;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.IOrderService;
import app.cn.qtt.bm.service.IShopService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;
import app.cn.qtt.bm.service.pojo.EmailRequestBean;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

/**
 * @author GeYanmeng
 * @Description 邮件发送调度服务实现类
 * @date 2013-6-19 上午9:58:42
 * @type EmailSendScheduleServiceImpl
 * @project BespeakMeal
 */
public class EmailSendScheduleServiceImpl implements SendScheduleService {
	private CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());

	private SendInterfaceService sendInterfaceService;
	private ThreadPoolTaskExecutor emailTaskExecutor;
	private IOrderService orderService;
	private IShopService shopService;
	private IGoodsService goodsService;
	private IEmailService emailService;
	private ICommonService commonService;
	
	@Override
	public void processSendSchedule() {
		try {
			Date nowDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
			gLogger.info("邮件发送任务开始");
			gLogger.info("系统现在时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
			int startHour = 0;
			int endHort = 0;
			TSysParameter parameter = new TSysParameter();
			parameter.setParamCode("stat_hour");
			CommonRequestBean requestBean = new CommonRequestBean();
			requestBean.setSysParameter(parameter);
			List<TSysParameter> list = null;
			CommonResponseBean responseBean = commonService.getSysParameter(requestBean);
			if(Constants.SELECT_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				list = responseBean.getList();
				startHour = NumberUtils.toInt(list.get(0).getParamValue());
			}else{
				throw responseBean.getException();
			}
			
			parameter.setParamCode("end_hour");
			requestBean.setSysParameter(parameter);
			responseBean = commonService.getSysParameter(requestBean);
			if(Constants.SELECT_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				list = responseBean.getList();
				endHort = NumberUtils.toInt(list.get(0).getParamValue());
			}else{
				throw responseBean.getException();
			}
			 
			if (nowHour >= startHour && nowHour <= endHort) {
				this.sendBusiness();
			}
			nowDate = new Date();
			calendar.setTime(nowDate);
			gLogger.info("本次邮件发送任务结束时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
		} catch (Exception e) {
			gLogger.exception("processMmsSendSchedule", e);
		}
	}

	/**
	 * 处理邮件发送业务
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	private void sendBusiness() {
		final String xFunctionName = "sendBusines";
		gLogger.begin(xFunctionName);
		Long start = System.currentTimeMillis();
		try {
			gLogger.info("开始进入发送流程");
			start = System.currentTimeMillis();
			OrderResponseBean orderResponseBean;
			List<TShopDailyInfo> shopDailyInfoList;
			OrderRequestBean orderRequestBean = new OrderRequestBean();
			TShopDailyInfoExample shopDailyInfoExample = new TShopDailyInfoExample();
			// 取每日统计信息发送状态-未发送的数据 
			shopDailyInfoExample.createCriteria()
				.andShopDailyEmailStatusEqualTo(Constants.SHOP_DAILY_STATUS_00)
				.andShopDailySmsStatusNotEqualTo(Constants.SHOP_DAILY_STATUS_00)
				.andCreateTimeGreaterThan(new Date(System.currentTimeMillis()-1000*60*60*24));
			
			orderRequestBean.setShopDailyInfoExample(shopDailyInfoExample);

			orderResponseBean = orderService.queryDailyOrderInfo(orderRequestBean);
			
			if (Constants.SELECT_SUCCESS_CODE.equals(orderResponseBean.getResponseCode())) {
				shopDailyInfoList = (List<TShopDailyInfo>) orderResponseBean.getResultList();
			} else {
				gLogger.error(orderResponseBean.getException() + orderResponseBean.getErrMsg());
				throw orderResponseBean.getException();
			}
			if(CollectionUtils.isEmpty(shopDailyInfoList)){//如果没取到数据直接结束任务
				gLogger.info("邮件：没有要发送的数据,结束任务");
				return;
			}

			// 初始化buffer表
			gLogger.info("初始化邮件buffer表");
			initEmailBuffer(shopDailyInfoList);
			gLogger.info("初始化邮件buffer表结束");
			// 开始进入发送
			processSend();
			gLogger.debug("一次JOB业务处理耗时，耗时：【" + (System.currentTimeMillis() - start) + "】毫秒！");
			gLogger.info("更新订单商店信息表...");
			updateShopDailyInfoStatus(shopDailyInfoList,Constants.SHOP_DAILY_STATUS_02);
			gLogger.info("更新订单商店信息表结束...");

		} catch (Exception e) {
			gLogger.error(e.getMessage());
			e.printStackTrace();
		}
		gLogger.end(xFunctionName);
		
	}
	
	/**
	 * @param shopDailyInfoList
	 * @throws Exception
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	private void updateShopDailyInfoStatus(List<TShopDailyInfo> shopDailyInfoList,String status) throws Exception {
		for (TShopDailyInfo shopDailyInfo : shopDailyInfoList) {
			shopDailyInfo.setShopDailyEmailStatus(status);
			shopDailyInfo.setModifyTime(new Date());
			
			OrderRequestBean requestBean = new OrderRequestBean();
			requestBean.setShopDailyInfo(shopDailyInfo);
			// 修改订单状态
			OrderResponseBean responseBean = orderService.updateShopDailyInfo(requestBean);
			
			if(!responseBean.getResponseCode().equals("00")){
				throw responseBean.getException();
			}
		}
	}

	/**
	 * 初始化邮件发送表
	 * @param shopDailyInfoList
	 * @throws Exception
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	private void initEmailBuffer(List<TShopDailyInfo> shopDailyInfoList) throws Exception {
		// 根据订单取订单商店信息
		List<TSendEmailBuffer> emailBufferList = new ArrayList<TSendEmailBuffer>();
		
		if (!CollectionUtils.isEmpty(shopDailyInfoList)) {
			// 更新邮件发送状态为发送中
			this.updateShopDailyInfoStatus(shopDailyInfoList,Constants.SHOP_DAILY_STATUS_01);
			
			for (TShopDailyInfo shopDailyInfo : shopDailyInfoList) {
				TSendEmailBuffer sendEmailBuffer = new TSendEmailBuffer();
				//邮件编号（外部编号关联）
				sendEmailBuffer.setBufferEmailCode(UUID.randomUUID().toString());
				sendEmailBuffer.setBufferEmailToAddress(shopDailyInfo.getShopInfo().getShopEmail());
				sendEmailBuffer.setBufferEmailFromAddress(CacheConstants.getParamValueByName(Constants.EMAIL_FROM_ADDRESS));
				// 是否需要身份验证(00-需要 01-不需要)
				sendEmailBuffer.setBufferEmailValidate(Constants.EMAIL_VALIDATE_00);
				sendEmailBuffer.setBufferEmailUserName(CacheConstants.getParamValueByName(Constants.EMAIL_USER_NAME));
				sendEmailBuffer.setBufferEmailPassword(CacheConstants.getParamValueByName(Constants.EMAIL_USER_PASSWORD));
				sendEmailBuffer.setBufferEmailSubject(CacheConstants.getParamValueByName(Constants.EMAIL_SUBJECT));
				sendEmailBuffer.setBufferEmailContent(shopDailyInfo.getShopSendContent());
				sendEmailBuffer.setBufferEmailAttachFiles("");// 邮件附件的文件名
				sendEmailBuffer.setBufferEmailSmtpHost(CacheConstants.getParamValueByName(Constants.SMTP_HOST));// 发送邮件的服务器的IP 
				sendEmailBuffer.setBufferEmailSmtpPort(CacheConstants.getParamValueByName(Constants.SMTP_PORT));// 发送邮件的服务器的端口
				sendEmailBuffer.setBufferEmailType(Constants.BUFFER_EMAIL_TYPE);
				sendEmailBuffer.setBufferEmailRunStartus(Constants.EMAIL_STATUS_01);
				sendEmailBuffer.setCreateUserCode(shopDailyInfo.getCreateUserCode());
				sendEmailBuffer.setCreateTime(new Date());
				
				emailBufferList.add(sendEmailBuffer);
			}
		}else{
			gLogger.info("没有要初始化的数据");
			return;
		}
		EmailRequestBean emailRequestBean = new EmailRequestBean();
		emailRequestBean.setSendEmailBufferList(emailBufferList);
		// 插入彩信buffer表
		emailService.insertBatchSendEmailBuffer(emailRequestBean);
	}

	/**
	 * 处理发送
	 * @throws Exception
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	private void processSend() throws Exception {
		try {
			EmailRequestBean requestBean = new EmailRequestBean();
			
			TSendEmailBufferExample example = new TSendEmailBufferExample();
			example.createCriteria().andBufferEmailRunStartusEqualTo(Constants.EMAIL_STATUS_01);
			
			requestBean.setSendEmailBufferExample(example);
			// 获取符合要求
			List<TSendEmailBuffer> emailBufferList = (List<TSendEmailBuffer>) emailService.selectEmailBuffer(requestBean).getResultList();

			// 遍历未发送彩信集合 BEGIN
			if (!CollectionUtils.isEmpty(emailBufferList)) {
				// 调用邮件下发接口 
				this.doSendEmail(emailBufferList);
				gLogger.debug("emailBufferList == " + emailBufferList);
			}else{
				gLogger.info("emailBufferList为空，结束发送邮件");
			}
		} catch (Exception e) {
			gLogger.exception("processSend", e);
			throw e;
		}
	}

	/**
	 * 发送邮件
	 * @param emailBufferList
	 * @throws Exception
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	public void doSendEmail(List<TSendEmailBuffer> emailBufferList) throws Exception {
		String xFunctionName = "doSendMms";
		for (final TSendEmailBuffer sendEmailBuffer : emailBufferList) {
			try {
				// 提交线程时间
				gLogger.debug("#进入线程池队列#,BufferEmailId：" + sendEmailBuffer.getBufferEmailId());
				emailTaskExecutor.execute(new Runnable() {
					public void run() {
						// 邮件下发接口
						sendInterfaceService.doSendEmailInterface(sendEmailBuffer);
					}
				});
			} catch (Exception e) {
				gLogger.exception(xFunctionName, e);
			}
		}
	}

	/**
	 * @return the emailService
	 */
	public IEmailService getEmailService() {
		return emailService;
	}

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(IEmailService emailService) {
		this.emailService = emailService;
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
	 * @return the emailTaskExecutor
	 */
	public ThreadPoolTaskExecutor getEmailTaskExecutor() {
		return emailTaskExecutor;
	}

	/**
	 * @param emailTaskExecutor the emailTaskExecutor to set
	 */
	public void setEmailTaskExecutor(ThreadPoolTaskExecutor emailTaskExecutor) {
		this.emailTaskExecutor = emailTaskExecutor;
	}

	/**
	 * @return the orderService
	 */
	public IOrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * @return the shopService
	 */
	public IShopService getShopService() {
		return shopService;
	}

	/**
	 * @param shopService the shopService to set
	 */
	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}

	/**
	 * @return the goodsService
	 */
	public IGoodsService getGoodsService() {
		return goodsService;
	}

	/**
	 * @param goodsService the goodsService to set
	 */
	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	/**
	 * @return the commonService
	 */
	public ICommonService getCommonService() {
		return commonService;
	}

	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}

}
