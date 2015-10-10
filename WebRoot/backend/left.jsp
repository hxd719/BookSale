<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>   
<html xmlns="http://www.w3.org/1999/xhtml">
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
<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>

</head>
<body>
    <div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="#">后台管理</a></h1>
      <!-- Logo (221px wide) -->
      <a href="#"><img id="logo" src="resources/images/logo.png" alt="Simpla Admin logo" /></a>
      <!-- Sidebar Profile links -->
      <div id="profile-links"> Hello, <a href="#" title="Edit your profile">admin</a><br />
        <br />
        <a href="#" title="Sign Out">退出</a> </div>
      <ul id="main-nav">
        <!-- Accordion Menu -->
        <li> <a href="#" class="nav-top-item">
          <!-- Add the class "current" to current menu item -->
          	信息管理 </a>
          <ul>
            <li><a href="BuyInfo_list?pm.pageNum=1" target="rightFrame"> 购买信息  </a></li>
            <!-- Add class "current" to sub menu items also -->
            <li><a href="SellInfo_list?pm.pageNum=1" target="rightFrame"> 出售信息 </a></li>
            <li><a href="Category_list?pm.pageNum=1" target="rightFrame"> 分类信息 </a></li>
            <li><a href="Category/Category_add.jsp" target="rightFrame"> 添加分类 </a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 图书管理  </a>
          <ul>
            <li><a href="Book_list?pm.pageNum=1" target="rightFrame"> 图书列表 </a></li>
            <li><a href="Book/Book_add.jsp" target="rightFrame"> 增加图书 </a></li>
            <li><a href="Catch/Catch_modify.jsp" target="rightFrame"> 爬虫设置 </a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 会员管理 </a>
          <ul>
            <li><a href="User_list?pm.pageNum=1" target="rightFrame"> 会员列表 </a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 系统管理 </a>
          <ul>
            <li><a href="Admin/Admin_modify.jsp?id=1" target="rightFrame"> 修改密码 </a></li>
          </ul>
        </li>
      </ul>
      <!-- End #main-nav -->
    </div>
  </div>
</body>
</html>

