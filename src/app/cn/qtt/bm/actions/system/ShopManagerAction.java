package app.cn.qtt.bm.actions.system;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.DateUtil;
import app.cn.qtt.bm.common.utils.MD5Encrypt;
import app.cn.qtt.bm.common.utils.TxUUIDGenerator;
import app.cn.qtt.bm.manage.IUserMgr;
import app.cn.qtt.bm.manage.impl.GoodsMgr;
import app.cn.qtt.bm.manage.impl.OrderMgr;
import app.cn.qtt.bm.manage.impl.ShopMgr;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.GoodsRequestBean;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;
import app.cn.qtt.bm.service.pojo.ShopRequestBean;
import app.cn.qtt.bm.service.pojo.ShopResponseBean;

@Namespace("/system")
@Results({
    @Result(name = "add", location = "/WEB-INF/content/system/shop-manager-add.jsp"),
    @Result(name = "edit", location = "/WEB-INF/content/system/shop-manager-edit.jsp"),
    @Result(name = "showGoods", location = "/WEB-INF/content/system/shop-manager-goods.jsp"),
    @Result(name = "curShopView", location = "/WEB-INF/content/system/curShopView.jsp"),
    @Result(name = "updatePasswordView", location = "/WEB-INF/content/system/updateShopPasswordView.jsp"),
    @Result(name = "getUserOrder", location = "/WEB-INF/content/system/user-order.jsp")

    
})
@ParentPackage("system-default")
public class ShopManagerAction extends BaseAction{

	private static final long serialVersionUID = 2341698460705283544L;

	private IUserMgr userMgr;
	private ShopMgr shopMgr;
	private GoodsMgr goodsMgr;
	private OrderMgr  orderMgr;
	
	private ShopRequestBean requestBean;
	private ShopResponseBean responseBean;
	private GoodsResponseBean goodsResponseBean;

	private TShopInfo shopInfo;
	
