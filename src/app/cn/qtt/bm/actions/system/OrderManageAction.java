package app.cn.qtt.bm.actions.system;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.manage.IGoodsMgr;
import app.cn.qtt.bm.manage.IOrderMgr;
import app.cn.qtt.bm.model.OrderOperateBean;
import app.cn.qtt.bm.model.ShopOrderdailyStatistics;
import app.cn.qtt.bm.model.TShopCategoryGoods;
import app.cn.qtt.bm.model.TShopInfo;
import app.cn.qtt.bm.model.TSysUserInfo;
import app.cn.qtt.bm.service.pojo.GoodsResponseBean;
import app.cn.qtt.bm.service.pojo.OrderResponseBean;



@Namespace("/system")
@ParentPackage("system-default")

@Results({
    @Result(name = "order-count", location = "/WEB-INF/content/system/order-count.jsp"),
    @Result(name = "order-countByShopId", location = "/WEB-INF/content/system/order-countByShopId.jsp")
})
public class OrderManageAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5759664327232321982L;
	
	
	private IOrderMgr orderMgr;
	
	private IGoodsMgr goodsMgr;
	
	private TShopCategoryGoods shopCategoryGoods;
	
	private List<TShopCategoryGoods> categoryList;
	
	private OrderResponseBean orderResponseBean;
	
	private String categoryIdArr;
	
	private String requestShopId;
	
	/**
	 * 订单查询日期
	 */
	private String searchDate;
	
	/**
	 * 
	* @Title: orederCount
	* @Description:按分组统计当天的订单
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String orederCount(){
		final String xFunctionName  = "orederCount()";
		gLogger.begin(xFunctionName);
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
			orderResponseBean = orderMgr.queryDailyOrderInfo(shopCategoryGoods,sysUserInfo);
			countPriseAndNum(orderResponseBean);
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
		return "order-count";
	
	}
	/**
	 * 
	* @Title: orederCountAjax
	* @Description: ajax按分组统计当天的订单
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String orederCountAjax(){
		final String xFunctionName  = "orederCountAjax()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			TSysUserInfo sysUserInfo =null;
			if(userbean!=null){
				sysUserInfo=userbean.getUserInfo();
			}
			
			
			//查默认分组下及每天都提供的菜单
			shopCategoryGoods = new TShopCategoryGoods();
			if(StringUtils.isNotBlank(categoryIdArr)){
				shopCategoryGoods.setCategoryId(Integer.valueOf(categoryIdArr));
			}
			if(categoryList!=null&&categoryList.size()>0){
				shopCategoryGoods = categoryList.get(0);
			}
			orderResponseBean = orderMgr.queryDailyOrderInfo(shopCategoryGoods,sysUserInfo);
			countPriseAndNum(orderResponseBean);
			
			result.put("isSuccess",true);
			result.put("orderResponseBean",orderResponseBean);
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		
		return null;
	
	}
	
	
	/**
	 * 
	* @Title: orederCount
	* @Description:按分组统计当天的订单
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String orderCountByShopId(){
		final String xFunctionName  = "orederCountByShopId()";
		gLogger.begin(xFunctionName);
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			if(StringUtils.isNotBlank(requestShopId)){
				TSysUserInfo sysUserInfo = new TSysUserInfo();
				sysUserInfo.setUserShopId(Integer.valueOf(requestShopId));
				TShopInfo shopInfo = new TShopInfo();
				shopInfo.setShopId(Integer.valueOf(requestShopId));
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
				orderResponseBean = orderMgr.queryDailyOrderInfo(shopCategoryGoods,sysUserInfo);
				countPriseAndNum(orderResponseBean);
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			gLogger.end(xFunctionName);
		}
		
		return "order-countByShopId";
	
	}
	/**
	 * 
	* @Title: orederCountAjax
	* @Description: ajax按分组统计当天的订单
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String orderCountByShopIdAjax(){
		final String xFunctionName  = "orederCountAjax()";
		gLogger.begin(xFunctionName);
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			UserBean userbean = super.getSystemUserInfoFromSession();
			
			if(StringUtils.isNotBlank(requestShopId)){
				TSysUserInfo sysUserInfo = new TSysUserInfo();
				sysUserInfo.setUserShopId(Integer.valueOf(requestShopId));
				//查默认分组下及每天都提供的菜单
				shopCategoryGoods = new TShopCategoryGoods();
				if(StringUtils.isNotBlank(categoryIdArr)){
					shopCategoryGoods.setCategoryId(Integer.valueOf(categoryIdArr));
				}
				if(categoryList!=null&&categoryList.size()>0){
					shopCategoryGoods = categoryList.get(0);
				}
				orderResponseBean = orderMgr.queryDailyOrderInfo(shopCategoryGoods,sysUserInfo);
				countPriseAndNum(orderResponseBean);
				
				result.put("isSuccess",true);
				result.put("orderResponseBean",orderResponseBean);
			}
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			gLogger.end(xFunctionName);
			super.print(result);
		}
		
		return null;
	
	}
	
	
	
	/**
	 * 
	* @Title: countPriseAndNum
	* @Description:对每日的数量和价格作统计
	* @param @param orderResponseBean    设定文件
	* @return void    返回类型
	* @throws
	 */
	@SuppressWarnings("unchecked")
	private void countPriseAndNum(OrderResponseBean orderResponseBean){
		if(orderResponseBean!=null&&orderResponseBean.getResultList()!=null&&orderResponseBean.getResultList().size()>0){
			int priceCountDaily=0;//每日订单价格统计（按分组）
			int numberCountDaily=0;//每日订单总量统计（按分组）
			int noGetNumCountDaily=0;//每日订单未领取数量统计（按分组）
			List<ShopOrderdailyStatistics> list = (List<ShopOrderdailyStatistics>) orderResponseBean.getResultList();
			for(ShopOrderdailyStatistics shopOrderdailyStatistics:list){
				if(shopOrderdailyStatistics!=null){
					priceCountDaily = priceCountDaily+(shopOrderdailyStatistics.getShopGoodsPrice())*(shopOrderdailyStatistics.getShopGoodsCount());
					numberCountDaily = numberCountDaily+shopOrderdailyStatistics.getShopGoodsCount();
					noGetNumCountDaily =noGetNumCountDaily+shopOrderdailyStatistics.getOrderRunStatusCount();
				}
			}
			orderResponseBean.setPriceCountDaily(priceCountDaily);
			orderResponseBean.setNumberCountDaily(numberCountDaily);
			orderResponseBean.setNoGetNumCountDaily(noGetNumCountDaily);
			
		}
	}

	/**
	 * 导出当天订餐明细
	 * @return excel
	 * @author tipx
	 * @createtime 2013-8-31 上午10:33:02
	 */
	public String exportOrderOperateDetail(){
		final String xFunctionName = "exportOrderOperateDetil";
		gLogger.begin(xFunctionName);
		
		try {
			//只导当天的订餐明细
			searchDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
//			if(StringUtils.isBlank(searchDate)){
//				gLogger.debug("查询日期为空");
//				return ERROR;
//			}
			
			List<OrderOperateBean> list = orderMgr.queryOrderOperateDetail(searchDate);
			//若没有任何订餐数据，则初始化，导出空内容
			if(null == list){
				list = new ArrayList<OrderOperateBean>();
			}
			
			HttpServletResponse response = super.getResponse();
			
			String fileName = searchDate+"订餐明细.xls";
			
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
	 * 导出当天订餐明细
	 * @return excel
	 * @author tipx
	 * @createtime 2013-8-31 上午10:33:02
	 */
	public String exportInvalidUserOrderOperateDetail(){
		final String xFunctionName = "exportInvalidUserOrderOperateDetail";
		gLogger.begin(xFunctionName);
		
		try {
			//只导当天的订餐明细
			searchDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
//			if(StringUtils.isBlank(searchDate)){
//				gLogger.debug("查询日期为空");
//				return ERROR;
//			}
			
			List<OrderOperateBean> list = orderMgr.queryInvalidUserOrderOperateDetail(searchDate);
			//若没有任何订餐数据，则初始化，导出空内容
			if(null == list){
				list = new ArrayList<OrderOperateBean>();
			}
			
			HttpServletResponse response = super.getResponse();
			
			String fileName = searchDate+"订餐明细.xls";
			
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
	
	//===========================================private============================================
	/**
	 * 导出Excel
	 * @param list 订餐明细信息
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
	
	//==============================================getter/setter=================================================
	public IOrderMgr getOrderMgr() {
		return orderMgr;
	}

	public void setOrderMgr(IOrderMgr orderMgr) {
		this.orderMgr = orderMgr;
	}

	public IGoodsMgr getGoodsMgr() {
		return goodsMgr;
	}

	public void setGoodsMgr(IGoodsMgr goodsMgr) {
		this.goodsMgr = goodsMgr;
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

	public OrderResponseBean getOrderResponseBean() {
		return orderResponseBean;
	}

	public void setOrderResponseBean(OrderResponseBean orderResponseBean) {
		this.orderResponseBean = orderResponseBean;
	}

	public String getCategoryIdArr() {
		return categoryIdArr;
	}

	public void setCategoryIdArr(String categoryIdArr) {
		this.categoryIdArr = categoryIdArr;
	}
	public String getRequestShopId() {
		return requestShopId;
	}
	public void setRequestShopId(String requestShopId) {
		this.requestShopId = requestShopId;
	}
	public String getSearchDate() {
	
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
	
		this.searchDate = searchDate;
	}
	public static long getSerialversionuid() {
	
		return serialVersionUID;
	}
}
