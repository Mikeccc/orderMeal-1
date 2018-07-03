package app.cn.qtt.bm.common.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysMenuInfo;
import app.cn.qtt.bm.model.TSysRoleInfo;
import app.cn.qtt.bm.model.TSysUserInfo;


public class UserBean implements Serializable{

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = 8840506541317992429L;


	/**
	 * 用户ID
	 */
	 
	private String userCode;
	
	
	 /**
	 * 用户类别
	 */
	 
	private String userType;
	
	
	
	private TShopInfo shopInfo;
	
	
	 /**
	 * 用户信息
	 */
	 
	private TSysUserInfo userInfo;
	
	
	
	
	 /**
	 * 角色
	 */
	 
	private List<TSysRoleInfo> roles;
	
	
	
	
	
	 /**
	 * 菜单
	 */
	 
	private List<TSysMenuInfo> menus;
	
	
	
	 /**
	 * 可访问的资源路径
	 */
	 
	private Vector<String> visitPaths;



	public String getUserCode() {
	
		return userCode;
	}



	public void setUserCode(String userCode) {
	
		this.userCode = userCode;
	}



	public String getUserType() {
	
		return userType;
	}



	public void setUserType(String userType) {
	
		this.userType = userType;
	}



	public TSysUserInfo getUserInfo() {
	
		return userInfo;
	}



	public void setUserInfo(TSysUserInfo userInfo) {
	
		this.userInfo = userInfo;
	}



	public List<TSysRoleInfo> getRoles() {
	
		return roles;
	}



	public void setRoles(List<TSysRoleInfo> roles) {
	
		this.roles = roles;
	}



	public List<TSysMenuInfo> getMenus() {
	
		return menus;
	}



	public void setMenus(List<TSysMenuInfo> menus) {
	
		this.menus = menus;
	}



	public Vector<String> getVisitPaths() {
	
		return visitPaths;
	}



	public void setVisitPaths(Vector<String> visitPaths) {
	
		this.visitPaths = visitPaths;
	}



	public TShopInfo getShopInfo() {
	
		return shopInfo;
	}



	public void setShopInfo(TShopInfo shopInfo) {
	
		this.shopInfo = shopInfo;
	}
	
	
	
	
}

