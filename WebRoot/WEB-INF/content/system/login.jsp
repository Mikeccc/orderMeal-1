<%@ page language="java" import="java.util.*,app.cn.qtt.bm.common.constant.Constants" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>中国移动手机动漫基地-订餐系统</title>
   	<meta http-equiv="pragma" content="no-cache"/> 
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/> 
	<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
	
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="../js/common.js"></script>
	<script type="text/javascript" src="../js/system/login.js"></script>
	<script type="text/javascript">
	 var contextPath = "<%=path%>";
	 var token = '${sessionScope.login_token}';
	 
	 if(window.parent.document.body != null){
	 	window.parent.location = window.location;
	 }
	 
	 </script>
  </head>
 <style type="text/css">
.login_bg{width:100%; height:100%; background:url(../images/login/login_1920x700.jpg) top center no-repeat;}

.login_warp{width:1003px;  display:table; margin:auto;}

.login_top{wdith:100%; height:309px;}

.login_Con{width:398px; height:265px; margin:auto;}

.One_list_box{width:158px; margin:auto;}

.radio_box{float:left; margin:9px 5px 0px 10px;}

.radio_font{float:left; padding-top:12px;}

.Two_list_box{width:360px; margin:auto; clear:both;}

.Two_list_box a:link {
 	color: #FFFFFF; /*连接默认的颜色变化*/
 	text-decoration:none;}
	
.Two_list_box a:visited {
 	color: #FFFFFF; /*连接访问的颜色变化*/
 	text-decoration:none;}
	
.Two_list_box a:hover {
 	color: #333333; /*鼠标经过的颜色变化*/
 	text-decoration:none;}
	
.Two_list_box a:active { 
 	color: #FFFFFF; /*鼠标按下的颜色变化*/
 	text-decoration:none;}

.Tlist_font{display:block; padding:10px 0px; font-size:14px;}

.hqmm_btn{display:block; width:148px; height:41px; background:url(../images/login/hqmm_btn.jpg) no-repeat; line-height:41px; text-align:center; font-size:16px; color:#FFFFFF; float:left; margin:10px 15px; _margin:10px 10px;}

.dl_btn{display:block; width:148px; height:41px; background:url(../images/login/dl_btn.jpg) no-repeat; line-height:41px; text-align:center; font-size:16px; color:#FFFFFF; float:left; margin:10px 15px;}

.Lo_footer{width:398px; margin:auto; text-align:center; line-height:48px;}

</style>
<body>
<div class="login_bg">
	<div class="login_warp">
		<div class="login_top"></div>
		<div class="login_Con">
			<form action="../system/dologin" method="post" id="form_login">
			<input type="hidden" name="comeFrom" value="login"/>
			<input type="hidden" name="to" value="${to }"/>
			<div class="One_list_box">
			
				<ul>
					<c:choose>
						<c:when test="${identifier == '1'}">
							<li class="radio_box"><input name="userType" style="display:none" type="radio" checked="checked" value="<%=Constants.USER_TYPE_ADMIN %>" /></li>
						</c:when>
						<c:when test="${identifier == '2'}">
							<li class="radio_box"><input name="userType" style="display:none" type="radio" checked="checked" value="<%=Constants.USER_TYPE_SHOP %>" /></li>
						</c:when>
						<c:otherwise>
							<li class="radio_box"><input name="userType" type="radio" <c:if test="${userType == '03'}">checked="checked"</c:if> value="<%=Constants.USER_TYPE_ADMIN %>" /></li>
							<li class="radio_font">管理员</li>
							<li class="radio_box"><input name="userType" type="radio" <c:if test="${empty userType  || userType == '02'}">checked="checked"</c:if> value="<%=Constants.USER_TYPE_SHOP %>" /></li>
							<li class="radio_font">供应商</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="Two_list_box">
				<font class="Tlist_font">手机号码</font>
				<input name="userPhoneNumber" id="userPhoneNumber" type="text" style="width:360px; height:24px;" value="${userPhoneNumber }" />
			</div>
			<div class="Two_list_box">
				<font class="Tlist_font">密码</font>
				<input name="userPassword" type="password" style="width:360px; height:24px;" />
			</div>
			<div class="Two_list_box">
				<a href="javascript:resetPassword()" id="captcha_button" class="hqmm_btn">获取密码</a>
				<a  style="display: none;" id="wait_button" class="hqmm_btn">获取密码</a>
				<a href="javascript:loginSubmit()" class="dl_btn">登录</a>
			</div>
			<div class="Two_list_box">
				<div style="color: red;margin-left: 50px;" id="errorMessageDiv">${errorMessage }</div>
			</div>
			</form>
		</div>
		<div class="Lo_footer" style="font-family:arial;">Copyright &copy;2013 中国移动手机动漫基地版权所有</div>		
	</div>
</div>
</body>
</html>
