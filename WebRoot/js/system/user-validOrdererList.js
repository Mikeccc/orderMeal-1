
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
	
//	var defaultOpt = {
//		//
//	}
//	
//	$.SimplePager = function($owner, opt){
//		//
//	}

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
var tpl = '<ul class="Nlist_box">'+
			'<li class="fxk_box"><input name="chkBox" type="checkbox" value="{userId}"</li>'+
			'<li class="Nlist_wid_one _field hidden" name="userCode" data-value="{userCode}">{userCode}</li>'+
			'<li class="Nlist_wid_userName _field" name="userName" data-value="{userName}">{userName}</li>'+
			'<li class="Nlist_wid_two _field" name="userPhoneNumber" data-value="{userPhoneNumber}">{userPhoneNumber}</li>'+
			'<li class="Nlist_wid_email _field hidden" name="userEmail" data-value="{userEmail}">{userEmail}</li>'+
			'<li class="Nlist_wid_email _field" name="department" data-value="{department}">{departmentText}</li>'+
			'<li class="Nlist_wid_email " ><a id="{userCode}"  onclick="showOrders(this)" style="cursor: pointer;color:blue;">查看订单详细信息</a></li>'+
			'<li class="Nlist_wid_two _field hidden" name="userId" data-value="{userId}">{userId}</li></ul>';

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
				$("[name='selectAll']:checkbox").attr("checked", false);
				
				//加载
				var data = json.responseBean;
				//列表数据
				var userList = data.userlist;

				var html = [];
				$.each(userList, function(i, u){
					
					//文本内容截取处理
					var departmentText = u.department;
					if(departmentText.length > 10){
						departmentText = departmentText.substring(0, 10) + "...";
					}
					u.departmentText = departmentText;
					
					html.push(nano(tpl, u));
				});
				$(".namelist").html(html.join(""));
				
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

var emailPattern = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
var phonePattern = /^((((13[5-9]{1})|(15[0,1,2,7,8,9]{1})|(147{1})|(18[2,3,7,8])){1}\d{1})|((134[0-8]{1}){1})){1}\d{7}$/;

function addUsers(users, fn){
	users = users || [];
	if(users.length < 1){
		$.dialog.alert("请录入订餐人员信息！");
		return;
	}

	//验证
	var flag = true;
	$.each(users, function(i, u){
		var userName = u.userName || "",
			userPhoneNumber = u.userPhoneNumber || "",
			//userEmail = u.userEmail || "",
			department = u.department || "";
		
		if('' == $.trim(userName)){
			//不允许为空
			$.dialog("第["+(i+1)+"]行：姓名不存在！");
			return flag = false;
		}else if($.trim(userName).replace(/[^\x00-\xff]/g,"**").length > 8){
			
			//长度不允许超过50
			$.dialog("第["+(i+1)+"]行：姓名长度不允许超过8位（汉字占2位）！");
			return flag = false;
		}
		
		if('' == $.trim(userPhoneNumber)){
			//不允许为空
			$.dialog("第["+(i+1)+"]行：手机号码不存在！");
			return flag = false;
		}else if(!phonePattern.test(userPhoneNumber)){
			//手机正则验证
			$.dialog("第["+(i+1)+"]行：请填写中国移动的手机号码！");
			return flag = false;
		}
		
//		if('' == $.trim(userEmail)){
//			$.dialog("第["+(i+1)+"]行：请填写Email！");
//			return flag = false;
//		}else if(!emailPattern.test($.trim(userEmail))){
//			//邮件正则验证
//			$.dialog("第["+(i+1)+"]行：Email格式不正确！");
//			return flag = false;
//		}
		
		if('' == $.trim(department)){
			//不允许为空
			$.dialog("第["+(i+1)+"]行：科室/公司不存在！");
			return flag = false;
		}else if(department.length > 50){
			//长度不允许超过50
			$.dialog("第["+(i+1)+"]行：科室/公司不允许超过50个字！");
			return flag = false;
		}
	});
	if(!flag){
		return;
	}

    var loading = $.dialog({title: false, cancel:false});
    CAjax(path + "/system/user!addOrderers",
			{
    			data: {list: users}
			},
			function(json){
				loading.close();
				//刷新列表(不论成功、失败)
				loadPage($('#fenye'), $('#fenye').data("currentpage"));
				
				if(false == json.isSuccess){
					var msg = json.errorMsg || "订餐人员添加失败！";
					var successCount = json.successCount || 0,
						failCount = json.failCount || 0;
					if(json.code){
						msg += " (error code:"+json.code + ")";
					}
					msg = "添加成功数：" + successCount + "<br/>添加失败数：" + failCount + "<br/><hr/>失败原因：<br/>" + msg;
					$.dialog.error(msg);
					return;
				}
				fn();
				$.dialog.tips("订餐人员添加成功！");
			},
			function(json){
				loading.close();
				$.dialog.error("数据请求失败！\n状态码：["+json.status+"]");
			}
		);
}




