<%@ page language="java" import="java.util.*,app.cn.qtt.bm.common.constant.Constants" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	
	<meta http-equiv="pragma" content="no-cache"/> 
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/> 
	<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
	
	<jsp:include page="/common/order/mate.jsp"></jsp:include>
	<%--
	<script type="text/javascript" src="../js/order/login.js"></script>
	 --%>

	<script type="text/javascript">
	 var token = '${sessionScope.login_token}';
	
	 var waitTime = 60;
		
		function loginSubmit(){
			
			var userPhoneNumber = $('#userPhoneNumber').val();
			if(userPhoneNumber == ''){
				$('#errorMessageDiv').html('手机号不能为空');
				return ;
			}
			else{
				var currentTime = new Date();
				var n = currentTime.getTime();
				$('#form_login').attr('action','dologin?t='+n);
				$('#form_login').submit();
			}
		}


		function resetPassword(){
			
			var userPhoneNumber = $('#userPhoneNumber').val();
			if(userPhoneNumber == ''){
				$('#errorMessageDiv').html('手机号不能为空');
				return ;
			}
			
			if(userPhoneNumber != null && userPhoneNumber != ''){
				CAjax("resetPassword", {
					data : {
						'userPhoneNumber' : userPhoneNumber,
						'token' : token
					}
				}, function(msg) {
					if(msg.isSuccess){
						beginWait();
						alert("您的密码已经以短信方式发送到您的手机，请查收！");
					}
					else{
						if(msg.errorMessage){
							alert(msg.errorMessage);
						}
					}
					
					
					if(msg.token){
						token = msg.token;
					}

				}, function(msg) {

				});
				
			}
			else{
				alert('请先输入用户手机号码');
			}
			
			
		}


		function beginWait(){
			waitTime = 60;
			$('#wait_button').attr('value',waitTime+'秒再获取');
			$('#captcha_button').hide();
			$('#wait_button').show();
			waitTimeOut();
		}

		function waitTimeOut(){
			setTimeout(function(){
				if(waitTime > 0){
					waitTime--;
					$('#wait_button').attr('value',waitTime+'秒再获取');
					waitTimeOut();
				}
				else{
					$('#captcha_button').show();
					$('#wait_button').hide();
				}
			}, 1000);
		}
		
		//设置复选框
		function doChecked(chkbox){
			if($(chkbox).is(":checked")){
				$('#remember').val("1");
			}else{
                $('#remember').val("");
			}
		}
        
        $(document).ready(function(){
            $('#userPhoneNumber').val('${userPhoneNumber}');
            $('#userPassword').val('${userPassword}');
            if('1' == '${remember}'){
                $('#remember').val('1');
                $('#checkedPic').show();
                $('#uncheckedPic').hide();
            }
        });
		
	</script>
  </head>
  
  
  <body>
  	<div id="Wrap">
		<header id="bar">
			<div class="side_list_top">
		    	<!--<a class="back" data-role="none" href="shop-list">返回</a>  -->
		        <font class="title">登录</font>
		    </div>
		</header>
		<section>
			<div class="side_list">
				<img src="../images/login_banner.jpg" style="padding-bottom:10px;" />
		    </div>
		</section>
		<form id="loginForm" action="dologin"  method="post" id="form_login"  data-ajax="false" data-role="none">
		<input type="hidden" name="comeFrom" value="login"/>
		<input type="hidden" name="userType" value="<%=Constants.USER_TYPE_CUSTOMER %>"/>
		<input type="hidden" name="to" value="${to }"/>
		
		<section>
			<div class="side_list">
				<input id="remember" name="remember" type="hidden" value="" />
		    	<div class="line_list2"><input placeholder="请输入手机号码" name="userPhoneNumber" id="userPhoneNumber" type="text" name="textfield" class="input_txt" /></div>
				<div class="line_list"><input placeholder="请输入密码" name="userPassword" id="userPassword" type="password" name="textfield"  class="input_txt" /></div>
		        <div class="line_list"><input name="" type="checkbox" value="" ${remember == '1' ? 'checked="checked"' : ''} onclick="doChecked(this);" />记住密码</div>
		    </div>
		</section>
		<section>
			<div class="side_list3 font_center" style="padding-top: 10px;">
			  <input type="button" id="captcha_button" value="获取密码" class="Yellow_input_btn"  style="cursor: pointer;" onclick="javascript:resetPassword();" />
			  <input type="button" id="wait_button" value="60秒后再获取" class="Yellow_input_btn"  style="cursor: pointer;display:none;font-family:Arial;font-size:14px;font-weight:normal;font-style:normal;text-decoration:none;color:#FFFFFF;" />
			  &nbsp;
			  <input type="button" class="Green_input_btn" value="登录"  style="cursor: pointer;" onclick="javascript:$('#loginForm').submit();"/>
		    </div>
		</section>
		</form>
		<section>
				<div style="clear:both;  margin:20px auto; width:320px; color: red;   text-align:center;" id="errorMessageDiv">${errorMessage }</div>
		</section>
		<section>
			<div class="ts_list" style="margin:0 auto;">
		    	亲爱的客官，如果<font style="color:#FFA200">是首次来我们这里抢饭，或者忘记密码了</font>，就用填写的手机号码获取密码喽～<br />
		想要快速登录来抢饭？那就请<a style="color:#7EB700" href="http://wap.dm.10086.cn/images/caiman/sign/client/bespeakmeal/android/package/bespeakmeal.apk" >下载</a>使用这款神器吧。仅支持Android版本。    </div>
		<div class="Cop_box" style="margin: 0 auto;">Copyright ©2013 中国移动手机动漫基地版权所有</div>
		</section>
		
		</div>
  </body>
</html>
