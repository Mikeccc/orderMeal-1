
 /*
 * 文 件 名:  LogService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-4-5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.service.impl;

import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.dao.TLogUserAccessDAO;
import app.cn.qtt.bm.dao.TLogUserLoginDAO;
import app.cn.qtt.bm.service.ILogService;
import app.cn.qtt.bm.service.pojo.LogRequestBean;
import app.cn.qtt.bm.service.pojo.LogResponseBean;



 /**       
 * 项目名称：CmService_new    
 * 类名称：LogService    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-4-5 下午3:51:20    
 * 修改人：linch   
 * 修改时间：2013-4-5 下午3:51:20    
 * 修改备注：    
 * @version       
 */

public class LogServiceImpl implements ILogService {

	protected CCrppLog4j log = new CCrppLog4j(this.getClass()
			.getName());
	
	
	private TLogUserLoginDAO tLogUserLoginDAO;
	
	private TLogUserAccessDAO tLogUserAccessDAO;
	
	/**
	 * 方法名称:      saveUserLoginLog  
	 * 方法描述:      保存用户日志
	 * @param requestBean
	 * @return        
	 * @Author:      linch
	 * @Create Date: 2013-4-5 下午3:51:20
	 */

	@Override
	public LogResponseBean saveUserLoginLog(
			LogRequestBean requestBean) {
		LogResponseBean response = new LogResponseBean();
		String xFunctionName = "saveUserLoginLog";
		
		try {
			log.begin(xFunctionName);
			
			//验证传参
			if (requestBean == null
					|| requestBean.getLogUserLogin() == null
					) {
				response.setResponseCode(String
						.valueOf(ExceptionConstants.ILLEGAL_ARGUMENT_CODE));
				response.setErrMsg("argument is null !");
				return response;
			}

			
			Integer id = tLogUserLoginDAO.insert(requestBean.getLogUserLogin() );
			requestBean.getLogUserLogin().setLogUserLoginId(id);
			response.setLogUserLogin(requestBean.getLogUserLogin());
			
		} catch (Exception e) {
			response.setResponseCode(String
					.valueOf(ExceptionConstants.UNKNOWN_BUSINESS_CODE));
			response.setException(e);
			response.setErrMsg("exception !");
			log.exception(xFunctionName, e);
		}
		finally{
			log.end(xFunctionName);
		}
		
		return response;
		

	}
	
	
	/**
	 * 方法名称:      saveUserLoginLog  
	 * 方法描述:      保存用户日志
	 * @param requestBean
	 * @return        
	 * @Author:      linch
	 * @Create Date: 2013-4-5 下午3:51:20
	 */

	@Override
	public LogResponseBean saveLogsAccess(
			LogRequestBean requestBean) {
		LogResponseBean response = new LogResponseBean();
		String xFunctionName = "saveLogsAccess";
		
		try {
			log.begin(xFunctionName);
			
			//验证传参
			if (requestBean == null
					|| requestBean.gettLogUserAccess() == null
					) {
				response.setResponseCode(String
						.valueOf(ExceptionConstants.ILLEGAL_ARGUMENT_CODE));
				response.setErrMsg("argument is null !");
				return response;
			}

			
			tLogUserAccessDAO.insert(requestBean.gettLogUserAccess() );
			
		} catch (Exception e) {
			response.setResponseCode(String
					.valueOf(ExceptionConstants.UNKNOWN_BUSINESS_CODE));
			response.setException(e);
			response.setErrMsg("exception !");
			log.exception(xFunctionName, e);
		}
		finally{
			log.end(xFunctionName);
		}
		
		return response;
		

	}
	
	

	public TLogUserLoginDAO getTLogUserLoginDAO() {
	
		return tLogUserLoginDAO;
	}

	public void setTLogUserLoginDAO(TLogUserLoginDAO tLogUserLoginDAO) {
	
		this.tLogUserLoginDAO = tLogUserLoginDAO;
	}


	public TLogUserLoginDAO gettLogUserLoginDAO() {
	
		return tLogUserLoginDAO;
	}


	public void settLogUserLoginDAO(TLogUserLoginDAO tLogUserLoginDAO) {
	
		this.tLogUserLoginDAO = tLogUserLoginDAO;
	}


	public TLogUserAccessDAO gettLogUserAccessDAO() {
	
		return tLogUserAccessDAO;
	}


	public void settLogUserAccessDAO(TLogUserAccessDAO tLogUserAccessDAO) {
	
		this.tLogUserAccessDAO = tLogUserAccessDAO;
	}

	public void setTLogUserAccessDAO(TLogUserAccessDAO tLogUserAccessDAO) {
		
		this.tLogUserAccessDAO = tLogUserAccessDAO;
	}

}