function addWin(){

	var dialog = $.dialog({
	    fixed: true,
	    resize:false,
	    opacity: .1,
	    title:"订餐人员添加",
	    content: '<textarea style="width:350px;height:200px;margin-bottom:6px;resize:none;" /><div style="color:blue;">*录入格式为:姓名,手机号码,科室/公司<br/>*可录入多条信息，每行录入一条</div>',
	    okVal:"添加",
	    cancelVal:"返回列表",
	    init: function () {
	        var input = this.DOM.content.find('textarea')[0];
	        input.focus();
	    },
	    ok: function (here) {
	    	//解析数据
	        var $input = this.DOM.content.find('textarea');
	        //每一行为一条记录
	        var vals = $input.val().split(/\n/);
	        //用逗号分隔的数据依次为prop中定义的顺序
	        var rs = [], prop=["userName", "userPhoneNumber", "department"];

	        $.each(vals, function(i, row){
	        	if("" == $.trim(row)){
	        		return true;
	        	}
	        	var r = {};
	        	//将全角统一替换为半角英文逗号
	        	row = row.replace(/，/g, ",");
	        	$.each(row.split(","), function(j, v){
	        		r[prop[j]] = $.trim(v);
	        	});
	        	if(!$.isEmptyObject(r)){
	        		rs.push(r);
	        	}
	        });
	        
	        addUsers(rs, function(){
	        	dialog.close();
	        });
	        return false; //不关闭窗口
	    },
	    cancel: true
	});
}

function editWin(){
	var $selections = $(".namelist :checked");
	if($selections.length < 1){
		$.dialog.alert("请选择待编辑的信息！");
		return;
	}else if($selections.length > 1){
		$.dialog.alert("一次只允许编辑一条信息！");
		return;
	}
	var userId = $selections[0].value;

	var data = {};
	$selections.parent().parent().find("._field").each(function(i, li){
		var $this = $(this);
		data[$this.attr('name')] = $this.data("value");
	});
	
	var dialog = $.dialog({
	    fixed: true,
	    resize:false,
	    opacity: .1,
	    title:"订餐人员编辑",
	    content: $('div._editForm').html(),
	    okVal:"修改",
	    cancelVal:"返回列表",
	    init: function(){
	    	//初始化表单数据
	        var $content = this.DOM.content;
	        $.each(data, function(p, v){
	        	$content.find("[name='"+p+"']").val(v);
	        });
	        
	        //初始化表单验证
	        addValidator($content.find("form"));
	    },
	    ok: function (here) {
	    	//验证不能过直接返回
	    	if(!this.DOM.content.find("form").valid()){
	    		return false;
	    	}
	    	var formData = {};
	    	//解析数据
	        this.DOM.content.find("input").each(function(){
	        	var $this = $(this);
	        	formData[$this.attr('name')]=$.trim($this.val());
	        });

	        var loading = $.dialog({title: false, cancel:false});
	        CAjax(path + "/system/user!updateOrderer",
					{
	        			data: {user: formData}
					},
					function(json){
						loading.close();
						//刷新列表(不论成功、失败)
		    			loadPage($('#fenye'), $('#fenye').data("currentpage"));
		    			
		    			if(false == json.isSuccess){
		    				var msg = json.errorMsg || "订餐人员修改失败！";
		    				if(json.code){
		    					msg += " (error code:"+json.code + ")";
		    				}
		    				$.dialog.error(msg);
		    				return;
		    			}
			        	dialog.close();
		    			$.dialog.tips("订餐人员修改成功！");
					},
					function(json){
						loading.close();
						$.dialog.error("数据请求失败！\n状态码：["+json.status+"]");
					}
				);
	        return false; //不关闭窗口
	    },
	    cancel: true
	});
}

