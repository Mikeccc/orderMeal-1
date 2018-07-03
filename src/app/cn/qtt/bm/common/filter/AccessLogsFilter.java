package app.cn.qtt.bm.common.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.constant.Constants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.pojo.UserBean;
import app.cn.qtt.bm.common.utils.ContextUtil;
import app.cn.qtt.bm.common.utils.DateUtil;
import app.cn.qtt.bm.common.utils.StringUtil;
import app.cn.qtt.bm.common.utils.SystemUtility;
import app.cn.qtt.bm.manage.ILogMgr;
import app.cn.qtt.bm.manage.impl.LogMgr;
import app.cn.qtt.bm.model.TLogUserAccess;


/**
 * 系统访问日志过滤器
 * 
 * @title
 * @descriptor
 * @author zy
 * @version 2012-5-11
 */
public class AccessLogsFilter implements Filter {
	protected CCrppLog4j log = new CCrppLog4j(AccessLogsFilter.class.getName());
	private DateUtil du = new DateUtil();
	private static List<String> _ignoreExtList = new ArrayList<String>(); // 不过滤的关键字
	private static List<Map> cacheLogsRecordList = new ArrayList<Map>(); // 分批次更新缓存
	
	private ILogMgr logMgr;

	private String accessSource = "ENTERPRISE"; // 来源


	/**
	 * init
	 * 
	 * @param filterConfig
	 *            FilterConfig
	 * @throws ServletException
	 * @todo Implement this javax.servlet.Filter method
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// 可以通过以下代码获取Filter参数
		String accessSource = filterConfig.getInitParameter("accessSource");
		// Enumeration initParamNames = filterConfig.getInitParameterNames();
		// while (initParamNames != null && initParamNames.hasMoreElements()) {
		// System.out.println(initParamNames.nextElement());
		// }
		
		
		if (!SystemUtility.isEmpty(accessSource)) {
			this.accessSource = accessSource;
		}
		// 增加不需要记录日志的关键字
		_ignoreExtList.add(".JS");
		_ignoreExtList.add(".CSS");
		_ignoreExtList.add(".JPG");
		_ignoreExtList.add(".JPEG");
		_ignoreExtList.add(".PNG");
		_ignoreExtList.add(".GIF");
		_ignoreExtList.add(".SWF");
	}

	/**
	 * doFilter
	 * 
	 * @param servletRequest
	 *            ServletRequest
	 * @param servletResponse
	 *            ServletResponse
	 * @param filterChain
	 *            FilterChain
	 * @throws IOException
	 * @throws ServletException
	 * @todo Implement this javax.servlet.Filter method
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// 记录日志
		
		//初始化日志服务
		Object object = ContextUtil.getBean("logMgr");
		if(object != null && object instanceof LogMgr){
			logMgr = (LogMgr) object;
		}

		saveAccessLog((HttpServletRequest) arg0);
		arg2.doFilter(arg0, arg1);
	}

	/**
	 * 记录日志进数据库_分段批量提交
	 * 
	 * @param req
	 */
	private void saveAccessLog(HttpServletRequest req) throws ServletException {
		Map<String, Object> recordMap = new HashMap<String, Object>();
		
		
		String path = req.getServletPath();
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
			Object orderUser = req.getSession().getAttribute(Constants.SESSION_ORDER_USER_NAME);
			Object systemUser = req.getSession().getAttribute(Constants.SESSION_SYSTEM_USER_NAME);
			//全部获取所有的系统用户和订单用户，先根据目录请求来判断取哪个用户，如果取不到，另个有值，则取另个
			if(namespace.equals(Constants.NAMESPACE_ORDER_NAME)){
				object = orderUser;
			}
			if(namespace.equals(Constants.NAMESPACE_SYSTEM_NAME)){
				object = systemUser;
			}
			
			if(object == null && systemUser != null){
				object = systemUser;
			}
			
			if(object == null && orderUser != null){
				object = orderUser;
			}
			
			if(object != null && object instanceof UserBean){
				userBean = (UserBean)object;
			}
			
		}
		
