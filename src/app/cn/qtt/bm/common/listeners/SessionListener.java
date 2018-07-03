
 /*
 * 文 件 名:  SessionListener.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-3-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.common.listeners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import app.cn.qtt.bm.common.http.MySessionContext;



 /**       
 * 项目名称：CmEnterpriseProject    
 * 类名称：SessionListener    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-3-24 下午3:16:20    
 * 修改人：linch   
 * 修改时间：2013-3-24 下午3:16:20    
 * 修改备注：    
 * @version       
 */

public class SessionListener implements HttpSessionListener{
	
	private MySessionContext myc = MySessionContext.getInstance();

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		myc.AddSession(httpSessionEvent.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		myc.DelSession(session);
	}


}

