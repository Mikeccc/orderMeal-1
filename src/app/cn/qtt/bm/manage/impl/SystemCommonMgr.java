
 /*
 * 文 件 名:  SystemMgr.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-4-1
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.manage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.MD5Encrypt;
import app.cn.qtt.bm.manage.ISystemCommonMgr;
import app.cn.qtt.bm.model.TSysMenuFuncInfo;
import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.model.TSysRoleInfo;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.model.TSysUserSeq;
import app.cn.qtt.bm.service.ICommonService;
import app.cn.qtt.bm.service.IMenuService;
import app.cn.qtt.bm.service.IRoleService;
import app.cn.qtt.bm.service.IUserService;
import app.cn.qtt.bm.service.pojo.CommonRequestBean;
import app.cn.qtt.bm.service.pojo.CommonResponseBean;
import app.cn.qtt.bm.service.pojo.MenuRequestBean;
import app.cn.qtt.bm.service.pojo.MenuResponseBean;
import app.cn.qtt.bm.service.pojo.RoleRequestBean;
import app.cn.qtt.bm.service.pojo.RoleResponseBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;



 /**       
 * 项目名称：CmEnterpriseProject    
 * 类名称：SystemMgr    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-4-1 下午7:51:16    
 * 修改人：linch   
 * 修改时间：2013-4-1 下午7:51:16    
 * 修改备注：    
 * @version       
 */

public class SystemCommonMgr extends BaseService implements ISystemCommonMgr{
	
	
	
	 /**
	 * 通用服务
	 */
	 
	private ICommonService commonService;
	
	
	 /**
	 * 角色服务
	 */
	 
	private IRoleService roleService;
	
	
	private IMenuService menuService;
	
	private IUserService userService;

	
	
	/**
	* 方法名称:      queryRolesByUserCode  
	* 方法描述:      根据userCode查询角色
	* @param userCode
	* @return
	* @throws CMException          
	* @Author:      linch
	* @Create Date: 2013-4-1 下午9:56:31
	*/ 
	 
