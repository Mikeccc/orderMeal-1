<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>内容错误</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/New_page.css"/>
</head>

<body>
	<div style="text-align: center;width: 100%;height: 100%;">
		<div style="width:700px; margin:100px auto; display:inline-table; border:5px solid #E8E8E8">
			<div style="float:left; margin:10px 20px; width:266px; height:230px; background:url(../images/404_bg.png) no-repeat;">
				<font style=" display:block; margin-top:70px; text-align:center; font-size:60px;">错误</font>
			</div>
			<div style=" float:left; width:350px; margin-top:70px;font-size: 16px;">对不起，您请求的内容包含高危或非法字符！</div>
		
		</div>
	</div>
</body>
</html>
