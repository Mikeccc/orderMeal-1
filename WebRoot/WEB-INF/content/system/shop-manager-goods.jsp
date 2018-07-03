<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>中国移动手机动漫基地-订餐系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/common.js"></script>
</head>
<script type="text/javascript">
var contextPath = "${ctx}";
var firstCategoryId ='${shopCategoryGoods.categoryId}';
$(document).ready(function() {
    jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
        $(tab_conbox).find("li").hide();
        $(tabtit).find("li:first").addClass("thistab").show(); 
        $(tab_conbox).find("li:first").show();
    
        $(tabtit).find("li").bind(shijian,function(){
          $(this).addClass("thistab").siblings("li").removeClass("thistab"); 
            var activeindex = $(tabtit).find("li").index(this);
            $(tab_conbox).children().eq(activeindex).show().siblings().hide();
            return false;
        });
    
    };
    /*调用方法如下：*/
    //$.jqtab("#tabs","#tab_conbox","click");
    
    //$.jqtab("#tabs2","#tab_conbox2","mouseenter");
    
});
var globalCategoryId='-1';
$(document).ready(function(){

    globalCategoryId =firstCategoryId;

});

function showShopGoods(obj){
    var shopId =  $(obj).attr('id');
    $('#hiddenShopId').val(shopId);
  var url = '/BespeakMeal/system/shop-manager!showShopGoods';
    document.getElementById("listForm").action = url;
    document.getElementById("listForm").submit();
}

function setCategory(categoryId,shopId){
    if($('#'+categoryId).hasClass("thistab")){
        return ;
    }else{
        $('#tabs li').removeClass("thistab");
        $('#'+categoryId).addClass("thistab");
        globalCategoryId = categoryId;
//          globalTimeCode='00';
        getGoods(1,categoryId,shopId);
    }
}
    
function getGoods(pageNo,categoryId,shopId){
    CAjax("shop-manager!queryGoodsByPageAjax",
        {
            data : {
                'currentPage' : pageNo,
                'shopCategoryGoods.categoryId' : globalCategoryId=='-1'?'':categoryId,
                'shopInfo.shopId' : shopId
            }
        },
        function(msg){
            if(msg.isSuccess){
                var html='';
                $.each(msg.goodsResponseBean.resultList,function(i,v){
                    html+='<div class="cd_list_box"><span class="cd_listL" style="cursor:pointer" onclick="getGoodsDetail('+v.shopGoodsId+')">'+v.shopGoodsName+'</span><span class="cd_listR">￥'+v.shopGoodsPrice+'</span></div>';
                });
                $('#goodsView').html(html);
            }
        }
    );
}
</script>
<body>
<div class="dp_name" >店铺名：<font class="dp_font">狼吞虎咽</font></div>
<div>
    <ul class="tabs" id="tabs">
       <c:forEach items="${categoryList}" var="row" varStatus="j" begin="0">
                <c:choose>
                            <c:when test="${j.index==0}">
                                <li onclick="setCategory(${row.categoryId},${row.shopId })" id="${row.categoryId}" class="thistab"><a href="javascript:setCategory(${row.categoryId},${row.shopId })">${row.categoryName}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li onclick="setCategory(${row.categoryId},${row.shopId })" id="${row.categoryId}"><a href="javascript:setCategory(${row.categoryId},${row.shopId })">${row.categoryName}</a></li>
                            </c:otherwise>
                </c:choose>
            </c:forEach>
       <span style="float:right; padding:5px 5px 0px 0px;"><input type="image" src="${ctx }/images/button/fhlist_btn.gif" onclick="location.href='${ctx }/system/shop-manager';" /></span>
    </ul>
    <ul class="tab_conbox" id="tab_conbox">
        <li class="tab_con">
           <p>
            <div style="width:100%; display:inline-block; clear:both" id="goodsView">
                  <c:forEach items="${goodsResponseBean.resultList}" var="row" varStatus="j" begin="0">
                       <div class="cd_list_box">
                          <span class="cd_listL" style="cursor:pointer" onclick="getGoodsDetail(${row.shopGoodsId})">${row.shopGoodsName}</span>
                          <span class="cd_listR">￥${row.shopGoodsPrice}</span>
                       </div> 
                  </c:forEach>                        
            </div>
           </p>
         </li>
     </ul>
</div>
</body>
</html>
