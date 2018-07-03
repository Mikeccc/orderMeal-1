<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<section>
	<div class="side_list">
    	<div class="fh_box"><a href="#Top" onclick="window.scrollTo(0,0);return false;" >返回顶部</a></div>
        <div class="Cop_box">Copyright ©2013 中国移动手机动漫基地版权所有</div>
        <div style="height:80px;">&nbsp;</div>
    </div>
</section>

	<div class="bot_tsbox">
    	<div class="bot_ts_list">
    		<c:if test="${not empty sessionScope.user_order_bean  }">
    			<font style="display:block; text-align:center; color:#FFFFFF">客官：${sessionScope.user_order_bean.userInfo.userPhoneNumber }您好！</font>
            	<div class="grxx_box" >
            	<a class="Grsml2_input_btn2" href="add-shoping-cart!goOnAdd" data-ajax="false">继续订购</a>
            	<a class="grYellow_input_btn"  href="my-order" data-ajax="false">我的饭盒</a>
            	<a class="blue_input_btn2"  href="login!doLogout" data-ajax="false" >退出</a>
            </div>
    		</c:if>
    		<c:if test="${empty sessionScope.user_order_bean.userInfo }">
    			<ul>
	            	<li class="bot_img"><img src="../images/dcxt_icon.png" width="64" /></li>
	                <li class="bot_font">动漫食堂欢迎你!</li>
	                <li class="bot_btn"> <input type="button" class="blue_input_btn" value="登录"  data-ajax="false"  onclick="goLogin()"/></li>
            	</ul>    
    		</c:if>
        </div>    
    </div>
    <script type="text/javascript">
$(document).ready(function () {
        var bt = $('#Top');
        var sw = $(document.body)[0].clientWidth;

        var limitsw = (sw - 1060) / 2 - 40;
        if (limitsw > 0){
                limitsw = parseInt(limitsw);
                bt.css("right",limitsw);
        }

        $(window).scroll(function() {
                var st = $(window).scrollTop();
                if(st > 30){
                        bt.show();
                }else{
                        bt.hide();
                }
        });
});
function goLogin(){
	window.location.href="login";
}
</script>