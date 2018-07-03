<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>中国移动手机动漫基地-订餐系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />	
	<script type="text/javascript" src="${ctx }/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/main/thirdParty/artDialog/jquery.artDialog.js?skin=black"></script>
	<script type="text/javascript" src="${ctx }/js/common.js"></script>
	<script type="text/javascript" src="${ctx }/js/system/user-validOrdererList.js"></script>
	<style type="text/css">

.close_font{float:right; line-height:30px; font-size:14px;}

.dj_title{background:#EDEDED; line-height:30px; text-indent:10px;}

.dj_dclist_box{display:inline-block; line-height:30px;}

.dclist_box{display:inline-block; border-bottom:1px dashed #CCCCCC; width:274;}

.dclist_box2{display:inline-block; width:274;}

.dclist_cmfont{display:block; float:left; width:180px; text-indent:10px;}

.dclist_font{display:block; float:left; width:40px; text-align:center}
</style>
	<script type="text/javascript">
		var path = "${ctx }", contextPath = path;
	</script>
	
	<script type="text/javascript">
	$(document).ready(function() {
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
		/*调用方法如下：*/
		$.jqtab("#tabs","#tab_conbox","click");
		
		$.jqtab("#tabs2","#tab_conbox2","mouseenter");
		
		
	});
	
	function clickTab(i){
		$('#tabbox .tab_conbox').hide();
		$('#tabbox .tab_conbox[data-index="'+i+'"]').show();
	}
</script>
	
  </head>
  
  <c:if test="${ empty shopNameList }">
  		<div style="text-align: center; color:red" >
  			查不到此用户的订单信息!
  		</div>
  </c:if>
<c:if test="${ !empty shopNameList }">
<div id="tabbox" style="width:300px;">
    <ul class="tabs" id="tabs">
      <c:forEach items="${shopNameList }" var="row4"  varStatus="i" begin="0">
          <li><a href="#" onclick="clickTab('${i.index}')">${row4.shopName }</a></li>
   	  </c:forEach>
    </ul>
     <c:forEach  items="${MyOrderListList}" var="row" varStatus="i"  begin="0">
   		 <ul class="tab_conbox"  data-index="${i.index }"  <c:if test="${i.index > 0 }">style="display:none;"</c:if>>
	        <li class="tab_con">
	           <p>
			   	<c:forEach items="${row }" var="row2" varStatus="j" begin="0">
			   	<div class="dj_dclist_box">
	                	<p class="dclist_box">
	                        <font class="dclist_cmfont">${row2.shopGoodsName }</font>
	                        <font class="dclist_font">￥${row2.shopGoodsPrice }</font>
	                        <c:if test="${row2.shopGoodsName !='总计:' }">
	                        	<font class="dclist_font">x${row2.shopGoodsCount }</font>
	                        </c:if>
	                    </p>
	              </div>
	              </c:forEach>
				</p>
	        </li> 	           
       
    	</ul>
                
    	
   </c:forEach>
   
</div>
  </c:if>
  </body>
</html>
