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
import app.cn.qtt.bm.common.utils.StringUtil;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoExample;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TOrderInfoShopExample;
import app.cn.qtt.bm.model.TSendMmsBuffer;
import app.cn.qtt.bm.model.TSendMmsBufferExample;
import app.cn.qtt.bm.model.TSendSmsBuffer;
import app.cn.qtt.bm.model.TSysParameter;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.model.TSysUserInfoExample;
import app.cn.qtt.bm.scheduler.SendInterfaceService;
import app.cn.qtt.bm.scheduler.SendScheduleService;
import app.cn.qtt.bm.scheduler.pojo.CSendMmsRequest;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.IMmsService;
import app.cn.qtt.bm.service.IOrderService;
import app.cn.qtt.bm.service.IShopService;
import app.cn.qtt.bm.service.ISmsService;
import app.cn.qtt.bm.service.IUserService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;
import app.cn.qtt.bm.service.pojo.MmsRequestBean;
import app.cn.qtt.bm.service.pojo.MmsResponseBean;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;
import app.cn.qtt.bm.service.pojo.SmsRequestBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;

/***
 * 
 * @author LONGTENG.WEI
 * @Description 彩信下发调度任务服务
 */
public class MmsSendScheduleServiceImpl implements SendScheduleService {
	private CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());

	private SendInterfaceService sendInterfaceService;
	private ThreadPoolTaskExecutor mmsTaskExecutor;
	private IOrderService orderService;
	private IShopService shopService;
	private IGoodsService goodsService;
	private IMmsService mmsService;
	private IUserService userService;
	private ICommonService commonService;
	private ISmsService smsService;
	
	/**
	 * 正式执行发送schedule
	 */
	public void processSendSchedule() {
		try {
			Date nowDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
			
			gLogger.info("彩信下发任务开始");
			gLogger.info("系统现在时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":" + calendar.get(Calendar.MILLISECOND));
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
			gLogger.info("本次彩信下发任务结束时间: " + nowHour + ":" + calendar.get(Calendar.MINUTE) + ":"
					+ calendar.get(Calendar.SECOND) + ":" + calendar.get(Calendar.MILLISECOND));
		} catch (Exception e) {
			gLogger.exception("processMmsSendSchedule", e);
		}
	}

	/***
	 * 处理彩信发送JOB业务
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
			List<TOrderInfo> orderList;
			OrderRequestBean orderRequestBean = new OrderRequestBean();
			TOrderInfoExample orderInfoExample = new TOrderInfoExample();
			// 订单状态为已订购
			String runStatus = "";
			if(CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_1) != null){
				runStatus = CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_1);
			}
			orderInfoExample.createCriteria().andOrderRunStatusEqualTo(runStatus);
			orderRequestBean.setOrderInfoExample(orderInfoExample);

			orderResponseBean = orderService.queryOrders(orderRequestBean);
			if (!orderResponseBean.getResponseCode().equals("00")) {
				throw orderResponseBean.getException();
			}
			orderList = (List<TOrderInfo>) orderResponseBean.getResultList();

			if (CollectionUtils.isEmpty(orderList)) {// 如果没取到数据直接结束任务
				gLogger.info("彩信：没有要发送的数据,结束任务");
				return;
			}

			// 初始化buffer表
			gLogger.info("初始化彩信buffer表");
			List<TOrderInfoShop> orderInfoShopList = initMmsBuffer(orderList);
			gLogger.info("初始化彩信buffer表结束");
			gLogger.info("开始更新orderInfoShop状态为发送中");
			modifyOrderShopInfoRunStatusForJob(orderInfoShopList,CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_2),CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_1));
			gLogger.info("将orderInfoShop状态为发送中完毕");
			// 开始进入发送
			processSend();
			gLogger.debug("一次JOB业务处理耗时，耗时：【" + (System.currentTimeMillis() - start) + "】毫秒！");
			gLogger.info("更新oderShop表...");
			modifyOrderShopInfoRunStatusForJob(orderInfoShopList, CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_3),CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_2));
			gLogger.info("更新oderShop表...");
			gLogger.info("更新oder表...");
			updateOrderStatus(orderList, CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_3),CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_2));
			gLogger.info("更新oder表结束");
		} catch (Exception e) {
			gLogger.error(e.getMessage());
			e.printStackTrace();
		}
		gLogger.end(xFunctionName);
	}
	
	

	/**
	 * 修改订单状态
	 * 
	 * @param orderList
	 * @param status
	 * @author GeYanmeng
	 * @throws Exception
	 * @date 2013-6-20
	 */
	private void updateOrderStatus(List<TOrderInfo> orderList, String targetStatus,String origStatus) throws Exception {
		OrderRequestBean requestBean = null;
		for (TOrderInfo orderInfo : orderList) {
			orderInfo.setOrderRunStatus(targetStatus);
			orderInfo.setModifyTime(new Date());
			
			requestBean = new OrderRequestBean();
			requestBean.setOrderInfo(orderInfo);
			requestBean.setOrderRunStatus(origStatus);

			OrderResponseBean responseBean = orderService.modifyOrderRunStatusForJob(requestBean);
			if (!responseBean.getResponseCode().equals("00")) {
				throw responseBean.getException();
			}
			if(responseBean.getDbReturnValue() < 1){
				throw new Exception("更新失败");
			}
		}
	}

	/**
	 * 更新订单商店信息状态
	 * 
	 * @param orderInfoShopList
	 * @param status
	 * @author Gabriel
	 * @throws Exception
	 * @date 2013-7-1
	 */
	private void modifyOrderShopInfoRunStatusForJob(List<TOrderInfoShop> orderInfoShopList, String targetStatus,String origStatus) throws Exception {
		OrderRequestBean requestBean = null;
		for (TOrderInfoShop orderInfoShop : orderInfoShopList) {
			orderInfoShop.setOrderShopRunStatus(targetStatus);
			orderInfoShop.setModifyTime(new Date());
			requestBean =  new OrderRequestBean();
			requestBean.setOrderInfoShop(orderInfoShop);
			requestBean.setOrderShopRunStatus(origStatus);

			OrderResponseBean responseBean = orderService.modifyOrderShopInfoRunStatusForJob(requestBean);
			if (!responseBean.getResponseCode().equals("00")) {
				throw responseBean.getException();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<TOrderInfoShop> initMmsBuffer(List<TOrderInfo> orderList) throws Exception {
		// 根据订单取订单商店信息
		OrderResponseBean orderResponseBean = null;

		List<TOrderInfoShop> orderInfoShopList = new ArrayList<TOrderInfoShop>();
		List<TOrderInfoShop> orderInfoShopListUpdateList = null;
		List<TSendSmsBuffer> smsBufferList = new ArrayList<TSendSmsBuffer>();
		if (!CollectionUtils.isEmpty(orderList)) {
			updateOrderStatus(orderList, CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_2),CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_1));
			for (TOrderInfo orderInfo : orderList) {
				List<TOrderInfoShop> orderInfoShopListTemp = null;
				orderInfoShopListUpdateList = new ArrayList<TOrderInfoShop>();
				
				OrderRequestBean orderRequestBean = new OrderRequestBean();
				TOrderInfoShopExample example = new TOrderInfoShopExample();
				example.createCriteria().andOrderIdEqualTo(orderInfo.getOrderId())
						.andOrderShopRunStatusEqualTo(CacheConstants.getParamValueByName(Constants.ORDER_RUN_STATUS_1));
				orderRequestBean.setOrderInfoShopExample(example);
				
				orderResponseBean = orderService.queryOrderInfoShopByExample(orderRequestBean);
				if (Constants.SELECT_SUCCESS_CODE.equals(orderResponseBean.getResponseCode())) {
					orderInfoShopListTemp = (List<TOrderInfoShop>) orderResponseBean.getResultList();
				} else {
					throw orderResponseBean.getException();
				}

				if (!CollectionUtils.isEmpty(orderInfoShopListTemp)) {
					MmsRequestBean mmsBufferRequestBean = new MmsRequestBean();
					List<TSendMmsBuffer> mmsBufferList = new ArrayList<TSendMmsBuffer>();
					Date date = new Date();
					for (TOrderInfoShop orderInfoShop : orderInfoShopListTemp) {
						TSendMmsBuffer sendMmsBuffer = new TSendMmsBuffer();
						sendMmsBuffer.setCreateTime(date);
						// 使用UUID作为彩信外部主键
						sendMmsBuffer.setMmsFeginCode(UUID.randomUUID().toString().replace("-", ""));
						sendMmsBuffer.setCreateUserCode(orderInfoShop.getCreateUserCode());
						sendMmsBuffer.setMmsRunStatus(Constants.BUFFER_RUN_STATUS_01);
						sendMmsBuffer.setMmsFilePath(orderInfoShop.getOrderShopQrcode());// 二维码路径
						if(StringUtil.isBlank(orderInfoShop.getOrderShopQrcode())){
							//如果二维码路径为空，则不插入buffer表
							continue;
						}
						// 设置附件路径 此处要生成文本的内容
						String mmsFilePath = SmilUtils.generateMmsTextFile(orderInfoShop, sendMmsBuffer);
						
						sendMmsBuffer.setMmsFilePath(mmsFilePath);
						sendMmsBuffer.setMmsStatus(Constants.MMS_STATUS_01);
						sendMmsBuffer.setMmsSendNumber(CacheConstants.getParamValueByName(Constants.MMS_SEND_ADDRESS));// 传入发送方号码
						// 接收号码
						/************获取接收号码***********/
						UserRequestBean userRequestBean = new UserRequestBean();
						TSysUserInfoExample userExample = new TSysUserInfoExample();
						userExample.createCriteria().andUserCodeEqualTo(orderInfoShop.getCreateUserCode());
						userRequestBean.setSysUserInfoExample(userExample);
						
						UserResponseBean userResponseBean = userService.queryForUserByExample(userRequestBean);
						
						String receiveMdn = "";
						if (Constants.SELECT_SUCCESS_CODE.equals(userResponseBean.getResponseCode())) {
							TSysUserInfo sysUserInfo = (TSysUserInfo)userResponseBean.getResultList().get(0);
							receiveMdn = sysUserInfo.getUserPhoneNumber();
						} else {
								throw userResponseBean.getException();
						}
						/************获取接收号码***********/
						sendMmsBuffer.setMmsReceiverNumber(receiveMdn);
						// 彩信类型
						sendMmsBuffer.setMmsType(Constants.MMS_TYPE_01);
						// 彩信主题
						sendMmsBuffer.setMmsSubject(CacheConstants.getParamValueByName(Constants.MMS_SUBJECT));
						// 生产smil文件 使用 common.util包下的SmilUtils工具
						String filePath = this.generateSmil(sendMmsBuffer);
						sendMmsBuffer.setMmsFilePath(filePath);
		
						mmsBufferList.add(sendMmsBuffer);
						orderInfoShopListUpdateList.add(orderInfoShop);
						
						TSendSmsBuffer sendSmsBuffer = new TSendSmsBuffer();
						// 短信内容
						String smsContent = SmilUtils.generateSmsContent(orderInfoShop, sendMmsBuffer);
						sendSmsBuffer.setBufferCreateTime(date);
						// 采用UUID作为短信buffer表外部主键
						sendSmsBuffer.setBufferSn(UUID.randomUUID().toString());
						sendSmsBuffer.setBufferMessage(smsContent);
						// 短信发送号码
						sendSmsBuffer.setBufferSenderName(CacheConstants.getParamValueByName(Constants.SMS_SENDER_NAME));
						// 短信内容接收号码
						sendSmsBuffer.setBufferReceiverMdn(receiveMdn);
						// 短信来源地址
						sendSmsBuffer.setBufferSourceAddr(CacheConstants.getParamValueByName(Constants.SMS_SOURCE_ADDR));
						//短信发送状态 可发送
						sendSmsBuffer.setBufferStatus(Constants.SMS_STATUS_01);
						sendSmsBuffer.setBufferType(Constants.SMS_TYPE_01);
						
						smsBufferList.add(sendSmsBuffer);
					}
					mmsBufferRequestBean.setMmsBufferList(mmsBufferList);
					// 插入彩信buffer表
					mmsService.insertBatchSendMmsBuffer(mmsBufferRequestBean);
				}
				orderInfoShopList.addAll(orderInfoShopListUpdateList);
			}
			
			SmsRequestBean smsBufferRequestBean = new SmsRequestBean();
			smsBufferRequestBean.setSendSmsBufferList(smsBufferList);
			smsService.insertBatchSendSmsBuffer(smsBufferRequestBean);
		}else{
			gLogger.debug("取到的orderInfoShop列表为空");
		}
		
		return orderInfoShopList;
	}

	/**
	 * 生成smil文件
	 * 
	 * @param tSendMmsBuffer
	 * @author GeYanmeng
	 * @throws Exception
	 * @date 2013-6-20
	 */
	private String generateSmil(TSendMmsBuffer sendMmsBuffer) throws Exception {
		String smilFileList = SmilUtils.markDefaultSmil(sendMmsBuffer.getMmsFeginCode(),
				sendMmsBuffer.getMmsFilePath(), CacheConstants.getParamValueByName(Constants.LAST_FRAME_FILE_PATH));

		return smilFileList;
	}

	/**
	 * 处理发送
	 */
	@SuppressWarnings("unchecked")
	protected void processSend() throws Exception {
		try {
			MmsRequestBean requestBean = new MmsRequestBean();
			TSendMmsBufferExample example = new TSendMmsBufferExample();
			example.createCriteria().andMmsRunStatusEqualTo(Constants.BUFFER_RUN_STATUS_01)
					.andMmsStatusEqualTo(Constants.MMS_STATUS_01);
			requestBean.setSendMmsBufferExample(example);
			// 获取符合要求数据
			MmsResponseBean responseBean = mmsService.selectMmsBuffer(requestBean);
			if (!responseBean.getResponseCode().equals("00")) {
				throw responseBean.getException();
			}
			List<TSendMmsBuffer> mmsBufferList = (List<TSendMmsBuffer>) responseBean.getResultList();

			// 遍历未发送彩信集合 BEGIN
			if (!CollectionUtils.isEmpty(mmsBufferList)) {
				List<CSendMmsRequest> mmsList = new ArrayList<CSendMmsRequest>();
				for (TSendMmsBuffer sendMmsBuffer : mmsBufferList) {
					Integer bufferId = sendMmsBuffer.getMmsId();
					String mmsFeginCode = sendMmsBuffer.getMmsFeginCode();
					String mdn = sendMmsBuffer.getMmsSendNumber();
					String sendto = sendMmsBuffer.getMmsReceiverNumber();
					String sendType = sendMmsBuffer.getMmsType();
					String subject = sendMmsBuffer.getMmsSubject();
					String sendAddress = CacheConstants.getParamValueByName(Constants.MMS_SEND_ADDRESS);
					String mmsTransparentType = sendMmsBuffer.getMmsTransparentType();
					String mmsFilePath = sendMmsBuffer.getMmsFilePath();
					// 封装彩信对象CSendMmsRequest
					if (StringUtils.isNotBlank(mdn) && StringUtils.isNotBlank(sendto)) {
						CSendMmsRequest request = new CSendMmsRequest(); // 组装待发彩信对象
						String mmsId = sendMmsBuffer.getMmsFeginCode();
						request.setMmsFeginCode(mmsFeginCode);
						request.setId(bufferId);
						request.setMmsId(mmsId);
						request.setMmsSubject(subject);
						request.setRecipient(sendto);
						request.setSender(mdn);
						request.setUserId(mdn);
						request.setContent(mmsFilePath);
						request.setSource(mmsFilePath);
						// 文本内容及spID,serviceID
						request.setSpID(CacheConstants.getParamValueByName(Constants.MMS_SP_ID));
						request.setSpServiceID(CacheConstants.getParamValueByName(Constants.MMS_SP_SERVICE_ID));
						request.setMdnAddressFlag(mmsTransparentType);
						request.setSenderAddress(sendAddress);
						request.setSendType(sendType);

						mmsList.add(request);
					}
				}
				// 遍历未发送彩信集合 END

				// 调用彩信下发接口
				if (mmsList != null && mmsList.size() > 0) {
					this.doSendMms(mmsList);
					gLogger.debug("mmsList == " + mmsList);
				}

				// 存在SMIL文件的时候 进行发送 END
			}else{
				gLogger.debug("mmsBufferList为空，没有要下发的彩信");
			}
		} catch (Exception e) {
			gLogger.exception("processSend", e);
			throw e;
		}
	}

	/**
	 * 彩信下发接口
	 * 
	 * @param mmsList
	 *            List<CSendMmsRequest> 待下发彩信对象
	 * @throws Exception
	 */
	public void doSendMms(List<CSendMmsRequest> mmsList) throws Exception {
		String xFunctionName = "doSendMms";
		for (final CSendMmsRequest request : mmsList) {
			try {
				// 提交线程时间
				gLogger.debug("#进入线程池队列#：" + request.getBatchId() + ",MMSID:" + request.getMmsId());
				mmsTaskExecutor.execute(new Runnable() {
					public void run() {
						// 彩信下发接口
						sendInterfaceService.doSendMmsInterface(request);
					}
				});
			} catch (Exception e) {
				gLogger.exception(xFunctionName, e);
			}
		}
	}

	public ThreadPoolTaskExecutor getMmsTaskExecutor() {
		return mmsTaskExecutor;
	}

	public void setMmsTaskExecutor(ThreadPoolTaskExecutor mmsTaskExecutor) {
		this.mmsTaskExecutor = mmsTaskExecutor;
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

	public IMmsService getMmsService() {
		return mmsService;
	}

	public void setMmsService(IMmsService mmsService) {
		this.mmsService = mmsService;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
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

	/**
	 * @return 返回 smsService
	 */
	public ISmsService getSmsService() {
		return smsService;
	}

	/**
	 * @param 对smsService进行赋值
	 */
	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}
}
