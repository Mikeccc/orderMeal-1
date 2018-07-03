<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../js/main/thirdParty/jquery.js"></script>
<script type="text/javascript" src="../js/main/thirdParty/jquery.dimensions.js"></script>
<script type="text/javascript" src="../js/main/thirdParty/jquery.accordion.js"></script>
<script type="text/javascript" src="../js/main/thirdParty/jquery.treeview.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 保存旧的被点击菜单
	var _oldLi;
	// 菜单点击后变色
	$("span.file a").click(function(){
		// 恢复默认的颜色
		if(_oldLi){ 
			_oldLi.css("color","#333");
			_oldLi.css("font-weight","normal");
		}
		// 保存当前菜单
		_oldLi = $(this); 
		// 变色
		$(this).css("color","#04d");
		$(this).css("font-weight","bold");
	});
	$('#sidebarList').accordion({
		header: "a.sidebarTitle"
	});
	$("#sidebarList").accordion("activate", 0);
	$("#sidebarSubList").treeview({
		persist: "location",
		collapsed: true,
		unique: true
	});
	$(".file:first").click();
});

function changeContextUrl(url){
	if(url){
		window.parent.frames[3].location=url;
	}
}
</script>
</head>

<body>
<div id="sidebarList" class="sidebarTitle">
	
	<c:forEach items="${systemMenuList }" var="row" varStatus="j" begin="0">
		<qtt:PermissionTag menuCode="${row.node.menuId }">
		<a class="sidebarTitle"><div class="sidebarTitleInner">${row.node.menuName }</div></a>
		<div class="sidebarContent">
			<ul id="sidebarSubList" class="filetree">
				<c:forEach items="${row.children}" var="childrenRowOne" >
					<qtt:PermissionTag menuCode="${childrenRowOne.node.menuId}">
					<li><c:choose><c:when test="${childrenRowOne.node.menuIsLeaf == '1'}"><span class="file" style="cursor: pointer;" onclick="changeContextUrl('${childrenRowOne.node.menuUrl }');">${childrenRowOne.node.menuName }</span></c:when><c:otherwise><span class="folder">${childrenRowOne.node.menuName }</span></c:otherwise></c:choose>
						<c:forEach items="${childrenRowOne.children}" var="childrenRowTwo" >
							<qtt:PermissionTag menuCode="${childrenRowTwo.node.menuId }">
							<ul>
								<li><c:choose><c:when test="${childrenRowTwo.node.menuIsLeaf == '1'}"><span class="file" style="cursor: pointer;" onclick="changeContextUrl('${childrenRowTwo.node.menuUrl }');">${childrenRowTwo.node.menuName }</span></c:when><c:otherwise><span class="folder">${childrenRowTwo.node.menuName }</span></c:otherwise></c:choose></li>
							</ul>
							</qtt:PermissionTag>
						</c:forEach>
					</li>
					</qtt:PermissionTag>
				</c:forEach>
			
			</ul>
		</div>
		</qtt:PermissionTag>
    </c:forEach>
	
</div>
</body>
</html>
