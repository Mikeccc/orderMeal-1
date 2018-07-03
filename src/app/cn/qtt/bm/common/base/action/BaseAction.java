package app.cn.qtt.bm.common.base.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.pojo.request.IPageRequestBean;
import app.cn.qtt.bm.common.utils.DateJsonValueProcessor;
import app.cn.qtt.bm.model.TLogUserLogin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 

 /**       
  * 项目名称：CmEnterpriseProject    
  * 类名称：BaseAction    
  * 类描述：    
  * 创建人：linch  
  * 创建时间：2013-3-24 下午2:34:07    
  * 修改人：linch   
  * 修改时间：2013-3-24 下午2:34:07    
  * 修改备注：    
  * @version       
  */  
 
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	
	
	protected CCrppLog4j gLogger = new CCrppLog4j(this.getClass().getName());
	
	
	protected static final String SYSTEM_ERROR_NAME = "系统异常";
	
	protected String id;
	
	protected String[] ids;
	

	/**
	 * 当前页码
	 */
	protected String currentPage = "1";
	/**
	 * 每页条数
	 */
	protected String pageRecorders = "10";

	/**
	 * 总页数
	 */

	protected String totalPages = "0";

	
	
	protected Integer totalResults=0;
	
	 /**
	 * 错误信息
	 */
		 
	protected String errorMessage;
	
	/**
	 * 登陆的身份状态标示，1为管理员，2为供应商
	 */
	private String identifier;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}


	public Integer getTotalResults() {
		return totalResults;
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public HttpSession getSession() {
		return getRequest().getSession();
	}



	public void setTotalResults(Integer totalResults) {
	
		this.totalResults = totalResults;
	}




	/**
	 * 方法名称: print 方法描述: list json
	 * 
	 * @param list
	 * @Author: linch
	 * @Create Date: 2012-7-3 下午2:23:06
	 */

	public void print(List<Object> list) {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());
		this.print(JSONArray.fromObject(list.toArray(), cfg).toString());
	}

	/**
	 * 方法名称: print 方法描述: map json
	 * 
	 * @param map
	 * @Author: linch
	 * @Create Date: 2012-7-3 下午2:22:58
	 */

	public void print(Map<Object, Object> map) {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());
		this.print(JSONObject.fromObject(map, cfg).toString());
	}
	
	
	
	
	/**
	* 方法名称:      fillAjaxError  
	* 方法描述:      填充错误信息
	* @param relust
	* @param errorMessage        
	* @Author:      linch
	* @Create Date: 2013-4-4 下午9:52:59
	*/ 
	 
	public void fillAjaxError(Map<Object,Object> result,String errorMessage){
		result.put("isSuccess", false);
		if(StringUtils.isNotBlank(errorMessage)){
			result.put("errorMessage", errorMessage);
		}
	}

	
	
	/**
	* 方法名称:      fillAjaxSuccess  
	* 方法描述:      返回成功
	* @param relust
	* @Author:      linch
	* @Create Date: 2013-4-5 上午11:43:21
	*/ 
	 
	public void fillAjaxSuccess(Map<Object,Object> result){
		result.put("isSuccess", true);
	}
	
	/**
	 * 方法名称: print 方法描述: bean json
	 * 
	 * @param bean
	 * @Author: linch
	 * @Create Date: 2012-7-3 下午2:22:44
	 */

	public void print(Object bean) {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());
		this.print(JSONObject.fromObject(bean, cfg).toString());
	}

	/**
	 * 方法名称: print 方法描述: json
	 * 
	 * @param json
	 * @Author: linch
	 * @Create Date: 2012-7-3 下午2:22:34
	 */

	public void print(String json) {
		try {
			this.getResponse().setContentType(
					"text/html; charset=UTF-8");
			this.getResponse().setHeader("Cache-Control", "no-cache");
			this.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getUserIp(){
		String ip = "";
		try {
			ip = getRequest().getRemoteAddr();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ip;
	}
	
	
	

	
	
	/**
	 * 方法名称: newPageBean 方法描述: 组装分页信息
	 * 
	 * @return
	 * @Author: linch
	 * @Create Date: 2012-5-17 下午3:20:10
	 */

	protected IPageRequestBean newPageBean(IPageRequestBean page) {
		Pattern p = Pattern.compile("^[0-9]*$");
		if (StringUtils.isNotBlank(this.currentPage)
				&& p.matcher(this.currentPage).find()) {
			// 如果输入的页面比总页面大。取最大页面
			if (StringUtils.isNotBlank(this.totalPages)
					&& p.matcher(this.totalPages).find()
					&& !"0".equals(this.totalPages)) {
				if (Integer.valueOf(this.currentPage) > Integer
						.valueOf(this.totalPages))
					this.currentPage = this.totalPages;
			}
			page.setCurrentPage(Integer.valueOf(this.currentPage));

		}
		if (StringUtils.isNotBlank(this.pageRecorders)
				&& p.matcher(this.pageRecorders).find()) {
			page.setPageRecorders(Integer.valueOf(this.pageRecorders));
		}

		return page;
	}
	
	

	/**
	* 方法名称:      getUserInfoFromSession  
	* 方法描述:      从session中获取system用户信息
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-2 上午10:21:14
	*/ 
	 
	protected UserBean getSystemUserInfoFromSession(){
		
		Object object = getSession().getAttribute(Constants.SESSION_SYSTEM_USER_NAME);
		UserBean userBean = null;
		if(object != null && object instanceof UserBean){
			userBean = (UserBean)object;
		}
		
		return userBean;
	}
	
	
	
	
	/**
	* 方法名称:      getOrderUserInfoFromSession  
	* 方法描述:      获取biz用户信息
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-7 下午5:52:20
	*/ 
	 
	protected UserBean getOrderUserInfoFromSession(){
		
		Object object = getSession().getAttribute(Constants.SESSION_ORDER_USER_NAME);
		UserBean userBean = null;
		if(object != null && object instanceof UserBean){
			userBean = (UserBean)object;
		}
		
		return userBean;
	}
	
	
	
	
	/**
	 * 通过ActionContext上下文获取HttpServletRequest对象
	 * @return request
	 */
	protected HttpServletRequest getContextRequest() {
		gLogger.debug("getContextRequest() start");
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)ctx.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		gLogger.debug("getContextRequest() end");
		return request;
	}
	

	/**
	* 方法名称:      getUserLoginLogFromSession  
	* 方法描述:      获取system用户日志
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-7 下午4:15:37
	*/ 
	 
	protected TLogUserLogin getSystemUserLoginLogFromSession(){
		
		Object object = getSession().getAttribute(Constants.SESSION_SYSTEM_USER_LOGIN_LOG_NAME);
		TLogUserLogin log = null;
		if(object != null && object instanceof TLogUserLogin){
			log = (TLogUserLogin)object;
		}
		
		return log;
	}
	
	
	
	/**
	* 方法名称:      getOrderUserLoginLogFromSession  
	* 方法描述:      获取biz用户日志
	* @return        
	* @Author:      linch
	* @Create Date: 2013-4-7 下午5:54:58
	*/ 
	 
	protected TLogUserLogin getOrderUserLoginLogFromSession(){
		
		Object object = getSession().getAttribute(Constants.SESSION_ORDER_USER_NAME);
		TLogUserLogin log = null;
		if(object != null && object instanceof TLogUserLogin){
			log = (TLogUserLogin)object;
		}
		
		return log;
	}

	/**
	 * 设置cookie，缺省有效期1年、并自动设置secure为true
	 * @param key 名称
	 * @param value 值
	 * @throws Exception
	 * @author Mr.Tipx
	 * @createtime 2013-12-12 下午2:07:12
	 */
	protected void addCookie(String key, String value) throws Exception{
		int maxAge = 60 * 60 * 24 * 30 * 12; //12个月
		addCookie(key, value, maxAge, true);
	}
	
	/**
	 * 设置cookie
	 * @param key 名称
	 * @param value 值
	 * @param maxAge 有效期
	 * @param secure 安全性
	 * @throws Exception
	 * @author Mr.Tipx
	 * @createtime 2013-12-12 下午2:07:12
	 */
	protected void addCookie(String key, String value, int maxAge, boolean secure) throws Exception{
		//登录成功后，根据勾选的状态，记住cookie
        Cookie cookie = new Cookie(key, URLEncoder.encode(value,"utf-8"));
        cookie.setMaxAge(maxAge); //有效期限
        //安全设置，不能设置，设置为true，将只有https才能使用
        //考虑httpOnly，需要jdk6+环境
        //cookie.setSecure(secure); 
        
        getResponse().addCookie(cookie);
	}
	
	/**
	 * 根据键名获取相应的cookie值
	 * @param key cookie名称
	 * @return value
	 * @throws Exception
	 * @author Mr.Tipx
	 * @createtime 2013-12-12 下午2:06:29
	 */
	protected String getCookie(String key) throws Exception{
		String ret = "";
		Cookie[] cookies = getRequest().getCookies();
		
        if(cookies != null && cookies.length > 0){ 
        	for(Cookie cookie : cookies){
        		if(cookie.getName().equals(key)){ 
                	ret = URLDecoder.decode(cookie.getValue(), "utf-8"); 
                }
        	}
        }
        return ret;
	}
	
	/**
	 * 获取所有cookie信息
	 * @return cookie<key, value>
	 * @throws Exception
	 * @author tipx
	 * @createtime 2013-12-12 下午2:05:34
	 */
	protected Map<String, String> getCookies() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		Cookie[] cookies = getRequest().getCookies();
		
        if(cookies != null && cookies.length > 0){ 
        	for(Cookie cookie : cookies){
        		map.put(cookie.getName(), URLDecoder.decode(cookie.getValue(), "utf-8"));
        	}
        }
        return map;
	}
	
	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageRecorders() {
		return pageRecorders;
	}

	public void setPageRecorders(String pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}
	
	public String getErrorMessage() {
	
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
	
		this.errorMessage = errorMessage;
	}

	public String getEncodeErrorMessage() throws Exception{
		
		return URLEncoder.encode(errorMessage, "UTF-8");
	}
	
	public String getCurSessionId(){
		return getSession().getId();
	}

	public String getIdentifier() {
		return CacheConstants.LOGIN_IDENTIFIER;
	}

	
	
}
