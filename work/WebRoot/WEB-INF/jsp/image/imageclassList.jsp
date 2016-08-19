<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="css/table-style.css" />
<base href="<%=basePath%>">

<title>图片类别列表</title>
<script type="text/javascript">
	function edit(id){
			$('#dd').dialog({    
			    title: '编辑类别',    
			    width: 400,    
			    top:200,
			    closed: false,    
			    cache: false,    
			    href: 'imageclass/edit?id='+id,
			    modal: true,
			}); 
		}
</script>
</head>

<body>
<div id="dd"></div>
	<div class="text-center" style="margin-top:40px;margin-bottom: 30px;">
		<a class="btn btn-primary"  role="button" href="imageclass/save">类别添加</a>
	</div>
	<div style="padding:10px">
	<div style="box-shadow:6px 6px 6px 0px rgba(180, 180, 180, 0.4);">
	<table class="bordered">
		<thead>
			<tr>
			<th>
				<label>类别ID</label>
			</th>
			<th>
				<label>类别名称</label>
			</th>
			<th>
				<label>类别描述</label>
			</th>
			<c:if test="${user.permission!=0}">
				<th style="text-align:center">
					<label>操作</label>
				</th>
			</c:if>
			</tr>
		</thead>
		<c:forEach items="${lists}" var="imageClassify">
			<tr>
				<td>${imageClassify.imageClassifyId}</td>
				<td>${imageClassify.imageClassifyName}</td>
				<td>${imageClassify.imageClassifyDespretion}</td>
				<c:if test="${user.permission!=0}">
					<td style="text-align:center">
					<c:if test="${user.permission==2}">
							<a href="javascript:;" onclick="deleteConfirm('imageclass/delete?id=${imageClassify.imageClassifyId}')">删除</a>
					</c:if> 
<!-- 					<a href="javascript:;" onclick="javascript:post('imageclass/edit', {id:${imageClassify.imageClassifyId}})">编辑</a> -->
					<a href="javascript:;" onclick="edit(${imageClassify.imageClassifyId})">编辑</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
</body>
</html>
