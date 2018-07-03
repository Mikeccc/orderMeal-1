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
	
    <!-- <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/css/screen.css"></script> -->
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/additional-methods.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/additional-methods-extend.js"></script>
	<script type="text/javascript" src="${ctx }/js/common.js"></script>
    <script type="text/javascript" src="${ctx }/js/system/shopManager.js"></script>

	<script type="text/javascript">
		var path = "${ctx }", contextPath = path;
		var firstCategoryId ='${shopCategoryGoods.categoryId}';
	</script>
  </head>
  
  <body>
 
<table border="0" cellspacing="0" cellpadding="0" style="margin:10px;" class="ny_table">
	<tr>
		<td>店铺名称：</td>
		<td><input name="shopName" type="text"  value=""/></td>
		<td>手机号码：</td>
		<td><input name="shopPhoneNumber" type="text"  value=""/></td>
		<td><a name="searchBtn" href="javascript:void(0);"><img src="images/button/cx.gif" /></a></td>
	</tr>
</table>
<div class="ny_top">
	<ul>
		<li><a name="addBtn" href="javascript:void(0);"><img src="images/button/tj.gif" /></a></li>
		<li><a name="editBtn" href="javascript:void(0);"><img src="images/button/xg.gif" /></a></li>
		<li><a name="delBtn" href="javascript:void(0);"><img src="images/button/sc.gif" /></a></li>
	</ul>
</div>
<table border="1" cellpadding="0" cellspacing="0" bordercolor="#C3D9F1" class="gys_table">
  <tr style=" background:#ECFDFA">
    <td><input name="selectAll" type="checkbox" /></td>
    <td width="150">店铺名称</td>
    <td width="100">手机号码</td>
    <td width="100">QQ号码</td>
    <td width="150">邮箱</td>
    <td width="200">地址</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <c:forEach  items="${responseBean.resultList}" var="row" varStatus="j" begin="0">
  <tr class="row_data">
    <td><input name="chkBox" type="checkbox" value="${row.shopId }"  phoneNum="${row.shopPhoneNumber }"/></td>
    <td class="_field" name="shopName" data-value="${row.shopName}">${row.shopName }</td>
    <td class="_field" name="shopPhoneNumber" data-value="${row.shopPhoneNumber}">${row.shopPhoneNumber }</td>
    <td class="_field" name="shopQq" data-value="${row.shopQq}">${row.shopQq }</td>
    <td class="_field" name="shopEmail" data-value="${row.shopEmail}">${row.shopEmail }</td>
    <td class="_field" name="shopAddress" data-value="${row.shopAddress}">
	    <c:choose>
	        <c:when test="${fn:length(row.shopAddress) > 20}">
	            ${fn:substring(row.shopAddress, 0, 20)}...
	        </c:when>
	        <c:otherwise>
	            ${row.shopAddress}
	        </c:otherwise>
	    </c:choose>
    </td>
    <td ><a id="${row.shopId }" onclick="showShopGoods(this)" style="cursor: pointer;color:blue;">点我查看菜单</a></td>
    <td ><a id="${row.shopId }" onclick="showShopOrder(this)" style="cursor: pointer;color:blue;">查看当日订单</a></td>
    <td><input type="button" onclick="changeStatus('${row.shopStatus}', '${row.shopId }', this)" 
        value="<c:if test="${row.shopStatus eq '00' }">暂停</c:if><c:if test="${row.shopStatus eq '01' }">启动</c:if>" />
        <input type="hidden" class="_field" name="shopLogoFileId" value="${row.shopLogoFileId }" />
        <input type="hidden" class="_field" name="fileAccessUrl" value="${row.sysFile.fileAccessUrl}" />
    </td>
  </tr>
  </c:forEach>
    
</table>

<div class="pagebar" id="fenye" data-url="${ctx }/system/shop-manager!queryListJson" 
		data-totalrecords="${responseBean.totalRecords}" data-currentpage="${responseBean.currentPage}" 
		data-totalpages="${responseBean.totalPages }" data-params="" data-renderer="shopRenderer">
	<span>总数：<font name="totalRecords">${responseBean.totalRecords} </font></span>
	<a name="firstPage" href="javascript:void(0)">首页</a>
	<a name="prePage" href="javascript:void(0)" >上一页</a>
	<a name="nextPage" href="javascript:void(0)">下一页</a>
	<a name="lastPage" href="javascript:void(0)">尾页</a>
	<span>页次：<font name="currentPage">${responseBean.currentPage}</font>/<font name="totalPages">${responseBean.totalPages }</font></span>
	<span class="go_input"><input name="goPage" type="text" maxlength="4"/></span><span name="gobtn" class="GO_btn">GO</span>
