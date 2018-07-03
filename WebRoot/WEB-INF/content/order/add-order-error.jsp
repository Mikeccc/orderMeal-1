<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <jsp:include page="/common/order/mate.jsp" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/front.css" />

  </head>
  
<body>
<a name="Top" id="Top"></a>
<div id="Wrap">
<header id="bar">
	<div class="side_list_top">
    	<a class="back" data-role="none" href="shop-list">返回</a>         
        <font class="title">温馨提示</font>
    </div>
</header>
<section>
	<div class="side_list">
	<div style="padding:30px 10px; line-height:30px;">提示：${errorMessage}</div>
	</div>
</section>
    	<jsp:include page="/common/order/footer.jsp" />
</body>
</html>
