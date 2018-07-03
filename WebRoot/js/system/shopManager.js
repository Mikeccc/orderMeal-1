
var globalfilePath="";

$(document).ready(function(){
	/**
		 * 调用插件
		 */
		if($.browser.msie && ($.browser.version == "6.0")){
			$.getScript(path+"/js/uploadify2/jquery.uploadify.js",function(){
				
			});
		}
		else{
			$.getScript(path+"/js/uploadify2/jquery.uploadify.min.js",function(){
				
			});
		}
	});

//tab
//调用方法如下：
//$.jqtab("#tabs","#tab_conbox","click");
jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
	$(tab_conbox).find("li").hide();
	$(tabtit).find("li:first").addClass("thistab").show(); 
	$(tab_conbox).find("li:first").show();

	$(tabtit).find("li").bind(shijian,function(){
	    $(this).addClass("thistab").siblings("li").removeClass("thistab"); 
		var activeindex = $(tabtit).find("li").index(this);
		$(tab_conbox).children().eq(activeindex).show().siblings().hide();
		return false;
	});
};
//按店铺和分类加载菜品
function loadGoods(pageNo, categoryId, shopId, currentTab){
	var $currentTab = $(currentTab);
	
	//各自的tab存各自的参数
	$currentTab.data("params", {
        'shopCategoryGoods.categoryId' : categoryId,
        'shopInfo.shopId' : shopId
    });
	
    //将参数copy到分页条中，重新渲染
	var $menuPagebar = $('.thistab').parent().parent().find(".pagebar");
	
	$menuPagebar.data('currentpage', $currentTab.data('currentpage'));
	$menuPagebar.data('totalpages', $currentTab.data('totalpages'));
	$menuPagebar.data('totalrecords', $currentTab.data('totalrecords'));
	$menuPagebar.data('params', $currentTab.data('params'));
	
	//重新render
	$menuPagebar.renderPagebar(goPage);
	
	//在tab中存入loadflag,若当前tab 已加载过数据，则不再重新加载
	if($currentTab.data("loadflag")){
		return;
	}
	//初始化数据
	$currentTab.data('currentpage', 1);
	$currentTab.data('totalpages', 1);
	$currentTab.data('totalrecords', 0);
	
	//初始加载第1页数据
	loadPage($menuPagebar, 1);
	return;

	
    CAjax(path+"/system/shop-manager!queryGoodsByPageAjax",
        {
            data : {
                'currentPage' : pageNo,
                'shopCategoryGoods.categoryId' : categoryId,
                'shopInfo.shopId' : shopId
            }
        },
        function(json){
            if(json.isSuccess){
            	//成功加载后（含没有数据），窗口关闭前，不再重新加载 
            	$currentTab.data("loadflag", true);
            	
            	//根据当前的 tab，获取tab对应的li(内容定义在<p>下)
            	var itemClass = $currentTab.data("item");
            	var $content = $currentTab.parents('div._shopWindowContent').find('li.'+itemClass+" p");
            	
                var list = json.goodsResponseBean.resultList;
            	if(!list || list.length < 1){
            		$content.html('当前分类下还没有任何菜品！');
                	return;
                }
                
                var html=[];
                $.each(list,function(i,v){
                    html.push(nano('<div class="cd_list_box"><span class="cd_listL">{shopGoodsName}</span><span class="cd_listR">￥{shopGoodsPrice}</span></div>', v));
                });
                $content.html(html.join(""));
            }
        }
    );
}


