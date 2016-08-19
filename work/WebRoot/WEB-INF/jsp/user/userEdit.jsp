<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!-- this page specific styles -->
<link href="css/new-user.css" rel="stylesheet" type="text/css">
<!-- bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<base href="<%=basePath%>">

<title>用户编辑</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	$(function(){
		var num = ${userInfo.permission};
		var select;
		if(num==0)
			select = $('#opt0');
		else if(num==1)
			select = $('#opt1');
		else
			select = $('#opt2');
		select.prop('selected',true);
	});
</script>
</head>

<body>
       
       <div style="margin: 10px">
		<form id="ff" action="user/editSubmit" method="post" class="new_user_form ">
            <input type="hidden" name="id" value="${userInfo.userId}">
			<table class="table table-bordered">
				<tr>
					<td>用户名</td>
					<td>
						<input readonly="readonly" required="true" class="span9" type="text" value="${userInfo.userName}"/>
					</td>
				</tr>
				<tr>
					<td>密码</td>
					<td>
						<input readonly="readonly" required="true" class="span9" type="text" value="${userInfo.userPassword}"/>
					</td>
				</tr>
				<tr>
					<td>权限</td>
					<td>
					 	<select name="permission">
					 		<c:choose>
					 			<c:when test="${userInfo.permission==0}">
					 				<option id="opt0" value="0" selected="true">operator</option>
					 			</c:when>
					 			<c:otherwise>
					 				<option id="opt0" value="0">operator</option>
					 			</c:otherwise>
					 		</c:choose>
	                        <c:choose>
					 			<c:when test="${userInfo.permission==1}">
					 				<option id="opt1" value="1" selected="true">manager</option>
					 			</c:when>
					 			<c:otherwise>
					 				<option id="opt1" value="1">manager</option>
					 			</c:otherwise>
					 		</c:choose>
					 		<c:choose>
					 			<c:when test="${userInfo.permission==2}">
					 				<option id="opt2" value="2" selected="true">admin</option>
					 			</c:when>
					 			<c:otherwise>
					 				<option id="opt2" value="2">admin</option>
					 			</c:otherwise>
					 		</c:choose>
	                    </select> 
					</td>
				</tr>
				<tr>
					<td>自我描述</td>
					<td>
					 	<textarea readonly="readonly" name="desp" class="span9">${userInfo.description}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<center>
						<input class="btn btn-info" type="submit" value="提交修改"/>
					</center>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