		if(userBean == null ){
			userBean = new UserBean();
		}
		
		
		String url = req.getRequestURL().toString();
		String uri = req.getRequestURI();
		for (int ii = 0; ii < _ignoreExtList.size(); ii++) {
			if (uri.toUpperCase().endsWith((String) _ignoreExtList.get(ii)))
				return;
		}
		String queryString = req.getQueryString();

		try {
			if ("POST".equals(req.getMethod().toUpperCase())) {
				if (!SystemUtility.isEmpty(queryString)) {
					queryString = queryString + "&";
				}
				StringBuffer queryStrb = new StringBuffer();
				if (req.getParameterMap() != null) {
					Iterator entries = req.getParameterMap().entrySet()
							.iterator();
					Map.Entry entry = null;
					String name = "";
					String value = "";
					while (entries.hasNext()) {
						entry = (Map.Entry) entries.next();
						name = (String) entry.getKey();
						Object valueObj = entry.getValue();
						if (null == valueObj) {
							value = "";
						} else if (valueObj instanceof String[]) {
							String[] values = (String[]) valueObj;
							for (int i = 0; i < values.length; i++) {
								value = values[i] + ",";
							}
							value = value.substring(0, value.length() - 1);
						} else {
							value = valueObj.toString();
						}
						if (!SystemUtility.isEmpty(queryString)
								&& queryString.indexOf((name + "=")) > -1) {
						} else {
							queryStrb.append(name).append("=").append(value);
						}
					}
				} else {
					Enumeration paramNames = req.getParameterNames();
					String name = "";
					String value = "";
					while (paramNames.hasMoreElements()) {
						name = (String) paramNames.nextElement();
						String[] values = req.getParameterValues(name);
						for (int i = 0; i < values.length; i++) {
							value = values[i] + ",";
						}
						value = value.substring(0, value.length() - 1);
						if (!SystemUtility.isEmpty(queryString)
								&& queryString.indexOf((name + "=")) > -1) {
						} else {
							queryStrb.append(name).append("=").append(value);
						}
					}
				}
				if (SystemUtility.isEmpty(queryString)
						|| queryStrb.toString().length() > queryString.length()) {
					queryString = queryStrb.toString();
				}
				
			}
		} catch (Exception ex) {
		}
		// if (uri.indexOf("home-page") > -1 || uri.indexOf("index") > -1) {
		//
		// } else {
		// if (SystemUtility.isEmpty(queryString)) {
		// throw new ServletException("unknow access source.");
		// }
		// if (uri.indexOf("civilized-activities") > -1
		// || uri.indexOf("category") > -1 || uri.indexOf("end-product") > -1 ||
		// uri.indexOf("video-sort") > -1 || uri.indexOf("image-sort") > -1 ||
		// uri.indexOf("figure") > -1) {
		// if (queryString.indexOf("sessionIDs") > -1) {
		//
		// } else {
		// throw new ServletException("unknow access source.");
		// }
		// }
		// }
		String clientIP = req.getRemoteAddr();
		String serverIP = req.getServerName();
		try {
			if (!serverIP.startsWith("1") && !"127.0.0.1".equals(serverIP)) {
				serverIP = InetAddress.getLocalHost().getHostAddress();
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
			}
		} catch (Exception ex) {
			serverIP = req.getServerName();
		}
		if (SystemUtility.isEmpty(serverIP)) {
			serverIP = req.getServerName();
		}
		if (!SystemUtility.isEmpty(serverIP)) {
			String[] tmpServerIP = serverIP.split("/");
			serverIP = "";
			for (String tmpStr : tmpServerIP) {
				if (!"127.0.0.1".equals(tmpStr)) {
					serverIP = tmpStr + "," + serverIP;
				}
			}
			if (serverIP.length() > 31) {
				serverIP = serverIP.substring(0, 31);
			}
		}

