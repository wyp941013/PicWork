<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<base href="<%=basePath%>">

<title>索引更新</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	function ajax_indexUpdate() {
		$.messager.progress();
		$.ajax({
			type : "GET",
			url : "index/indexUpdate",
			success : function(state) {
				$.messager.progress('close');
				if (state == "success") {
					$.messager.confirm('提示', '索引更新成功！', function(r) {
						window.location.reload();
					});
				} else
					$.messager.alert('提示', '索引更新失败！请与管理员联系！', 'error');
			},
			error : function() {
				$.messager.alert('提示', '索引更新失败！请与管理员联系！', 'error');
			}
		});
	}
</script>
</head>
<body style="background-color:#F8F8FB;">
		<div class="text-center" style="margin:50px auto;width:700px;">
			<p class="text-danger text-left">${recent}</p>
			<div class="well">
				<c:if test="${empty isIndexLatest}">
					<button class="btn btn-info btn-lg" style="margin-top:50px" onclick="ajax_indexUpdate()">更新索引</button>
				</c:if> 
				<c:if test="${isIndexLatest==false}">
					<h3 class="text-info">提示:上次添加图片后还未更新索引,建议更新</h3>
					<button class="btn btn-info btn-lg" style="margin-top:50px" onclick="ajax_indexUpdate()">更新索引</button>
				</c:if>
				<c:if test="${isIndexLatest==true}">
					<h3 class="text-success">已是最新索引</h3>
				</c:if>
			</div>
			
		</div>

</body>
</html>
