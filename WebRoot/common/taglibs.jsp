<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"  %>
<%@ taglib prefix="qtt" uri="/WEB-INF/tld/qtt.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String _port = "";
    //80端口不显示,防止ie9等可能会出现跨域提示
    if(80 != request.getServerPort()){
        _port = ":"+request.getServerPort();
    }

    //路径统一以"/"结尾
    String _tmpPath = request.getScheme() + "://" + request.getServerName() + _port + request.getContextPath() + "/";
    //统一使用绝对url
    pageContext.setAttribute("path", _tmpPath);
    
    //页面载入时，服务器的当刻时间
    java.util.Date now = new java.util.Date();
    pageContext.setAttribute("currentDate", app.cn.qtt.bm.common.utils.DateUtil.format(now, "yyyy-MM-dd"));
    pageContext.setAttribute("currentTime", app.cn.qtt.bm.common.utils.DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
    
    //缓存时间, 加在js,css引入时做为附加请求参数
    //项目部署或需要清理缓存时变更, 达到自动清理缓存的目的
    String _cacheVersion = "20130809";
    pageContext.setAttribute("cacheVersion", _cacheVersion);
%>