		String referer = req.getHeader("Referer");
		String userAgent = req.getHeader("User-Agent");
		String mid = req.getParameter("MISC_MID");
		String mobile = "";
//		String mobile = req.getHeader("HTTP_X_NETWORK_INFO");
//		if (mobile == null) {
//			mobile = req.getHeader("x-up-calling-line-id");
//		}
//		if (mobile == null) {
//			mobile = req.getHeader("x-up-subno");
//		}
//		if (mobile == null) {
//			mobile = req.getHeader("X-Up-Calling-Line-ID");
//		}
//		if (mobile == null) {
//			mobile = req.getHeader("HTTP_X_UP_CALLING_LINE_ID");
//		}
		String uid = "";
		if (userBean.getUserInfo() != null && StringUtils.isNotBlank(userBean.getUserInfo().getUserPhoneNumber())) {
			
			if(StringUtils.isNotBlank(userBean.getUserInfo().getUserPhoneNumber())){
				mobile = userBean.getUserInfo().getUserPhoneNumber();
			}
			if(StringUtils.isNotBlank(userBean.getUserCode())){
				uid = userBean.getUserCode();
			}
			
			
		}
		if (!SystemUtility.isEmpty(mobile)) {
			mobile = StringUtil.getPrimalPhoneNumberFrom(mobile);
			if (!SystemUtility.isNumber(mobile)) {
				mobile = "";
			}
		}

		

