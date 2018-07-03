package app.cn.qtt.bm.model;

import java.sql.Date;

public class OrderOperateBean {
	private String shopName;
	private String shopGoodsName;
	private Integer shopGoodsCount;
	private String userName;
	private Date createTime;
	private Date modifyTime;
	private String paidStatus;
	private Integer operateTimes;
	private String userPhoneNumber;
	private String department;
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopGoodsName() {
		return shopGoodsName;
	}
	public void setShopGoodsName(String shopGoodsName) {
		this.shopGoodsName = shopGoodsName;
	}
	public Integer getShopGoodsCount() {
		return shopGoodsCount;
	}
	public void setShopGoodsCount(Integer shopGoodsCount) {
		this.shopGoodsCount = shopGoodsCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}
	public Integer getOperateTimes() {
		return operateTimes;
	}
	public void setOperateTimes(Integer operateTimes) {
		this.operateTimes = operateTimes;
	}
}
