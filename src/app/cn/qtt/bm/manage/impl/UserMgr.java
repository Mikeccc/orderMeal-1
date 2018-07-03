package app.cn.qtt.bm.manage.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.Assert;

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.DateUtil;
import app.cn.qtt.bm.common.utils.MD5Encrypt;
import app.cn.qtt.bm.manage.ISmsMgr;
import app.cn.qtt.bm.manage.IUserMgr;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.TSysLinkUserRole;
import app.cn.qtt.bm.model.TSysLinkUserRoleExample;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.model.TSysUserInfoExample;
import app.cn.qtt.bm.module.password.LoginPasswordManage;
import app.cn.qtt.bm.service.IOrderService;
import app.cn.qtt.bm.service.IUserService;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;


public class UserMgr extends BaseService implements IUserMgr {
	
	
	private IUserService userService;
	
	private ISmsMgr smsMgr;
	
	private SystemCommonMgr systemCommonMgr;
	
	private IOrderService orderService; 
	
	




	/**
	* 方法名称:      queryUserLogin  
	* 方法描述:      登录逻辑
	* @param userCode		登录名	
	* @param userPassword	密码
	* @return				用户信息
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-4-1 下午7:33:32
	*/ 
	 
	public TSysUserInfo queryUserLogin(String userCode, String userPassword)
			throws CMException,CMRollBackException {
		return queryUserLogin(userCode, userPassword, null);
	}
	
	
	
	/**
	* 方法名称:      queryUserLogin  
	* 方法描述:      登录逻辑
	* @param userCode 		登录名
	* @param userPassword	密码
	* @param userType		用户类型
	* @return				用户信息
	* @throws CMException 
	* @throws CMRollBackException 
	* @Author:      linch
	* @Create Date: 2013-4-1 下午7:32:29
	*/ 
	 
