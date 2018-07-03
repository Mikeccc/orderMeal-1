<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>中国移动手机动漫基地-订餐系统</title>
	<script src="${ctx }/js/jquery-1.8.2.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/system/orderCountByShopId.js" type="text/javascript"></script>
	
	<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />
	
	<script type="text/javascript">
        var contextPath = "${ctx}";
		var firstCategoryId ='${shopCategoryGoods.categoryId}';
	</script>
</head>
<body>
	<div id="tabbox">
	    <ul class="tabs" id="tabs">
			<c:forEach items="${categoryList}" var="row" varStatus="j" begin="0">
	    		<c:choose>
					 		<c:when test="${j.index==0}">
					 			<c:choose>
									<c:when test="${fn:length(row.categoryName) > 5}">
										<li onclick="setCategory(${row.categoryId})" id="category_${row.categoryId}" class="thistab"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${fn:substring(row.categoryName, 0, 5)}...</a></li>
									</c:when>
									<c:otherwise>
										<li onclick="setCategory(${row.categoryId})" id="category_${row.categoryId}" class="thistab"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${row.categoryName}</a></li>
									</c:otherwise>
								</c:choose>
					 		</c:when>
					 		<c:otherwise>
					 			<c:choose>
									<c:when test="${fn:length(row.categoryName) > 5}">
										<li onclick="setCategory(${row.categoryId})" id="category_${row.categoryId}"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${fn:substring(row.categoryName, 0, 5)}...</a></li>
									</c:when>
									<c:otherwise>
										<li onclick="setCategory(${row.categoryId})" id="category_${row.categoryId}"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${row.categoryName}</a></li>
									</c:otherwise>
								</c:choose>
					 		</c:otherwise>
				</c:choose>
	    	</c:forEach>	    
	    </ul>
	    <ul class="tab_conbox" id="tab_conbox">
	        <li class="tab_con">
	           <p>
		   	  		<table border="1" cellpadding="0" cellspacing="0" bordercolor="#C3D9F1" class="gys_table" width="98%" id="orderCountView">
					  	<tr style=" background:#ECFDFA">
							<td width="40%">菜名</td>
							<td width="20%">价格</td>
							<td width="20%">总数</td>
							<td width="20%">未领取</td>
					  	</tr>
					  	<c:forEach items="${orderResponseBean.resultList}" var="row" varStatus="j" begin="0">
						  	<tr>
								<td>${row.shopGoodsName}</td>
								<td>￥${row.shopGoodsPrice}</td>
								<td><b class="yellow_font">x</b>${row.shopGoodsCount}</td>
								<td>${row.orderRunStatusCount}</td>
						  	</tr>
					  	</c:forEach>
					 	<tr>
							<td><b>总计：</b></td>
							<td><b class="yellow_font">￥${orderResponseBean.priceCountDaily}</b></td>
							<td><b class="yellow_font">x</b>${orderResponseBean.numberCountDaily}</td>
							<td>${orderResponseBean.noGetNumCountDaily}</td>
					  	</tr>
					</table>
			</p>
	       </li>             
	    </ul>
	</div>
</body>
</html>