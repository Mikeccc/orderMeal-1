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
    	<a class="back" data-role="none" href="shop-list">返回</a>
        <font class="title">我的饭盒</font>
    </div>
</header>
<section>
	<div class="side_list">	
	<c:set var="listSize" value="${fn:length(list2) }"></c:set>
	<c:forEach items="${list2}" var="childrenRow" varStatus="i" begin="0" end="${listSize }">
		<c:choose>
				<c:when test="${i.last}">
					   <div class="side_list">
    						<font style=" display:block; line-height:24px; padding:20px; float:left">总计：￥${childrenRow.shopGoodsPrice }</font>
        					<font style=" display:block; line-height:24px; padding:20px; float:right">份数：${childrenRow.shopGoodsCount }</font>
   						</div>
				</c:when>
				<c:otherwise>
					<div class="cp_list">
        				<img src="${childrenRow.fileAccessUrl  }" style="float:left; margin-left:10px;" width="120" height="80"/>
            			<div class="wdfh_cplist">
            				<font class="wdfh_cpfont">菜名：${childrenRow.shopGoodsName}<br />价格：￥${childrenRow.shopGoodsPrice}<br />份数：x${childrenRow.shopGoodsCount}</font>            	            
            			</div>
       			  </div>
			</c:otherwise>
		 </c:choose>
	</c:forEach>
        
    </div>
    <div class="cp_list2">
        <c:forEach  items="${list2}" var="row" varStatus="j" begin="${listSize-1 }" end="${listSize }">
					<div class="cp_img"><img src="${row.orderShopQrcode}" /></div>
					<c:if test="${not empty row.captchas ||row.captchas!=''}">
						<div style="line-height:30px;text-align:center;font-size:15px;">验证码：<font style="color:red;">${row.captchas}</font></div>
					</c:if>
					<c:set var="orderShopQrcode" value="${row.orderShopQrcode}"></c:set>
					<c:choose>
							<c:when test="${not empty orderShopQrcode}">
								 <font style="font-size:14px; color:#999999;">亲爱的客官，<font class="red_font">如您在领餐前没收到二维码，可将上图中的二维码保存到手机中</font>，以便领餐时使用，也可使用验证码领餐～</font>
							</c:when>
							<c:otherwise>
								<span class="lcts_font">亲爱的客官，您还没有点餐或已点餐但未下单哟～</span>
							</c:otherwise>
					</c:choose>	
				</c:forEach>
    </div>
    <div class="warp_box cz_btn_box">
			
			
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
						  <div class="cz_btnbox"><input type="button" class="xg_Yellow_input_btn" value="我要修改"  onclick="$.mobile.changePage('add-shoping-cart!list?orderId=${orderId}&shopId=${shopId}')"/></div>
						<%} else {%>
						<span style="display:block; float:left; margin:auto; width:70px;"><button type="button" onclick="$.mobile.changePage('shop-list');" style="border: 0;"  data-role="none" data-ajax="false" class="jxdg_btn2">返回首页</button></span>
						<%}%>
	</div>    
</section>
	<jsp:include page="/common/order/footer.jsp" />
</div>
</body>
</html>
