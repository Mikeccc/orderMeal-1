package app.cn.qtt.bm.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.ILogMgr;
import app.cn.qtt.bm.manage.IShopMgr;
import app.cn.qtt.bm.manage.ISystemCommonMgr;
import app.cn.qtt.bm.manage.IUserMgr;
import app.cn.qtt.bm.model.TLogUserLogin;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysMenuFuncInfo;
import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.model.TSysRoleInfo;
import app.cn.qtt.bm.model.TSysUserInfo;


public class UserLoginAction extends BaseAction{

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = -7781172552269123626L;
	
	
	protected static final String USER_CODE_NAME = "用户账号";
	
	protected static final String ENTERPRISE_CODE_NAME = "集团编号";
	
	protected static final String USER_PASSWORD_NAME = "密码";
	
	protected static final String USER_ORIGIN_PASSWORD_NAME = "原密码";
	
	protected static final String USER_PHONE_NUMBER_NAME = "手机号";
	
	protected static final String CAPTCHA_NAME = "验证码";
	
	protected static final String USER_TYPE_NAME = "用户类型";
	
	protected static final String IS_BLANK_NAME = "不能为空";
	
	protected static final String NOT_PASS_NAME = "验证不通过,请确认用户信息是否正确";
	
	protected static final String LOGIN_EXCEPTION_NAME = "验证时出现异常";
	
	protected static final String TOKEN_EXCEPTION_NAME = "页面超时或请求太频繁，请重新刷新该页面或稍后再试";
	
	protected static final String CAPTCHA_MAX_SEND_NAME = "您的验证码请求太过频繁，请稍后再试";
	
	protected static final String CAPTCHA_IS_NULL_NAME = "无有效验证码，请重新请求新短信验证码";
	
	protected static final String CAPTCHA_IS_ERROR_NAME = "验证码错误，请重新录入";
	
	protected static final String USER_NONENTITY_NAME = "没有该用户信息";
	
	protected static final String NOT_RIGHT = "不正确";
	
	protected static final String UPDATE_SUCCESS = "修改成功！";
	
	protected static final String UPDATE_FAIL = "修改失败！";
	
	protected static final String RELOGIN = "请重新登录！";
	
	protected static final String FAIL_LOGIN_PASS_RESET_NAME = "您连续输入密码错误的次数已达3次，密码失效，请重新获取密码";
	
	
	
	 /**
	 * 是否要验证短信
	 */
	 
	protected static boolean NEED_CHECK_CAPTCHA = false;
	
	
	protected IUserMgr userMgr;
	
	protected ISystemCommonMgr systemCommonMgr;
	
	
	protected ILogMgr logMgr;
	
	
	protected IShopMgr shopMgr; 
	
	
	
	
	/**
	* 方法名称:      dologin  
	* 方法描述:      执行登录操作
	* @param userCode
	* @param userPassword
	* @param enterprisCode
	* @param captcha
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-3 上午12:11:27
	*/ 
	 
