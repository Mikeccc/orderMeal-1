package app.cn.qtt.bm.service.pojo;

import java.util.List;

import app.cn.qtt.bm.model.TSysFile;


/**
 * @title 通用服务_请求结果（响应）对象
 * 
 * @descriptor
 * @author jjf
 * @version 2013-3-27
 */
public class ResponseBean {
	/**
	 * 服务处理结果代码 00 为正确
	 */
	private String responseCode = "00";
	private Exception exception; // 异常
	private String errMsg; // 错误信息_自定义
	private String errMsgDetail; // 错误信息_自定义
	/**
	 * 总页数
	 */
	private int totalPages;
	private int currentPage;


	/**
	 * 总记录数
	 */
	private int totalRecords;
	/**
	 * 存放DAO层返回的结果集
	 */
	private List<?> resultList;
	
	private TSysFile sysFile;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getErrMsgDetail() {
		return errMsgDetail;
	}

	public void setErrMsgDetail(String errMsgDetail) {
		this.errMsgDetail = errMsgDetail;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the resultList
	 */
	public List<?> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList the resultList to set
	 */
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public TSysFile getSysFile() {
		return sysFile;
	}

	public void setSysFile(TSysFile sysFile) {
		this.sysFile = sysFile;
	}
	
}
