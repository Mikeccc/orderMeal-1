<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>中国移动手机动漫基地-订餐系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="Shortcut Icon" href="../images/favicon.ico" />
<script type="text/javascript" src="../js/main/thirdParty/jquery.js"></script>
<script type="text/javascript" src="../js/main/thirdParty/jqDnR.js"></script>
<script type="text/javascript" src="../js/main/thirdParty/jquery.blockUI.js"></script>
<script src="../js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/main/init.js"></script>
</head>
<body scroll="no" style="height: 100%;">
<!--弹出对话框 begin-->
<div class="jqDnR" id="dialog">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="lt"><div class="space"></div></td>
      <td class="t jqHandle jqDrag"><div class="ltdiv">&nbsp;</div><div class="rtdiv">&nbsp;</div></td>
      <td class="rt"><div class="space"></div></td>
    </tr>
    <tr>
      <td class="lm"></td>
      <td class="m"><iframe id="dialogFrame" src="" width="100%" height="100%" scrolling="no" frameborder="0">&nbsp;</iframe></td>
      <td class="rm"></td>
    </tr>
    <tr>
      <td class="lb"></td>
      <td class="b" align="right"><input type="button" class="winbt dialogSubmit" value="&nbsp;确定&nbsp;" />&nbsp;<input type="button" class="winbt dialogClose" value="&nbsp;关闭&nbsp;" /></td>
      <td class="rb"></td>
    </tr>
  </table>
</div>

<!--主体框架-->
<div>
  <div><iframe id="headeFrame" scrolling="no" src="../common/system/header.jsp" width="100%" height="113" frameborder="0"></iframe></div>
  <div id="middle">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td id="sidebar" style="display: block;"><iframe id="sidebarFrame" scrolling="no" src="left" width="100%" height="100%" frameborder="0"></iframe></td>
        <td id="zoombar">&nbsp;</td>
        <td id="container"><iframe id="containerFrame" src="" width="100%" height="100%" frameborder="0"></iframe></td>
      </tr>
    </table>
  </div>
  <div id="footer"><iframe id="footerFrame" scrolling="no" src="../common/system/footer.html" width="100%" height="41" frameborder="0"></iframe></div>
</div>

</body>
</html>