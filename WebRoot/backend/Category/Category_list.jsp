<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="java.util.*,cn.sjzc.booksale.utill.*,cn.sjzc.booksale.model.*,java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.sjzc.booksale.model.Book"%>
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
  <script type="text/javascript">

	function deleBatch() {
	if(confirm("确定要删除么？")) {
	document.getElementById("form").submit();
	}
	}

	function checkAll() {
		var ids = window.document.getElementsByName("ids");
		for(var i=0;i<ids.length;i++) {
			ids[i].checked=window.document.getElementById("checkbox").checked;
		}
	}
	
	function go1(page) {
		window.location.href="Category_list?searchKey=<%= request.getAttribute("searchKey")==null?"":request.getAttribute("searchKey") %>&pm.pageNum="+page;
	}
	
	function delet(id) {
		if(confirm("确定要删除么？")) {
			document.getElementById("form").action="Category_delete?id="+id;
			document.getElementById("form").submit();
		}
	}
	

</script>
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
<% PagerVO data = (PagerVO)request.getAttribute("data");
  	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 %>
</head>

<body>
 <div id="main-content">
    <!-- Main Content Section with everything -->
    <!-- Page Head -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>列表</h3>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          <div id="search_box"> 
			<form id="serachfrom" method="post" action="User_list?pm.pageNum=1"> 
				<div>
				<input type="text" name="searchKey" id="s" value="<%= (request.getAttribute("searchKey")==null)?"":request.getAttribute("searchKey") %>" class="swap_value" /> 
				<input type="image" src="resources/images/btn_search_box.gif" width="27" height="24" id="go" alt="Search" title="Search" /> 
				</div>
			</form> 
		</div> 
		
		<form id="form" action="Category_deletebatch" method="post" >
				<div>
			    <input type="hidden" name="pm.pageNum" value="<%= data.getCurrenPageNum() %>" />
			    <input type="hidden" name="searchKey" value="<%= (request.getAttribute("searchKey")=="")?"":request.getAttribute("searchKey") %>" />
			    </div>
          <table>
            <thead>
              <tr>
                <th>
                  <input class="check-all" type="checkbox" />
                </th>
                <th>分类名称</th>
                <th colspan="2" >操作</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                <div class="bulk-actions align-left">
                    <a class="button" href="javascript:deleBatch()">删除</a> 
                    </div>
                    
                    <div class="pagination">
                 <a href="javascript:go1(1)">首页</a>
				<% for(int i=data.getCurrenPageNum()-3 ; i<= data.getCurrenPageNum()+3 ;i++) { 
					if(i<1) {i = 1;}
					if(i> (data.getTotal()/10+1)) { break;}
				%>
				<%= i==data.getCurrenPageNum()?"<font color='red'>"+i+"</font>":"<a class='number' href='javascript:go1("+i+")'>"+i+"</a>" %>
				<% } %>
				<c:if test="<%= data.getCurrenPageNum() != (data.getTotal()/10+1)%>" >
				<a href="javascript:go1(<%= data.getCurrenPageNum()+1 %>);">下页</a>
				</c:if>
				<a href="javascript:go1(<%= (data.getTotal()/10+1) %>);">尾页</a>
				</div>
                </td>
              </tr>
            </tfoot>
            <tbody>
            
            <% List<Category> books = (List<Category>)data.getDatas(); int i = 1;
      		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
      			Category book = (Category) iterator.next();
      
       		%>
            
              <tr>
                <td>
                  <input type="checkbox" name="ids" value="<%= book.getId()  %>" />
                </td>
                <td><%= book.getName()  %></td>
                <td>
                   <a href="Category_modify?id=<%= book.getId()%>" title="编辑"><img src="resources/images/icons/pencil.png" alt="Edit" /></a><a href="javascript:delet(<%=book.getId()%>)" title="删除"><img src="resources/images/icons/cross.png" alt="Delete" /></a>
                </td>
              </tr>
              
              <% } %>
              
            </tbody>
          </table>
        </form>
        </div>
        <!-- End #tab1 -->
        
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
  </div>
</body>
</html>

