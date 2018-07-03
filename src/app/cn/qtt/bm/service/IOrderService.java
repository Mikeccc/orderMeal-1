package app.cn.qtt.bm.service;

import app.cn.qtt.bm.service.pojo.OrderRequestBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

/**
 * 
 * @author GeYanmeng
 * @Description 订单管理接口
 * @date 2013-6-9 下午4:20:38
 * @type IOrderService
 * @project BespeakMeal
 */
public interface IOrderService {
	/**
	 * 查询订单信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean queryOrders(OrderRequestBean requestBean);
	
	/**
	 * 增加订单
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean addOrderInfo(OrderRequestBean requestBean);
	
	/**
	 * 增加订单,店铺关联信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean addOrderShopInfo(OrderRequestBean requestBean);
	
	/**
	 * 增加订单,商品关联信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean addOrderGoodsInfo(OrderRequestBean requestBean);
	
	/**
	 * 修改订单信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean modifyOrderInfo(OrderRequestBean requestBean);
	
	/**
	 * 修改订单,商店关联信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean modifyOrderShopInfo(OrderRequestBean requestBean);
	
	/**
	 * 修改订单,商品关联信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean modifyOrderGoodsInfo(OrderRequestBean requestBean);
	
	/**
	 * 修改订单状态
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean modifyOrderRunStatus(OrderRequestBean requestBean);
	
	/**
	 * 修改主订单状态
	 * @param requestBean
	 * @return
	 * @author Gabriel
	 * @createtime 2013-11-13 上午11:32:39
	 */
	public OrderResponseBean modifyOrderRunStatusForJob(OrderRequestBean requestBean);
	
	/**
	 * 修改订单商店信息的状态
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author Gabriel
	 * @date 2013-7-1
	 */
	public OrderResponseBean modifyOrderShopInfoRunStatus(OrderRequestBean requestBean);
	
	/**
	 * 修改订单商店信息状态job专用
	 * @param requestBean
	 * @return
	 * @author Gabriel
	 * @createtime 2013-11-13 上午11:05:32
	 */
	public OrderResponseBean modifyOrderShopInfoRunStatusForJob(OrderRequestBean requestBean);
	
	/**
	 * 删除订单
	 * @param requestBean
	 * @return
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean deleteOrder(OrderRequestBean requestBean);
	
	/**
	 * 删除订单,商店关联信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean deleteOrderShopInfo(OrderRequestBean requestBean);
	
	/**
	 * 删除订单,商品关联信息
	 * @param requestBean
	 * @return
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean deleteOrderGoodsInfo(OrderRequestBean requestBean);
	
	/**
	 * 订单日统计(发送内容)
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public OrderResponseBean queryDailyOrderInfo(OrderRequestBean requestBean);
	
	/**
	 * 查询订单商店信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-17
	 */
	public OrderResponseBean queryOrderInfoShop(OrderRequestBean requestBean);
	
	/**
	 * 查询订单商品信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-17
	 */
	public OrderResponseBean queryOrderInfoGoods(OrderRequestBean requestBean);
	
	/**
	 * 查询商家每日统计信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-18
	 */
	public OrderResponseBean queryShopOrderDailyStatistics(OrderRequestBean requestBean);
	
	/**
	 * 更新每日统计信息(发给商家)
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	public OrderResponseBean updateShopDailyInfo(OrderRequestBean requestBean);
	
	/**
	 * 查询我的饭盒
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-25
	 */
	public OrderResponseBean queryMyOrder(OrderRequestBean requestBean);
	
	/**
	 * 获取订单单位商店下的总价
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-26
	 */
	public OrderResponseBean getOrderShopPrice(OrderRequestBean requestBean);
	
	/**
	 * 查询订单详情
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author Gabriel
	 * @date 2013-6-26
	 */
	public OrderResponseBean queryShopOrderDetail(OrderRequestBean requestBean);
	
	/**
	 * 插入到每日统计发送给商家信息表中
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author Gabriel
	 * @date 2013-6-28
	 */
	public OrderResponseBean addShopDailyInfo(OrderRequestBean requestBean);
	
	/**
	 * 通过Example查询订单商店信息
	 * @param requestBean
	 * @return OrderResponseBean
	 * @author Gabriel
	 * @date 2013-7-1
	 */
	public OrderResponseBean queryOrderInfoShopByExample(OrderRequestBean requestBean);
	
	public OrderResponseBean queryMyOrder2(OrderRequestBean requestBean) ;
	
	public OrderResponseBean queryMyOrder3(OrderRequestBean requestBean);
	/**
	 * 订单操作详情 包括领餐和刷二维码
	 * @param requestBean
	 * @return OrderOperateBean
	 */
	public OrderResponseBean queryOrderOperateDetil(OrderRequestBean requestBean);
	
	public OrderResponseBean queryMyOrderByUserCodeAndTime(OrderRequestBean requestBean) ;
	
	public OrderResponseBean queryInvalidUserOrderOperateDetil(OrderRequestBean requestBean) ;
	
	/**
	 * 通过验证码和手机后4位进行领餐
	 * @param requestBean
	 * @return 返回1表示可以领餐，0无法完成领餐
	 * @author Gabriel
	 * @createtime 2013-9-17 上午10:50:01
	 */
	public OrderResponseBean verifyPhoneNumberAndCode(OrderRequestBean requestBean);
		
}
