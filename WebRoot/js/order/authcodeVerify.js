	
	function skipToVerify(){
		var mdn = $('#mdn').val().replace(/[ ]/g,"");//去空格
		if(mdn==""||mdn=="请输入手机号码后4位"){
			alert("请输入手机号码后4位");
			return;
		}
		if(isNaN(mdn)){
			alert("请输入正确的手机号码后4位");
			return;
		}
		if(mdn.length!=4){
			alert("请输入正确的手机号码后4位");
			return;
		}
		var authcode = $('#authcode').val().replace(/[ ]/g,"");//去空格
		if(authcode==""||authcode=="请输入验证码"){
			alert("请输入验证码");
			return;
		}
		$('#skipToVerifyByAuthcode').submit();
	}