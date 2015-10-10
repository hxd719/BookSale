<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>   
<html style="height: 100%;" xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 </title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<style type="text/css">
body {
font-family: Arial, Helvetica, sans-serif;
color: #555;
background: #f0f0f0;
font-size: 12px;
}

</style>
</head>
<body style="overflow-y: hidden;height: 100%;">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="250" valign="top">
    <iframe height="100%" width="100%"  scrolling="no" frameborder="0" frameborder="0" src="left.jsp" name="leftFrame" id="leftFrame" title="leftFrame">
    </iframe>
    </td>
    <!-- 主 要内容-->
    <td valign="top">
    <iframe height="100%" width="100%" scrolling="yes" frameborder="0" frameborder="0" src="BuyInfo_list?pm.pageNum=1" name="rightFrame" id="rightFrame" title="rightFrame">
    </iframe>
    </td>
  </tr>
</table>
</body>
</html>

