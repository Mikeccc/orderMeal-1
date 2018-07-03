/***************************************************
* JS:init.js
* 版本:1.0
* 作者:liujf
****************************************************/

$(document).ready(function(){
	$('.home').mouseover(function(){
			$(this).attr('class','home_on');			
	})
	$('.home').mouseup(function(){
			$(this).attr('class','home_on');			
	})
	$('.home').mouseout(function(){
			$(this).attr('class','home');			
	})
	$('.home').mousedown(function(){
			$(this).attr('class','home_on');			
	})
	
	$('.help').mouseover(function(){
			$(this).attr('class','help_on');			
	})
	$('.help').mouseup(function(){
			$(this).attr('class','help_on');			
	})
	$('.help').mouseout(function(){
			$(this).attr('class','help');			
	})
	$('.help').mousedown(function(){
			$(this).attr('class','help_on');			
	})
	
	$('.exit').mouseover(function(){
			$(this).attr('class','exit_on');			
	})
	$('.exit').mouseup(function(){
			$(this).attr('class','exit_on');			
	})
	$('.exit').mouseout(function(){
			$(this).attr('class','exit');			
	})
	$('.exit').mousedown(function(){
			$(this).attr('class','exit_on');			
	})
	
	$('.sort').mouseover(function(){
			$(this).attr('class','sort_on');			
	})
	$('.sort').mouseup(function(){
			$(this).attr('class','sort_on');			
	})
	$('.sort').mouseout(function(){
			$(this).attr('class','sort');			
	})
	$('.sort').mousedown(function(){
			$(this).attr('class','sort_on');			
	})
});