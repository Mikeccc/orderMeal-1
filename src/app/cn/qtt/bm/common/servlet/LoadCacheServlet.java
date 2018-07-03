package app.cn.qtt.bm.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ICacheable;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.ContextUtil;
import app.cn.qtt.bm.common.utils.StringUtil;
import app.cn.qtt.bm.common.utils.SystemConfig;



 /**       
  * 项目名称：CmEnterpriseProject    
  * 类名称：LoadCacheServlet    
  * 类描述：    
  * 创建人：linch  
  * 创建时间：2013-3-27 下午10:16:42    
  * 修改人：linch   
  * 修改时间：2013-3-27 下午10:16:42    
  * 修改备注：    
  * @version       
  */  
 
public class LoadCacheServlet extends HttpServlet{

	
	 /**
	 * 注释内容
	 */
	 
	private static final long serialVersionUID = 6778446602407907792L;

	protected CCrppLog4j log = new CCrppLog4j(this.getClass().getName());
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	private static int count = 1;

	private static String[] PERMIT_IP = null;
	
	private String servletParamsNames = null;
	
	
	 /**
	 * 可缓存的beanname
	 */
	 
	private String cacheableBeanNames = null;
	
	
	/**
	 * 初始化时装载代码表
	 * 
	 * @throws ServletException
	 */
	
	
	public void init() throws ServletException {
		this.servletParamsNames = getInitParameter("servletParamsNames");
		this.cacheableBeanNames = getInitParameter("cacheableBeanNames");
		
		if (!loadCachedtable(false)) {
			System.out.print("System will halt.");
			log.error("System will halt.");
		} else {
			log.info("Init SystemConstant...");
//			SystemConstant sc = new SystemConstant();
			initServletContextParams(getServletContext());
		}
	}
	

