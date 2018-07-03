package app.cn.qtt.bm.actions.system;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import app.cn.qtt.bm.actions.UserLoginAction;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.TxUUIDGenerator;
import app.cn.qtt.bm.model.TSysUserInfo;


@Namespace("/system")
@ParentPackage("system-default")
@Results({
    @Result(name = Constants.HOME, type="chain", params={"actionName","home"}),
    @Result(name=  Constants.LOGINLOCATION,type="redirect", location="${to}"),
})
public class LoginAction extends UserLoginAction{

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = -2527802207455315829L;
	
	/**
	 * 登录手机号码
	 */

	private String userPhoneNumber;

	/**
	 * 登录密码
	 */

	private String userPassword;

	/**
	 * 短信验证码
	 */

	private String captcha;

	/**
	 * 来源
	 */

	private String comeFrom;

	/**
	 * 请求令牌
	 */

	private String token;
	
	
	
	 /**
	 * 用户类型
	 */
	 
	private String userType;
	
	
	 /**
	 * 页面跳转
	 */
	 
	private String to;
	
	
	
	public String execute(){
		
		final String xFunctionName  = "UserLoginAction.execute()";
		gLogger.begin(xFunctionName);
		
		try{
			//默认产生一个请求短信发送的令牌
			this.token = TxUUIDGenerator.getUUID();
			super.getSession().setAttribute(Constants.SESSION_LOGIN_TOKEN_NAME,
					this.token);
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
		return SUCCESS;
	}

	
	
	/**
	* 方法名称:      dologin  
	* 方法描述:      
	* @return        
	* @Author:      linch
	* @Create Date: 2013-6-21 上午10:57:17
	*/ 
	@Action(value = "dologin", results = {

	})
	public String dologin(){
		
		final String xFunctionName  = "UserLoginAction.dologin()";
		gLogger.begin(xFunctionName);
		try {

			gLogger.debug("[userPhoneNumber]: " + userPhoneNumber);
			gLogger.debug("[userPassword] 	: " + userPassword);
			gLogger.debug("[captcha] 		: " + captcha);
			gLogger.debug("[userType] 		: " + userType);
			gLogger.debug("[to] 			: " + to);

			UserBean userBean = null;

			if (StringUtils.isBlank(comeFrom)
					|| !Constants.COME_FROM_LOGIN.equals(comeFrom)) {
				userBean = super.getSystemUserInfoFromSession();
			}

			if (userBean == null) {
				// userCode不为空
				if (StringUtils.isBlank(userPhoneNumber)) {
					if(Constants.COME_FROM_LOGIN.equals(comeFrom)){
						errorMessage = USER_PHONE_NUMBER_NAME + IS_BLANK_NAME;
					}
					return Constants.LOGIN;
				}
				// userPassword不为空
				if (StringUtils.isBlank(userPassword)) {
					if(Constants.COME_FROM_LOGIN.equals(comeFrom)){
						errorMessage = USER_PASSWORD_NAME + IS_BLANK_NAME;
					}
					return Constants.LOGIN;
				}

				if (NEED_CHECK_CAPTCHA) {
					if (StringUtils.isBlank(captcha)) {
						if(Constants.COME_FROM_LOGIN.equals(comeFrom)){
							errorMessage = CAPTCHA_NAME + IS_BLANK_NAME;
						}
						return Constants.LOGIN;
					}
				}

				if (StringUtils.isBlank(userType)) {
					if(Constants.COME_FROM_LOGIN.equals(comeFrom)){
						errorMessage = USER_TYPE_NAME + IS_BLANK_NAME;
					}
					return Constants.LOGIN;
				}
				
				// 执行登录操作
				userBean = super.dologin(userPhoneNumber, userPassword, captcha,
						userType , Constants.MODULE_TYPE_SYSTEM);

				if (userBean == null) {
					//用户登录失败的逻辑
					boolean result = userMgr.saveUserLoginFailCount(userPhoneNumber, userType);
					if(result){
						errorMessage = FAIL_LOGIN_PASS_RESET_NAME;
					}
					return Constants.LOGIN;
				}
			}

		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
			return Constants.LOGIN;
		} finally {
			gLogger.end(xFunctionName);
		}

		if(StringUtils.isNotBlank(to)){
			try {
				to = URLDecoder.decode(to,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				gLogger.exception(xFunctionName, e);
				errorMessage = LOGIN_EXCEPTION_NAME;
				return Constants.LOGIN;
				
			}
			return Constants.LOGINLOCATION;
		}
		else{
			return Constants.HOME;
		}
		
	}
	
	
	
	/**
	 * 方法名称: doLogout 方法描述: 系统登出
	 * 
	 * @return
	 * @Author: linch
	 * @Create Date: 2013-4-7 下午4:19:06
	 */
	@Action(value = "doLogout", results = {

	})
	public String doLogout() {
		final String xFunctionName = "UserLoginAction.doLogout()";
		gLogger.begin(xFunctionName);
		try {

			super.dologout(Constants.MODULE_TYPE_SYSTEM);

		}  catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
			return Constants.LOGIN;
		} finally {
			gLogger.end(xFunctionName);
		}

		return Constants.LOGIN;
	}
	
	
	
	/**
	* 方法名称:      resetPassword  
	* 方法描述:      重置密码
	* @return        
	* @Author:      linch
	* @Create Date: 2013-6-21 上午10:57:17
	*/ 
	@Action(value = "resetPassword", results = {

	})
	public String resetPassword(){
		
		final String xFunctionName  = "LoginAction.resetPassword()";
		gLogger.begin(xFunctionName);
		Map<Object,Object> result =  new HashMap<Object,Object>();
		
		try {

			gLogger.debug("[userPhoneNumber]: " + userPhoneNumber);
			gLogger.debug("[token] 			: " + token);
			gLogger.debug("[userType] 		: " + userType);
			
			// token不为空
			if(StringUtils.isBlank(token)){
				errorMessage = TOKEN_EXCEPTION_NAME;
				fillAjaxError(result,errorMessage);
				return null;
			}
			
			// userCode不为空
			if (StringUtils.isBlank(userPhoneNumber)) {
				errorMessage = USER_PHONE_NUMBER_NAME + IS_BLANK_NAME;
				fillAjaxError(result,errorMessage);
				return null;
			}
			
			
			if (StringUtils.isBlank(userType)) {
				errorMessage = USER_TYPE_NAME + IS_BLANK_NAME;
				fillAjaxError(result,errorMessage);
				return null;
			}
			
			String sessionToken = (String) super.getSession().getAttribute(
					Constants.SESSION_LOGIN_TOKEN_NAME);
			//验证令牌是否一致
			if(StringUtils.isBlank(sessionToken) || !sessionToken.equals(token)){
				errorMessage = TOKEN_EXCEPTION_NAME;
				fillAjaxError(result,errorMessage);
				return null;
			}

			//根据手机号码获取用户信息
			TSysUserInfo user =  new TSysUserInfo();
			user.setUserPhoneNumber(userPhoneNumber);
			user.setUserStatus(Constants.EFFECTIVE_STATUS);
			user.setUserType(userType);

			List<TSysUserInfo> list = userMgr.queryUserByUserProperties(user);
			
			if(CollectionUtils.isEmpty(list)){
				errorMessage = USER_NONENTITY_NAME;
				fillAjaxError(result,errorMessage);
				return null;
			}
			
			user = list.get(0);
			
			UserBean userBean = new UserBean();
			userBean.setUserInfo(user);
			userBean.setUserCode(user.getUserCode());
			
			//执行重置新用户密码操作
			userMgr.saveResetConsumerPasswordToSms(userBean);
			
			fillAjaxSuccess(result);

		} 
		catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
			fillAjaxError(result,errorMessage);
		}
		catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
			fillAjaxError(result,errorMessage);
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
			fillAjaxError(result,errorMessage);
		} finally {
			this.token = TxUUIDGenerator.getUUID();
			result.put("token", token);
			super.getSession().setAttribute(Constants.SESSION_LOGIN_TOKEN_NAME,
					this.token);
			super.print(result);
			gLogger.end(xFunctionName);
		}

		
		return null;
	}
	
	
	
