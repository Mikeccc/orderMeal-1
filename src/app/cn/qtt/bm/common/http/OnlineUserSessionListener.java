package app.cn.qtt.bm.common.http;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <pre>
 * ============================================================
 * 功能说明：
 * 非正常退出，比如客户端系统崩溃或突然死机，
 * 可以采用隔一段时间session没活动就删除该session所对应的用户来解决，
 * 这样用户需要等待一段时间之后就可以正常登录。
 * ============================================================
 * </pre>
 * @author hfx
 *
 */
//implements HttpSessionBindingListener
public class OnlineUserSessionListener {

	
	/* 
	 * 
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
		
		
	}

	/* 
	 * 
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {

		
	}

	

}
