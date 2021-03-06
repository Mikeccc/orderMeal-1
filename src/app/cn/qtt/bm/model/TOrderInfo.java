package app.cn.qtt.bm.model;

import java.util.Date;

public class TOrderInfo {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.order_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Integer orderId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.order_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String orderCode;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.order_name
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String orderName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.order_desc
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String orderDesc;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.order_run_status
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String orderRunStatus;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.order_goods_count
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String orderGoodsCount;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.order_price
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String orderPrice;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.create_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String createUserCode;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.modify_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String modifyUserCode;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.create_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Date createTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_order_info.modify_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Date modifyTime;
    /**
     * 订单详情信息对象
     * Added by GeYanmeng 2013-6-13
     */
    private TOrderInfoGoods orderInfoGoods;
    /**
     * 用户信息对象
     * Added by GeYanmeng 2013-6-17
     */
    private TSysUserInfo sysUserInfo;
    
    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.order_id
     *
     * @return the value of t_order_info.order_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.order_id
     *
     * @param orderId the value for t_order_info.order_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.order_code
     *
     * @return the value of t_order_info.order_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderCode() {
        return orderCode;
    }
    
    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.order_code
     *
     * @param orderCode the value for t_order_info.order_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.order_name
     *
     * @return the value of t_order_info.order_name
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.order_name
     *
     * @param orderName the value for t_order_info.order_name
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.order_desc
     *
     * @return the value of t_order_info.order_desc
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderDesc() {
        return orderDesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.order_desc
     *
     * @param orderDesc the value for t_order_info.order_desc
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.order_run_status
     *
     * @return the value of t_order_info.order_run_status
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderRunStatus() {
        return orderRunStatus;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.order_run_status
     *
     * @param orderRunStatus the value for t_order_info.order_run_status
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderRunStatus(String orderRunStatus) {
        this.orderRunStatus = orderRunStatus;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.order_goods_count
     *
     * @return the value of t_order_info.order_goods_count
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderGoodsCount() {
        return orderGoodsCount;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.order_goods_count
     *
     * @param orderGoodsCount the value for t_order_info.order_goods_count
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderGoodsCount(String orderGoodsCount) {
        this.orderGoodsCount = orderGoodsCount;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.order_price
     *
     * @return the value of t_order_info.order_price
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getOrderPrice() {
        return orderPrice;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.order_price
     *
     * @param orderPrice the value for t_order_info.order_price
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.create_user_code
     *
     * @return the value of t_order_info.create_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.create_user_code
     *
     * @param createUserCode the value for t_order_info.create_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.modify_user_code
     *
     * @return the value of t_order_info.modify_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getModifyUserCode() {
        return modifyUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.modify_user_code
     *
     * @param modifyUserCode the value for t_order_info.modify_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setModifyUserCode(String modifyUserCode) {
        this.modifyUserCode = modifyUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.create_time
     *
     * @return the value of t_order_info.create_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.create_time
     *
     * @param createTime the value for t_order_info.create_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_order_info.modify_time
     *
     * @return the value of t_order_info.modify_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_order_info.modify_time
     *
     * @param modifyTime the value for t_order_info.modify_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	/**
	 * @return the orderInfoGoods
	 */
	public TOrderInfoGoods getOrderInfoGoods() {
		return orderInfoGoods;
	}

	/**
	 * @param orderInfoGoods the orderInfoGoods to set
	 */
	public void setOrderInfoGoods(TOrderInfoGoods orderInfoGoods) {
		this.orderInfoGoods = orderInfoGoods;
	}

	/**
	 * @return the sysUserInfo
	 */
	public TSysUserInfo getSysUserInfo() {
		return sysUserInfo;
	}

	/**
	 * @param sysUserInfo the sysUserInfo to set
	 */
	public void setSysUserInfo(TSysUserInfo sysUserInfo) {
		this.sysUserInfo = sysUserInfo;
	}
}