//	/**
//	* 方法名称:      systemSmsRequest  
//	* 方法描述:      
//	* @return        
//	* @Author:      linch
//	* @Create Date: 2013-4-5 下午12:05:30
//	*/ 
//	 
//	public String smsRequest(){
//		final String xFunctionName  = "systemSmsRequest";
//		gLogger.begin(xFunctionName);
//		
//		Map<Object,Object> result =  new HashMap<Object,Object>();
//		
//		try{
//			gLogger.debug("[userCode] 		: "+userCode);
//			gLogger.debug("[token] 			: "+token);
//			
//			if(StringUtils.isBlank(token)){
//				errorMessage = TOKEN_EXCEPTION_NAME;
//				fillAjaxError(result,errorMessage);
//				return null;
//			}
//			
//			if(StringUtils.isBlank(userCode)){
//				errorMessage = USER_CODE_NAME + IS_BLANK_NAME;
//				fillAjaxError(result,errorMessage);
//				return null;
//			}
//			
//			String sessionToken = (String) super.getSession().getAttribute(
//					Constants.SESSION_LOGIN_TOKEN_NAME);
//			//验证令牌是否一致
//			if(StringUtils.isBlank(sessionToken) || !sessionToken.equals(token)){
//				errorMessage = TOKEN_EXCEPTION_NAME;
//				fillAjaxError(result,errorMessage);
//				return null;
//			}
//			
//			super.getSession().removeAttribute(Constants.SESSION_LOGIN_TOKEN_NAME);
//			
//			TSysUserInfo tSysUserInfo =  new TSysUserInfo();
//			tSysUserInfo.setUserCode(userCode);
//			tSysUserInfo.setUserType(Constants.USER_SYSTEM_CODE_TYPE);
//			tSysUserInfo.setUserStatus(Constants.EFFECTIVE_STATUS);
//			
//			List<TSysUserInfo> users = userMgr.queryUserByUserProperties(tSysUserInfo);
//			
//			TSysUserInfo user = null;
//			
//			if(CollectionUtils.isNotEmpty(users)){
//				user = users.get(0);
//			}
//			
//			if(user == null){
//				errorMessage = USER_NONENTITY_NAME;
//				fillAjaxError(result,errorMessage);
//				return null;
//			}
//			
//			if(smsMgr.countCanSendSmsCaptcha(user.getUserPhoneNumber())){
//				TSmsCaptcha captcha = smsMgr.saveSmsVerificationCaptcha(
//						user.getUserPhoneNumber(), user.getUserCode(),Constants.CAPTCHA_LOGIN_TYPE);
//				
//				//待发送成功时
//				if(captcha != null){
//					fillAjaxSuccess(result);
//				}
//			}
//			else{
//				//请求发送超过乏值
//				errorMessage = CAPTCHA_MAX_SEND_NAME;
//				fillAjaxError(result,errorMessage);
//			}
//			
//			
//		}
//		catch (SystemException e) {
//			gLogger.exception(xFunctionName, e);
//			fillAjaxError(result,SYSTEM_ERROR_NAME);
//		}
//		catch (Exception e) {
//			gLogger.exception(xFunctionName, e);
//			fillAjaxError(result,SYSTEM_ERROR_NAME);
//		}
//		finally{
//			//短信请求令牌刷新
//			this.token = TxUUIDGenerator.getUUID();
//			result.put("token", token);
//			super.getSession().setAttribute(Constants.SESSION_LOGIN_TOKEN_NAME,
//					this.token);
//			super.print(result);
//			gLogger.end(xFunctionName);
//		}
//		
//		return null;
//	}
//	
	
	

	public String getToken() {
		
		return token;
	}


	public void setToken(String token) {
	
		this.token = token;
	}

	public String getUserPhoneNumber() {
	
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
	
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserPassword() {
	
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
	
		this.userPassword = userPassword;
	}

	public String getCaptcha() {
	
		return captcha;
	}

	public void setCaptcha(String captcha) {
	
		this.captcha = captcha;
	}

	public String getComeFrom() {
	
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
	
		this.comeFrom = comeFrom;
	}

	public String getUserType() {
	
		return userType;
	}

	public void setUserType(String userType) {
	
		this.userType = userType;
	}



	public String getTo() {
	
		return to;
	}



	public void setTo(String to) {
	
		this.to = to;
	}

	

}

