/**
 * 
 */
package app.cn.qtt.bm.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.utils.UtilTools;
import app.cn.qtt.bm.dao.TOrderInfoGoodsDAO;
import app.cn.qtt.bm.dao.TShopCategoryGoodsDAO;
import app.cn.qtt.bm.dao.TShopCategoryGoodsLinkDAO;
import app.cn.qtt.bm.dao.TShopGoodsDAO;
import app.cn.qtt.bm.dao.TShopGoodsTimeDAO;
import app.cn.qtt.bm.model.NewTShopGoods;
import app.cn.qtt.bm.model.TOrderInfoGoods;
import app.cn.qtt.bm.model.TOrderInfoGoodsExample;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopCategoryGoodsExample;
import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopCategoryGoodsLinkExample;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopGoodsExample;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;

/**
 * @author GeYanmeng
 * @Description 商品信息服务接口实现类
 * @date 2013-6-9 下午4:14:58
 * @type GoodsServiceImpl
 * @project BespeakMeal
 */
public class GoodsServiceImpl extends CommonServiceImpl implements IGoodsService {
	/**
	 * 商品信息数据访问对象
	 */
	private TShopGoodsDAO shopGoodsDAO;
	/**
	 * 商品信息分类数据访问对象
	 */
	private TShopCategoryGoodsDAO shopCategoryGoodsDAO;
	/**
	 * 商品分类、商品信息关联数据访问对象
	 */
	TShopCategoryGoodsLinkDAO shopCategoryGoodsLinkDAO;
	/**
	 * 订单商品信息数据访问对象
	 */
	TOrderInfoGoodsDAO orderInfoGoodsDAO;
	/**
	 * 商品供应时间
	 */
	TShopGoodsTimeDAO shopGoodsTimeDAO;
	

	@Override
	public GoodsResponseBean queryGoodsCategoryByShopId(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopCategoryGoodsExample example = new TShopCategoryGoodsExample();
		List<TShopCategoryGoods> resultList  = null;
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo().getShopId() != null){
				example.createCriteria().andShopIdEqualTo(requestBean.getShopInfo().getShopId());
				resultList = shopCategoryGoodsDAO.selectByExample(example);
			}else{
				throw new Exception("商店信息id不能为空");
			}

			responseBean.setResultList(resultList);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public GoodsResponseBean queryGoodsByCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopGoodsExample example = new TShopGoodsExample();
		List<NewTShopGoods> list = null;
		int totalRecords = 0;// 总记录数
		int totalPages = 1;// 总页数
		int pageRecords = 0;//每页的记录数
		int currentPage = 1;//当前页码
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if (requestBean.getShopGoodsExample() != null) {
				example = requestBean.getShopGoodsExample();
			}
			if (null !=requestBean.getPageRecords() && requestBean.getPageRecords() != 0) {
				pageRecords = requestBean.getPageRecords();// 如果传进每页记录数则使用
			} else {
				totalRecords = shopGoodsDAO.countTotalRecordsByExample(requestBean.getShopCategoryGoods().getCategoryId(),
						requestBean.getTimeCodeList(),requestBean.getShopInfo().getShopId(),example);
				pageRecords = totalRecords;
			}
			
			if (null != requestBean.getCurrentPage() && requestBean.getCurrentPage() != 0) {
				currentPage = requestBean.getCurrentPage();
			}
			list = shopGoodsDAO.selectPagesByExample((currentPage - 1)*pageRecords, 
												      pageRecords,
												      requestBean.getShopCategoryGoods().getCategoryId(),
												      requestBean.getTimeCodeList(),
												      requestBean.getShopInfo().getShopId(),
												      example);
			totalRecords = shopGoodsDAO.countTotalRecordsByExample(requestBean.getShopCategoryGoods().getCategoryId(),
					requestBean.getTimeCodeList(),requestBean.getShopInfo().getShopId(),example);
			if(totalRecords != 0){
				totalPages = UtilTools.getTotalPages(pageRecords, totalRecords);// 总页数
			}
			if(totalPages ==0){
				totalPages = 1;
			}
			//若总页数小于当前页数（比如删除最后一页的最后一条记录），则重置当前页为最后一页
			if(totalPages < currentPage){
				currentPage = totalPages;
			}

