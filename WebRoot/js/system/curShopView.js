
	$(document).ready(function(){
		updateValidate($("#curShopForm"));
	});

	function updateCurShop(){
		if(!$("#curShopForm").valid()){
    		return false;
    	}
		
		var shopName = $('#shopName').html().replace(/[ ]/g,"");
		if(shopName==null||shopName==""){
			return;
		}
		var shopPhoneNumber = $('#shopPhoneNumber').attr("value").replace(/[ ]/g,"");
		if(shopPhoneNumber==null||shopPhoneNumber==""){
			alert("请输入移动手机号码。");
			return;
		}
		var reg = /^((((13[5-9]{1})|(15[0,1,2,7,8,9]{1})|(147{1})|(18[2,3,7,8])){1}\d{1})|((134[0-8]{1}){1})){1}\d{7}$/;
		if (!reg.test(shopPhoneNumber)){
			alert("请输入正确的移动手机号码。");
			return;
		}
		
		var shopQq = $('#shopQq').attr("value").replace(/[ ]/g,"");
		if(shopQq==null||shopQq==""){
			alert("请输入QQ号。");
			return;
		}
		var qqReg = /^[1-9]\d{4,9}$/;
		if(!qqReg.test(shopQq)){
			alert("请输入正确的QQ号。");
			return;
		}
			
		var shopEmail = $('#shopEmail').attr("value").replace(/[ ]/g,"");
		if(shopEmail==null||shopEmail==""){
			alert("请输入电子邮箱。");
			return;
		}
		var emailReg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(!emailReg.test(shopEmail)){
			alert("请输入正确的电子邮箱。");
			return;
		}
		var shopAddress = $('#shopAddress').attr("value").replace(/[ ]/g,"");
		if(shopAddress==null||shopAddress==""){
			alert("请输入地址。");
			return;
		}
		CAjax("shop-manager!updateCurrentShop",
				{
					data : {
						'shopInfo.shopName' : shopName,
						'shopInfo.shopPhoneNumber' : shopPhoneNumber,
						'shopInfo.shopQq' : shopQq,
						'shopInfo.shopEmail' : shopEmail,
						'shopInfo.shopAddress' : shopAddress
					}
				},
				function(msg){
					if(msg.isSuccess){
						if(msg.shopInfo!=null){
							$('#shopPhoneNumber').attr("value",msg.shopInfo.shopPhoneNumber);
							$('#shopQq').attr("value",msg.shopInfo.shopQq);
							$('#shopEmail').attr("value",msg.shopInfo.shopEmail);
							$('#shopAddress').attr("value",msg.shopInfo.shopAddress);
							alert("信息修改成功！");
						}
					}else{
						if(msg.isInBmTime){
							alert("该操作不可在订餐及统计时间段内操作，请稍后再试");
							return;
						}
						if(msg.isduplicateFlag){//号码重复
							alert("手机号码已存在，请输入新的手机号！");
						}
					}
				}
			);
		
	}
	
	
	function getUpdatePasswordView(){
		window.parent.frames[3].location='shop-manager!getUpdatePasswordView';
	}
	
	//为对象添加验证
	function updateValidate($form){
		$form.validate({
			rules:{
		        shopPhoneNumber: {
		            required: true,
		            mobileYD: true
		        },
		        shopQq: {
		            required:true,
		            number: true,
		            minLength2:5,
		            maxLength2:11
		        },
		        shopEmail: {
		            required: true,
		            email: true
		        },
		        shopAddress: {  
		            required:true,
		            maxLength2:100
		        }
			},
			messages: {
		        shopPhoneNumber: {
		            required: "请填写手机号码",
		            mobileYD: "请填写中国移动的手机号码"
		        },
		        shopQq: {
		            required: "请填写QQ号码",
		            number: "QQ号码必须为数字",
		            minLength2: "至少录入5位QQ号码",
		            maxLength2: "至多录入11位QQ号码"
		        },
		        shopEmail: {
		            required: "请填写Email",
		            email: "Email格式不正确"
		        },
		        shopAddress: {  
		            required: "请填写供应商地址",
		            maxLength2: "填写内容不能超过100个字(汉字占2位)"
		        }
			},
			/* 设置验证触发事件 */  
		    focusInvalid: false,   
		    onkeyup: false,   
		    
		    /* 设置错误信息提示DOM */  
		    errorPlacement: function(error, element) {    
		    	error.css({"margin-left": "60px"});
		        error.appendTo( element.parent());       
		    }
		});
	}