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
	<script type="text/javascript" src="${ctx }/js/system/user-invalidOrdererList.js"></script>
	<script type="text/javascript" src="${ctx }/js/main/thirdParty/DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var path = "${ctx }", contextPath = path;
        var currentTimeStr = "${currentTime}";
		
        var ORDER_COUNT_END_TIME = "${ORDER_COUNT_END_TIME}";
        var countEndTime = currentTimeStr.replace(/ \d{2}:\d{2}:\d{2}/, " "+ORDER_COUNT_END_TIME);
        var countTime = new Date(Date.parse(countEndTime.replace(/-/g, "/")));
        
        var currentTime = new Date(Date.parse(currentTimeStr.replace(/-/g, "/")));
        var queryDate = currentTimeStr.replace(/ \d{2}:\d{2}:\d{2}/, "");
        
        //当日是否已到达统计时间
        var todayFlag = true;
        
        //15点后，取今天，否则取昨天
        if(currentTime.getHours() < countTime.getHours()){
            var preDate = new Date(currentTime.getTime()-24*60*60*1000);
            var m = "0"+(preDate.getMonth()+1); //将月份加上1后，开头补0
            m = m.substring(m.length - 2); //若月份为1-9，则转换为01-09
            
            var d = "0"+preDate.getDate(); //将日期开头补0
            d = d.substring(d.length - 2);

            queryDate = preDate.getFullYear()+"-"+m+"-"+d;
            
            todayFlag = false;
        }
	</script>
  </head>
  
  <body>
<div class="ny_top">
	<div style="padding-left:10px;">
	  <div style="float:left;">日期：<input id="queryDate" style="width:150px"/></div>
	  <div style="float:left;"><input type="image" name="searchBtn" src="images/button/cx.gif" style="padding: 2px 0 0 5px;"/>
	  <!-- <font class="loading hidden" style="padding:0 0 5px 5px;">数据加载完成...</font> --></div>
      <div style="float:left;">
        <input type="image" name="exportBtn" data-url="${path}system/user!exportInvalidOrderer" src="images/button/wgrydc_btn.gif" style="padding: 2px 0 0 5px;"/>
      </div>
    </div>
</div>
<div style="width:100%; clear:both;">

	<div class="namelist">
  	  <c:forEach  items="${responseBean.resultList}" var="row" varStatus="j" begin="0">
		<ul class="Nlist_box">
			<!-- <li class="fxk_box"><input name="chkBox" type="checkbox" value="${row.userId }" /></li> -->
			<!-- <li class="Nlist_wid_one _field" name="userCode">${row.userCode }</li> -->
			<li class="Nlist_wid_userName _field" name="userName">${row.userName }</li>
			<li class="Nlist_wid_two _field" name="userPhoneNumber">${row.userPhoneNumber }</li>
            <li class="Nlist_wid_email _field" name="department">
                <c:choose>
                    <c:when test="${fn:length(row.department) > 10}">
                        ${fn:substring(row.department, 0, 10)}...
                    </c:when>
                    <c:otherwise>
                        ${row.department}
                    </c:otherwise>
                </c:choose>
            </li>
		      <li class="Nlist_wid_email _field" ><a  id="${row.userCode }"  onclick="showOrders(this)" style="cursor: pointer;color:blue;">查看订单详细信息</a></li>
			<li class="Nlist_wid_two _field hidden" name="userId">${row.userId }</li>
		</ul>
      </c:forEach>
	</div>
	<div class="pagebar" id="fenye" data-url="${ctx }/system/user!queryInvalidOrdererListJson" 
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
</body>
</html>