	private String shopPhone;
	private Integer shopId;
	public Integer getShopId() {
		return shopId;
	}


	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}


	private String oldPassword;
	private String newPassword;
	
	private String shopStatus;
	
	private String userCode;

	private String fileAccessUrl;
	
	private TShopCategoryGoods shopCategoryGoods;
	
	List<TShopCategoryGoods> categoryList;

	
	public String execute(){
		final String xFunctionName  = "GoodsManageAction.execute()";
		ShopRequestBean tShopRequestBean = new ShopRequestBean();
		TShopInfo tShopInfo = new TShopInfo();
		try{
			gLogger.begin(xFunctionName);
//			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
//			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
//					return LOGIN;
//			}
			if(null != shopInfo){
				if(StringUtils.isNotBlank(shopInfo.getShopName())){
					tShopInfo.setShopName(shopInfo.getShopName().trim());
				}
				if(StringUtils.isNotBlank(shopInfo.getShopPhoneNumber())){
					tShopInfo.setShopPhoneNumber(shopInfo.getShopPhoneNumber().trim());
				}
			}
			tShopRequestBean.setPageRecords(Integer.parseInt(super.getPageRecorders()));
			tShopRequestBean.setCurrentPage(Integer.parseInt(super.getCurrentPage()));
			tShopRequestBean.setShopInfo(tShopInfo);
			responseBean = shopMgr.selectFuzzyPagesShops(tShopRequestBean);
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return SUCCESS;
	}


	/**
	 * 供应商列表 JSON
	 * @Author huangwj
	 * @Create Date: 2013-06-28
	 * @return JSON
	 */
	public String queryListJson(){
		final String xFunctionName = "ShopManagerAction.queryListJson()";
		gLogger.begin(xFunctionName);
		
		ShopRequestBean tShopRequestBean = new ShopRequestBean();
		TShopInfo tShopInfo = new TShopInfo();
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(null != shopInfo){
				if(StringUtils.isNotBlank(shopInfo.getShopName())){
					tShopInfo.setShopName(shopInfo.getShopName());
				}
				if(StringUtils.isNotBlank(shopInfo.getShopPhoneNumber())){
					tShopInfo.setShopPhoneNumber(shopInfo.getShopPhoneNumber());
				}
			}
			
			tShopRequestBean.setPageRecords(Integer.parseInt(super.getPageRecorders()));
			tShopRequestBean.setCurrentPage(Integer.parseInt(super.getCurrentPage()));
			tShopRequestBean.setShopInfo(tShopInfo);
			responseBean = shopMgr.selectFuzzyPagesShops(tShopRequestBean);
			
			result.put("responseBean", responseBean);
			result.put("isSuccess", true);
			
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			result.put("isSuccess", false);
			result.put("errorMsg", e.getMessage());
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	
	/**
	 * 添加店铺
	 *方法名称 addShop
	 *方法描述
	 * @return
	 * @Date 2013-6-19
	 * @author xupj
	 */
	public String addOrUpdateShop(){
 		final String xFunctionName  = "GoodsManageAction.addOrUpdateShop()";
		boolean flag = false;
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
				result.put("isSuccess", false);
				result.put("nopass", 1001);
				return null;
			}
			
			if(isInBmTime()){
				result.put("isSuccess", false);
				//result.put("errorMsg", "订餐时段["+getOrderTime()+"]禁止维护店铺信息！");
				result.put("errorMsg", "订餐时段禁止维护店铺信息！");
				flag = false;
				return null;
			}
			
			if(null == shopInfo){
				result.put("isSuccess", false);
				result.put("errorMsg", "未提交任何店铺信息！");
				flag = false;
				return null;
			}

			flag = shopMgr.validPhoneShopAndUser(shopInfo);
			if(!flag){
				result.put("isSuccess", false);
				result.put("errorMsg", "店铺手机号码已存在，请输入新的手机号！");
				flag = false;
				return null;
			}
			if(null ==shopInfo.getShopId()){
				//flag = shopMgr.addShop(shopInfo, sessionUserInfo.getUserInfo());
				flag = shopMgr.addShop(shopInfo,sessionUserInfo.getUserInfo(), fileAccessUrl);
			}else{
				flag = shopMgr.modifyShop(shopInfo, sessionUserInfo.getUserInfo(), fileAccessUrl);
			}
			if(flag){
				result.put("isSuccess", true);
			}else{
				result.put("isSuccess", false);
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			result.put("isSuccess", false);
			result.put("errorMsg", e.getMessage());
		}finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	 * 删除店铺
	 *方法名称 deleteShop
	 *方法描述
	 * @return
	 * @Date 2013-6-19
	 * @author xupj
	 */
	public String deleteShop(){
		final String xFunctionName  = "GoodsManageAction.deleteShop()";
		boolean flag = false;
		TShopInfo tShopInfo = new TShopInfo();
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
				result.put("isSuccess", false);
				result.put("nopass", 1001);
				return null;
			}
			
			if(isInBmTime()){
				result.put("isSuccess", false);
				result.put("errorMsg", "订餐时段禁止维护店铺信息！");
				flag = false;
				return null;
			}
			
			ids = id.split(",");
			for(int i = 0; i< ids.length; i++){
				if(null != ids[i]){
					tShopInfo.setShopId(Integer.parseInt(ids[i]));
					flag = shopMgr.deleteShop(tShopInfo);
				}
			}
			if(flag){
				result.put("isSuccess", true);
			}else{
				result.put("isSuccess", false);
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			result.put("isSuccess", false);
			result.put("errorMsg", e.getMessage());
		}finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	 * 暂停/启用店铺
	 *方法名称 toggleShop
	 *方法描述
	 * @return
	 * @Date 2013-6-19
	 * @author xupj
	 */
	public String toggleShop(){
		final String xFunctionName  = "GoodsManageAction.toggleShop()";
		boolean flag = false;
		String[] phoneNumArr = null;
		TSysUserInfo sysUserInfo = null;
		TShopInfo tShopInfo = new TShopInfo();
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			gLogger.begin(xFunctionName);
			UserBean sessionUserInfo = super.getSystemUserInfoFromSession();
			if(null == sessionUserInfo || null == sessionUserInfo.getUserInfo()){
				result.put("isSuccess", false);
				result.put("nopass", 1001);
				return null;
			}
			
			if(isInBmTime()){
				result.put("isSuccess", false);
				result.put("errorMsg", "订餐时段禁止维护店铺信息！");
				flag = false;
				return null;
			}
			
			sysUserInfo = sessionUserInfo.getUserInfo();
			
			phoneNumArr = shopPhone.split(",");
			ids = id.split(",");
			for(int i = 0; i< phoneNumArr.length; i++){
				if(null != phoneNumArr[i]){
					tShopInfo.setShopPhoneNumber(phoneNumArr[i]);
					tShopInfo.setShopId(Integer.parseInt(ids[i]));
					flag = shopMgr.toggleShop(tShopInfo, sysUserInfo, shopStatus);
				}
			}
			if(flag){
				result.put("isSuccess", true);
			}else{
				result.put("isSuccess", false);
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
			result.put("isSuccess", false);
			result.put("errorMsg", e.getMessage());
		}finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	 * 跳转到店铺菜单页面
	 *方法名称 showShopGoods
	 *方法描述
	 * @return
	 * @Date 2013-6-28
	 * @author xupj
	 */
	public String showShopGoods(){
		final String xFunctionName  = "showShopGoods()";
		GoodsRequestBean goodRequestBean = new GoodsRequestBean();
		GoodsResponseBean categoryResponseBean = null;
		try{
			//查菜单分组
			categoryResponseBean= goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			if(categoryResponseBean!=null&&categoryResponseBean.getResultList()!=null){
				categoryList=(List<TShopCategoryGoods>) categoryResponseBean.getResultList();
				if(null != categoryList){
					shopCategoryGoods = (TShopCategoryGoods)	categoryList.get(0);
				}
				goodRequestBean.setShopInfo(shopInfo);
				goodRequestBean.setShopCategoryGoods(shopCategoryGoods);
				goodRequestBean.setPageRecords(15);
				goodRequestBean.setCurrentPage(1);
				goodsResponseBean = goodsMgr.queryGoodsByCategory(goodRequestBean);
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return "showGoods";
	}
	
	/**
	 * 查询菜单分类
	 * @return
	 * @Date 2013-7-2
	 * @author huangwj
	 */
	public String queryCategoryAjax(){
		final String xFunctionName  = "queryCategoryAjax()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			//查菜单分组
			GoodsResponseBean categoryResponseBean= goodsMgr.queryGoodsCategoryByShopId(shopInfo);
			result.put("categoryResponseBean", categoryResponseBean);
			result.put("isSuccess",true);
			
		}catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			result.put("isSuccess",false);
			result.put("errorMsg",e.getMessage());
		} finally {
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	 * ajax查询菜单
	 *方法名称 queryGoodsByPageAjax
	 *方法描述
	 * @return
	 * @Date 2013-6-28
	 * @author xupj
	 */
	public String queryGoodsByPageAjax(){
		final String xFunctionName  = "queryGoodsByPageAjax()";
		gLogger.begin(xFunctionName);
		GoodsRequestBean goodRequestBean = new GoodsRequestBean();
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			goodRequestBean.setShopInfo(shopInfo);
			goodRequestBean.setShopCategoryGoods(shopCategoryGoods);
			goodRequestBean.setPageRecords(Constants.DEFAULT_PAGERECORDS);
			goodRequestBean.setCurrentPage(Integer.parseInt(super.currentPage));
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
	* @Title: getCurrentShop
	* @Description:获取当前登录的店铺
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getCurrentShop(){
		final String xFunctionName  = "getCurrentShop()";
		gLogger.begin(xFunctionName);
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			Integer shopId=null;
			if(userbean!=null){
				if(userbean.getUserInfo()!=null){
					shopId = userbean.getUserInfo().getUserShopId();
				}
			}
			shopInfo =	shopMgr.queryShopInfoById(shopId);
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return "curShopView";
	}
	
	/**
	 * 
	* @Title: updateCurrentShop
	* @Description:修改当前登录的店铺信息
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String updateCurrentShop(){
		final String xFunctionName  = "updateCurrentShop()";
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
			shopInfo.setShopId(shopId);
			if(isInBmTime()){
				result.put("isSuccess",false);
				result.put("isInBmTime",true);
				return null;
			}
			if(!shopInfo.getShopPhoneNumber().equals(sysUserInfo.getUserPhoneNumber())){
				boolean isduplicateFlag = shopMgr.validPhoneShopAndUser(shopInfo);
				if(!isduplicateFlag){
					result.put("isSuccess", false);
					result.put("isduplicateFlag", true);
					return null;
				}
			}
			boolean flag = shopMgr.modifyShop(shopInfo, sysUserInfo, null);
			if(flag==true){
				shopInfo =	shopMgr.queryShopInfoById(shopId);
				result.put("isSuccess",true);
			}
			result.put("shopInfo",shopInfo);
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	/**
	 * 
	* @Title: getUpdatePasswordView
	* @Description: 进入修改当前店铺密码界面
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getUpdatePasswordView(){
		final String xFunctionName  = "getUpdatePasswordView()";
		gLogger.begin(xFunctionName);
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return "updatePasswordView";
	}
	/**
	 * 
	* @Title: updateShopPassword
	* @Description:  修改当前登录店铺密码
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String updateShopPassword(){
		final String xFunctionName  = "updateCurrentShop()";
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
			if(StringUtils.isNotBlank(oldPassword)){
				if(!MD5Encrypt.MD5Encode(oldPassword).equals(sysUserInfo.getUserPassword())){//原始密码错误
					result.put("isSuccess",false);
				}else{
					sysUserInfo.setUserPassword(MD5Encrypt.MD5Encode(newPassword));
					sysUserInfo.setModifyTime(new Date());
					boolean flag = userMgr.updateUserPassword(sysUserInfo);
					if(flag==true){
						result.put("isSuccess",true);
						result.put("shopInfo",shopInfo);
					}
				}
			}
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
	}
	
	

	/**
	 * 
	 * 供应商图片上传ajax
	 * @return json
	 */
	public String imageUpload(){
		String xFunctionName = "imageUpload()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			//session中获得
			MultiPartRequestWrapper request = (MultiPartRequestWrapper) super.getContextRequest();
			//手动取值
			File[] uploadFiles = request.getFiles("upload");
			String[] uploadFileNames = request.getFileNames("upload");
			if (uploadFiles == null || uploadFiles.length <= 0
					|| !uploadFiles[0].isFile() || !uploadFiles[0].exists()) {
				result.put("isSuccess", false);
				result.put("code", "02");
				result.put("errorMsg", "文件错误！");
				print(result);
				return null;
			}
			File uploadFile = uploadFiles[0];
			String uploadFileName = uploadFileNames[0];

			//创建上传文件目的保存路径
			String dateStr = new SimpleDateFormat("yyyy//MM//dd//HH").format(new Date());
			// 按日期拼路径
			String path = CacheConstants.LINSHI_UPLOAD_IMAGE_FILE_PATH() + "//" + dateStr + "//";
			
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			
			String fileName = TxUUIDGenerator.getUUID() + fileType;
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File file = new File(path + fileName);
			if (!file.exists()) {
				filePath.createNewFile();
			}
			//保存文件
			FileUtils.copyFile(uploadFile, file);
			
			result.put("isSuccess", true);
			result.put("filePath", file.getAbsolutePath());

			if(StringUtils.isNotBlank(file.getAbsolutePath())){
				if(file.getAbsolutePath().indexOf(CacheConstants.TOP_FILE_PATH()) > -1){
					String fileAccessUrl="";
					String replaceTopPath = CacheConstants.ORIGINAL_IMAGE_HTTP_PATH_PREFIX;//从配置文件取出的头部替换路径
					if(StringUtils.isNotBlank(replaceTopPath)){
						fileAccessUrl = file.getAbsolutePath().replace(CacheConstants.TOP_FILE_PATH(),replaceTopPath);
					}else{
						fileAccessUrl= file.getAbsolutePath().replace(CacheConstants.TOP_FILE_PATH(), CacheConstants.TOP_REPLACE_FILE_PATH());
					}
					result.put("fileAccessUrl", fileAccessUrl.replaceAll("\\\\", "/")); //url路径时,统一转换成"/"
				}
			}
			
		} 
		catch (Exception e) {
			result.put("isSuccess", false);
			result.put("code", "99");
			result.put("errorMsg", e.getMessage());
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		return null;
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
			String bmEndTime =CacheConstants.ORDER_COUNT_END_TIME();
			Date now = new Date();
			String nowTime = sdf.format(now);
			nowCal.setTime(sdf.parse(nowTime));
			bmStartCal.setTime(sdf.parse(bmStartTime));
			orderCountEndCal.setTime(sdf.parse(bmEndTime));
			
			if(bmStartCal.before(nowCal)&& orderCountEndCal.after(nowCal)){//在订餐时间段内
				flag=true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	
	
	public String getUserOrder(){
		String xFunctionName = "getUserOrder()";
		try{
			gLogger.begin(xFunctionName);
			MyOrder myOrder = new MyOrder();
			myOrder.setUserCode(userCode);
			myOrder.setShopId(shopId);
		}catch(Exception e){
			gLogger.exception(xFunctionName,e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return "getUserOrder";
	}

	//--------------------------------------------getter/setter-----------------------------------------------
	public ShopResponseBean getResponseBean() {
		return responseBean;
	}

	public void setResponseBean(ShopResponseBean responseBean) {
		this.responseBean = responseBean;
	}

	public ShopMgr getShopMgr() {
		return shopMgr;
	}
	public void setShopMgr(ShopMgr shopMgr) {
		this.shopMgr = shopMgr;
	}
	public ShopRequestBean getRequestBean() {
		return requestBean;
	}

	public void setRequestBean(ShopRequestBean requestBean) {
		this.requestBean = requestBean;
	}

	public TShopInfo getShopInfo() {
		return shopInfo;
	}
	public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	
	public GoodsMgr getGoodsMgr() {
		return goodsMgr;
	}

	public void setGoodsMgr(GoodsMgr goodsMgr) {
		this.goodsMgr = goodsMgr;
	}
	
	public List<TShopCategoryGoods> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<TShopCategoryGoods> categoryList) {
		this.categoryList = categoryList;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public IUserMgr getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(IUserMgr userMgr) {
		this.userMgr = userMgr;
	}

	public TShopCategoryGoods getShopCategoryGoods() {
		return shopCategoryGoods;
	}

	public void setShopCategoryGoods(TShopCategoryGoods shopCategoryGoods) {
		this.shopCategoryGoods = shopCategoryGoods;
	}

	public GoodsResponseBean getGoodsResponseBean() {
		return goodsResponseBean;
	}

	public void setGoodsResponseBean(GoodsResponseBean goodsResponseBean) {
		this.goodsResponseBean = goodsResponseBean;
	}

	public String getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}


	public String getFileAccessUrl() {
		return fileAccessUrl;
	}


	public void setFileAccessUrl(String fileAccessUrl) {
		this.fileAccessUrl = fileAccessUrl;
	}
	
	
	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public OrderMgr getOrderMgr() {
		return orderMgr;
	}


	public void setOrderMgr(OrderMgr orderMgr) {
		this.orderMgr = orderMgr;
	}

}