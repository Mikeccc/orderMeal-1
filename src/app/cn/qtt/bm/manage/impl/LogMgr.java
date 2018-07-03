
 /*
 * 文 件 名:  LogMgr.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-4-5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.manage.impl;

import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.ILogMgr;
import app.cn.qtt.bm.model.TLogUserAccess;
import app.cn.qtt.bm.model.TLogUserLogin;
import app.cn.qtt.bm.service.ILogService;
import app.cn.qtt.bm.service.pojo.LogRequestBean;
import app.cn.qtt.bm.service.pojo.LogResponseBean;



 /**       
 * 项目名称：CmEnterpriseProject    
 * 类名称：LogMgr    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-4-5 下午3:55:46    
 * 修改人：linch   
 * 修改时间：2013-4-5 下午3:55:46    
 * 修改备注：    
 * @version       
 */

public class LogMgr extends BaseService implements ILogMgr{
	
	private ILogService logService;
	
	
	
	
	/**
	* 方法名称:      saveUserLoginLog  
	* 方法描述:      保存用登录日志
	* @param userBean
	* @param ip
	* @param sessionId
	* @return
	* @throws CMException   
	* @throws CMRollBackException       
	* @Author:      linch
	* @Create Date: 2013-4-5 下午4:24:38
	*/ 
	 
	public TLogUserLogin saveUserLoginLog(UserBean userBean,
			String ip, String sessionId) throws CMException,CMRollBackException {
		final String xFunctionName = "saveSmsVerificationCaptcha";
		gLogger.begin(xFunctionName);
		TLogUserLogin login = new TLogUserLogin();
		
		try {
			Assert.notNull(userBean);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userBean is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userBean");
		}
		
		try {
			Assert.notNull(ip);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("ip is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "ip");
		}
		
		try {
			Assert.notNull(sessionId);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("sessionId is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "sessionId");
		}
		
		try{
			
			login.setUserCode(userBean.getUserCode());
			
			login.setUserLoginIp(ip);
			login.setUserLoginTime(new Date());
			login.setUserSessionId(sessionId);
			
			
			//填充请求
			LogRequestBean requestBean = new LogRequestBean();
			requestBean.setLogUserLogin(login);
			
			LogResponseBean responseBean = logService.saveUserLoginLog(requestBean);
			
			
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			else{
				login = responseBean.getLogUserLogin();
			}
		}
		catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
		return login;
		
	}
	
	
	
	
	/**
	* 方法名称:      saveLogUserAccesses  
	* 方法描述:      保存日志列表
	* @param tLogUserAccesses
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-6-8 下午5:52:20
	*/ 
	 
	public void saveLogUserAccesses(List<TLogUserAccess> tLogUserAccesses)
			throws CMException, CMRollBackException {
		final String xFunctionName = "saveLogsAccesses";
		gLogger.begin(xFunctionName);
		
		try {
			Assert.notEmpty(tLogUserAccesses);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userBean is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userBean");
		}
		
		//循环保存日志
		try{
			
			for(TLogUserAccess tLogUserAccess : tLogUserAccesses){
				LogRequestBean requestBean = new LogRequestBean();
				requestBean.settLogUserAccess(tLogUserAccess);
				LogResponseBean responseBean = logService.saveLogsAccess(requestBean);
				if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
					super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
					throw CMRollBackException.sys(responseBean.getErrMsg(),
							ExceptionConstants.SERVICE_CODE, null);
				}
				
			}
			
		}
		catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
	}

	public ILogService getLogService() {
	
		return logService;
	}

	public void setLogService(ILogService logService) {
	
		this.logService = logService;
	}



	
	

}

