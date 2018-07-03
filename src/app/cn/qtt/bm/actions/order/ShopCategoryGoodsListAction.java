package app.cn.qtt.bm.actions.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.DateUtil;
import app.cn.qtt.bm.manage.impl.GoodsMgr;
import app.cn.qtt.bm.manage.impl.ShopMgr;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopGoodsTime;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;
import app.cn.qtt.bm.service.pojo.ShopRequestBean;
import app.cn.qtt.bm.service.pojo.ShopResponseBean;

@ParentPackage("order-default")
@Namespace("/order")
public class ShopCategoryGoodsListAction extends BaseAction{

	private static final long serialVersionUID = -3085618442896507824L;
	private ShopMgr shopMgr;
	private GoodsMgr goodsMgr;
	private TShopInfo shopInfo;
	private TShopCategoryGoods category;
	private TShopCategoryGoodsLink categoryLink;
	private TShopGoodsTime shopGoodsTime;
	
	private ShopRequestBean shopRequestBean;
	private ShopResponseBean shopResponseBean;
	private Integer shopId;
	private Integer orderId;//useful
	
	private List<TShopCategoryGoods> categoryList;
	
	private Integer categoryId;
	private GoodsRequestBean goodsRequestBean;
	private GoodsResponseBean goodsResponseBean;
	private List<TShopGoods> goodsList;
	
	private List<String> timeCodeList;
	private Integer shopGoodsId;
	
	
	public String execute(){
		final String xFunctionName  = "ShopCategoryGoodsListAction.execute()";
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getOrderUserInfoFromSession();
//			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
//					return LOGIN;
//			}
			
			//根据店铺ID查商品分类
			shopInfo = shopMgr.queryShopInfoById(shopId);
			GoodsResponseBean categoryResponseBean = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
				categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
			}
			
			if (categoryList != null && categoryList.size() > 0) {

				if (null != shopGoodsId) {

				}

				if (categoryId == null) {
					category = (TShopCategoryGoods) categoryResponseBean.getResultList().get(0);
					categoryId = category.getCategoryId();
				} else {
					category = new TShopCategoryGoods();
					category.setCategoryId(categoryId);
				}

				categoryLink = new TShopCategoryGoodsLink();
				categoryLink.setCategoryId(categoryId);

				shopGoodsTime = new TShopGoodsTime();
				// shopGoodsTime.setShopGoodsTimeId(1);
				DateUtil du = new DateUtil();
				int day = du.getDayOfWeek(new Date()) - 1;
				String timeCode;
				switch (day) {
				case 1:
					timeCode = Constants.TIME_CODE_MONDAY;
					break;
				case 2:
					timeCode = Constants.TIME_CODE_TUESDAY;
					break;
				case 3:
					timeCode = Constants.TIME_CODE_WEDNESDAY;
					break;
				case 4:
					timeCode = Constants.TIME_CODE_THURSDAY;
					break;
				case 5:
					timeCode = Constants.TIME_CODE_FRIDAY;
					break;
				default:
					timeCode = Constants.TIME_CODE_EVERYDAY;
					;
				}
				timeCodeList = new ArrayList<String>();
				timeCodeList.add(0, timeCode);
				timeCodeList.add(1, Constants.TIME_CODE_EVERYDAY);

				// 根据店铺和分类查商品
				goodsRequestBean = new GoodsRequestBean();
				goodsRequestBean.setShopInfo(shopInfo);
				goodsRequestBean.setShopCategoryGoods(category);
				goodsRequestBean.setShopCategoryGoodsLink(categoryLink);
				goodsRequestBean.setTimeCodeList(timeCodeList);
				goodsResponseBean = goodsMgr.queryGoodsByCategory(goodsRequestBean);
				if (goodsResponseBean != null && goodsResponseBean.getResultList() != null) {
					goodsList = (List<TShopGoods>) goodsResponseBean.getResultList();
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
	

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public TShopCategoryGoods getCategory() {
		return category;
	}

	public void setCategory(TShopCategoryGoods category) {
		this.category = category;
	}



	public GoodsMgr getGoodsMgr() {
		return goodsMgr;
	}



	public void setGoodsMgr(GoodsMgr goodsMgr) {
		this.goodsMgr = goodsMgr;
	}



	public List<TShopCategoryGoods> getCategoryList() {
		return categoryList;
	}



	public void setCategoryList(List<TShopCategoryGoods> categoryList) {
		this.categoryList = categoryList;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public GoodsRequestBean getGoodsRequestBean() {
		return goodsRequestBean;
	}

	public void setGoodsRequestBean(GoodsRequestBean goodsRequestBean) {
		this.goodsRequestBean = goodsRequestBean;
	}

	public GoodsResponseBean getGoodsResponseBean() {
		return goodsResponseBean;
	}

	public void setGoodsResponseBean(GoodsResponseBean goodsResponseBean) {
		this.goodsResponseBean = goodsResponseBean;
	}

	public List<TShopGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<TShopGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public TShopCategoryGoodsLink getCategoryLink() {
		return categoryLink;
	}

	public void setCategoryLink(TShopCategoryGoodsLink categoryLink) {
		this.categoryLink = categoryLink;
	}

	public TShopGoodsTime getShopGoodsTime() {
		return shopGoodsTime;
	}

	public void setShopGoodsTime(TShopGoodsTime shopGoodsTime) {
		this.shopGoodsTime = shopGoodsTime;
	}



	public Integer getOrderId() {
		return orderId;
	}



	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}



	public List<String> getTimeCodeList() {
		return timeCodeList;
	}



	public void setTimeCodeList(List<String> timeCodeList) {
		this.timeCodeList = timeCodeList;
	}



	public Integer getShopGoodsId() {
		return shopGoodsId;
	}



	public void setShopGoodsId(Integer shopGoodsId) {
		this.shopGoodsId = shopGoodsId;
	}
	
}
