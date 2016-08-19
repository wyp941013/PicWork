<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@include file="/WEB-INF/jsp/common/common.jsp" %>
  	<link rel="stylesheet" type="text/css" href="css/ace.min.css" />
    <base href="<%=basePath%>">
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		function open_search_form(){
			$('#searchForm').dialog('open');
		}
		function edit(id){
			$('#dd').dialog({    
			    title: '用户编辑',    
			    width: 400, 
			    top: 200,   
			    closed: false,    
			    cache: false,
			    href:'user/edit?id='+id,    
			    modal: true
			});     
			$('#dd').dialog('open'); 
		}
	</script>
  </head>
  <body>
  	<div id="dd" class="easyui-dialog" data-options="closed:true">
  	</div>
  	<div class="text-center" style="margin-top:40px;margin-bottom: 30px;">
  		<button class="easyui-linkbutton" iconCls="icon-search" onclick="open_search_form()">查找用户</button>
  		<c:if test="${user.permission==2}">
  			<a style="margin-right:50px;" class="pull-right btn btn-primary"  role="button" href="user/save">添加用户</a>
  		</c:if>
  	</div>
    <table class="table table-striped table-bordered table-hover" >
    	<thead >
    		<tr>
    		<th><label>用户ID</label></th>
    		<th><label>用户名</label></th>
    		<th><label>用户邮箱</label></th>
    		<th><label>用户描述</label></th>
    		<th><label>用户权限</label></th>
    		<c:if test="${user.permission==2}">
    			<th><label>操作</label></th>
    		</c:if>
    		</tr>
    	</thead>
    	<c:forEach items="${lists}" var="userInfo">
    		<tr>
    		<td>${userInfo.userId}</td>
    		<td>${userInfo.userName}</td>
    		<td>${userInfo.email}</td>
    		<td style="max-width: 400px;">${userInfo.description}</td>
    		<td>
	    		<c:choose>
	    			<c:when test="${userInfo.permission==0}">
	    				operator
	    			</c:when>
	    			<c:when test="${userInfo.permission==1}">
	    				manager
	    			</c:when>
	    			<c:otherwise>
	    				admin
	    			</c:otherwise>
	    		</c:choose>
    		</td>
    		<c:if test="${user.permission==2}">
	    		<td>
	    			<a href="javascript:;" onclick="deleteConfirm('user/delete?id=${userInfo.userId}')">删除</a>
<!-- 	    			<a href="javascript:;" onclick="javascript:post('user/edit', {id:${userInfo.userId}})">编辑</a> -->
	    			<a href="javascript:;" onclick="edit(${userInfo.userId})">编辑</a>
	    		</td>
    		</c:if>
    		</tr>
    	</c:forEach>
    </table>
    <center>
      <ul class="pagination">
		  <li><a href="user/list?page=1&userId=${userId}&permission=${permission}&username=${username}">首页</a></li>
		  <c:choose>
		  	<c:when test="${page-2>=1}">
		  		<li><a href="user/list?page=${page-2}&userId=${userId}&permission=${permission}&username=${username}">${page-2}</a></li>
		  		<li><a href="user/list?page=${page-1}&userId=${userId}&permission=${permission}&username=${username}">${page-1}</a></li>
		  	</c:when>
		  	<c:when test="${page-2==0}">
		  		<li><a href="user/list?page=${page-1}&userId=${userId}&permission=${permission}&username=${username}">${page-1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li class="active"><a href="user/list?page=${page}&userId=${userId}&permission=${permission}&username=${username}">${page}</a></li>
		  <c:choose>
		  	<c:when test="${lastPage-page>=2}">
		  		<li><a href="user/list?page=${page+1}&userId=${userId}&permission=${permission}&username=${username}">${page+1}</a></li>
		  		<li><a href="user/list?page=${page+2}&userId=${userId}&permission=${permission}&username=${username}">${page+2}</a></li>
		  	</c:when>
		  	<c:when test="${lastPage-page==1}">
		  		<li><a href="user/list?page=${page+1}&userId=${userId}&permission=${permission}&username=${username}">${page+1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li><a href="user/list?page=${lastPage}&userId=${userId}&permission=${permission}&username=${username}">末页</a></li>
		</ul>
	</center>
    <div class="easyui-dialog" id="searchForm" style="width:400px;top:100px" data-options="title:'查找用户',closed:true">
    	<form action="${pageContext.request.contextPath}/user/list" method="post">
    		<table class="table">
    			<tr>
    				<td>用户ID</td>
    				<td><input type="text" class="form-control" name="userId" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
    			</tr>
    			<tr>
    				<td>用户名</td>
    				<td><input type="text" class="form-control" name="userName"/></td>
    			</tr>
    			<tr>
    				<td>用户权限</td>
    				<td>
    					<select name="permission">
    						<option></option>
    						<option value="0">operator</option>
    						<option value="1">manager</option>
    						<option value="2">admin</option>
    					</select> 
    				</td>
    			</tr>
    			<tr>
    				<td></td>
    				<td><input class="btn btn-primary" type="submit" value="查找"/></td>
    			</tr>
    		</table>
    	</form>
    </div>
  </body>
</html>
