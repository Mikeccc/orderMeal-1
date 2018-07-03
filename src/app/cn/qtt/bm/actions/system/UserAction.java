package app.cn.qtt.bm.actions.system;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import app.cn.qtt.bm.common.base.action.BaseAction;
import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.IUserMgr;
import app.cn.qtt.bm.manage.impl.OrderMgr;
import app.cn.qtt.bm.common.utils.MD5Encrypt;
import app.cn.qtt.bm.model.MyOrder;
import app.cn.qtt.bm.model.OrderOperateBean;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;
import app.cn.qtt.bm.service.pojo.UserResponseBean;

/**
 * 用户信息维护
 * @author huangwj
 * @version 2013-06-25
 * @since JDK 1.6
 */
@Namespace("/system")
@ParentPackage("system-default")
@Results({
	@Result(name = "curSystemUserView", location = "/WEB-INF/content/system/curSystemUserView.jsp"),
    @Result(name = "modifySystemUserPasswordView", location = "/WEB-INF/content/system/modifySystemUserPasswordView.jsp"),
	@Result(name = "getUserOrder", location = "/WEB-INF/content/system/user-order.jsp")

})
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -2705805170260609209L;

	private IUserMgr userMgr;
	private OrderMgr orderMgr;
	
	private Integer userId;
	private String userName;
	private String userPhoneNumber;
	
	private UserResponseBean responseBean;
	private List<TSysUserInfo> list;
	private TSysUserInfo user;
	
	private Integer[] userIds;
	private String userCode;
	private String createTime;
	
	private String searchDate;
	
	private String oldPassword;
	private String newPassword;
	private OrderResponseBean responseOrderBean;
	private List<MyOrder> myOrderShopList;
	List<List<MyOrder>> MyOrderListList;
	List<MyOrder> shopNameList ;
	
	
	public List<MyOrder> getShopNameList() {
		return shopNameList;
	}

	public void setShopNameList(List<MyOrder> shopNameList) {
		this.shopNameList = shopNameList;
	}

	public List<MyOrder> getMyOrderShopList() {
		return myOrderShopList;
	}

	public void setMyOrderShopList(List<MyOrder> myOrderShopList) {
		this.myOrderShopList = myOrderShopList;
	}

	public List<List<MyOrder>> getMyOrderListList() {
		return MyOrderListList;
	}

	public void setMyOrderListList(List<List<MyOrder>> myOrderListList) {
		MyOrderListList = myOrderListList;
	}

	public String queryUserOrderList(){
		String xFunctionName = "getUserOrder()";
		try{
			gLogger.begin(xFunctionName);
			Set<Integer> shopIdSet = new HashSet<Integer>();
			List shopList = new ArrayList();
			responseOrderBean = userMgr.queryOrdererByUserCode(userCode, createTime);
		
			List<MyOrder> myOrderShopList = new ArrayList<MyOrder>();
			List<MyOrder> myOrderList =(List<MyOrder>) responseOrderBean.getResultList();
			Integer shopId =null;
			for(MyOrder myOrder: myOrderList){
				shopIdSet.add(myOrder.getShopId());
			}
			 MyOrderListList = new ArrayList<List<MyOrder>>();
			for(Integer shopIds : shopIdSet){
				List<MyOrder> myOrderLists = new ArrayList<MyOrder>();
				for(MyOrder myOrder: myOrderList){
					if(shopIds != null && shopIds != 0 &&  myOrder.getShopId() != null &&  shopIds - myOrder.getShopId() == 0){
						myOrderLists.add(myOrder);
					}
				}
				MyOrderListList.add(myOrderLists);
			}
			shopNameList  = new ArrayList<MyOrder>();
			for(Integer shopIds : shopIdSet){
				List<MyOrder> myOrderLists = new ArrayList<MyOrder>();
				for(MyOrder myOrder: myOrderList){
					if(shopIds != null && shopIds != 0 &&  myOrder.getShopId() != null &&  shopIds - myOrder.getShopId() == 0){
						shopNameList.add(myOrder);
						break;
					}
				}
			}
			//this.shopNameList = shopNameList;
		}catch(Exception e){
			gLogger.exception(xFunctionName,e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return "getUserOrder";
	}
	
	/**
	 * 违规订餐用户列表
	 * @Author huangwj
	 * @Create Date: 2013-06-25
	 * @return view
	 */
	public String invalidOrdererList() {

		final String xFunctionName = "UserAction.invalidOrdererList()";
		gLogger.begin(xFunctionName);
		try {
			int page = 1;
			responseBean = userMgr.queryInvalidOrderer(null, page, Constants.DEFAULT_PAGERECORDS);
			super.getRequest().setAttribute("ORDER_COUNT_END_TIME", CacheConstants.ORDER_COUNT_END_TIME());
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			return ERROR;
		} finally {
			gLogger.end(xFunctionName);
		}
		return "invalidOrdererList";
	}

	/**
	 * 违规订餐用户列表 JSON
	 * @Author huangwj
	 * @Create Date: 2013-06-25
	 * @return JSON
	 */
	public String queryInvalidOrdererListJson(){
		final String xFunctionName = "UserAction.queryInvalidOrdererListJson()";
		gLogger.begin(xFunctionName);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int page = Integer.valueOf(currentPage).intValue();
			responseBean = userMgr.queryInvalidOrderer(searchDate, page, Constants.DEFAULT_PAGERECORDS);
			
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
	 * 查询违规人员违规当天的订单
	 * @return JSON
	 * @author tipx
	 * @createtime 2013-8-25 上午2:34:48
	 */
	public String queryInvalidOrder(){
		final String xFunctionName = "UserAction.queryInvalidOrder()";
		gLogger.begin(xFunctionName);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {

			if(StringUtils.isBlank(searchDate)){
				result.put("isSuccess", false);
				result.put("errorMsg", "查询日期为空");
				return null;
			}
			Date invalidDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDate);

			List<String> orderStatusList = new ArrayList<String>();
			orderStatusList.add(Constants.ORDER_RUN_STATUS_SEND);   //订单发送完成
			orderStatusList.add(Constants.ORDER_RUN_STATUS_EXPIRED); //订单已过期
			
			OrderResponseBean responseBean = orderMgr.queryDetailOrderInfoByTime(userCode, invalidDate, orderStatusList);
			if(!"00".equals(responseBean.getResponseCode())){

				result.put("isSuccess", false);
				result.put("errorMsg", "订单信息查询失败");
				return null;
			}
			
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
	 * 导出违规订餐用户报表
	 * @author huangwj
	 * @createtime 2013-8-25 上午12:33:56
	 */
	public String exportInvalidOrderer(){
		final String xFunctionName = "UserAction.exportInvalidOrderer()";
		gLogger.begin(xFunctionName);
		
		try {
			List<OrderOperateBean> list = orderMgr.queryInvalidUserOrderOperateDetail(searchDate);
			//若没有任何订餐数据，则初始化，导出空内容
			if(null == list){
				list = new ArrayList<OrderOperateBean>();
			}			
			HttpServletResponse response = super.getResponse();
			
			String fileName = searchDate+"违规人员名单.xls";
			
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			exportExcel(list, response.getOutputStream());
			
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			return ERROR;
		} finally {
			gLogger.end(xFunctionName);
		}
		return null;
	}
	
	/**
	 * 导出Excel
	 * @param list 违规订餐人员信息
	 * @param os 输出流
	 * @throws Exception Excel操作失败
	 * @author tipx 
	 * @createtime 2013-8-31 上午10:35:55
	 */
	private void exportExcel(List<OrderOperateBean> list, OutputStream os) throws Exception{
		final String xFunctionName = "exportExcel";
		gLogger.begin(xFunctionName);
		
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			
			//设置默认列宽
			sheet.setDefaultColumnWidth(18);
			
			//标题行
			HSSFRow row = sheet.createRow(0);
			
			//创建标题
			HSSFCell cell = row.createCell(0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("店铺名称");
			
			cell = row.createCell(1);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("商品名称");
			
			cell = row.createCell(2);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("订购数量");
			
			cell = row.createCell(3);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("订餐人");
			
			cell = row.createCell(4);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("订餐人号码");
			
			cell = row.createCell(5);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("所属部门/公司");
			
			cell = row.createCell(6);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("订餐时间");
			
			cell = row.createCell(7);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("订餐状态");
			
			cell = row.createCell(8);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("二维码扫描次数");
			
			if(CollectionUtils.isNotEmpty(list)){
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				
				int i = 1;
				for (OrderOperateBean bean : list) {
					//数据行
					row = sheet.createRow(i++);

					//填充数据
					cell = row.createCell(0);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getShopName());
					
					cell = row.createCell(1);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getShopGoodsName());
					
					cell = row.createCell(2);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getShopGoodsCount());
					
					cell = row.createCell(3);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getUserName());
					
					cell = row.createCell(4);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getUserPhoneNumber());
					
					cell = row.createCell(5);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getDepartment());
					
					cell = row.createCell(6);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(formater.format(bean.getCreateTime()));
					
					cell = row.createCell(7);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getPaidStatus());
					
					cell = row.createCell(8);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(bean.getOperateTimes());
				}
			}

			workbook.write(os);
			os.flush();
			//操作结束，关闭文件　　
			os.close();
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			throw e;
		} finally {
			gLogger.end(xFunctionName);
		}
	}
	
	
	/**
	 * 有效订餐用户列表
	 * @Author huangwj
	 * @Create Date: 2013-06-25
	 * @return view
	 */
	public String validOrdererList() {

		final String xFunctionName = "UserAction.validOrdererList()";
		gLogger.begin(xFunctionName);
		try {
			int page = 1;
			responseBean = userMgr.queryValidOrderer(null, page, Constants.DEFAULT_PAGERECORDS);
		} catch (Exception e) {
			gLogger.exception(xFunctionName, e);
			return ERROR;
		} finally {
			gLogger.end(xFunctionName);
		}
		return "validOrdererList";
	}

	/**
	 * 有效订餐用户列表 JSON
	 * @Author huangwj
	 * @Create Date: 2013-06-25
	 * @return JSON
	 */
	public String queryValidOrdererListJson(){
		final String xFunctionName = "UserAction.queryValidOrdererListJson()";
		gLogger.begin(xFunctionName);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int page = Integer.valueOf(currentPage).intValue();
			responseBean = userMgr.queryValidOrderer(user, page, Constants.DEFAULT_PAGERECORDS);
			
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

	//批量添加订餐用户
	public String addOrderers() {
		final String xFunctionName = "UserAction.addOrderers()";
		gLogger.begin(xFunctionName);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			
			if(null == list || list.size() < 1){
				result.put("isSuccess", false);
				result.put("errorMsg", "用户信息未提交！");
				return null; //在finally处print
			}

			UserBean ub = super.getSystemUserInfoFromSession();
			if(null == ub || null == ub.getUserInfo()){
				result.put("isSuccess", false);
				result.put("nopass", 1001);
				result.put("errorMsg", "用户未登录或session已过期，请重新登录！");
				return null;
			}
			
			String userCode = ub.getUserInfo().getUserCode();
			
			Date now = new Date();
			
			StringBuilder sb = new StringBuilder();
			int successCount = 0,
				failCount = 0;
			for (TSysUserInfo user : list) {
				user.setCreateUserCode(userCode);
				user.setModifyUserCode(userCode);
				user.setCreateTime(now);
				user.setModifyTime(now);
				
				responseBean = userMgr.addOrderer(user);
				if(!Constants.INSERT_SUCCESS_CODE.equals(responseBean.getResponseCode())){
					//失败时，记录该信息
					sb.append(responseBean.getErrMsg()).append("<br/>");
					failCount++;
				}else{
					successCount++;
				}
			}
			if(sb.length() > 0){
				result.put("isSuccess", false);
				result.put("errorMsg", sb.toString());
				result.put("successCount", successCount);
				result.put("failCount", failCount);
				return null;
			}
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

	//修改订餐用户
	public String updateOrderer() {
		final String xFunctionName = "UserAction.updateOrderer()";
		gLogger.begin(xFunctionName);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			UserBean ub = super.getSystemUserInfoFromSession();
			if(null == ub || null == ub.getUserInfo()){
				result.put("isSuccess", false);
				result.put("nopass", 1001);
				result.put("errorMsg", "用户未登录或session已过期，请重新登录！");
				return null;
			}
			
			String userCode = ub.getUserCode();
			
			if(null == user || StringUtils.isBlank(user.getUserCode()) || null == user.getUserId()){
				result.put("isSuccess", false);
				result.put("errorMsg", "用户信息未提交！");
				return null;//在finally处print
			}
			
			if(isInBmTime()){
				result.put("isSuccess", false);
				result.put("errorMsg", "订餐时段禁止维护用户信息！");
				return null;
			}

			Date now = new Date();
			
			user.setModifyUserCode(userCode);
			user.setModifyTime(now);

			responseBean = userMgr.updateOrderer(user);
			if(Constants.UPDATE_SUCCESS_CODE.equals(responseBean.getResponseCode())){
				result.put("isSuccess", true);
			}else{
				result.put("isSuccess", false);
				result.put("errorCode", responseBean.getResponseCode());
				result.put("errorMsg", responseBean.getErrMsg());
			}
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

	//删除订餐用户
	public String deleteOrderer() {
		final String xFunctionName = "UserAction.updateOrderer()";
		gLogger.begin(xFunctionName);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			UserBean ub = super.getSystemUserInfoFromSession();
			if(null == ub || null == ub.getUserInfo()){
				result.put("isSuccess", false);
				result.put("nopass", 1001);
				result.put("errorMsg", "用户未登录或session已过期，请重新登录！");
				return null;
			}
			
			if(null == userIds || userIds.length < 1){
				result.put("isSuccess", false);
				result.put("errorMsg", "用户信息未提交！");
				return null;//在finally处print
			}
			
			if(isInBmTime()){
				result.put("isSuccess", false);
				result.put("errorMsg", "订餐时段禁止维护用户信息！");
				return null;
			}

			StringBuilder msgs = new StringBuilder();
			for (Integer id: userIds) {
				responseBean = userMgr.deleteOrderer(id);
				if(!Constants.DELETE_SUCCESS_CODE.equals(responseBean.getResponseCode())){

					//失败时，记录该信息
					msgs.append(responseBean.getErrMsg()).append("<br/>");
				}
			}

			if(msgs.length() < 1){
				result.put("isSuccess", true);
			}else{
				result.put("isSuccess", false);
				result.put("errorMsg", msgs.toString());
			}
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
	 * 
	 * 获取管理员用户信息
	 * @return
	 * @author wangwenwei
	 * @createtime 2013-8-25 上午12:03:48
	 */
	public String getCurentSystemUser(){
		final String xFunctionName = "UserAction.getCurentSystemUser()";
		gLogger.begin(xFunctionName);
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			user = userbean.getUserInfo();
			
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return "curSystemUserView";
	}
	/**
	 * 
	* @Title: getUpdatePasswordView
	* @Description: 进入修改当前管理员账号密码界面
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getModifySystemUserPasswordView(){
		final String xFunctionName  = "getModifySystemUserPasswordView()";
		gLogger.begin(xFunctionName);
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
		}catch(Exception e){
			gLogger.exception(xFunctionName, e);
		}finally{
			gLogger.end(xFunctionName);
		}
		return "modifySystemUserPasswordView";
	}
	/**
	 * 
	 *  修改管理员密码
	 * @return
	 * @author wangwenwei
	 * @createtime 2013-8-25 上午12:09:52
	 */
	public String modifySystemUserPassword(){
		final String xFunctionName  = "modifySystemUserPassword()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			if(userbean!=null){
				user=userbean.getUserInfo();
			}
			if(StringUtils.isNotBlank(oldPassword)){
				if(!MD5Encrypt.MD5Encode(oldPassword).equals(user.getUserPassword())){//原始密码错误
					result.put("isSuccess",false);
				}else{
					user.setUserPassword(MD5Encrypt.MD5Encode(newPassword));
					user.setModifyTime(new Date());
					boolean flag = userMgr.updateUserPassword(user);
					if(flag==true){
						result.put("isSuccess",true);
						result.put("user",user);
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

	
	//---------------------getter/setter-----------------------------
	public IUserMgr getUserMgr() {
		return userMgr;
	}

	public void setUserMgr(IUserMgr userMgr) {
		this.userMgr = userMgr;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}


	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public UserResponseBean getResponseBean() {
		return responseBean;
	}

	public void setResponseBean(UserResponseBean responseBean) {
		this.responseBean = responseBean;
	}

	public List<TSysUserInfo> getList() {
		return list;
	}

	public void setList(List<TSysUserInfo> list) {
		this.list = list;
	}

	public TSysUserInfo getUser() {
		return user;
	}

	public void setUser(TSysUserInfo user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer[] getUserIds() {
		return userIds;
	}

	public void setUserIds(Integer[] userIds) {
		this.userIds = userIds;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public OrderMgr getOrderMgr() {
	
		return orderMgr;
	}

	public void setOrderMgr(OrderMgr orderMgr) {
	
		this.orderMgr = orderMgr;
	}

	public static long getSerialversionuid() {
	
		return serialVersionUID;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public OrderResponseBean getResponseOrderBean() {
		return responseOrderBean;
	}

	public void setResponseOrderBean(OrderResponseBean responseOrderBean) {
		this.responseOrderBean = responseOrderBean;
	}

}