	protected UserBean dologin(String userPhoneNumber, String userPassword,
			String captcha, String userType, String moduleType) {
		final String xFunctionName  = "dologin";
		gLogger.begin(xFunctionName);
		
		UserBean userBean = null;
		try {
			TSysUserInfo userInfo = null;
			
			if(StringUtils.isBlank(userType)){
				gLogger.error(">>>>> userType is null <<<<<");
				throw CMException.sys("userType is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userType");
			}
			
			if(StringUtils.isBlank(moduleType)){
				gLogger.error(">>>>> moduleType is null <<<<<");
				throw CMException.sys("moduleType is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "moduleType");
			}
			
			try {
				userInfo = userMgr.queryUserLogin(userPhoneNumber, userPassword,userType);
			} catch (CMException e) {
				gLogger.exception(xFunctionName, e);
				gLogger.error(">>> user login error <<<<<");
				errorMessage = NOT_PASS_NAME;
				return null;
			}
			//用户信息为空的情况
			if(userInfo == null){
				gLogger.error(">>>>> user info is null <<<<<");
				errorMessage = NOT_PASS_NAME;
				return null;
			}
			else{
				//用户类别
				String thisUserType = userInfo.getUserType();
				
				if(StringUtils.isBlank(thisUserType) ||(StringUtils.isNotBlank(userType) && !thisUserType.equals(userType)) ){
					gLogger.error(">>>>> user type is error; type is :"+thisUserType+" <<<<<");
					errorMessage = NOT_PASS_NAME;
					return null;
				}
				//短信验证码
				if(NEED_CHECK_CAPTCHA){
//					TSmsCaptcha responseCaptcha = smsMgr.queryEffectiveCaptchaValidation(userInfo.getUserCode(),Constants.CAPTCHA_LOGIN_TYPE);
//					
//					if(responseCaptcha == null){
//						gLogger.error(">>>>> responseCaptcha is null <<<<<");
//						errorMessage = CAPTCHA_IS_NULL_NAME;
//						return null;
//					}
//					
//					if(!captcha.equals(responseCaptcha.getCaptchaValidationContent())){
//						gLogger.error(">>>>> captcha is error <<<<<");
//						errorMessage = CAPTCHA_IS_ERROR_NAME;
//						return null;
//					}
					
					
				}
				
				gLogger.info(">>>>> user verify is pass!  <<<<<");
				
				
				userBean = new UserBean();
				
				userBean.setUserCode(userInfo.getUserCode());	//填充用户id
				userBean.setUserType(userType);				//用户类型
				userBean.setUserInfo(userInfo);				//用户基本信息
				
				
				//获取用户角色
				List<TSysRoleInfo> roles = systemCommonMgr.queryRolesByUserCode(userInfo.getUserCode());
				userBean.setRoles(roles);
				
				//获取店铺信息
				if(Constants.USER_TYPE_SHOP.equals(userType)){
					TShopInfo shopInfo = shopMgr.queryShopInfoById(userBean.getUserInfo().getUserShopId());
					userBean.setShopInfo(shopInfo);
				}
				
	//			if (CollectionUtils.isNotEmpty(userBean.getRoles())
	//					&& StringUtils.isNotBlank(userBean.getRoles().get(0)
	//							.getRoleCode())) {
	//				//查询用户菜单
	//				Map<TSysMenuInfo, List<TSysMenuFuncInfo>> menus = systemCommonMgr
	//						.queryMenuByRoleCode(userBean.getRoles().get(0)
	//								.getRoleCode());
	//				//填充菜单信息
	//				systemCommonMgr.findMenusVisitPathsIntoEnterpriseUserBean(userBean, menus);
	//			}
				
				//查询用户菜单
				Map<TSysMenuInfo, List<TSysMenuFuncInfo>> menus = systemCommonMgr
						.queryMenuByUserCode(userInfo.getUserCode());
				
				if(!menus.isEmpty()){
					//填充菜单信息
					systemCommonMgr.findMenusVisitPathsIntoEnterpriseUserBean(
							userBean, menus);
				}
				
				//保存用户登录日志
				if(userBean != null){
					HttpServletRequest request = super.getRequest();
					
					String ip = request.getLocalAddr();
					String sessionId = request.getSession().getId();
					
					TLogUserLogin userLoginLog =logMgr.saveUserLoginLog(userBean, ip, sessionId);
					
					//用户登录日志保存session
					if(userLoginLog != null){
						//后台系统日志
						if(moduleType.equals(Constants.MODULE_TYPE_SYSTEM)){
							super.getSession().setAttribute(Constants.SESSION_SYSTEM_USER_LOGIN_LOG_NAME, userLoginLog);
						}
						//发送系统日志
						if(moduleType.equals(Constants.MODULE_TYPE_ORDER)){
							super.getSession().setAttribute(Constants.SESSION_ORDER_USER_LOGIN_LOG_NAME, userLoginLog);
						}
						
					}
					
				}
				
				//把用户信息放在session
				if(moduleType.equals(Constants.MODULE_TYPE_SYSTEM)){
					super.getSession().setAttribute(Constants.SESSION_SYSTEM_USER_NAME, userBean);
				}
				if(moduleType.equals(Constants.MODULE_TYPE_ORDER)){
					super.getSession().setAttribute(Constants.SESSION_ORDER_USER_NAME, userBean);
				}
				
			}
			
			
			
			
		}
		catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
			userBean = null;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
			userBean = null;
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
		return userBean;
	}

	
	
	
	/**
	* 方法名称:      dologout  
	* 方法描述:      退出操作        
	* @Author:      linch
	* @Create Date: 2013-4-7 下午4:16:49
	*/ 
	 
	protected void dologout(String moduleType) {
		final String xFunctionName  = "dologout";
		gLogger.begin(xFunctionName);
		
		try {
			if(StringUtils.isBlank(moduleType)){
				gLogger.error(">>>>> moduleType is null <<<<<");
				throw CMException.sys("moduleType is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "moduleType");
			}
			//获取用户信息
			UserBean userBean = null;
			if(moduleType.equals(Constants.MODULE_TYPE_SYSTEM)){
				userBean = super.getSystemUserInfoFromSession();
			}
			if(moduleType.equals(Constants.MODULE_TYPE_ORDER)){
				userBean = super.getOrderUserInfoFromSession();
			}
			//清除用户信息
			if(userBean != null){
				gLogger.info(">>>>> userCode : "+userBean.getUserCode()+" , do logout");
				if(moduleType.equals(Constants.MODULE_TYPE_SYSTEM)){
					super.getSession().removeAttribute(Constants.SESSION_SYSTEM_USER_NAME);
				}
				if(moduleType.equals(Constants.MODULE_TYPE_ORDER)){
					super.getSession().removeAttribute(Constants.SESSION_ORDER_USER_NAME);
				}
				
			}
			
			TLogUserLogin userLoginLog = null;
			if(moduleType.equals(Constants.MODULE_TYPE_SYSTEM)){
				userLoginLog = super.getSystemUserLoginLogFromSession();
			}
			if(moduleType.equals(Constants.MODULE_TYPE_ORDER)){
				userLoginLog = super.getOrderUserLoginLogFromSession();
			}
			
				
			if(userLoginLog != null){
				if(moduleType.equals(Constants.MODULE_TYPE_SYSTEM)){
					super.getSession().removeAttribute(Constants.SESSION_SYSTEM_USER_LOGIN_LOG_NAME);
				}
				if(moduleType.equals(Constants.MODULE_TYPE_ORDER)){
					super.getSession().removeAttribute(Constants.SESSION_ORDER_USER_LOGIN_LOG_NAME);
				}
			}	
			
			
		}
		catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
	}

	public IUserMgr getUserMgr() {
	
		return userMgr;
	}


	public void setUserMgr(IUserMgr userMgr) {
	
		this.userMgr = userMgr;
	}


	public ISystemCommonMgr getSystemCommonMgr() {
	
		return systemCommonMgr;
	}


	public void setSystemCommonMgr(ISystemCommonMgr systemCommonMgr) {
	
		this.systemCommonMgr = systemCommonMgr;
	}


	public ILogMgr getLogMgr() {
	
		return logMgr;
	}


	public void setLogMgr(ILogMgr logMgr) {
	
		this.logMgr = logMgr;
	}




	public IShopMgr getShopMgr() {
		return shopMgr;
	}




	public void setShopMgr(IShopMgr shopMgr) {
		this.shopMgr = shopMgr;
	}

	
	

}

