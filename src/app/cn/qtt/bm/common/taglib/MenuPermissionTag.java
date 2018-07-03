package app.cn.qtt.bm.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.model.TSysMenuInfo;


public class MenuPermissionTag extends TagSupport{

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = -4072178129571919402L;
	
	private CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());
	
	
	
	 /**
	 * 菜单CODE
	 */
	 
	private String menuCode;
	
	
	 /**
	 * 菜单地址
	 */
	 
	private String menuPath;
	
	
	
	
	/**
	* 方法名称:      isHaspermisson  
	* 方法描述:      验证是否有权限
	* @param userBean
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-2 上午9:03:20
	*/ 
	 
	private boolean isHaspermisson(UserBean userBean){
		
		
		//验证路径
		if(StringUtils.isNotBlank(menuPath)){
			//如果访问路径列表不为空
			if(CollectionUtils.isNotEmpty(userBean.getVisitPaths())){
				//如果列表有则通过
				if(userBean.getVisitPaths().indexOf(menuPath) > -1){
					return true;
				}
			}
			
			if(CollectionUtils.isNotEmpty(userBean.getMenus())){
				
				for(TSysMenuInfo menu : userBean.getMenus()){
					if (menu != null
							&& StringUtils.isNotBlank(menu.getMenuUrl())
							&& menu.getMenuUrl().indexOf(menuPath) > -1) {
						return true;
					}
				}
				
			}
			
		}
		
		//菜单不为空的话
		if(StringUtils.isNotBlank(menuCode)){
			if(CollectionUtils.isNotEmpty(userBean.getMenus())){
				
				for(TSysMenuInfo menu : userBean.getMenus()){
					if (menu != null
							&& menu.getMenuId() != null
							&& StringUtils.equals(menuCode, String.valueOf(menu.getMenuId()))) {
						return true;
					}
				}
				
			}
			
		}
			
		return false;
	}
	
	
	
	public int doStartTag() throws JspException {
		
		if(StringUtils.isBlank(menuCode) && StringUtils.isBlank(menuPath)){
			gLogger.debug(">>>>> security no pass!");
			return SKIP_BODY;
		}
		
		
		HttpServletRequest request= (HttpServletRequest) pageContext.getRequest();
		String path = request.getServletPath();
		String namespace = "";
		if(path.indexOf("/") > -1){
			namespace = path.substring(0, path.lastIndexOf("/"));
		}
		//命名空间
		if(namespace.indexOf("/") > -1){
			namespace = namespace.substring(namespace.lastIndexOf("/")+1);
		}
		
		UserBean userBean = null;
		if(StringUtils.isNotBlank(namespace)){
			
			Object object = null;
			if(namespace.equals(Constants.NAMESPACE_ORDER_NAME)){
				object = pageContext.getSession().getAttribute(Constants.SESSION_ORDER_USER_NAME);
			}
			if(namespace.equals(Constants.NAMESPACE_SYSTEM_NAME)){
				object = pageContext.getSession().getAttribute(Constants.SESSION_SYSTEM_USER_NAME);
			}
			
			if(object != null && object instanceof UserBean){
				userBean = (UserBean)object;
			}
			
		}
		
		//如果session里没有用户信息则跳过
		if (userBean != null
				&& CollectionUtils.isEmpty(userBean.getVisitPaths())
				&& CollectionUtils.isEmpty(userBean.getMenus())) {
			gLogger.debug(">>>>> security no pass! <<<<<");
			return SKIP_BODY;
		}
		
		
		if(isHaspermisson(userBean)){
			//验证查询通过
			gLogger.debug(">>>>> Security pass! <<<<<");
			return EVAL_BODY_INCLUDE;
		}
		
		
		gLogger.debug(">>>>> security no pass! <<<<<");
		return SKIP_BODY;

	}

	public int doEndTag() throws JspException {

		return EVAL_PAGE;
	}

	public String getMenuCode() {
	
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
	
		this.menuCode = menuCode;
	}

	public String getMenuPath() {
	
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
	
		this.menuPath = menuPath;
	}

	
	

}

