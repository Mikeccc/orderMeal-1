/**
 * 
 */
package app.cn.qtt.bm.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.utils.UtilTools;
import app.cn.qtt.bm.dao.TOrderInfoShopDAO;
import app.cn.qtt.bm.dao.TShopInfoDAO;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TOrderInfoShopExample;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TShopInfoExample;
import app.cn.qtt.bm.service.IShopService;
import app.cn.qtt.bm.service.pojo.ShopRequestBean;
import app.cn.qtt.bm.service.pojo.ShopResponseBean;

/**
 * @author GeYanmeng
 * @Description 店铺相关服务实现类
 * @date 2013-6-9 下午3:13:42
 * @type ShopServiceImpl
 * @project BespeakMeal
 */
public class ShopServiceImpl implements IShopService {
	
	/**
	 * 商店信息数据库访问对象
	 */
	private TShopInfoDAO shopInfoDAO;
	private TOrderInfoShopDAO tOrderInfoShopDAO;

	@Override
	public ShopResponseBean queryShops(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		TShopInfoExample example = null;

		List<TShopInfo> list = null;
		
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(null != requestBean.getShopInfoExample()){
				example = requestBean.getShopInfoExample();
			}else {
				example = new TShopInfoExample();
				TShopInfoExample.Criteria criteria=example.createCriteria();
				
				if(null != requestBean.getShopInfo().getShopId()){
					criteria.andShopIdEqualTo( requestBean.getShopInfo().getShopId());
				}
				if(StringUtils.isNotBlank(requestBean.getShopInfo().getShopStatus())){
					criteria.andShopStatusEqualTo(requestBean.getShopInfo().getShopStatus());
				}
				if(StringUtils.isNotBlank(requestBean.getShopInfo().getShopPhoneNumber())){
					criteria.andShopPhoneNumberEqualTo(requestBean.getShopInfo().getShopPhoneNumber());
				}
			}
			list = shopInfoDAO.selectByExample(example);

			responseBean.setResultList(list);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public ShopResponseBean addShopInfo(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo() != null){
				shopInfoDAO.insert(requestBean.getShopInfo());
			}else{
				throw new Exception("商店信息对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public ShopResponseBean modifyShopInfo(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo() != null){
				shopInfoDAO.updateByPrimaryKeySelective(requestBean.getShopInfo());
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
	public ShopResponseBean queryFuzzyPagesShops(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		TShopInfoExample example = new TShopInfoExample();
		List<TShopInfo> list = null;
		int totalRecords = 0;// 总记录数
		int totalPages = 0;// 总页数
		int pageRecords = 0;//每页的记录数
		int currentPage = 0;//当前页码
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if (requestBean.getShopInfoExample() != null) {
				example = requestBean.getShopInfoExample();
			}
			if (requestBean.getPageRecords() != 0) {
				pageRecords = requestBean.getPageRecords();// 如果传进每页记录数则使用
			} else {
				pageRecords = Constants.DEFAULT_PAGERECORDS;// 如果不传,则使用默认最大每页记录数
			}
			if (requestBean.getCurrentPage() != 0) {
				currentPage = requestBean.getCurrentPage();
			}
			list = shopInfoDAO.selectPagesByExample((currentPage - 1)
					* pageRecords, pageRecords, example);
			totalRecords = shopInfoDAO.countByExample(example);
			totalPages = UtilTools.getTotalPages(pageRecords, totalRecords);// 总页数
			if(totalPages == 0){
				totalPages = 1;
			}
			//若总页数小于当前页数（比如删除最后一页的最后一条记录），则重置当前页为最后一页
			if(totalPages < currentPage){
				currentPage = totalPages;
			}

			responseBean.setResultList(list);
			responseBean.setTotalPages(totalPages);
			responseBean.setTotalRecords(totalRecords);
			responseBean.setCurrentPage(currentPage);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	@Override
	public ShopResponseBean deleteShopInfo(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo() != null){
				shopInfoDAO.updateByPrimaryKeySelective(requestBean.getShopInfo());
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
	
	/*
	 * 物理删除
	 * @see app.cn.qtt.bm.service.IShopService#deleteShopInfoPermanent(app.cn.qtt.bm.service.pojo.ShopRequestBean)
	 */
	@Override
	public ShopResponseBean deleteShopInfoPermanently(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo() != null){
				shopInfoDAO.deleteByPrimaryKey(requestBean.getShopInfo().getShopId());
			}else{
				throw new Exception("商品信息对象不能为空");
			}
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}
	
	@Override
	public ShopResponseBean deleteShopInfoRecords(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopInfo() != null){
				if(null != requestBean.getShopInfo().getShopId()){
					shopInfoDAO.deleteByPrimaryKey(requestBean.getShopInfo().getShopId());
				}else{
					throw new Exception("shopId不能为空");
				}
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
	public ShopResponseBean selectOrderShopByOrderId(ShopRequestBean requestBean) {
		ShopResponseBean responseBean = new ShopResponseBean();
		List<TOrderInfoShop> oderInfoShopInfoList = null;
		try {
			if (requestBean == null) {
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderId() != null) {
				TOrderInfoShopExample example=new TOrderInfoShopExample();
				example.createCriteria().andOrderIdEqualTo(requestBean.getOrderId());
				oderInfoShopInfoList = (List<TOrderInfoShop>)tOrderInfoShopDAO.selectByExample(example);
			}else{
				throw new Exception("订单id不能为空");
			}
			
			responseBean.setResultList(oderInfoShopInfoList);
		} catch (Exception e) {
			responseBean.setErrMsg(e.getMessage());
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setException(e);
		}

		return responseBean;
	}

	/**
	 * @return the shopInfoDAO
	 */
	public TShopInfoDAO getShopInfoDAO() {
		return shopInfoDAO;
	}

	/**
	 * @param shopInfoDAO the shopInfoDAO to set
	 */
	public void setShopInfoDAO(TShopInfoDAO shopInfoDAO) {
		this.shopInfoDAO = shopInfoDAO;
	}

	/**
	 * @return the tOrderInfoShopDAO
	 */
	public TOrderInfoShopDAO gettOrderInfoShopDAO() {
		return tOrderInfoShopDAO;
	}

	/**
	 * @param tOrderInfoShopDAO the tOrderInfoShopDAO to set
	 */
	public void settOrderInfoShopDAO(TOrderInfoShopDAO tOrderInfoShopDAO) {
		this.tOrderInfoShopDAO = tOrderInfoShopDAO;
	}

}
