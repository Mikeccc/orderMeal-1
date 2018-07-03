package app.cn.qtt.bm.manage.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.utils.TxUUIDGenerator;
import app.cn.qtt.bm.manage.IShopMgr;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TShopInfoExample;
import app.cn.qtt.bm.model.TSysFile;
import app.cn.qtt.bm.model.TSysLinkUserRole;
import app.cn.qtt.bm.model.TSysLinkUserRoleExample;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.model.TSysUserInfoExample;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.IShopService;
import app.cn.qtt.bm.service.IUserService;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;
import app.cn.qtt.bm.service.pojo.ShopRequestBean;
import app.cn.qtt.bm.service.pojo.ShopResponseBean;
import app.cn.qtt.bm.service.pojo.UserRequestBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;

public class ShopMgr extends BaseService implements IShopMgr {

	private IShopService shopService;
	private IUserService userService;
	private IGoodsService goodsService;

	private SystemCommonMgr systemCommonMgr;

	/**
	 * 根据shopId查商店对象
	 * 
	 * @param shopId
	 * @return
	 * @throws CMException
	 */
	public TShopInfo queryShopInfoById(Integer shopId) throws CMException {
		String xFunctionName = "findShopInfoById";
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		ShopResponseBean shopResponseBean = null;
		TShopInfo tShopInfo = null;
		try {
			if (null == shopId) {
				gLogger.end(xFunctionName);
				throw CMException.sys("shopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopId");
			}

			TShopInfoExample shopInfoExample = new TShopInfoExample();
			shopInfoExample.createCriteria().andShopIdEqualTo(shopId);

			shopRequestBean.setShopInfoExample(shopInfoExample);
			shopResponseBean = shopService.queryShops(shopRequestBean);
			List<?> list = shopResponseBean.getResultList();
			if (CollectionUtils.isNotEmpty(list)) {
				tShopInfo = (TShopInfo) list.get(0);
				if(tShopInfo!=null){
					Integer imgId = tShopInfo.getShopImgFileId();
					if(imgId!=null){
						TSysFile sysFile = new TSysFile();
						sysFile.setFileId(imgId);
						GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
						goodsRequestBean.setSysFile(sysFile);
						GoodsResponseBean goodsResponseBean1 = goodsService.querySysFileByFileId(goodsRequestBean);
						if(goodsResponseBean1!=null&&goodsResponseBean1.getSysFile()!=null){
							tShopInfo.setSysFile(goodsResponseBean1.getSysFile());
						}
					}
				}
				
			}
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return tShopInfo;
	}

	/**
	 * 查询有效店铺列表(前端)
	 * 
	 * @param shopId
	 * @return TShopCategoryGoods
	 * @throws CMException
	 */
	public ShopResponseBean selectShops(TShopInfo shopInfo) throws CMException {
		String xFunctionName = "selectShops";
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		ShopResponseBean shopResponseBean = null;
		try {
			if (null == shopInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "userBean");
			}
			shopRequestBean.setShopInfo(shopInfo);
			shopResponseBean = shopService.queryShops(shopRequestBean);
			if(shopResponseBean!=null&&shopResponseBean.getResultList()!=null){
				List<TShopInfo> shopInfos = (List<TShopInfo>) shopResponseBean.getResultList();
				for(TShopInfo shopInfo2:shopInfos){
					if(shopInfo2!=null){
						Integer imgId = shopInfo2.getShopImgFileId();
						if(imgId!=null){
							TSysFile sysFile = new TSysFile();
							sysFile.setFileId(imgId);
							GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
							goodsRequestBean.setSysFile(sysFile);
							GoodsResponseBean goodsResponseBean1 = goodsService.querySysFileByFileId(goodsRequestBean);
							if(goodsResponseBean1!=null&&goodsResponseBean1.getSysFile()!=null){
								shopInfo2.setSysFile(goodsResponseBean1.getSysFile());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return shopResponseBean;
	}

	/**
	 * 新增店铺
	 * 
	 * @param shopInfo  店铺信息
	 * @param operateUser  操作用户
	 * @param tempPath  图片临时路径
	 * @return 
	 *     true - 店铺新增成功<br/>
	 *     false - 店铺新增失败
	 * @throws CMException 参数为null或店铺信息不完整
	 * @throws CMRollBackException 添加店铺时发异常
	 * @author tipx
	 * @createtime 2013-8-6 上午10:25:23
	 */
	public boolean addShop(TShopInfo shopInfo, TSysUserInfo operateUser, String tempPath) 
					                             throws CMException, CMRollBackException {
		String xFunctionName = "addShop";
		boolean resultFlag = false;
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		UserRequestBean userRequestBean = new UserRequestBean();
		UserResponseBean userResponseBean = null;
		ShopResponseBean shopResponseBean = null;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean = null;
		TSysUserInfo tSysUserInfo = new TSysUserInfo();
		TSysLinkUserRole tSysLinkUserRole = new TSysLinkUserRole();
		Date sysdDate = new Date();
		try {
			if (null == shopInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			if (null == operateUser) {
				gLogger.end(xFunctionName);
				throw CMException
						.sys("sysUserInfo is null",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"sysUserInfo");
			}
			// 验证店铺名称
			if (StringUtils.isBlank(shopInfo.getShopName())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证手机号
			if (StringUtils.isBlank(shopInfo.getShopPhoneNumber())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopPhoneNumber is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证qq号
			if (StringUtils.isBlank(shopInfo.getShopQq())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopQq is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证邮箱
			if (StringUtils.isBlank(shopInfo.getShopEmail())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopEmail is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证地址
			if (StringUtils.isBlank(shopInfo.getShopAddress())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopAddress is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			
			if(!validShopName(shopInfo)){
				gLogger.end(xFunctionName);
				throw CMException.sys("店铺名称已存在！",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			
			// 创建人员
			shopInfo.setShopStatus(Constants.SHOP_EFFECT_STATUS_00);
			shopInfo.setCreateUserCode(operateUser.getUserCode());
			shopInfo.setModifyUserCode(operateUser.getUserCode());
			shopInfo.setCreateTime(sysdDate);
			shopInfo.setModifyTime(sysdDate);
			
			
			
			
			if(StringUtils.isNotBlank(tempPath)){
				File temFile = new File(tempPath);//临时图片文件
				
				String path = CacheConstants.ZHENGSHI_UPLOAD_IMAGE_FILE_PATH();
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd-HH");
				String dateStr = fm.format(new Date());
				dateStr = dateStr.replace("-", "//");
				// 按日期拼路径
				path += "//" + dateStr + "//";
				String fileType = tempPath.substring(tempPath.lastIndexOf("."));
				String fileName = TxUUIDGenerator.getUUID() + fileType;
				File filePath = new File(path);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				File formalFile = new File(path + fileName);
				if (!formalFile.exists()) {
					filePath.createNewFile();
				}
				BufferedInputStream inBuff = null;
		        BufferedOutputStream outBuff = null;
		        try {
		            inBuff = new BufferedInputStream(new FileInputStream(temFile));
		            outBuff = new BufferedOutputStream(new FileOutputStream(formalFile));
		            byte[] b = new byte[1024];
		            int len;
		            while ((len = inBuff.read(b)) != -1) {
		                outBuff.write(b, 0, len);
		            }
		            outBuff.flush();
		        } finally {
		            // 关闭流
		            if (inBuff != null)
		                inBuff.close();
		            if (outBuff != null)
		                outBuff.close();
		        }
		        
		        TSysFile sysFile = new TSysFile();
		        sysFile.setFilePathUrl(formalFile.getPath());
		        String accessUrl = formalFile.getPath();
		        accessUrl = accessUrl.replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH());
		        accessUrl = accessUrl.replaceAll("\\\\", "/"); //存储url路径时,统一转换成"/"
		        sysFile.setFileAccessUrl(accessUrl);

		        sysFile.setFileStatus(Constants.SYS_FILE_00);
		        sysFile.setCreateUserCode(operateUser.getUserCode());
		        sysFile.setCreateTime(new Date());
		        goodsRequestBean.setSysFile(sysFile);
		        goodsResponseBean = goodsService.addSysFile(goodsRequestBean);
		        if("00".equals(goodsResponseBean.getResponseCode())){
		        	Integer newFileId= goodsResponseBean.getFileId();//插入完成后返回的新生成ID
		        	shopInfo.setShopLogoFileId(newFileId);
		        }
			}
			
			shopRequestBean.setShopInfo(shopInfo);

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			// 添加店铺信息
			shopResponseBean = shopService.addShopInfo(shopRequestBean);
			if ("00".equals(shopResponseBean.getResponseCode())) {
				// 店铺插入到数据库返回回来的店铺id
				TShopInfoExample example = new TShopInfoExample();
				example.createCriteria().andShopPhoneNumberEqualTo(
						shopInfo.getShopPhoneNumber());
				shopRequestBean.setShopInfoExample(example);
				shopResponseBean = shopService.queryShops(shopRequestBean);
				if ("00".equals(shopResponseBean.getResponseCode())) {
					TShopInfo tShopInfo = (TShopInfo) shopResponseBean
							.getResultList().get(0);
					tSysUserInfo.setUserShopId(tShopInfo.getShopId());
				}
				// 新增用户
//				tSysUserInfo.setUserCode(shopInfo.getShopPhoneNumber());
				String userCode = systemCommonMgr.saveAndQueryUserCodeByType(Constants.USER_TYPE_SHOP);
				tSysUserInfo.setUserCode(userCode);
				
				tSysUserInfo.setUserName(shopInfo.getShopName());
				tSysUserInfo.setUserPhoneNumber(shopInfo.getShopPhoneNumber());
				tSysUserInfo.setUserEmail(shopInfo.getShopEmail());
				tSysUserInfo.setUserType(Constants.USER_TYPE_SHOP);
				tSysUserInfo.setUserStatus(Constants.EFFECTIVE_STATUS);
				// 初始设置登录状态
				tSysUserInfo.setIsOnline(Constants.IS_ONLINE_FALSE);
				tSysUserInfo.setCreateUserCode(operateUser.getUserCode());
				tSysUserInfo.setModifyUserCode(operateUser.getUserCode());
				tSysUserInfo.setCreateTime(sysdDate);
				tSysUserInfo.setModifyTime(sysdDate);
				
				tSysUserInfo.setUserPassword(Constants.ORIGINAL_PASSWORD);
				userRequestBean.setSysuserinfo(tSysUserInfo);
				userResponseBean = userService.addUserInfo(userRequestBean);

				if ("00".equals(userResponseBean.getResponseCode())) {
					// 添加用户角色表
					tSysLinkUserRole.setRoleId(Constants.DEFAULT_ROLE_ID_MERCHANT);
					tSysLinkUserRole.setUserCode(tSysUserInfo.getUserCode());
					tSysLinkUserRole.setCreateUserCode(operateUser.getUserCode());
					tSysLinkUserRole.setModifyUserCode(operateUser.getUserCode());
					tSysLinkUserRole.setCreateTime(sysdDate);
					tSysLinkUserRole.setModifyTime(sysdDate);
					userRequestBean.setSysLinkUserRole(tSysLinkUserRole);
					userResponseBean = userService
							.addLinkUserRoles(userRequestBean);
					if ("00".equals(userResponseBean.getResponseCode())) {
						resultFlag = true;
					} else {
						throw new CMRollBackException(
								userResponseBean.getErrMsg());
					}
				}
			} else {
				throw new CMRollBackException(shopResponseBean.getErrMsg());
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

	@Override
	public boolean modifyShop(TShopInfo shopInfo, TSysUserInfo sysUserInfo,String tempPath)
			throws CMException, CMRollBackException {
		String xFunctionName = "modifyShop";
		boolean resultFlag = false;
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		ShopResponseBean shopResponseBean = null;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean = null;
		Date sysdDate = new Date();
		try {
			if (null == shopInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			if (null == sysUserInfo) {
				gLogger.end(xFunctionName);
				throw CMException
						.sys("sysUserInfo is null",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"sysUserInfo");
			}
			// 验证店铺id
			if (null == shopInfo.getShopId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证店铺名称
			if (StringUtils.isBlank(shopInfo.getShopName())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证手机号
			if (StringUtils.isBlank(shopInfo.getShopPhoneNumber())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopPhoneNumber is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证qq号
			if (StringUtils.isBlank(shopInfo.getShopQq())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopQq is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证邮箱
			if (StringUtils.isBlank(shopInfo.getShopEmail())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopEmail is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			// 验证地址
			if (StringUtils.isBlank(shopInfo.getShopAddress())) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopAddress is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			
			if(!validShopName(shopInfo)){
				gLogger.end(xFunctionName);
				throw CMException.sys("店铺名称已存在！",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			
			// 更改人员编号
			shopInfo.setModifyUserCode(sysUserInfo.getUserCode());
			// 更改时间
			shopInfo.setModifyTime(sysdDate);
			
			shopRequestBean.setShopInfo(shopInfo);
			
			if(StringUtils.isNotBlank(tempPath)){
				File temFile = new File(tempPath);//临时图片文件
				
				SimpleDateFormat fm = new SimpleDateFormat("yyyy//MM//dd//HH");
				String dateStr = fm.format(new Date());
				// 按日期拼路径
				String path = CacheConstants.ZHENGSHI_UPLOAD_IMAGE_FILE_PATH() + "//" + dateStr + "//";
				String fileType = tempPath.substring(tempPath.lastIndexOf("."));
				String fileName = TxUUIDGenerator.getUUID() + fileType;
				File filePath = new File(path);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				File formalFile = new File(path + fileName);
				if (!formalFile.exists()) {
					filePath.createNewFile();
				}

				//保存文件
				FileUtils.copyFile(temFile, formalFile);
		        
		        TSysFile sysFile = new TSysFile();
		        sysFile.setFilePathUrl(formalFile.getPath());
		        
		        String accessUrl = formalFile.getPath();
		        accessUrl = accessUrl.replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH());
		        accessUrl = accessUrl.replaceAll("\\\\", "/"); //存储url路径时,统一转换成"/"
		        sysFile.setFileAccessUrl(accessUrl);
		        
		        sysFile.setFileStatus(Constants.SYS_FILE_00);
		        sysFile.setCreateUserCode(sysUserInfo.getUserCode());
		        sysFile.setCreateTime(new Date());
		        goodsRequestBean.setSysFile(sysFile);
		        goodsResponseBean = goodsService.addSysFile(goodsRequestBean);
		        if("00".equals(goodsResponseBean.getResponseCode())){
		        	Integer newFileId= goodsResponseBean.getFileId();//插入完成后返回的新生成ID
		        	shopInfo.setShopLogoFileId(newFileId);
		        }
			}
			// 修改店铺信息
			shopResponseBean = shopService.modifyShopInfo(shopRequestBean);
			if ("00".equals(shopResponseBean.getResponseCode())) {
				TSysUserInfo tSysUserInfo = new TSysUserInfo();
				UserRequestBean userRequestBean = new UserRequestBean();
				UserResponseBean userResponseBean = null;

				// 根据shopId，查询用户，再对该用户进行修改
				tSysUserInfo.setUserShopId(shopInfo.getShopId());
				userRequestBean.setSysuserinfo(tSysUserInfo);
				userResponseBean = userService
						.queryForUserByUserProperties(userRequestBean);

				if ("00".equals(userResponseBean.getResponseCode())) {
					List<TSysUserInfo> userList = userResponseBean
							.getUserlist();

					// 若未找到关联的用户，则直接抛出异常
					if (null == userList || userList.isEmpty()) {
						gLogger.end(xFunctionName);
						throw CMException.sys(
								"Shop's default user dose not exist！",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"shopInfo");
					}
					tSysUserInfo = userList.get(0);
				} else {
					throw new CMRollBackException(userResponseBean.getErrMsg());
				}

				// 修改用户
				tSysUserInfo.setUserShopId(shopInfo.getShopId());
//				tSysUserInfo.setUserName(shopInfo.getShopName());
				tSysUserInfo.setUserEmail(shopInfo.getShopEmail());
				tSysUserInfo.setUserPhoneNumber(shopInfo.getShopPhoneNumber());
				tSysUserInfo.setModifyUserCode(sysUserInfo.getUserCode());
				tSysUserInfo.setModifyTime(sysdDate);

				userRequestBean.setSysuserinfo(tSysUserInfo);
				// 修改用户信息
				userRequestBean.setSysuserinfo(tSysUserInfo);
				userResponseBean = userService.modifyUserInfo(userRequestBean);
				if ("00".equals(userResponseBean.getResponseCode())) {
					resultFlag = true;
				} else {
					throw new CMRollBackException(userResponseBean.getErrMsg());
				}
			} else {
				throw new CMRollBackException(shopResponseBean.getErrMsg());
			}
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		} catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	@Override
	public boolean deleteShop(TShopInfo shopInfo) throws CMException,
			CMRollBackException {
		String xFunctionName = "deleteShop";
		boolean resultFlag = false;
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		UserRequestBean userRequestBean = new UserRequestBean();
		ShopResponseBean shopResponseBean = null;
		UserResponseBean userResponseBean = null;
		try {
			if (null == shopInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}

			if (null == shopInfo.getShopId()) {
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"sysUserInfo");
			}
			Integer shopId = shopInfo.getShopId();
			shopRequestBean.setShopInfo(shopInfo);
			// 物理删除店铺信息
			shopResponseBean = shopService.deleteShopInfoPermanently(shopRequestBean);
			if ("00".equals(shopResponseBean.getResponseCode())) {

				TSysUserInfo tmpUser = new TSysUserInfo();
				// 根据shopId，查询用户
				tmpUser.setUserShopId(shopInfo.getShopId());
				userRequestBean.setSysuserinfo(tmpUser);
				userResponseBean = userService.queryForUserByUserProperties(userRequestBean);

				if ("00".equals(userResponseBean.getResponseCode())) {
					List<TSysUserInfo> userList = userResponseBean.getUserlist();

					// 若未找到关联的用户，则直接成功
					if (null == userList || userList.isEmpty()) {
						return resultFlag = true;
					}
					tmpUser = userList.get(0);
				} else {
					throw new CMRollBackException(userResponseBean.getErrMsg());
				}


				//清理用户与角色的关联
				TSysLinkUserRole tSysLinkUserRole = new TSysLinkUserRole();
				TSysLinkUserRoleExample eg = new TSysLinkUserRoleExample();
				TSysLinkUserRoleExample.Criteria c = eg.createCriteria();
				c.andUserCodeEqualTo(tmpUser.getUserCode());
				
				userRequestBean = new UserRequestBean();
				userRequestBean.setSysLinkUserRole(tSysLinkUserRole);
				userRequestBean.setSysLinkUserRoleExample(eg);
				userResponseBean = userService.deleteLinkUserRole(userRequestBean);

				if (!"00".equals(userResponseBean.getResponseCode())) {
					throw new CMRollBackException(userResponseBean.getErrMsg());
				}
				
				// 物理删除用户
				userRequestBean.setSysuserinfo(tmpUser);
				userResponseBean = userService.deleteUserInfoPermanently(userRequestBean);
				if ("00".equals(userResponseBean.getResponseCode())) {
					resultFlag = true;
				} else {
					throw new CMRollBackException(userResponseBean.getErrMsg());
				}
			} else {
				throw new CMRollBackException(shopResponseBean.getErrMsg());
			}
		} catch (CMRollBackException e) {
			throw e;
		} catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}
		finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	@Override
	public boolean toggleShop(TShopInfo shopInfo, TSysUserInfo sysUserInfo,
			String shopCurrentStatus) throws CMException, CMRollBackException {
		String xFunctionName = "toggleShop";
		boolean resultFlag = false;
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		UserRequestBean userRequestBean = new UserRequestBean();
		ShopResponseBean shopResponseBean = null;
		UserResponseBean userResponseBean = null;
		Date sysdDate = new Date();
		try {
			if (null == shopInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			if (null == sysUserInfo) {
				gLogger.end(xFunctionName);
				throw CMException
						.sys("sysUserInfo is null",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"sysUserInfo");
			}
			if (null == shopInfo.getShopId()) {
				gLogger.end(xFunctionName);
				throw CMException
						.sys("ShopId is null",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"sysUserInfo");
			}

			// 根据当前提交的状态进行切换
			if (Constants.SHOP_FAILURE_STATUS.equals(shopCurrentStatus)) {
				ShopRequestBean requestBean = new ShopRequestBean();
				requestBean.setShopInfo(shopInfo);
				//前台只传了id与手机号码，所以此处需要重新查询
				ShopResponseBean responseBean = shopService.queryShops(requestBean);
				
				if(null != responseBean.getResultList() && responseBean.getResultList().size() > 0){
					shopInfo = (TShopInfo)responseBean.getResultList().get(0);
				}else{
					gLogger.end(xFunctionName);
					throw CMException.sys("店铺不存在！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
				}
				
				if(!validShopName(shopInfo)){
					gLogger.end(xFunctionName);
					throw CMException.sys("店铺名称已存在！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
				}
				
				//启用时，需要验证手机号码是否已经被占用
				if(!validPhoneShopAndUser(shopInfo)){
					gLogger.end(xFunctionName);
					throw CMException.sys("店铺的手机号码已被占用，请先修改店铺的手机号码！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
				}
				
				shopInfo.setShopStatus(Constants.SHOP_EFFECT_STATUS_00);
			} else {
				shopInfo.setShopStatus(Constants.SHOP_FAILURE_STATUS);
			}

			shopInfo.setModifyUserCode(sysUserInfo.getUserCode());
			shopInfo.setModifyTime(sysdDate);
			shopRequestBean.setShopInfo(shopInfo);
			// 逻辑删除店铺信息
			shopResponseBean = shopService.deleteShopInfo(shopRequestBean);
			if ("00".equals(shopResponseBean.getResponseCode())) {
				// 逻辑删除用户

				// 根据shopId，查询用户，再对该用户进行修改
				TSysUserInfo tSysUserInfo = new TSysUserInfo();
				tSysUserInfo.setUserShopId(shopInfo.getShopId());
				userRequestBean.setSysuserinfo(tSysUserInfo);
				userResponseBean = userService
						.queryForUserByUserProperties(userRequestBean);

				if ("00".equals(userResponseBean.getResponseCode())) {
					List<TSysUserInfo> userList = userResponseBean
							.getUserlist();

					// 若未找到关联的用户，则直接抛出异常
					if (null == userList || userList.isEmpty()) {
						gLogger.end(xFunctionName);
						throw CMException.sys("店铺的默认用户不存在！", ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
					}
					tSysUserInfo = userList.get(0);
				} else {
					throw new CMRollBackException(userResponseBean.getErrMsg());
				}

				// 根据当前提交的状态进行切换
				if (Constants.SHOP_FAILURE_STATUS.equals(shopCurrentStatus)) {
					tSysUserInfo.setUserStatus(Constants.USER_STATUS_01);
				} else {
					tSysUserInfo.setUserStatus(Constants.USER_STATUS_02);
				}

				tSysUserInfo.setModifyUserCode(sysUserInfo.getUserCode());
				tSysUserInfo.setModifyTime(sysdDate);
				userRequestBean.setSysuserinfo(tSysUserInfo);
				userResponseBean = userService.updateUser(userRequestBean);
				if ("00".equals(userResponseBean.getResponseCode())) {
					resultFlag = true;
				} else {
					throw new CMRollBackException(userResponseBean.getErrMsg());
				}
			} else {
				throw new CMRollBackException(shopResponseBean.getErrMsg());
			}
		} catch (CMRollBackException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		} catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		} finally {
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	@Override
	public ShopResponseBean selectFuzzyPagesShops(ShopRequestBean requestBean)
			throws CMException {
		String xFunctionName = "selectFuzzyPagesShops";
		ShopResponseBean shopResponseBean = null;
		TShopInfoExample example = new TShopInfoExample();
		TShopInfoExample.Criteria criteria = example.createCriteria();
		try {
			if (null != requestBean) {
				if (null != requestBean.getShopInfo()) {
					if (StringUtils.isNotBlank(requestBean.getShopInfo()
							.getShopName())) {
						criteria.andShopNameLike("%"
								+ requestBean.getShopInfo().getShopName() + "%");
					}
					if (StringUtils.isNotBlank(requestBean.getShopInfo()
							.getShopPhoneNumber())) {
						criteria.andShopPhoneNumberLike("%"
								+ requestBean.getShopInfo()
										.getShopPhoneNumber() + "%");
					}
				}
				requestBean.setShopInfoExample(example);

			}

			shopResponseBean = shopService.queryFuzzyPagesShops(requestBean);
			String replaceTopPath = CacheConstants.ORIGINAL_IMAGE_HTTP_PATH_PREFIX;//从配置文件取出的头部替换路径
			if(StringUtils.isNotBlank(replaceTopPath)){
				if(shopResponseBean!=null&&shopResponseBean.getResultList()!=null&&shopResponseBean.getResultList().size()>0){
					List<TShopInfo> list = (List<TShopInfo>) shopResponseBean.getResultList();
					for(TShopInfo shopInfo:list){
						TSysFile sysFile = shopInfo.getSysFile();
						if(sysFile!=null&&StringUtils.isNotBlank(sysFile.getFilePathUrl())){
							String accessFilePath ="";
							if(sysFile.getFilePathUrl().indexOf(CacheConstants.TOP_FILE_PATH()) > -1){
								sysFile.setFileAccessUrl(sysFile.getFilePathUrl().replace(CacheConstants.TOP_FILE_PATH(),replaceTopPath));
							}
						}
					}
				}
			}
			if (!"00".equals(shopResponseBean.getResponseCode())) {
				throw new CMException(shopResponseBean.getErrMsg());
			}
		} catch (CMException e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return shopResponseBean;
	}

	/**
	 * 物理删除
	 *方法名称 deleteShopRecords
	 *方法描述
	 * @param shopInfo
	 * @param sysUserInfo
	 * @return
	 * @throws CMException
	 * @throws CMRollBackException
	 * @Date 2013-7-1
	 * @author xupj
	 */
	public boolean deleteShopRecords(TShopInfo shopInfo,
			TSysUserInfo sysUserInfo) throws CMException, CMRollBackException {
		String xFunctionName = "modifyShop";
		boolean resultFlag = false;
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		UserRequestBean userRequestBean = new UserRequestBean();
		ShopResponseBean shopResponseBean = null;
		UserResponseBean userResponseBean = null;
		TSysUserInfo tSysUserInfo = new TSysUserInfo();
		Date sysdDate = new Date();
		try {
			if (null == shopInfo) {
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			if (null == sysUserInfo) {
				gLogger.end(xFunctionName);
				throw CMException
						.sys("sysUserInfo is null",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"sysUserInfo");
			}
			if (null == shopInfo.getShopId()) {
				gLogger.end(xFunctionName);
				throw CMException
						.sys("ShopId is null",
								ExceptionConstants.ILLEGAL_ARGUMENT_CODE,
								"sysUserInfo");
			}
			shopRequestBean.setShopInfo(shopInfo);
			shopResponseBean = shopService.deleteShopInfoPermanently(shopRequestBean);
			if ("00".equals(shopResponseBean.getResponseCode())) {
				TSysUserInfoExample example = new TSysUserInfoExample();
				example.createCriteria().andUserShopIdEqualTo(
						shopInfo.getShopId());
				userRequestBean.setSysUserInfoExample(example);
				userResponseBean = userService
						.queryForUserByExample(userRequestBean);
				if (userResponseBean.getResultList().size() > 0) {
					tSysUserInfo = (TSysUserInfo) userResponseBean
							.getResultList().get(0);
					userRequestBean.setSysuserinfo(tSysUserInfo);
					userResponseBean = userService
							.deleteUserInfoPermanently(userRequestBean);
					if ("00".equals(userResponseBean.getResponseCode())) {
						resultFlag = true;
					} else {
						throw new CMRollBackException(
								userResponseBean.getErrMsg());
					}
				}
			} else {
				throw new CMRollBackException(shopResponseBean.getErrMsg());
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

	@Override
	public boolean validPhoneShopAndUser(TShopInfo shopInfo) throws CMException {
		String xFunctionName = "validPhoneShopAndUser";
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		UserRequestBean userRequestBean = new UserRequestBean();
		ShopResponseBean shopResponseBean = null;
		UserResponseBean userResponseBean = null;
		boolean resultFlag = false;
		try {
			Integer shopId = shopInfo.getShopId();
			if(null == shopId){
				shopId = -1;
			}
			
			// 验证店铺手机号码
			TShopInfoExample shopInfoExample = new TShopInfoExample();
			shopInfoExample
					.createCriteria()
					.andShopPhoneNumberEqualTo(
							shopInfo.getShopPhoneNumber().trim())
					.andShopStatusEqualTo(Constants.SHOP_EFFECT_STATUS_00);
			shopRequestBean.setShopInfoExample(shopInfoExample);
			shopResponseBean = shopService.queryShops(shopRequestBean);
			if ("00".equals(shopResponseBean.getResponseCode())) {
				List<TShopInfo> shopList = (List<TShopInfo>)shopResponseBean.getResultList();
				if (shopList.size() == 0) {
					resultFlag = true;
				} else {
					return (shopId.intValue() == shopList.get(0).getShopId().intValue());
					//return resultFlag = false; //店铺手机号码已经重复，则直接返回
				}
			} else {
				throw new CMException(shopResponseBean.getErrMsg());
			}
			// 验证用户手机号码
			TSysUserInfoExample sysUserInfoExample = new TSysUserInfoExample();
			sysUserInfoExample
					.createCriteria()
					.andUserPhoneNumberEqualTo(
							shopInfo.getShopPhoneNumber().trim())
					.andUserStatusEqualTo(Constants.EFFECTIVE_STATUS)
					.andUserTypeEqualTo(Constants.USER_TYPE_02);
			userRequestBean.setSysUserInfoExample(sysUserInfoExample);
			userResponseBean = userService
					.queryForUserByExample(userRequestBean);
			if ("00".equals(userResponseBean.getResponseCode())) {
				List<TSysUserInfo> userList = (List<TSysUserInfo>)userResponseBean.getResultList();
				if (userList.size() == 0) {
					resultFlag = true;
				} else {
					resultFlag = (shopId.intValue() == userList.get(0).getUserShopId().intValue());
					//resultFlag = false;
				}
			} else {
				throw new CMException(userResponseBean.getErrMsg());
			}

		} catch (CMException e) {
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
	 * 验证店铺名称唯一
	 * @param shopName
	 * @return
	 * @throws CMException
	 */
	private boolean validShopName(TShopInfo shopInfo) throws CMException {
		String xFunctionName = "validShopName";
		ShopRequestBean shopRequestBean = new ShopRequestBean();
		ShopResponseBean shopResponseBean = null;
		boolean resultFlag = false;
		try {
			// 验证店铺名称
			TShopInfoExample shopInfoExample = new TShopInfoExample();
			shopInfoExample.createCriteria()
					.andShopNameEqualTo(shopInfo.getShopName().trim())
					.andShopStatusEqualTo(Constants.SHOP_EFFECT_STATUS_00);
			shopRequestBean.setShopInfoExample(shopInfoExample);
			shopResponseBean = shopService.queryShops(shopRequestBean);
			if ("00".equals(shopResponseBean.getResponseCode())) {
				List<TShopInfo> shopList = (List<TShopInfo>)shopResponseBean.getResultList();
				if (shopList.size() == 0) {
					resultFlag = true;
				} else {
					int shopId = 0;
					//新增时，id为null
					if(null != shopInfo.getShopId()){
						shopId = shopInfo.getShopId().intValue();
					}
					
					//若id一致，则店铺名称未重复
					resultFlag = (shopList.get(0).getShopId().intValue() == shopId);
					return resultFlag; 
				}
			} else {
				throw new CMException(shopResponseBean.getErrMsg());
			}

		} catch (CMException e) {
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

	public IShopService getShopService() {
		return shopService;
	}

	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public SystemCommonMgr getSystemCommonMgr() {
		return systemCommonMgr;
	}

	public void setSystemCommonMgr(SystemCommonMgr systemCommonMgr) {
		this.systemCommonMgr = systemCommonMgr;
	}
	
	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}
}
