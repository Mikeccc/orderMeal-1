package app.cn.qtt.bm.manage;

import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.ShopRequestBean;
import app.cn.qtt.bm.service.pojo.ShopResponseBean;

/**
 * 商店管理接口
 * @author xupj
 * @date 2013-6-9
 */
public interface IShopMgr {
	
	/**
	 * 查询有效店铺列表(前端)
	 *方法名称 selectShops
	 *方法描述
	 * @param shopInfo
	 * @Date 2013-6-9
	 * @author xupj
	 * @return 
	 * @throws CMException 
	 */
	public TShopInfo queryShopInfoById(Integer shopId) throws CMException ;
	/**
	 * 查询有效店铺列表(前端)
	 *方法名称 selectShops
	 *方法描述
	 * @param shopInfo
	 * @Date 2013-6-9
	 * @author xupj
	 * @return 
	 */
	public ShopResponseBean selectShops(TShopInfo shopInfo) throws CMException;
	/**
	 * 添加店铺
	 *方法名称 addShop
	 *方法描述
	 * @param shopInfo
	 * @param sysUserInfo
	 * @return
	 * @Date 2013-6-9
	 * @author xupj
	 */
	public boolean addShop(TShopInfo shopInfo,TSysUserInfo sysUserInfo,String tempPath) throws CMException,CMRollBackException;
	/**
	 * 修改店铺
	 *方法名称 modifyShop
	 *方法描述
	 * @param shopInfo
	 * @param sysUserInfo
	 * @return
	 * @Date 2013-6-9
	 * @author xupj
	 */
	public boolean modifyShop(TShopInfo shopInfo,TSysUserInfo sysUserInfo,String tempPath) throws CMException,CMRollBackException;
	/**
	 * 物理删除店铺
	 *方法名称 deleteShop
	 *方法描述
	 * @param shopInfo
	 * @return
	 * @Date 2013-6-9
	 * @author xupj
	 */
	public boolean deleteShop(TShopInfo shopInfo) throws CMException,CMRollBackException;
	/**
	 * 暂停/启用店铺
	 *方法名称 toggleShop
	 *方法描述
	 * @param shopInfo
	 * @return
	 * @Date 2013-6-9
	 * @author xupj
	 */
	public boolean toggleShop(TShopInfo shopInfo, TSysUserInfo sysUserInfo, String shopCurrentStatus) throws CMException,CMRollBackException;
	/**
	 *模糊(分页)查询店铺(后端使用)
	 *方法名称 selectFuzzyPagesShops
	 *方法描述
	 * @param shopInfo
	 * @return
	 * @Date 2013-6-12
	 * @author xupj
	 */
	public ShopResponseBean selectFuzzyPagesShops(ShopRequestBean requestBean) throws CMException;
	/**
	 * 验证电话号码唯一
	 *方法名称 validPhoneShopAndUser
	 *方法描述
	 * @param shopInfo
	 * @return
	 * @throws CMException
	 * @Date 2013-6-28
	 * @author xupj
	 */
	public boolean validPhoneShopAndUser(TShopInfo shopInfo) throws CMException;
	
	public boolean deleteShopRecords(TShopInfo shopInfo,TSysUserInfo sysUserInfo) throws CMException, CMRollBackException;
	
	
}
