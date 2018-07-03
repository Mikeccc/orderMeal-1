<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>中国移动手机动漫基地-订餐系统</title>
	<script src="../js/jquery-1.8.2.min.js" type="text/javascript"></script>
	<script src="../js/common.js" type="text/javascript"></script>
	<script src="../js/system/updateShopPasswordView.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<script type="text/javascript">
        var contextPath = "${ctx}";
	</script>
</head>
<body>
	<span class="xglist" id="optSpan"><input id="oldPasswordText" type="text" value="原始密码" style="width:250px;" /></span>
	<span class="xglist" id="opSpan" style="display:none"><input id="oldPassword" type="password" value="" style="width:250px;" /></span>
	<span class="xglist" id="nptSpan"><input id="newPasswordText" type="text" value="新密码" style="width:250px;" /></span>
	<span class="xglist" id="npSpan" style="display:none"><input id="newPassword" type="password" value="" style="width:250px;" /></span>
	<div class="xglist">
		<img src="../images/button/qx.gif" onclick="cancle()" style="cursor:pointer;"/>
		<img src="../images/button/xg.gif" onclick="updatePassword()" style="cursor:pointer;"/>
	</div>
</body>
</html>