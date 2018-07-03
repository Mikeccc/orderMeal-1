<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <jsp:include page="/common/order/mate2.jsp" />
	<script type="text/javascript" src="../js/order/authcodeVerify.js"></script>
  </head>
  
<body>
	<a name="top" id="top"></a>
	<div class="warp_box nav_box">
		<font class="nav_font" style="padding-left:140px;">验证</font>
	</div>
	<form action="get-order!skipToVerifyByAuthcode"  method="post" id="skipToVerifyByAuthcode" data-ajax="false">
	<h1></h1>
	<div class="warp_box">
		<div class="login_box">
            <div class="login_line">
                <div class="login_line_L">手机号码：</div>
                <div class="login_line_R">
                  <label>
                  <input id="mdn" name="mdn" type="text" name="textfield" value="请输入手机号码后4位" onfocus="if (value =='请输入手机号码后4位'){value =''}" onblur="if (value ==''){value='请输入手机号码后4位'}" style="width:190px; height:26px;"/>
                  </label>
                </div>
            </div>
			<div class="login_line">
				<div class="login_line_L">验证码：</div>
				<div class="login_line_R">
				  <label>
				  <input id="authcode" name="authcode" type="text" value="请输入验证码" onfocus="if (value =='请输入验证码'){value =''}" onblur="if (value ==''){value='请输入验证码'}" name="textfield" style="width:190px; height:26px;"/>
				  </label>
				</div>
			</div>
			<div class="login_line">
				<div class="login_line_L">&nbsp;</div>
				<div class="login_line_R login_cz_btn">
                    <a href="#" class="dl_btn" onclick="javascript:skipToVerify();">验证</a>
				</div>
			</div>
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
