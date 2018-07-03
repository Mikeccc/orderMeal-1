
 /*
 * 文 件 名:  BSRequestBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-6-5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.common.pojo.request;


 /**       
 * 项目名称：CmBespeakMeal    
 * 类名称：BSRequestBean    
 * 类描述：    请求对象父类
 * 创建人：linch  
 * 创建时间：2013-6-5 下午5:33:39    
 * @version       
 */

public class RequestBean {
	/**
	 * 当前页码
	 */
	private int currentPage=0;
	private int pageRecords=0;
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the pageRecords
	 */
	public int getPageRecords() {
		return pageRecords;
	}
	/**
	 * @param pageRecords the pageRecords to set
	 */
	public void setPageRecords(int pageRecords) {
		this.pageRecords = pageRecords;
	}
	
}

