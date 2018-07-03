/***************************************************
* JS:init.js
* 版本:1.0
* 作者:liujf
****************************************************/

var _screenHeight;
var _screenWidth;
var _headerHeight = 108 + 5;
var _footerHeight = 42;
var _contentHeight = 0;
var _contentWidth = 0;
var _zoombarHeight = 0;

// 设置菜单大小
function setSize(){
	var _menuHeight = 24;
	var _menuHeightSum = 0;
	var _sidbarWidth = 220;
	var _menuListDiv = $("#sidebarFrame").contents().find("#sidebarList .sidebarContent");
	_screenHeight = document.documentElement.clientHeight;
	_screenWidth = document.documentElement.clientWidth;
	_contentHeight = _screenHeight - (_headerHeight + _footerHeight);
	_contentWidth  = _screenWidth - _sidbarWidth;
	$("#containerFrame").css("height",_contentHeight - 2 + "px");
	$("#containerFrame").css("width",_contentWidth + "px");
	$("#sidebarFrame").css("height",_contentHeight - 2 + "px");
	$("#zoombar").css("height",_contentHeight + "px");
	for(var i=0;i<_menuListDiv.length;i++){
		_menuHeightSum = _menuHeightSum + _menuHeight;
	}
	for(var i=0;i<_menuListDiv.length;i++){
		_menuListDiv[i].style.height = (_contentHeight - _menuHeightSum) + 'px';
	}
}

$(window).bind('load',function(){
	setTimeout(function(){setSize();},10);
	//setSize();
});
$(window).bind('resize',function(){
	setTimeout(function(){setSize();},10);
	//setSize();
});

$(document).ready(function(){
	// 设置缩放栏
	$('#zoombar').click(function(){
		if($('#sidebar').css('display')=='block'){
			$('#sidebar').css('display','none');
			$("#containerFrame").css("width",_contentWidth + 212 + "px");
			$(this).css('background','url(../images/turnOn.gif) left center no-repeat');
		}else{
			$('#sidebar').css('display','block');
			$("#containerFrame").css("width",_contentWidth + "px");
			$(this).css('background','url(../images/turnOff.gif) left center no-repeat');
		}
	});
	
	// 按钮效果
	$('.winbt').mouseover(function(){$(this).attr('class','winbtHover');})
	$('.winbt').mouseup(function(){$(this).attr('class','winbt');})
	$('.winbt').mouseout(function(){$(this).attr('class','winbt');})
	$('.winbt').mousedown(function(){$(this).attr('class','winbtDown');})
	$('.rtdiv').mouseover(function(){$(this).attr('class','rtdivHover');})
	$('.rtdiv').mouseup(function(){$(this).attr('class','rtdiv');})
	$('.rtdiv').mouseout(function(){$(this).attr('class','rtdiv');})
	$('.rtdiv').mousedown(function(){$(this).attr('class','rtdivDown');})
	
	// 格式化对话框位置
	$.fn.formatDialogPosition = function(_width,_height){
		var _halfScreenWidth = _screenWidth / 2;
		var _halfScreenHeight = _screenHeight / 2;
		var _halfWidth = _width / 2;
		var _halfHeight = _height / 2;
		$(this).css("left",_halfScreenWidth - _halfWidth - 4);
		$(this).css("top",_halfScreenHeight - _halfHeight - 30);
	}
	$.fn.closeDialog = function(_width,_height){
		$(this).css("display","none");
		// 格式化对话框位置
		$("#dialogFrame").attr("src","");
		$(this).formatDialogPosition(_width,_height);
		$.unblockUI();
	};
	// 初始化对话框
	$.fn.dialog = function(_src,_title,_width,_height){
		// 设置对话框的标题
		$(this).find(".ltdiv").text(_title);
		// 设置对话框的地址
		$(this).find("#dialogFrame").attr("src",_src);
		// 设置对话框的宽度
		$(this).find("#dialogFrame").attr("width",_width+"px");
		// 设置对话框的高度
		$(this).find("#dialogFrame").attr("height",_height+"px");
		// 初始化对话框位置
		$(this).formatDialogPosition(_width,_height);
		// 初始化对话框,让它可拖动可改变大小
		$(this).jqDrag('.jqDrag').jqResize('.jqResize');
		// 关闭对话框并重置对话框位置		
		$(this).find(".rtdiv").click(function(){$("#dialog").closeDialog(_width,_height)});
		$(this).find(".dialogClose").click(function(){$("#dialog").closeDialog(_width,_height)});
		$(this).find(".dialogSubmit").click(function(){$("#dialog").closeDialog(_width,_height)});
		// 锁住主页面
		$.blockUI({message: $(this)});
	}
});

function getUserDetail(){
	CAjax("home!getUserDetail",
			{
				data : {
					
				}
			},
			function(msg){				
				if (msg.isSuccess) {
					if(msg.sysUserInfo!=null){
						if(msg.isAdmin){
							$('#userCode').html(msg.sysUserInfo.userCode);
							$('#userName').html(msg.sysUserInfo.userName);
							$('#userLevel').html(msg.sysUserInfo.userLevel);
							$('#userTel').html(msg.sysUserInfo.userPhoneNumber);
							$('#userArea').html(msg.sysUserInfo.userArea);
						}else{
							$('#userCode').html(msg.sysUserInfo.userCode);
							$('#userName').html(msg.sysUserInfo.userName);
							$('#userTel').html(msg.sysUserInfo.userPhoneNumber);
							$('#userRole').html(msg.sysUserInfo.tSysRoleInfo[0].roleName);
						}
						$('#pop-div').show();
					}else{
						return;
					}
				}
			}
		);
}

function hideDiv(){
	$('#pop-div').hide();
}

