<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<base href="<%=basePath%>">

<title>类别添加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	function ajax_validate_classname() {
		var classname = $("input[name='name']").val();
		$.ajax({
			type : 'post',
			url : 'imageclass/validateClassName',
			data : 'classname=' + classname,
			success : function(msg) {
				if (msg != null && msg != "")
					$.messager.alert('警告', msg);
			}
		});
	}
</script>
</head>
<body style="background-color: #FBFBFD;">
	<div class="easyui-dialog" data-options="title:'类别添加',closable:false,maximizable:true,top:'150px'" style="width:400px;">
		<form action="${pageContext.request.contextPath }/imageclass/saveSubmit" method="post">
			<table class="table table-bordered">
				<tr>
					<td>类别名称</td>
					<td>
						<input required="true" class="form-control" name="name" onblur="ajax_validate_classname()" />
						<span style="font-size:15px;color:red;">${clas_error}</span>
					</td>
				</tr>
				<tr>
					<td>类别描述</td>
					<td><input type="text" class="form-control" name="desp" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center">
						<input class="btn btn-info" type="submit" value="添加类别"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