	public List<TSysRoleInfo> queryRolesByUserCode(String userCode)
			throws CMException,CMRollBackException {
		
		final String xFunctionName = "queryRolesByUserCode";
		gLogger.begin(xFunctionName);
		
		List<TSysRoleInfo> roles = new ArrayList<TSysRoleInfo>();
		
		try {
			Assert.notNull(userCode);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userCode is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userCode");
		}
		
		
		
		try{
			TSysUserInfo user =  new TSysUserInfo();
			user.setUserCode(userCode);
			
			UserRequestBean requestBean = new UserRequestBean();
			requestBean.setSysuserinfo(user);
			
			RoleResponseBean responseBean = roleService.queryForRoleByUser(requestBean);
			//TODO 获取角色
			
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			else{
				if(CollectionUtils.isNotEmpty(responseBean.getList())){
					
					roles = responseBean.getList();
				}
				
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
		
		
		return roles;
		
	}
	
	
	
	
	
	/**
	* 方法名称:      queryMenuByUserCode  
	* 方法描述:      根据userCode查询菜单
	* @param userCode
	* @return
	* @throws CMException  
	* @throws CMRollBackException      
	* @Author:      linch
	* @Create Date: 2013-4-1 下午9:56:44
	*/ 
	 
	public Map<TSysMenuInfo, List<TSysMenuFuncInfo>> queryMenuByUserCode(String userCode)
			throws CMException,CMRollBackException  {
		
		final String xFunctionName = "queryMenuByUserCode";
		gLogger.begin(xFunctionName);
		
		Map<TSysMenuInfo, List<TSysMenuFuncInfo>> menus = new HashMap<TSysMenuInfo, List<TSysMenuFuncInfo>>();
		
		try {
			Assert.notNull(userCode);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userCode is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userCode");
		}
		
		
		
		try{
			TSysUserInfo user =  new TSysUserInfo();
			user.setUserCode(userCode);
			
			UserRequestBean requestBean = new UserRequestBean();
			requestBean.setSysuserinfo(user);
			
			MenuResponseBean responseBean = menuService.queryForMenuByUser(requestBean);
			
			
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			else{
				if(CollectionUtils.isNotEmpty(responseBean.getList())){
					
					List<?> list = responseBean.getList();
					
					menus = menuService.queryForMenuFuncList((List<TSysMenuInfo>) list);
					
				}
				
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
		}finally{
			gLogger.end(xFunctionName);
		}
		
		return menus;
		
	}
	
	
	/**
	* 方法名称:      saveAndQueryUserCodeByType  
	* 方法描述:      根据type获取用户CODE
	* @param type
	* @return
	* @throws CMException          
	* @Author:      linch
	* @Create Date: 2013-4-2 上午10:07:13
	*/ 
	 
	public String saveAndQueryUserCodeByType(String type)
			throws CMException,CMRollBackException  {
		final String xFunctionName = "saveAndQueryUserCodeByType";
		gLogger.begin(xFunctionName);
		
		String userCode = null;

		//类别不能为空
		try {
			Assert.notNull(type);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("type is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "type");
		}
		

		try{
			
			CommonRequestBean requestBean = new CommonRequestBean();
			TSysUserSeq userSeq = new TSysUserSeq();
			userSeq.setBusinessType(type);	//添加类别
			requestBean.settSysUserSeq(userSeq);
			CommonResponseBean responseBean = commonService.getSysUserSeq(requestBean);
			
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
			}
			else{
				if (responseBean.getUserSeq() != null
						&& responseBean.getUserSeq().getBusinessId() != null) {
					
					String businessId = String.valueOf(responseBean.getUserSeq().getBusinessId());
					StringBuffer buffer = new StringBuffer();
					
					//已类别开头
					buffer.append(type);
					
					for(int i = 0;i < Constants.USER_CODE_LENGTH - businessId.length() - 2; i++){
						//补齐0
						buffer.append("0");
					}
					buffer.append(businessId);

					userCode = buffer.toString();
				}
			}
			
			
			if(StringUtils.isBlank(userCode)){
				throw CMRollBackException.sys("userCode generation failed!",
						ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
			}
			//TODO 实现逻辑
		}
		catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}finally{
			gLogger.end(xFunctionName);
		}
		
		return userCode;
	}
	
	
	/**
	* 方法名称:      queryMenuByRoleId  
	* 方法描述:      根据角色CODE查询菜单
	* @param roleCode
	* @return
	* @throws CMException          
	* @Author:      linch
	* @Create Date: 2013-4-1 下午11:07:39
	*/ 
	 
	public Map<TSysMenuInfo, List<TSysMenuFuncInfo>> queryMenuByRoleId( Integer roleId)
			throws CMException,CMRollBackException {
		
		final String xFunctionName = "queryMenuByRole";
		gLogger.begin(xFunctionName);
		
		Map<TSysMenuInfo, List<TSysMenuFuncInfo>> menus = new HashMap<TSysMenuInfo, List<TSysMenuFuncInfo>>();
		
		try {
			Assert.notNull(roleId);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("roleCode is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "roleCode");
		}
		
		
		
		try{
			TSysRoleInfo role = new TSysRoleInfo();
			role.setRoleId(roleId);
			
			RoleRequestBean requestBean = new RoleRequestBean();
			requestBean.setSysroleinfo(role);
			
			MenuResponseBean responseBean = menuService.queryForMenuByUserRole(requestBean);
			
			
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			else{
				if(CollectionUtils.isNotEmpty(responseBean.getList())){
					
					List<?> list = responseBean.getList();
					
					menus = menuService.queryForMenuFuncList((List<TSysMenuInfo>) list);
					
				}
				
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
		}finally{
			gLogger.end(xFunctionName);
		}
		
		return menus;
		
	}
	
	
	
	
	
	/**
	* 方法名称:      findMenusVisitPathsIntoEnterpriseUserBean  
	* 方法描述:      填充菜单信息到用户bean
	* @param userBean
	* @param menu* @throws CMException  tion        
	* @Author:      linch
	* @Create Date: 2013-4-1 下午10:33:32
	*/ 
	 
	public void findMenusVisitPathsIntoEnterpriseUserBean(
			UserBean userBean, Map<TSysMenuInfo, List<TSysMenuFuncInfo>> menus)
					throws CMException {
		
		final String xFunctionName = "findVisitPathsIntoEnterpriseUserBean";
		gLogger.begin(xFunctionName);
		
		//菜单列表不能为空
		try {
			Assert.notEmpty(menus);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("userCode is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userCode");
		}
		
		//用户BEAN为空，则直接实例化
		if(userBean == null){
			userBean = new UserBean();
		}
		
		try{

			Iterator<Entry<TSysMenuInfo, List<TSysMenuFuncInfo>>> iterator = menus.entrySet().iterator();
			List<TSysMenuInfo> menuInfos = new ArrayList<TSysMenuInfo>();
			Vector<String> paths = new  Vector<String>();
			List<TSysMenuFuncInfo> menufuncs = null;
			//遍历MAP
			while (iterator.hasNext()) {
				Map.Entry<TSysMenuInfo, List<TSysMenuFuncInfo>> entry = (Map.Entry<TSysMenuInfo, List<TSysMenuFuncInfo>>) iterator
						.next();
				//取key
				menuInfos.add(entry.getKey());
				//取功能菜单
				menufuncs = entry.getValue();
				for(TSysMenuFuncInfo menufunc : menufuncs){
					if(menufunc != null && StringUtils.isNotBlank(menufunc.getFuncUrl())){
						paths.add(menufunc.getFuncUrl());
					}
				}
				
			}
			
			userBean.setMenus(menuInfos);
			userBean.setVisitPaths(paths);
			
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}finally{
			gLogger.end(xFunctionName);
		}
		
		

	}
	
	
	
	
	/**
	* 方法名称:      queryMenuByType  
	* 方法描述:      根据类别查询菜单
	* @param type
	* @return
	* @throws CMException          
	* @Author:      linch
	* @Create Date: 2013-4-2 上午10:17:10
	*/ 
	 
	public List<TSysMenuInfo> queryMenuByType(String type)
			throws CMException {
		
		final String xFunctionName = "queryMenuByType";
		gLogger.begin(xFunctionName);
		
		List<TSysMenuInfo> menus = new ArrayList<TSysMenuInfo>();

		//类别不能为空
		try {
			Assert.notNull(type);
		} catch (IllegalArgumentException e) {
			gLogger.end(xFunctionName);
			throw CMException.sys("type is null",
					ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "type");
		}
		
		
		try{
			
			TSysMenuInfo menu = new TSysMenuInfo();
			menu.setMenuType(type);
			
			MenuRequestBean requestBean = new MenuRequestBean();
			requestBean.setSysmenuinfo(menu);
			
			
			MenuResponseBean responseBean = menuService.queryForMenuByType(requestBean);
			
			
			//不成功的时候
			if(!Constants.SERVICE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				super.printErrorLogByResponse(gLogger, responseBean, xFunctionName);
				throw CMRollBackException.sys(responseBean.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
			else{
				
				if(CollectionUtils.isNotEmpty(responseBean.getList())){
					menus = responseBean.getList();
				}
				
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
		
		return menus;
		
	}
	
	
	
	
	
	/**
	* 方法名称:      queryMenuLocalTree  
	* 方法描述:      迭代获取整颗树
	* @param menus
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-2 上午11:48:51
	*/ 
	 
	public List<Object> queryMenuLocalTree(List<TSysMenuInfo> menus){
		
		List<Object> list = new ArrayList<Object>();
		
		if(CollectionUtils.isNotEmpty(menus)){
			
			for(TSysMenuInfo menu : menus){
				//如果parent为空当作根节点
				if(menu != null && null == menu.getMenuParentId()){
					//迭代查询取整颗树
					list.add(this.queryMenusLocalTreeByParent(menus, menu));
				}
			}
			
		}
		
		return list;
		
	}
	
	
	
	/**
	* 方法名称:      queryMenusLocalTreeByParent  
	* 方法描述:      
	* @param menus
	* @param target
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-2 上午11:44:24
	*/ 
	 
	public Map<String,Object> queryMenusLocalTreeByParent(List<TSysMenuInfo> menus,TSysMenuInfo target){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("node", target);	//保存本身对象
		
		List<Map<String,Object>> children = null;
		
		if(StringUtils.isBlank(target.getMenuIsLeaf()) || !"1".equals(target.getMenuIsLeaf())){
			children = new ArrayList<Map<String,Object>>();
			for(TSysMenuInfo menu : menus){
				if(menu != null && target.getMenuId().equals(menu.getMenuParentId()) ){
					//循环迭代取子节点值
					Map<String,Object> child= queryMenusLocalTreeByParent(menus,menu);
					//添加子节点
					children.add(child);
				}
			}
		}
		map.put("children", children);
		return map;
		
	}
	
	/**
	 * 
	* @Title: updataPassword
	* @Description: 修改密码
	* @param @param userCode
	* @param @param newPassword
	* @param @return    设定文件
	* @return Boolean    返回类型
	* @throws
	 */
	public Boolean updataPassword(String userCode,String newPassword) throws CMException,CMRollBackException{
		final String xFunctionName = "updataPassword";
		gLogger.begin(xFunctionName);
		Boolean flag = null;
		
		try{
			UserResponseBean response = new UserResponseBean();
			UserRequestBean userRequestBean = new UserRequestBean();
			TSysUserInfo userInfo  = new TSysUserInfo();
			userInfo.setUserCode(userCode);
			userRequestBean.setSysuserinfo(userInfo);
			response = userService.queryForUserByUserProperties(userRequestBean);
			if(response!=null&&response.getUserlist()!=null&&response.getUserlist().size()>0){
				userInfo = (TSysUserInfo) response.getUserlist().get(0);
			}
			String passwordMD5 = MD5Encrypt.MD5Encode(newPassword);
			userInfo.setUserPassword(passwordMD5);
		
			userRequestBean.setSysuserinfo(userInfo);
			userRequestBean.setCurrentUserCode(userCode);
			response = userService.updateUser(userRequestBean);
			
			if(response!=null&&response.getResponseCode().equals(Constants.UPDATE_SUCCESS_CODE)){
				flag=true;
			}else{
				super.printErrorLogByResponse(gLogger, response,
						xFunctionName);
				throw CMException.sys(response.getErrMsg(),
						ExceptionConstants.SERVICE_CODE, null);
			}
		}catch (CMRollBackException e) {
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
		
		return flag;
		
	}
	
	public Boolean checkOldPassword(String userCode,String oldPassword) throws CMException{
		final String xFunctionName = "updataPassword";
		gLogger.begin(xFunctionName);
		Boolean flag = null;
		try{
			UserResponseBean response = new UserResponseBean();
			UserRequestBean request = new UserRequestBean();
			TSysUserInfo sysuserinfo = new TSysUserInfo();
			sysuserinfo.setUserCode(userCode);
			request.setSysuserinfo(sysuserinfo);
			response = userService.queryForUserByUserProperties(request);
			if(Constants.SELECT_SUCCESS_CODE.equals(response.getResponseCode())){
				if(response!=null&&response.getUserlist()!=null){
					TSysUserInfo tSysUserInfo=(TSysUserInfo) response.getUserlist().get(0);
					
					
					if(tSysUserInfo!=null&&StringUtils.isNotBlank(tSysUserInfo.getUserPassword())){
						if(MD5Encrypt.MD5Encode(oldPassword).equals(tSysUserInfo.getUserPassword())){
							flag=true;
						}else{
							flag=false;
						}
					}else{
						flag=false;
					}
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
		}
		catch (Exception e) {
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
	 * 
	* @Title: getUserDetail
	* @Description: 获取当前登录用户信息
	* @param @param userCode
	* @param @return    设定文件
	* @return TSysUserInfo    返回类型
	* @throws
	 */
	public TSysUserInfo getUserDetail(String userCode) throws CMException,CMRollBackException{
		final String xFunctionName = "getUserDetail";
		gLogger.begin(xFunctionName);
		TSysUserInfo sysUserInfo = null;
		try{
			UserResponseBean response = new UserResponseBean();
			UserRequestBean userRequestBean = new UserRequestBean();
			TSysUserInfo userInfo  = new TSysUserInfo();
			userInfo.setUserCode(userCode);
			userRequestBean.setSysuserinfo(userInfo);
			response = userService.queryForUserByUserProperties(userRequestBean);
			if(response!=null&&response.getUserlist()!=null&&response.getUserlist().size()>0){
				sysUserInfo = (TSysUserInfo) response.getUserlist().get(0);
			}
			if(!Constants.SELECT_SUCCESS_CODE.equals(response.getResponseCode())){
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

		return sysUserInfo;
	}
	

	public ICommonService getCommonService() {
	
		return commonService;
	}


	public void setCommonService(ICommonService commonService) {
	
		this.commonService = commonService;
	}


	public IRoleService getRoleService() {
	
		return roleService;
	}


	public void setRoleService(IRoleService roleService) {
	
		this.roleService = roleService;
	}





	public IMenuService getMenuService() {
	
		return menuService;
	}





	public void setMenuService(IMenuService menuService) {
	
		this.menuService = menuService;
	}





	public IUserService getUserService() {
		return userService;
	}





	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	
	
	
}

