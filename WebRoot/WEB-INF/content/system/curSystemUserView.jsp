<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>中国移动手机动漫基地-订餐系统</title>
	<script type="text/javascript">
		function getUpdatePasswordView(){
			window.parent.frames[3].location='${path}/system/user!getModifySystemUserPasswordView';
		}
	</script>
</head>
<body>
	<div>
		<br />
		<br />
		<span style="display: block;margin: 10px;padding: 3px 0;width: 400px;font-size:20px;">手机号码：${user.userPhoneNumber}</span>
		<span style="margin: 10px;"><img src="${ctx}/images/button/xgmm.gif" id="modifyPassword" style="cursor:pointer;" onclick="javascript:getUpdatePasswordView()"/></span>
	</div>
</body>
</html>