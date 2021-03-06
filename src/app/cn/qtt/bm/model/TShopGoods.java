package app.cn.qtt.bm.model;

import java.util.Date;

public class TShopGoods {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_goods_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Integer shopGoodsId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Integer shopId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_goods_name
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String shopGoodsName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_goods_desc
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String shopGoodsDesc;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_goods_price
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String shopGoodsPrice;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_goods_img_file_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Integer shopGoodsImgFileId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_goods_type
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String shopGoodsType;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.shop_goods_status
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String shopGoodsStatus;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.create_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String createUserCode;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.modify_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private String modifyUserCode;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.create_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Date createTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column t_shop_goods.modify_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    private Date modifyTime;
    
    
    private String categoryGoodsId;
    
    private String timeCode;
    
    private String temporaryFilePath;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_goods_id
     *
     * @return the value of t_shop_goods.shop_goods_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Integer getShopGoodsId() {
        return shopGoodsId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_goods_id
     *
     * @param shopGoodsId the value for t_shop_goods.shop_goods_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopGoodsId(Integer shopGoodsId) {
        this.shopGoodsId = shopGoodsId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_id
     *
     * @return the value of t_shop_goods.shop_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_id
     *
     * @param shopId the value for t_shop_goods.shop_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_goods_name
     *
     * @return the value of t_shop_goods.shop_goods_name
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getShopGoodsName() {
        return shopGoodsName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_goods_name
     *
     * @param shopGoodsName the value for t_shop_goods.shop_goods_name
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopGoodsName(String shopGoodsName) {
        this.shopGoodsName = shopGoodsName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_goods_desc
     *
     * @return the value of t_shop_goods.shop_goods_desc
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getShopGoodsDesc() {
        return shopGoodsDesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_goods_desc
     *
     * @param shopGoodsDesc the value for t_shop_goods.shop_goods_desc
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopGoodsDesc(String shopGoodsDesc) {
        this.shopGoodsDesc = shopGoodsDesc;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_goods_price
     *
     * @return the value of t_shop_goods.shop_goods_price
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getShopGoodsPrice() {
        return shopGoodsPrice;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_goods_price
     *
     * @param shopGoodsPrice the value for t_shop_goods.shop_goods_price
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopGoodsPrice(String shopGoodsPrice) {
        this.shopGoodsPrice = shopGoodsPrice;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_goods_img_file_id
     *
     * @return the value of t_shop_goods.shop_goods_img_file_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Integer getShopGoodsImgFileId() {
        return shopGoodsImgFileId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_goods_img_file_id
     *
     * @param shopGoodsImgFileId the value for t_shop_goods.shop_goods_img_file_id
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopGoodsImgFileId(Integer shopGoodsImgFileId) {
        this.shopGoodsImgFileId = shopGoodsImgFileId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_goods_type
     *
     * @return the value of t_shop_goods.shop_goods_type
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getShopGoodsType() {
        return shopGoodsType;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_goods_type
     *
     * @param shopGoodsType the value for t_shop_goods.shop_goods_type
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopGoodsType(String shopGoodsType) {
        this.shopGoodsType = shopGoodsType;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.shop_goods_status
     *
     * @return the value of t_shop_goods.shop_goods_status
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getShopGoodsStatus() {
        return shopGoodsStatus;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.shop_goods_status
     *
     * @param shopGoodsStatus the value for t_shop_goods.shop_goods_status
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setShopGoodsStatus(String shopGoodsStatus) {
        this.shopGoodsStatus = shopGoodsStatus;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.create_user_code
     *
     * @return the value of t_shop_goods.create_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.create_user_code
     *
     * @param createUserCode the value for t_shop_goods.create_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.modify_user_code
     *
     * @return the value of t_shop_goods.modify_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public String getModifyUserCode() {
        return modifyUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.modify_user_code
     *
     * @param modifyUserCode the value for t_shop_goods.modify_user_code
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setModifyUserCode(String modifyUserCode) {
        this.modifyUserCode = modifyUserCode;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.create_time
     *
     * @return the value of t_shop_goods.create_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.create_time
     *
     * @param createTime the value for t_shop_goods.create_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column t_shop_goods.modify_time
     *
     * @return the value of t_shop_goods.modify_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column t_shop_goods.modify_time
     *
     * @param modifyTime the value for t_shop_goods.modify_time
     *
     * @abatorgenerated Sat Jun 08 15:02:10 CST 2013
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public String getCategoryGoodsId() {
		return categoryGoodsId;
	}

	public void setCategoryGoodsId(String categoryGoodsId) {
		this.categoryGoodsId = categoryGoodsId;
	}

	public String getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(String timeCode) {
		this.timeCode = timeCode;
	}

	public String getTemporaryFilePath() {
		return temporaryFilePath;
	}

	public void setTemporaryFilePath(String temporaryFilePath) {
		this.temporaryFilePath = temporaryFilePath;
	}
    
    
}