//显示菜单
function showShopGoods(obj){
	var $btn = $(obj);
    var shopId =  $btn.attr('id'),
    	shopName = $btn.parents("tr.row_data").find("._field[name='shopName']").text();

    //检索分类
	CAjax(path+"/system/shop-manager!queryCategoryAjax",{data : {'shopInfo.shopId' : shopId}},function(json){
        if(json.isSuccess){
            var list = json.categoryResponseBean.resultList;
            if(!list || list.length < 1){
            	$.dialog.alert("店铺【"+shopName+"】还没有任何菜单分类！");
            	return;
            }

            var html=[], contentLi=[];
            $.each(list, function(i,v){
            	v.shopId = shopId; //用于模板替换
            	v.itemClass = "item_"+i;
                html.push(nano('<li><a href="#" onclick="loadGoods(1, \'{categoryId}\', \'{shopId}\', this)" data-item="{itemClass}">{categoryName}</a></li>', v));
                contentLi.push(nano('<li class="tab_con {itemClass}"><p></p></li>', v)); //按分类添加等量的内容li              
            });
            
            //直接模板内容
            var tpl = $('div._shopWindowTpl').html();
            tpl = nano(tpl, {
            	shopName: shopName,
            	categorys: html.join(""),
            	tab_conbox: contentLi.join("")
            });
            
        	$.dialog({
        		title:"查看菜单",
        	    width: '100%',
        	    height: '100%',
        	    left: '0%',
        	    top: '0%',
        	    fixed: true,
        	    resize: false,
        	    drag: false,
        	    padding:"0",
        	    content: tpl,
        	    init: function(){
        	    	var $content = this.DOM.content;
        	    	//设置全屏样式，使内容最大
        	    	$content.css({width: "100%", height:"100%"});
        	    	//初始化tab
        	    	$.jqtab(".tabs",".tab_conbox","click");
        	    	$content.find('.thistab a').click(); //初始查询分类数据
        	    }
            });
        }else{
			$.dialog.error(json.errorMsg || "菜单分类加载失败！");
        }
    });
}

//显示当日订单
function showShopOrder(obj){
	var $btn = $(obj);
	var shopId =  $btn.attr('id'),
		shopName = $btn.parents("tr.row_data").find("._field[name='shopName']").text();
    //检索分类
	CAjax(path+"/system/shop-manager!queryCategoryAjax",{data : {'shopInfo.shopId' : shopId}},function(json){
        if(json.isSuccess){
            var list = json.categoryResponseBean.resultList;
            if(!list || list.length < 1){
            	$.dialog.alert("店铺【"+shopName+"】尚未上传任何菜单，因此当日无订单。");
            	return;
            }else{
            	window.parent.frames[3].location=path+"/system/order-manage!orderCountByShopId?requestShopId="+shopId;
            }
        }
	});
     
}

//添加供应商请求
function addShops(formData, fn){

    var loading = $.dialog({title: false, cancel:false});
    CAjax(path+"/system/shop-manager!addOrUpdateShop",
			{
				data : {
					shopInfo: formData, 
					fileAccessUrl: formData.fileAccessUrl
				}
			},
			function(json){
				loading.close();
				loadPage($('#fenye'), $('#fenye').data("currentpage"));
				if(false == json.isSuccess){
					var msg = json.errorMsg || "供应商维护失败！";
					if(json.code){
						msg += " (error code:"+json.code + ")";
					}
					$.dialog.error(msg);
					return;
				}
				fn();
				$.dialog.tips("供应商维护成功！");
			},
			function(json){},
			function(json){loading.close();} //complete
		);
    return false; //不关闭窗口
}

function addWin(){

	var dialog = $.dialog({
	    fixed: true,
	    resize:false,
	    opacity: .1,
	    title:"供应商添加",
	    content: $('._editForm').html(),
	    okVal:"添加",
	    cancelVal:"返回列表",
	    init: function(){
	    	var $content = this.DOM.content;
	    	//初始化
			uploadifyInit($content.find(":file").id(), $content.find("img").id(), $content);
	    	addValidate($content.find("form"));
	    },
	    ok: function (here) {
	    	//若表单验证不成功，return false，不关闭窗口即可
	    	if(!this.DOM.content.find("form").valid()){
	    		return false;
	    	}

	    	var formData = {};
	    	//解析数据
	        this.DOM.content.find("input").each(function(){
	        	var $this = $(this);
	        	formData[$this.attr('name')]=$.trim($this.val());
	        });
	        
	        addShops(formData, function(){
	        	dialog.close();
	        });
	        return false; //不关闭窗口
	    },
	    cancel: true
	});
}

