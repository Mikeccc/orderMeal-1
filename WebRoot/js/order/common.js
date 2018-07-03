	var	password = '';
	var	serviceId = '';
	var	timestamp = '';
	var linkAjaxValue="修改密码";

	$(document).on("pagebeforecreate",function(event,data){
		
		if(linkAjaxValue){
			$('a').each(function(i,v){
				if($(v).html().indexOf(linkAjaxValue) >= 0){
					if(!$(v).attr('data-ajax')){
						$(v).attr('data-ajax','false');
					}
				}
			});
		}
		
		$('input').each(function(i,v){
			if($(v).attr('type') && $(v).attr('type') != 'hidden'){
				if(!$(v).attr('data-role')){
					$(v).attr('data-role','none');
				}
			}
		});
		

	});
	
	
	$(document).ready(function(){
		$.mobile.page.prototype.options.domCache= false;
		$.mobile.defaultPageTransition = "slide";
		$.mobile.defaultDialogTransition = "fade";
		$.mobile.loadingMessage = "请稍后";
		$.mobile.pageLoadErrorMessage = "页面加载异常";
	});
	
	
	function CAjax(url, params, sfn, efn) {
		var newUrl = (arguments.length >= 1) ? arguments[0] : null;
		var newParams = (arguments.length >= 2) ? arguments[1] : null;
		var newSfn = (arguments.length >= 3) && typeof sfn == 'function'  ? arguments[2] : null;
		var newEfn = (arguments.length >= 4) && typeof efn == 'function'  ? arguments[3] : null;
		
		if (newUrl && newParams) {
			if(newParams.success && !newSfn && typeof newParams.success == 'function'){
				newSfn = newParams.success;
			}
			if(newParams.error && !newEfn && typeof newParams.error == 'function'){
				newEfn = newParams.error;
			}
			
			var ajaxPropery = {
				type : 'POST',
				url : newUrl,
				dataType : 'json',
				data : {},
				async : true,
				success : function(msg) {

				},
				error : function(msg) {

				}

			};
			ajaxPropery = $.extend(ajaxPropery, newParams);
			ajaxPropery.success = function(msg){
				if(msg.nopass != null && msg.nopass != ''){
					noPassCallbackFn(msg.nopass);
				}
				else{
					if($.isFunction(newSfn))
						newSfn(msg);
				}
			};
			ajaxPropery.error = function(msg){
				if(msg.nopass != null && msg.nopass != ''){
					noPassCallbackFn(msg.nopass);
				}
				else{
					if($.isFunction(newEfn))
						newEfn(msg);
				}
			};
			
			
			$.ajax(ajaxPropery);
		}
	}
	
	
	function noPassCallbackFn(noPassKey){
		switch (noPassKey) {
		case 1001:
			$('body').load('login');
			break;
		case 1002:
			$('body').load('../common/error.jsp');
			break;
		//权限异常
		case 1010:
			$('body').load('../error/competence-error.jsp');
			break;
		//内容异常
		case 1020:
			$('body').load('../error/content-error.jsp');
			break;
		default:
			break;
		}
	}
	
