package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.TSysRoleInfo;
import app.cn.qtt.bm.model.TSysUserInfo;

public class RoleRequestBean extends RequestBean {

	private int currentPage = 1;// 当前页
	
	private int pageRecorders = 10; // 每页10条数据 
	
	private TSysRoleInfo sysroleinfo;
	private List<TSysRoleInfo> sysroleinfoList;
	private TSysUserInfo sysUserinfo;
	

	public Integer getCurrentPage() {
		return currentPage;
	}

	public int getPageRecorders() {
		return pageRecorders;
	}

	public TSysRoleInfo getSysroleinfo() {
		return sysroleinfo;
	}

	public void setSysroleinfo(TSysRoleInfo sysroleinfo) {
		this.sysroleinfo = sysroleinfo;
	}

	public List<TSysRoleInfo> getSysroleinfoList() {
		return sysroleinfoList;
	}

	public void setSysroleinfoList(List<TSysRoleInfo> sysroleinfoList) {
		this.sysroleinfoList = sysroleinfoList;
	}

	public TSysUserInfo getCurrentUser() {
		// TODO Auto-generated method stub
		return sysUserinfo;
	}

	public void setCurrentUser(TSysUserInfo userinfo) {
		// TODO Auto-generated method stub
		this.sysUserinfo=userinfo;
	}
	
}
