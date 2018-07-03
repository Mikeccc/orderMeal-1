package app.cn.qtt.bm.manage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.OrderOperateBean;
import app.cn.qtt.bm.model.TOrderInfo;
import app.cn.qtt.bm.model.TOrderInfoGoods;
import app.cn.qtt.bm.model.TOrderInfoShop;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;

public interface IOrderMgr {
	/**
	 * 订单列表查询
	 *方法名称 selectOrders
	 *方法描述
	 * @param orderInfo
	 * @param orderInfoShop
	 * @param sysUserInfo
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public OrderResponseBean selectOrders(TOrderInfo orderInfo)throws CMException;
	/**
	 * 查询我的饭盒
	 *方法名称 selectMyOrderGoods
	 *方法描述
	 * @return
	 * @throws CMException
	 * @Date 2013-6-25
	 * @author xupj
	 */
	public OrderResponseBean selectMyOrderGoods(TOrderInfo orderInfo, TOrderInfoShop orderInfoShop)throws CMException;
	
	/**
	 * 查看当前用户当前店铺订单商品
	 *方法名称 selectOrderGoodsByOrSpID
	 *方法描述
	 * @param orderInfoShop
	 * @return
	 * @Date 2013-6-28
	 * @author xupj
	 */
	public OrderResponseBean selectOrderGoodsByOrderIdAndShopId(MyOrder order); 
	/**
	 * 添加购物车
	 *方法名称 addShoppingCart
	 *方法描述
	 * @param orderInfo
	 * @param orderInfoShop
	 * @param sysUserInfo
	 * @param orderInfoGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public Map addShoppingCart(TOrderInfo orderInfo, TOrderInfoShop orderInfoShop,TSysUserInfo sysUserInfo,TOrderInfoGoods orderInfoGoods,String shopGoodsPrice)throws CMException,CMRollBackException;
	/**
	 * 添加订单
	 *方法名称 addOrder
	 *方法描述
	 * @param orderInfoList
	 * @param orderShopInfoList
	 * @param orderInfoGoodsList
	 * @param sysUserInfo
	 * @return
	 * @throws CMException
	 * @throws CMRollBackException
	 * @Date 2013-6-17
	 * @author xupj
	 */
	public boolean addOrder(TOrderInfo orderInfo, List<TOrderInfoShop> orderShopInfoList, List<TOrderInfoGoods> orderInfoGoodsList, TSysUserInfo sysUserInfo,String type) throws CMException,CMRollBackException;;
	/**
	 * 
	 * 订单修改
	 *方法名称 modifyOrder
	 *方法描述
	 * @param orderInfo
	 * @param orderInfoShop
	 * @param sysUserInfo
	 * @param orderInfoGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	//public boolean modifyOrder(TOrderInfo orderInfo, TOrderInfoShop orderInfoShop,TSysUserInfo sysUserInfo,TOrderInfoGoods orderInfoGoods) throws CMException,CMRollBackException;
	/**
	 * 订单状态修改
	 *方法名称 modifyOrderRunStatus
	 *方法描述
	 * @param orderInfo
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean modifyOrderRunStatus(TOrderInfo orderInfo, TSysUserInfo sysUserInfo,Integer shopId) throws CMException,CMRollBackException;
	/**
	 * 删除订单
	 *方法名称 deleteOrder
	 *方法描述
	 * @param orderInfo
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean deleteOrder(TOrderInfo orderInfo) throws CMException,CMRollBackException;
	/**
	 * 删除订单店铺
	 *方法名称 deleteOrderShop
	 *方法描述
	 * @param orderInfo
	 * @param orderInfoShop
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean deleteOrderShop(TOrderInfo orderInfo, TOrderInfoShop orderInfoShop) throws CMException,CMRollBackException;
	/**
	 * 删除订单商品
	 *方法名称 deleteOrderGoods
	 *方法描述
	 * @param orderInfo
	 * @param orderInfoGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean deleteOrderGoods(TOrderInfo orderInfo,TOrderInfoGoods orderInfoGoods,TSysUserInfo sysUserInfo) throws CMException,CMRollBackException ;
	/**
	 * 订单日统计
	 *方法名称 queryDailyOrderInfo
	 *方法描述
	 * @param orderInfo
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public OrderResponseBean queryDailyOrderInfo(TShopCategoryGoods shopCategoryGoods,TSysUserInfo sysUserInfo) throws CMException;
	
	/**
	 * 查询订单
	 *方法名称 selectOrderStatus
	 *方法描述
	 * @param orderId
	 * @return
	 * @throws CMException
	 * @Date 2013-7-12
	 * @author xupj
	 */
	public String selectOrderStatus(Integer orderId) throws CMException;
	
	/**
	 * 更新店铺
	 *方法名称 updateOrderShop
	 *方法描述
	 * @param orderInfoShop
	 * @return
	 * @throws CMException
	 * @throws CMRollBackException
	 * @Date 2013-7-12
	 * @author xupj
	 */
	public boolean updateOrderShop(TOrderInfoShop orderInfoShop)  throws CMException,CMRollBackException ;
	
	/**
	 * 取消订单
	 *方法名称 modifyOrderCancelStatus
	 *方法描述
	 * @param orderInfo
	 * @param sysUserInfo
	 * @return
	 * @throws CMException
	 * @throws CMRollBackException
	 * @Date 2013-8-1
	 * @author xupj
	 */
	public boolean modifyOrderCancelStatus(TOrderInfo orderInfo,TSysUserInfo sysUserInfo) throws CMException, CMRollBackException;
	/**
	 * 查询有效订单
	 *方法名称 selectMyOrderGoods2
	 *方法描述
	 * @param orderInfo
	 * @param orderInfoShop
	 * @return
	 * @throws CMException
	 * @Date 2013-8-1
	 * @author xupj
	 */
	public OrderResponseBean selectMyOrderGoods2(TOrderInfo orderInfo,TOrderInfoShop orderInfoShop) throws CMException;
	
	public OrderResponseBean queryDetailOrderInfoByTime(String userCode, Date createTime, List orderStatusList) throws CMException,Exception;
	
	/**
	 * 查询订单明细
	 * @param searchDate 订单日期
	 * @return 订单明细信息
	 * @throws CMException
	 * @author tipx
	 * @createtime 2013-8-31 下午9:01:41
	 */
	public List<OrderOperateBean> queryOrderOperateDetail(String searchDate) throws CMException;
	
	public List<OrderOperateBean> queryInvalidUserOrderOperateDetail(String searchDate) throws CMException;
}
