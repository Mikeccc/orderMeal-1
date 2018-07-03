/**
 * 
 */
package app.cn.qtt.bm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.dao.TOrderInfoDAO;
import app.cn.qtt.bm.dao.TOrderInfoGoodsDAO;
import app.cn.qtt.bm.dao.TOrderInfoShopDAO;
import app.cn.qtt.bm.dao.TShopDailyInfoDAO;
import app.cn.qtt.bm.model.CountAndPrice;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.OrderOperateBean;
import app.cn.qtt.bm.model.ShopOrderdailyStatistics;
import app.cn.qtt.bm.model.SmsContext;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoExample;
import app.cn.qtt.bm.model.TOrderInfoGoods;
import app.cn.qtt.bm.model.TOrderInfoGoodsExample;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TOrderInfoShopExample;
import app.cn.qtt.bm.model.TShopDailyInfo;
import app.cn.qtt.bm.model.TShopDailyInfoExample;
import app.cn.qtt.bm.service.IOrderService;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

/**
 * @author GeYanmeng
 * @Description 订单管理实现类
 * @date 2013-6-9 下午4:35:02
 * @type OrderServiceImpl
 * @project BespeakMeal
 */
public class OrderServiceImpl implements IOrderService {
	
	/**
	 * 订单信息数据访问对象
	 */
	private TOrderInfoDAO orderInfoDAO;
	/**
	 * 订单商品信息详情数据访问对象
	 */
	private TOrderInfoGoodsDAO orderInfoGoodsDAO;
	/**
	 * 订单店铺详情数据访问对象
	 */
	private TOrderInfoShopDAO orderInfoShopDAO;
	/**
	 * 发送给商家信息
	 */
	private TShopDailyInfoDAO shopDailyInfoDAO;
	
