<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <jsp:include page="/common/order/mate2.jsp" />

  </head>
  
<body>
	<a name="top" id="top"></a>
		<div class="warp_box nav_box">
			<a href="javascript:history.go(-1);" class="fh_btn"><img src="../images/front/fh_btn.jpg" height="43" /></a>
			<font class="nav_font">我的饭盒</font>
		</div>
		<div class="warp_box">
			<div class="top_login">
				<span class="top_login_font">客官：${tel}</span>
			</div>
		</div>
		<h1></h1>
	
	<form action="get-order!modifyStatus?orderId=${orderId}" name="addOrderForm" method="post" >
	<input type="hidden" name = "shopId" id ="shopId" value="${shopId }"/>
	<input type="hidden" name = "tel" id ="tel" value="${tel }"/>
		<div class="warp_box">
			<div class="sj_title_list">[订餐详细]</div>
			<div class="xzline_box">
				<c:set var="listSize" value="${fn:length(list) }"></c:set>
				<c:forEach items="${list}" var="childrenRow" varStatus="i" begin="0" end="${listSize }">
					<c:choose>
						<c:when test="${i.last}">
							<span class="cdline_list">
								<ul>
									<li class="cd_font"><b>总计：</b></li>
									<li class="xg_jianju">&nbsp;</li>
									<li class="cd_jiage"><b>￥${childrenRow.shopGoodsPrice }</b></li>
									<li class="cd_fs"><b>${childrenRow.shopGoodsCount }</b></li>
								</ul>			
							</span>
						</c:when>
						<c:otherwise>
							<span class="cdline_list">
								<ul>
									<li class="cd_font">${childrenRow.shopGoodsName}</li>					
									<li class="xg_jianju">&nbsp;</li>
									<li class="cd_jiage">￥${childrenRow.shopGoodsPrice}</li>
									<li class="cd_fs">x${childrenRow.shopGoodsCount}</li>
								</ul>			
							</span>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</div>
		</div>
		<div class="warp_box cz_btn_box">
			<c:choose>
				<c:when test="${listSize==0}">
					<font color="red">无有效数据或该订单已领取!</font>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="childrenRow" varStatus="i" begin="0" end="0">
						<c:set var="shopStatus" value="${childrenRow.orderShopRunStatus}"></c:set>
					</c:forEach>
					<c:choose>
						<c:when test="${shopStatus=='04'}">
						<font style="color:red;text-align: center;">该店铺订单已领取</font>
						</c:when>
						<c:when test="${shopStatus=='03'}">
							<button type="submit" class="wyxg_btn" style="border: 0px;" data-role="none">完成领取</button>
						</c:when>
						<c:otherwise>
							<font style="color:red;text-align: center;">该店铺订单未下单</font>
						</c:otherwise>
					</c:choose>
					
				</c:otherwise>
			</c:choose>
			
		</div>
		<div class="warp_box cz_btn_box">
			<font style="color:red;text-align: center;"><a style="color:blue;text-decoration:underline" href="${path}order/get-order!getAuthcodeVerify" data-ajax="false">验证码领餐</a></font>
		</div>
	</form>
   <footer>
                <div class="warp_box foot_font" style="font-family:arial;">
                        Copyright &copy;2013 中国移动手机动漫基地版权所有
                </div>
        </footer>
</body>
</html>
