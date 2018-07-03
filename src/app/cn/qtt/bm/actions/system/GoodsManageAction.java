package app.cn.qtt.bm.actions.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.CodeCache;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.TxUUIDGenerator;
import app.cn.qtt.bm.manage.IGoodsMgr;
import app.cn.qtt.bm.manage.impl.GoodsMgr;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopCategoryGoodsLink;
import app.cn.qtt.bm.model.TShopGoods;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;
@Namespace("/system")
@ParentPackage("system-default")
@Results({
	@Result(name = "authcodeVerifyViewSkip", location = "/WEB-INF/content/system/authcode-verify-skip.jsp")
})
public class GoodsManageAction extends BaseAction{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5527245457287152062L;

	private IGoodsMgr goodsMgr;
	
	private GoodsResponseBean goodsResponseBean;
	
	private TShopGoods shopGoods;
	
	private TShopCategoryGoods shopCategoryGoods;
	
	private List<TShopCategoryGoods> categoryList;
	
	private String timeCode;
	
	private String categoryIdArr;
	
	private String shopGoodId;
	
	private String flag;//验证码领餐时，1表示从供应商页面过来，而非后退按钮过来
	
	public String execute(){
		final String xFunctionName  = "GoodsManageAction.execute()";
		gLogger.begin(xFunctionName);
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			if(userbean!=null){
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			//查菜单分组
			GoodsResponseBean categoryResponseBean = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
				categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
			}
			
			
			//查默认分组下及每天都提供的菜单
			shopCategoryGoods = new TShopCategoryGoods();
			if(categoryList!=null&&categoryList.size()>0){
				shopCategoryGoods = categoryList.get(0);
			}
			GoodsRequestBean goodRequestBean = new GoodsRequestBean();
			List<String> timeCodeList=new ArrayList<String>();
			timeCodeList.add(Constants.TIME_CODE_EVERYDAY);
			goodRequestBean.setTimeCodeList(timeCodeList);
			goodRequestBean.setShopInfo(shopInfo);
			goodRequestBean.setShopCategoryGoods(shopCategoryGoods);
			goodRequestBean.setCurrentPage(1);
			goodsResponseBean = goodsMgr.queryGoodsByCategory(goodRequestBean);
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
		return SUCCESS;
	}
	
