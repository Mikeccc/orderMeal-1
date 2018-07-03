
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
			//'<li class="fxk_box"><input name="chkBox" type="checkbox" value="{userId}"</li>'+
			//'<li class="Nlist_wid_one _field" name="userCode">{userCode}</li>'+
			'<li class="Nlist_wid_userName _field" name="userName">{userName}</li>'+
			'<li class="Nlist_wid_two _field" name="userPhoneNumber">{userPhoneNumber}</li>'+
			'<li class="Nlist_wid_email _field" name="department">{departmentText}</li>'+
			'<li class="Nlist_wid_two _field hidden" name="userId">{userId}</li>'+
			'<li class="Nlist_wid_email " name="userId"><a  id="{userCode}"  onclick="showOrders(this)" style="cursor: pointer;color:blue;">查看订单详细信息</a></li></ul>';

function loadPage(owner, page){

    var loading = $.dialog({title: false, cancel:false});
	params = owner.data("params") || {};
	var url = owner.data("url")+'?ver=' + Math.random();
	CAjax(url, {
		data: $.extend(params, {currentPage : page})
	}, function(json){
		loading.close();
		if(false == json.isSuccess){
			var msg = json.errorMsg || "列表数据检索失败！";
			if(json.code){
				msg += " (error code:"+json.code + ")";
			}
			$.dialog.error(msg);
			return;
		}
		//加载
		var data = json.responseBean;
		//列表数据
		var resultList = data.resultList;

		var html = [],
			searchDate = params.searchDate;
		$.each(resultList, function(i, u){
			
			//文本内容截取处理
			var departmentText = u.department;
			if(departmentText.length > 10){
				departmentText = departmentText.substring(0, 10) + "...";
			}
			u.departmentText = departmentText;
			u.searchDate = searchDate;
			html.push(nano(tpl, u));
		});
		$(".namelist").html(html.join(""));
		
		//更新分页信息
		owner.data('currentpage', data.currentPage);
		owner.data('totalpages', data.totalPages);
		owner.data('totalrecords', data.totalRecords);
		//重新render
		owner.renderPagebar(goPage);
	}, function(){
		loading.close();
	});
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

$(document).ready(function(){

	$('#queryDate').click(function(){
		WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
	});
	
	//设置分页
	var $pagebar = $("#fenye").renderPagebar(goPage);
	
	//设置查询功能
	var $searchContainer = $(".ny_top"),
		$queryDate = $("#queryDate");
	
	//设置缺省查询日期
	$queryDate.val(queryDate);

	//设置查询按钮后，初始查询一次
	$searchContainer.find("[name='searchBtn']").click(function(){
		var searchDate = $queryDate.val();
		
		if(!searchDate){
			$.dialog.alert("请输入查询日期");
			return;
		}
		if(!/\d{2}-\d{2}-\d{2}/.test(searchDate)){
			$.dialog.alert("查询日期格式不正确！");
			return;
		}
		
		var today = new Date();
		var tmpDate = searchDate.split("-");
		tmpDate = new Date(tmpDate[0], tmpDate[1]-1, tmpDate[2]);
		
		if(tmpDate.getTime() > today.getTime()){
			$.dialog.alert("未来的统计数据还未生成！");
			return;
		}
		
		if(!todayFlag && searchDate == getDateString(today)){
			$.dialog.alert("今天的统计数据还未生成！");
			return;
		}
		$pagebar.data("params", {searchDate: searchDate});
		//检索列表数据
		loadPage($pagebar, 1);
	}).click();
	
	//设置导出功能
	$searchContainer.find("[name='exportBtn']").click(function(){
		
		var searchDate = $queryDate.val();
		
		if(!searchDate){
			$.dialog.alert("请输入查询日期");
			return;
		}
		if(!/\d{2}-\d{2}-\d{2}/.test(searchDate)){
			$.dialog.alert("查询日期格式不正确！");
			return;
		}
		window.open($(this).data("url") + "?searchDate="+searchDate);
	});
});

//将Date转换为yyyy-MM-dd字符串
function getDateString(date){
	var m = "0"+(date.getMonth()+1); //将月份加上1后，开头补0
    m = m.substring(m.length - 2); //若月份为1-9，则转换为01-09
    
    var d = "0"+date.getDate(); //将日期开头补0
    d = d.substring(d.length - 2);
    
    var r = [];
    r.push(date.getFullYear(), m, d)
	return r.join("-");
}

function showOrders(obj){
	var $btn = $(obj);
	var queryDate = $('#queryDate').val();
	var userCode = $btn.attr('id'),
	url = path +'/system/user!queryUserOrderList?userCode='+userCode+'&createTime='+queryDate+'';
	 //检索分类
	var dialog = $.dialog;
	dialog.open(url);
	$(".aui_title").text("订单详细信息")
}

//查看订单
function showInvalidOrder(userCode, searchDate){
	//alert([userCode, searchDate]);
}