</div>
<div class="_editForm hidden">
<form action="#">
    <div style="width:520px;">
        <div style="float:left;height:200px;">
			<div><input name="shopId" type="hidden" ><input name="fileAccessUrl" type="hidden" ><input name="shopLogoFileId" type="hidden" ></div>
			<div class="dp_name" style="width:330px;height:30px;">店铺名称：<input type="text" name="shopName" style="width:250px;" /></div>
				
			<span class="xglist" style="clear:both;width:330px;height:30px;"><font class="font_block">手机号码：</font><input type="text" name="shopPhoneNumber" style="width:250px;" /></span>
			<span class="xglist" style="width:330px;height:30px;"><font class="font_block">QQ：</font><input type="text"  name="shopQq" style="width:250px;" /></span>
			<span class="xglist" style="width:330px;height:30px;"><font class="font_block">E-Mail：</font><input type="text"  name="shopEmail" style="width:250px;" /></span>
			<span class="xglist" style="width:330px;height:30px;"><font class="font_block">地址：</font><input type="text" name="shopAddress" style="width:250px;" /></span>
	
        </div>
        <div style="float:right;">
	       <div class="xxcd_imgL" style="clear:both;"><img width="160" height="160" src="${ctx }/images/default.jpg" style="border:1px solid #CCC"/></div>
	       <div style="clear:both;">
               <label><input name="file" type="file" class="hidden"/></label>
               <label style="color:#FF0000;">请上传规格为160×160，</label><br />
               <label style="color:#FF0000;">大小不超过200K的图片</label>
	       </div>
        </div>
    </div>
</form>
</div>
<div class="_shopWindowTpl hidden">
	<div class="dp_name" >店铺名称：<font class="dp_font">{shopName}</font></div>
	<div class="_shopWindowContent">
	    <ul class="tabs">
	       {categorys}
	       <!-- <span style="float:right; padding:5px 5px 0px 0px;"><input type="image" src="${ctx }/images/button/fhlist_btn.gif" onclick="location.href='${ctx }/system/shop-manager';" /></span> -->
	    </ul>
	    <ul class="tab_conbox">{tab_conbox}
	        <!-- <li class="tab_con">
	           <p>
	             <div class="cd_list_box">
	                <span class="cd_listL">红烧排骨</span>
	                <span class="cd_listR">￥12.0</span>
	             </div>
	             <div class="cd_list_box">
	                <span class="cd_listL">红烧排骨</span>
	                <span class="cd_listR">￥12.0</span>
	             </div>
	             <div class="cd_list_box">
	                <span class="cd_listL">红烧排骨</span>
	                <span class="cd_listR">￥12.0</span>
	             </div>
	             <div class="cd_list_box">
	                <span class="cd_listL">红烧排骨</span>
	                <span class="cd_listR">￥12.0</span>
	             </div>
	            </p>
	        </li> -->
	     </ul>
		<div class="pagebar menu" data-url="${ctx }/system/shop-manager!queryGoodsByPageAjax" 
		        data-totalrecords="0" data-currentpage="0" 
		        data-totalpages="0" data-params="" data-renderer="menuRenderer">
		    <span>总数：<font name="totalRecords">0</font></span>
		    <a name="firstPage" href="javascript:void(0)">首页</a>
		    <a name="prePage" href="javascript:void(0)" >上一页</a>
		    <a name="nextPage" href="javascript:void(0)">下一页</a>
		    <a name="lastPage" href="javascript:void(0)">尾页</a>
		    <span>页次：<font name="currentPage">1</font>/<font name="totalPages">1</font></span>
		    <span class="go_input"><input name="goPage" type="text" maxlength="4"/></span><span name="gobtn" class="GO_btn">GO</span>
		</div>
	</div>
</div>
</body>
</html>
