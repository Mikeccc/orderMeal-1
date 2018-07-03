package app.cn.qtt.bm.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.dao.TSendMmsBufferDAO;
import app.cn.qtt.bm.dao.TSendMmsHistoryDAO;
import app.cn.qtt.bm.model.TSendMmsBuffer;
import app.cn.qtt.bm.model.TSendMmsBufferExample;
import app.cn.qtt.bm.model.TSendMmsHistory;
import app.cn.qtt.bm.scheduler.pojo.CSendMmsRequest;
import app.cn.qtt.bm.service.IMmsService;
import app.cn.qtt.bm.service.pojo.MmsRequestBean;
import app.cn.qtt.bm.service.pojo.MmsResponseBean;
import cn.qtt.core.message.model.MultimediaMessage;
import cn.qtt.core.message.service.impl.MultimediaMessageServiceImpl;
import cn.qtt.core.toolkit.StringHelper;

public class MmsServiceImpl implements IMmsService{
	private CCrppLog4j log = new CCrppLog4j(getClass().getName());
	
	/**
	 * 彩信发送数据访问对象
	 */
	TSendMmsBufferDAO sendMmsBufferDAO;
	/**
	 * 彩信发送历史数据访问对象
	 */
	TSendMmsHistoryDAO sendMmsHistoryDAO;
	