	@Override
	public OrderResponseBean queryOrders(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TOrderInfoExample example = new TOrderInfoExample();
		List<TOrderInfo> list = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderInfoExample() != null) {
				example = requestBean.getOrderInfoExample();
			}
			list = orderInfoDAO.selectByExample(example);
			responseBean.setResultList(list);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean addOrderInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfo() != null){
				orderInfoDAO.insert(requestBean.getOrderInfo());
			}else{
				throw new Exception("订单对象不能为空");
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean addOrderShopInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfoShop() != null){
				orderInfoShopDAO.insert(requestBean.getOrderInfoShop());
			}else{
				throw new Exception("订单店铺详情对象不能为空");
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean addOrderGoodsInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfoGoods() != null){
				orderInfoGoodsDAO.insert(requestBean.getOrderInfoGoods());
			}else{
				throw new Exception("订单商品详情对象不能为空");
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean modifyOrderInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfo() != null){
				orderInfoDAO.updateByPrimaryKeySelective(requestBean.getOrderInfo());
			}else{
				throw new Exception("订单对象不能为空");
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean modifyOrderShopInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TOrderInfoShopExample example = new TOrderInfoShopExample();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderInfoShopExample() != null) {
				example = requestBean.getOrderInfoShopExample();
			}
			if(requestBean.getOrderInfoShop() != null){
				orderInfoShopDAO.updateByExampleSelective(requestBean.getOrderInfoShop(), example);
			}else{
				throw new Exception("订单店铺详情对象不能为空");
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean modifyOrderGoodsInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfoGoods() != null){
				orderInfoGoodsDAO.updateByPrimaryKeySelective(requestBean.getOrderInfoGoods());
			}else{
				throw new Exception("订单商品详情对象不能为空");
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean modifyOrderRunStatus(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			TOrderInfo orderInfo = requestBean.getOrderInfo();
			if(orderInfo != null){
				int returnValue = orderInfoDAO.updateByPrimaryKeySelective(orderInfo);
				responseBean.setUpdateReturnValue(returnValue);
			}else{
				throw new Exception("订单对象不能为空");
			}
			
			responseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	/**
	 * @see app.cn.qtt.bm.service.IOrderService#modifyOrderRunStatusForJob(app.cn.qtt.bm.service.pojo.OrderRequestBean)
	 * @param requestBean
	 * @return
	 * @author Gabriel
	 * @createtime 2013-11-13 上午11:33:42
	 */
	@Override
	public OrderResponseBean modifyOrderRunStatusForJob(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try {
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfo() == null){
				throw new Exception("订单对象不能为空");
			}
			
			TOrderInfo orderInfo = new TOrderInfo();
			orderInfo.setCreateTime(requestBean.getOrderInfo().getCreateTime());
			orderInfo.setCreateUserCode(requestBean.getOrderInfo().getCreateUserCode());
			orderInfo.setModifyTime(requestBean.getOrderInfo().getModifyTime());
			orderInfo.setModifyUserCode(requestBean.getOrderInfo().getModifyUserCode());
			orderInfo.setOrderCode(requestBean.getOrderInfo().getOrderCode());
			orderInfo.setOrderDesc(requestBean.getOrderInfo().getOrderDesc());
			orderInfo.setOrderGoodsCount(requestBean.getOrderInfo().getOrderGoodsCount());
			orderInfo.setOrderInfoGoods(requestBean.getOrderInfo().getOrderInfoGoods());
			orderInfo.setOrderName(requestBean.getOrderInfo().getOrderName());
			orderInfo.setOrderPrice(requestBean.getOrderInfo().getOrderPrice());
			orderInfo.setOrderRunStatus(requestBean.getOrderInfo().getOrderRunStatus());
			orderInfo.setSysUserInfo(requestBean.getOrderInfo().getSysUserInfo());
			
			if(StringUtils.isBlank(requestBean.getOrderRunStatus())){
				throw new Exception("订单状态不能为空");
			}
			TOrderInfoExample example = new TOrderInfoExample();
			example.createCriteria().andOrderCodeEqualTo(requestBean.getOrderInfo().getOrderCode())
					.andOrderRunStatusEqualTo(requestBean.getOrderRunStatus());

			int returnValue = orderInfoDAO.updateByExampleSelective(orderInfo, example);

			responseBean.setDbReturnValue(returnValue);
			responseBean.setResponseCode(Constants.UPDATE_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean deleteOrder(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfo() != null){
				orderInfoDAO.deleteByPrimaryKey(requestBean.getOrderInfo().getOrderId());
			}else{
				throw new Exception("订单对象不能为空");
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean deleteOrderShopInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TOrderInfoShopExample example = new TOrderInfoShopExample();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderInfoShopExample() != null) {
				example = requestBean.getOrderInfoShopExample();
			}
			if(requestBean.getOrderInfoShop()!=null){
				if(null != requestBean.getOrderInfoShop().getOrderId() && null ==requestBean.getOrderInfoShop().getShopId() )
				{
					example.createCriteria().andOrderIdEqualTo(requestBean.getOrderInfoShop().getOrderId());
				}
				if(null != requestBean.getOrderInfoShop().getOrderId()&&null != requestBean.getOrderInfoShop().getShopId()){
					example.createCriteria().andOrderIdEqualTo(requestBean.getOrderInfoShop().getOrderId())
														.andShopIdEqualTo(requestBean.getOrderInfoShop().getShopId());
				}
			}
			orderInfoShopDAO.deleteByExample(example);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean deleteOrderGoodsInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TOrderInfoGoodsExample example = new TOrderInfoGoodsExample();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderInfoGoodsExample() != null) {
				example = requestBean.getOrderInfoGoodsExample();
			}
			if (requestBean.getOrderInfoGoods() != null) {
				example.createCriteria().andOrderIdEqualTo(requestBean.getOrderInfoGoods().getOrderId())
						.andShopIdEqualTo(requestBean.getOrderInfoGoods().getShopId())
						.andShopGoodsIdEqualTo(requestBean.getOrderInfoGoods().getShopGoodsId());
				
				orderInfoGoodsDAO.deleteByExample(example);
			}else{
				throw new Exception("OrderInfoGoods对象不能为空");
			}
			
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.DELETE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean queryDailyOrderInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TShopDailyInfoExample example = new TShopDailyInfoExample();
		List<TShopDailyInfo> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getShopDailyInfoExample() != null) {
				example = requestBean.getShopDailyInfoExample();
			}
			
			resultList = shopDailyInfoDAO.selectByExample(example);
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean queryOrderInfoShop(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TOrderInfoShopExample example = new TOrderInfoShopExample();
		List<TOrderInfoShop> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			
			if (requestBean.getOrderInfoShop() != null) {
				if(null != requestBean.getOrderInfoShop().getShopId()){
					example.createCriteria()
					.andOrderIdEqualTo(
							requestBean.getOrderInfoShop().getOrderId())
					.andShopIdEqualTo(requestBean.getOrderInfoShop().getShopId());
					
					resultList = orderInfoShopDAO.selectByExample(example);
				}else{
					example.createCriteria()
					.andOrderIdEqualTo(
							requestBean.getOrderInfoShop().getOrderId());
					resultList = orderInfoShopDAO.selectByExample(example);
				}
				
			}else{
				throw new Exception("OrderInfoShop对象为空");
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean queryOrderInfoGoods(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TOrderInfoGoodsExample example = new TOrderInfoGoodsExample();
		List<TOrderInfoGoods> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(null != requestBean.getOrderInfoGoods().getShopGoodsId()){
				example.createCriteria().andOrderIdEqualTo(requestBean.getOrderInfoGoods().getOrderId())
						.andShopIdEqualTo(requestBean.getOrderInfoGoods().getShopId())
						.andShopGoodsIdEqualTo(requestBean.getOrderInfoGoods().getShopGoodsId());
				resultList = orderInfoGoodsDAO.selectByExample(example);
			}else if(requestBean.getOrderInfoGoods() != null){
				example.createCriteria().andOrderIdEqualTo(requestBean.getOrderInfoGoods().getOrderId())
						.andShopIdEqualTo(requestBean.getOrderInfoGoods().getShopId());
	
				resultList = orderInfoGoodsDAO.selectByExample(example);
			}else{
				throw new Exception("OrderInfoGoods对象为空");
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean queryShopOrderDailyStatistics(
			OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<ShopOrderdailyStatistics> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopOrderdailyStatistics() != null){
				resultList = orderInfoDAO.selectShopOrderDailyStatistics(requestBean.getShopOrderdailyStatistics());
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean updateShopDailyInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			TShopDailyInfo shopDailyInfo = requestBean.getShopDailyInfo();
			if(shopDailyInfo != null){
				int returnValue = shopDailyInfoDAO.updateByPrimaryKeySelective(shopDailyInfo);
				responseBean.setUpdateReturnValue(returnValue);
			}else{
				throw new Exception("TShopDailyInfo对象不能为空");
			}
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean queryMyOrder(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<MyOrder> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getMyOrder() != null){
				resultList = orderInfoDAO.selectMyOrderInfo(requestBean.getMyOrder());
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	public OrderResponseBean queryMyOrder2(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<MyOrder> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getMyOrder() != null){
				resultList = orderInfoDAO.selectMyOrderInfo2(requestBean.getMyOrder());
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	public OrderResponseBean queryMyOrderByUserCodeAndTime(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<MyOrder> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getMyOrder() != null){
				resultList = orderInfoDAO.selectMyOrderByUserCodeAndTime(requestBean.getMyOrder());
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	
	@Override
	public OrderResponseBean getOrderShopPrice(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List result = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderInfoShop() != null) {
				result = orderInfoShopDAO.calculatePriceAndAmount(requestBean.getOrderInfoShop());
			}else{
				throw new Exception("OrderInfoShop对象为空");
			}
			if(!CollectionUtils.isEmpty(result)){
				CountAndPrice countAndPrice = (CountAndPrice) result.get(0);
				responseBean.setPrice(countAndPrice.getPrice());
				responseBean.setOrderGoodsPrice(countAndPrice.getPrice());
				responseBean.setOrderGoodsCount(countAndPrice.getCount());
			}
			
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean queryShopOrderDetail(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<SmsContext> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderInfoShop() != null) {
				resultList = orderInfoShopDAO.selectShopOrderDetail(requestBean.getOrderInfoShop());
			}else{
				throw new Exception("OrderInfoShop对象为空");
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean addShopDailyInfo(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean==null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getShopDailyInfoList()==null||requestBean.getShopDailyInfoList().size()==0){
				throw new Exception("hopDailyInfoList为空");
			}
			
			shopDailyInfoDAO.batchInsert(requestBean.getShopDailyInfoList());
		}catch(Exception e){
			responseBean.setResponseCode(Constants.INSERT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean queryOrderInfoShopByExample(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		TOrderInfoShopExample example = new TOrderInfoShopExample();
		List<TOrderInfoShop> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if (requestBean.getOrderInfoShopExample() != null) {
				example = requestBean.getOrderInfoShopExample();
			}else{
				throw new Exception("OrderInfoShopExample对象为空");
			}
			
			resultList = orderInfoShopDAO.selectByExample(example);
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	/* 修改订单商店信息的状态
	 * @see app.cn.qtt.bm.service.IOrderService#modifyOrderShopInfoRunStatus(app.cn.qtt.bm.service.pojo.OrderRequestBean)
	 */
	@Override
	public OrderResponseBean modifyOrderShopInfoRunStatus(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfoShop() == null){
				throw new Exception("TOrderInfoShop对象不能为空");
			}
			TOrderInfoShop orderInfoShop = requestBean.getOrderInfoShop();
			TOrderInfoShopExample example = new TOrderInfoShopExample();
			example.createCriteria().andOrderShopIdEqualTo(orderInfoShop.getOrderShopId())
					.andOrderShopRunStatusNotEqualTo(Constants.ORDER_RUN_STATUS_0);

			orderInfoShopDAO.updateByExampleSelective(orderInfoShop, example);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	/**
	 * @see app.cn.qtt.bm.service.IOrderService#modifyOrderShopInfoRunStatusForJob(app.cn.qtt.bm.service.pojo.OrderRequestBean)
	 * @param requestBean
	 * @return
	 * @author Gabriel
	 * @createtime 2013-11-13 上午11:05:53
	 */
	@Override
	public OrderResponseBean modifyOrderShopInfoRunStatusForJob(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getOrderInfoShop() == null){
				throw new Exception("TOrderInfoShop对象不能为空");
			}
			if(StringUtils.isBlank(requestBean.getOrderShopRunStatus())){
				throw new Exception("OrderShopRunStatus不能为空");
			}
				
			TOrderInfoShop orderInfoShop = requestBean.getOrderInfoShop();
			TOrderInfoShopExample example = new TOrderInfoShopExample();
			example.createCriteria().andOrderShopIdEqualTo(orderInfoShop.getOrderShopId())
					.andOrderShopRunStatusEqualTo(requestBean.getOrderShopRunStatus());

			orderInfoShopDAO.updateByExampleSelective(orderInfoShop, example);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.UPDATE_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	@Override
	public OrderResponseBean queryMyOrder3(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<MyOrder> resultList = null;
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(requestBean.getMyOrder() != null){
				resultList = orderInfoDAO.selectMyOrderInfo3(requestBean.getMyOrder());
			}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	/**
	 * @return the orderInfoDAO
	 */
	public TOrderInfoDAO getOrderInfoDAO() {
		return orderInfoDAO;
	}

	/**
	 * @param orderInfoDAO the orderInfoDAO to set
	 */
	public void setOrderInfoDAO(TOrderInfoDAO orderInfoDAO) {
		this.orderInfoDAO = orderInfoDAO;
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
	 * @return the orderInfoShopDAO
	 */
	public TOrderInfoShopDAO getOrderInfoShopDAO() {
		return orderInfoShopDAO;
	}

	/**
	 * @param orderInfoShopDAO the orderInfoShopDAO to set
	 */
	public void setOrderInfoShopDAO(TOrderInfoShopDAO orderInfoShopDAO) {
		this.orderInfoShopDAO = orderInfoShopDAO;
	}

	/**
	 * @return the shopDailyInfoDAO
	 */
	public TShopDailyInfoDAO getShopDailyInfoDAO() {
		return shopDailyInfoDAO;
	}

	/**
	 * @param shopDailyInfoDAO the shopDailyInfoDAO to set
	 */
	public void setShopDailyInfoDAO(TShopDailyInfoDAO shopDailyInfoDAO) {
		this.shopDailyInfoDAO = shopDailyInfoDAO;
	}

	@Override
	public OrderResponseBean queryOrderOperateDetil(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<OrderOperateBean> resultList = null;
		Map<String, Object> params=new HashMap<String, Object>();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			String searchDate = requestBean.getSearchDate();
			
			if(StringUtils.isBlank(searchDate)){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				searchDate = sdf.format(new Date());
			}
			params.put("currentTime", searchDate);
				
			//if(requestBean.getMyOrder() != null){
				resultList = orderInfoDAO.selectOrderOperateDetail(params);
			//}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}

	/**
	 * @see app.cn.qtt.bm.service.IOrderService#verifyPhoneNumberAndCode(app.cn.qtt.bm.service.pojo.OrderRequestBean)
	 * @param requestBean
	 * @return
	 * @author Gabriel
	 * @createtime 2013-9-17 上午10:55:43
	 */
	@Override
	public OrderResponseBean verifyPhoneNumberAndCode(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<TOrderInfoShop> resultList = null;
		try {
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			if(StringUtils.isBlank(requestBean.getShortPhoneNumber())){
				throw new Exception("shortPhoneNumber为空");
			}
			if(StringUtils.isBlank(requestBean.getCaptchas())){
				throw new Exception("captchas为空");
			}
			
			resultList = orderInfoShopDAO.selectByVerifyCondition(requestBean.getShortPhoneNumber(),requestBean.getCaptchas());
			
			responseBean.setResultList(resultList);
			responseBean.setResponseCode(Constants.SELECT_SUCCESS_CODE);
		} catch (Exception e) {
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	
	@Override
	public OrderResponseBean queryInvalidUserOrderOperateDetil(OrderRequestBean requestBean) {
		OrderResponseBean responseBean = new OrderResponseBean();
		List<OrderOperateBean> resultList = null;
		Map<String, Object> params=new HashMap<String, Object>();
		try{
			if(requestBean == null){
				throw new Exception("请求对象为空");
			}
			String searchDate = requestBean.getSearchDate();
			
			if(StringUtils.isBlank(searchDate)){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				searchDate = sdf.format(new Date());
			}
			params.put("currentTime", searchDate);
				
			//if(requestBean.getMyOrder() != null){
				resultList = orderInfoDAO.selectInvalidUserOrderOperateDetail(params);
			//}
			
			responseBean.setResultList(resultList);
		}catch(Exception e){
			responseBean.setResponseCode(Constants.SELECT_FAIL_CODE);
			responseBean.setErrMsg(e.getMessage());
			responseBean.setException(e);
		}
		
		return responseBean;
	}
	

}
