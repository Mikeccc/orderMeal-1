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
	<script src="${ctx }/js/system/goodsManage.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/js/main/thirdParty/artDialog/jquery.artDialog.js?skin=black"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/base.css" />
	<script type="text/javascript">
		var path = "${ctx }", contextPath = path;
		var firstCategoryId ='${shopCategoryGoods.categoryId}';
	</script>
</head>
<body>
	<div style="display:inline-block; padding:10px 0px 0px 5px;">
		<span style="display:block; float:left; padding:0px 5px;cursor:pointer;" onclick="getAddGoodsView()"><img src="${ctx }/images/button/tjcd.gif" border="0" /></span>
		<span style="display:block; float:left; padding:0px 5px;cursor:pointer;" onclick="getAddCategoryView()"><img src="${ctx }/images/button/tjfz.gif" /></span>
		<span style="display:block; float:left; padding:0px 5px;cursor:pointer;" onclick="getUpdateCategoryView()"><img src="${ctx }/images/button/xgfz.gif" /></span>
		<span style="display:block; float:left; padding:0px 5px;cursor:pointer;" onclick="getDelCategoryView()"><img src="${ctx }/images/button/scfz.gif" /></span>
	</div>
	<div id="tabbox">
	    <ul class="tabs" id="tabs">
	    	<c:forEach items="${categoryList}" var="row" varStatus="j" begin="0">
	    		<c:choose>
					 		<c:when test="${j.index==0}">
					 			<c:choose>
									<c:when test="${fn:length(row.categoryName) > 5}">
										<li onclick="setCategory(${row.categoryId})" id="${row.categoryId}" class="thistab"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${fn:substring(row.categoryName, 0, 5)}...</a></li>
									</c:when>
									<c:otherwise>
										<li onclick="setCategory(${row.categoryId})" id="${row.categoryId}" class="thistab"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${row.categoryName}</a></li>
									</c:otherwise>
								</c:choose>
					 		</c:when>
					 		<c:otherwise>
					 			<c:choose>
									<c:when test="${fn:length(row.categoryName) > 5}">
										<li onclick="setCategory(${row.categoryId})" id="${row.categoryId}"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${fn:substring(row.categoryName, 0, 5)}...</a></li>
									</c:when>
									<c:otherwise>
										<li onclick="setCategory(${row.categoryId})" id="${row.categoryId}"><a href="javascript:setCategory(${row.categoryId})" title="${row.categoryName}">${row.categoryName}</a></li>
									</c:otherwise>
								</c:choose>
					 		</c:otherwise>
				</c:choose>
	    	</c:forEach>
	    </ul>
	    <ul class="tab_conbox" id="tab_conbox">
	        <li class="tab_con">
	           <p>
			   	  <div style="padding:0px 0px 40px 48px;" id="timeDiv">
						<a href="javascript:setTimeCode('00')" id="timeCode_00" style="display:block; float:left; padding:3px 20px; background:#F5F5F5; margin-left:5px; color:#FF0000">每天</a>
						<a href="javascript:setTimeCode('01')" id="timeCode_01" style="display:block; float:left; padding:3px 20px; background:#F5F5F5; margin-left:5px;">周一</a>
						<a href="javascript:setTimeCode('02')" id="timeCode_02" style="display:block; float:left; padding:3px 20px; background:#F5F5F5; margin-left:5px;">周二</a>
						<a href="javascript:setTimeCode('03')" id="timeCode_03" style="display:block; float:left; padding:3px 20px; background:#F5F5F5; margin-left:5px;">周三</a>
						<a href="javascript:setTimeCode('04')" id="timeCode_04" style="display:block; float:left; padding:3px 20px; background:#F5F5F5; margin-left:5px;">周四</a>
						<a href="javascript:setTimeCode('05')" id="timeCode_05" style="display:block; float:left; padding:3px 20px; background:#F5F5F5; margin-left:5px;">周五</a>
					</div>
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
	
	<!-- 新增菜单 start-->
	<div style="display:none;" id="addGoodsDiv" >
		<div style="width:450px;">
		
			<div style="float:left;">
				<span class="xglist2" style="_margin:10px 10px 10px 5px;"><input type="text" id="addGoodsName" value="菜名" onfocus="if (value =='菜名'){value =''}" onblur="if (value ==''){value='菜名'}" style="width:242px;" /></span>
				<span class="xglist2" style="_margin:10px 10px 10px 5px;"><input type="text" id="addGoodsPrice" value="价格，如：12" onfocus="if (value =='价格，如：12'){value =''}" onblur="if (value ==''){value='价格，如：12'}" style="width:217px;" />元</span>
				<span class="xglist2">
					<select id="addGoodsCategorySelect" style="width:247px;">
					</select>
				</span>
				<span class="xglist2"><input name="everydayRadio" type="radio" id="everydayRadio" checked="checked" value="00" />每天<input name="onedayRadio" id="onedayRadio" type="radio" id="" value="" />指定</span>
				<span style="display:none" id="fiveDaysSpan" class="xglist2"><input name="" id="timeCode_01" type="checkbox" value="01" class="sday"/>周一 <input name="" id="timeCode_02" type="checkbox" value="02" class="sday"/>周二 <input name="" id="timeCode_03" type="checkbox" value="03" class="sday"/>周三 <input name="" id="timeCode_04" type="checkbox" value="04" class="sday"/>周四 <input name="" id="timeCode_05" type="checkbox" value="05" class="sday"/>周五 </span>				
				<span class="xglist2" style="_margin:10px 10px 10px 5px;">
					<textarea id="addGoodsDetaiDesc" style="width:250px">菜品描述</textarea>
				</span>
			</div>
			<div style="float:right;">
				<span>
					<img width="150" height="150" id="addImg" src="${ctx }/images/default.jpg" style="border:solid 1px #CCC;"/>
					<label><input name="file" type="file"  id="uploadImage"  style="display: none;"/></label>
					<label style="color:#FF0000;">请上传规格为150×150，</label><br/>
					<label style="color:#FF0000;">大小不超过200K的图片</label>
				</span>
			</div>
		</div>
	</div>
	<!-- 新增菜单 end-->
		
	<!-- 添加分组 start -->
	<div style="display:none" id="addCategoryDiv">
		<div class="tjlist_box"><input type="text" id="addCategoryName" value="分组名称" onfocus="if (value =='分组名称'){value =''}" onblur="if (value ==''){value='分组名称'}" style="width:200px;" /></div>
	</div>
	<!-- 添加分组 end -->
	
	<!-- 修改分组 start -->
	<div style="display:none" id="updateCategoryDiv">
		<div class="tjlist_box"><input type="text" id="updateCategoryName" value="" style="width:200px;" /></div>
	</div>
	<!-- 修改分组 end -->
	
	
	<!-- 删除分组 start -->
	<div>
		
	</div>
	<!-- 删除分组 end -->
	
	
	<!-- 菜单详情 start -->
	<div style="display:none" id="goodsDetailDiv">
		<div class="tjlist_box">
			<div class="xxcd_imgL"><img width="150" height="150" id="goodsDetailImg" src="" /></div>
			<div class="xxcd_imgR">
				<font><input id="goodsDetaiName" type="text" value="" style="width:200px;" /></font>
				<font><input id="goodsDetaiPrice" type="text" value="" style="width:200px;" /></font>
				<font><select id="goodsDetaiCategorySelect" style="width:206px;">
					</select>
				</font>
			</div>
		</div>
		<div class="xgpic_box"><input name="file" type="file"  id="uploadImage2"  style="display: none;"/></div>
		<div class="jieshuo_box">
			<div class="jieshuo_top">小二解说</div>
			<div class="jieshuo_text"><textarea id="goodsDetaiDesc" style="width:100%"></textarea>
			</div>
		</div>
	</div>
	<!-- 菜单详情 end -->
</body>
</html>