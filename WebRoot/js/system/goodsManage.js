
	var globalCategoryId='-1';
	var scrollTop = document.documentElement.scrollTop;
	
	var globalTimeCode='00';
	
	var globalfilePath="";//上传的图片临时文件地址
	
	var isUpdate = false;
	
	var addGoodsDivString="";
	var addCategoryDiv="";
	var updateCategoryDiv="";
	var goodsDetailDiv="";
	$(document).ready(function(){
		globalCategoryId =firstCategoryId;
		addGoodsDivString=$('#addGoodsDiv').html();
		addCategoryDivString=$('#addCategoryDiv').html();
		updateCategoryDivString=$('#updateCategoryDiv').html();
		goodsDetailDivString=$('#goodsDetailDiv').html();
		/**
		 * 调用插件
		 */
		if($.browser.msie && ($.browser.version == "6.0")){
			$.getScript("../js/uploadify2/jquery.uploadify.js",function(){
				
			});
		}
		else{
			$.getScript("../js/uploadify2/jquery.uploadify.min.js",function(){
				
			});
		}
	
	});
	
	/**
	 * 进入添加菜单界面
	 */
	function getAddGoodsView(){
		CAjax("goods-manage!getAddGoodsView",
				{
					data : {
						
					}
				},
				function(msg){
					if(msg.isSuccess){
						var html='';
						$.each(msg.categoryList,function(i,v){
							var categoryName="";
							if(getRealySize(v.categoryName)>10){
								categoryName=v.categoryName.substring(0,5)+"...";
							}else{
								categoryName=v.categoryName;
							}
							if(v.categoryId==globalCategoryId){
								html +='<option value="'+v.categoryId+'" selected="selected" title="'+v.categoryName+'">'+categoryName+'</option>';
							}else{
								html +='<option value="'+v.categoryId+'">'+categoryName+'</option>';
							}
						});
						$('#addGoodsCategorySelect').html(html);

						
						var dialog = $.dialog({
						    fixed: true,
						    resize:false,
						    opacity:0.5,
						    title:"添加菜单",
						    content: $('#addGoodsDiv').html(),
						    okVal:"添加",
						    cancelVal:"返回列表",
						    init: function(){
						    	$('#addGoodsDiv').html("");
						    	uploadifyInit("uploadImage","scimg_btn.jpg","addImg");
						    },
						    ok: function (here) {
						    	addGoods(function(){
						    		$('#addGoodsDiv').html(addGoodsDivString);
						        	dialog.close();
						        });
						        return false; //不关闭窗口
						    },
						    cancel: function(){
						    	$('#uploadImage').html("");
						    	$('#addGoodsDiv').html(addGoodsDivString);
						    }
						});
						
						$('#everydayRadio').click(function(){
							$("#onedayRadio").attr("checked",false);
							$('#fiveDaysSpan').hide();
							$('#fiveDaysSpan input').attr("checked",false);
						});
						$('#onedayRadio').click(function(){
							$("#everydayRadio").attr("checked",false);
							$('#fiveDaysSpan').show();
						});	
						$('input.sday').click(function(){
							if($(this).attr("checked")=="checked"){
								var flag=true;
								$.each($('input.sday'),function(i,v){
									if($(v).attr("checked")!="checked"){
										flag=false;
										return false;
									}
								});
								if(flag==true){
									$("#onedayRadio").attr("checked",false);
									$('#fiveDaysSpan').hide();
									$('#fiveDaysSpan input').attr("checked",false);
									$("#everydayRadio").attr("checked","checked");
								}
							}
						});
						if(globalTimeCode!=null&&globalTimeCode!=""){//带入时间
							if(globalTimeCode!="00"){
								$('#onedayRadio').click();
								$('#timeCode_'+globalTimeCode).click();
							}
						}
						$('#addGoodsDetaiDesc').focus(function(){
							if ($('#addGoodsDetaiDesc').html() =='菜品描述'){
								$('#addGoodsDetaiDesc').html("");
							}
						});
						$('#addGoodsDetaiDesc').blur(function(){
							if ($('#addGoodsDetaiDesc').html() ==''){
								$('#addGoodsDetaiDesc').html("菜品描述");
							}
						});
					}else{
						if(msg.isInBmTime){
							$.dialog.tips("该操作不可在订餐及统计时间段内操作，请稍后再试");
						}
					}
				}
			);
		
		
	}
	
	/**
	 * 添加菜单
	 */
	function addGoods(fn){
		var addGoodsName = $('#addGoodsName').attr("value").replace(/[ ]/g,"");
		if(addGoodsName==null||addGoodsName==''||addGoodsName=='菜名'){
			$.dialog.tips("请输入菜名");
			return;
		}
		if(getRealySize(addGoodsName)>18){
			$.dialog.tips("您输入的菜名过长，请重新输入。");
			return;
		}
		var addGoodPrice = $('#addGoodsPrice').attr("value").replace(/[ ]/g,"");
		if(addGoodsPrice==null||addGoodsPrice==''||addGoodsPrice=='价格，如：12'){
			$.dialog.tips("请输入价格");
			return;
		}
		var r = /^\+?[1-9][0-9]*$/;//正整数 
		if(!r.test(addGoodPrice)){
			$.dialog.tips("请输入正确的价格");
			return;
		}
		
		if(Number(addGoodPrice)>=1000){
			$.dialog.tips("您输入的价格过大，请重新输入！");
			return;
		}
		if(globalfilePath==""){
			$.dialog.tips("请上传菜单图片！");
			return;
		}
		var addGoodsCategoryId = $('#addGoodsCategorySelect option:selected').attr("value").replace(/[ ]/g,"");
		var timeCode="00";
		if($("#onedayRadio").attr("checked")=="checked"){
			timeCode="";
			$.each($('#fiveDaysSpan input'),function(i,v){
				if($(v).attr('checked')=="checked"){
					timeCode =timeCode+$(v).attr('value')+',';
				}
			});
			var lastIndex = timeCode.lastIndexOf(',');
		    if (lastIndex > -1) {
		          timeCode = timeCode.substring(0, lastIndex) + timeCode.substring(lastIndex + 1, timeCode.length);
		     }
			if(timeCode==""){
				$.dialog.tips("请选择菜单日期");
				return;
			}
		}
		var shopGoodsDesc = $('#addGoodsDetaiDesc').attr("value").replace(/[ ]/g,"");
		if(shopGoodsDesc=='菜品描述'){
			shopGoodsDesc='';
		}
		
		CAjax("goods-manage!addGoods",
				{
					data : {
						'shopGoods.shopGoodsName' : addGoodsName,
						'shopGoods.shopGoodsPrice' : addGoodPrice,
						'shopGoods.categoryGoodsId' : addGoodsCategoryId,
						'shopGoods.timeCode': timeCode,
						'shopGoods.shopGoodsDesc': shopGoodsDesc,
						'shopGoods.temporaryFilePath': globalfilePath

					}
				},
				function(msg){
					if(msg.isSuccess){
						$.dialog.tips("添加成功");
						globalfilePath="";
						fn();
						if(addGoodsCategoryId==globalCategoryId){
							getGoods(1);
						}else{
							setCategory(addGoodsCategoryId);
						}
						
					}else{
						if(msg.isInBmTime){
							$.dialog.tips("该操作不可在订餐及统计时间段内操作，请稍后再试");
							return;
						}
						if(msg.isGoodsNameDuplicate){
							$.dialog.tips("该菜单名已存在，请重新输入。");
							return;
						}
					}
				}
			);
	}
	
	function getAddCategoryView(){
		var dialog = $.dialog({
		    fixed: true,
		    resize:false,
		    opacity:0.5,
		    title:"添加分组",
		    content: $('#addCategoryDiv').html(),
		    okVal:"添加",
		    cancelVal:"返回列表",
		    init: function(){
		    	$('#addCategoryDiv').html("");
		    },
		    ok: function (here) {
		    	addCategory(function(){
		    		$('#addCategoryDiv').html(addCategoryDivString);
		        	dialog.close();
		        });
		        return false; //不关闭窗口
		    },
		    cancel: function(){
		    	$('#addCategoryDiv').html(addCategoryDivString);
		    }
		});
	}
	
	/**
	 * 增加分组
	 * @returns
	 */
	function addCategory(fn){
		var addCategoryName = $('#addCategoryName').attr("value").replace(/[ ]/g,"");
		if(addCategoryName==null||addCategoryName==''||addCategoryName=='分组名称'){
			$.dialog.tips("请输入分组名称");
			return;
		}
		CAjax("goods-manage!addCategory",
				{
					data : {
						'shopCategoryGoods.categoryName' : addCategoryName
						
					}
				},
				function(msg){
					if(msg.isSuccess){
						$.dialog.tips("添加成功");
						var html='';
						$.each(msg.categoryList,function(i,v){
							var categoryName="";
							if(getRealySize(v.categoryName)>10){
								categoryName=v.categoryName.substring(0,5)+"...";
							}else{
								categoryName=v.categoryName;
							}
							html +='<li onclick="setCategory('+v.categoryId+')" id="'+v.categoryId+'"><a href="javascript:setCategory('+v.categoryId+')" title="'+v.categoryName+'">'+categoryName+'</a></li>';
						});
						$('#tabs').html(html);
						fn();
						setCategory(globalCategoryId);	
					}else{
						if(msg.isInBmTime){
							$.dialog.tips("该操作不可在订餐及统计时间段内操作，请稍后再试");
							return;
						}
						if(msg.moreThanMaxNum){
							$.dialog.tips("菜单分组最多只能10个！");
							return;
						}
						if(msg.isGoodsCategoryNameDuplicate){
							$.dialog.tips("该分组名已存在，请重新输入。");
						}
					}
				}
			);
	}
	
	
	
	function getUpdateCategoryView(){
		var oldCategoryName = $('.thistab a').html();
		if(oldCategoryName!=null){
			$('#updateCategoryName').attr("value",oldCategoryName);
		}
		var dialog = $.dialog({
		    fixed: true,
		    resize:false,
		    opacity:0.5,
		    title:"修改分组",
		    content: $('#updateCategoryDiv').html(),
		    okVal:"修改",
		    cancelVal:"返回列表",
		    init: function(){
		    	$('#updateCategoryDiv').html("");
		    },
		    ok: function (here) {
		    	updateCategory(function(){
		    		$('#updateCategoryDiv').html(updateCategoryDivString);
		        	dialog.close();
		        });
		        return false; //不关闭窗口
		    },
		    cancel: function(){
		    	$('#updateCategoryDiv').html(updateCategoryDivString);
		    }
		});
	}
	/**
	 * 修改分组
	 * @returns
	 */
	function updateCategory(fn){
		var oldCategoryName = $('.thistab a').html();
		var newCategoryName = $('#updateCategoryName').attr("value").replace(/[ ]/g,"");
		if(oldCategoryName==newCategoryName){
			$.dialog.tips("更新成功");
			retrun;
		}
		CAjax("goods-manage!updateCategory",
				{
					data : {
						'shopCategoryGoods.categoryName' : newCategoryName,
						'shopCategoryGoods.categoryId' : globalCategoryId
					}
				},
				function(msg){
					if(msg.isSuccess){
						$.dialog.tips("更新成功");
						var html='';
						$.each(msg.categoryList,function(i,v){
							var categoryName="";
							if(getRealySize(v.categoryName)>10){
								categoryName=v.categoryName.substring(0,5)+"...";
							}else{
								categoryName=v.categoryName;
							}
							html +='<li onclick="setCategory('+v.categoryId+')" id="'+v.categoryId+'"><a href="javascript:setCategory('+v.categoryId+')" title="'+v.categoryName+'">'+categoryName+'</a></li>';
						});
						$('#tabs').html(html);
						fn();
						setCategory(globalCategoryId);	
					}else{
						if(msg.isInBmTime){
							$.dialog.tips("该操作不可在订餐及统计时间段内操作，请稍后再试");
							return;
						}
						if(msg.isGoodsCategoryNameDuplicate){
							$.dialog.tips("该分组名已存在，请重新输入。");
						}
					}
				}
			);
	}
	
	function getDelCategoryView(){
		var dialog = $.dialog({
		    fixed: true,
		    resize:false,
		    opacity:0.5,
		    title:"删除分组",
		    content: "确实要删除该分组吗?删除分组后，分组下的菜单将同步被删除。",
		    okVal:"删除",
		    cancelVal:"返回列表",
		    ok: function (here) {
		    	delCategory(function(){
		        	dialog.close();
		        });
		        return false; //不关闭窗口
		    },
		    cancel: function(){
		    	
		    }
		});
	}
	
	/**
	 * 删除分组
	 */
	function delCategory(fn){
		CAjax("goods-manage!delCategory",
				{
					data : {
						'shopCategoryGoods.categoryId' : globalCategoryId
						
					}
				},
				function(msg){
					if(msg.isSuccess){
						$.dialog.tips("删除成功");
						var html='';
						$.each(msg.categoryList,function(i,v){
							var categoryName="";
							if(getRealySize(v.categoryName)>10){
								categoryName=v.categoryName.substring(0,5)+"...";
							}else{
								categoryName=v.categoryName;
							}
							html +='<li onclick="setCategory('+v.categoryId+')" id="'+v.categoryId+'"><a href="javascript:setCategory('+v.categoryId+')" title="'+v.categoryName+'">'+categoryName+'</a></li>';
						});
						$('#tabs').html(html);
						fn();
						$('#tabs li:first').click();
					}else{
						if(msg.isInBmTime){
							$.dialog.tips("该操作不可在订餐及统计时间段内操作，请稍后再试");
							return;
						}
					}
				}
			);
		
	}
	
	function setCategory(categoryId){
		if($('#'+categoryId).hasClass("thistab")){
			return ;
		}else{
			$('#tabs li').removeClass("thistab");
			$('#'+categoryId).addClass("thistab");
			globalCategoryId = categoryId;
//			globalTimeCode='00';
			getGoods(1);
		}
	}
	
	function setTimeCode(timeCode){
		if($('#timeCode_'+timeCode).css('color')=="rgb(255, 0, 0)"){
			return ;
		}else{
			$('#timeDiv a').css('color','');
			$('#timeCode_'+timeCode).css('color','#FF0000');
			globalTimeCode=timeCode;
			getGoods(1);
		}
	}
	function getGoods(pageNo){
		CAjax("goods-manage!queryGoodsByPageAjax",
				{
					data : {
						currentPage : pageNo,
						categoryIdArr : globalCategoryId=='-1'?'':globalCategoryId,
					    timeCode : globalTimeCode
					}
				},
				function(msg){
					if(msg.isSuccess){
						var html='';
						$.each(msg.goodsResponseBean.resultList,function(i,v){
							html+='<div class="cd_list_box"><span class="cd_listL" style="cursor:pointer" onclick="getGoodsDetail('+v.shopGoodsId+')">'+v.shopGoodsName+'</span><span class="cd_listR">￥'+v.shopGoodsPrice+'</span></div>';
						});
						$('#goodsView').html(html);
					}
				}
			);
	}
	/**
	 * 获取菜单详情
	 * @param shopGoodId
	 * @returns
	 */
	
	function getGoodsDetail(shopGoodId){
		if(shopGoodId!=null&&shopGoodId!=''){
			CAjax("goods-manage!getGoodsDetail",
					{
						data : {
							shopGoodId : shopGoodId
						}
					},
					function(msg){
						if(msg.isSuccess){
							if(msg.goodsResponseBean!=null&&msg.goodsResponseBean.shopGoods!=null){
								$('#goodsDetailImg').attr('src',msg.goodsResponseBean.shopGoods.temporaryFilePath.replace(/\\/g, "/"));
								$('#goodsDetaiName').attr('value',msg.goodsResponseBean.shopGoods.shopGoodsName);
								$('#goodsDetaiPrice').attr('value',msg.goodsResponseBean.shopGoods.shopGoodsPrice);
								var html='';
								$.each(msg.categoryList,function(i,v){
									var categoryName="";
									if(getRealySize(v.categoryName)>10){
										categoryName=v.categoryName.substring(0,5)+"...";
									}else{
										categoryName=v.categoryName;
									}
									if(v.categoryId==globalCategoryId){
										html +='<option value="'+v.categoryId+'" selected="selected" title="'+v.categoryName+'">'+categoryName+'</option>';
									}else{
										html +='<option value="'+v.categoryId+'">'+categoryName+'</option>';
									}
								});
								$('#goodsDetaiCategorySelect').html(html);
								$('#goodsDetaiDesc').html(msg.goodsResponseBean.shopGoods.shopGoodsDesc);
								var dialog = $.dialog({
								    fixed: false,
								    resize:true,
								    opacity:0.5,
								    title:"菜单详情",
								    content: $('#goodsDetailDiv').html(),
								    okVal:"修改",
								    cancelVal:"返回列表",
								    button:[{name: '删除', callback: function(){
								    	$('#goodsDetailDiv').html(goodsDetailDivString);
								    	globalfilePath="";
								    	$.dialog.confirm('您确定更要删除该菜单吗？', function () {
								    	    delGoods(shopGoodId,function(){
								    	    	dialog.close();
								    	    });
								    	}, function () {
								    	});
								    }}],
								    init: function(){
								    	$('#goodsDetailDiv').html("");
								    },
								    ok: function (here) {
								    	updateGoods(shopGoodId,function(){
								    		$('#goodsDetailDiv').html(goodsDetailDivString);
								        	dialog.close();
								        });
								        return false; //不关闭窗口
								    },
								    cancel: function(){
								    	$('#goodsDetailDiv').html(goodsDetailDivString);
								    	globalfilePath="";
								    }
								});
								uploadifyInit("uploadImage2","xgimg_btn.jpg","goodsDetailImg");
							}else{
								$.dialog.tips('该商品已被删除或不存在!');
							}
						}
					}
				);
		}
	}
	/**
	 * 修改菜单
	 * @returns
	 */
	function updateGoods(goodsId,fn){	
		if(goodsId==null||goodsId==""){
			return;
		}
		var goodsDetaiName = $('#goodsDetaiName').attr("value").replace(/[ ]/g,"");
		if(goodsDetaiName==null||goodsDetaiName==''){
			$.dialog.tips("请输入菜名");
			return;
		}
		if(getRealySize(goodsDetaiName)>18){
			$.dialog.tips("您输入的菜名过长，请重新输入。");
			return;
		}
		var goodsDetaiPrice = $('#goodsDetaiPrice').attr("value").replace(/[ ]/g,"");
		if(goodsDetaiPrice==null||goodsDetaiPrice==''){
			$.dialog.tips("请输入价格");
			return;
		}
		var r = /^\+?[1-9][0-9]*$/;//正整数 
		if(!r.test(goodsDetaiPrice)){
			$.dialog.tips("请输入正确的价格");
			return;
		}
		if(Number(goodsDetaiPrice)>=1000){
			$.dialog.tips("您输入的价格过大，请重新输入！");
			return;
		}
		var goodsDetaiCategoryId = $('#goodsDetaiCategorySelect option:selected').attr("value").replace(/[ ]/g,"");
		var goodsDetaiDesc = $('#goodsDetaiDesc').attr("value").replace(/[ ]/g,"");
		
		CAjax("goods-manage!updateGoods",
				{
					data : {
						'shopGoods.shopGoodsId' : goodsId,
						'shopGoods.shopGoodsName' : goodsDetaiName,
						'shopGoods.shopGoodsPrice' : goodsDetaiPrice,
						'shopGoods.categoryGoodsId' : goodsDetaiCategoryId,
						'shopGoods.shopGoodsDesc' : goodsDetaiDesc,
						'shopGoods.temporaryFilePath': globalfilePath
					}
				},
				function(msg){
					if(msg.isSuccess){
						$.dialog.tips("修改成功");
						globalfilePath="";
						fn();
						getGoods(1);
					}else{
						if(msg.isInBmTime){
							$.dialog.tips("该操作不可在订餐及统计时间段内操作，请稍后再试");
							return;
						}
						if(msg.isGoodsNameDuplicate){
							$.dialog.tips("该菜单名已存在，请重新输入。");
						}
					}
				}
			);
	}
	
	
	function getDelGoodsView(goodsId){
		var dialog = $.dialog({
		    fixed: true,
		    resize:false,
		    opacity:0.5,
		    title:"删除菜单",
		    content: "确实要删除该菜单吗。",
		    okVal:"删除",
		    cancelVal:"返回菜单",
		    ok: function (here) {
		    	delGoods(goodsId,function(){
		        	dialog.close();
		        });
		        return false; //不关闭窗口
		    },
		    cancel: function(){
		    	
		    }
		});
	}
	/**
	 * 删除菜单
	 * @returns
	 */
	function delGoods(goodsId,fn){
		CAjax("goods-manage!delGoods",
				{
					data : {
						'shopGoods.shopGoodsId' : goodsId
					}
				},
				function(msg){
					if(msg.isSuccess){
						$.dialog.tips("删除成功");
						fn();
						getGoods(1);
					}else{
						if(msg.isInBmTime){
							$.dialog.tips("该操作不可在订餐及统计时间段内操作，请稍后再试");
							return;
						}
					}
				}
		);
		
	}
	
	/**
	 * 文本上传插件添加
	 */
	function uploadifyInit(id,buttonImageName,imgId){
		$("#"+id).uploadify({
			//开启调试
			'debug' : false,
			//是否自动上传
			'auto':true,
			//超时时间
			'successTimeout':5000,
			'formData':{
//					'eid':eid,
//					'password':password,
//					'serviceId':serviceId,
//					'timestamp':timestamp
	        },
	        overrideEvents:['onSelectError', 'onDialogClose'],
	        //flash
	        'swf': path+'/js/uploadify2/uploadify.swf?ver=' + Math.random(),
	        //不执行默认的onSelect事件
//		        'overrideEvents' : ['onDialogClose'],
	      	//文件选择后的容器ID
	        'queueID':'fileQueue1',
	        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
	        'fileObjName':'upload',
	        //上传处理程序
	        'uploader':path+'/system/goods-manage!imgFileUpload?ver=' + Math.random(),
	        //浏览按钮的背景图片路径
	        'buttonImage':path+'/images/button/'+buttonImageName,
	        //浏览按钮的宽度
	         'width':'116',
	        //浏览按钮的高度
	        'height':'28',
	        //expressInstall.swf文件的路径
//		        'expressInstall':path+'/js/uploadify2/expressInstall.swf?ver=' + Math.random(),
	        //在浏览窗口底部的文件类型下拉菜单中显示的文本
	        'fileTypeDesc':'支持的格式：',
	        //允许上传的文件后缀
	        'fileTypeExts':'*.jpg',
	        //上传文件的大小限制
	        'fileSizeLimit':'3MB',
	        //上传数量
	        'queueSizeLimit' : 1,
	        'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
	             //有时候上传进度什么想自己个性化控制，可以利用这个方法
	             //使用方法见官方说明
	        },
	        //选择上传文件后调用
	        'onSelect' : function(file) {
	        	isUpdate = true;
	        },
	        'onDialogClose' : function(file) {
	        	isUpdate = false;
	        },
	        //返回一个错误，选择文件的时候触发
	        'onSelectError':function(file, errorCode, errorMsg){
	            switch(errorCode) {
	                case -100:
	                    alert("上传的文件数量已经超出系统限制的1个文件！");
	                    break;
	                case -110:
	                    alert("图片 ["+file.name+"] 大小超出系统限制的3M大小！");
	                    break;
	                case -120:
	                    alert("图片 ["+file.name+"] 大小异常！");
	                    break;
	                case -130:
	                    alert("图片 ["+file.name+"] 类型不正确！");
	                    break;
	            }
	            isUpdate = false;
	        },
	        //检测FLASH失败调用
	        'onFallback':function(){
	            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
	            isUpdate = false;
	        },
	        //上传到服务器，服务器返回相应信息到data里
	        'onUploadSuccess':function(file, data, response){
	           var result = $.parseJSON(data);
	           if(result.isSuccess){
	        	   globalfilePath = result.filePath;
	        	   isUpdate = true;
	        	   if(result.accessFilePath!=null&&result.accessFilePath!=""){
	        		   $('#'+imgId).attr('src', result.accessFilePath);
	        		   if($('#'+imgId).is(":hidden")){
	        			   $('#'+imgId).show();
	        		   }
	        	   }
	           }
	        }
		});
	}
	
	function getRealySize(str){
		var realLength = 0, len = str.length, charCode = -1;
	    for (var i = 0; i < len; i++) {
	        charCode = str.charCodeAt(i);
	        if (charCode >= 0 && charCode <= 128) realLength += 1;
	        else realLength += 2;
	    }
	    return realLength;
	}