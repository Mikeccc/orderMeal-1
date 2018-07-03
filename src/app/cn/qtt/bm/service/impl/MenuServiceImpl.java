package app.cn.qtt.bm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.dao.TSysMenuFuncInfoDAO;
import app.cn.qtt.bm.dao.TSysMenuInfoDAO;
import app.cn.qtt.bm.dao.TSysRoleInfoDAO;
import app.cn.qtt.bm.dao.TSysUserInfoDAO;
import app.cn.qtt.bm.model.TSysMenuFuncInfo;
import app.cn.qtt.bm.model.TSysMenuFuncInfoExample;
import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.model.TSysMenuInfoExample;
import app.cn.qtt.bm.model.TSysRoleInfo;
import app.cn.qtt.bm.service.IMenuService;
import app.cn.qtt.bm.service.pojo.MenuRequestBean;
import app.cn.qtt.bm.service.pojo.MenuResponseBean;
import app.cn.qtt.bm.service.pojo.RoleRequestBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;

public class MenuServiceImpl implements IMenuService {
	private TSysMenuInfoDAO tSysMenuInfoDAO;
	private TSysMenuFuncInfoDAO tSysMenuFuncInfoDAO;
	private TSysUserInfoDAO tSysUserInfoDAO;
	private TSysRoleInfoDAO tSysRoleInfoDAO;
	public MenuResponseBean queryForMenuByType(
			MenuRequestBean requestBean) {
		TSysMenuInfoExample example =new TSysMenuInfoExample();
		MenuResponseBean responseBean=new MenuResponseBean();
		try{
			if(null==requestBean){
				throw new Exception("requestBean为空");
			}else if(null==requestBean.getSysmenuinfo()){
				throw new Exception("Sysmenuinfo为空");
			}else if(StringUtils.isBlank(requestBean.getSysmenuinfo().getMenuType())){
				throw new Exception("MenuType为空");
			}else{
				example.createCriteria().andMenuTypeEqualTo(requestBean.getSysmenuinfo().getMenuType());				
				responseBean.setList(tSysMenuInfoDAO.selectByExample(example));
			}
			
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}
		
		
		return responseBean;
	}

	/**
	 * 根据用户编码查询菜单实现
	 * @param RoleRequestBean
	 * @return MenuResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public MenuResponseBean queryForMenuByUser(
			UserRequestBean requestBean) {
		
		MenuResponseBean menuResponseBean=new MenuResponseBean();
		
		List<TSysRoleInfo> roleInfoList=new ArrayList<TSysRoleInfo>();
		try{
			if(null==requestBean){
				throw new Exception("requestBean 请求对象为空");
			}
			if(null==requestBean.getSysuserinfo()){
				throw new Exception("用户信息对象为空");
			}
			//根据用户查询角色		
			roleInfoList=tSysRoleInfoDAO.selectRoleByUserCode(requestBean.getSysuserinfo());
			if(null==roleInfoList||roleInfoList.size()==0){
				throw new Exception("查找当前岗位失败");
			}
			//根据角色查询菜单
			List<TSysMenuInfo> menuInfoList=new ArrayList<TSysMenuInfo>();
			
			menuInfoList=tSysMenuInfoDAO.selectMenuLeftJoinRoleMenu(roleInfoList.get(0));
			menuResponseBean.setList(menuInfoList);
			menuResponseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		}catch(Exception e){
			menuResponseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			menuResponseBean.setErrMsg(e.getMessage());
			menuResponseBean.setException(e);
			
		}
		//根据菜单查询功能
		return menuResponseBean;
	}
	/**
	 * 根据菜单实现
	 * @param RoleRequestBean
	 * @return MenuResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public Map queryForMenuFuncList(List<TSysMenuInfo> menuInfoList) {
		// TODO Auto-generated method stub
		Map<TSysMenuInfo,List<TSysMenuFuncInfo>> resultMap=new HashMap();
		List<TSysMenuFuncInfo> menuFuncList =null;
		for(int i=0;i<menuInfoList.size();i++){
			TSysMenuFuncInfoExample example =new TSysMenuFuncInfoExample();
			example.createCriteria().andMenuIdEqualTo(menuInfoList.get(i).getMenuId());
			menuFuncList=tSysMenuFuncInfoDAO.selectByExample(example);
			resultMap.put(menuInfoList.get(i), menuFuncList);
		}
		return resultMap;
	}

	



	public TSysMenuInfoDAO getTSysMenuInfoDAO() {
		return tSysMenuInfoDAO;
	}

	public void setTSysMenuInfoDAO(TSysMenuInfoDAO sysMenuInfoDAO) {
		tSysMenuInfoDAO = sysMenuInfoDAO;
	}

	public MenuResponseBean queryForMenuFuncByMenuCode(
			MenuRequestBean requestBean) {
		TSysMenuFuncInfoExample example =new TSysMenuFuncInfoExample();
		MenuResponseBean responseBean=new MenuResponseBean();
		try{
			if(null==requestBean){
				throw new Exception("requestBean 为空");
			}else if(null==requestBean.getSysmenuinfo()){
				throw new Exception("Sysmenuinfo 为空");
			}else{
				example.createCriteria().andMenuIdEqualTo(requestBean.getSysmenuinfo().getMenuId());			
				responseBean.setList(tSysMenuFuncInfoDAO.selectByExample(example));
			}			
		}catch(Exception e){
			responseBean.setException(e);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}		
		
		return responseBean;
	}

	public TSysUserInfoDAO getTSysUserInfoDAO() {
		return tSysUserInfoDAO;
	}

	public void setTSysUserInfoDAO(TSysUserInfoDAO sysUserInfoDAO) {
		tSysUserInfoDAO = sysUserInfoDAO;
	}

	public MenuResponseBean queryForMenuByUserRole(
			RoleRequestBean requestBean) {
		MenuResponseBean responseBean=new MenuResponseBean();
		try{
			if(null==requestBean){
				throw new NullPointerException("requestBean 请求对象为空");
			}
			if(null==requestBean.getSysroleinfo()){
				throw new NullPointerException("sysroleinfo为空");
			}
			List<TSysMenuInfo> list=tSysMenuInfoDAO.selectMenuLeftJoinRoleMenu(requestBean.getSysroleinfo());
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
			responseBean.setList(list);
		}catch(Exception e){
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
		}
		
		return responseBean;
	}

	public TSysMenuFuncInfoDAO getTSysMenuFuncInfoDAO() {
		return tSysMenuFuncInfoDAO;
	}

	public void setTSysMenuFuncInfoDAO(TSysMenuFuncInfoDAO sysMenuFuncInfoDAO) {
		tSysMenuFuncInfoDAO = sysMenuFuncInfoDAO;
	}

	public TSysRoleInfoDAO getTSysRoleInfoDAO() {
		return tSysRoleInfoDAO;
	}

	public void setTSysRoleInfoDAO(TSysRoleInfoDAO sysRoleInfoDAO) {
		tSysRoleInfoDAO = sysRoleInfoDAO;
	}

}
