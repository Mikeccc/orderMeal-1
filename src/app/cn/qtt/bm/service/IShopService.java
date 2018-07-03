package app.cn.qtt.bm.service;

import app.cn.qtt.bm.service.pojo.ShopRequestBean;
import app.cn.qtt.bm.service.pojo.ShopResponseBean;

/**
 * 
 * @author GeYanmeng
 * @Description 店铺相关服务接口
 * @date 2013-6-9 下午3:08:21
 * @type IShopService
 * @project BespeakMeal
 */
public interface IShopService {
	/**
	 * 查询店铺（前端用）
	 * @param requestBean
	 * @return ShopResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public ShopResponseBean queryShops(ShopRequestBean requestBean);
	
	/**
	 * 新增店铺信息
	 * @param requestBean
	 * @return ShopResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public ShopResponseBean addShopInfo(ShopRequestBean requestBean);
	
	/**
	 * 修改店铺信息
	 * @param requestBean
	 * @return
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public ShopResponseBean modifyShopInfo(ShopRequestBean requestBean);
	
	/**
	 * 模糊(分页)查询店铺(后端使用)
	 * @param requestBean
	 * @return ShopResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public ShopResponseBean queryFuzzyPagesShops(ShopRequestBean requestBean);
	
	/**
	 * 删除店铺信息(逻辑删除)
	 * @param requestBean
	 * @return ShopResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public ShopResponseBean deleteShopInfo(ShopRequestBean requestBean);
	
	/**
	 * 删除店铺信息(物理删除)
	 * @param requestBean
	 * @return ShopResponseBean
	 * @author Gabriel
	 * @date 2013-7-1
	 */
	public ShopResponseBean deleteShopInfoPermanently(ShopRequestBean requestBean);
	
	/**
	 * 根据订单ID查询商店详情
	 * @param requestBean
	 * @return ShopResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public ShopResponseBean selectOrderShopByOrderId(ShopRequestBean requestBean);

	ShopResponseBean deleteShopInfoRecords(ShopRequestBean requestBean);
	
}
