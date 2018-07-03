package app.cn.qtt.bm.actions.order;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.impl.ShopMgr;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.service.pojo.ShopRequestBean;
import app.cn.qtt.bm.service.pojo.ShopResponseBean;

@ParentPackage("order-default")
@Namespace("/order")
@Results({
    @Result(name = "shopCategoryGoodsList",type="redirectAction",location="shop-category-goods-list",params={"shopId","${shopId}"})
})
public class ShopListAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	private ShopMgr shopMgr;
	private TShopInfo shopInfo;
	private ShopRequestBean shopRequestBean;
	private ShopResponseBean shopResponseBean;
	private int shopId;
	
	public String execute(){
		final String xFunctionName  = "ShopListAction.execute()";
		TShopInfo tShopInfo = new TShopInfo();
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
//			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
//					return LOGIN;
//			}
			
			tShopInfo.setShopStatus(Constants.SHOP_EFFECT_STATUS_00);//状态 00-生效 01-失效
			shopResponseBean = shopMgr.selectShops(tShopInfo);
			List shopList = shopResponseBean.getResultList();
			if(CollectionUtils.isNotEmpty(shopList)){
				int shopListSize = shopList.size();
				if(shopListSize == 1){
					TShopInfo shopInfo = (TShopInfo)shopList.get(0);
					shopId = shopInfo.getShopId();
					return "shopCategoryGoodsList";
				}
			}
			
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return SUCCESS;
	}
	
	
	public ShopResponseBean getShopResponseBean() {
		return shopResponseBean;
	}
	public void setShopResponseBean(ShopResponseBean shopResponseBean) {
		this.shopResponseBean = shopResponseBean;
	}
	public ShopMgr getShopMgr() {
		return shopMgr;
	}
	public void setShopMgr(ShopMgr shopMgr) {
		this.shopMgr = shopMgr;
	}
	public ShopRequestBean getShopRequestBean() {
		return shopRequestBean;
	}
	public void setShopRequestBean(ShopRequestBean shopRequestBean) {
		this.shopRequestBean = shopRequestBean;
	}
	public TShopInfo getShopInfo() {
		return shopInfo;
	}
	public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}


	public int getShopId() {
		return shopId;
	}


	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
}
