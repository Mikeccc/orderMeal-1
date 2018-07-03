/*
 * 文 件 名:  PasswordOverTimeQuartzJobImpl.java
 * 版    权:  QTT Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Gabriel
 * 修改时间:  2013-8-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package app.cn.qtt.bm.scheduler.impl;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.scheduler.IPasswordOverTimeQuartzJobBean;
import app.cn.qtt.bm.service.IUserService;
import app.cn.qtt.bm.service.pojo.UserResponseBean;

/**
 * 密码失效实现类
 * @project BespeakMeal
 * @author Gabriel
 * @createtime 2013-8-24 下午9:19:53
 * @modify_description 
 * @mender  
 * @modifytime 
 * @version 1.0
 * @since JDK 1.6
 */
public class PasswordOverTimeQuartzJobImpl implements IPasswordOverTimeQuartzJobBean {
	private CCrppLog4j gLogger = new CCrppLog4j(OverTimeOrderQuartzJobBean.class.getName());
	
	private IUserService userService;
	
	/**
	 * @see app.cn.qtt.bm.scheduler.IPasswordOverTimeQuartzJobBean#startJob()
	 * @author Gabriel
	 * @createtime 2013-8-24 下午9:19:53
	 */
	@Override
	public void startJob() {
		try{
			gLogger.begin("startJob");
			this.processJob();
		}catch(Exception e){
			gLogger.exception("startJob", e);
		}finally{
			gLogger.info("#######job 结束#######");
		}
		
	}

	/**
	 * 处理job内容
	 * @throws Exception
	 * @author Gabriel
	 * @createtime 2013-8-24 下午9:28:00
	 */
	private void processJob() throws Exception {
		gLogger.info("'15天未修改过密码进行失效处理'方法开始");
		UserResponseBean responseBean = userService.passwordOverTime();
		if(responseBean.getResponseCode().equals(Constants.UPDATE_FAIL_CODE)){
			gLogger.error("密码失效操作出现异常:" + responseBean.getErrMsg());
			throw responseBean.getException();
		}else if(responseBean.getDbReturnValue() == 0){
			gLogger.info("本次job没有需要进行密码失效的用户");
		}else{
			gLogger.info("本次失效了"+responseBean.getDbReturnValue()+"户的密码。");
		}
	}

	/**
	 * @return 返回 userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param 对userService进行赋值
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