	public String queryGoodsByPageAjax(){
		final String xFunctionName  = "queryGoodsByPageAjax()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			if(userbean!=null){
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			
			
			//查默认分组下及每天都提供的菜单
			TShopCategoryGoods shopCategoryGoods = new TShopCategoryGoods();
			shopCategoryGoods.setCategoryId(Integer.valueOf(categoryIdArr));
			GoodsRequestBean goodRequestBean = new GoodsRequestBean();
			if(StringUtils.isNotBlank(timeCode)){
					List<String> timeCodeList=new ArrayList<String>();
					timeCodeList.add(timeCode);
					goodRequestBean.setTimeCodeList(timeCodeList);
			}
			goodRequestBean.setShopInfo(shopInfo);
			goodRequestBean.setShopCategoryGoods(shopCategoryGoods);
			goodRequestBean.setCurrentPage(1);
			goodsResponseBean = goodsMgr.queryGoodsByCategory(goodRequestBean);
			result.put("goodsResponseBean", goodsResponseBean);
			result.put("isSuccess",true);
			
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	/**
	 * 
	* @Title: getAddGoodsView
	* @Description: 进入添加菜单界面
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getAddGoodsView(){
		final String xFunctionName  = "getAddGoodsView()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			TSysUserInfo sysUserInfo =null;
			if(userbean!=null){
				sysUserInfo=userbean.getUserInfo();
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			
			if(isInBmTime()){
				result.put("isSuccess",false);
				result.put("isInBmTime",true);
				return null;
			}
			
			//查菜单分组
			GoodsResponseBean categoryResponseBean = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
				categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
				if(categoryList==null||categoryList.size()<=0){
					TShopCategoryGoods shopCategoryGoods = new TShopCategoryGoods();
					shopCategoryGoods.setCategoryStatus(Constants.GOOD_CATEGORY_STATUS_00);//生效状态“00”
					shopCategoryGoods.setCategoryName("默认");
					shopCategoryGoods.setShopId(shopId);
					goodsMgr.addGoodsCategory(shopCategoryGoods);
					GoodsResponseBean categoryResponseBean2 = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
					if(categoryResponseBean2!=null&&categoryResponseBean2.getResultList()!=null){
						categoryList=(List<TShopCategoryGoods>) categoryResponseBean2.getResultList();
					}
				}
				result.put("categoryList",categoryList);
			}
			result.put("isSuccess",true);
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	/**
	 * 
	* @Title: addGoods
	* @Description: 添加菜单
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String addGoods(){
		final String xFunctionName  = "addGoods()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			TSysUserInfo sysUserInfo =null;
			if(userbean!=null){
				sysUserInfo=userbean.getUserInfo();
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			TShopInfo shopInfo = new TShopInfo();
			
			shopInfo.setShopId(shopId);
			shopGoods.setShopId(shopId);
			if(StringUtils.isNotBlank(shopGoods.getShopGoodsName())){
				boolean flag =goodsMgr.isGoodsNameDuplicate(shopGoods.getShopGoodsName(),shopInfo);
				if(flag==true){
					result.put("isSuccess",false);
					result.put("isGoodsNameDuplicate",true);
					return null;
				}
			}
			
			
			TShopCategoryGoodsLink shopCategoryGoodsLink = new TShopCategoryGoodsLink();
			shopCategoryGoodsLink.setCategoryId(Integer.valueOf(shopGoods.getCategoryGoodsId()));
			shopCategoryGoodsLink.setCreateUserCode(sysUserInfo.getUserCode());
			shopCategoryGoodsLink.setModifyUserCode(sysUserInfo.getUserCode());
			shopCategoryGoodsLink.setCreateTime(new Date());
			shopCategoryGoodsLink.setModifyTime(new Date());
			goodsMgr.addGoods(shopGoods,shopCategoryGoodsLink,sysUserInfo);
			result.put("isSuccess",true);
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: getGoodsDetail
	* @Description: 根据ID获取菜单详情
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getGoodsDetail(){
		final String xFunctionName  = "getGoodsDetail()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			TSysUserInfo sysUserInfo =null;
			if(userbean!=null){
				sysUserInfo=userbean.getUserInfo();
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			if(StringUtils.isNotBlank(shopGoodId)){
				TShopGoods shopGoodsExample = new TShopGoods();
				shopGoodsExample.setShopGoodsId(Integer.valueOf(shopGoodId));
				shopGoodsExample.setShopGoodsStatus(Constants.ORDER_GOOD_STATUS_00);
				goodsResponseBean =goodsMgr.queryGoodsInfoById(shopGoodsExample);
				result.put("goodsResponseBean", goodsResponseBean);
				//查菜单分组
				GoodsResponseBean categoryResponseBean = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
				if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
					categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
					result.put("categoryList",categoryList);
				}
			}else{
				result.put("goodsResponseBean",null);
			}
			
			
			result.put("isSuccess",true);
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	 * 
	* @Title: updateGoods
	* @Description: 修改菜单
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String updateGoods(){
		final String xFunctionName  = "updateGoods()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			TSysUserInfo sysUserInfo =null;
			if(userbean!=null){
				sysUserInfo=userbean.getUserInfo();
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			if(isInBmTime()){
				result.put("isSuccess",false);
				result.put("isInBmTime",true);
				return null;
			}
			
			if(StringUtils.isNotBlank(shopGoods.getShopGoodsName())){
				shopGoods.setShopGoodsStatus(Constants.ORDER_GOOD_STATUS_00);
				GoodsResponseBean goodsResponseBean =goodsMgr.queryGoodsInfoById(shopGoods);
				if(goodsResponseBean!=null&&goodsResponseBean.getShopGoods()!=null&&StringUtils.isNotBlank(goodsResponseBean.getShopGoods().getShopGoodsName())){
					String oldShopGoodsName= goodsResponseBean.getShopGoods().getShopGoodsName();
					if(!shopGoods.getShopGoodsName().equals(oldShopGoodsName)){
						boolean flag =goodsMgr.isGoodsNameDuplicate(shopGoods.getShopGoodsName(),shopInfo);
						if(flag==true){
							result.put("isSuccess",false);
							result.put("isGoodsNameDuplicate",true);
							return null;
						}
					}
				}
				
			}
			
			
			shopGoods.setShopId(shopId);
			TShopCategoryGoodsLink shopCategoryGoodsLink = new TShopCategoryGoodsLink();
			shopCategoryGoodsLink.setShopGoodsId(shopGoods.getShopGoodsId());
			shopCategoryGoodsLink.setCategoryId(Integer.valueOf(shopGoods.getCategoryGoodsId()));
			shopCategoryGoodsLink.setCreateUserCode(sysUserInfo.getUserCode());
			shopCategoryGoodsLink.setModifyUserCode(sysUserInfo.getUserCode());
			shopCategoryGoodsLink.setCreateTime(new Date());
			shopCategoryGoodsLink.setModifyTime(new Date());
			boolean flag = goodsMgr.modifyGoods(shopGoods,shopCategoryGoodsLink,sysUserInfo);
			if(flag==true){
				result.put("isSuccess",true);
			}
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}

	/**
	 * 
	* @Title: delGoods
	* @Description: 删除菜单
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String delGoods(){
		final String xFunctionName  = "delGoods()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			TSysUserInfo sysUserInfo =null;
			if(userbean!=null){
				sysUserInfo=userbean.getUserInfo();
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			if(isInBmTime()){
				result.put("isSuccess",false);
				result.put("isInBmTime",true);
				return null;
			}
			shopGoods.setShopId(shopId);
			shopGoods.setShopGoodsStatus(Constants.ORDER_GOOD_STATUS_01);
			boolean flag = goodsMgr.deleteGoods(shopGoods);
			if(flag==true){
				result.put("isSuccess",true);
			}
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	* @Title: addCategory
	* @Description: 添加分组
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String addCategory(){
		final String xFunctionName  = "queryUsersByPage()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			if(userbean!=null){
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			if(isInBmTime()){//在订餐及统计时间内
				result.put("isSuccess",false);
				result.put("isInBmTime",true);
				return null;
			}
			if(goodsMgr.queryCountCategoryNum(shopInfo)>=Integer.valueOf(CacheConstants.CATEGORY_MAX_NUM())){//大于等于最大分组个数
				result.put("isSuccess",false);
				result.put("moreThanMaxNum",true);
				return null;
			}
			if(StringUtils.isNotBlank(shopCategoryGoods.getCategoryName())){
				boolean flag =goodsMgr.isGoodsCategoryNameDuplicate(shopCategoryGoods.getCategoryName(),shopInfo);
				if(flag==true){
					result.put("isSuccess",false);
					result.put("isGoodsCategoryNameDuplicate",true);
					return null;
				}
			}
			
			shopCategoryGoods.setShopId(shopId);
			shopCategoryGoods.setCategoryStatus(Constants.GOOD_CATEGORY_STATUS_00);//生效状态“00”
			goodsMgr.addGoodsCategory(shopCategoryGoods);
			//查菜单分组
			GoodsResponseBean categoryResponseBean = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
				categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
			}
			result.put("categoryList",categoryList);
			result.put("isSuccess",true);
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	 * 
	* @Title: updateCategory
	* @Description: 修改分组
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	
	public String updateCategory(){
		final String xFunctionName  = "queryUsersByPage()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			if(userbean!=null){
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			
			if(isInBmTime()){
				result.put("isSuccess",false);
				result.put("isInBmTime",true);
				return null;
			}
			if(StringUtils.isNotBlank(shopCategoryGoods.getCategoryName())){
				boolean flag =goodsMgr.isGoodsCategoryNameDuplicate(shopCategoryGoods.getCategoryName(),shopInfo);
				if(flag==true){
					result.put("isSuccess",false);
					result.put("isGoodsCategoryNameDuplicate",true);
					return null;
				}
			}
			
			shopCategoryGoods.setShopId(shopId);
			goodsMgr.modifyCategory(shopCategoryGoods);
			//查菜单分组
			GoodsResponseBean categoryResponseBean = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
				categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
			}
			result.put("categoryList",categoryList);
			result.put("isSuccess",true);
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: delCategory
	* @Description: 删除分组
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String delCategory(){
		final String xFunctionName  = "delCategory()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			if(userbean!=null){
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			
			TShopInfo shopInfo = new TShopInfo();
			shopInfo.setShopId(shopId);
			
			if(isInBmTime()){
				result.put("isSuccess",false);
				result.put("isInBmTime",true);
				return null;
			}
			shopCategoryGoods.setShopId(shopId);
			goodsMgr.deleteGoodsCategory(shopCategoryGoods);
			//查菜单分组
			GoodsResponseBean categoryResponseBean = goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
				categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
			}
			result.put("categoryList",categoryList);
			result.put("isSuccess",true);
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	/**
	 * 
	* @Title: imgFileUpload
	* @Description:菜单图片上传ajax
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String imgFileUpload(){
		String xFunctionName = "imgFileUpload()";
		Map<String,Object> result = new HashMap<String,Object>();
		InputStream is = null;
		OutputStream os =null;
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		OutputStreamWriter write = null;
		BufferedWriter bw = null;
		try {
			//session中获得
			MultiPartRequestWrapper request = (MultiPartRequestWrapper) super.getContextRequest();
			//手动取值
			File[] fileUploads = request.getFiles("upload");
			if (fileUploads == null || fileUploads.length <= 0
					|| !fileUploads[0].isFile() || !fileUploads[0].exists()) {
				result.put("isSuccess", false);
				result.put("code", "02");
				result.put("message", "文件错误");
				print(result);
				return null;
			}
			
			File fileUpload = fileUploads[0];
			is=new FileInputStream(fileUpload);
			
			String path = CacheConstants.LINSHI_UPLOAD_IMAGE_FILE_PATH();
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
			File file = new File(path + fileName);
			if (!file.exists()) {
				filePath.createNewFile();
			}
			os= new FileOutputStream(file);
	        byte[] buffer = new byte[1024];  
	  
	        int length = 0;  
	  
	        //读取myFile文件输出到toFile文件中  
	        while ((length = is.read(buffer)) > 0) {  
	            os.write(buffer, 0, length);  
	        }  
         
			
	        is.close();  
	    
	        os.close();  
			
			
			

			
			
		
			
			
			
			result.put("isSuccess", true);
			result.put("filePath", file.getAbsolutePath());
			if(StringUtils.isNotBlank(file.getAbsolutePath())){
				if(file.getAbsolutePath().indexOf(CacheConstants.TOP_FILE_PATH()) > -1){
					String replaceTopPath = CacheConstants.ORIGINAL_IMAGE_HTTP_PATH_PREFIX;//从配置文件取出的头部替换路径
					String accessFilePath ="";
					if(StringUtils.isNotBlank(replaceTopPath)){
						accessFilePath = file.getAbsolutePath().replace(CacheConstants.TOP_FILE_PATH(),replaceTopPath);
					}else{
						accessFilePath= file.getAbsolutePath().replace(CacheConstants.TOP_FILE_PATH(),CacheConstants.TOP_REPLACE_FILE_PATH());
					}
					result.put("accessFilePath", accessFilePath.replaceAll("\\\\", "/")); //url路径时,统一转换成"/"
				}
			}
			 
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			result.put("isSuccess", false);
			result.put("code", "99");
			gLogger.exception(xFunctionName, e);
		}finally{
			
			try {
				if(read != null){
					read.close();
				}
				if(bufferedReader != null){
					bufferedReader.close();
				}
				if(write != null){
					write.close();
				}
				if(bw != null){
					bw.flush();
					bw.close();
				}
				
			} catch (Exception e2) {
				result.put("isSuccess", false);
				result.put("code", "99");
				gLogger.exception(xFunctionName, e2);
			}
			gLogger.end(xFunctionName);
		}
		super.print(result);
		return null;
	}
	/**
	 * 
	 * 进入验证码验证跳转页面，非真实页面
	 * @return
	 * @author wangwenwei
	 * @createtime 2013-9-23 下午5:28:55
	 */
	public String getAuthcodeVerifySkip(){
		final String xFunctionName  = "getAuthcodeVerify()";
		gLogger.begin(xFunctionName);
		try{
			
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
		} finally {
			gLogger.end(xFunctionName);
		}
		return "authcodeVerifyViewSkip";
	}
	
	/**
	 * 
	* @Title: isInBmTime
	* @Description: 判断当前时间是否在订餐时间
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	private boolean isInBmTime(){
		boolean flag = false;
		Calendar calendar = Calendar.getInstance(); 
		int week = calendar.get(Calendar.DAY_OF_WEEK)-1; 
		if(week==0||week==6){
			return false;
		}
		try {
			Calendar bmStartCal = Calendar.getInstance();
			Calendar nowCal = Calendar.getInstance();
			Calendar orderCountEndCal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String bmStartTime = CacheConstants.BM_START_TIME();
			String bmEndTime = CacheConstants.ORDER_COUNT_END_TIME();
			Date now = new Date();
			String nowTime = sdf.format(now);
			nowCal.setTime(sdf.parse(nowTime));
			bmStartCal.setTime(sdf.parse(bmStartTime));
			orderCountEndCal.setTime(sdf.parse(bmEndTime));
			if(bmStartCal.before(nowCal)&& orderCountEndCal.after(nowCal)){//在订餐时间段内
				flag=true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}

	public IGoodsMgr getGoodsMgr() {
		return goodsMgr;
	}

	public void setGoodsMgr(IGoodsMgr goodsMgr) {
		this.goodsMgr = goodsMgr;
	}

	public GoodsResponseBean getGoodsResponseBean() {
		return goodsResponseBean;
	}

	public void setGoodsResponseBean(GoodsResponseBean goodsResponseBean) {
		this.goodsResponseBean = goodsResponseBean;
	}

	public TShopGoods getShopGoods() {
		return shopGoods;
	}

	public void setShopGoods(TShopGoods shopGoods) {
		this.shopGoods = shopGoods;
	}

	public TShopCategoryGoods getShopCategoryGoods() {
		return shopCategoryGoods;
	}

	public void setShopCategoryGoods(TShopCategoryGoods shopCategoryGoods) {
		this.shopCategoryGoods = shopCategoryGoods;
	}

	public List<TShopCategoryGoods> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<TShopCategoryGoods> categoryList) {
		this.categoryList = categoryList;
	}

	public String getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(String timeCode) {
		this.timeCode = timeCode;
	}

	public String getCategoryIdArr() {
		return categoryIdArr;
	}

	public void setCategoryIdArr(String categoryIdArr) {
		this.categoryIdArr = categoryIdArr;
	}

	public String getShopGoodId() {
		return shopGoodId;
	}

	public void setShopGoodId(String shopGoodId) {
		this.shopGoodId = shopGoodId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
	
	
	
	
}

