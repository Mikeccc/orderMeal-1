	var	password = '';
	var	serviceId = '';
	var	timestamp = '';
	var   eid = '';
	var   adcToken = '';


	function FaceImage(key, name, passUrl) {
	    this.key = key;
	    this.name = name;
	    this.passUrl = passUrl;
	}
	
	var faceImages=[
	    new FaceImage(1,"24x24(1)","http://dm.10086.cn/images/caiman/sign/face/1/24x24.png"),
	    new FaceImage(2,"24x24(2)","http://dm.10086.cn/images/caiman/sign/face/2/24x24.png"),
	    new FaceImage(3,"24x24(3)","http://dm.10086.cn/images/caiman/sign/face/3/24x24.png"),
	    new FaceImage(4,"24x24(4)","http://dm.10086.cn/images/caiman/sign/face/4/24x24.png"),
	    new FaceImage(5,"24x24(5)","http://dm.10086.cn/images/caiman/sign/face/5/24x24.png"),
	    new FaceImage(6,"24x24(6)","http://dm.10086.cn/images/caiman/sign/face/6/24x24.png"),
	    new FaceImage(7,"24x24(7)","http://dm.10086.cn/images/caiman/sign/face/7/24x24.png"),
	    new FaceImage(8,"24x24(8)","http://dm.10086.cn/images/caiman/sign/face/8/24x24.png"),
	    new FaceImage(9,"24x24(9)","http://dm.10086.cn/images/caiman/sign/face/9/24x24.png"),
	    new FaceImage(10,"24x24(10)","http://dm.10086.cn/images/caiman/sign/face/10/24x24.png"),
	    new FaceImage(11,"24x24(11)","http://dm.10086.cn/images/caiman/sign/face/11/24x24.png"),
	    new FaceImage(12,"24x24(12)","http://dm.10086.cn/images/caiman/sign/face/12/24x24.png"),
	    new FaceImage(13,"24x24(13)","http://dm.10086.cn/images/caiman/sign/face/13/24x24.png"),
	    new FaceImage(14,"24x24(14)","http://dm.10086.cn/images/caiman/sign/face/14/24x24.png"),
	    new FaceImage(15,"24x24(15)","http://dm.10086.cn/images/caiman/sign/face/15/24x24.png"),
	    new FaceImage(16,"24x24(16)","http://dm.10086.cn/images/caiman/sign/face/16/24x24.png"),
	    new FaceImage(17,"24x24(17)","http://dm.10086.cn/images/caiman/sign/face/17/24x24.png"),
	    new FaceImage(18,"24x24(18)","http://dm.10086.cn/images/caiman/sign/face/18/24x24.png"),
	    new FaceImage(19,"24x24(19)","http://dm.10086.cn/images/caiman/sign/face/19/24x24.png"),
	    new FaceImage(20,"24x24(20)","http://dm.10086.cn/images/caiman/sign/face/20/24x24.png"),
	    new FaceImage(21,"24x24(21)","http://dm.10086.cn/images/caiman/sign/face/21/24x24.png"),
	    new FaceImage(22,"24x24(22)","http://dm.10086.cn/images/caiman/sign/face/22/24x24.png"),
	    new FaceImage(23,"24x24(23)","http://dm.10086.cn/images/caiman/sign/face/23/24x24.png"),
	    new FaceImage(24,"24x24(24)","http://dm.10086.cn/images/caiman/sign/face/24/24x24.png")
	];
	
	
	function CAjax(url, params, sfn, efn, cfn) {
		var newUrl = (arguments.length >= 1) ? arguments[0] : null;
		var newParams = (arguments.length >= 2) ? arguments[1] : null;
		var newSfn = (arguments.length >= 3) && typeof sfn == 'function'  ? arguments[2] : null;
		var newEfn = (arguments.length >= 4) && typeof efn == 'function'  ? arguments[3] : null;
		var newCfn = (arguments.length >= 5) && typeof efn == 'function'  ? arguments[4] : null;
		
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
			ajaxPropery.complete = function(msg){
				if($.isFunction(newCfn))
					newCfn(msg);
			};
			$.ajax(ajaxPropery);
		}
	}
	
	
	function noPassCallbackFn(noPassKey){
		//(JSP中将contextPath放到全局中)，此处做初始化，防止contextPath为空
		var contextPath = window.contextPath || "";
		switch (noPassKey) {
		case 1001:
			$('body').load(contextPath+'/system/login');
			break;
		case 1002:
			$('body').load(contextPath+'/common/error.jsp');
			break;
		//权限异常
		case 1010:
			$('body').load(contextPath+'/error/competence-error.jsp');
			break;
		//内容异常
		case 1020:
			$('body').load(contextPath+'/error/content-error.jsp');
			break;
		default:
			break;
		}
	}
	
	//更新passkey
	function setPassKey(newServerId,newPassword,newTimestamp){
		if(newServerId != null && serviceId != null)
			serviceId  = newServerId;
		if(newPassword != null && password != null)
			password = newPassword;
		if(newTimestamp != null && timestamp != null)
			timestamp = newTimestamp;
	}
	
	function showBox(divId,left,top){
		if(divId && $('#'+divId).height() > 0){
			$("<div id='mask'></div>").addClass("mask")
		        .appendTo("body")
		        .fadeIn(200);
		
			if(left != null && top != null){
				$('#'+divId).css({
					'left' : left,
					'top' : top
				});
			}
			$('#'+divId).show();
			
		}
	}
	
	function hideBox(divId){
		if(divId && $('#'+divId).height() > 0){
			$('#mask').remove();
			$('#'+divId).hide();
			
		}
	}

	/* Nano Templates (Tomasz Mazur, Jacek Becela) */
	//模板替换引擎 nano
	function nano(template, data) {
	  return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
	    var keys = key.split("."), v = data[keys.shift()];
	    for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
	    return (typeof v !== "undefined" && v !== null) ? v : "";
	  });
	}
	

	//换行符替换
	function nl2br(t){
		return t.replace(/(\n\r|\r\n)/gi, '\n').replace(/(\n|\r)/gi, '<br/>');;
	}
	//换行符还原
	function br2nl(t){
		return t.replace(/<br\/>/gi, '\n');
	}

	//添加id方法，
	;(function($, undefined){
		//添加id方法
		var idSeed = 0,
			defaultPrefix = "jq-gen-";
		
		$.extend({
			/**
			 * 使用统一方式生成id，可最大程度上避免id重复
			 * @param prefix 前缀，缺省为"jq-gen-"
			 * @returns 生成的id
			 */
			id: function(prefix){
				return (prefix || defaultPrefix) + (++idSeed);
			}
		});
		$.fn.extend({
			/**
			 * 获取/创建元素id
			 * @param newId 若传入id，则使用该id重新设置元素，若为空，则返回元素id（若当前元素没有id，则自动生成id）
			 * @returns 元素id
			 */
			id: function(newId){
				if(undefined != newId){
					this.attr("id", newId);
				}else if(undefined == this.attr("id")){
					this.attr("id", $.id());
				}
				return this.attr("id");
			}
		});
	})(jQuery);
	
	//覆盖jq的param方法，以适用于页面传入集合时，后台能自动转换为list
	;(function($){
		//覆盖jq的param方法
		//方法取自jquery源码包,未做任何修改，只改动buildParams方法
		$.extend({
			param : function( a, traditional ) {
				var s = [],
					add = function( key, value ) {
						// If value is a function, invoke it and return its value
						value = jQuery.isFunction( value ) ? value() : value;
						s[ s.length ] = encodeURIComponent( key ) + "=" + encodeURIComponent( value );
					};

				// Set traditional to true for jQuery <= 1.3.2 behavior.
				if ( traditional === undefined ) {
					traditional = jQuery.ajaxSettings.traditional;
				}

				// If an array was passed in, assume that it is an array of form elements.
				if ( jQuery.isArray( a ) || ( a.jquery && !jQuery.isPlainObject( a ) ) ) {
					// Serialize the form elements
					jQuery.each( a, function() {
						add( this.name, this.value );
					});

				} else {
					// If traditional, encode the "old" way (the way 1.3.2 or older
					// did it), otherwise encode params recursively.
					for ( var prefix in a ) {
						buildParams( prefix, a[ prefix ], traditional, add );
					}
				}
				// Return the resulting serialization
				return s.join( "&" ).replace( r20, "+" );
			}
		});
		//拷自jquery源码包，只修改类型为obj时，将prefix[name] -> 修改为 prefix.name
		var r20 = /%20/g,
			rbracket = /\[\]$/;
		function buildParams( prefix, obj, traditional, add ) {
			if ( jQuery.isArray( obj ) ) {
				jQuery.each( obj, function( i, v ) {
					if ( traditional || rbracket.test( prefix ) ) {
						add( prefix, v );

					} else {
						//buildParams( prefix + "[" + ( typeof v === "object" ? i : "" ) + "]", v, traditional, add );
						//修改为v不为object时，直接使用prefix
						buildParams( prefix + ( typeof v === "object" ? ("[" + i + "]") : "" ) , v, traditional, add );
					}
				});
			} else if ( !traditional && jQuery.type( obj ) === "object" ) {
				// Serialize object item.
				for ( var name in obj ) {
					//将prefix[name] -> 修改为 prefix.name（适用于struts2）
					buildParams( prefix + "." + name, obj[ name ], traditional, add );
				}
			} else {
				// Serialize scalar item.
				add( prefix, obj );
			}
		}
	})(jQuery);