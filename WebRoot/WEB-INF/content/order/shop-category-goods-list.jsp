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
    	<a class="back"  href="shop-list">返回</a>   
        <font class="title">今日餐品</font>
    </div>
</header>
<section>
	<div class="side_list">
    	<font class="sj_font">${shopInfo.shopName }&nbsp; | &nbsp;联系电话：${shopInfo.shopPhoneNumber }</font>
   		<c:forEach  items="${goodsList}" var="row" varStatus="j" begin="0">
    		<c:if test="${j.index%2 == '0'}">
    			<div class="cp_list">
		        	<img src="${row.fileAccessUrl }" style="float:left; margin-left:10px;" width="120"  height="80" />
		            <div class="cp_jsfont">菜名：${row.shopGoodsName }<br />价格：￥${row.shopGoodsPrice }<br />
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
		            <input type="button" value="详情" class="Yesml_input_btn" onclick="$.mobile.changePage('goods-detail?shopGoodsId=${row.shopGoodsId }&categoryId=${categoryId}&shopId=${row.shopId}')" />&nbsp; 
	            	<input type="button" class="Grsml_input_btn" value="我要订购" onclick="$.mobile.changePage('add-shoping-cart?orderId=${orderId}&shopId=${row.shopId}&shopGoodsId=${row.shopGoodsId }&shopGoodsPrice=${row.shopGoodsPrice}')"  /></div>
       				<%} else {%>
	            	<input type="button" value="详情" class="Yesml_input_btn" onclick="$.mobile.changePage('goods-detail?shopGoodsId=${row.shopGoodsId }&categoryId=${categoryId}&shopId=${row.shopId}')" />&nbsp; </div>
	           		<%}%>
       			</div>
      			</c:if>	
       		<c:if test="${j.index%2 != '0'}">
	    			<div class="cp_list right_bg">
			        	<img src="${row.fileAccessUrl }" style="float:left; margin-left:10px;" width="120"  height="80"/>
			            <div class="cp_jsfont">菜名：${row.shopGoodsName }<br />价格：￥${row.shopGoodsPrice }<br />
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
		            <input type="button" value="详情" class="Yesml_input_btn" onclick="$.mobile.changePage('goods-detail?shopGoodsId=${row.shopGoodsId }&categoryId=${categoryId}&shopId=${row.shopId}')" />&nbsp; 
	            	<input type="button" class="Grsml_input_btn" value="我要订购" onclick="$.mobile.changePage('add-shoping-cart?orderId=${orderId}&shopId=${row.shopId}&shopGoodsId=${row.shopGoodsId }&shopGoodsPrice=${row.shopGoodsPrice}')"  /></div>
       				<%} else {%>
	            	<input type="button" value="详情" class="Yesml_input_btn" onclick="$.mobile.changePage('goods-detail?shopGoodsId=${row.shopGoodsId }&categoryId=${categoryId}&shopId=${row.shopId}')" />&nbsp; </div>
	           		<%}%>
       			</div>
       		</c:if>	
  		</c:forEach>
    </div>
</section>
  <jsp:include page="/common/order/footer.jsp" />
</div>
</body>
</html>
