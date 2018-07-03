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
		<a class="back" data-role="none" href="shop-category-goods-list?shopId=${shopId}">返回</a>
        <font class="title">套餐详情</font>
    </div>
</header>
<section>
	<div class="side_list">
    	<div class="cp_img"><img src="${sysFile.fileAccessUrl }"  width="300" height="200"/></div>	
        <div class="cp_xqlist">
       	  <div class="fontcen_box">菜名：${shopGoods.shopGoodsName }<br /><font class="red_font">价格：￥${shopGoods.shopGoodsPrice }</font></div>
      </div>        
    </div>
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
    <div class="cz_btnbox"><input type="button" class="Grsml_input_btn" value="我要订购"  onclick="$.mobile.changePage('add-shoping-cart?shopId=${shopGoods.shopId}&shopGoodsId=${shopGoods.shopGoodsId }&shopGoodsPrice=${shopGoods.shopGoodsPrice}')"/>&nbsp;<input type="button" class="hui_input_btn" value="选择其他套餐"  onclick="$.mobile.changePage('shop-category-goods-list?shopId=${shopGoods.shopId}&shopGoodsId=${shopGoods.shopGoodsId}&categoryId=${categoryId}')"/></div>
    <%} else {%> 
    <div class="cz_btnbox"><input type="button" class="hui_input_btn" value="选择其他套餐"  onclick="$.mobile.changePage('shop-category-goods-list?shopId=${shopGoods.shopId}&shopGoodsId=${shopGoods.shopGoodsId}&categoryId=${categoryId}')"/></div>
    <%}%>
    <div class="cp_list2">
    	小二解说<br /><br />
        <font style="font-size:14px; color:#999999;">${shopGoods.shopGoodsDesc }</font>
    </div>
</section>
  <jsp:include page="/common/order/footer.jsp" />
</div>
</body>
</html>
