
 /*
 * 文 件 名:  RequestPermissionInterceptor.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2013-4-3
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.common.intercepors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.http.MySessionContext;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.model.TSysMenuInfo;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


 /**       
 * 项目名称：CmEnterpriseProject    
 * 类名称：RequestPermissionInterceptor    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2013-4-3 下午10:31:48    
 * 修改人：linch   
 * 修改时间：2013-4-3 下午10:31:48    
 * 修改备注：    
 * @version       
 */

public class RequestPermissionInterceptor extends AbstractInterceptor {

	 
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = -6881439231682470593L;
	
	
	private CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());
	
	/**
	 * 不需要验证的
	 */
	 
	private String noVerifyRequest;
	
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		gLogger.debug(">>>>> begin Request Permission <<<<<");
		
		try {
			noVerifyRequest = CacheConstants.NO_VERIFY_REQUEST_PERMISSION();
			
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//请求路径
			String path = request.getServletPath();
			String namespace = "";
			if(path.indexOf("/") > -1){
				namespace = path.substring(0, path.lastIndexOf("/"));
			}
			//命名空间
			if(namespace.indexOf("/") > -1){
				namespace = namespace.substring(namespace.lastIndexOf("/")+1);
			}
			
			path = path.substring(path.lastIndexOf("/") + 1 );
			
			//判断是否需要验证
			if(!isNotVerify(path)){
			
				String sessionId = (String) request.getParameter("sessionId");
				
				HttpSession session = null;
				if(StringUtils.isNotBlank(sessionId)){
					session = MySessionContext.getInstance().getSession(sessionId);
				}
				else{
					session = request.getSession();
				}
				
				HttpServletResponse response = ServletActionContext.getResponse();
				
				
				UserBean userBean = null;
				if(StringUtils.isNotBlank(namespace)){
					//根据不同的用户空间获取不同的用户信息
					Object object = null;
					if(namespace.equals(Constants.NAMESPACE_ORDER_NAME)){
						object = session.getAttribute(Constants.SESSION_ORDER_USER_NAME);
					}
					if(namespace.equals(Constants.NAMESPACE_SYSTEM_NAME)){
						object = session.getAttribute(Constants.SESSION_SYSTEM_USER_NAME);
					}
					
					if(object != null && object instanceof UserBean){
						userBean = (UserBean)object;
					}
					
				}
				
				
				boolean isPass = false;
				int noPassKey = 0;
				if(userBean == null){
					noPassKey = 1001;
				}
			
			
				if(userBean != null){
					boolean result = isPassRequestPermission(path,userBean);
					if(!result){
						noPassKey = 1002;
					}
					else{
						isPass = true;
					}
				}
				
				
				if(!isPass){
					gLogger.debug(">>>>> request permission no pass! <<<<<");
					gLogger.debug(">>>>> noPassKey : "+noPassKey+" <<<<<");
					
					//判断是那种请求
					if (request.getHeader("X-Requested-With") != null
							&& request.getHeader("X-Requested-With").equals(
									"XMLHttpRequest")) {
						PrintWriter pw = response.getWriter();
						response.setCharacterEncoding("text/html; charset=utf-8");
						response.setContentType("text/html; charset=utf-8");
						pw.write("{\"nopass\":" + ExceptionConstants.SYSTEM_COMPETENCE_NO_PASS_CODE + "}");
						return null;
					} else {
						switch (noPassKey)
						{
						case 1001:
							return "login";
						case 1002:
							return "competence";
						default:
							break;
						}
					}
					
				}
			}
			
			
		} catch (Exception e) {
			gLogger.error(e.getMessage());
		}
		finally{
			gLogger.debug(">>>>> end Request Permission <<<<<");
		}
		
		return invocation.invoke();
	}
	
	
	/**
	* 方法名称:      isPassPath  
	* 方法描述:      过滤不需要过滤的结尾
	* @param path
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-3 下午10:00:48
	*/ 
	 
	private boolean isNotVerifyEndPath(String path){
		if (path.indexOf(".png") != -1)
			return true;
		else if (path.indexOf(".gif") != -1)
			return true;
		else if (path.indexOf(".jpg") != -1)
			return true;
		else if (path.indexOf(".js") != -1)
			return true;
		else if (path.indexOf(".css") != -1)
			return true;
		else
			return false;
	}
	
	
	private boolean isNotVerify(String path){
		if(StringUtils.isNotBlank(noVerifyRequest)){
			String[] requests = null;
			if(noVerifyRequest.indexOf(";") > -1){
				requests = noVerifyRequest.split(";");
			}
			if(noVerifyRequest.indexOf("|") > -1){
				requests = noVerifyRequest.split("|");
			}
			if(noVerifyRequest.indexOf(",") > -1){
				requests = noVerifyRequest.split(",");
			}
			
			if(requests != null && requests.length > 0){
				for(String request :requests){
					if(path.endsWith(request)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	private boolean isPassRequestPermission(String path,UserBean userBean){
		
		//不需要的验证的结尾
		if(isNotVerifyEndPath(path)){
			return true;
		}
		
		//如果访问路径列表不为空
		if(CollectionUtils.isNotEmpty(userBean.getVisitPaths())){
			//如果列表有则通过
			if(userBean.getVisitPaths().indexOf(path) > -1){
				return true;
			}
		}
		
		if(CollectionUtils.isNotEmpty(userBean.getMenus())){
			
			for(TSysMenuInfo menu : userBean.getMenus()){
				if (menu != null
						&& StringUtils.isNotBlank(menu.getMenuUrl())
						&& path.equals(menu.getMenuUrl())) {
					return true;
				}
			}
			
		}
		
		if(CollectionUtils.isNotEmpty(userBean.getVisitPaths())){
			for(String visitPath : userBean.getVisitPaths()){
				if (StringUtils.isNotBlank(visitPath)
						&& path.equals(visitPath)) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	

}