	@Override
	public String doSend(CSendMmsRequest request) {
		MultimediaMessage message = null;
		try {
			//封装彩信发送对象
			message = this.initMultimediaMessage(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MultimediaMessageServiceImpl multimediaMessageService = new MultimediaMessageServiceImpl();
		multimediaMessageService.setSpId(CacheConstants.getParamValueByName(Constants.MMS_SP_ID));
		multimediaMessageService.setSpServiceId(CacheConstants.getParamValueByName(Constants.MMS_SP_SERVICE_ID));
		multimediaMessageService.setSenderAddress(CacheConstants.getParamValueByName(Constants.MMS_SEND_ADDRESS));
		multimediaMessageService.setSpPassword(CacheConstants.getParamValueByName(Constants.MMS_SP_PASSWORD));
		multimediaMessageService.setMmsReceiveUrl(CacheConstants.getParamValueByName(Constants.MMS_RECEIVE_URL));
		multimediaMessageService.setMmsSendUrl(CacheConstants.getParamValueByName(Constants.MMS_SEND_URL));
		Map map = new HashMap();
		map.put("txt", "text/plain; charset=UTF-8");
		map.put("text", "text/plain; charset=UTF-8");
		map.put("smil", "application/smil");
		map.put("3gp", "video/3gpp");
		map.put("3gpp", "video/3gpp");
		map.put("jpeg", "image/jpeg");
		map.put("gif", "image/gif");
		map.put("png", "image/png");
		map.put("amr", "audio/amr");
		multimediaMessageService.setContentTypeMap(map);
		
		request.setBeforeSendTime(new Date());
		
		String result = multimediaMessageService.sendMms(message);
		request.setAfterSendTimeSendTime(new Date());
		// 更新发送历史
		this.updateMmsSentHistory(request, result);
		
		return null;
	}
	
	private void updateMmsSentHistory(final CSendMmsRequest request, final String result) {
		final String xFunctionName = "updateMmsSentHistory(CSendMmsRequest request, final String result)";
		try {
			log.begin(xFunctionName);
			log.debug("彩信网关返回流水号 -> " + result);
			final boolean success = StringUtils.isNotBlank(result) ? true : false; // 是否发送成功
			String mmsHistorySendStatus = "";
			String mmsSendStatus = "";
			if (success) { // 发送成功
				mmsHistorySendStatus = Constants.MMS_HISTORY_SEND_STATUS_00;
				mmsSendStatus = Constants.BUFFER_RUN_STATUS_04;
			} else { //发送失败
				mmsHistorySendStatus = Constants.MMS_HISTORY_SEND_STATUS_01;
				mmsSendStatus = Constants.BUFFER_RUN_STATUS_00;
			}
			
			//1.update buffer表
			MmsRequestBean requestBean = new MmsRequestBean();
			TSendMmsBuffer sendMmsBuffer = new TSendMmsBuffer();
			sendMmsBuffer.setMmsId(request.getId());
			sendMmsBuffer.setModifyTime(new Date());
			sendMmsBuffer.setMmsRunStatus(mmsSendStatus);
			requestBean.setSendMmsBuffer(sendMmsBuffer);
			MmsResponseBean responseBean = this.updateMmsBufferStatus(requestBean);
			if(!responseBean.getResponseCode().equals("00")){
				throw responseBean.getException();
			}
			
			//2.insert into t_send_mms_history
			TSendMmsHistory sendMmsHistory = new TSendMmsHistory();
			
			sendMmsHistory.setMmsFeginCode(request.getMmsFeginCode());
			sendMmsHistory.setMmsHistorySendMdn(request.getSender());
			sendMmsHistory.setMmsHistoryReceiveMdn(request.getRecipient());
			sendMmsHistory.setMmsHistorySubject(request.getMmsSubject());
			sendMmsHistory.setMmsHistoryFilePaths(request.getSource());
			sendMmsHistory.setMmsHistorySn(request.getMmsId());// 彩信流水号
			sendMmsHistory.setMmsHistorySendStatus(mmsHistorySendStatus);
			sendMmsHistory.setMmsHistorySendSwift(result);//彩信返回流水号
			sendMmsHistory.setMmsHistoryFeeType(request.getFeeType());//收费类型
			sendMmsHistory.setMmsHistoryCreateTime(new Date());
			sendMmsHistory.setMmsHistorySendType(request.getSendType());
			sendMmsHistory.setMmsHistorySendCount(request.getSend_count());//发送次数 
			sendMmsHistory.setMmsHistoryTransparentNumber(request.getSenderAddress());
			sendMmsHistory.setMmsHistoryBeforeSendTime(request.getBeforeSendTime());
			sendMmsHistory.setMmsHistoryAfterSendTime(request.getAfterSendTimeSendTime());
			
			requestBean.setSendMmsHistory(sendMmsHistory);
			responseBean = this.insertSendMmsHistory(requestBean);
			if(!responseBean.getResponseCode().equals("00")){
				throw responseBean.getException();
			}
		} catch (Exception e) {
			log.exception(xFunctionName, e);
		} finally {
			log.end(xFunctionName);
		}
	}

	@Override
	public MmsResponseBean insertBatchSendMmsBuffer(MmsRequestBean requestBean) {
		MmsResponseBean responseBean = new MmsResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getMmsBufferList() == null ||requestBean.getMmsBufferList().size()==0){
				throw new Exception("彩信发送列表为空");
			}
			sendMmsBufferDAO.batchInsert(requestBean.getMmsBufferList());
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public MmsResponseBean updateMmsBufferStatus(MmsRequestBean requestBean) {
		MmsResponseBean responseBean = new MmsResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendMmsBuffer() != null){
				sendMmsBufferDAO.updateByPrimaryKeySelective(requestBean.getSendMmsBuffer());
			}else{
				throw new Exception("SendMmsBuffer为空");
			}
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public MmsResponseBean insertSendMmsHistory(MmsRequestBean requestBean) {
		MmsResponseBean responseBean = new MmsResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendMmsHistory() != null){
				sendMmsHistoryDAO.insert(requestBean.getSendMmsHistory());
			}else{
				throw new Exception("SendMmsHistory对象为空");
			}
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public MmsResponseBean selectMmsBuffer(MmsRequestBean requestBean) {
		MmsResponseBean responseBean = new MmsResponseBean();
		TSendMmsBufferExample example = new TSendMmsBufferExample();
		List<TSendMmsBuffer> resultList = null;
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getSendMmsBufferExample() !=null){
				example = requestBean.getSendMmsBufferExample();
			}
			resultList = sendMmsBufferDAO.selectByExample(example);
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	/**
	 * 
	 * @Description: 获取发送彩漫实体信息
	 * @see app.cn.qtt.cmbase.mms.MmsDataIntefacdSendService#getMultimediaMessage(app.cn.qtt.cmbase.model.TMmsDatainterfaceSend)
	 */
	public MultimediaMessage initMultimediaMessage(CSendMmsRequest request) throws Exception {
		MultimediaMessage message = new MultimediaMessage();
		final String mmsId = String.valueOf(request.getMmsId()); // t_mms_datainterface_send表主键作为关联值
		String subject = request.getMmsSubject();
		String callingMdn = StringHelper.getPrimalPhoneNumberFrom(request.getSender());
		String[] calledMdns = new String[] { request.getRecipient()};
		String[] attachments = StringUtils.split(request.getContent(),Constants.EMAIL_ATTACHFILES_SPLIT_CODE);

		log.info("彩信发送表ID为：" + mmsId);
		message.setMmsId(mmsId);
		log.info("彩信主题为：[" + subject + "]");
		message.setSubject(subject);

		log.info("彩信主叫号码：[" + callingMdn + "]");
		message.setCallingMdn(callingMdn);
		if (ArrayUtils.isEmpty(calledMdns)) {
			throw new Exception("彩信被叫号码不能为空！");
		}
		// 校验被叫号码的合法性
		for (int i = 0; i < calledMdns.length; i++) {
			String calledMdn = StringHelper.getPrimalPhoneNumberFrom(calledMdns[i]);
			log.info("彩信[" + mmsId + "]被叫号码为：" + calledMdn);
			calledMdns[i] = calledMdn;
		}
		
		message.setCalledMdns(calledMdns);
		// 校验彩信附件的合法性
		if (ArrayUtils.isEmpty(attachments)) {
			throw new Exception("彩信附件列表不能为空！");
		}
		int index = 1;
		for (String filepath : attachments) {
			File file = new File(filepath);
			log.info("彩信[" + mmsId + "]附件[" + index++ + "]:" + file);
			if (!file.exists()) {
				throw new Exception("[" + file + "]该文件不存在！");
			}
		}
		// 设置彩信附件
		message.setAttachments(attachments);
		//预透传的号码
		message.setSenderAddress(request.getSenderAddress());
		
		return message;
	}
	
	/**
	 * @return the sendMmsBufferDAO
	 */
	public TSendMmsBufferDAO getSendMmsBufferDAO() {
		return sendMmsBufferDAO;
	}

	/**
	 * @param sendMmsBufferDAO the sendMmsBufferDAO to set
	 */
	public void setSendMmsBufferDAO(TSendMmsBufferDAO sendMmsBufferDAO) {
		this.sendMmsBufferDAO = sendMmsBufferDAO;
	}

	/**
	 * @return the sendMmsHistoryDAO
	 */
	public TSendMmsHistoryDAO getSendMmsHistoryDAO() {
		return sendMmsHistoryDAO;
	}

	/**
	 * @param sendMmsHistoryDAO the sendMmsHistoryDAO to set
	 */
	public void setSendMmsHistoryDAO(TSendMmsHistoryDAO sendMmsHistoryDAO) {
		this.sendMmsHistoryDAO = sendMmsHistoryDAO;
	}

}
