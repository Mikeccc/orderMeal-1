package app.cn.qtt.bm.actions.order;


import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.impl.GoodsMgr;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TSysFile;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;

@ParentPackage("order-default")
@Namespace("/order")
public class GoodsDetailAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	

	private GoodsMgr goodsMgr;
	private TShopGoods shopGoods;
	private Integer shopGoodsId;
	private Integer categoryId;
	private GoodsResponseBean goodsResponseBean;
	private GoodsRequestBean goodsRequestBean;
	private TSysFile  sysFile;
	private Integer shopId;
	public Integer getShopId() {
		return shopId;
	}


	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}


	public String execute(){
		final String xFunctionName  = "GoodsDetailAction.execute()";
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
//			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
//					return LOGIN;
//			}
			
			shopGoods = new TShopGoods();
			shopGoods.setShopGoodsId(shopGoodsId);
			shopGoods.setShopGoodsStatus(Constants.SHOP_EFFECT_STATUS_00);//状态 00-生效 01-失效
			goodsResponseBean = goodsMgr.queryGoodsInfoById(shopGoods);
			
			if("00".equals(goodsResponseBean.getResponseCode())){
				shopGoods = goodsResponseBean.getShopGoods();
			}
			
			Integer imgFieldId = shopGoods.getShopGoodsImgFileId();
			TSysFile tempSysFile = new TSysFile();
			tempSysFile.setFileId(imgFieldId);
			goodsRequestBean = new GoodsRequestBean();
			goodsRequestBean.setSysFile(tempSysFile);
			GoodsResponseBean goodsResponseBean2 = goodsMgr.querySysFileByFileId(goodsRequestBean);
			sysFile = goodsResponseBean2.getSysFile();

		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return SUCCESS;
	}
	
	
	public GoodsMgr getGoodsMgr() {
		return goodsMgr;
	}

	public void setGoodsMgr(GoodsMgr goodsMgr) {
		this.goodsMgr = goodsMgr;
	}

	public TShopGoods getShopGoods() {
		return shopGoods;
	}

	public void setShopGoods(TShopGoods shopGoods) {
		this.shopGoods = shopGoods;
	}


	public Integer getShopGoodsId() {
		return shopGoodsId;
	}


	public void setShopGoodsId(Integer shopGoodsId) {
		this.shopGoodsId = shopGoodsId;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public GoodsResponseBean getGoodsResponseBean() {
		return goodsResponseBean;
	}


	public void setGoodsResponseBean(GoodsResponseBean goodsResponseBean) {
		this.goodsResponseBean = goodsResponseBean;
	}


	public GoodsRequestBean getGoodsRequestBean() {
		return goodsRequestBean;
	}


	public void setGoodsRequestBean(GoodsRequestBean goodsRequestBean) {
		this.goodsRequestBean = goodsRequestBean;
	}


	public TSysFile getSysFile() {
		return sysFile;
	}


	public void setSysFile(TSysFile sysFile) {
		this.sysFile = sysFile;
	}



}
