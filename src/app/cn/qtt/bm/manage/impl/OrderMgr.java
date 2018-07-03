package app.cn.qtt.bm.manage.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.utils.DateUtil;
import app.cn.qtt.bm.common.utils.RandomUtils;
import app.cn.qtt.bm.manage.IOrderMgr;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.OrderOperateBean;
import app.cn.qtt.bm.model.ShopOrderdailyStatistics;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoExample;
import app.cn.qtt.bm.model.TOrderInfoGoods;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TOrderInfoShopExample;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.IOrderService;
import app.cn.qtt.bm.service.IShopService;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;
import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

public class OrderMgr extends BaseService implements IOrderMgr {

	private IOrderService orderService;
	private IGoodsService goodService;
	private IShopService shopService;

	
	@Override
	public OrderResponseBean selectOrders(TOrderInfo orderInfo)
			throws CMException {
		String xFunctionName = "selectOrders";
		List<Map> listMap = new ArrayList<Map>();
		TOrderInfoShop orderInfoShop = new TOrderInfoShop();
		TOrderInfo listOrderInfo = new TOrderInfo();
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		TOrderInfoExample orderInfoExample = new TOrderInfoExample();
		TOrderInfoExample.Criteria criteria = orderInfoExample.createCriteria();
		try {
			if (null != orderInfo) {
				criteria.andOrderIdEqualTo(orderInfo.getOrderId());
				orderInfoShop.setOrderId(orderInfo.getOrderId());
			}
			// if(null != sysUserInfo){
			// criteria.andCreateUserCodeEqualTo(sysUserInfo.getUserCode());
			// }
			orderRequestBean.setOrderInfoShop(orderInfoShop);
			// orderRequestBean.setOrderInfo(orderInfo);
			orderRequestBean.setOrderInfoExample(orderInfoExample);
			orderResponseBean = orderService.queryOrders(orderRequestBean);
			if (!"00".equals(orderResponseBean.getResponseCode())) {
				throw new CMException(orderResponseBean.getErrMsg());
			} else {
				List resultList = orderResponseBean.getResultList();
				if(CollectionUtils.isNotEmpty(resultList)){
					listOrderInfo = (TOrderInfo) resultList.get(0);
				}
			}
			orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
			if ("00".equals(orderResponseBean.getResponseCode())) {
				List<TOrderInfoShop> resultList = (List<TOrderInfoShop>) orderResponseBean.getResultList();
				for (TOrderInfoShop orderShop : resultList) {
					MyOrder myOrder = new MyOrder();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("shopInfo", orderShop.getShopInfo());
					myOrder.setOrderId(orderShop.getOrderId());
					myOrder.setShopId(orderShop.getShopId());
					orderRequestBean.setMyOrder(myOrder);
					orderResponseBean = orderService.queryMyOrder(orderRequestBean);
					if ("00".equals(orderResponseBean.getResponseCode())) {
						map.put("myOrderList",orderResponseBean.getResultList());
					}
					listMap.add(map);
				}
			}
			if (!"00".equals(orderResponseBean.getResponseCode())) {
				throw new CMException(orderResponseBean.getErrMsg());
			}
			orderResponseBean.setResultListMap(listMap);
			orderResponseBean.setOrderInfo(listOrderInfo);
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return orderResponseBean;
	}
	
	
	public OrderResponseBean queryDetailOrderInfoByTime(String userCode, Date createTime, List orderStatusList) throws CMException,Exception{
		String xFunctionName = "queryDetailOrderInfoByTime";
		gLogger.begin(xFunctionName);
		OrderResponseBean orderResponseBean = new OrderResponseBean();
		try{
			List<Map> listMap = new ArrayList<Map>();
			TOrderInfoShop orderInfoShop = new TOrderInfoShop();
			TOrderInfo listOrderInfo = new TOrderInfo();
			OrderRequestBean orderRequestBean = new OrderRequestBean();
			TOrderInfoExample orderInfoExample = new TOrderInfoExample();
			TOrderInfoExample.Criteria criteria = orderInfoExample.createCriteria();
			criteria.andCreateUserCodeEqualTo(userCode);
			criteria.andCreateTimeEqualTo(createTime);
			criteria.andOrderRunStatusIn(orderStatusList);
			orderRequestBean.setOrderInfoExample(orderInfoExample);
			orderResponseBean = orderService.queryOrders(orderRequestBean);
			if (!"00".equals(orderResponseBean.getResponseCode())) {
				throw new CMException(orderResponseBean.getErrMsg());
			} else {
				List resultList = orderResponseBean.getResultList();
				if(CollectionUtils.isNotEmpty(resultList)){
					listOrderInfo = (TOrderInfo) resultList.get(0);
					orderInfoShop.setOrderId(listOrderInfo.getOrderId());
					orderRequestBean.setOrderInfoShop(orderInfoShop);
				}
				orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
				if ("00".equals(orderResponseBean.getResponseCode())) {
					List<TOrderInfoShop> resultOrderShopList = (List<TOrderInfoShop>) orderResponseBean.getResultList();
					for (TOrderInfoShop orderShop : resultOrderShopList) {
						MyOrder myOrder = new MyOrder();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("shopInfo", orderShop.getShopInfo());
						myOrder.setOrderId(orderShop.getOrderId());
						myOrder.setShopId(orderShop.getShopId());
						orderRequestBean.setMyOrder(myOrder);
						orderResponseBean = orderService.queryMyOrder3(orderRequestBean);
						if ("00".equals(orderResponseBean.getResponseCode())) {
							map.put("myOrderList",orderResponseBean.getResultList());
						}else{
							continue;
						}
						listMap.add(map);
					}
					orderResponseBean.setResultListMap(listMap);
					orderResponseBean.setOrderInfo(listOrderInfo);
				}
			}
		}catch(CMException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}finally{
			gLogger.end(xFunctionName);
		}
		return orderResponseBean;
	}

	/**
	 * 点击抢 添加购物车
	 */
	public Map addShoppingCart(TOrderInfo orderInfo,TOrderInfoShop orderInfoShop, TSysUserInfo sysUserInfo,TOrderInfoGoods orderInfoGoods, String shopGoodsPrice)
			throws CMException, CMRollBackException {
		String xFunctionName = "addOrder";
		//----------------------------------------------------------------------------字段定义初始化--begin-------------------------------------------------------//
		DateUtil du = new DateUtil();
		Date sysToday = new Date();
		String orderCode = null;
		Integer orderId = null;
	    String orderStatus = null;
		boolean resultFlag = false;
		//----------------------------------------------------------------------------字段定义初始化--end-------------------------------------------------------//
		//----------------------------------------------------------------------------对象定义创建--begin-------------------------------------------------------//
		Map<String, Object> resultMap = new HashMap<String, Object>();
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean = null;
		OrderResponseBean orderResponseBean = null;
		TOrderInfo tOrderInfo = new TOrderInfo();
		TOrderInfoShop tOrderInfoShop = new TOrderInfoShop();
		TOrderInfoGoods tOrderInfoGoods = new TOrderInfoGoods();
		//----------------------------------------------------------------------------对象定义创建--end-------------------------------------------------------//
		try {
		//----------------------------------------------------------------------------前端传入对象验证--begin----------------------------------------------//
			if (null == orderInfoShop) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoShop is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
						"orderInfoShop");
			}
			if (null == orderInfoGoods) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
						"orderInfoGoods");
			}
			//----------------------------------------------------------------------------前端传入对象验证--end----------------------------------------------//
			//-------------------------------------------------------根据当前时间和状态获取改用户创建的订单信息--begin------------------------------------//
			TOrderInfoExample orderExample = new TOrderInfoExample();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			orderExample.createCriteria().andCreateUserCodeEqualTo(sysUserInfo.getUserCode())
											.andCreateTimeGreaterThan(sdf.parse(du.getCurDate()));
			orderRequestBean.setOrderInfoExample(orderExample);
			orderResponseBean = orderService.queryOrders(orderRequestBean);
			//-------------------------------------------------------根据当前时间和状态获取改用户创建的订单信息--end------------------------------------//
			//-------------------------------------------------------
			if ("00".equals(orderResponseBean.getResponseCode())) {
				//---------------点击 "抢"，修改订单数跟订单价格 --begin-----------------------------------------------//
				List listOrder = orderResponseBean.getResultList();
				//--查看当前用户系统中是否存在订单  存在:修改订单  不存在:重新创建
				if (listOrder.size() > 0) {
					//---当前用户已存在订单,对订单信息进行修改------begin-------------------------------------//
					TOrderInfo listOrderInfo = (TOrderInfo) listOrder.get(0);
					orderCode = listOrderInfo.getOrderCode();
					orderId = listOrderInfo.getOrderId();
					orderStatus = listOrderInfo.getOrderRunStatus();
					if("04".equals(orderStatus) ){
						throw CMException.sys("订单已失效，不能修改订单！",100000,"");

					}
//					if("03".equals(orderStatus)){
//						throw CMException.sys("订单短信已发送完毕，不能修改订单！",100000,"");
//					}
//					if("01".equals(orderStatus)){
//						throw CMException.sys("订单已下单，请先取消订单！",100000,"");
//					}
					tOrderInfoShop.setOrderId(orderId);
					tOrderInfoGoods.setOrderId(orderId);
					tOrderInfo.setOrderId(orderId);
					//每点击抢一次，购物车数量自动加1
					Integer tempGoodCount = Integer.parseInt(listOrderInfo.getOrderGoodsCount().trim()) + 1;
					//--------------------从shopGood中查询商品价格 begin-----------------------------------//
					TShopGoods shopGoods = new TShopGoods();
					shopGoods.setShopGoodsId(orderInfoGoods.getShopGoodsId());
					shopGoods.setShopGoodsStatus(Constants.BUFFER_RUN_STATUS_00);
					goodsRequestBean.setShopGoods(shopGoods);
					goodsResponseBean = goodService.queryGoodsInfoById(goodsRequestBean);
					String goodPrice = null;
					if ("00".equals(goodsResponseBean.getResponseCode())) {
						// List list = goodsResponseBean.getResultList();

						TShopGoods tShopGoods = goodsResponseBean.getShopGoods();
						goodPrice = tShopGoods.getShopGoodsPrice();
					}
					
					//------------------从shopGood中查询商品价格 end------------------------------------//
					//------------------新的信息赋值到listOrderInfo中,提交到service层进行数据加工--begin---------------//
					//新的订单临时价格处理
					double tempOrderPrice = Double.parseDouble(listOrderInfo.getOrderPrice().trim());
					tempOrderPrice = tempOrderPrice + Double.parseDouble(goodPrice);
					// 订单状态已订购
					listOrderInfo.setOrderRunStatus(orderStatus);
					listOrderInfo.setOrderGoodsCount("" + tempGoodCount);
					listOrderInfo.setOrderPrice("" + tempOrderPrice);
					orderRequestBean.setOrderInfo(listOrderInfo);
					orderResponseBean = orderService.modifyOrderInfo(orderRequestBean);
					//------------------新的信息赋值到listOrderInfo中,提交到service层进行数据加工--end---------------//
					if ("00".equals(orderResponseBean.getResponseCode())) {
						resultFlag = true;
					} else {
						resultFlag = false;
						throw new CMRollBackException(orderResponseBean.getErrMsg());
					}
				} else {
					//-------------------订单不存在时，重新创建订单--begin--------------------------------------------------//
					//生成新的订单code,orderInfo对象赋值 
					orderCode = RandomUtils.getBatchId();
					tOrderInfo.setOrderCode(orderCode);
					tOrderInfo.setOrderGoodsCount(Constants.DEFAULT_SHOP_CART_NUM_1);
					tOrderInfo.setOrderPrice(shopGoodsPrice);
					tOrderInfo.setCreateUserCode(sysUserInfo.getUserCode());
					tOrderInfo.setCreateTime(sysToday);
					orderStatus = Constants.BUFFER_RUN_STATUS_00;
					tOrderInfo.setOrderRunStatus(Constants.BUFFER_RUN_STATUS_00);
					orderRequestBean.setOrderInfo(tOrderInfo);
					orderResponseBean = orderService.addOrderInfo(orderRequestBean);
					if ("00".equals(orderResponseBean.getResponseCode())) {
						//-------------------订单不存在时，重新创建订单--end--------------------------------------------------//
						resultFlag = true;
						orderResponseBean = orderService.queryOrders(orderRequestBean);
						if ("00".equals(orderResponseBean.getResponseCode())) {
							List listOrder2 = orderResponseBean.getResultList();
							if (listOrder2.size() > 0) {
								TOrderInfo listOrderInfo = (TOrderInfo) listOrder2.get(0);
								orderCode = listOrderInfo.getOrderCode();
								orderId = listOrderInfo.getOrderId();
								tOrderInfoShop.setOrderId(orderId);
								tOrderInfoGoods.setOrderId(orderId);
								tOrderInfo.setOrderId(orderId);
								tOrderInfo.setOrderPrice(shopGoodsPrice);
							}
						}
					} else {
						throw new CMRollBackException(
								orderResponseBean.getErrMsg());
					}
				}
			} else {
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
			//-----------------------------------------订单店铺插入数据-----begin------------------------------------------------//
			//-----------------------------------------获取订单店铺数据-----begin------------------------------------------------//
			tOrderInfoShop.setShopId(orderInfoShop.getShopId());
			tOrderInfoShop.setOrderId(tOrderInfo.getOrderId());
			orderRequestBean.setOrderInfoShop(tOrderInfoShop);
			orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
			//-----------------------------------------获取订单店铺数据-----end------------------------------------------------//

			if ("00".equals(orderResponseBean.getResponseCode())) {
				//订单店铺中查不到改订单编号时，插入数据--------------------begin-----------------------------------//
				List listOrderShop = orderResponseBean.getResultList();
				if (listOrderShop.size() == 0) {
					orderInfoShop.setOrderId(orderId);
					orderInfoShop.setCreateUserCode(sysUserInfo.getUserCode());
					orderInfoShop.setCreateTime(sysToday);
					orderInfoShop.setOrderShopRunStatus(orderStatus);
					orderInfoShop.setPrice(shopGoodsPrice);
					orderRequestBean.setOrderInfoShop(orderInfoShop);
					orderResponseBean = orderService.addOrderShopInfo(orderRequestBean);
					//订单店铺中查不到改订单编号时，插入数据--------------------end-----------------------------------//
					if ("00".equals(orderResponseBean.getResponseCode())) {
						resultFlag = true;
					} else {
						throw new CMRollBackException(
								orderResponseBean.getErrMsg());
					}
				} else {
					//订单店铺中查到改订单编号数据时，修改该店铺的总价格--------------------begin-----------------------------------//
					//--获取订单店铺总价格---begin
					orderResponseBean.setOrderInfoShop(tOrderInfoShop);
					orderResponseBean = orderService.getOrderShopPrice(orderRequestBean);
					//--获取订单店铺总价格---end
					if ("00".equals(orderResponseBean.getResponseCode())) {
						Double tempPrice = Double.parseDouble(shopGoodsPrice) + Double.parseDouble(orderResponseBean.getPrice());
						tOrderInfoShop.setPrice(tempPrice.toString());
						tOrderInfoShop.setOrderShopRunStatus(orderStatus);
						tOrderInfoShop.setModifyUserCode(sysUserInfo.getUserCode());
						tOrderInfoShop.setModifyTime(sysToday);
						orderRequestBean.setOrderInfoShop(tOrderInfoShop);
						TOrderInfoShopExample example = new TOrderInfoShopExample();
											example.createCriteria()
													.andShopIdEqualTo(tOrderInfoShop.getShopId())
													.andOrderIdEqualTo(tOrderInfoShop.getOrderId());
						orderRequestBean.setOrderInfoShopExample(example);
						orderResponseBean = orderService.modifyOrderShopInfo(orderRequestBean);
						//订单店铺中查到改订单编号数据时，修改该店铺的总价格--------------------end-----------------------------------//
						if (!"00".equals(orderResponseBean.getResponseCode())) {
							throw new CMRollBackException(
									orderResponseBean.getErrMsg());
						}
					}
				}
			} else {
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
			//-------------------------------------------订单商品----begin-----------------------------------------------------//
			tOrderInfoGoods.setOrderId(orderId);
			tOrderInfoGoods.setShopGoodsId(orderInfoGoods.getShopGoodsId());
			tOrderInfoGoods.setShopId(orderInfoShop.getShopId());
			orderRequestBean.setOrderInfoGoods(tOrderInfoGoods);
			if (null != orderRequestBean) {
				//根据orderId 和 ShopGoodsId 查询当前orderInfoGoods表goodcount和goodprice
				orderResponseBean = orderService.queryOrderInfoGoods(orderRequestBean);
			}
			if ("00".equals(orderResponseBean.getResponseCode())) {
				List list = orderResponseBean.getResultList();
				if (list.size() > 0) {
					//-----存在进行修改--begin----------------------------//
					TOrderInfoGoods listOrderInfoGoods = (TOrderInfoGoods) list.get(0);
					String shoopGoodsCount = listOrderInfoGoods.getShopGoodsCount();
					Integer totalGoodsCount = Integer.parseInt(shoopGoodsCount.trim()) + 1;
					listOrderInfoGoods.setShopGoodsCount(totalGoodsCount.toString());
					listOrderInfoGoods.setModifyTime(sysToday);
					listOrderInfoGoods.setModifyUserCode(sysUserInfo.getUserCode());
					orderRequestBean.setOrderInfoGoods(listOrderInfoGoods);
					orderResponseBean = orderService.modifyOrderGoodsInfo(orderRequestBean);
					//-----存在进行修改--end----------------------------
					if ("00".equals(orderResponseBean.getResponseCode())) {
						resultFlag = true;
					} else {
						resultFlag = false;
						throw new CMRollBackException(
								orderResponseBean.getErrMsg());
					}
				} else {
					//---插入新的订单---begin-------//
					orderInfoGoods.setShopGoodsCount(Constants.DEFAULT_SHOP_CART_NUM_1);
					orderInfoGoods.setShopId(orderInfoShop.getShopId());
					orderInfoGoods.setOrderId(orderId);
					orderInfoGoods.setCreateUserCode(sysUserInfo.getUserCode());
					orderInfoGoods.setCreateTime(sysToday);
					orderRequestBean.setOrderInfoGoods(orderInfoGoods);
					orderResponseBean = orderService.addOrderGoodsInfo(orderRequestBean);
					if ("00".equals(orderResponseBean.getResponseCode())) {
						//---插入新的订单---end-------//
						resultFlag = true;
					} else {
						resultFlag = true;
						throw new CMRollBackException(
								orderResponseBean.getErrMsg());
					}
				}
			} else {
				resultFlag = false;
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
			//-------------------------------------------订单商品----end-----------------------------------------------------//
		}
	
		catch (CMRollBackException e) {
			throw e;
		}
		catch(CMException e){
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}
		resultMap.put("resultFlag", resultFlag);
		resultMap.put("orderId", orderId);
		return resultMap;
	}


	/**
	 * 修改订单状态
	 */
	public boolean modifyOrderRunStatus(TOrderInfo orderInfo,TSysUserInfo sysUserInfo,Integer shopId) throws CMException, CMRollBackException {
		String xFunctionName = "modifyOrderRunStatus";
		//-------------------------------------------------------字段 对象定义实例化--begin------------------------------------------------//
		boolean resultFlag = false;
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		Date sysDate = new Date();
		TOrderInfoShop tOrderInfoShop = new TOrderInfoShop(); 
		//-------------------------------------------------------字段 对象定义实例化--end------------------------------------------------//
		try {
			//-----------------------------------------------------对前端传入对象验证--begin----------------------------------------------//
			if (null == orderInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			if (null == orderInfo.getOrderId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			if(null == shopId){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopId");
			}
			//-----------------------------------------------------对前端传入对象验证--end----------------------------------------------//
			
		
			
			//--------------------------------------------------修改orderInfoShop状态--begin--------------------------------------//
			//orderInfoShop 对象赋值
			TOrderInfoShopExample example = new TOrderInfoShopExample();
			tOrderInfoShop.setOrderId(orderInfo.getOrderId());
			tOrderInfoShop.setOrderShopRunStatus(Constants.BUFFER_RUN_STATUS_04);
			tOrderInfoShop.setModifyUserCode(sysUserInfo.getUserCode());
			tOrderInfoShop.setModifyTime(sysDate);
			example.createCriteria().andShopIdEqualTo(shopId).andOrderIdEqualTo(orderInfo.getOrderId());
			orderRequestBean.setOrderInfoShop(tOrderInfoShop);
			//构造条件example
			orderRequestBean.setOrderInfoShopExample(example);
			orderResponseBean = orderService.modifyOrderShopInfo(orderRequestBean);
			//--------------------------------------------------修改orderInfoShop状态--end--------------------------------------//
			if("00".equals(orderResponseBean.getResponseCode())){
				resultFlag = true;
			}else{
				resultFlag = false;
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
			
			//-----------------------------------------------------查询店铺状态---begin------------------------------------------------------//
			TOrderInfoShop orderInfoShop = new TOrderInfoShop(); 
			//orderInfoShop.setShopId(shopId);
			orderInfoShop.setOrderId(orderInfo.getOrderId());
			orderRequestBean.setOrderInfoShop(orderInfoShop);
			orderInfoShop.setOrderShopRunStatus(Constants.BUFFER_RUN_STATUS_03);
			orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
			boolean shopStatusFlag = false;
			if("00".equals(orderResponseBean.getResponseCode())){
				if(orderResponseBean.getResultList().size() > 0){
					List<TOrderInfoShop> list =(List<TOrderInfoShop>) orderResponseBean.getResultList();
					for(TOrderInfoShop orderInfoShopList : list){
						if(Constants.BUFFER_RUN_STATUS_04.equals(orderInfoShopList.getOrderShopRunStatus())){
							shopStatusFlag = true;
						}else{
							shopStatusFlag = false;
							break;
						}
					}
					
				}
			}
			//-----------------------------------------------------查询店铺状态---end------------------------------------------------------//
			
			if(shopStatusFlag){
				orderInfo.setOrderRunStatus(Constants.BUFFER_RUN_STATUS_04);
				//-----------------------------------------------------修改orderInfo对象--begin----------------------------------------------//
				//orderInfo 对象赋值
				orderInfo.setOrderRunStatus(orderInfo.getOrderRunStatus());
				orderInfo.setModifyUserCode(sysUserInfo.getUserCode());
				orderInfo.setModifyTime(sysDate);
				orderRequestBean.setOrderInfo(orderInfo);
				// 调用service方法
				orderResponseBean = orderService.modifyOrderInfo(orderRequestBean);
				if (!"00".equals(orderResponseBean.getResponseCode())) {
					resultFlag = false;
					throw new CMRollBackException(orderResponseBean.getErrMsg());
				}
				//-----------------------------------------------------修改orderInfo对象--end----------------------------------------------//
			}
		} catch (CMRollBackException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	/**
	 * 修改订单取消状态
	 */
	public boolean modifyOrderCancelStatus(TOrderInfo orderInfo,TSysUserInfo sysUserInfo) throws CMException, CMRollBackException {
		String xFunctionName = "modifyOrderRunStatus";
		//-------------------------------------------------------字段 对象定义实例化--begin------------------------------------------------//
		boolean resultFlag = false;
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		Date sysDate = new Date();
		TOrderInfoShop tOrderInfoShop = new TOrderInfoShop(); 
		//-------------------------------------------------------字段 对象定义实例化--end------------------------------------------------//
		try {
			//-----------------------------------------------------对前端传入对象验证--begin----------------------------------------------//
			if (null == orderInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			if (null == orderInfo.getOrderId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
		
			//-----------------------------------------------------对前端传入对象验证--end----------------------------------------------//
			
			
			//-----------------------------------------------------查询店铺状态---begin------------------------------------------------------//
			TOrderInfoShop orderInfoShop = new TOrderInfoShop(); 
			//orderInfoShop.setShopId(shopId);
			orderInfoShop.setOrderId(orderInfo.getOrderId());
			orderRequestBean.setOrderInfoShop(orderInfoShop);
			orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
			boolean shopStatusFlag = false;
			if("00".equals(orderResponseBean.getResponseCode())){
				if(orderResponseBean.getResultList().size() > 0){
					List<TOrderInfoShop> list =(List<TOrderInfoShop>) orderResponseBean.getResultList();
					for(TOrderInfoShop orderInfoShopList : list){
						orderInfoShopList.setOrderShopRunStatus(Constants.BUFFER_RUN_STATUS_00);
						orderInfoShopList.setOrderShopQrcode("");
						orderInfoShopList.setModifyUserCode(sysUserInfo.getUserCode());
						orderInfoShopList.setModifyTime(sysDate);
						orderRequestBean.setOrderInfoShop(orderInfoShopList);
						orderResponseBean = orderService.modifyOrderShopInfoRunStatus(orderRequestBean);
						if("00".equals(orderResponseBean.getResponseCode())){
							shopStatusFlag = true;
						}else{
							shopStatusFlag = false;
							throw new CMRollBackException(orderResponseBean.getErrMsg());
						}
					}
					
				}
			}
			//-----------------------------------------------------查询店铺状态---end------------------------------------------------------//
			
			if(shopStatusFlag){
				orderInfo.setOrderRunStatus(Constants.BUFFER_RUN_STATUS_00);
				//-----------------------------------------------------修改orderInfo对象--begin----------------------------------------------//
				//orderInfo 对象赋值
				orderInfo.setOrderRunStatus(orderInfo.getOrderRunStatus());
				orderInfo.setModifyUserCode(sysUserInfo.getUserCode());
				orderInfo.setModifyTime(sysDate);
				orderRequestBean.setOrderInfo(orderInfo);
				// 调用service方法
				orderResponseBean = orderService.modifyOrderInfo(orderRequestBean);
				if (!"00".equals(orderResponseBean.getResponseCode())) {
					resultFlag = false;
					throw new CMRollBackException(orderResponseBean.getErrMsg());
				}
				//-----------------------------------------------------修改orderInfo对象--end----------------------------------------------//
			}
		} catch (CMRollBackException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}
	
	/**
	 * 删除订单
	 */
	public boolean deleteOrder(TOrderInfo orderInfo) throws CMException,
			CMRollBackException {
		String xFunctionName = "deleteOrder";
		boolean resultFlag = false;
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = new OrderResponseBean();
		TOrderInfoGoods tOrderInfoGoods = new TOrderInfoGoods();
		TOrderInfoShop tOrderInfoShop = new TOrderInfoShop();
		try {
			if (null != orderInfo) {
				if (null != orderInfo.getOrderId()) {
					tOrderInfoGoods.setOrderId(orderInfo.getOrderId());
					tOrderInfoShop.setOrderId(orderInfo.getOrderId());
				}
			} else {
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			orderRequestBean.setOrderInfo(orderInfo);
			orderResponseBean = orderService.deleteOrder(orderRequestBean);
			if ("00".equals(orderResponseBean.getResponseCode())) {
				// 删除订单店铺
				orderResponseBean = null;
				orderRequestBean.setOrderInfoShop(tOrderInfoShop);
				orderResponseBean = orderService
						.deleteOrderShopInfo(orderRequestBean);
				if ("00".equals(orderResponseBean.getResponseCode())) {
					orderResponseBean = null;
					orderRequestBean.setOrderInfoGoods(tOrderInfoGoods);
					// 删除订单商品关联表
					orderResponseBean = orderService
							.deleteOrderGoodsInfo(orderRequestBean);
					if ("00".equals(orderResponseBean.getResponseCode())) {
						resultFlag = true;
					} else {
						resultFlag = false;
						throw new CMRollBackException(
								orderResponseBean.getErrMsg());
					}
				} else {
					resultFlag = false;
					throw new CMRollBackException(orderResponseBean.getErrMsg());
				}
			} else {
				resultFlag = false;
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
		} catch (CMRollBackException e) {
			throw e;
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	/**
	 * 删除订单店铺
	 */
	public boolean deleteOrderShop(TOrderInfo orderInfo,
			TOrderInfoShop orderInfoShop) throws CMException,
			CMRollBackException {
		String xFunctionName = "deleteOrderGoods";
		boolean resultFlag = false;
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		try {
			if (null == orderInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			if (null == orderInfoShop) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoShop is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
						"orderInfoShop");
			}
			if (null != orderInfo.getOrderId()) {
				orderInfoShop.setOrderId(orderInfo.getOrderId());
			} else {
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			orderRequestBean.setOrderInfoShop(orderInfoShop);
			// 删除订单店铺关联表
			orderResponseBean = orderService
					.deleteOrderShopInfo(orderRequestBean);
			if ("00".equals(orderResponseBean.getResponseCode())) {
				orderRequestBean = new OrderRequestBean();
				orderResponseBean = null;
				TOrderInfoShop tOrderInfoShop = new TOrderInfoShop();
				tOrderInfoShop.setOrderId(orderInfo.getOrderId());
				orderRequestBean.setOrderInfoShop(tOrderInfoShop);
				orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
				List list = orderResponseBean.getResultList();
				// 订单关联表为空 直接删除订单
				if (list.size() == 0) {
					TOrderInfo tOrderInfo = new TOrderInfo();
					tOrderInfo.setOrderId(orderInfo.getOrderId());
					orderRequestBean.setOrderInfo(tOrderInfo);
					orderResponseBean = orderService
							.deleteOrder(orderRequestBean);
					if ("00".equals(orderResponseBean.getResponseCode())) {
						resultFlag = true;
					} else {
						resultFlag = false;
						throw new CMRollBackException(
								orderResponseBean.getErrMsg());
					}
				}
				resultFlag = true;
			} else {
				resultFlag = false;
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
		} catch (CMRollBackException e) {
			throw e;
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}

		return resultFlag;
	}

	/**
	 * 删除订单物品
	 */
	public boolean deleteOrderGoods(TOrderInfo orderInfo,TOrderInfoGoods orderInfoGoods, TSysUserInfo sysUserInfo)
								throws CMException, CMRollBackException {
		String xFunctionName = "deleteOrderGoods";
		//-----------------------------------变量定义级初始化--begin--------------------------------------//
		boolean resultFlag = false;
		//临时订单商品总量
		String orderGoodCount = null;
		//临时订单商品总价格
		String orderGoodPrice = null;
		//临时订单店铺总价格
		String orderShopPrice = null;
		//临时单总商品总价格
		Double totalPrice = null;
		//当天时间
		Date sysDate = new Date();
		//-----------------------------------变量定义--end-----------------------------------------------//
		//-----------------------------------对象的实例化及初始化--begin--------------------------------//
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		TOrderInfoShop tOrderInfoShop = new TOrderInfoShop();
		TOrderInfoExample orderInfoExample = new TOrderInfoExample();
		OrderResponseBean orderResponseBean = null;
		GoodsResponseBean goodsResponseBean = null;
		//-----------------------------------对象的实例化及初始化--end--------------------------------//
		try {
			//---前端传进对象验证 begin--//
			if (null == orderInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfo is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			if (null == orderInfoGoods) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoGoods is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoGoods");
			}
			if (null != orderInfo.getOrderId()) {
				orderInfoGoods.setOrderId(orderInfo.getOrderId());
			} else {
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			if (null == orderInfoGoods.getShopGoodsId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoGoods");
			}
			if (null == orderInfoGoods.getShopId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoGoods");
			}
			//---前端传进对象验证 end--//
			// --------------------------------------------------查询当个商品信息-begin--------------------------------------------------//
			orderInfoGoods.setOrderId(orderInfo.getOrderId());
			orderRequestBean.setOrderInfoGoods(orderInfoGoods);
			orderResponseBean = orderService.queryOrderInfoGoods(orderRequestBean);
			// --------------------------------------------------查询当个商品信息-end--------------------------------------------------//
			if ("00".equals(orderResponseBean.getResponseCode())) {
				TOrderInfoGoods tOrderInfoGoodsInfo = null;
				List orderInfoGoodsList = orderResponseBean.getResultList();
				if(orderInfoGoodsList.size() >0 ){
					tOrderInfoGoodsInfo = (TOrderInfoGoods) orderInfoGoodsList.get(0);
				}else{
					throw CMException.sys("该订单物品不存在，不能进行删除",300000,"");
				}
				//获取订单商品总量
				orderGoodCount = tOrderInfoGoodsInfo.getShopGoodsCount();
				//----------------------------------------------查询有效商品单价-begin------------------------------------------------------//
				TShopGoods shopGoods = new TShopGoods();
				shopGoods.setShopGoodsId(orderInfoGoods.getShopGoodsId());
				shopGoods.setShopGoodsStatus("00");
				goodsRequestBean.setShopGoods(shopGoods);
				goodsResponseBean = goodService.queryGoodsInfoById(goodsRequestBean);
				if ("00".equals(goodsResponseBean.getResponseCode())) {
					TShopGoods tShopGoods = goodsResponseBean.getShopGoods();
					orderGoodPrice = tShopGoods.getShopGoodsPrice();
					//----------------------------------------------查询有效商品单价-end------------------------------------------------------//
				}
				//订单中该商品总价 = 商品单价*数量
				totalPrice = Double.parseDouble(orderGoodCount) * Double.parseDouble(orderGoodPrice);
			}
			//-------------------------------------------------查询订单店铺总价-begin-------------------------------------//
			tOrderInfoShop.setOrderId(orderInfo.getOrderId());
			tOrderInfoShop.setShopId(orderInfoGoods.getShopId());
			orderRequestBean.setOrderInfoShop(tOrderInfoShop);
			orderResponseBean = orderService.getOrderShopPrice(orderRequestBean);
			//-------------------------------------------------查询订单店铺总价-end-------------------------------------//
			if ("00".equals(orderResponseBean.getResponseCode())) {
				orderShopPrice = orderResponseBean.getPrice();
			}else{
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
			//-------------------------------------------------删除订单店铺-begin-------------------------------------------------//
			// 删除订单物品关联表
			orderRequestBean.setOrderInfoGoods(orderInfoGoods);
			orderResponseBean = orderService.deleteOrderGoodsInfo(orderRequestBean);
			//-------------------------------------------------删除订单店铺-end-------------------------------------------------//
			if ("00".equals(orderResponseBean.getResponseCode())) {
				// ------------------------------------------------查询订单商品表-begin----------------------------------------//
				TOrderInfoGoods tOrderInfoGoods = new TOrderInfoGoods();
				tOrderInfoGoods.setOrderId(orderInfo.getOrderId());
				tOrderInfoGoods.setShopId(orderInfoGoods.getShopId());
				orderRequestBean.setOrderInfoGoods(tOrderInfoGoods);
				orderResponseBean = orderService.queryOrderInfoGoods(orderRequestBean);
				// ------------------------------------------------查询订单商品表-end----------------------------------------//
				if ("00".equals(orderResponseBean.getResponseCode())) {
					List list = orderResponseBean.getResultList();
				//-------------------------------------------------店铺的订单商品为空时删除商品-begin--------------------------//
					if (list.size() == 0) {
						tOrderInfoShop.setOrderId(orderInfo.getOrderId());
						orderRequestBean.setOrderInfoShop(tOrderInfoShop);
						// 删除订单店铺
						orderResponseBean = orderService.deleteOrderShopInfo(orderRequestBean);
						if ("00".equals(orderResponseBean.getResponseCode())) {
							resultFlag = true;
						} else {
							resultFlag = false;
							throw new CMRollBackException(orderResponseBean.getErrMsg());
						}
					}
					//-------------------------------------------------店铺的订单商品为空时删除商品-end--------------------------//
					//------------------------------------------------- 修改订单信息--begin----------------------------------------//
					TOrderInfo tOrderInfo = new TOrderInfo();
					//modify by linch
					orderInfoExample.createCriteria().andOrderIdEqualTo(orderInfo.getOrderId());
					orderRequestBean.setOrderInfoExample(orderInfoExample);
//					tOrderInfo.setOrderId(orderInfo.getOrderId());
//					orderRequestBean.setOrderInfo(tOrderInfo);
					//-------------------------------------------------查询原来订单信息--begin------------------------------------//
					orderResponseBean = orderService.queryOrders(orderRequestBean);
					List listOrder = orderResponseBean.getResultList();
					TOrderInfo listOrderInfo = (TOrderInfo) listOrder.get(0);
					//获取原来订单中总价和总数量
					String orderListGoodCount = listOrderInfo.getOrderGoodsCount();
					String orderListGoodPrice = listOrderInfo.getOrderPrice();
					String orderStatus = listOrderInfo.getOrderRunStatus();
					//-------------------------------------------------查询原来订单信息--end------------------------------------//
					//--更改订单总量和总价begin
					Integer newOrderGoodCount = Integer.parseInt(orderListGoodCount) - Integer.parseInt(orderGoodCount);
					Double tempPrice = Double.parseDouble(orderListGoodPrice) - totalPrice;
					//--更改订单总量和总价end
					//更改原先订单数量及总价
					listOrderInfo.setOrderGoodsCount(newOrderGoodCount.toString());
					listOrderInfo.setOrderPrice(tempPrice.toString());
					listOrderInfo.setModifyTime(sysDate);
//					orderInfoExample.createCriteria().andOrderIdEqualTo(tOrderInfo.getOrderId());
					orderRequestBean.setOrderInfo(listOrderInfo);
					orderResponseBean = orderService.modifyOrderInfo(orderRequestBean);
					//------------------------------------------------- 修改订单信息--end----------------------------------------//
					if ("00".equals(orderResponseBean.getResponseCode())) {
						resultFlag = true;
						//----------------------------------------------- 修改店铺价格--begin------------------------------------//
						double tempShopPrice = Double.parseDouble(orderShopPrice) - totalPrice;
						tOrderInfoShop.setPrice(""+tempShopPrice);
						tOrderInfoShop.setModifyUserCode(sysUserInfo.getUserCode());
						tOrderInfoShop.setModifyTime(sysDate);
						//tOrderInfoShop.setOrderShopRunStatus(orderStatus);
						orderRequestBean.setOrderInfoShop(tOrderInfoShop);
						TOrderInfoShopExample example = new TOrderInfoShopExample();
						example.createCriteria().andShopIdEqualTo(tOrderInfoShop.getShopId()).andOrderIdEqualTo(tOrderInfoShop.getOrderId());
						orderRequestBean.setOrderInfoShopExample(example);
						orderResponseBean = orderService.modifyOrderShopInfo(orderRequestBean);
						//----------------------------------------------- 修改店铺价格--end------------------------------------//
						if (!"00".equals(orderResponseBean.getResponseCode())) {
							resultFlag = false;
							throw new CMRollBackException(orderResponseBean.getErrMsg());
						}
						//-----------------------------------------------订单总价格为0时删除订单----begin----------------------------//
						orderRequestBean.setOrderInfoExample(orderInfoExample);
						orderResponseBean = orderService.queryOrders(orderRequestBean);
						TOrderInfo listDelOrderInfo = null;
						List listDelOrder = orderResponseBean.getResultList();
						if(listDelOrder.size() > 0){
							listDelOrderInfo = (TOrderInfo) listOrder.get(0);
						}else{
							throw new CMRollBackException("<<<<<<<<<<<<<<系统异常，订单出错，请重新添加订单！>>>>>>>>>>>>>>>>>>");
						}
						//当订单表数量及当地总价为空时，直接删除订单
						if(StringUtils.isBlank(listDelOrderInfo.getOrderGoodsCount()) || StringUtils.isBlank(listDelOrderInfo.getOrderPrice())){
							orderRequestBean.setOrderInfo(orderInfo);
							orderResponseBean = orderService.deleteOrder(orderRequestBean);
							if(!"00".equals(orderResponseBean.getResponseCode())){
								throw new CMRollBackException(orderResponseBean.getErrMsg());
							}
						}
						if(Integer.parseInt(listDelOrderInfo.getOrderGoodsCount()) <= 0 || Double.parseDouble(listDelOrderInfo.getOrderPrice()) <=0 ){
							orderRequestBean.setOrderInfo(orderInfo);
							orderResponseBean = orderService.deleteOrder(orderRequestBean);
							if(!"00".equals(orderResponseBean.getResponseCode())){
								throw new CMRollBackException(orderResponseBean.getErrMsg());
							}
						}
						//-----------------------------------------------订单总价格为0时删除订单----end----------------------------//
					} else {
						resultFlag = false;
						throw new CMRollBackException(orderResponseBean.getErrMsg());
					}
				} else {
					resultFlag = false;
					throw new CMRollBackException(orderResponseBean.getErrMsg());
				}
			}
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	/**
	 * 查询每日统计信息
	 */
	public OrderResponseBean queryDailyOrderInfo(
			TShopCategoryGoods shopCategoryGoods, TSysUserInfo sysUserInfo)
			throws CMException {
		String xFunctionName = "queryDailyOrderInfo";
		boolean resultFlag = false;
		//--------------------------------------------------------------对象实例化---begin----------------------------------------------------//
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		ShopOrderdailyStatistics shopOrderdailyStatistics = new ShopOrderdailyStatistics();
		//--------------------------------------------------------------对象实例化---end----------------------------------------------------//
		try {
			//---------------------------------------------------------- 前端传入对象验证---begin---------------------------------------------//
			if (null != sysUserInfo) {
				if (null != sysUserInfo.getUserShopId()) {
					shopOrderdailyStatistics.setShopId(sysUserInfo
							.getUserShopId());
				}
			} else {
				gLogger.end(xFunctionName);
				throw CMException.sys("UserShopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "orderInfo");
			}
			if (null != shopCategoryGoods) {
				if (null != shopCategoryGoods.getCategoryId()) {
					shopOrderdailyStatistics.setCategoryId(shopCategoryGoods
							.getCategoryId());
				}
			} else {
				gLogger.end(xFunctionName);
				throw CMException.sys("CategoryId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
						"shopCategoryGoods");
			}
			//---------------------------------------------------------- 前端传入对象验证---begin---------------------------------------------//
			//ShopOrderdailyStatistics对象赋值
			orderRequestBean.setShopOrderdailyStatistics(shopOrderdailyStatistics);
			//----------------------------------------------------------查询系统每日统计信息 ----begin--------------------------------------//
			orderResponseBean = orderService.queryShopOrderDailyStatistics(orderRequestBean);
			if (!"00".equals(orderResponseBean.getResponseCode())) {
				//----------------------------------------------------------查询系统每日统计信息 ----end--------------------------------------//
				throw new CMException(orderResponseBean.getErrMsg());
			}
		} catch (CMException e) {
			throw e;
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return orderResponseBean;
	}

	/**
	 * 提交订单  下单
	 */
	public boolean addOrder(TOrderInfo orderInfo,List<TOrderInfoShop> orderShopInfoList,List<TOrderInfoGoods> orderInfoGoodsList, TSysUserInfo sysUserInfo,String type)
			throws CMException, CMRollBackException {
		String xFunctionName = "addOrder";
		boolean resultFlag = false;
		Date sysDate = new Date();
		String  orderStatus = null;
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		String orderCode = null;
		try {
			//-----------------------------------------------------------------前端传入对象验证--begin-------------------------------------------------------//
			if (null == orderInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoList is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoList");
			}
			if (null == orderShopInfoList) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderShopInfoList is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderShopInfoList");
			}
			if (null == orderInfoGoodsList) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoGoodsList is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoGoodsList");
			}
			//-----------------------------------------------------------------前端传入对象验证--end-------------------------------------------------------//

			//-----------------------------------------------------------------查询订单信息---begin------------------------------------------------------//
			TOrderInfoExample orderExample = new TOrderInfoExample();
			orderExample.createCriteria().andOrderIdEqualTo(orderInfo.getOrderId());
			orderRequestBean.setOrderInfoExample(orderExample);
			orderResponseBean = orderService.queryOrders(orderRequestBean);
			//-------------------------------------------------------根据当前时间和状态获取改用户创建的订单信息--end------------------------------------//
			//-------------------------------------------------------
			if ("00".equals(orderResponseBean.getResponseCode())) {
				List listOrder = orderResponseBean.getResultList();
				if (listOrder.size() > 0) {
					//---当前用户已存在订单,对订单信息进行修改------begin-------------------------------------//
					TOrderInfo listOrderInfo = (TOrderInfo) listOrder.get(0);
					  orderStatus = listOrderInfo.getOrderRunStatus();
					  orderCode = listOrderInfo.getOrderCode();
					if("04".equals(orderStatus) ){
						throw CMException.sys("订单已失效，不能修改订单！",100000,"");

					}
//					if("03".equals(orderStatus)){
//						throw CMException.sys("订单短信已发送完毕，不能修改订单！",100000,"");
//					}
//					if("02".equals(orderStatus)){
//						throw CMException.sys("订单短信发送中，不能修改订单！",100000,"");
//					}
				}
			}else{
				throw CMException.sys("系统异常！",200000,"");
			}
			//-----------------------------------------------------------------查询订单信息---end------------------------------------------------------//

			
			//----------------------------------------------------------- 更改订单物品表信息--begin---------------------------------------------//
			for (TOrderInfoGoods orderInfoGoods : orderInfoGoodsList) {
				if (null == orderInfoGoods.getOrderId()) {
					gLogger.end(xFunctionName);
					throw CMException.sys("OrderId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"OrderId");
				}
				if (null == orderInfoGoods.getOrderGoodsId()) {
					gLogger.end(xFunctionName);
					throw CMException.sys("OrderGoodsId is null",
							ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
							"OrderGoodsId");
				}
				if (null == orderInfoGoods.getShopId()) {
					gLogger.end(xFunctionName);
					throw CMException.sys("ShopId is null",
							ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "ShopId");
				}
				if (null == orderInfoGoods.getShopGoodsId()) {
					gLogger.end(xFunctionName);
					throw CMException.sys("ShopGoodsId is null",
							ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
							"ShopGoodsId");
				}
				if (null == orderInfoGoods.getShopGoodsCount()) {
					gLogger.end(xFunctionName);
					throw CMException.sys("ShopGoodsCount is null",
							ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
							"ShopGoodsCount");
				}
				orderInfoGoods.setModifyUserCode(sysUserInfo.getUserCode());
				orderInfoGoods.setModifyTime(sysDate);
				orderRequestBean.setOrderInfoGoods(orderInfoGoods);
				orderResponseBean = orderService.modifyOrderGoodsInfo(orderRequestBean);
				if ("00".equals(orderResponseBean.getResponseCode())) {
					resultFlag = true;
					continue;
				} else {
					resultFlag = false;
					throw new CMRollBackException(orderResponseBean.getErrMsg());
				}
			}
			//----------------------------------------------------------- 更改订单物品表信息--end---------------------------------------------//
			//----------------------------------------------------------- 修改订单店铺表信息--begin------------------------------------------//
			for (TOrderInfoShop orderInfoShop : orderShopInfoList) {
				if (null == orderInfoShop.getOrderId()) {
					gLogger.end(xFunctionName);
					throw CMException.sys("OrderId is null",
							ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
							"ShopGoodsId");
				}
				if (null == orderInfoShop.getShopId()) {
					gLogger.end(xFunctionName);
					throw CMException.sys("ShopId is null",
							ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "ShopId");
				}

				orderRequestBean.setOrderInfoShop(orderInfoShop);
				orderResponseBean = orderService.getOrderShopPrice(orderRequestBean);
				if ("00".equals(orderResponseBean.getResponseCode())) {
					TOrderInfoShop tOrderInfoShop = new TOrderInfoShop();
					tOrderInfoShop.setPrice(orderResponseBean.getPrice());
					if(type.equals("1")|| type.equals("继续订购")){
						tOrderInfoShop.setOrderShopRunStatus(orderStatus);
					}
					else if(type.equals("2")|| type.equals("我要下单")){
						tOrderInfoShop.setOrderShopRunStatus(Constants.BUFFER_RUN_STATUS_01);
						orderRequestBean.setOrderInfoShop(orderInfoShop);
						orderResponseBean = orderService.queryOrderInfoShop(orderRequestBean);
						if(Constants.SELECT_SUCCESS_CODE.equals(orderResponseBean.getResponseCode())){
							List list = orderResponseBean.getResultList();
							if(CollectionUtils.isNotEmpty(list)){
								TOrderInfoShop listOrderInfoShop = (TOrderInfoShop) list.get(0);
								String captchas = this.createGetOrderCode(""+listOrderInfoShop.getOrderShopId());
								tOrderInfoShop.setCaptchas(captchas);
							}
						}
					}
					tOrderInfoShop.setModifyUserCode(sysUserInfo.getUserCode());
					tOrderInfoShop.setModifyTime(sysDate);
					tOrderInfoShop.setOrderShopQrcode(orderInfoShop.getOrderShopQrcode());
				
					orderRequestBean.setOrderInfoShop(tOrderInfoShop);
					TOrderInfoShopExample example = new TOrderInfoShopExample();
					example.createCriteria().andShopIdEqualTo(orderInfoShop.getShopId())
														.andOrderIdEqualTo(orderInfoShop.getOrderId());
					orderRequestBean.setOrderInfoShopExample(example);
					orderResponseBean = orderService.modifyOrderShopInfo(orderRequestBean);
					if ("00".equals(orderResponseBean.getResponseCode())) {
						resultFlag = true;
						continue;
					} else {
						resultFlag = false;
						throw new CMRollBackException(orderResponseBean.getErrMsg());
					}
				}
			}
			//----------------------------------------------------------- 修改订单店铺表信息--end------------------------------------------//
			//-----------------------------------------------------获取最终订单的总数量和总价格操作--begin----------------------------------//
			TOrderInfoShop tOrderInfoShop = new TOrderInfoShop();
			tOrderInfoShop.setOrderId(orderInfo.getOrderId());
			orderRequestBean.setOrderInfoShop(tOrderInfoShop);
			orderResponseBean = orderService.getOrderShopPrice(orderRequestBean);
			//------------------------------------------------------------获取最终订单的总数量和总价格操作---end-----------------------------//
			if ("00".equals(orderResponseBean.getResponseCode())) {
				//获取最终订单的总数量和总价格结果---begin
				orderInfo.setOrderGoodsCount(orderResponseBean.getOrderGoodsCount());
				orderInfo.setOrderPrice(orderResponseBean.getOrderGoodsPrice());
				//获取最终订单的总数量和总价格结果---end
				//------------------------------------------------------------修改订单信息--begin----------------------------------------------//
				//修改订单状态 
				//type 1:继续订购  2：下单
				if(type.equals("1")|| type.equals("继续订购")){
					orderInfo.setOrderRunStatus(orderStatus);
				}
				else if(type.equals("2")|| type.equals("我要下单")){
					orderInfo.setOrderRunStatus(Constants.BUFFER_RUN_STATUS_01);
				}
				
				orderInfo.setModifyUserCode(sysUserInfo.getUserCode());
				orderInfo.setModifyTime(sysDate);
				//-------------------------------------------------------不需要执行代码，但可能影响程序逻辑 暂时注释--begin--------------------------------//
//				TOrderInfoExample orderInfoExample = new TOrderInfoExample();
//				orderInfoExample.createCriteria().andOrderIdEqualTo(orderInfo.getOrderId());
//				orderRequestBean.setOrderInfoExample(orderInfoExample);
				//-------------------------------------------------------不需要执行代码，但可能影响程序逻辑 暂时注释--end--------------------------------//
				orderRequestBean.setOrderInfo(orderInfo);
				orderResponseBean = orderService.modifyOrderInfo(orderRequestBean);
				//------------------------------------------------------------修改订单信息--end----------------------------------------------//
				if ("00".equals(orderResponseBean.getResponseCode())) {
					resultFlag = true;
				} else {
					resultFlag = false;
					throw new CMRollBackException(orderResponseBean.getErrMsg());
				}
			} else {
				resultFlag = false;
				throw new CMRollBackException(orderResponseBean.getErrMsg());
			}
		} 
		catch(CMRollBackException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	public String createGetOrderCode(String orderShopID){
		StringBuffer sb = new StringBuffer();
		String tempOrderShopId  ="";
		if(orderShopID.length() < 4){
			for(int i =0 ; i < 4- orderShopID.length() ; i++){
				sb.append("0");
			}
			tempOrderShopId = orderShopID;
		}else{
			 tempOrderShopId =  orderShopID.substring(orderShopID.length()-4, orderShopID.length());
		}
		
		 String strA = "123456789";
		 strA = radomCode(strA, 2);
		 sb.append(tempOrderShopId);
		 sb.append(strA);
		 return sb.toString();
	}
	private static String radomCode(String ssource, int m)
    {
        Random r = new Random();
        char src[] = ssource.toCharArray();
        char buf[] = new char[m];
        for(int i = 0; i < m; i++)
        {
            int rnd = Math.abs(r.nextInt()) % src.length;
            buf[i] = src[rnd];
        }

        return new String(buf);
    }
	
	@Override
	public OrderResponseBean selectMyOrderGoods(TOrderInfo orderInfo,TOrderInfoShop orderInfoShop) 
					throws CMException {
		String xFunctionName = "selectMyOrderGoods";
		OrderResponseBean responseBean = new OrderResponseBean();
		OrderRequestBean requestBean = new OrderRequestBean();
		MyOrder myOrder = new MyOrder();
		try {
			if(null != orderInfo.getOrderId()){
				myOrder.setOrderId(orderInfo.getOrderId());
			}
			if(null != orderInfoShop.getOrderShopId()){
				myOrder.setShopId(orderInfoShop.getOrderShopId());
			}
			if(StringUtils.isNotBlank(orderInfo.getCreateUserCode())){
				myOrder.setUserCode(orderInfo.getCreateUserCode());
			}
			requestBean.setMyOrder(myOrder);
			responseBean = orderService.queryMyOrder(requestBean);
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return responseBean;
	}
	
	public OrderResponseBean selectMyOrderGoods2(TOrderInfo orderInfo,TOrderInfoShop orderInfoShop) 
			throws CMException {
String xFunctionName = "selectMyOrderGoods";
OrderResponseBean responseBean = new OrderResponseBean();
OrderRequestBean requestBean = new OrderRequestBean();
MyOrder myOrder = new MyOrder();
try {
	if(null != orderInfo.getOrderId()){
		myOrder.setOrderId(orderInfo.getOrderId());
	}
	if(null != orderInfoShop.getOrderShopId()){
		myOrder.setShopId(orderInfoShop.getOrderShopId());
	}
	if(StringUtils.isNotBlank(orderInfo.getCreateUserCode())){
		myOrder.setUserCode(orderInfo.getCreateUserCode());
	}
	requestBean.setMyOrder(myOrder);
	responseBean = orderService.queryMyOrder2(requestBean);
} catch (Exception e) {
	gLogger.exception(xFunctionName, e);
} finally {
	gLogger.end(xFunctionName);
}
return responseBean;
}

	@Override
	public OrderResponseBean selectOrderGoodsByOrderIdAndShopId(MyOrder order) {
		String xFunctionName = "selectOrderGoodsByOrSpID";
		//----------------------------------------------------------对象初始化---begin----------------------------------------------------//
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		TOrderInfoShop orderInfoShop = new TOrderInfoShop();
		MyOrder listOrderInfo = new MyOrder();
		//----------------------------------------------------------对象初始化---end----------------------------------------------------//
		try {
			//-------------------------------------------------------前端传入对象验证---begin-------------------------------------------//
			if (null == order) {
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoShop is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoShop");
			}
			if (null == order.getOrderId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoShop");
			}
			if (null == order.getShopId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoShop");
			}
			//-------------------------------------------------------前端传入对象验证---begin-------------------------------------------//
			//MyOrder
			orderRequestBean.setMyOrder(order);
			orderResponseBean = orderService.queryMyOrder(orderRequestBean);

			if (!"00".equals(orderResponseBean.getResponseCode())) {
				throw new CMException(orderResponseBean.getErrMsg());
			} else {
				List resultList = orderResponseBean.getResultList();
				int size = resultList.size();
				listOrderInfo = (MyOrder) resultList.get(size - 1);
			}
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}

		return orderResponseBean;
	}
	
	
	public String selectOrderStatus(Integer orderId) throws CMException{
		String xFunctionName = "selectOrderGoodsByOrSpID";
		String orderStatus = null;
		try{
			if(null == orderId){
				gLogger.end(xFunctionName);
				throw CMException.sys("orderId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderId");
			}
			OrderRequestBean orderRequestBean = new OrderRequestBean();
			TOrderInfoExample orderInfoExample = new TOrderInfoExample();
			orderInfoExample.createCriteria().andOrderIdEqualTo(orderId);
			orderRequestBean.setOrderInfoExample(orderInfoExample);
			OrderResponseBean orderResponseBean = orderService.queryOrders(orderRequestBean);
			if(CollectionUtils.isNotEmpty(orderResponseBean.getResultList())){
				TOrderInfo orderInfo = (TOrderInfo) orderResponseBean.getResultList().get(0);
				orderStatus = orderInfo.getOrderRunStatus();
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return orderStatus;
	}
	
	public boolean updateOrderShop(TOrderInfoShop orderInfoShop)  throws CMException,CMRollBackException {
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		boolean flag = false;
		String xFunctionName = "updateOrderShop";
		try{
			if(null == orderInfoShop){
				gLogger.end(xFunctionName);
				throw CMException.sys("orderInfoShop is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoShop");
			}
			if(null == orderInfoShop.getOrderId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoShop");
			}
			if(null == orderInfoShop.getShopId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoShop");
			}
			if(null == orderInfoShop.getOrderShopQrcode()){
				gLogger.end(xFunctionName);
				throw CMException.sys("OrderShopQrcode is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"orderInfoShop");
			}
			
			TOrderInfoShopExample example = new TOrderInfoShopExample();
			example.createCriteria().andOrderIdEqualTo(orderInfoShop.getOrderId())
												.andShopIdEqualTo(orderInfoShop.getShopId());
			orderRequestBean.setOrderInfoShop(orderInfoShop);
			orderRequestBean.setOrderInfoShopExample(example);
			orderResponseBean = orderService.modifyOrderShopInfo(orderRequestBean);
			if("00".equals(orderResponseBean.getResponseCode())){
				flag = true;
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
	
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderOperateBean> queryOrderOperateDetail(String searchDate) throws CMException{
		final String xFunctionName = "queryOrderOperateDetil";
		gLogger.begin(xFunctionName);

		List<OrderOperateBean> resultList = null;
		try {

			OrderRequestBean requestBean = new OrderRequestBean();
			requestBean.setSearchDate(searchDate);
			
			OrderResponseBean responseBean = orderService.queryOrderOperateDetil(requestBean);
			if(!"00".equals(responseBean.getResponseCode())){
				throw CMException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, "queryOrderOperateDetil");
			}
			
			resultList = (List<OrderOperateBean>)responseBean.getResultList();
			
		} catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultList;
	}
	/**
	 * 
	 * 通过手机号码前四位和验证码查询订单
	 * @param mdn
	 * @param authcode
	 * @return
	 * @author wangwenwei
	 * @createtime 2013-9-24 上午10:53:30
	 */
	public OrderResponseBean selectOrderGoodsByMdnAndAuthcode(String mdn,String authcode) {
		String xFunctionName = "selectOrderGoodsByOrSpID";
		//----------------------------------------------------------对象初始化---begin----------------------------------------------------//
		OrderRequestBean orderRequestBean = new OrderRequestBean();
		OrderResponseBean orderResponseBean = null;
		//----------------------------------------------------------对象初始化---end----------------------------------------------------//
		try {
			//-------------------------------------------------------前端传入对象验证---begin-------------------------------------------//
			if (null == mdn) {
				gLogger.end(xFunctionName);
				throw CMException.sys("mdn is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"mdn");
			}
			if (null == authcode) {
				gLogger.end(xFunctionName);
				throw CMException.sys("authcode is null",ExceptionConstants.ILLEGAL_ARGUMENT_CODE,"authcode");
			}
			//-------------------------------------------------------前端传入对象验证---begin-------------------------------------------//
			//MyOrder
			orderRequestBean.setShortPhoneNumber(mdn);
			orderRequestBean.setCaptchas(authcode);
			orderResponseBean = orderService.verifyPhoneNumberAndCode(orderRequestBean);

			if (!"00".equals(orderResponseBean.getResponseCode())) {
				throw new CMException(orderResponseBean.getErrMsg());
			}
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}

		return orderResponseBean;
	}

	@Override
	public List<OrderOperateBean> queryInvalidUserOrderOperateDetail(String searchDate)
			throws CMException {
		final String xFunctionName = "queryInvalidUserOrderOperateDetail";
		gLogger.begin(xFunctionName);

		List<OrderOperateBean> resultList = null;
		try {

			OrderRequestBean requestBean = new OrderRequestBean();
			requestBean.setSearchDate(searchDate);
			
			OrderResponseBean responseBean = orderService.queryInvalidUserOrderOperateDetil(requestBean);
			if(!"00".equals(responseBean.getResponseCode())){
				throw CMException.sys(responseBean.getErrMsg(), ExceptionConstants.SERVICE_CODE, "queryOrderOperateDetil");
			}
			
			resultList = (List<OrderOperateBean>)responseBean.getResultList();
			
		} catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultList;
	}
	
	//--------------------------------------------------------------------get/set------------------------------------------------------//
	public IShopService getShopService() {
		return shopService;
	}

	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}

	public IGoodsService getGoodService() {
		return goodService;
	}

	public void setGoodService(IGoodsService goodService) {
		this.goodService = goodService;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}


	

}
