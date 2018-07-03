
var waitTime = 60;

$(document).ready(function() {
	
});



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
				alert("您的密码已经以短信方式发送到您的手机，请查收！");
				beginWait();
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
	$('#wait_button').html(waitTime+'秒再操作');
	$('#captcha_button').hide();
	$('#wait_button').show();
	waitTimeOut();
}

function waitTimeOut(){
	setTimeout(function(){
		if(waitTime > 0){
			waitTime--;
			$('#wait_button').html(waitTime+'秒再操作');
			waitTimeOut();
		}
		else{
			$('#captcha_button').show();
			$('#wait_button').hide();
		}
	}, 1000);
}