	public TSysUserInfo queryUserLogin(String userPhoneNumber, String userPassword,
			String userType) throws CMException,CMRollBackException  {
		final String xFunctionName = "queryUserLogin";
		gLogger.begin(xFunctionName);
		//userCode 不为空
		try {
			Assert.notNull(userPhoneNumber);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userPhoneNumber is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userPhoneNumber");
		}
		
		//userCode 不为空
		try {
			Assert.notNull(userPassword);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userPassword is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userPassword");
		}
		
		try{
			
			
			//MD5加密
			String passwordMD5 = MD5Encrypt.MD5Encode(userPassword);
			
			UserRequestBean requestBean = new UserRequestBean();
			
			TSysUserInfo user = new TSysUserInfo();
			//设置用户userPhoneNumber
			user.setUserPhoneNumber(userPhoneNumber);
			//设置用户md5后的用户密码
			user.setUserPassword(passwordMD5);
			user.setUserType(userType);
			requestBean.setSysuserinfo(user);
			user.setUserStatus(Constants.EFFECTIVE_STATUS);
			
			UserResponseBean responseBean = userService
					.queryForUserByUserProperties(requestBean);
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			else{
				List<?> list = responseBean.getUserlist();
				//不为空的时候访问true;
				if(CollectionUtils.isNotEmpty(list) && list.get(0) != null){
					
					if(list.get(0) instanceof TSysUserInfo){
						return (TSysUserInfo)list.get(0);
					}
					
				}

				return null;
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
	
	
	
	
	/**
	* 方法名称:      queryUserByUserProperties  
	* 方法描述:      根据用户信息查询用户列表
	* @param user
	* @return
	* @throws CMException
	* @throws CMRollBackException       
	* @Author:      linch
	* @Create Date: 2013-4-4 下午9:12:54
	*/ 
	 
	@SuppressWarnings("unchecked")
	public List<TSysUserInfo> queryUserByUserProperties(TSysUserInfo user)
			throws CMException, CMRollBackException {
		final String xFunctionName = "queryUserByUserProperties";
		gLogger.begin(xFunctionName);
		List<TSysUserInfo> users= new ArrayList<TSysUserInfo>();
		
		try {
			Assert.notNull(user);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("user is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "user");
		}
		
		try {
			
			UserRequestBean requestBean = new UserRequestBean();
			requestBean.setSysuserinfo(user);

			UserResponseBean responseBean = userService
					.queryForUserByUserProperties(requestBean);
			
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			else{
				users = responseBean.getUserlist();
			}
			
			
		} catch (CMRollBackException e) {
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
		
		return users;
	}
	
	
	 
	
	
	/**
	* 方法名称:      saveUserLoginFailCount  
	* 方法描述:      保存用户登录失败的逻辑
	* @param userPhoneNumber
	* @param userType
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-7-19 下午2:46:47
	*/ 
	 
	public boolean saveUserLoginFailCount(String userPhoneNumber , String userType) throws CMException, CMRollBackException{
		
		final String xFunctionName = "saveUserLoginFailCount";
		gLogger.begin(xFunctionName);
		boolean result = false;
		
		try {
			Assert.notNull(userPhoneNumber);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userPhoneNumber is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userPhoneNumber");
		}
		
		try {
			Assert.notNull(userType);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userPhoneNumber is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userType");
		}
		
		try{
			
			TSysUserInfo user = new TSysUserInfo();
			user.setUserPhoneNumber(userPhoneNumber);
			user.setUserStatus(Constants.EFFECTIVE_STATUS);
			user.setUserType(userType);
			//获取有效用户
			List<TSysUserInfo> users = queryUserByUserProperties(user);
			
			if(!CollectionUtils.isEmpty(users)){
				user = users.get(0);
			}
			
			if(null != user && StringUtils.isNotBlank(user.getUserCode())){
				LoginPasswordManage manage = LoginPasswordManage.instance();
				Integer count = manage.getUserLoginFailNum(userPhoneNumber);
				Integer maxNum = NumberUtils.toInt(CacheConstants.MAX_FAIL_LOGIN_PASSWORD_RESET_NUM(), 0);
				
				//更新用户失败条数
				manage.addUserLoginFailNum(userPhoneNumber);
				
				//失败数大于乏值则执行密码重置的操作
				if(maxNum > 0 && count + 1 >= maxNum){
					//获取新的重置密码
					int temp =  new Random().nextInt(1000000);
					String password = String.valueOf(temp);
					StringBuffer buffer = new StringBuffer();
					for(int i = 0;i < 6 - password.length() ; i++){
						//补齐0
						buffer.append("0");
					}
					buffer.append(password);
					password = buffer.toString();
					
					//密码无需MD5加密保存，表示该用户无法进行正常登录，只能在登录页面进行手动密码重置
					user.setUserPassword(password);
					
					//更新用户信息
					UserRequestBean userRequestBean = new UserRequestBean();
					userRequestBean.setSysuserinfo(user);
					UserResponseBean responseBean = userService.updateUser(userRequestBean);
					
					if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
						super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
						throw CMRollBackException.sys(responseBean.getErrMsg(),
								ExceptionConstants.SERVICE_CODE, null);
					}
					
					//情况该手机用户的失败次数
					manage.clearUserLoginFailNum(userPhoneNumber);
					result = true;
				}
				
				
			}
			
			
			
		}catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
			
		}
		return result;
	}
	
	
	
	/**
	 * 
	* @Title: findUserByUserCode
	* @Description: 通过用户code查找用户
	* @param @param userCode
	* @param @return    设定文件
	* @return TSysUserInfo    返回类型
	* @throws
	 */
	public TSysUserInfo findUserByUserCode(String userCode)throws CMException, CMRollBackException{
		final String xFunctionName = "findUserByUserCode";
		gLogger.begin(xFunctionName);
		TSysUserInfo tSysUserInfo =null;
		
		try {
			Assert.notNull(userCode);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userCode is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userCode");
		}
		
		try{
			if(StringUtils.isNotBlank(userCode)){
				UserResponseBean response = new UserResponseBean();
				UserRequestBean request = new UserRequestBean();
				TSysUserInfo sysuserinfo = new TSysUserInfo();
				sysuserinfo.setUserCode(userCode);
				request.setSysuserinfo(sysuserinfo);
				response = userService.queryForUserByUserProperties(request);
				if(response!=null && response.getUserlist()!=null && response.getUserlist().size() > 0){
					tSysUserInfo=(TSysUserInfo) response.getUserlist().get(0);
				}
				if(!Constants.SELECT_SUCCESS_CODE.equals(response.getResponseCode())){
					super.printErrorLogByResponse(gLogger, response,
							xFunctionName);
					throw CMRollBackException.sys(response.getErrMsg(),
							ExceptionConstants.SERVICE_CODE, null);
				}
			}
		}catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
			
		}
		return tSysUserInfo;
	}
	
	
	
	/**
	 * 
	* @Title: logOffUser
	* @Description: 注销用户
	* @param @param adminId
	* @param @return    设定文件
	* @return Boolean    返回类型
	* @throws
	 */
	public Boolean delUser(String currentUserCode,String adminId) throws CMException, CMRollBackException{
		String xFunctionName  = "delUser";
		gLogger.begin(xFunctionName);
		Boolean flag = null;
		try{
			UserResponseBean response = new UserResponseBean();
			UserRequestBean request = new UserRequestBean();
			TSysUserInfo sysuserinfo = new TSysUserInfo();
			sysuserinfo.setUserCode(adminId);
			sysuserinfo.setUserStatus(Constants.NO_EFFECTIVE_STATUS);//状态位为失效
			request.setSysuserinfo(sysuserinfo);
			request.setCurrentUserCode(currentUserCode);
			response = userService.forbidUser(request);
			if(response.getResponseCode().equals(Constants.UPDATE_SUCCESS_CODE)){
				flag=true;
			}else{
				flag =false;
				super.printErrorLogByResponse(gLogger, response,
						xFunctionName);
				throw CMRollBackException.sys(response.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			
		}catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
			return flag;
	}
	
	
	
	
	
	/**
	* 方法名称:      saveResetConsumerPasswordToSms  
	* 方法描述:      根据用户重置密码
	* @param userBean
	* @throws CMException
	* @throws CMRollBackException        
	* @Author:      linch
	* @Create Date: 2013-6-27 下午7:31:25
	*/ 
	 
	public void saveResetConsumerPasswordToSms(UserBean userBean)
			throws CMException, CMRollBackException {
	
		String xFunctionName  = "saveConsumerPasswordToSms";
		gLogger.begin(xFunctionName);
		
		try {
			Assert.notNull(userBean);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userBean is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userBean");
		}
		
		try {
			Assert.notNull(userBean.getUserCode());
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userCode is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userCode");
		}
		
		try {
			Assert.notNull(userBean.getUserInfo());
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userCode is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userInfo");
		}
		
		//模版
		String template = CacheConstants.CONSUMER_LOGIN_PASSWORD_TEMPLATE();
		
		try {
			Assert.notNull(template);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("template is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "template");
		}
		
		try{
			
			List<String> params = new ArrayList<String>(1);
			
			//密码默认6位随机值，不满6位前面补0
			int temp =  new Random().nextInt(1000000);
			String password = String.valueOf(temp);
			StringBuffer buffer = new StringBuffer();
			for(int i = 0;i < 6 - password.length() ; i++){
				//补齐0
				buffer.append("0");
			}
			buffer.append(password);
			password = buffer.toString();
			params.add(password);
			
			String passwordMD5 = MD5Encrypt.MD5Encode(password);
			
			smsMgr.saveSmsByTemplate(template, params, userBean.getUserInfo()
					.getUserPhoneNumber(), Constants.SMS_TYPE_CONSUMER_PASSWORD);
			
			TSysUserInfo userInfo = userBean.getUserInfo();
			userInfo.setUserPassword(passwordMD5);
			
			//更新用户信息
			UserRequestBean userRequestBean = new UserRequestBean();
			userRequestBean.setSysuserinfo(userInfo);
			UserResponseBean responseBean = userService.updateUser(userRequestBean);
			
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			
			
		}catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
	}
	
	
	/**
	 * 
	* @Title: findCheckByUserTelAndType
	* @Description: 检查用户电话是否已经存在
	* @param @param userTel
	* @param @param userType
	* @param @return    设定文件
	* @return Boolen    返回类型
	* @throws
	 */
	public Boolean findCheckByUserTelAndType(String userTel,String userType) throws CMException, CMRollBackException{
		String xFunctionName  = "checkUserTel";
		gLogger.begin(xFunctionName);
		Boolean flag = null;
		
		try {
			Assert.notNull(userTel);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userTel is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userTel");
		}
		
		try {
			Assert.notNull(userType);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userType is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userType");
		}
		
		try{
			try {
				Assert.notNull(userTel);
			} catch (IllegalArgumentException e) {
				gLogger.end(xFunctionName);
				throw CMRollBackException.sys("userTel is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userTel");
			}
			UserRequestBean request = new UserRequestBean();
			TSysUserInfo sysuserinfo = new TSysUserInfo();
			
			sysuserinfo.setUserType(userType);
			sysuserinfo.setUserPhoneNumber(userTel);
			sysuserinfo.setUserStatus(Constants.EFFECTIVE_STATUS);
			request.setSysuserinfo(sysuserinfo);
			UserResponseBean response = userService.queryForUserByUserProperties(request);
			
			if(Constants.SELECT_SUCCESS_CODE.equals(response.getResponseCode())){
				if(response!=null&&response.getUserlist()!=null){
					if(response.getUserlist().size()>0){
						flag = true;
					}else{
						flag = false;
					}
				}else{
					flag = false;
				}
			}else{
				super.printErrorLogByResponse(gLogger, response,
						xFunctionName);
				throw CMRollBackException.sys(response.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			
			
		}catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return flag;
	}


	/**
	 * 查询有效订餐人员
	 * @param user 查询条件
	 * @param currentPage 当前页
	 * @param pageRecords 页大小(每页记录数)
	 * @return 分页数据
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	@Override
	public UserResponseBean queryValidOrderer(TSysUserInfo user, int currentPage, int pageRecords) throws CMException, CMRollBackException{
		final String xFunctionName = "queryValidOrderer";
		gLogger.begin(xFunctionName);

		if(null == user){
			user = new TSysUserInfo();
		}
		
		//查询的用户类型为订餐人员
		user.setUserType(Constants.USER_TYPE_CUSTOMER);
		user.setUserStatus(Constants.USER_STATUS_01);

		UserResponseBean responseBean = queryOrderer(user, currentPage, pageRecords);

		gLogger.end(xFunctionName);
		return responseBean;
	}


	/**
	 * 查询违规订餐人员
	 * @param user 查询条件
	 * @param currentPage 当前页
	 * @param pageRecords 页大小(每页记录数)
	 * @return 分页数据
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	@Override
	public UserResponseBean queryInvalidOrderer(String searchDate, int currentPage, int pageRecords) throws CMException, CMRollBackException{

		final String xFunctionName = "queryInvalidOrderer";
		gLogger.begin(xFunctionName);

		UserResponseBean responseBean = null;
		try {
			UserRequestBean requestBean = new UserRequestBean();
			
			TSysUserInfo user = new TSysUserInfo();
			
			//查询的用户类型为订餐人员
			user.setUserType(Constants.USER_TYPE_CUSTOMER);
			user.setUserStatus(Constants.USER_STATUS_01);
			
			requestBean.setSysuserinfo(user);
			requestBean.setCurrentPage(currentPage);
			requestBean.setPageRecords(pageRecords);
			
			//查询日期
			if(StringUtils.isBlank(searchDate)){
				searchDate = DateUtil.format(new Date(), "yyyy-MM-dd") ;
			}
			//判断若查询日期为当天，且系统当前时间还没到达统计结束时间，则将时间置为2099-12-31，确保统计结束前，不会出现违规人员
			searchDate = filterDate(searchDate);
			
			requestBean.setDateTime(searchDate);
	
			responseBean = userService.queryIllegalUser(requestBean);

			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(), ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return responseBean;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<TSysUserInfo> queryInvalidOrderer(String searchDate) throws CMException{

		final String xFunctionName = "queryInvalidOrderer";
		gLogger.begin(xFunctionName);

		List<TSysUserInfo> list = null;
		try {
			UserRequestBean requestBean = new UserRequestBean();
			
			TSysUserInfo user = new TSysUserInfo();
			
			//查询的用户类型为订餐人员
			user.setUserType(Constants.USER_TYPE_CUSTOMER);
			user.setUserStatus(Constants.USER_STATUS_01);
			
			//查询日期
			if(StringUtils.isBlank(searchDate)){
				searchDate = DateUtil.format(new Date(), "yyyy-MM-dd") ;
			}
			//判断若查询日期为当天，且系统当前时间还没到达统计结束时间，则将时间置为2099-12-31，确保统计结束前，不会出现违规人员
			searchDate = filterDate(searchDate);
			
			requestBean.setDateTime(searchDate);
	
			UserResponseBean responseBean = userService.queryIllegalUser(searchDate);

			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			list = (List<TSysUserInfo>)responseBean.getResultList();
			
		}catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			throw CMException.sys(e.getMessage(), ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return list;
	}
	
	/**
	 * 判断若查询日期为当天，
	 * 且系统当前时间还没到达统计结束时间，
	 * 则将时间置为2099-12-31，确保统计结束前，不会出现违规人员
	 * @param searchDate 查询日期
	 * @return 过滤后的查询日期
	 * @author tipx
	 * @createtime 2013-8-23 上午11:18:09
	 */
	private String filterDate(String searchDate){
		final String xFunctionName = "filterDate";
		gLogger.begin(xFunctionName);
		
		String result = searchDate;
		try {
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			
			String nowStr = formater.format(now);
			
			Date countEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowStr + " " + CacheConstants.ORDER_COUNT_END_TIME());

			//判断若查询日期为当天，且系统当前时间还没到达统计结束时间，则将时间置为2099-12-31，确保统计结束前，不会出现违规人员
			if(nowStr.equals(searchDate) && now.before(countEndTime)){
				result = "2099-12-31";
			}

		} catch (ParseException e) {
			gLogger.exception(xFunctionName, e);
			//异常时，使用最大日期
			result = "2099-12-31";
		} finally{
			gLogger.end(xFunctionName);
		}
		return result;
	}

	/**
	 * 添加订餐人员
	 * @param user 用户信息
	 * @return 
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	@Override
	public UserResponseBean addOrderer(TSysUserInfo user) throws CMException, CMRollBackException {
		final String xFunctionName = "addOrderer";
		gLogger.begin(xFunctionName);
		
		if(null == user){
			throw CMException.sys("待添加的用户信息不存在！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "user");
		}
		
		UserResponseBean responseBean = null;
		try {
			UserRequestBean requestBean = new UserRequestBean();
			requestBean.setSysuserinfo(user);
			
			//自动生成userCode
			String userCode = systemCommonMgr.saveAndQueryUserCodeByType(Constants.USER_TYPE_CUSTOMER);
			user.setUserCode(userCode);
			
			//验证同一类型的用户，手机必须唯一
			TSysUserInfo tmpUser = new TSysUserInfo();
			tmpUser.setUserPhoneNumber(user.getUserPhoneNumber());
			tmpUser.setUserType(Constants.USER_TYPE_CUSTOMER);
			tmpUser.setUserStatus(Constants.USER_STATUS_01); //只查有效的用户手机
			
			List<TSysUserInfo> tmpList = queryUserByUserProperties(tmpUser);
			if(null != tmpList && tmpList.size() > 0){
				responseBean = new UserResponseBean();
				responseBean.setResponseCode(ExceptionConstants.ILLEGAL_ARGUMENT_CODE+"");
				responseBean.setErrMsg("手机号码["+ user.getUserPhoneNumber() +"]已存在！");
				return responseBean;
			}
			
			//强制设置用户类型
			user.setUserType(Constants.USER_TYPE_CUSTOMER);
			//初始设置有效
			user.setUserStatus(Constants.USER_STATUS_01);
			//初始设置登录状态
			user.setIsOnline(Constants.IS_ONLINE_FALSE);
			//初始密码
			user.setUserPassword(Constants.ORIGINAL_PASSWORD);
	
			responseBean = userService.addUserInfo(requestBean);

			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
			//为用户设置默认角色
			responseBean = addLinkRole(user.getUserCode(), Constants.DEFAULT_ROLE_ID_CONSUMER, user.getCreateUserCode());
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(), ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return responseBean;
	}


	/**
	 * 修改订餐人员
	 * @param user 用户信息
	 * @return 
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	@Override
	public UserResponseBean updateOrderer(TSysUserInfo user) throws CMException, CMRollBackException {
		final String xFunctionName = "updateOrderer";
		gLogger.begin(xFunctionName);
		
		if(null == user){
			throw CMException.sys("待修改的用户信息不存在！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "user");
		}
		
		UserResponseBean responseBean = null;
		try {
			
			//验证同一类型的用户，手机必须唯一
			TSysUserInfo tmpUser = new TSysUserInfo();
			tmpUser.setUserPhoneNumber(user.getUserPhoneNumber());
			tmpUser.setUserType(Constants.USER_TYPE_CUSTOMER);
			tmpUser.setUserStatus(Constants.USER_STATUS_01); //只查有效的用户手机
			
			List<TSysUserInfo> tmpList = queryUserByUserProperties(tmpUser);
			//需要额外判断，手机号码重复的用户不是当前待修改的用户
			if(null != tmpList && tmpList.size() > 0 && (tmpList.get(0).getUserId().intValue() !=  user.getUserId().intValue())){
				responseBean = new UserResponseBean();
				responseBean.setResponseCode(ExceptionConstants.ILLEGAL_ARGUMENT_CODE+"");
				responseBean.setErrMsg("手机号码["+ user.getUserPhoneNumber() +"]已存在！");
				return responseBean;
			}
			
			tmpUser = findUserByUserCode(user.getUserCode());
			//判断订餐类型下的用户是否存在
			if(null == tmpUser || !Constants.USER_TYPE_CUSTOMER.equals(tmpUser.getUserType())){
				responseBean = new UserResponseBean();
				responseBean.setResponseCode(ExceptionConstants.ILLEGAL_ARGUMENT_CODE+"");
				responseBean.setErrMsg("用户["+ user.getUserCode() +"]未找到！");
				return responseBean;
			}
			
			tmpUser.setUserName(user.getUserName());
			tmpUser.setUserPhoneNumber(user.getUserPhoneNumber());
			tmpUser.setUserEmail(user.getUserEmail());
			tmpUser.setDepartment(user.getDepartment());
			tmpUser.setModifyTime(user.getModifyTime());
			tmpUser.setModifyUserCode(user.getModifyUserCode());

			//强制设置用户类型
//			user.setUserType(Constants.USER_TYPE_CUSTOMER);

			UserRequestBean requestBean = new UserRequestBean();
			requestBean.setSysuserinfo(tmpUser);
			
			responseBean = userService.updateUser(requestBean);

			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(), ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return responseBean;
	}

	
	/**
	 * 分页查询用户
	 * @param user 查询条件
	 * @param currentPage 当前页
	 * @param pageRecords 页大小(每页记录数)
	 * @return 分页数据
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	public OrderResponseBean queryOrdererByUserCode(String userCode,String createTime) throws CMException, CMRollBackException{
		final String xFunctionName = "queryOrderer";
		gLogger.begin(xFunctionName);
		
		if(null == userCode){
			throw CMException.sys("查询条件对象为null", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "user");
		}
		OrderRequestBean requestBean = new OrderRequestBean();
		OrderResponseBean responseBean = null;
		try {
			MyOrder myOrder = new MyOrder();
			myOrder.setUserCode(userCode);
			if(StringUtils.isNotBlank(createTime)){
				SimpleDateFormat format = new   SimpleDateFormat("yyyy-MM-dd"); 
				Date myDate = format.parse(createTime); 
				DateUtil du = new DateUtil();
				Date endTime = du.addDay(myDate, 1);
				myOrder.setCreateTime(createTime);
				myOrder.setEndTime(du.dateToString(endTime));
			}
			
			
			requestBean.setMyOrder(myOrder);
			responseBean = orderService.queryMyOrderByUserCodeAndTime(requestBean);

			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(), ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return responseBean;
	}
	

	/**
	 * 删除订餐人员
	 * @param userId 用户id
	 * @return 
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	@Override
	public UserResponseBean deleteOrderer(Integer userId) throws CMException, CMRollBackException {
		final String xFunctionName = "updateOrderer";
		gLogger.begin(xFunctionName);
		
		if(null == userId){
			throw CMException.sys("待删除的用户信息未提交！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userid");
		}
		
		UserResponseBean responseBean = null;
		try {
			UserRequestBean requestBean = new UserRequestBean();
			TSysUserInfo user = new TSysUserInfo();
			user.setUserId(userId);
			requestBean.setSysuserinfo(user);
			
			responseBean = userService.queryUserById(requestBean);
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
			//用户未找到
			if(null == responseBean.getSysuserinfo()){
				throw CMException.sys("待删除的用户不存在！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "user");
			}
			
			TSysUserInfoExample eg = new TSysUserInfoExample();
			TSysUserInfoExample.Criteria c = eg.createCriteria();
			c.andUserIdEqualTo(userId);
			//强制设置本次删除的用户类型为订餐人员
			c.andUserTypeEqualTo(Constants.USER_TYPE_CUSTOMER);
			requestBean.setSysUserInfoExample(eg);
	
			responseBean = userService.deleteUserInfo(requestBean);

			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(), ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return responseBean;
	}
	
	public boolean updateUserPassword(TSysUserInfo sysUserInfo) throws CMException, CMRollBackException{
		String xFunctionName = "findShopInfoById";
		boolean flag =false;
		try{
			if(null == sysUserInfo){
				throw CMException.sys("用户信息为空", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userinfo");
			}
			
			sysUserInfo.setModifyTime(new Date());
			UserResponseBean UserResponseBean = new UserResponseBean();
			UserRequestBean userRequestBean = new UserRequestBean();
			userRequestBean.setSysuserinfo(sysUserInfo);
			UserResponseBean =userService.updateUser(userRequestBean);
			if(UserResponseBean.getResponseCode().equals(Constants.UPDATE_SUCCESS_CODE)){
				flag=true;
			}else{
				flag =false;
				super.printErrorLogByResponse(gLogger, UserResponseBean,
						xFunctionName);
				throw CMRollBackException.sys(UserResponseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
		}catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
			return flag;
	}
	
	/**
	 * 设置用户角色
	 * @param userCode  待设置角色的用户编码
	 * @param roleId  角色id
	 * @param operateUser  操作用户
	 * @return UserResponseBean
	 */
	public UserResponseBean addLinkRole(String userCode, Integer roleId, String operateUserCode){

		TSysLinkUserRole tSysLinkUserRole = new TSysLinkUserRole();
		Date sysdDate = new Date();
		
		// 添加用户角色表
		tSysLinkUserRole.setRoleId(roleId);
		tSysLinkUserRole.setUserCode(userCode);
		
		tSysLinkUserRole.setCreateUserCode(operateUserCode);
		tSysLinkUserRole.setModifyUserCode(operateUserCode);
		tSysLinkUserRole.setCreateTime(sysdDate);
		tSysLinkUserRole.setModifyTime(sysdDate);
		
		UserRequestBean userRequestBean = new UserRequestBean();
		userRequestBean.setSysLinkUserRole(tSysLinkUserRole);
		return userService.addLinkUserRoles(userRequestBean);
	}
	
	/**
	 * 清除用户角色关联
	 * @param userCode  待设置角色的用户编码
	 * @param roleId  角色id
	 * @return UserResponseBean
	 */
	public UserResponseBean removeLinkRole(String userCode, Integer roleId){

		TSysLinkUserRole tSysLinkUserRole = new TSysLinkUserRole();
		
		TSysLinkUserRoleExample eg = new TSysLinkUserRoleExample();
		TSysLinkUserRoleExample.Criteria c = eg.createCriteria();
		//传入id时，代表删除指定role
		if(null != roleId && roleId > 0){
			c.andRoleIdEqualTo(roleId);
		}
		c.andUserCodeEqualTo(userCode);
		
		UserRequestBean userRequestBean = new UserRequestBean();
		userRequestBean.setSysLinkUserRole(tSysLinkUserRole);
		userRequestBean.setSysLinkUserRoleExample(eg);
		return userService.deleteLinkUserRole(userRequestBean);
	}
	
	//--------------------------------------------------------------private------------------------------------------------------------------
	/**
	 * 分页查询用户
	 * @param user 查询条件
	 * @param currentPage 当前页
	 * @param pageRecords 页大小(每页记录数)
	 * @return 分页数据
	 * @throws CMException
	 * @throws CMRollBackException
	 */
	private UserResponseBean queryOrderer(TSysUserInfo user, int currentPage, int pageRecords) throws CMException, CMRollBackException{
		final String xFunctionName = "queryOrderer";
		gLogger.begin(xFunctionName);
		
		if(null == user){
			throw CMException.sys("查询条件对象为null", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "user");
		}
		
		UserResponseBean responseBean = null;
		try {
			UserRequestBean requestBean = new UserRequestBean();
			requestBean.setSysuserinfo(user);
			requestBean.setCurrentPage(currentPage);
			requestBean.setPageRecords(pageRecords);
	
			responseBean = userService.fuzzyQueryForUserByUserProperties(requestBean);

			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, null);
			}
			
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(), ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally{
			gLogger.end(xFunctionName);
		}
		return responseBean;
	}
	
	//----------------------------------------getter/setter-----------------------------------------
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public ISmsMgr getSmsMgr() {
		
		return smsMgr;
	}

	public void setSmsMgr(ISmsMgr smsMgr) {
	
		this.smsMgr = smsMgr;
	}

	public SystemCommonMgr getSystemCommonMgr() {
		return systemCommonMgr;
	}

	public void setSystemCommonMgr(SystemCommonMgr systemCommonMgr) {
		this.systemCommonMgr = systemCommonMgr;
	}
	
	public IOrderService getOrderService() {
		return orderService;
	}



	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
}