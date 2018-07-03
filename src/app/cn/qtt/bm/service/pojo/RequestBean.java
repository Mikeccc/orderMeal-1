package app.cn.qtt.bm.service.pojo;

import java.util.Date;

/**
 * @title 通用服务_请求对象超类
 * @descriptor
 * @author zy
 * @version 2012-5-17
 */
public class RequestBean {
	private Integer currentPage; // 分页
	private Integer pageRecords; // 分页

	private Date fromDate;//开始时间
	private Date endDate;//结束时间
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the pageRecords
	 */
	public Integer getPageRecords() {
		return pageRecords;
	}
	/**
	 * @param pageRecords the pageRecords to set
	 */
	public void setPageRecords(Integer pageRecords) {
		this.pageRecords = pageRecords;
	}
	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
