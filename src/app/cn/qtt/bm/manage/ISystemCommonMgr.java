
 /*
 * 文 件 名:  ISystemCommonMgr.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-6-17
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.manage;

import java.util.List;
import java.util.Map;

import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.model.TSysMenuFuncInfo;
import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.model.TSysRoleInfo;
import app.cn.qtt.bm.model.TSysUserInfo;


 /**       
 * 项目名称：BespeakMeal    
 * 类名称：ISystemCommonMgr    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-6-17 下午6:28:46    
 * 修改人：linch   
 * 修改时间：2013-6-17 下午6:28:46    
 * 修改备注：    
 * @version       
 */

public interface ISystemCommonMgr {
	
	/**
	* 方法名称:      queryRolesByUserCode  
	* 方法描述:      根据userCode查询角色
	* @param userCode
	* @return
	* @throws CMException  
	* @throws CMRollBackException              
	* @Author:      linch
	* @Create Date: 2013-4-1 下午9:56:31
	*/ 
	 
	public List<TSysRoleInfo> queryRolesByUserCode(String userCode)
			throws CMException,CMRollBackException;
	
	
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
			throws CMException,CMRollBackException;
	
	/**
	* 方法名称:      saveAndQueryUserCodeByType  
	* 方法描述:      根据type获取用户CODE
	* @param type
	* @return
	* @throws CMException  
	* @throws CMRollBackException       
	* @Author:      linch
	* @Create Date: 2013-4-2 上午10:07:13
	*/ 
	 
	public String saveAndQueryUserCodeByType(String type)
			throws CMException,CMRollBackException;
	
	
	/**
	* 方法名称:      queryMenuByRoleId  
	* 方法描述:      根据角色CODE查询菜单
	* @param roleCode
	* @return
	* @throws CMException  
	* @throws CMRollBackException       
	* @Author:      linch
	* @Create Date: 2013-4-1 下午11:07:39
	*/ 
	 
	public Map<TSysMenuInfo, List<TSysMenuFuncInfo>> queryMenuByRoleId( Integer roleId)
			throws CMException,CMRollBackException;
			

	/**
	* 方法名称:      findMenusVisitPathsIntoEnterpriseUserBean  
	* 方法描述:      填充菜单信息到用户bean
	* @param userBean
	* @param menus
	* @throws CMException  
	* @Author:      linch
	* @Create Date: 2013-4-1 下午10:33:32
	*/ 
	 
	public void findMenusVisitPathsIntoEnterpriseUserBean(
			UserBean userBean, Map<TSysMenuInfo, List<TSysMenuFuncInfo>> menus)
					throws CMException ;
	
	
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
			throws CMException;
	
	
	/**
	* 方法名称:      queryMenuLocalTree  
	* 方法描述:      迭代获取整颗树
	* @param menus
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-2 上午11:48:51
	*/ 
	 
	public List<Object> queryMenuLocalTree(List<TSysMenuInfo> menus);
	
	
	
	/**
	* 方法名称:      queryMenusLocalTreeByParent  
	* 方法描述:      
	* @param menus
	* @param target
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-2 上午11:44:24
	*/ 
	 
	public Map<String,Object> queryMenusLocalTreeByParent(List<TSysMenuInfo> menus,TSysMenuInfo target);
	
	
	
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
	public Boolean updataPassword(String userCode,String newPassword) throws CMException,CMRollBackException;
	
	
	
	public Boolean checkOldPassword(String userCode,String oldPassword) throws CMException;
	
	
	/**
	 * 
	* @Title: getUserDetail
	* @Description: 获取当前登录用户信息
	* @param @param userCode
	* @param @return    设定文件
	* @return TSysUserInfo    返回类型
	* @throws
	 */
	public TSysUserInfo getUserDetail(String userCode) throws CMException,CMRollBackException;
	
}

