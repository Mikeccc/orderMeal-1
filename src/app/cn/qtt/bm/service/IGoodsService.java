package app.cn.qtt.bm.service;

import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;

/**
 * 商品信息服务接口
 * @author GeYanmeng
 * @Description
 * @date 2013-6-9 下午3:32:01
 * @type IGoodsService
 * @project BespeakMeal
 */
public interface IGoodsService extends ICommonService{
	/**
	 * 根据店铺id查询分类
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean queryGoodsCategoryByShopId(GoodsRequestBean requestBean);
	/**
	 * 根据店铺商品分类id/(有效供应时间)查询商品(分页)
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean queryGoodsByCategory(GoodsRequestBean requestBean);
	
	/**
	 * 新增商品分组
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean addGoodsCategory(GoodsRequestBean requestBean);
	
	/**
	 * 删除商品分组
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean deleteGoodsCategory(GoodsRequestBean requestBean);
	
	/**
	 * 新增商品信息
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean addGoodsInfo(GoodsRequestBean requestBean);
	
	/**
	 * 增加商品分类和商品关联信息
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean addLinkGoodsCategory(GoodsRequestBean requestBean);
	
	/**
	 * 删除商品信息(逻辑删除)
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean deleteGoodsInfo(GoodsRequestBean requestBean);
	
	/**
	 * 删除商品信息(物理删除)
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-18
	 */
	public GoodsResponseBean deleteGoodsInfoPhysical(GoodsRequestBean requestBean);
	
	/**
	 * 删除商品关联和商品信息
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean deleteLinkGoodsCategory(GoodsRequestBean requestBean);
	
	/**
	 * 查询商品详情
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean queryGoodsInfoById(GoodsRequestBean requestBean);
	/**
	 * 根据商品名称和店铺ID查询商品
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean queryGoodsInfoByGoodsName(GoodsRequestBean requestBean);
	/**
	 * 根据分组名称和店铺ID查询分组
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean queryGoodsCategoryByCategoryName(GoodsRequestBean requestBean);
	/**
	 * 修改商品信息
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean modifyGoodsInfo(GoodsRequestBean requestBean);
	
	/**
	 * 修改商品关联和商品信息
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-9
	 */
	public GoodsResponseBean modifyLinkGoodsCategory(GoodsRequestBean requestBean);
	
	/**
	 * 根据订单查询商品详情
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-13
	 */
	public GoodsResponseBean selectShopGoodsInfoByOrderId(GoodsRequestBean requestBean);
	
	/**
	 * 查询商品分类关联信息
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-18
	 */
	public GoodsResponseBean queryLinkGoodsCategory(GoodsRequestBean requestBean);
	
	/**
	 * 修改商品分组
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-18
	 */
	public GoodsResponseBean modifyGoodsCategory(GoodsRequestBean requestBean);
	
	/**
	 * 增加商品供应时间关联信息
	 * @param requestBean
	 * @return GoodsResponseBean
	 * @author GeYanmeng
	 * @date 2013-6-21
	 */
	public GoodsResponseBean addShopGoodsTime(GoodsRequestBean requestBean);
	/**
	 * 
	* @Title: queryGoodsCategoryByShopI
	* @Description: 按店铺ID统计分组数
	* @param @param requestBean
	* @param @return    设定文件
	* @return GoodsResponseBean    返回类型
	* @throws
	 */
	public GoodsResponseBean queryGoodsCategoryCountNumByShopId(GoodsRequestBean requestBean);
	
	/**
	 * 查询包含图片商品信息
	 *方法名称 queryImgGoodsInfoById
	 *方法描述
	 * @param requestBean
	 * @return
	 * @Date 2014-1-10
	 * @author xupj
	 */
	public GoodsResponseBean queryImgGoodsInfoById(GoodsRequestBean requestBean);
	
}
