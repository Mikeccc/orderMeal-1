<%@ page language="java" import="java.util.*,app.cn.qtt.bm.common.constant.Constants" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<jsp:include page="/common/order/mate2.jsp"></jsp:include>
	<script type="text/javascript" src="../js/order/updatePassword.js"></script>
	<script type="text/javascript">
	 var token = '${sessionScope.login_token}';
	</script>
  </head>
  
  
  <body>
  	<a  name="top" id="top"></a>
		<div class="warp_box nav_box">
			<a href="javascript:history.go(-1);"  class="fh_btn"><img src="../images/front/fh_btn.jpg" height="43" /></a>
			<font class="nav_font">修改密码</font>
		</div>
	
		<form action="updatePassword"  method="post" id="form_update" data-ajax="false">
		<input type="hidden" name="userType" value="<%=Constants.USER_TYPE_CUSTOMER %>"/>
		<input type="hidden" name="token" value="${sessionScope.login_token}"/>
		
		<div class="login_box">
            <div class="login_line">
                <div class="login_line_L">原密码：</div>
                <div class="login_line_R">
                  <label>
                  <input id="userPassword" name="userPassword" type="password" name="textfield" style="width:190px; height:26px;"/>
                  </label>
                </div>
            </div>
			<div class="login_line">
				<div class="login_line_L">新密码：</div>
				<div class="login_line_R">
				  <label>
				  <input id="newPassword" name="newPassword" type="password" name="textfield" style="width:190px; height:26px;"/>
				  </label>
				</div>
			</div>
            <div class="login_line">
                <div class="login_line_L">确认密码：</div>
                <div class="login_line_R">
                  <label>
                  <input id="repeatPassword" name="repeatPassword" type="password" name="textfield" style="width:190px; height:26px;"/>
                  </label>
                </div>
            </div>
			<div class="login_line">
				<div class="login_line_L">&nbsp;</div>
				<div class="login_line_R login_cz_btn">
                    <a href="#" class="dl_btn" onclick="javascript:doSubmit();">修改</a>
                    <a href="#" class="hqmm_btn" onclick="javascript:history.go(-1);">返回</a>
				</div>
			</div>
			<div class="login_line">
				<div style="color: red;margin-left: 50px;" id="errorMessageDiv">${errorMessage }</div>
			</div>
		</div>	
		</form>
  	 <footer>
                <div class="warp_box foot_font" style="font-family:arial;">
                        Copyright &copy;2013 中国移动手机动漫基地版权所有
                </div>
        </footer>
  
  </body>
</html>
