<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="java.util.*,cn.sjzc.booksale.model.*,java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/dataTable/css/demo_page.css" type="text/css" />
<link rel="stylesheet" href="resources/dataTable/css/demo_table.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="resources/dataTable/jquery.dataTables.min.js"></script>
<style type="text/css">
body {
font-family: Arial, Helvetica, sans-serif;
color: #555;
background: #f0f0f0;
font-size: 12px;
}

#main-content {
margin: 0 30px 0 10px;
padding: 40px 0 0 0;
}
p { 
padding: 12px 0; 
margin: 0; 
font-size: .8em; 
line-height: 1.5; 
} 
form { 
margin: 0; 
} 
#search_box { 
width: 201px; 
height: 31px; 
background: url(resources/images/bg_search_box.gif); 
} 
#search_box #s { 
float: left; 
padding: 0; 
margin: 6px 0 0 6px; 
border: 0; 
width: 159px; 
background: none; 
font-size: .8em; 
} 
#search_box #go { 
float: right; 
margin: 3px 4px 0 0; 
} 
</style>
<script type="text/javascript">


</script>
</head>
<% int id = Integer.valueOf(request.getParameter("id"));
 %>

<body>
 <div id="main-content">
    <!-- Main Content Section with everything -->
    <!-- Page Head -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>修改</h3>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab2">
          <form action="Admin_update" method="post">
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
           
           <div> 
            <input type="hidden" id="id" name="id" value="<%= id %>" />
            </div>
            
             
             <p>
              <label>原密码</label>
              <input class="text-input small-input" type="text" id="small-input" name="password1" value="" />
              <!-- Classes for input-notification: success, error, information, attention -->
            </p>
            
            
            <p>
              <label>密码</label>
              <input class="text-input small-input" type="text" id="small-input" name="password" value="" />
              <!-- Classes for input-notification: success, error, information, attention -->
            </p>
            <p>
              <input class="button" type="submit" value="提交" />
            </p>
            <div class="clear"></div>
            <!-- End .clear -->
          </form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
  </div>
</body>
</html>

