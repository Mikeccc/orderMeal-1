package app.cn.qtt.bm.service;

import app.cn.qtt.bm.service.pojo.MenuRequestBean;
import app.cn.qtt.bm.service.pojo.RoleRequestBean;
import app.cn.qtt.bm.service.pojo.RoleResponseBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;


public interface IRoleService {
	/**
	 * 用户增加自定义角色
	 * @param RoleRequestBean
	 * @return RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27
	 */
	public RoleResponseBean addDefinedRole(RoleRequestBean requestBean);
	
	/**
	 * 根据角色属性查询
	 * @param RoleRequestBean
	 * @return RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27
	 */
	public RoleResponseBean queryForRole(RoleRequestBean requestBean);
	
	/**
	 * 根据用户编码查询
	 * @param UserRequestBean
	 * @return RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27
	 */
	public RoleResponseBean queryForRoleByUser(UserRequestBean requestBean);
	
	/**
	 * 根据角色编码查询
	 * @param UserRequestBean
	 * @return RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27
	 */
	public RoleResponseBean queryForRoleByCode(RoleRequestBean requestBean);
	
	/**
	 * 删除自定义角色
	 * @param RoleRequestBean
	 * @return RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27
	 */
	public RoleResponseBean deleteDefinedRole(RoleRequestBean requestBean);
	
	/**
	 * 编辑自定义角色
	 * @param RoleRequestBean
	 * @return RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27
	 */
	public RoleResponseBean updateRole(RoleRequestBean requestBean);
	
	/**
	 * 编辑自定义角色权限
	 * @param RoleRequestBean
	 * @return RoleRequestBean
	 * @author jjf
	 * @since 2013-3-27
	 */
	public RoleResponseBean updateRole(RoleRequestBean requestBean,MenuRequestBean menuInfoRequestBean);
	
}