function editWin(){
	var $selections = $(".gys_table [name='chkBox']:checked");
	if($selections.length < 1){
		$.dialog.alert("请选择待编辑的供应商！");
		return;
	}else if($selections.length > 1){
		$.dialog.alert("一次只允许编辑一条供应商信息！");
		return;
	}

	var data = {
		shopId: $selections[0].value
	};
	$selections.parent().parent().find("._field").each(function(i, li){
		var $this = $(this), 
			value = ("input" == this.nodeName.toLowerCase() ? $this.val() : $this.data("value"));
		data[$this.attr('name')] = value;
	});
	
	var dialog = $.dialog({
	    fixed: true,
	    resize:false,
	    opacity: .1,
	    title:"供应商编辑",
	    content: $('div._editForm').html(),
	    okVal:"修改",
	    cancelVal:"返回列表",
	    init: function(){
	    	//初始化表单数据
	        var $content = this.DOM.content;
	    	//初始化
			uploadifyInit($content.find(":file").id(), $content.find("img").id(), $content);
			$content.find("img").attr('src', data.fileAccessUrl.replace(/\\/g, "/")); //FF下,url路径只认识"/"
	        $.each(data, function(p, v){
	        	$content.find("[name='"+p+"']").val(v);
	        });
	        //未修改图片时，不传该值
	        $content.find("[name='fileAccessUrl']").val("");
	    	//初始化验证
	    	addValidate(this.DOM.content.find("form"));
	    },
	    ok: function (here) {
	    	//若表单验证不成功，return false，不关闭窗口即可
	    	if(!this.DOM.content.find("form").valid()){
	    		return false;
	    	}
	    	
	    	var formData = {};
	    	//解析数据
	        this.DOM.content.find("input").each(function(){
	        	var $this = $(this);
	        	formData[$this.attr('name')]=$.trim($this.val());
	        });
	        
	        addShops(formData, function(){
	        	dialog.close();
	        });
	        return false; //不关闭窗口
	    },
	    cancel: true
	});
}

function deleteWin(){
	
	var $selections = $(".gys_table [name='chkBox']:checked");
	if($selections.length < 1){
		$.dialog.alert("请选择待删除的供应商！");
		return;
	}
	
	$.dialog.confirm('确定要删除选中的供应商？', function () {
		
		var ids=[], shopPhone=[];
		$selections.each(function(){
			var $this = $(this);
			ids.push($this.val());
			shopPhone.push($this.attr("phoneNum"));
		});
		
		var loading = $.dialog({title: false, cancel:false});
		
		CAjax(path + "/system/shop-manager!deleteShop",
				{
					data: {id: ids.join(","), shopPhone: shopPhone.join(",")}
				},
				function(json){
					loading.close();
					loadPage($('#fenye'), $('#fenye').data("currentpage"));
					
					if(false == json.isSuccess){
						var msg = json.errorMsg || "供应商信息删除失败！";
						if(json.code){
							msg += " (error code:"+json.code + ")";
						}
						$.dialog.error(msg);
						return;
					}
					$.dialog.tips("供应商信息删除成功！");
				},
				function(json){
					loading.close();
					$.dialog.error("数据请求失败！\n状态码：["+json.status+"]");
				}
			);
		
		});
}

//暂停/启用
function changeStatus(status, shopId, input){
	var operateName = input.value;
	var data = {};
	$(input).parents("tr.row_data").find("td._field").each(function(){
		var $this = $(this);
		data[$this.attr("name")] = $this.text();
	});

	$.dialog.confirm(nano("确认<font color='red'>{o}</font>供应商【{name}】吗？", {o: operateName, name: data.shopName}), function(){

		var loading = $.dialog({title: false, cancel:false});
		
		CAjax(path + "/system/shop-manager!toggleShop",
				{
					data: {id: shopId, shopPhone: data.shopPhoneNumber, shopStatus: status}
				},
				function(json){
					loading.close();
					//刷新列表(不论成功、失败)
					loadPage($('#fenye'), 1);
					
					if(false == json.isSuccess){
						var msg = json.errorMsg || "供应商"+operateName+"失败！";
						if(json.code){
							msg += " (error code:"+json.code + ")";
						}
						$.dialog.error(msg);
						return;
					}
					$.dialog.tips("供应商"+operateName+"成功！");
				},
				function(json){
					loading.close();
					$.dialog.error(json.errorMsg || "数据请求失败！请重新登录！");//\n状态码：["+json.status+"]");
				}
			);
		
		});
}

