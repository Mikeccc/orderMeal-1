package app.cn.qtt.bm.manage;

import java.util.List;

import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.model.TLogUserAccess;
import app.cn.qtt.bm.model.TLogUserLogin;

public interface ILogMgr {

	
	
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
	* @Create Date: 2013-6-9 下午5:08:27
	*/ 
	 
	public TLogUserLogin saveUserLoginLog(UserBean userBean,
			String ip, String sessionId) throws CMException,CMRollBackException ;
	
	
	
	
	/**
	* 方法名称:      saveLogUserAccesses  
	* 方法描述:      保存日志列表
	* @param tLogUserAccesses
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-6-9 下午5:08:31
	*/ 
	 
	public void saveLogUserAccesses(List<TLogUserAccess> tLogUserAccesses) throws CMException,CMRollBackException;
	
}

