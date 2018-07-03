package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.common.pojo.request.RequestBean;
import app.cn.qtt.bm.model.TSysLinkUserRole;
import app.cn.qtt.bm.model.TSysLinkUserRoleExample;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.model.TSysUserInfoExample;

public class UserRequestBean extends RequestBean {

	private TSysUserInfo sysuserinfo;
	TSysUserInfoExample sysUserInfoExample;
	private List<TSysUserInfo> sysUserinfoList;
	
	private List<String> UserCodeList;//用户编码List	
	private List<?> resultList;
	private String currentUserCode;//当前用户
	/**
	 * 用户code
	 */
	private String userCode;
	
	/**
	 * 日期时间
	 */
	private String dateTime;
	
	TSysLinkUserRole sysLinkUserRole;
	TSysLinkUserRoleExample sysLinkUserRoleExample;
	
	public List<String> getUserCodeList() {
		return UserCodeList;
	}

	public void setUserCodeList(List<String> userCodeList) {
		UserCodeList = userCodeList;
	}

	public TSysUserInfo getSysuserinfo() {
		return sysuserinfo;
	}

	public void setSysuserinfo(TSysUserInfo sysuserinfo) {
		this.sysuserinfo = sysuserinfo;
	}

	public List<TSysUserInfo> getSysUserinfoList() {
		return sysUserinfoList;
	}

	public void setSysUserinfoList(List<TSysUserInfo> sysUserinfoList) {
		this.sysUserinfoList = sysUserinfoList;
	}

	public String getCurrentUserCode() {
		return currentUserCode;
	}

	public void setCurrentUserCode(String currentUserCode) {
		this.currentUserCode = currentUserCode;
	}

	/**
	 * @return the sysUserInfoExample
	 */
	public TSysUserInfoExample getSysUserInfoExample() {
		return sysUserInfoExample;
	}

	/**
	 * @param sysUserInfoExample the sysUserInfoExample to set
	 */
	public void setSysUserInfoExample(TSysUserInfoExample sysUserInfoExample) {
		this.sysUserInfoExample = sysUserInfoExample;
	}

	/**
	 * @return the sysLinkUserRole
	 */
	public TSysLinkUserRole getSysLinkUserRole() {
		return sysLinkUserRole;
	}

	/**
	 * @param sysLinkUserRole the sysLinkUserRole to set
	 */
	public void setSysLinkUserRole(TSysLinkUserRole sysLinkUserRole) {
		this.sysLinkUserRole = sysLinkUserRole;
	}

	/**
	 * @return the sysLinkUserRoleExample
	 */
	public TSysLinkUserRoleExample getSysLinkUserRoleExample() {
		return sysLinkUserRoleExample;
	}

	/**
	 * @param sysLinkUserRoleExample the sysLinkUserRoleExample to set
	 */
	public void setSysLinkUserRoleExample(
			TSysLinkUserRoleExample sysLinkUserRoleExample) {
		this.sysLinkUserRoleExample = sysLinkUserRoleExample;
	}

	/**
	 * @return the dateTime
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the resultList
	 */
	public List<?> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList the resultList to set
	 */
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @return 返回 userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param 对userCode进行赋值
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
