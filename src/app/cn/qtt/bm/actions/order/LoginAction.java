
 /*
 * 文 件 名:  LoginAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-6-26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.actions.order;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import app.cn.qtt.bm.common.utils.Base64;
import app.cn.qtt.bm.common.utils.MD5Encrypt;
import app.cn.qtt.bm.common.utils.TxUUIDGenerator;
import app.cn.qtt.bm.model.TSysUserInfo;


 /**       
 * 项目名称：BespeakMeal    
 * 类名称：LoginAction    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-6-26 下午5:10:20    
 * 修改人：linch   
 * 修改时间：2013-6-26 下午5:10:20    
 * 修改备注：    
 * @version       
 */
@ParentPackage("order-default")
@Namespace("/order")
@Results({
    @Result(name = Constants.HOME, type="chain", params={"actionName","shop-list"}),
    @Result(name=  Constants.LOGINLOCATION,type="redirect", location="${to}")
})
public class LoginAction extends UserLoginAction{

	
	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = 1009440363615864330L;
	
	//记住用户密码时，回传到页面的密码使用该值，提交登录时，在Action做转换
	private static final String REMEMBER_PASSWORD_MASK_CODE = "*********";
	
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
	 * 新密码
	 */

	private String newPassword;
	
	
	
	 /**
	 * 用户类型
	 */
	 
	private String userType;
	
	
	/**
	 * 页面跳转
	 */
	 
	private String to;
	
	
	/**
	 * 是否记住用户密码
	 */
	private String remember;

	public String execute(){
		
		final String xFunctionName  = "LoginAction.execute()";
		gLogger.begin(xFunctionName);
		
		try{
			//默认产生一个请求短信发送的令牌
			this.token = TxUUIDGenerator.getUUID();
			super.getSession().setAttribute(Constants.SESSION_LOGIN_TOKEN_NAME,
					this.token);
			
			Map<String, String> cookies = super.getCookies();
			if(StringUtils.isNotBlank(cookies.get("userPhoneNumber"))){
				userPhoneNumber = cookies.get("userPhoneNumber");
				userPassword = REMEMBER_PASSWORD_MASK_CODE; //cookies.get("userPassword"); //返回页面时，使用固定的字符串代替密码，而后在Action中做处理
				
				remember = "1"; //cookie中有值时，自动勾选
			}
		}
		catch (Exception e) {
			e.printStackTrace();
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

			UserBean userBean = null;

			if (StringUtils.isBlank(comeFrom)
					|| !Constants.COME_FROM_LOGIN.equals(comeFrom)) {
				userBean = super.getOrderUserInfoFromSession();
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
				
				//若用户记住密码，且并未修改过密码，则页面中的密码值与常量一致；
				//同时，cookie中的密码存在时，还原密码值(即不将密码传到前台页面)
				if(REMEMBER_PASSWORD_MASK_CODE.equals(userPassword) && StringUtils.isNotBlank(super.getCookie("userPassword"))){
					userPassword = decryptPassWord(super.getCookie("userPassword"), userPhoneNumber);
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
						userType , Constants.MODULE_TYPE_ORDER);

				if (userBean == null) {
					//用户登录失败的逻辑
					boolean result = userMgr.saveUserLoginFailCount(userPhoneNumber, userType);
					if(result){
						errorMessage = FAIL_LOGIN_PASS_RESET_NAME;
					}
					return Constants.LOGIN;
				}
				
				//登录成功后，根据勾选的状态，记住cookie
				if("1".equals(remember)){
			        super.addCookie("userPhoneNumber", userPhoneNumber);
			        super.addCookie("userPassword", encryptPassWord(userPassword, userPhoneNumber));
				}else{
			        super.addCookie("userPhoneNumber", "");
			        super.addCookie("userPassword", "");
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
			user.setUserType(Constants.USER_TYPE_CUSTOMER);

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
	
	

	

	
	/**
	* 方法名称:      updatePassword  
	* 方法描述:      修改密码
	* @return        
	* @Author:      huangwj
	* @Create Date: 2013-7-3 下午10:57:17
	*/ 
	@Action(value = "updatePasswordView", results = {
	    @Result(name = "updatePasswordView", location = "/WEB-INF/content/order/updatePassword.jsp")
	})
	public String updatePasswordView(){
		return "updatePasswordView";
	}
	
	/**
	* 方法名称:      updatePassword  
	* 方法描述:      修改密码
	* @return        
	* @Author:      huangwj
	* @Create Date: 2013-7-3 下午10:57:17
	*/ 
	@Action(value = "updatePassword", results = {
	    @Result(name = "updatePasswordView", location = "/WEB-INF/content/order/updatePassword.jsp")
	})
	public String updatePassword(){
		
		final String xFunctionName  = "LoginAction.updatePassword()";
		gLogger.begin(xFunctionName);
		
		try {
			
			UserBean ub = super.getOrderUserInfoFromSession();
			if(null == ub || null == ub.getUserInfo()){
				return LOGIN;
			}

			TSysUserInfo user = ub.getUserInfo();
			
			gLogger.debug("[token] 			: " + token);
			
			// token不为空
			if(StringUtils.isBlank(token)){
				errorMessage = TOKEN_EXCEPTION_NAME;
				return "updatePasswordView";
			}
			
			//用户密码不正确
			if(!MD5Encrypt.MD5Encode(userPassword).equals(user.getUserPassword())){
				errorMessage = USER_ORIGIN_PASSWORD_NAME + NOT_RIGHT;
				return "updatePasswordView";
			}
			user.setUserPassword(MD5Encrypt.MD5Encode(newPassword));

			//修改密码
			if(userMgr.updateUserPassword(user)){
				errorMessage = USER_PASSWORD_NAME + UPDATE_SUCCESS + RELOGIN;
				return LOGIN;
			}else{
				errorMessage = USER_PASSWORD_NAME + UPDATE_FAIL;
			}
			return "updatePasswordView";
		} 
		catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
		}
		catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			errorMessage = LOGIN_EXCEPTION_NAME;
		} finally {
			this.token = TxUUIDGenerator.getUUID();
			super.getSession().setAttribute(Constants.SESSION_LOGIN_TOKEN_NAME, this.token);
			gLogger.end(xFunctionName);
		}
		return "updatePasswordView";
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

			super.dologout(Constants.MODULE_TYPE_ORDER);

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
	 * 对密码进行简单加密
	 * @param pwd 明文密码
	 * @param maskCode 掩码
	 * @return base64(md5(maskCode) + base64(maskCode + 密码))
	 * @throws Exception
	 * @author Mr.Tipx
	 * @createtime 2013-12-12 下午2:25:01
	 */
	private static String encryptPassWord(String pwd, String maskCode) throws Exception{
		return Base64.encode(MD5Encrypt.MD5Encode(maskCode) + Base64.encode(maskCode + pwd));
	}
	
	/**
	 * 密文解码
	 * @param str 密文
	 * @param maskCode 掩码
	 * @return 明文密码
	 * @author Mr.Tipx
	 * @createtime 2013-12-12 下午2:26:09
	 */
	private static String decryptPassWord(String str, String maskCode){
		String ret = Base64.decode(str);
		ret = ret.replaceFirst(MD5Encrypt.MD5Encode(maskCode), "");
		ret = Base64.decode(ret).replaceFirst(maskCode, "");
		return ret;
	}
	
	//=====================================getter/setter============================
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


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getTo() {
	
		return to;
	}


	public void setTo(String to) {
	
		this.to = to;
	}


	public String getRemember() {
	
		return remember;
	}


	public void setRemember(String remember) {
	
		this.remember = remember;
	}
	
	
}