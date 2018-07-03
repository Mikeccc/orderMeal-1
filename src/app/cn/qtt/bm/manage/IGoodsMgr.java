package app.cn.qtt.bm.manage;

import java.util.List;

import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopGoodsTime;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;

public interface IGoodsMgr {
	
	/**
	 * 根据店铺id查询分类
	 *方法名称 queryGoodsCategoryByShopId
	 *方法描述
	 * @return
	 * @Date 2013-6-12
	 * @author xupj
	 */
	public GoodsResponseBean queryGoodsCategoryByShopId(TShopInfo shopInfo) throws CMException;
	/**
	 * 根据商品分类查询商品
	 *方法名称 queryGoodsByCategory
	 *方法描述
	 * @param shopCategoryGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public GoodsResponseBean queryGoodsByCategory(GoodsRequestBean goodsRequestBean) throws CMException;
	/**
	 * 添加商品分类信息
	 *方法名称 addGoodsCategory
	 *方法描述
	 * @param shopCategoryGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean addGoodsCategory(TShopCategoryGoods shopCategoryGoods) throws CMException,CMRollBackException;
	/**
	 * 删除商品分类信息
	 *方法名称 deleteGoodsCategory
	 *方法描述
	 * @param shopCategoryGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean deleteGoodsCategory(TShopCategoryGoods shopCategoryGoods) throws CMException,CMRollBackException;
	/**
	 * 添加商品信息
	 *方法名称 addGoods
	 *方法描述
	 * @param shopGoods
	 * @param shopCategoryGoodsLink
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean addGoods(TShopGoods shopGoods, TShopCategoryGoodsLink shopCategoryGoodsLink, TSysUserInfo sysUserInfo) throws CMException,CMRollBackException;
	/**
	 * 删除商品信息
	 *方法名称 deleteGoods
	 *方法描述
	 * @param shopGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean deleteGoods(TShopGoods shopGoods) throws CMException,CMRollBackException;
	/**
	 * 根据商品编号查询商品信息
	 *方法名称 queryGoodsInfoById
	 *方法描述
	 * @param shopGoods
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public GoodsResponseBean queryGoodsInfoById(TShopGoods shopGoods) throws CMException;
	/**
	 * 修改商品信息
	 *方法名称 modifyGoods
	 *方法描述
	 * @param shopGoods
	 * @param shopCategoryGoodsLink
	 * @return
	 * @Date 2013-6-13
	 * @author xupj
	 */
	public boolean modifyGoods(TShopGoods shopGoods, TShopCategoryGoodsLink shopCategoryGoodsLink, TSysUserInfo sysUserInfo) throws CMException,CMRollBackException;

	/**
	 * 修改类别
	 *方法名称 modifyCategory
	 *方法描述
	 * @param shopCategoryGoods
	 * @return
	 * @throws CMException
	 * @throws CMRollBackException
	 * @Date 2013-6-18
	 * @author xupj
	 */
	public boolean modifyCategory(TShopCategoryGoods shopCategoryGoods) throws CMException,CMRollBackException;
	
	/**
	 * 
	* @Title: querySysFileByFileId
	* @Description: 根据文件ID查询上传文件
	* @param @param goodsRequestBean
	* @param @return
	* @param @throws CMException
	* @param @throws CMRollBackException    设定文件
	* @return GoodsResponseBean    返回类型
	* @throws
	 */
	public GoodsResponseBean querySysFileByFileId(GoodsRequestBean goodsRequestBean) throws CMException,CMRollBackException;
	
	/**
	 * 
	* @Title: querySysFileByFileId
	* @Description: 判断菜单名是否重复
	* @param @param goodsRequestBean
	* @param @return
	* @param @throws CMException
	* @param @throws CMRollBackException    设定文件
	* @return GoodsResponseBean    返回类型
	* @throws
	 */
	
	public boolean isGoodsNameDuplicate(String goodsName,TShopInfo shopInfo)throws CMException, CMRollBackException;
	/**
	 * 
	* @Title: querySysFileByFileId
	* @Description: 判断分组名是否重复
	* @param @param goodsRequestBean
	* @param @return
	* @param @throws CMException
	* @param @throws CMRollBackException    设定文件
	* @return GoodsResponseBean    返回类型
	* @throws
	 */
	public boolean isGoodsCategoryNameDuplicate(String categoryName,TShopInfo shopInfo)throws CMException, CMRollBackException;
	/**
	 * 
	* @Title: querySysFileByFileId
	* @Description: 根据店铺查询其菜单分组总数
	* @param @param goodsRequestBean
	* @param @return
	* @param @throws CMException
	* @param @throws CMRollBackException    设定文件
	* @return GoodsResponseBean    返回类型
	* @throws
	 */
	public int queryCountCategoryNum(TShopInfo shopInfo)throws CMException, CMRollBackException;
}