function deleteWin(){
	
	var $selections = $(".namelist :checked");
	if($selections.length < 1){
		$.dialog.alert("请选择待删除的信息！");
		return;
	}
	var userIds=[];
	$selections.each(function(){
		userIds.push($(this).val());
	});
	
	$.dialog.confirm('确定要删除选中的信息？', function () {
		var loading = $.dialog({title: false, cancel:false});
		CAjax(path + "/system/user!deleteOrderer",
				{
					data: {userIds: userIds}
				},
				function(json){
					loading.close();
					//刷新列表(不论成功、失败)
					loadPage($('#fenye'), $('#fenye').data("currentpage"));
					
					if(false == json.isSuccess){
						var msg = json.errorMsg || "订餐人员删除失败！";
						if(json.code){
							msg += " (error code:"+json.code + ")";
						}
						$.dialog.error(msg);
						return;
					}
					$.dialog.tips("订餐人员删除成功！");
				},
				function(json){
					loading.close();
					$.dialog.error("数据请求失败！\n状态码：["+json.status+"]");
				}
			);
		
	});
}

function addValidator($form, opt){
	$form.validate($.extend({
		rules:{
	        userCode: {
	            required:true,
	            idtype: true,
	            maxLength2: 20
	        },
	        userName: {
	            required:true,
	            maxLength2: 8
	        },
	        userPhoneNumber: {
	            required: true,
	            mobileYD: true
	        },
//	        userEmail: {
//	            required: true,
//	            email: true
//	        },
	        department: {
	            required:true,
	            maxLength2: 50
	        }
		},
		messages: {
			userCode: {
	            required: "请填写用户编码",
	            idtype: "只能使用字母、数字、下划线",
	            maxLength2: "用户编码最大不能超过20个字"
			},
	        userName: {
	            required: "请填写用户名称",
	            maxLength2: "姓名最大不能超过8位(汉字占2位)"
	        },
			userPhoneNumber: {
	            required: "请填写手机号码",
	            mobileYD: "请填写中国移动的手机号码"
	        },
//	        userEmail: {
//	            required: "请填写Email",
//	            email: "Email格式不正确"
//	        },
	        department: {
	            required: "请填写科室/公司",
	            maxLength2: "科室/公司最大不能超过50个字(汉字占2个字)"
	        }
		},
		/* 设置验证触发事件 */  
	    focusInvalid: false,   
	    onkeyup: false,   
	    
	    /* 设置错误信息提示DOM */  
	    errorPlacement: function(error, element) {
	    	error.css({"margin-left": "70px"});
	        error.appendTo( element.parent());
	    }
	}, opt||{}));
}


function showOrders(obj){
	var $btn = $(obj);
	var userCode = $btn.attr('id'),
	//queryDate ='2013-09-02',
	//url = path +'/system/user!queryUserOrderList?userCode='+userCode+'&createTime='+queryDate+'';
	url = path +'/system/user!queryUserOrderList?userCode='+userCode+'';
	 //检索分类
	var dialog = $.dialog;
	dialog.open(url);
	$(".aui_title").text("订单详细信息")
}

$(document).ready(function(){
	//设置全选功能
	$("[name='selectAll']:checkbox").change(function(){
		$(".namelist :checkbox").attr("checked", this.checked);
	});
	//设置复选框反向设置全选框功能(此处使用live，因为翻页时会更新checkbox)
	$(".namelist :checkbox").live("change", function(){
		var total = $(".namelist :checkbox").length,
			checked = $(".namelist :checked").length;
		$("[name='selectAll']:checkbox").attr("checked", total == checked);
	});
	
	//设置分页
	var $pagebar = $("#fenye").renderPagebar(goPage
//			{
//		url: "",
//		success: function(){},
//		error: $.noop}
			);
	
	//设置添加、修改、删除功能
	$(".ny_top [name='addBtn']").click(addWin);
	$(".ny_top [name='editBtn']").click(editWin);
	$(".ny_top [name='delBtn']").click(deleteWin);
	//导出
	$(".ny_top [name='exportBtn']").click(function(){
		var datec = +new Date;
		window.open(path + "/system/order-manage!exportOrderOperateDetail?v="+datec);
	});

	//设置查询功能
	var $searchContainer = $(".ny_table"),
		$inputs = $searchContainer.find("input");
	$searchContainer.find("[name='searchBtn']").click(function(){
		var searchData = {};
		$inputs.each(function(){
			var $this = $(this);
			searchData[$this.attr("name")] = $.trim($this.val());
		});
		$pagebar.data("params", {user:searchData});
		//检索列表数据
		loadPage($pagebar, 1);
	});
});