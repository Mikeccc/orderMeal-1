<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <jsp:include page="/common/order/mate2.jsp" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/front.css" />

  </head>
  
<body>
		<div class="warp_box nav_box">
			<a href="javascript:history.go(-1);" class="fh_btn"><img src="../images/front/fh_btn.jpg" height="43" /></a>
			<font class="nav_font">温馨提示</font>
		</div>
		<div class="warp_box">
			<div class="top_login">
				<c:choose>
					<c:when test="${not empty  sessionScope.user_order_bean.userInfo.userPhoneNumber}">
						<span class="top_login_font">${sessionScope.user_order_bean.userInfo.userPhoneNumber }</span>
						<a href="my-order" class="wdfh_btn" data-ajax="false">我的饭盒</a>
						<a href="add-shoping-cart!goOnAdd" class="wdfh_btn" data-ajax="false">继续订购</a>
						<%--
						<a href="updatePasswordView" class="xgmm_btn">修改密码</a>
						<a href="login!doLogout" class="logout_btn" data-ajax="false">退出</a>
						 --%>
					</c:when>
					<c:otherwise>
						<a href="login" class="xgmm_btn" data-ajax="false">登录</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	<div class="czts_font">领取操作出错！！</div>
  	 <footer>
                <div class="warp_box foot_font" style="font-family:arial;">
                        Copyright &copy;2013 中国移动手机动漫基地版权所有
                </div>
        </footer>
</body>
</html>
