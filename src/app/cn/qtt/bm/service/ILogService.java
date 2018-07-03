package app.cn.qtt.bm.service;

import app.cn.qtt.bm.service.pojo.LogRequestBean;
import app.cn.qtt.bm.service.pojo.LogResponseBean;


public interface ILogService {
	
	
	
	/**
	* 方法名称:      saveUserLoginLog  
	* 方法描述:      保存用户登录日志
	* @param requestBean
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-5 下午3:50:44
	*/ 
	 
	public LogResponseBean saveUserLoginLog(LogRequestBean requestBean);
	
	
	
	
	/**
	* 方法名称:      saveLogsAccess  
	* 方法描述:      保存用户操作日志
	* @param requestBean
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-23 下午5:46:40
	*/ 
	 
	public LogResponseBean saveLogsAccess(
			LogRequestBean requestBean);
	
}
