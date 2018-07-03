<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shop-manager-add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx }/js/main/thirdParty/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/js/common.js"></script>
	<script type="text/javascript" src="${ctx }/js/system/shopManager.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />	

  </head>
  
<body>
<input type="hidden" id="shopId" name="shopId" value="${shopInfo.shopId }">
<div class="dp_name" >店铺名：<font class="dp_font"><input type="text" id="shopName" value="${shopInfo.shopName }"  style="width:250px;" /></font></div>
<span class="xglist"><font class="font_block">手机号码：</font><input type="text" id="shopPhoneNumber" value="${shopInfo.shopPhoneNumber }"  style="width:250px;" /></span>
<span class="xglist"><font class="font_block">QQ：</font><input type="text"  id="shopQq" value="${shopInfo.shopQq }"  style="width:250px;" /></span>
<span class="xglist"><font class="font_block">邮箱：</font><input type="text"  id="shopEmail" value="${shopInfo.shopEmail }"  style="width:250px;" /></span>
<span class="xglist"><font class="font_block">地址：</font><input type="text" id="shopAddress"  value="${shopInfo.shopAddress }"  style="width:250px;" /></span>
<div style=" width:600px; margin:10px;">
	<img src="images/button/tj.gif"  onclick="addShop()"/>
	<img src="images/button/fhlist_btn.gif" onclick="deleteShop()" />
</div>

</body>
</html>