//为对象添加验证
function addValidate($form){
	$form.validate({
		rules:{
			shopName: {
	            required:true
	        },
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
	            email: true,
	            validator: function(el){
	            	//不允许包含双字节，比如中文、全角/中文符号（逗号等）
	            	return !/[\u0391-\uFFE5]/g.test($(el).val());
	            }
	        },
	        shopAddress: {  
	            required:true,
	            maxLength2:100
	        }
		},
		messages: {
			shopName: {
	            required: "请填写店铺名称"
			},
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
	            email: "Email格式不正确",
	            validator: "不允许包含中文/全角符号"
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

//系统初始化
$(document).ready(function(){
	//设置全选功能
	$(".gys_table [name='selectAll']:checkbox").change(function(){
		//分页会改变html结构，所以每次都需要重新去查dom
		$(".gys_table [name='chkBox']:checkbox").attr("checked", this.checked)
	});
	//设置复选框反向设置全选框功能
	$(".gys_table [name='chkBox']:checkbox").live("change", function(){
		var total = $(".gys_table [name='chkBox']:checkbox").length,
			checked = $(".gys_table [name='chkBox']:checked").length;
		$(".gys_table [name='selectAll']:checkbox").attr("checked", total == checked);
	});
	
	//设置分页
	var $pagebar = $("#fenye").renderPagebar(goPage);
	
	//设置添加、修改、删除功能
	$(".ny_top [name='addBtn']").click(addWin);
	$(".ny_top [name='editBtn']").click(editWin);
	$(".ny_top [name='delBtn']").click(deleteWin);
	
	//设置查询功能
	var $searchContainer = $(".ny_table"),
		$inputs = $searchContainer.find("input");
	$searchContainer.find("[name='searchBtn']").click(function(){
		var searchData = {};
		$inputs.each(function(){
			var $this = $(this);
			searchData[$this.attr("name")] = $.trim($this.val());
		});
		$pagebar.data("params", {shopInfo:searchData});
		//检索列表数据
		loadPage($pagebar, 1);
	});
	
});

//-------------------------------------以下为分页处理--------------------------------------
//----------------------------------------简易分页处理---------------------------------------------
;(function($, undefined){

	function enable(btn, fn, owner){
		btn.addClass("enabled_link");
		//先移除已有事件（防止一直重复绑定），若无会自动忽略
		btn.unbind("click", fn);
		btn.bind("click", {owner: owner}, fn);
	}
	function disable(btn, fn, owner){
		btn.removeClass("enabled_link");
		btn.unbind("click", fn);
	}
	function enableAll(btns, fn, owner){
		$.each(btns, function(i, b){
			enable(b, fn, owner);
		});
	}
	
	function doRender(owner, fn) {
		var page = owner.data('currentpage');
			totalPages = owner.data('totalpages');
			
		var firstPageBtn = owner.find("[name='firstPage']"),
			prePageBtn = owner.find("[name='prePage']"),
			nextPageBtn = owner.find("[name='nextPage']"),
			lastPageBtn = owner.find("[name='lastPage']");
		
		enableAll([firstPageBtn, prePageBtn, nextPageBtn, lastPageBtn], fn, owner);
		if(page <= 1){
			page = 1;
			disable(firstPageBtn, fn, owner);
			disable(prePageBtn, fn, owner);
		}
		if(page >= totalPages){
			page = totalPages;
			disable(nextPageBtn, fn, owner);
			disable(lastPageBtn, fn, owner);
		}
		this.page = page;
	}
	
//		var defaultOpt = {
//			//
//		}
//	
//		$.SimplePager = function($owner, opt){
//			//
//		}

	$.fn.renderPagebar = function(fn) {
	    var $this = (this);
	    doRender($this, fn);
	    
	    $this.find("[name='currentPage']").text($this.data('currentpage'));
	    $this.find("[name='totalPages']").text($this.data('totalpages'));
	    $this.find("[name='totalRecords']").text($this.data('totalrecords'));
	    $this.find("[name='goPage']").val('');
	    $this.find(".GO_btn").unbind("click", fn);
	    $this.find(".GO_btn").bind("click", {owner: $this}, fn);

	    return $this;
	};
})(jQuery);
//----------------------------------------简易分页处理-------------------------------------------end

//-------------------------------------分页----------------------------------------------------

var tpl = '<tr class="row_data">'+
		    '<td><input name="chkBox" type="checkbox" value="{shopId}" shopStatus="{shopStatus}"  phoneNum="{shopPhoneNumber}" id="{shopId}"/></td>'+
		    '<td class="_field" name="shopName" data-value="{shopName}">{shopName}</td>'+
		    '<td class="_field" name="shopPhoneNumber" data-value="{shopPhoneNumber}">{shopPhoneNumber}</td>'+
		    '<td class="_field" name="shopQq" data-value="{shopQq}">{shopQq}</td>'+
		    '<td class="_field" name="shopEmail" data-value="{shopEmail}">{shopEmail}</td>'+
		    '<td class="_field" name="shopAddress" data-value="{shopAddress}">{shopAddressText}</td>'+
		    '<td ><a id="{shopId}" onclick="showShopGoods(this)" style="cursor: pointer;color:blue;">点我查看菜单</a></td>'+
		    '<td ><a id="{shopId}" onclick="showShopOrder(this)" style="cursor: pointer;color:blue;">查看当日订单</a></td>'+
		    '<td><input type="button" onclick="changeStatus(\'{shopStatus}\', \'{shopId}\', this)" value="{shopStatusText}" />'+ //此处value为shopStatusText
		         '<input type="hidden" class="_field" name="shopLogoFileId" value="{shopLogoFileId}" />'+
		         '<input type="hidden" class="_field" name="fileAccessUrl" value="{sysFile.fileAccessUrl}" />'+
		    '</td>'+
		  '</tr>';

function loadPage(owner, page){

    var loading = $.dialog({title: false, cancel:false});
	params = owner.data("params") || {};
	
	CAjax(owner.data("url")+'?ver=' + Math.random(),
			{
				data: $.extend(params, {currentPage : page})
			},
			function(json){
				loading.close();
				if(false == json.isSuccess){
					var msg = json.errorMsg || "列表数据检索失败！";
					if(json.code){
						msg += " (error code:"+json.code + ")";
					}
					$.dialog.error(msg);
					return;
				}
				
				//每次刷新列表后，去除全选框的打勾（若有），若有优化，应该为loadPage创建回调，并在回调处理其它与数据加载无关的操作
				$(".gys_table [name='selectAll']:checkbox").attr("checked", false);
				
				//数据处理
				var data = loadPage[owner.data("renderer")](json);
				
				//更新分页信息
				owner.data('currentpage', data.currentPage);
				owner.data('totalpages', data.totalPages);
				owner.data('totalrecords', data.totalRecords);
				//重新render
				owner.renderPagebar(goPage);
			},
			function(json){
				loading.close();
				$.dialog.error("数据请求失败！\n状态码：["+json.status+"]");
			}
		);
}
//店铺数据渲染
loadPage.shopRenderer = function(json){
	//加载
	var data = json.responseBean;
	//列表数据
	var resultList = data.resultList;

	var html = [];
	$.each(resultList, function(i, u){
		if("00" == u.shopStatus){
			u.shopStatusText = "暂停";
		}else if("01" == u.shopStatus){
			u.shopStatusText = "启动";
		}
		//id为null时，后台返回0，此处过滤
		if("0" == u.shopLogoFileId){
			u.shopLogoFileId = "";
		}
		
		//地址文本内容截取处理
		var shopAddressText = u.shopAddress;
		if(shopAddressText.length > 20){
			shopAddressText = shopAddressText.substring(0, 20) + "...";
		}
		u.shopAddressText = shopAddressText;
		
		//若图片对象不存在，则初始化一个空对象，防止模板替换异常
		if(!u.sysFile){
			u.sysFile={};
		}
		html.push(nano(tpl, u));
	});
	//清除原有数据，添加新数据
	$('.gys_table .row_data').remove();
	$(".gys_table").append(html.join(""));
	
	return data;
}
//菜单数据渲染
loadPage.menuRenderer = function(json){

	var $currentTab = $("li.thistab a");
	
	//成功加载后（含没有数据），窗口关闭前，不再重新加载 
	$currentTab.data("loadflag", true);
	
	//根据当前的 tab，获取tab对应的li(内容定义在<p>下)
	var itemClass = $currentTab.data("item");
	var $content = $currentTab.parents('div._shopWindowContent').find('li.'+itemClass+" p");
	
	var data = json.goodsResponseBean;
    var list = data.resultList;
	if(!list || list.length < 1){
		$content.html('当前分类下还没有任何菜品！');
    	return data;
    }
    
	//更新html内容
    var html=[];
    $.each(list,function(i,v){
        html.push(nano('<div class="cd_list_box"><span class="cd_listL">{shopGoodsName}</span><span class="cd_listR">￥{shopGoodsPrice}</span></div>', v));
    });
    $content.html(html.join(""));
    
	//更新分页信息
    $currentTab.data('currentpage', data.currentPage);
    $currentTab.data('totalpages', data.totalPages);
    $currentTab.data('totalrecords', data.totalRecords);
	
	return data;
}

function goPage(e){
	var owner = e.data.owner;
	var name = $(this).attr('name');
	var currentPage = owner.data('currentpage'),
		totalpages = owner.data('totalpages');
	var toPage = 1;
	switch(name){
		case 'firstPage':
			toPage = 1;
			break;
		case 'prePage':
			toPage = currentPage - 1;
			break;
		case 'nextPage':
			toPage = currentPage + 1;
			break;
		case 'lastPage':
			toPage = totalpages;
			break;
		case 'gobtn':
			var $goPage = owner.find("[name='goPage']");
			var pageNum = $goPage.val();
			//若录入不是数字，则清空go框
	    	if(!pageNum || !/^\d+$/.test(pageNum)){
	    		$goPage.val('');
	    		return;
	    	}
			toPage = pageNum;
			break;
	}
	//将跳转页数的值修正到[0, totalpages]内
	toPage = Math.max(toPage, 1);
	toPage = Math.min(toPage, totalpages);
	//目的页数与当前页数相同，则不执行请求
	if(toPage == currentPage){
		//若是单击go，则清空跳转框
		if('gobtn' == name){
			owner.find("[name='goPage']").val('');
		}
		return;
	}
	loadPage(owner, toPage);
}
//-------------------------------------分页----------------------------------------------------end





/**
 * 文本上传插件添加
 */
function uploadifyInit(id, imgId, $content){
	$("#"+id).uploadify({
		//开启调试
		'debug' : false,
		//是否自动上传
		'auto':true,
		//超时时间
		'successTimeout':5000,
		'formData':{
//				'eid':eid,
//				'password':password,
//				'serviceId':serviceId,
//				'timestamp':timestamp
        },
        overrideEvents:['onSelectError', 'onDialogClose'],
        //flash
        'swf': path+'/js/uploadify2/uploadify.swf?ver=' + Math.random(),
        //不执行默认的onSelect事件
//	        'overrideEvents' : ['onDialogClose'],
      	//文件选择后的容器ID
        'queueID':'fileQueue1',
        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
        'fileObjName':'upload',
        //上传处理程序
        'uploader':path+'/system/shop-manager!imageUpload?ver=' + Math.random(),
        //浏览按钮的背景图片路径
        'buttonImage':path+'/images/button/scimg_btn.jpg',
        //浏览按钮的宽度
         'width':'116',
        //浏览按钮的高度
        'height':'28',
        //expressInstall.swf文件的路径
//	        'expressInstall':path+'/js/uploadify2/expressInstall.swf?ver=' + Math.random(),
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc':'支持的格式：',
        //允许上传的文件后缀
        'fileTypeExts':'*.jpg;*.jpeg;*.bmp;*.png;*.gif',
        //上传文件的大小限制
        'fileSizeLimit':'200KB',
        //上传数量
        'queueSizeLimit' : 1,
        'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
             //有时候上传进度什么想自己个性化控制，可以利用这个方法
             //使用方法见官方说明
        },
        //选择上传文件后调用
        'onSelect' : function(file) {
        	$.dialog.mask("fileUpload");
        },
        'onDialogClose' : function(file) {
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError':function(file, errorCode, errorMsg){
            switch(errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的1个文件！");
                    break;
                case -110:
                    alert("图片 ["+file.name+"] 大小超出系统限制的200KB大小！");
                    break;
                case -120:
                    alert("图片 ["+file.name+"] 大小异常！");
                    break;
                case -130:
                    alert("图片 ["+file.name+"] 类型不正确！");
                    break;
            }
        },
        //检测FLASH失败调用
        'onFallback':function(){
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess':function(file, data, response){
        	$.dialog.unmask("fileUpload");
           var result = $.parseJSON(data);
           if(result.isSuccess){
        	   globalfilePath = result.filePath;
        	   if(result.fileAccessUrl!=null&&result.fileAccessUrl!=""){
        		   $('#'+imgId).attr('src', result.fileAccessUrl.replace(/\\/g, "/"));
        		   //存入图片字段
        		   $content.find("[name='fileAccessUrl']").val(result.filePath);
        	   }
           }
        }
	});
}