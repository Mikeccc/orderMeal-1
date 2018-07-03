package app.cn.qtt.bm.module.password;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;

import IceInternal.Instance;
import app.cn.qtt.bm.common.utils.DateUtil;

public class LoginPasswordManage {
	
	private ServletContext servletContext;
	
	
	private final static String LOGIN_PASSWORD_MANAGE_OBJ_KEY_NAME = "login_password_manage_obj";
	
	private final static String TIME_KEY_NAME = "time_key_name";
	
	private static LoginPasswordManage manage;
	
	
	private LoginPasswordManage(){
		
	}
	
	
	public static LoginPasswordManage instance(){
		if(null == manage){
			manage = new LoginPasswordManage();
		}
		return manage;
	}
	
	
	
	/**
	* 方法名称:      getManageObj  
	* 方法描述:      获取对象
	* @return
	* @throws Exception        
	* @Author:      linch
	* @Create Date: 2013-7-19 上午11:46:10
	*/ 
	 
	@SuppressWarnings("unchecked")
	private Map<String,String> getManageObj() throws Exception{
		//获取servlet 对象
		if(servletContext == null){
			servletContext = ServletActionContext.getServletContext();
		}
		
		if(servletContext == null){
			throw new Exception("servletContext is null");
		}
		//获取对象
		Object object = servletContext.getAttribute(LOGIN_PASSWORD_MANAGE_OBJ_KEY_NAME);
		Map<String,String> map = null;
		if(object != null){
			map = (HashMap<String, String>) object;
		}
		if(map == null){
			map = new HashMap<String,String>();
		}
		//初始化map，判断时间是否是同一天
		passwordManageInit(map);
		
		return map;	
	}
	
	
	
	
	
	/**
	* 方法名称:      saveObj  
	* 方法描述:      保存对象
	* @param map
	* @throws Exception        
	* @Author:      linch
	* @Create Date: 2013-7-19 上午11:45:36
	*/ 
	 
	public void saveObj(Map<String,String> map) throws Exception{
		//获取servlet 对象
		if(servletContext == null){
			ServletActionContext.getServletContext();
		}
		
		if(servletContext == null){
			throw new Exception("servletContext is null");
		}
		
		servletContext.setAttribute(LOGIN_PASSWORD_MANAGE_OBJ_KEY_NAME, map);
	}
	
	
	
	/**
	* 方法名称:      getUserLoginFailNum  
	* 方法描述:      获取失败用户数
	* @param userPhone
	* @return        
	* @Author:      linch
	* @Create Date: 2013-7-18 上午11:03:52
	*/ 
	 
	public Integer getUserLoginFailNum(String userPhone){
		Integer count = 0;
		try {
			if(StringUtils.isNotBlank(userPhone)){
				Map<String,String> map = getManageObj();
				if(map.containsKey(userPhone)){
					String value = map.get(userPhone);
					if(NumberUtils.isNumber(value)){
						count = NumberUtils.toInt(value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	
	/**
	* 方法名称:      clearUserLoginFailNum  
	* 方法描述:      清空用户手机号码
	* @param userPhone        
	* @Author:      linch
	* @Create Date: 2013-7-19 下午4:22:18
	*/ 
	 
	public void clearUserLoginFailNum(String userPhone){
		try {
			if(StringUtils.isNotBlank(userPhone)){
				Map<String,String> map = getManageObj();
				if(map.containsKey(userPhone)){
					map.remove(userPhone);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	* 方法名称:      addUserLoginFailNum  
	* 方法描述:      
	* @param userPhone
	* @throws Exception        
	* @Author:      linch
	* @Create Date: 2013-7-18 上午11:14:35
	*/ 
	 
	public void addUserLoginFailNum(String userPhone) throws Exception{
		Map<String,String> map = getManageObj();
		String value = map.get(userPhone);
		Integer count = 0;
		if(NumberUtils.isNumber(value)){
			count = NumberUtils.toInt(value);
		}
		count += 1;
		map.put(userPhone, String.valueOf(count));
		saveObj(map);
	}
	
	
	/**
	* 方法名称:      passwordManageInit  
	* 方法描述:      
	* @param map        
	* @Author:      linch
	* @Create Date: 2013-7-18 上午10:47:05
	*/ 
	 
	private void passwordManageInit(Map<String,String> map){
		if(null == map){
			map = new HashMap<String,String>();
		}
		String timeKey = map.get(TIME_KEY_NAME);
		DateUtil dateUtil = new DateUtil();
		String today = dateUtil.dateToString(new Date());
		//判断是否是同一天
		if(StringUtils.isBlank(timeKey) || !today.equals(timeKey)){
			//清空map
			map.clear();
			map.put(TIME_KEY_NAME, today);
		}
	}
	
	
	

}

