
 /*
 * 文 件 名:  MySessionContext.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-3-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.common.http;

import java.util.HashMap;

import javax.servlet.http.HttpSession;


 /**       
 * 项目名称：CmEnterpriseProject    
 * 类名称：MySessionContext    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-3-24 下午3:17:54    
 * 修改人：linch   
 * 修改时间：2013-3-24 下午3:17:54    
 * 修改备注：    
 * @version       
 */

public class MySessionContext {
	
	private static MySessionContext instance;
	
	private HashMap<Object,HttpSession> mymap;

	private MySessionContext() {
		mymap = new HashMap<Object,HttpSession>();
	}

	public static MySessionContext getInstance() {
		if (instance == null) {
			instance = new MySessionContext();
		}
		return instance;
	}

	public synchronized void AddSession(HttpSession session) {
		if (session != null) {
			mymap.put(session.getId(), session);
		}
	}

	public synchronized void DelSession(HttpSession session) {
		if (session != null) {
			mymap.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) mymap.get(session_id);
	}

}

