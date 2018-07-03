package app.cn.qtt.bm.common.base.service;

import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.service.pojo.ResponseBean;



 /**       
  * 项目名称：CmEnterpriseProject    
  * 类名称：BaseService    
  * 类描述：    
  * 创建人：linch  
  * 创建时间：2013-3-24 下午2:33:55    
  * 修改人：linch   
  * 修改时间：2013-3-24 下午2:33:55    
  * 修改备注：    
  * @version       
  */  
 
public class BaseService {

	protected CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());
	
	
	/**
	* 方法名称:      printErrorLogByResponse  
	* 方法描述:      打印错误信息
	* @param gLogger
	* @param response
	* @param xFunctionName        
	* @Author:      linch
	* @Create Date: 2012-5-17 上午11:17:11
	*/ 
	 
	protected void printErrorLogByResponse (CCrppLog4j gLogger ,ResponseBean response,String xFunctionName){
		if(response.getException() != null)
			gLogger.exception(xFunctionName, response.getException());
		gLogger.error("code : "+response.getResponseCode());
		gLogger.error("msg : "+response.getErrMsg());
		gLogger.error("msgDetail : "+response.getErrMsgDetail());
	}
}
