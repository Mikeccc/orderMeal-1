
	
	$(document).ready(function(){
		$('#oldPasswordText').focus(function(){
			$('#optSpan').hide();
			$('#opSpan').show();
			$('#oldPassword').focus();			
		});
		$('#oldPassword').blur(function(){
			if($(this).attr("value")==""){
				$('#opSpan').hide();
				$('#optSpan').show();
			}
		});
		
		$('#newPasswordText').focus(function(){
			$('#nptSpan').hide();
			$('#npSpan').show();
			$('#newPassword').focus();			
		});
		$('#newPassword').blur(function(){
			if($(this).attr("value")==""){
				$('#npSpan').hide();
				$('#nptSpan').show();
			}
		});
		
	});


	function updatePassword(){
		var oldPassword = $('#oldPassword').attr("value").replace(/[ ]/g,"");
		if(oldPassword==null||oldPassword==""){
			alert("请输入原始密码！");
			return;
		}
		var test = /^[0-9a-zA-Z]*$/g;   //只能输入字母或数字
		if(!test.test(oldPassword)){
			alert("你的输入的原始密码错误，请重新输入！");
			return;
		}
		var newPassword = $('#newPassword').attr("value").replace(/[ ]/g,"");
		if(newPassword==null||newPassword==""){
			alert("请输入新密码！");
			return;
		}
		if(test.exec(newPassword)){
			alert("你的输入的新密码格式有误，请重新输入！");
			return;
		}
		CAjax("user!modifySystemUserPassword",
				{
					data : {
						oldPassword : oldPassword,
						newPassword : newPassword
					}
				},
				function(msg){
					if(msg.isSuccess){
						alert("修改密码成功！"); 
					}else{
						alert("你的输入的原始密码错误，请重新输入！");
						return;
					}
					cancle();
				}
		);
		
	}
	
	function cancle(){
		
		$('#opSpan').hide();
		$('#optSpan').show();
		$('#npSpan').hide();
		$('#nptSpan').show();
		$("#oldPassword").attr("value","");
		$("#newPassword").attr("value","");
		return;
	}
	
	
	
	
				
				
				