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
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/additional-methods.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/main/thirdParty/validation/additional-methods-extend.js"></script>
	<script type="text/javascript" src="${ctx }/js/common.js"></script>
	<script type="text/javascript" src="${ctx }/js/system/user-validOrdererList.js"></script>
	<script type="text/javascript">
		var path = "${ctx }", contextPath = path;
	</script>
  </head>
  
  <body>
<table border="0" cellspacing="0" cellpadding="0" style="margin:10px;" class="ny_table">
  <tr>
	<td>姓名：</td>
	<td><input name="userName" type="text" /></td>
	<td>手机号码：</td>
	<td><input name="userPhoneNumber" type="text" /></td>
	<td>科室/公司：</td>
	<td><input name="department" type="text" /></td>
	<td><a name="searchBtn" href="javascript:void(0);"><img src="images/button/cx.gif" /></a></td>
  </tr>
</table>
<div class="ny_top">
	<ul>
		<li><input name="selectAll" type="checkbox" />本页全选</li>
		<!-- <li><b name="selectCount" class="yellow_font lineblock" style="width:60px;">共计：0</b></li>  -->
		<li><a name="addBtn" href="javascript:void(0);"><img src="images/button/tj.gif" /></a></li>
		<li><a name="editBtn" href="javascript:void(0);"><img src="images/button/xg.gif" /></a></li>
		<li><a name="delBtn" href="javascript:void(0);"><img src="images/button/sc.gif" /></a></li>
        <li><a name="exportBtn" href="javascript:void(0);"><img src="images/button/drdddc_btn.gif" /></a></li>
	</ul>
</div>
<div style="width:100%; clear:both;">

	<div class="namelist">
  	  <c:forEach  items="${responseBean.userlist}" var="row" varStatus="j" begin="0">
		<ul class="Nlist_box">
			<li class="fxk_box"><input name="chkBox" type="checkbox" value="${row.userId }" /></li>
			<li class="Nlist_wid_one _field hidden" name="userCode" data-value="${row.userCode }">${row.userCode }</li>
			<li class="Nlist_wid_userName _field" name="userName" data-value="${row.userName }">${row.userName }</li>
			<li class="Nlist_wid_two _field" name="userPhoneNumber" data-value="${row.userPhoneNumber }">${row.userPhoneNumber }</li>
			<li class="Nlist_wid_email _field hidden" name="userEmail" data-value="${row.userEmail }">${row.userEmail }</li>
			<li class="Nlist_wid_email _field" name="department" data-value="${row.department }">
		        <c:choose>
		            <c:when test="${fn:length(row.department) > 10}">
		                ${fn:substring(row.department, 0, 10)}...
		            </c:when>
		            <c:otherwise>
		                ${row.department}
		            </c:otherwise>
		        </c:choose>
		    </li>
		      <li class="Nlist_wid_email " ><a  id="${row.userCode }"  onclick="showOrders(this)" style="cursor: pointer;color:blue;">查看订单详细信息</a></li>
			<li class="Nlist_wid_two _field hidden" name="userId" data-value="${row.userId }">${row.userId }</li>
		</ul>
      </c:forEach>
	</div>
	<div class="pagebar" id="fenye" data-url="${ctx }/system/user!queryValidOrdererListJson" 
			data-totalrecords="${responseBean.totalRecords}" data-currentpage="${responseBean.currentPage}" 
			data-totalpages="${responseBean.totalPages }" data-params="">
		<span>总数：<font name="totalRecords">${responseBean.totalRecords} </font></span>
		<a name="firstPage" href="javascript:void(0)">首页</a>
		<a name="prePage" href="javascript:void(0)" >上一页</a>
		<a name="nextPage" href="javascript:void(0)">下一页</a>
		<a name="lastPage" href="javascript:void(0)">尾页</a>
		<span>页次：<font name="currentPage">${responseBean.currentPage}</font>/<font name="totalPages">${responseBean.totalPages }</font></span>
		<span class="go_input"><input name="goPage" type="text" maxlength="4"/></span><span name="gobtn" class="GO_btn">GO</span>
	</div>
</div>
<div class="_editForm hidden">
<form action="#">
<div><input name="userId" type="hidden" ><input name="userCode" type="hidden" ></div>
<div style="width:320px;height:45px;">姓名：  &nbsp;&nbsp;&nbsp;&nbsp;<input name="userName" type="text" style="width:240px;"/></div>
<div style="width:320px;height:45px;">手机号码：  <input name="userPhoneNumber" type="text" style="width:240px;"/></div>
<div style="width:320px;height:45px;" class="hidden">E-Mail：&nbsp;&nbsp;<input name="userEmail" type="text" style="width:240px;"/></div>
<div style="width:320px;height:45px;">科室/公司：<input name="department" type="text" style="width:240px;"/></div>
</form>
</div>
</body>
</html>