	@Override
	public void service(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException, RuntimeException {
		doGet(httpServletRequest, httpServletResponse);
	}
	
	
	/**
	 * Process the HTTP Post request
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * doGet和doPost方法用以手工刷新代码表
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 检验是否有刷新资源权限_IP
		if (!checkOperateRight(request, response))
			return;

		String action = request.getParameter("reload_type");
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		String thisPath = request.getContextPath() + request.getServletPath();
		if (thisPath == null || "".equals(thisPath.trim()))
			thisPath = "/loadParameter.hand";

		if (action != null) {
			String msg = "刷新成功。";
			if (action.equalsIgnoreCase("reload_parameter")) {
				msg = reloadParameter(request);
			} else if (action.equalsIgnoreCase("reload_prop")) {
				msg = reloadProp();
			}
			out.println("<html>");
			out.println("<head>");
			out.println("<title>刷新结果</title>");
			out.println("</head>");

			out.println("<body>");
			out
					.println("<div height=\"300\" align=\"center\" valign=\"middle\">");
			out.println("<p>" + msg + "</p>");
			out.println("<p>");
			out
					.println("<input type='button' name='btnClose' value='关　闭' onClick='window.close();'>");
			out
					.println("<input type='button' name='btnBack' value='返　回' onClick='history.go(-1);'>");
			out.println("</p>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>刷新应用系统资源</title>");
			out.println("</head>");

			out.println("<body style=\"font-size: 9pt\">");
			out
					.println("<div height=\"300\" align=\"center\" valign=\"middle\">");
			out.println("<p>请选择相应的刷新功能</p>");
			out
					.println("<p id=\"msg\" style=\"color: red; font-weight: bold\"></p>");
			out.println("<p>");
			out.println("<form name=\"frmRefresh\" action=\"" + thisPath
					+ "\">");
			out
					.println("<input type=\"hidden\" name=\"reload_type\" value=\"\">");
			out
					.println("<input type=\"button\" name=\"btnReloadCodeTable\" value=\"刷新参数(代码)表\" onClick=\"submitForm('reload_parameter');\">");

			out
					.println("&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"button\" name=\"btnReloadCodeTable\" value=\"刷新配置文件\" onClick=\"submitForm('reload_prop');\">");
			out.println("</form>");
			out.println("</p>");
			out.println("<p style=\"color:#ffffff\">");			
			String serverIP = InetAddress.getLocalHost().getHostAddress();
			try {
			if ("127.0.0.1".equals(serverIP)) {
				Enumeration netInterfaces = NetworkInterface
						.getNetworkInterfaces();
				InetAddress ip = null;
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) netInterfaces
							.nextElement();
					ip = (InetAddress) ni.getInetAddresses().nextElement();
					serverIP = serverIP + ip.toString();
				}
			}
			} catch(Exception ex) {
			}
			out.println("当前服务器IP：" + serverIP + "&nbsp;&nbsp;&nbsp;&nbsp;客户端IP：" + request.getRemoteAddr());
			out.println("</p>");
			out.println("</div>");
			out.println("<SCRIPT LANGUAGE=\"JavaScript\">");
			out.println("<!--");
			out.println("function submitForm(reload_type) {");
			out.println("	  document.all.reload_type.value=reload_type;");
			out.println("	  document.all.frmRefresh.submit();");
			out.println("	  document.all.msg.innerText = \"正在刷新，请稍候……\";");
			out.println("}");
			out.println("//-->");
			out.println("</SCRIPT>");
			out.println("</body>");
			out.println("</html>");
		}
		out.close();
	}

	
	
	
	/**
	* 方法名称:      initServletContextParams  
	* 方法描述:      
	* @param context        
	* @Author:      linch
	* @Create Date: 2012-12-11 上午10:50:50
	*/ 
	 
	private void initServletContextParams(ServletContext context){
		log.info("init ServletContext params ...");
		try {
			if(StringUtils.isNotBlank(servletParamsNames)){
				String[] params = servletParamsNames.split("\\|");
				if(params != null && params.length > 0){
					
					
					for(int i = 0;i<params.length;i++){
						
						String paramName = new String();
						String alias = new String();
						String param = params[i];
						if(StringUtils.isNotBlank(param)){
							if(param.indexOf(":") > 0){
								String[] vas = param.split(":");
								paramName = vas[0];
								alias = vas[1];
							}
							else{
								paramName = param;
								alias = param;
							}
							
							boolean isDeal = false;
							
							Field[] fields = Constants.class.getFields();
							for(Field field :fields){
								if(field.getName().equals(paramName)){
									if(field.getType().getName().equals("java.lang.String")){
										String result = (String) field.get(null);
										context.setAttribute(alias, result);
										params[i] = null;
										isDeal = true;
										break;
									}
								}
								
							}
							
							if(!isDeal){
								
								Method[] methods = Constants.class.getMethods();
								for(Method method : methods){
									if(method.getName().equals(paramName)){
										if(method.getReturnType().getName().equals("java.lang.String")){
											String result = (String) method.invoke(null, null);
											context.setAttribute(alias, result);
											params[i] = null;
											isDeal = true;
											break;
										}
									}
								}
							}
						}
						
					}
					
					
				}
				
				
			}
		} catch (Exception e) {
			log.exception(".initServletContextParams", e);
			
		}
		finally{
			log.info("init ServletContext params end...");
		}
	}
	
	
	
	/**
	 * 销毁所用的资源
	 */
	public void destroy() {
	}

	/**
	 * 刷新缓存参数表
	 * 
	 * @return String 刷新结果提示
	 */
	private synchronized String reloadParameter(HttpServletRequest request) {
		String retMsg = "";
		log.info("reload parameter tables...");

		if (loadCachedtable(true)) {
			initServletContextParams(request.getSession().getServletContext());
			retMsg = "刷新缓存参数成功。";
		} else {
			retMsg = "刷新缓存参数失败，请查看日志了解详细信息。";
		}
		return retMsg;
	}
	
	
	
	/**
	 * 刷新配置文件
	 * 
	 * @return String 刷新结果提示
	 */
	private synchronized String reloadProp() {
		String retMsg = "";
		log.info("reload properties file...");
		try {
			SystemConfig conf = new SystemConfig();
			conf.parseParam("", true);
			retMsg = "刷新配置文件成功。";
		} catch (Exception ex) {
			retMsg = "刷新配置文件失败，请查看日志了解详细信息。";
		}
		return retMsg;
	}

	/**
	 * 装载缓存参数表数据
	 * 
	 * @param forceRefresh
	 *            boolean 是否刷新标志
	 * @return boolean 装载是否成功标志
	 */
	private synchronized boolean loadCachedtable(boolean forceRefresh) {
		if (!forceRefresh)
			if (!CacheConstants.cache.isEmpty())
				return true;
		try {
			log.debug("Load cached tables " + (count++) + "  times.");

			if(StringUtils.isNotBlank(cacheableBeanNames)){
				String[] params = cacheableBeanNames.split("\\|");
				for(String param : params){
					Object object = ContextUtil.getBean(param);
					if(object instanceof ICacheable){
						log.debug("do cache bean name :"+object.getClass().getName());
						((ICacheable) object).doCache(CacheConstants.cache);
					}
					
				}
				
			}
			
			
		} catch (Exception ex) {
			log.exception(".loadCachedtable", ex);
			return false;
		}
		return true;
	}

	/**
	 * 检测是否有操作权限_IP权限
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	protected boolean checkOperateRight(HttpServletRequest request,
			HttpServletResponse response) {
		if (true) return true;
		String pIP = this.getInitParameter("permit_ip");
		if (StringUtil.isEmpty(pIP))
			pIP = "192.168.*,10.46,*";
		PERMIT_IP = pIP.split(",");

		String clientIP = request.getRemoteAddr();
		if ("127.0.0.1".equals(clientIP))
			return true;
		String passFlag = request.getParameter("letmepass");
		if (!StringUtil.isEmpty(passFlag))
			return true;

		if (PERMIT_IP != null && !StringUtil.isEmpty(clientIP)) {
			boolean bfound = false;
			for (int ii = 0; ii < PERMIT_IP.length; ii++) {
				Pattern pattern = Pattern.compile(PERMIT_IP[ii]);
				if (pattern.matcher(clientIP).find()) {
					bfound = true;
					break;
				}
			}
			if (bfound)
				return true;
		}
		try {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return false;
		} catch (IOException ex) {
			log.exception(".checkOperateRight", ex);
		}
		return true;
	}
	
	
	
	
}

