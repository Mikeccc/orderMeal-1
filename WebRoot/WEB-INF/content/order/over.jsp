<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<div class="side_list_top">
    	<a class="back" data-role="none" href="shop-list">返回</a>   
        <font class="title">温馨提示</font>
    </div>
</header>
<section>
	
</section>
<section>
	<div class="side_list">
		<div class="czts_font">您已完成领餐!</div>
		<div class="czts_font" ><a style="color:blue;text-decoration:underline" href="${path}order/get-order!getAuthcodeVerify" data-ajax="false">验证码领餐</a></div>
		
		<br />
		<br/>
	</div>
</section>
    	<jsp:include page="/common/order/footer.jsp" />
		
</body>
</html>
