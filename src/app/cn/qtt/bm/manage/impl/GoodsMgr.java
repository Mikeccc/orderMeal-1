package app.cn.qtt.bm.manage.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import app.cn.qtt.bm.common.base.service.BaseService;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.exception.CMException;
import app.cn.qtt.bm.common.exception.CMRollBackException;
import app.cn.qtt.bm.common.utils.TxUUIDGenerator;
import app.cn.qtt.bm.manage.IGoodsMgr;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopCategoryGoodsLinkExample;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopGoodsTime;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysFile;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.IGoodsService;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;


public class GoodsMgr extends BaseService implements IGoodsMgr {

	private IGoodsService goodsService;
	public IGoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(IGoodsService goodsService) {
		this.goodsService = goodsService;
	}

	/**
	 * 根据店铺id查询分类
	 */
	public GoodsResponseBean queryGoodsCategoryByShopId(TShopInfo shopInfo)
			throws CMException {
		String xFunctionName = "queryGoodsCategoryByShopId";
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		//TShopCategoryGoodsExample tShopCategoryGoodsExample = new TShopCategoryGoodsExample();
		try{
			if(null == shopInfo){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			if(null == shopInfo.getShopId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			//tShopCategoryGoodsExample.createCriteria().andShopIdEqualTo(shopInfo.getShopId());
			//goodsRequestBean.setShopCategoryGoodsExample(tShopCategoryGoodsExample);
			goodsRequestBean.setShopInfo(shopInfo);
			goodsResponseBean = goodsService.queryGoodsCategoryByShopId(goodsRequestBean);
			if(!"00".equals(goodsResponseBean.getResponseCode())){
				throw new CMException(goodsResponseBean.getErrMsg());
			}
		}
		catch(CMException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return goodsResponseBean;
	}

	/**
	 * 根据店铺商品分类id/(有效供应时间)查询商品(分页)
	 */
	public GoodsResponseBean queryGoodsByCategory(
			GoodsRequestBean goodsRequestBean) throws CMException {
		String xFunctionName = "queryGoodsByCategory";
		GoodsResponseBean goodsResponseBean= null;
		//TShopCategoryGoodsExample tShopCategoryGoodsExample = new TShopCategoryGoodsExample();
		//TShopGoodsTimeExample tShopGoodsTimeExample = new TShopGoodsTimeExample();
		try{
			if(null == goodsRequestBean.getShopInfo()){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			if(null == goodsRequestBean.getShopInfo().getShopId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopInfo");
			}
			if(null == goodsRequestBean.getShopCategoryGoods()){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopCategoryGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(null == goodsRequestBean.getShopCategoryGoods().getCategoryId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("CategoryId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
//			if(null == goodsRequestBean.getTimeCodeList()){
//				gLogger.end(xFunctionName);
//				throw CMException.sys("TimeCodeList is null",
//						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "TimeCodeList");
//			}
			//goodsRequestBean.setShopInfo(shopInfo);
			//goodsRequestBean.setShopCategoryGoods(shopCategoryGoods);
			//goodsRequestBean.setShopGoodsTime(shopGoodsTime);
		
			goodsResponseBean = goodsService.queryGoodsByCategory(goodsRequestBean);
			setImageFilePath(goodsResponseBean);
			if(!"00".equals(goodsResponseBean.getResponseCode())){
				throw new CMException(goodsResponseBean.getErrMsg());
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return goodsResponseBean;
	}

	//新增商品分组
	public boolean addGoodsCategory(TShopCategoryGoods shopCategoryGoods)
			throws CMException, CMRollBackException {
		String xFunctionName = "addGoodsCategory";
		boolean resultFlag = false;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		try{
			if(null == shopCategoryGoods){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopCategoryGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(null == shopCategoryGoods.getShopId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(StringUtils.isBlank(shopCategoryGoods.getCategoryName().trim())){
				gLogger.end(xFunctionName);
				throw CMException.sys("CategoryName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			goodsRequestBean.setShopCategoryGoods(shopCategoryGoods);
			//添加商品分类
			goodsResponseBean = goodsService.addGoodsCategory(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				resultFlag= true;
			}else{
				resultFlag= false;
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}
		catch(CMRollBackException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	//根据类别删除分组
	public boolean deleteGoodsCategory(TShopCategoryGoods shopCategoryGoods)
			throws CMException, CMRollBackException {
		String xFunctionName = "queryGoodsByCategory";
		boolean resultFlag = false;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		//TShopCategoryGoodsExample tShopCategoryGoodsExample = new TShopCategoryGoodsExample();
		//TShopGoodsTimeExample tShopGoodsTimeExample = new TShopGoodsTimeExample();
		TShopCategoryGoodsLinkExample tShopCategoryGoodsLinkExample = new TShopCategoryGoodsLinkExample();
		TShopGoods shopGoods = new TShopGoods();
		try{
			if(null == shopCategoryGoods){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopCategoryGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(null == shopCategoryGoods.getCategoryId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("CategoryId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			//根据类别id查询GoodLink商品id
			tShopCategoryGoodsLinkExample.createCriteria().andCategoryIdEqualTo(shopCategoryGoods.getCategoryId());
			goodsRequestBean.setShopCategoryGoodsLinkExample(tShopCategoryGoodsLinkExample);
			goodsResponseBean = goodsService.queryLinkGoodsCategory(goodsRequestBean);
			//删除ShopGood表类别的商品
			if("00".equals(goodsResponseBean.getResponseCode())){
				List<TShopCategoryGoodsLink> listShopCategoryGoodsLink = 	(List<TShopCategoryGoodsLink>) goodsResponseBean.getResultList();
				goodsResponseBean = null;
				for(TShopCategoryGoodsLink tShopCategoryGoodsLink : listShopCategoryGoodsLink){
					shopGoods.setShopGoodsId(tShopCategoryGoodsLink.getShopGoodsId());
					goodsRequestBean.setShopGoods(shopGoods);
					//物理删除商品
					goodsResponseBean = goodsService.deleteGoodsInfoPhysical(goodsRequestBean);
					if("00".equals(goodsResponseBean.getResponseCode())){
						continue;
					}else{
						throw new CMRollBackException(goodsResponseBean.getErrMsg());
					}
				}
			}
			//删除类别产品表
			goodsRequestBean.setShopCategoryGoods(shopCategoryGoods);
			goodsResponseBean =  goodsService.deleteLinkGoodsCategory(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				goodsResponseBean = null;
				//删除类别表
				goodsResponseBean = goodsService.deleteGoodsCategory(goodsRequestBean);
				if("00".equals(goodsResponseBean.getResponseCode())){
					resultFlag = true;
				}else{
					resultFlag = false;
					throw new CMRollBackException(goodsResponseBean.getErrMsg());
				}
			}else{
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}
		catch(CMRollBackException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	//添加商品
	public boolean addGoods(TShopGoods shopGoods,
			TShopCategoryGoodsLink shopCategoryGoodsLink, TSysUserInfo sysUserInfo) throws CMException,
			CMRollBackException {
		String xFunctionName = "queryGoodsByCategory";
		boolean resultFlag = false;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		Date sysDate = new Date();
		try{
			if(null == shopGoods){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopCategoryGoodsLink){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopCategoryGoodsLink is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoodsLink");
			}
			if(StringUtils.isBlank(shopGoods.getShopGoodsName())){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(StringUtils.isBlank(shopGoods.getTimeCode())){
				gLogger.end(xFunctionName);
				throw CMException.sys("timeCode is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopGoods.getShopId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(StringUtils.isBlank(shopGoods.getShopGoodsPrice())){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsPrice is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopCategoryGoodsLink.getCategoryId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("CategoryId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoodsLink");
			}
			
			if(StringUtils.isNotBlank(shopGoods.getTemporaryFilePath())){
				File temFile = new File(shopGoods.getTemporaryFilePath());//临时图片文件
				
				String path = CacheConstants.ZHENGSHI_UPLOAD_IMAGE_FILE_PATH();
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd-HH");
				String dateStr = fm.format(new Date());
				dateStr = dateStr.replace("-", "//");
				// 按日期拼路径
				path += "//" + dateStr + "//";
				String fileName = TxUUIDGenerator.getUUID() + ".jpg";
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
		        if(formalFile.getPath().indexOf(CacheConstants.TOP_FILE_PATH()) > -1){
					sysFile.setFileAccessUrl(formalFile.getPath().replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH()));
				}else{
					sysFile.setFileAccessUrl(formalFile.getPath());
				}
		        sysFile.setFileStatus(Constants.SYS_FILE_00);
		        sysFile.setCreateUserCode(sysUserInfo.getUserCode());
		        sysFile.setCreateTime(new Date());
		        goodsRequestBean.setSysFile(sysFile);
		        goodsResponseBean = goodsService.addSysFile(goodsRequestBean);
		        if("00".equals(goodsResponseBean.getResponseCode())){
		        	Integer newFileId= goodsResponseBean.getFileId();//插入完成后返回的新生成ID
		        	shopGoods.setShopGoodsImgFileId(newFileId);
		        }
			}
			
			
			shopGoods.setShopGoodsStatus(Constants.ORDER_GOOD_STATUS_00);
			shopGoods.setCreateUserCode(sysUserInfo.getUserCode());
		
			goodsRequestBean.setShopGoods(shopGoods);
			//添加商品
			goodsResponseBean = goodsService.addGoodsInfo(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				//添加商品和分组关联表
				Integer newShopGoodsId = goodsResponseBean.getShopGoodsId();//插入完成后返回的新生成ID
				GoodsRequestBean goodsRequestBean1 = new GoodsRequestBean();
				shopCategoryGoodsLink.setShopGoodsId(newShopGoodsId);
				shopCategoryGoodsLink.setCreateUserCode(sysUserInfo.getUserCode());
				shopCategoryGoodsLink.setCreateTime(sysDate);
				goodsRequestBean1.setShopCategoryGoodsLink(shopCategoryGoodsLink);
				goodsResponseBean = goodsService.addLinkGoodsCategory(goodsRequestBean1);
				if("00".equals(goodsResponseBean.getResponseCode())){
					resultFlag = true;
					//添加商品和时间关联表
					GoodsRequestBean goodsRequestBean2 = new GoodsRequestBean();
					TShopGoodsTime shopGoodsTime = new TShopGoodsTime();
					shopGoodsTime.setShopGoodsId(newShopGoodsId);
					shopGoodsTime.setCreateUserCode(sysUserInfo.getUserCode());
					shopGoodsTime.setCreateTime(new Date());
					if(shopGoods.getTimeCode().equals(Constants.TIME_CODE_EVERYDAY)){
						String[] timeCodeArray = Constants.TIME_CODE_LIST.split(",");
						for(String timeCode:timeCodeArray){
							shopGoodsTime.setTimeCode(timeCode);
							goodsRequestBean2.setShopGoodsTime(shopGoodsTime);
							goodsResponseBean = goodsService.addShopGoodsTime(goodsRequestBean2);
							if("00".equals(goodsResponseBean.getResponseCode())){
								resultFlag = true;
							}else{
								resultFlag = false;
								break;
							}
						}
					}else{
						String[] timeCodeList = shopGoods.getTimeCode().split(",");
						for(String timeCode:timeCodeList){
							shopGoodsTime.setTimeCode(timeCode);
							goodsRequestBean2.setShopGoodsTime(shopGoodsTime);
							goodsResponseBean = goodsService.addShopGoodsTime(goodsRequestBean2);
							if("00".equals(goodsResponseBean.getResponseCode())){
								resultFlag = true;
							}else{
								resultFlag = false;
								throw new CMRollBackException(goodsResponseBean.getErrMsg());
							}
						}
					}
				}else{
					resultFlag = false;
					throw new CMRollBackException(goodsResponseBean.getErrMsg());
				}
			}else{
				resultFlag = false;
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}	
		}
		catch(CMRollBackException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	//删除商品
	public boolean deleteGoods(TShopGoods shopGoods) throws CMException,
			CMRollBackException {
		String xFunctionName = "deleteGoods";
		boolean resultFlag = false;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		try{
			if(null == shopGoods){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopGoods.getShopGoodsId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			goodsRequestBean.setShopGoods(shopGoods);
			goodsResponseBean = goodsService.deleteGoodsInfo(goodsRequestBean);
			//删除商品
			if("00".equals(goodsResponseBean.getResponseCode())){
				resultFlag = true;
			}else{
				resultFlag = false;
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}
		catch(CMRollBackException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	//查询商品信息详情
	public GoodsResponseBean queryGoodsInfoById(TShopGoods shopGoods)
			throws CMException {
		String xFunctionName = "queryGoodsInfoById";
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		try{
			if(shopGoods == null){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopGoods.getShopGoodsId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			goodsRequestBean.setShopGoods(shopGoods);
			goodsResponseBean = goodsService.queryGoodsInfoById(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				
			}else{
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
			TSysFile sysFile = new TSysFile();
			TShopGoods shopGoods1 = goodsResponseBean.getShopGoods();
			int fileId = shopGoods1.getShopGoodsImgFileId();
			sysFile.setFileId(fileId);
			goodsRequestBean.setSysFile(sysFile);
			GoodsResponseBean goodsResponseBean1 = goodsService.querySysFileByFileId(goodsRequestBean);
			if(goodsResponseBean1!=null&&goodsResponseBean1.getSysFile()!=null
						&&StringUtils.isNotBlank(goodsResponseBean1.getSysFile().getFileAccessUrl())){
				String replaceTopPath = CacheConstants.ORIGINAL_IMAGE_HTTP_PATH_PREFIX;//从配置文件取出的头部替换路径
				String accessFilePath ="";
				if(StringUtils.isNotBlank(replaceTopPath)){
					if(StringUtils.isNotBlank(goodsResponseBean1.getSysFile().getFilePathUrl())){
						if(goodsResponseBean1.getSysFile().getFilePathUrl().indexOf(CacheConstants.TOP_FILE_PATH()) > -1){
							accessFilePath = goodsResponseBean1.getSysFile().getFilePathUrl().replace(CacheConstants.TOP_FILE_PATH(),replaceTopPath);
							shopGoods1.setTemporaryFilePath(accessFilePath);
						}						
					}	
				}else{
					shopGoods1.setTemporaryFilePath(goodsResponseBean1.getSysFile().getFileAccessUrl());
				}
				
			}
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return goodsResponseBean;
	}

	//修改商品
	public boolean modifyGoods(TShopGoods shopGoods,
			TShopCategoryGoodsLink shopCategoryGoodsLink, TSysUserInfo sysUserInfo) throws CMException,
			CMRollBackException {
		String xFunctionName = "queryGoodsByCategory";
		boolean resultFlag = false;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		Date sysDate = new Date();
		try{
			if(null == shopGoods){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopGoods is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopCategoryGoodsLink){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopCategoryGoodsLink is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoodsLink");
			}
			if(StringUtils.isBlank(shopGoods.getShopGoodsName())){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopGoods.getShopGoodsId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopGoods.getShopId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(StringUtils.isBlank(shopGoods.getShopGoodsPrice())){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsPrice is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopGoods");
			}
			if(null == shopCategoryGoodsLink.getShopGoodsId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("ShopGoodsId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoodsLink");
			}
			shopGoods.setShopGoodsStatus("00");
			shopGoods.setCreateUserCode(sysUserInfo.getUserCode());
			
			if(StringUtils.isNotBlank(shopGoods.getTemporaryFilePath())){
				File temFile = new File(shopGoods.getTemporaryFilePath());//临时图片文件
				
				String path = CacheConstants.ZHENGSHI_UPLOAD_IMAGE_FILE_PATH();
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd-HH");
				String dateStr = fm.format(new Date());
				dateStr = dateStr.replace("-", "//");
				// 按日期拼路径
				path += "//" + dateStr + "//";
				String fileName = TxUUIDGenerator.getUUID() + ".jpg";
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
		        if(formalFile.getPath().indexOf(CacheConstants.TOP_FILE_PATH()) > -1){
					sysFile.setFileAccessUrl(formalFile.getPath().replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH()));
				}else{
					sysFile.setFileAccessUrl(formalFile.getPath());
				}
		        sysFile.setFileStatus(Constants.SYS_FILE_00);
		        sysFile.setCreateUserCode(sysUserInfo.getUserCode());
		        sysFile.setCreateTime(new Date());
		        goodsRequestBean.setSysFile(sysFile);
		        goodsResponseBean = goodsService.addSysFile(goodsRequestBean);
		        if("00".equals(goodsResponseBean.getResponseCode())){
		        	Integer newFileId= goodsResponseBean.getFileId();//插入完成后返回的新生成ID
		        	shopGoods.setShopGoodsImgFileId(newFileId);
		        }
			}
			
			
			goodsRequestBean.setShopGoods(shopGoods);
			//修改商品
			goodsResponseBean = goodsService.modifyGoodsInfo(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				//修改商品关联表
				goodsRequestBean.setShopCategoryGoodsLink(shopCategoryGoodsLink) ;
				goodsResponseBean = goodsService.modifyLinkGoodsCategory(goodsRequestBean);
				if("00".equals(goodsResponseBean.getResponseCode())){
					resultFlag = true;
				}else{
					resultFlag = false;
					throw new CMRollBackException(goodsResponseBean.getErrMsg());
				}
			}else{
				resultFlag = false;
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}
		catch(CMRollBackException e){
			gLogger.exception(xFunctionName, e);
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
			throw CMRollBackException.sys(e.getMessage(),
					ExceptionConstants.UNKNOWN_BUSINESS_CODE, null);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}

	/**
	 * 修改类别
	 */
	public boolean modifyCategory(TShopCategoryGoods shopCategoryGoods)
			throws CMException, CMRollBackException {
		String xFunctionName = "modifyCategory";
		boolean resultFlag = false;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		try{
			if(null == shopCategoryGoods.getCategoryId()){
				gLogger.end(xFunctionName);
				throw CMException.sys("CategoryId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(StringUtils.isBlank(shopCategoryGoods.getCategoryName().trim())){
				gLogger.end(xFunctionName);
				throw CMException.sys("CategoryName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			goodsRequestBean.setShopCategoryGoods(shopCategoryGoods);
			goodsResponseBean = goodsService.modifyGoodsCategory(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				resultFlag = true;
			}else{
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}
		catch(CMRollBackException e){
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}
	
	/**
	 * 
	* @Title: isGoodsNameDuplicate
	* @Description:判断菜单名是否存在
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean isGoodsNameDuplicate(String goodsName,TShopInfo shopInfo)throws CMException, CMRollBackException {
		String xFunctionName = "modifyCategory";
		boolean resultFlag = true;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		try{
			if(StringUtils.isBlank(goodsName)){
				gLogger.end(xFunctionName);
				throw CMException.sys("goodsName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(shopInfo==null){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(shopInfo.getShopId()==null){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			goodsRequestBean.setShopInfo(shopInfo);
			TShopGoods shopGoods = new TShopGoods();
			shopGoods.setShopGoodsName(goodsName);
			shopGoods.setShopGoodsStatus(Constants.ORDER_GOOD_STATUS_00);
			goodsRequestBean.setShopGoods(shopGoods);
			goodsResponseBean = goodsService.queryGoodsInfoByGoodsName(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				if(goodsResponseBean.getResultList()!=null&&goodsResponseBean.getResultList().size()>0){
					resultFlag=true;
				}else{
					resultFlag=false;
				}
			}else{
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}catch(CMRollBackException e){
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}
	
	/**
	 * 
	* @Title: isGoodsNameDuplicate
	* @Description:判断分组名是否存在
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public boolean isGoodsCategoryNameDuplicate(String categoryName,TShopInfo shopInfo)throws CMException, CMRollBackException {
		String xFunctionName = "isGoodsCategoryNameDuplicate";
		boolean resultFlag = true;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		try{
			if(StringUtils.isBlank(categoryName)){
				gLogger.end(xFunctionName);
				throw CMException.sys("categoryName is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(shopInfo==null){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(shopInfo.getShopId()==null){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			goodsRequestBean.setShopInfo(shopInfo);
			TShopCategoryGoods shopCategoryGoods = new TShopCategoryGoods();
			shopCategoryGoods.setCategoryName(categoryName);
			goodsRequestBean.setShopCategoryGoods(shopCategoryGoods);
			goodsResponseBean = goodsService.queryGoodsCategoryByCategoryName(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				if(goodsResponseBean.getResultList()!=null&&goodsResponseBean.getResultList().size()>0){
					resultFlag=true;
				}else{
					resultFlag=false;
				}
			}else{
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}catch(CMRollBackException e){
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return resultFlag;
	}
	
	/**
	 * 
	* @Title: queryCountCategoryNum
	* @Description:统计该店铺的分组数
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public int queryCountCategoryNum(TShopInfo shopInfo)throws CMException, CMRollBackException {
		String xFunctionName = "isGoodsCategoryNameDuplicate";
		int categoryNum=0;
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		GoodsResponseBean goodsResponseBean= null;
		try{
			if(shopInfo==null){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopInfo is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			if(shopInfo.getShopId()==null){
				gLogger.end(xFunctionName);
				throw CMException.sys("shopId is null",
						ExceptionConstants.ILLEGAL_ARGUMENT_CODE, "shopCategoryGoods");
			}
			goodsRequestBean.setShopInfo(shopInfo);
			goodsResponseBean = goodsService.queryGoodsCategoryByShopId(goodsRequestBean);
			if("00".equals(goodsResponseBean.getResponseCode())){
				if(goodsResponseBean.getResultList()!=null&&goodsResponseBean.getResultList().size()>0){
					categoryNum = goodsResponseBean.getResultList().size();
				}
			}else{
				throw new CMRollBackException(goodsResponseBean.getErrMsg());
			}
		}catch(CMRollBackException e){
			throw e;
		}
		catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return categoryNum;
	}
	@SuppressWarnings("unchecked")
	private void setImageFilePath(GoodsResponseBean goodsResponseBean){
		GoodsRequestBean goodsRequestBean = new GoodsRequestBean();
		TSysFile sysFile = new TSysFile();
		if(goodsResponseBean.getResultList()!=null&&goodsResponseBean.getResultList().size()>0){
			List<TShopGoods> shopGoodsList = (List<TShopGoods>) goodsResponseBean.getResultList();
			for(TShopGoods shopGoods:shopGoodsList){
				
				Integer fileId = shopGoods.getShopGoodsImgFileId();
				sysFile.setFileId(fileId);
				goodsRequestBean.setSysFile(sysFile);
				GoodsResponseBean goodsResponseBean1 = goodsService.querySysFileByFileId(goodsRequestBean);
				if(goodsResponseBean!=null&&goodsResponseBean.getSysFile()!=null
						&&StringUtils.isNotBlank(goodsResponseBean.getSysFile().getFileAccessUrl())){
						shopGoods.setTemporaryFilePath(
								goodsResponseBean1.getSysFile().getFileAccessUrl());
				}
			}
		}
		
	}

	@Override
	public GoodsResponseBean querySysFileByFileId(GoodsRequestBean goodsRequestBean) throws CMException,
			CMRollBackException {
		GoodsResponseBean goodsResponseBean = goodsService.querySysFileByFileId(goodsRequestBean);
		return goodsResponseBean;
	}

}