		String sessionId = req.getSession().getId();
		String cookieId = "";// 存储JSESSION
		Cookie cookies[] = req.getCookies();
		if (cookies != null) {
			String cookieName = "JSESSIONID";
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					cookieId = cookieName + ":" + cookie.getValue();
				}
			}
		}

		String access_channel_id = ""; // 通过首页获取，或从Session中获取
		
		// if (uri.startsWith("")) { // 部分需要特殊处理的URI
		// Pattern p = Pattern.compile("ID=\\d+", Pattern.CASE_INSENSITIVE);
		// Matcher m = p.matcher(queryString);
		// boolean bFound = m.find();
		// if (bFound) {
		// articleId = m.group(0);
		// if (articleId.indexOf("=") > 0) {
		// articleId = articleId.split("=")[1];
		// }
		// }
		// }

		// t_logs_access_content表增加一个字段用于记录栏目Id

		

		// 增加Cookie
		recordMap.put("cookieId", cookieId);
		recordMap.put("sessionId", sessionId);

		recordMap.put("url", url);
		recordMap.put("uri", uri);
		recordMap.put("queryString", queryString);
		recordMap.put("clientIP", clientIP);
		recordMap.put("serverIP", serverIP);
		recordMap.put("referer", referer);
		recordMap.put("userAgent", userAgent);
		recordMap.put("accessTime", du.getToday());
		recordMap.put("mdn", mobile);
		recordMap.put("mid", mid);

		recordMap.put("accessCategoryUrl", url); // 记录请求地址
		recordMap.put("accessChannelId", access_channel_id);
		recordMap.put("accessModelId", ""); // 暂时留空
		recordMap.put("accessModelUrl", url);// 记录请求地址
		recordMap.put("accessSource", accessSource);

		// 根据来源判断
		String accessSourceType = "";
		if ("WAP".equals(accessSource)) {
			accessSourceType = "01";
		} else if ("MM".equals(accessSource)) {
			accessSourceType = "01";
		} else if ("WEB".equals(accessSource)) {
			accessSourceType = "02";
		} else if ("SERVICE".equals(accessSource)) {
			access_channel_id = req.getHeader("platformId");
			if (!SystemUtility.isEmpty(access_channel_id)) {
				if ("4".equals(access_channel_id)) {
					accessSourceType = "04";
				} else if ("5".equals(access_channel_id)) {
					accessSourceType = "05";
				} else if ("13".equals(access_channel_id)) {
					accessSourceType = "08";
				}
				recordMap.put("accessChannelId", access_channel_id);
			}
		}
		recordMap.put("accessSourceType", accessSourceType);
		recordMap.put("uid", uid);
		recordMap.put("accessContentId", "");

		cacheLogsRecordList.add(recordMap);

		int beginSaveNo = 0;
		try {
			if (du.getHourOfDay(du.getToday()) > 7
					&& du.getHourOfDay(du.getToday()) < 20) { // 工作时间日志记录零延时
				beginSaveNo = Integer.parseInt(CacheConstants.LOGS_BEGINSAVENO_WORKS());
			} else { // 非工作时间日志记录适当延时
				beginSaveNo = Integer.parseInt(CacheConstants.LOGS_BEGINSAVENO_SLEEP());
			}
		} catch (Exception ex) {
		}
		if (cacheLogsRecordList.size() >= beginSaveNo) {
			saveToDB();
		}
	}

	/**
	 * 把记录保存到数据库
	 * 
	 * @param uri
	 *            String
	 * @param articleId
	 *            String
	 * @param clientIP
	 *            String
	 * @param serverIP
	 *            String
	 */
	private void saveToDB() {
		try {
			if(logMgr != null){
				String categoryId = "";
				String accessContentId = "";
				String accessSn = ""; // uid+mdn+sessionId
				List<TLogUserAccess> logsAccesses = new ArrayList<TLogUserAccess>();
				for (int i = 0, sizeNo = cacheLogsRecordList.size(); i < sizeNo; i++) {
					TLogUserAccess accessLogsBean = new TLogUserAccess();
					Map logsMap = cacheLogsRecordList.get(i);
					accessLogsBean.setUserLogTime((Date) (logsMap.get("accessTime")));
					
					if (logsMap.get("uri") != null) {
						accessLogsBean.setUserOprateUri((String) logsMap
								.get("uri"));
					}
					if (logsMap.get("queryString") != null) {
						accessLogsBean.setUserOprateParame((String) logsMap
								.get("queryString"));
					}
					
					if (logsMap.get("serverIP") != null) {
						accessLogsBean.setUserServerIp((String) logsMap
								.get("serverIP"));
					}
					accessLogsBean.setUserCode((String)logsMap.get("uid"));
					accessLogsBean.setUserClientIp((String) logsMap.get("clientIP"));
					accessLogsBean.setUserServerIp((String) logsMap.get("serverIP"));
					accessLogsBean.setUserAgent((String) logsMap.get("userAgent"));
					accessLogsBean.setCookieId((String) logsMap.get("cookieId"));
					accessLogsBean.setSessionId((String) logsMap.get("sessionId"));



					logsAccesses.add(accessLogsBean);
				}
			
				logMgr.saveLogUserAccesses(logsAccesses);
				
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			// log.exception(".saveToDB", ex);
		} finally {
			cacheLogsRecordList.clear();
		}
	}

	/**
	 * 判断URL是否属于关键字
	 * 
	 * @param str
	 * @param url
	 * @return
	 */
	private boolean checkHasIn(String str, String url) {
		try {
			// 由于出现大小写问题，所以统计改成大写再进行匹配。
			str = str.toUpperCase();
			url = url.toUpperCase();
		} catch (Exception ex) {
		}
		String[] strArr = str.split(",");
		for (int i = 0, sizeNo = strArr.length; i < sizeNo; i++) {
			if (url.indexOf(strArr[i]) > -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证IP是否在黑名单中
	 * 
	 * @param clientIp
	 * @param blackIpList
	 * @throws IOException
	 */
	private  boolean checkHasBlackIpList(String clientIp, List<String> blackIpList)
			throws IOException {
		boolean bl = false;
		if (!SystemUtility.isEmpty(blackIpList)
				&& !SystemUtility.isEmpty(clientIp)) {
			for (String ipStr : blackIpList) {
				if (clientIp.equals(ipStr.trim())) {
					log.debug("无访问权限." + "clientIP:" + clientIp + ",ipStr:" + ipStr);
					bl = true;
					break;
				}
			}
		}
		return bl;
	}

	/**
	 * destroy
	 * 
	 * @todo Implement this javax.servlet.Filter method
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}



	public ILogMgr getLogMgr() {
	
		return logMgr;
	}

	public void setLogMgr(ILogMgr logMgr) {
	
		this.logMgr = logMgr;
	}

	public static void main(String[] args) throws Exception {
		Pattern p = Pattern.compile("categoryId\\d?=\\d+",
				Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher("categoryId1=111&categoryId2=112");
		while (m.find()) {
			String str = m.group(0);
			if (str.indexOf("=") > 0) {
				str = str.split("=")[1];
				System.out.println(str);
			}
		}
	}
}