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
		<a class="back" data-role="none" href="javascript:history.go(-1);">返回</a>
        <font class="title">我的饭盒</font>
    </div>
</header>
<section>
	<div class="side_list">	
	      		<font class="red_font">您当天已有生效的订单了，想要继续订购或修改订单，得先取消之前的订单呦，取消后记得及时下单哦，不然您就没饭吃啦！！</font>
    <div class="cz_btnbox">
    	<input type="button" class="Yellow_input_btn" value="取消订单"  onclick="$.mobile.changePage('cancel-order!cancel?orderId=${orderId}&shopId=${shopId}')" />
    </div>
</section>
	<jsp:include page="/common/order/footer.jsp" />
</div>
</body>
</html>
