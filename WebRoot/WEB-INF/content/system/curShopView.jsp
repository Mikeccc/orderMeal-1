<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>中国移动手机动漫基地-订餐系统</title>
	<script src="${ctx }/js/jquery-1.8.2.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/additional-methods.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/additional-methods-extend.js"></script>
	<script src="${ctx }/js/system/curShopView.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />
	<script type="text/javascript">
        var contextPath = "${ctx}";
	</script>
</head>
<body>
	<form action="#" id="curShopForm">
		<div class="dp_name" >店铺名：<font class="dp_font" id="shopName">${shopInfo.shopName}</font></div>
		<span class="xglist"><font class="font_block">手机号码：</font><input type="text" id="shopPhoneNumber" value="${shopInfo.shopPhoneNumber}"   style="width:250px;" /></span>
		<span class="xglist"><font class="font_block">QQ：</font><input type="text" id="shopQq" value="${shopInfo.shopQq}"   style="width:250px;" /></span>
		<span class="xglist"><font class="font_block">邮箱：</font><input type="text" id="shopEmail" value="${shopInfo.shopEmail}"   style="width:250px;" /></span>
		<span class="xglist"><font class="font_block">地址：</font><input type="text" id="shopAddress" value="${shopInfo.shopAddress}"   style="width:250px;" /></span>
		<div class="xglist">
			<span style="display:block; padding-left:60px;">
			<img src="${ctx }/images/button/xgmm.gif" onclick="getUpdatePasswordView()" style="cursor:pointer;"/>
			<img src="${ctx }/images/button/xgxx.gif" onclick="updateCurShop()" style="cursor:pointer;"/></span>
		</div>
	</form>
</body>
</html>