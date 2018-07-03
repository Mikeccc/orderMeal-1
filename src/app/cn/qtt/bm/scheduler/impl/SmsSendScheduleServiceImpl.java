package app.cn.qtt.bm.scheduler.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.SmilUtils;
import app.cn.qtt.bm.common.utils.UtilTools;
import app.cn.qtt.bm.model.SmsContext;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoExample;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.model.TSendSmsBufferExample;
import app.cn.qtt.bm.model.TShopDailyInfo;
import app.cn.qtt.bm.model.TShopDailyInfoExample;
import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.scheduler.SendInterfaceService;
import app.cn.qtt.bm.scheduler.SendScheduleService;
import app.cn.qtt.bm.scheduler.pojo.CSendSmsRequest;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.IOrderService;
import app.cn.qtt.bm.service.IShopService;
import app.cn.qtt.bm.service.ISmsService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;
import app.cn.qtt.bm.service.pojo.SmsRequestBean;
import app.cn.qtt.bm.service.pojo.SmsResponseBean;

/***
 * 
 * @author LONGTENG.WEI
 * @Description 短信下发调度任务服务
 */
public class SmsSendScheduleServiceImpl implements SendScheduleService {
	private CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());

	private SendInterfaceService sendInterfaceService;
	private ThreadPoolTaskExecutor smsTaskExecutor;
	private IOrderService orderService;
	private IShopService shopService;
	private IGoodsService goodsService;
	private ISmsService smsService;
	private ICommonService commonService;

	/**
	 * 正式执行发送schedule
	 */
	public void processSendSchedule() {
		try {
			Date nowDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
			
			gLogger.info("短信下发任务开始");
			gLogger.info("系统现在时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
			int startHour = 0;
			int endHour = 0;
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
				endHour = NumberUtils.toInt(list.get(0).getParamValue());
			}else{
				throw responseBean.getException();
			}
			
			if (nowHour >= startHour && nowHour <= endHour) {
				gLogger.info("初始化t_shop_daily_info数据开始");
				this.initShopDailyInfo();
				gLogger.info("初始化t_shop_daily_info数据完毕");
				this.sendBusiness();
			}
			nowDate = new Date();
			calendar.setTime(nowDate);
			gLogger.info("本次短信发送任务结束时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":"+calendar.get(Calendar.MILLISECOND));
		} catch (Exception e) {
			gLogger.exception("processMmsSendSchedule", e);
		}
	}

	/***
	 * 处理短信发送JOB业务
	 * 
	 * @param connector
	 */
	@SuppressWarnings("unchecked")
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
				.andShopDailySmsStatusEqualTo(Constants.SHOP_DAILY_STATUS_00)
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
				gLogger.info("短信：没有要发送的数据,结束任务");
				return;
			}

			// 初始化buffer表
			gLogger.info("初始化短信buffer表");
			initSmsBuffer(shopDailyInfoList);
			gLogger.info("初始化短信buffer表结束");
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
	 * 修改订单状态(发送完成)
	 * @param shopDailyInfoList
	 * @author GeYanmeng
	 * @throws Exception 
	 * @date 2013-6-20
	 */
	private void updateShopDailyInfoStatus(List<TShopDailyInfo> shopDailyInfoList,String status) throws Exception {
		for (TShopDailyInfo shopDailyInfo : shopDailyInfoList) {
			shopDailyInfo.setShopDailySmsStatus(status);
			shopDailyInfo.setModifyTime(new Date());
			
			OrderRequestBean requestBean = new OrderRequestBean();
			requestBean.setShopDailyInfo(shopDailyInfo);
			// 修改订单状态
			OrderResponseBean responseBean = orderService.updateShopDailyInfo(requestBean);
			
			if(!responseBean.getResponseCode().equals("00")){
				throw new Exception(responseBean.getErrMsg(),responseBean.getException());
			}
			if(responseBean.getUpdateReturnValue() < 1){
				throw new Exception("更新失败");
			}
		}
	}
	
	/**
	 * 初始化短信发送表
	 * @param shopDailyInfoList
	 * @throws Exception
	 * @author Gabriel
	 * @date 2013-6-28
	 */
	private void initSmsBuffer(List<TShopDailyInfo> shopDailyInfoList) throws Exception {
		List<TSendSmsBuffer> smsBufferList = new ArrayList<TSendSmsBuffer>();

		if (!CollectionUtils.isEmpty(shopDailyInfoList)) {
			// 更新短信发送状态为发送中
			this.updateShopDailyInfoStatus(shopDailyInfoList,Constants.SHOP_DAILY_STATUS_01);
		}else{
			gLogger.info("没有要初始化的数据");
			return;
		}
		//组装短信bufferList
		SmsRequestBean smsBufferRequestBean = new SmsRequestBean();
		
		Date date = new Date();
		for (TShopDailyInfo shopDailyInfo : shopDailyInfoList) {
			TSendSmsBuffer sendSmsBuffer = new TSendSmsBuffer();
			
			sendSmsBuffer.setBufferCreateTime(date);
			// 采用UUID作为短信buffer表外部主键
			sendSmsBuffer.setBufferSn(UUID.randomUUID().toString());
			sendSmsBuffer.setBufferMessage(shopDailyInfo.getShopSendContent());
			// 短信发送号码
			sendSmsBuffer.setBufferSenderName(CacheConstants.getParamValueByName(Constants.SMS_SENDER_NAME));
			// 短信内容接收号码
			sendSmsBuffer.setBufferReceiverMdn(shopDailyInfo.getShopInfo().getShopPhoneNumber());
			// 短信来源地址
			sendSmsBuffer.setBufferSourceAddr(CacheConstants.getParamValueByName(Constants.SMS_SOURCE_ADDR));
			//短信发送状态 可发送
			sendSmsBuffer.setBufferStatus(Constants.SMS_STATUS_01);
			sendSmsBuffer.setBufferType(Constants.SMS_TYPE_02);
			
			smsBufferList.add(sendSmsBuffer);
		}
		smsBufferRequestBean.setSendSmsBufferList(smsBufferList);
		//插入短信buffer表
		smsService.insertBatchSendSmsBuffer(smsBufferRequestBean);
	}

	/**
	 * 处理发送
	 */
	@SuppressWarnings("unchecked")
	protected void processSend() throws Exception {
		try {
			SmsRequestBean requestBean = new SmsRequestBean();
			TSendSmsBufferExample example = new TSendSmsBufferExample();
			example.createCriteria().andBufferStatusEqualTo(Constants.SMS_STATUS_01).andBufferTypeEqualTo(Constants.SMS_TYPE_02);
			requestBean.setSendSmsBufferExample(example);
			// 获取符合要的数据
			List<TSendSmsBuffer> smsBufferList = null;
			SmsResponseBean responseBean = smsService.selectSmsBuffer(requestBean);
			if(responseBean.getResponseCode().equals("00")){
				smsBufferList = (List<TSendSmsBuffer>) responseBean.getResultList();
			}else{
				throw responseBean.getException();
			}
			
			// 遍历未发送彩信集合 BEGIN
			if (!CollectionUtils.isEmpty(smsBufferList)) {
				List<CSendSmsRequest> smsList = new ArrayList<CSendSmsRequest>();
				for (TSendSmsBuffer sendSmsBufferTemp : smsBufferList) {
					Long bufferId = sendSmsBufferTemp.getBufferId();
					//短信发送号码
					String mdn = sendSmsBufferTemp.getBufferSenderName();
					//接收号码
					String sendto = sendSmsBufferTemp.getBufferReceiverMdn();
					//短信发送类型
					String sendType = sendSmsBufferTemp.getBufferType();
					//短信来源地址
					String senderAddress = sendSmsBufferTemp.getBufferSourceAddr();
					String bufferMessage = sendSmsBufferTemp.getBufferMessage();
					// 封装短信对象CSendMmsRequest
					if (StringUtils.isNotBlank(mdn) && StringUtils.isNotBlank(sendto)) {
						CSendSmsRequest request = new CSendSmsRequest(); // 组装待发短信对象
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
				// 调用短信下发接口
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
	 * 短信下发接口 add by jjf
	 */
	public void doSendSms(List<CSendSmsRequest> smsList) throws Exception {
		String xFunctionName = "doSendSms发送短信";
		for (final CSendSmsRequest request : smsList) {
			try {
				// 提交线程时间
				gLogger.debug("#进入线程池队列#：" + request.getBatchId() + ",SMSID:" + request.getSmsId());
				smsTaskExecutor.execute(new Runnable() {
					public void run() {
						// 短信下发接口
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
	 * 初始化t_shop_daily_info数据
	 * @author Gabriel
	 * @throws Exception 
	 * @date 2013-6-28
	 */
	private void initShopDailyInfo() throws Exception {
		OrderResponseBean orderResponseBean;
		List<TOrderInfo> orderList;
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		TOrderInfoExample orderInfoExample = new TOrderInfoExample();
		// 订单状态为已订购
		String runStatus = "";
		if(CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_3) != null){
			runStatus = CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_3);
		}
		gLogger.info("runStatus:"+runStatus);
		orderInfoExample.createCriteria()
			.andOrderRunStatusEqualTo(runStatus)
				.andCreateTimeGreaterThanOrEqualTo(new Date(System.currentTimeMillis()-1000*60*60*24));
		orderRequestBean.setOrderInfoExample(orderInfoExample);

		orderResponseBean = orderService.queryOrders(orderRequestBean);
		if(!orderResponseBean.getResponseCode().equals("00")){
			throw orderResponseBean.getException();
		}
		orderList = (List<TOrderInfo>) orderResponseBean.getResultList();
		
		if(CollectionUtils.isEmpty(orderList)){//如果没取到数据直接结束任务
			gLogger.info("没有符合要求的数据，初始化结束");
			return;
		}
		
		List<TShopDailyInfo> shopDailyInfoList = new ArrayList<TShopDailyInfo>();
		String shopIdList = "";
		for(TOrderInfo orderInfo:orderList){
			
			TOrderInfoShop orderInfoShop = new TOrderInfoShop();
			orderInfoShop.setOrderId(orderInfo.getOrderId());
			
			orderRequestBean = new OrderRequestBean();
			orderRequestBean.setOrderInfoShop(orderInfoShop);
			
			orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
			
			if(!orderResponseBean.getResponseCode().equals("00")){
				throw orderResponseBean.getException();
			}
			
			List<TOrderInfoShop> orderInfoShopList = (List<TOrderInfoShop>) orderResponseBean.getResultList();
			if(orderInfoShopList.size() == 0){
				gLogger.info("该订单为空订单，订单下没有任何商家");
				continue;
			}
			
			for(TOrderInfoShop orderInfoShopTemp:orderInfoShopList){
				System.out.println("shopIdList:"+shopIdList);
				TShopDailyInfo shopDailyInfo = new TShopDailyInfo();
				System.out.println("orderInfoShopTemp.getShopId():"+orderInfoShopTemp.getShopId());
				if(shopIdList.indexOf(orderInfoShopTemp.getShopId().toString()+",")<0){
				
					shopIdList += orderInfoShopTemp.getShopId()+",";
					System.out.println("shopIdList:"+shopIdList);
					shopDailyInfo.setShopDailyCode(orderInfoShopTemp.getOrderId()+SmilUtils.Random());
					shopDailyInfo.setShopId(orderInfoShopTemp.getShopId());
					/***********拼装短信和邮件内容start*************/
					orderRequestBean = new OrderRequestBean();
					orderRequestBean.setOrderInfoShop(orderInfoShopTemp);
					
					orderResponseBean = orderService.queryShopOrderDetail(orderRequestBean);
					if(!orderResponseBean.getResponseCode().equals("00")){
						throw orderResponseBean.getException();
					}
					List<SmsContext> smsContextList = (List<SmsContext>) orderResponseBean.getResultList();
					
					String msgContext = UtilTools.generateSmsContext(smsContextList);
					/***********拼装短信和邮件内容end**************/
					shopDailyInfo.setShopSendContent(msgContext);
					shopDailyInfo.setShopDailyEmailStatus(Constants.SHOP_DAILY_STATUS_00);
					shopDailyInfo.setShopDailySmsStatus(Constants.SHOP_DAILY_STATUS_00);
					shopDailyInfo.setCreateUserCode(orderInfoShopTemp.getCreateUserCode());
					shopDailyInfo.setCreateTime(new Date());
					
					shopDailyInfoList.add(shopDailyInfo);
				}else{
					continue;
				}
			}
		}
		
		orderRequestBean = new OrderRequestBean();
		orderRequestBean.setShopDailyInfoList(shopDailyInfoList);
		orderResponseBean = orderService.addShopDailyInfo(orderRequestBean);
		if(!orderResponseBean.getResponseCode().equals("00")){
			throw orderResponseBean.getException();
		}
	}

	public ThreadPoolTaskExecutor getSmsTaskExecutor() {
		return smsTaskExecutor;
	}

	public void setSmsTaskExecutor(ThreadPoolTaskExecutor smsTaskExecutor) {
		this.smsTaskExecutor = smsTaskExecutor;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public IShopService getShopService() {
		return shopService;
	}

	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}

	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public SendInterfaceService getSendInterfaceService() {
		return sendInterfaceService;
	}

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
