package app.cn.qtt.bm.manage;

import java.util.List;

import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;



 /**       
  * 项目名称：BespeakMeal    
  * 类名称：ISmsMgr    
  * 类描述：    短信
  * 创建人：linch  
  * 创建时间：2013-6-27 下午2:25:17    
  * 修改人：linch   
  * 修改时间：2013-6-27 下午2:25:17    
  * 修改备注：    
  * @version       
  */  
 
public interface ISmsMgr {

	
	

	
	
	/**
	* 方法名称:      saveSmsByTemplate  
	* 方法描述:      根据模版保存待发送短信
	* @param template			模版
	* @param params				替换参数
	* @param receiverMdn		接收方号码
	* @param type				类别
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-6-27 下午4:55:39
	*/ 
	 
	public void saveSmsByTemplate(String template, List<String> params ,String receiverMdn , String type)
			throws CMException, CMRollBackException;
}

