/***************************************************
* JS:init.js
* 版本:1.0
* 作者:liujf
****************************************************/

$(document).ready(function(){
	setSize();
	$('#password').keydown(function(event){
			if(event.keyCode == 13){
				alert("Enter");
			}													 
	})
});//500 485
function setSize(){
	var _width = screen.width;
	var _height = screen.height;
	alert('width:'+_width);
	alert('height:'+_height);
	if(_width == 800 && _height == 600){
		$('#login').attr('class','login_800x600');
		$('#inner').css('left',482);
		$('#inner').css('top',182);
	}else if(_width == 1280 && _height == 800){
		$('#login').attr('class','login_1280x800');
		$('#inner').css('left',722);
		$('#inner').css('top',282);
	}else if(_width == 1280 && _height == 1024){
		$('#login').attr('class','login_1280x1024');
		$('#inner').css('left',722);
		$('#inner').css('top',394);
	}else if(_width == 1400 && _height == 1050){
		$('#login').attr('class','login_1400x1050');
		$('#inner').css('left',782);
		$('#inner').css('top',407);
	}else if(_width == 1600 && _height == 1050){
		$('#login').attr('class','login_1600x1050');
		$('#inner').css('left',656);
		$('#inner').css('top',514);
	}else{
		$('#login').attr('class','login_1024x768');
		$('#inner').css('left',594);
		$('#inner').css('top',267);
	}
}