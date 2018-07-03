package app.cn.qtt.bm.service;

import java.util.List;
import java.util.Map;

import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.service.pojo.MenuRequestBean;
import app.cn.qtt.bm.service.pojo.MenuResponseBean;
import app.cn.qtt.bm.service.pojo.RoleRequestBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;


public interface IMenuService {
	/**
	 * 根据角色编码查询
	 * @param RoleRequestBean
	 * @return MenuResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public MenuResponseBean queryForMenuByUserRole(RoleRequestBean requestBean);
	
	/**
	 * 根据用户编码查询
	 * @param RoleRequestBean
	 * @return MenuResponseBean
	 * @author jjf
	 * @since 2013-3-27 15:40
	 */
	public MenuResponseBean queryForMenuByUser(UserRequestBean requestBean);
	/**
	 * 根据菜单类型查询
	 * @param MenuRequestBean
	 * @return MenuResponseBean
	 * @author jjf
	 * @since 2013-4-2
	 */
	public MenuResponseBean queryForMenuByType(MenuRequestBean requestBean);
	/**
	 * 根据菜单类型查询菜单功能
	 * @param MenuRequestBean
	 * @return MenuResponseBean
	 * @author jjf
	 * @since 2013-4-2
	 */
	public MenuResponseBean queryForMenuFuncByMenuCode(MenuRequestBean requestBean);
	

	/**
	 * 根据菜单列表找出所有的功能
	 * @param List
	 * @param Map
	 * @author JJF
	 * @since 2012-4-1
	 */
	public Map queryForMenuFuncList(List<TSysMenuInfo> MenuInfoList);
}
