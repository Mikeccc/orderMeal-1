package app.cn.qtt.bm.service.pojo;

import java.util.ArrayList;
import java.util.List;

import app.cn.qtt.bm.model.TSysUserInfo;


/**
 * @title 用户操作请求结果（响应）对象
 * @descriptor
 * @author 纪竣锋
 * @since 2013-3-17
 * @version
 */
public class UserResponseBean extends ResponseBean {
	private List userlist = new ArrayList();
	
	private int currentPage;//当前页
	
	TSysUserInfo sysuserinfo;
	private int dbReturnValue;

	/**
	 * 用户手机号码
	 */
	private String userPhoneNumber;
	
	
	public List getUserlist() {
		return userlist;
	}

	public void setUserlist(List userlist) {
		this.userlist = userlist;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the sysuserinfo
	 */
	public TSysUserInfo getSysuserinfo() {
		return sysuserinfo;
	}

	/**
	 * @param sysuserinfo the sysuserinfo to set
	 */
	public void setSysuserinfo(TSysUserInfo sysuserinfo) {
		this.sysuserinfo = sysuserinfo;
	}

	/**
	 * @return 返回 dbReturnValue
	 */
	public int getDbReturnValue() {
		return dbReturnValue;
	}

	/**
	 * @param 对dbReturnValue进行赋值
	 */
	public void setDbReturnValue(int dbReturnValue) {
		this.dbReturnValue = dbReturnValue;
	}

	/**
	 * @return 返回 userPhoneNumber
	 */
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	/**
	 * @param 对userPhoneNumber进行赋值
	 */
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

}
