<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>头部</title>
<link rel="stylesheet" type="text/css" href="../../css/style.css"/>
<script type="text/javascript" src="../../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../../js/main/header.js"></script>
<style type="text/css">
	.User_font{display:block; padding:29px 0 0 0; line-height:30px; color:#FFFFFF; float:left}
	img{ border:none;}
	.User_cz_btn{display:block; padding:31px 0 0 10px; float:left}
</style>
<script type="text/javascript">
	function changeContextUrl(url){
		if(url){
			window.parent.frames[3].location=url;
		}
	} 
	function showUserDetail(){
		window.parent.getUserDetail();
	}
</script>
</head>

<body>

   	<div style="float:left"><img src="../../images/top_cen.gif"/></div>
	<div style="float: right; position: absolute; z-index: 100; right: 20px; bottom: 1px;">
		<span class="User_cz_btn"><a href="javascript:window.parent.location='../../system/login!doLogout'"><img src="../../images/button/tc_btn.gif" /></a></span>
	</div>
	
<div id="headerShadow">&nbsp;</div>
</body>
</html>
