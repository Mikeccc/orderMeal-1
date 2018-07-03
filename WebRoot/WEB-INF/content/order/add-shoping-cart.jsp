<%@ page language="java" import="java.util.*,app.cn.qtt.bm.common.utils.DateUtil,app.cn.qtt.bm.common.cache.CacheConstants,org.apache.commons.lang.StringUtils" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <jsp:include page="/common/order/mate.jsp" />
  </head>
  
<body>
<a name="Top" id="Top"></a>
<div id="Wrap">
<header id="bar">
	<div class="side_list_top">
        <font class="title">订购</font>
    </div>
</header>
<form action="add-order?orderId=${orderId}&shopId=${shopId}" name="addOrderForm" data-ajax="false" method="post" >
<section>
	<div class="side_list">	
	<c:set var="listSize2" value="${fn:length(list) }"></c:set>
		<c:forEach  items="${list}" var="row" varStatus="j" begin="0">
			<font class="sj_font">${row.shopInfo.shopName}  |  联系电话：${row.shopInfo.shopPhoneNumber} </font>	
			<c:set var="listSize" value="${fn:length(row.myOrderList) }"></c:set>
			<c:forEach items="${row.myOrderList}" var="childrenRow" varStatus="i" begin="0" end="${listSize-2 <0?0:listSize-2}">
			<input type="hidden" name="orderGoodsId" value="${childrenRow.orderGoodsId}"/>
			<input type="hidden" name="shopIdGoodsIdCount" value="${row.shopInfo.shopId}_${childrenRow.shopGoodsId}_${childrenRow.shopGoodsCount}"/>
			<div class="cp_list">
	        	<img src="${childrenRow.fileAccessUrl }" style="float:left; margin-left:10px;" width="120"  height="80" />
	            <div class="wdfh_cplist">
	            	<font class="wdfh_cpfont">菜名：${childrenRow.shopGoodsName}<br />价格：￥${childrenRow.shopGoodsPrice}</font>
	            
	            	<input type="text" name="shopGoodsCount" style="float:left; width:60px; height:24px; text-align:center" maxlength="3" value="${childrenRow.shopGoodsCount}" />

	                <span class="wdfh_scbox"><input type="button" value="删除" class="red_input_btn" onclick="$.mobile.changePage('add-shoping-cart!deleteOrderGoods?shopId=${row.shopInfo.shopId}&orderId=${childrenRow.orderId}&shopGoodsId=${childrenRow.shopGoodsId}')" /></span>                
	            </div>
	        </div>
		</c:forEach>
		</c:forEach>
	
        
    </div>
    <div class="side_list">
 		 <c:choose>
			<c:when test="${empty  orderInfo.orderPrice}">
				无数据！
			</c:when>
			<c:otherwise>
				<font style=" display:block; line-height:24px; padding:20px; float:left">总计：￥${orderInfo.orderPrice }</font>
      				<font style=" display:block; line-height:24px; padding:20px; float:right">份数：${orderInfo.orderGoodsCount }</font>
			</c:otherwise>
		</c:choose>
    	
    </div>
    <div class="cz_btnbox">
    	<%
					String startTimeParameter = CacheConstants.BM_START_TIME();
					String overTimeParameter = CacheConstants.getParamValueByName("over_time");
	                if(StringUtils.isEmpty(overTimeParameter)){
	                	overTimeParameter="10:00:00";
	                }
	                
	                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
	                Date startTime = sdf.parse(startTimeParameter);
	                Date overTime = sdf.parse(overTimeParameter);
	                Date nowTime = sdf.parse(sdf.format(new Date()));
	                
					if(nowTime.before(overTime)&& nowTime.after(startTime)){
				%>
				<c:choose>
					<c:when test="${listSize2==0 }">
						<button type="submit" name="type" value="继续订购" class="Green_input_btn"   data-role="none" >继续订购</button>
					</c:when>
					<c:otherwise>
						  <button type="submit" name="type" value="1" class="Green_input_btn"  data-role="none" >继续订购</button>&nbsp;<button type="submit" name="type" value="2"  class="Yellow_input_btn"  data-role="none" >我要下单</button></div>
					</c:otherwise>
				</c:choose>
				<%} else {%> <%}%>
  
</section>

  <jsp:include page="/common/order/footer.jsp" />
</div>
</form>
</body>
</html>
