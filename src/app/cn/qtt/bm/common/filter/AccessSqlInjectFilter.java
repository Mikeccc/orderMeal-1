package app.cn.qtt.bm.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.RequestUtils;

import app.cn.qtt.bm.common.cache.CacheConstants;
import app.cn.qtt.bm.common.cache.ExceptionConstants;
import app.cn.qtt.bm.common.log.CCrppLog4j;
import app.cn.qtt.bm.common.utils.StringUtil;
import app.cn.qtt.bm.common.utils.SystemUtility;


/**
 * @title
 * @descriptor
 * @author zy
 * @version 2012-5-21
 */
public class AccessSqlInjectFilter implements Filter {
	protected CCrppLog4j log = new CCrppLog4j(AccessSqlInjectFilter.class
			.getName());

	// private String injectStr =
	// "'|and|or|exec|insert|select|delete|update|count|%|from|create|drop|truncate|";

	private String injectStr = "'|and|exec|insert|select|delete|update|count|%|from|create|drop|truncate|";

	/**
	 * init
	 * 
	 * @param filterConfig
	 *            FilterConfig
	 * @throws ServletException
	 * @todo Implement this javax.servlet.Filter method
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		String injectStr = filterConfig.getInitParameter("injectStr");
		if (SystemUtility.isEmpty(injectStr)) {
			this.injectStr = injectStr;
		}
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
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		log.info(".doFilter is working.");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		try {
//			String clientIP = servletRequest.getRemoteAddr();
//			checkHasBlackIpList(clientIP, SystemConstant.IPFILTER_BLACKLIST());

			// HttpServletRequest req = (HttpServletRequest) servletRequest;
			// UserBean userBean = new UserBean(); // 用户登录信息
			// if (req.getSession().getAttribute(
			// SystemConstant.CURRENT_USER_KEY_
			// + SystemConstant.USER_SESSION_NAME) != null) {
			// userBean = (UserBean) req.getSession().getAttribute(
			// SystemConstant.CURRENT_USER_KEY_
			// + SystemConstant.USER_SESSION_NAME);
			// }
			// String mdn = "";
			// if (!SystemUtility.isEmpty(userBean.getMdn())) {
			// mdn = userBean.getMdn();
			// }
			// checkHasBlackIpAndMdnList(mdn, clientIP, SystemConstant
			// .IPFILTER_BLACK_IPLIST());
//		} catch (IOException ex) {
//			throw ex;
//		}
		try{
			if (servletRequest instanceof HttpServletRequest) {
				
				Map<?, ?> map = request.getParameterMap();
				Map<Object, Object> parameterMap = new HashMap<Object, Object>(map);
				boolean bl = false;
				final String requestUrl = RequestUtils.getServletPath(request);
				final String shouldNotFilter = CacheConstants.SHOULD_NOT_FILTER_URL();
				if (MapUtils.isNotEmpty(parameterMap)) {
					Set<?> keySet = parameterMap.keySet();
					for (Object key : keySet) {
						Object value = parameterMap.get(key);
						if ("key".equals(key)
								|| StringUtils.contains(shouldNotFilter, ";"
										+ requestUrl + "?" + key + ";")) {
							parameterMap.put(key, value);
						} else {
							if (value instanceof String) {
								String string = (String) value;
								checkHasInKeywords(string);
								parameterMap.put(key, SystemUtility
										.filterXssStr(string));
								bl = true;
							} else if (value instanceof String[]) {
								String[] strings = (String[]) value;
								if (!ArrayUtils.isEmpty(strings)) {
									int i = 0;
									for (String string : strings) {
										checkHasInKeywords(string);
										string = SystemUtility.filterXssStr(string);
										strings[i] = string;
										i++;
									}
									parameterMap.put(key, strings);
									bl = true;
								}
							}
						}
					}
				}
				if (bl) {
					ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(
							request, parameterMap);
					filterChain.doFilter(wrapRequest, servletResponse);
					return;
				}
		
			}
		}
		catch (IOException e) {
			HttpServletResponse response = (HttpServletResponse) servletResponse; 
			
			if (request.getHeader("X-Requested-With") != null
					&& request.getHeader("X-Requested-With").equals(
							"XMLHttpRequest")) {
				PrintWriter pw = response.getWriter();
				response.setCharacterEncoding("text/html; charset=utf-8");
				response.setContentType("text/html; charset=utf-8");
				pw.write("{\"nopass\":" + ExceptionConstants.SYSTEM_CONTENT_NO_PASS_CODE + "}");
			}
			else{
				
				response.sendRedirect(request.getContextPath()+"/error/content-error.jsp");
			}
			 
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * destroy
	 * 
	 * @todo Implement this javax.servlet.Filter method
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 验证是否存在于sql关键词中
	 * 
	 * @param value
	 * @throws IOException
	 */
	private void checkHasInKeywords(String value) throws IOException {
		if (!SystemUtility.isEmpty(value)) {
			String[] injectStrArr = StringUtils.split(this.injectStr, '|');
			if (!ArrayUtils.isEmpty(injectStrArr)) {
				for (String injectTmp : injectStrArr) {
					if (StringUtils.isNotBlank(injectTmp)
							&& value.toLowerCase().indexOf(
									injectTmp.toLowerCase()) != -1) {
						throw new IOException("提交的请求中包含有非法字符！");
					}
				}
			}
		}
	}

	/**
	 * 验证访问号码及IP是否在黑名单中
	 * 
	 * @param clientIp
	 * @param blackIpList
	 * @throws IOException
	 */
	private void checkHasBlackIpAndMdnList(String mdn, String clientIp,
			String blackIpList) throws IOException {
		if (!SystemUtility.isEmpty(blackIpList)
				&& !SystemUtility.isEmpty(clientIp)
				&& !SystemUtility.isEmpty(mdn)) {
			mdn = StringUtil.getPrimalPhoneNumberFrom(mdn);
			String tmpStr = mdn + "#" + clientIp;
			if (blackIpList.indexOf(tmpStr) > -1) {
				log.debug("mdnAndClientIP:" + tmpStr + ",blackIpList:"
						+ blackIpList);
				throw new IOException("无访问权限.");
			}
		}
	}

	/**
	 * 验证IP是否在黑名单中
	 * 
	 * @param clientIp
	 * @param blackIpList
	 * @throws IOException
	 */
	private void checkHasBlackIpList(String clientIp, List<String> blackIpList)
			throws IOException {
		if (!SystemUtility.isEmpty(blackIpList)
				&& !SystemUtility.isEmpty(clientIp)) {
			for (String ipStr : blackIpList) {
				if (clientIp.equals(ipStr.trim())) {
					log.debug("clientIP:" + clientIp + ",ipStr:" + ipStr);
					throw new IOException("无访问权限.");
				}
			}
		}
	}
}