			responseBean.setResultList(list);
			responseBean.setCurrentPage(currentPage);
			responseBean.setTotalPages(totalPages);
			responseBean.setTotalRecords(totalRecords);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean addGoodsCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			shopCategoryGoodsDAO.insert(requestBean.getShopCategoryGoods());
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean deleteGoodsCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopCategoryGoods().getCategoryId() != null){
				shopCategoryGoodsDAO.deleteByPrimaryKey(requestBean.getShopCategoryGoods().getCategoryId());
			}else{
				throw new Exception("商品分类id不能为空");
			}
			
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean addGoodsInfo(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		int shopGoodsId = -1;
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopGoods() != null){
				shopGoodsId = shopGoodsDAO.insert(requestBean.getShopGoods());
			}else{
				throw new Exception("商品信息对象不能为空");
			}
			responseBean.setShopGoodsId(shopGoodsId);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean addLinkGoodsCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopCategoryGoodsLink() != null){
				shopCategoryGoodsLinkDAO.insert(requestBean.getShopCategoryGoodsLink());
			}else{
				throw new Exception("商品分组-商品关联对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean deleteGoodsInfo(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopGoods() != null ){
				shopGoodsDAO.updateByPrimaryKeySelective(requestBean.getShopGoods());
			}else{
				throw new Exception("商店信息对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	@Override
	public GoodsResponseBean deleteGoodsInfoPhysical(
			GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopGoods() != null ){
				shopGoodsDAO.deleteByPrimaryKey(requestBean.getShopGoods().getShopGoodsId());
			}else{
				throw new Exception("商店信息对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean deleteLinkGoodsCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopCategoryGoodsLinkExample example = new TShopCategoryGoodsLinkExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if (requestBean.getShopCategoryGoodsLinkExample() != null) {
				example = requestBean.getShopCategoryGoodsLinkExample();
			}
			shopCategoryGoodsLinkDAO.deleteByExample(example);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean queryGoodsInfoById(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopGoods shopGoods = null;
		TShopGoodsExample example = new TShopGoodsExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if (requestBean.getShopGoods().getShopGoodsId() != null) {
				example.createCriteria().andShopGoodsIdEqualTo(requestBean.getShopGoods().getShopGoodsId())
						.andShopGoodsStatusEqualTo(requestBean.getShopGoods().getShopGoodsStatus());
				List<TShopGoods> tempList = shopGoodsDAO.selectByExample(example);
				if(tempList.size() != 0){
					shopGoods = tempList.get(0);
				}
				
				responseBean.setShopGoods(shopGoods);
			}else{
				throw new Exception("商品信息id不能为空");
			}
			
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	public GoodsResponseBean queryImgGoodsInfoById(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		NewTShopGoods shopGoods = null;
		TShopGoodsExample example = new TShopGoodsExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if (requestBean.getShopGoods().getShopGoodsId() != null) {
				example.createCriteria().andShopGoodsIdEqualTo(requestBean.getShopGoods().getShopGoodsId())
						.andShopGoodsStatusEqualTo(requestBean.getShopGoods().getShopGoodsStatus());
				List<NewTShopGoods> tempList = shopGoodsDAO.selectImageByExample(example);
				if(tempList.size() != 0){
					shopGoods = tempList.get(0);
				}
				
				responseBean.setNewShopGoods(shopGoods);
			}else{
				throw new Exception("商品信息id不能为空");
			}
			
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	/**
	 * 
	* @Title: queryGoodsInfoByGoodsName
	* @Description:  根据菜单名查菜单
	* @param @param requestBean
	* @param @return    设定文件
	* @return GoodsResponseBean    返回类型
	* @throws
	 */
	public GoodsResponseBean queryGoodsInfoByGoodsName(GoodsRequestBean requestBean){
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopGoods shopGoods = null;
		TShopGoodsExample example = new TShopGoodsExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo()==null){
				throw new Exception("商店信息为空");
			}
			if(requestBean.getShopInfo().getShopId()==null){
				throw new Exception("商店Id为空");
			}
			if(requestBean.getShopGoods()== null){
				throw new Exception("商品信息对象为空");
			}
			if(StringUtils.isNotBlank(requestBean.getShopGoods().getShopGoodsName())){
				example.createCriteria().andShopIdEqualTo(requestBean.getShopInfo().getShopId())
				.andShopGoodsNameEqualTo(requestBean.getShopGoods().getShopGoodsName()).andShopGoodsStatusEqualTo(requestBean.getShopGoods().getShopGoodsStatus());
				List<TShopGoods> tempList = shopGoodsDAO.selectByExample(example);
				responseBean.setResultList(tempList);
			}else{
				throw new Exception("商品名不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	/**
	 * 
	* @Title: queryGoodsCategoryByCategoryName
	* @Description:  根据分组名查分组
	* @param @param requestBean
	* @param @return    设定文件
	* @return GoodsResponseBean    返回类型
	* @throws
	 */
	public GoodsResponseBean queryGoodsCategoryByCategoryName(GoodsRequestBean requestBean){
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopGoods shopGoods = null;
		TShopCategoryGoodsExample example = new TShopCategoryGoodsExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo()==null){
				throw new Exception("商店信息为空");
			}
			if(requestBean.getShopInfo().getShopId()==null){
				throw new Exception("商店Id为空");
			}
			if(requestBean.getShopCategoryGoods()== null){
				throw new Exception("分组对象为空");
			}
			if(StringUtils.isNotBlank(requestBean.getShopCategoryGoods().getCategoryName())){
				example.createCriteria().andShopIdEqualTo(requestBean.getShopInfo().getShopId())
				.andCategoryNameEqualTo(requestBean.getShopCategoryGoods().getCategoryName());
				List<TShopCategoryGoods> tempList = shopCategoryGoodsDAO.selectByExample(example);
				responseBean.setResultList(tempList);
			}else{
				throw new Exception("商品名不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	@Override
	public GoodsResponseBean modifyGoodsInfo(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopGoods()!= null){
				shopGoodsDAO.updateByPrimaryKeySelective(requestBean.getShopGoods());
			}else{
				throw new Exception("商品信息对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public GoodsResponseBean modifyLinkGoodsCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopCategoryGoodsLinkExample example = new TShopCategoryGoodsLinkExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopCategoryGoodsLink() != null){
				example.createCriteria().andShopGoodsIdEqualTo(requestBean.getShopCategoryGoodsLink().getShopGoodsId());
				
				shopCategoryGoodsLinkDAO.updateByExampleSelective(requestBean.getShopCategoryGoodsLink(), example);
			}else{
				throw new Exception("ShopCategoryGoodsLink对象不能为空");
			}
			
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	@Override
	public GoodsResponseBean selectShopGoodsInfoByOrderId(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TOrderInfoGoodsExample example = new TOrderInfoGoodsExample();
		List<TOrderInfoGoods> orderInfoGoodsList = null;
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderId() != null){
				example.createCriteria().andOrderIdEqualTo(requestBean.getOrderId());
				orderInfoGoodsList = (List<TOrderInfoGoods>)orderInfoGoodsDAO.selectByExample(example);
			}else{
				throw new Exception("订单id不能为空");
			}

			responseBean.setResultList(orderInfoGoodsList);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public GoodsResponseBean queryLinkGoodsCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopCategoryGoodsLinkExample example = new TShopCategoryGoodsLinkExample();
		List<TShopCategoryGoodsLink> resultList = null;
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if (requestBean.getShopCategoryGoodsLinkExample() != null) {
				example = requestBean.getShopCategoryGoodsLinkExample();
			}
			resultList = shopCategoryGoodsLinkDAO.selectByExample(example);

			responseBean.setResultList(resultList);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public GoodsResponseBean modifyGoodsCategory(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopCategoryGoodsExample example = new TShopCategoryGoodsExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			
			if(requestBean.getShopCategoryGoods() != null){
				example.createCriteria().andCategoryIdEqualTo(requestBean.getShopCategoryGoods().getCategoryId());
				shopCategoryGoodsDAO.updateByExampleSelective(requestBean.getShopCategoryGoods(), example);
			}else{
				throw new Exception("ShopCategoryGoods对象为空");
			}
			
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	@Override
	public GoodsResponseBean addShopGoodsTime(GoodsRequestBean requestBean) {
		GoodsResponseBean responseBean = new GoodsResponseBean();
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopGoodsTime() != null){
				shopGoodsTimeDAO.insert(requestBean.getShopGoodsTime());
			}else{
				throw new Exception("ShopGoodsTime对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	public GoodsResponseBean queryGoodsCategoryCountNumByShopId(GoodsRequestBean requestBean){
		GoodsResponseBean responseBean = new GoodsResponseBean();
		TShopCategoryGoodsExample example = new TShopCategoryGoodsExample();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo()==null){
				throw new Exception("请求商店对象为空");
			}
			if(requestBean.getShopInfo().getShopId() != null){
				example.createCriteria().andShopIdEqualTo(requestBean.getShopInfo().getShopId());
				int count = shopCategoryGoodsDAO.countByExample(example);
				responseBean.setCategoryCountNum(count);
			}else{
				throw new Exception("shopID不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;

	}
	
	/**
	 * @return the shopGoodsDAO
	 */
	public TShopGoodsDAO getShopGoodsDAO() {
		return shopGoodsDAO;
	}

	/**
	 * @param shopGoodsDAO the shopGoodsDAO to set
	 */
	public void setShopGoodsDAO(TShopGoodsDAO shopGoodsDAO) {
		this.shopGoodsDAO = shopGoodsDAO;
	}

	/**
	 * @return the shopCategoryGoodsDAO
	 */
	public TShopCategoryGoodsDAO getShopCategoryGoodsDAO() {
		return shopCategoryGoodsDAO;
	}

	/**
	 * @param shopCategoryGoodsDAO the shopCategoryGoodsDAO to set
	 */
	public void setShopCategoryGoodsDAO(TShopCategoryGoodsDAO shopCategoryGoodsDAO) {
		this.shopCategoryGoodsDAO = shopCategoryGoodsDAO;
	}

	/**
	 * @return the shopCategoryGoodsLinkDAO
	 */
	public TShopCategoryGoodsLinkDAO getShopCategoryGoodsLinkDAO() {
		return shopCategoryGoodsLinkDAO;
	}

	/**
	 * @param shopCategoryGoodsLinkDAO the shopCategoryGoodsLinkDAO to set
	 */
	public void setShopCategoryGoodsLinkDAO(
			TShopCategoryGoodsLinkDAO shopCategoryGoodsLinkDAO) {
		this.shopCategoryGoodsLinkDAO = shopCategoryGoodsLinkDAO;
	}

	/**
	 * @return the orderInfoGoodsDAO
	 */
	public TOrderInfoGoodsDAO getOrderInfoGoodsDAO() {
		return orderInfoGoodsDAO;
	}

	/**
	 * @param orderInfoGoodsDAO the orderInfoGoodsDAO to set
	 */
	public void setOrderInfoGoodsDAO(TOrderInfoGoodsDAO orderInfoGoodsDAO) {
		this.orderInfoGoodsDAO = orderInfoGoodsDAO;
	}

	/**
	 * @return the shopGoodsTimeDAO
	 */
	public TShopGoodsTimeDAO getShopGoodsTimeDAO() {
		return shopGoodsTimeDAO;
	}

	/**
	 * @param shopGoodsTimeDAO the shopGoodsTimeDAO to set
	 */
	public void setShopGoodsTimeDAO(TShopGoodsTimeDAO shopGoodsTimeDAO) {
		this.shopGoodsTimeDAO = shopGoodsTimeDAO;
	}

	
	
}
