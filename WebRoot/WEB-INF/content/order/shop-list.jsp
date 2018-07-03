<%@ page language="java" import="java.util.*,app.cn.qtt.bm.common.utils.DateUtil,app.cn.qtt.bm.common.cache.CacheConstants,org.apache.commons.lang.StringUtils" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <jsp:include page="/common/order/mate.jsp" />
	

  </head>
  
<body>
<a name="Top" id="Top"></a>
<div id="Wrap">
<header id="bar">
	<div class="side_list_top" data-role="none">
        <font class="title">商家列表</font>
    </div>
</header>
<section>
	<div class="side_list">
    	<div><img src="../images/sjlb_ban.jpg" /></div>
        <div><img src="../images/xcsm_img.jpg" /></div>    
    </div>
    <div class="side_list">
    	<c:forEach  items="${shopResponseBean.resultList}" var="row" varStatus="j" begin="0">
	 	<div class="sjxz_list">
        	<a href="shop-category-goods-list?shopId=${row.shopId }" class="sj_img" data-ajax="false" data-role="none">
					<c:choose>
						<c:when test="${not empty row.sysFile.fileAccessUrl}">
							<img src="${row.sysFile.fileAccessUrl}" width="96" height="96"/>
						</c:when>
						<c:otherwise>
							<img src="../images/front/dcimg1.jpg" width="96" height="96"/>
						</c:otherwise>
					</c:choose>
					</a>
            <span>
            		${row.shopName }<br /><font style="font-size:14px;">联系电话：${row.shopPhoneNumber }</font>
            </span>
        </div>    
	 </c:forEach>
    </div>
</section>
  <jsp:include page="/common/order/footer.jsp" />
</div>
</body>
</html>
