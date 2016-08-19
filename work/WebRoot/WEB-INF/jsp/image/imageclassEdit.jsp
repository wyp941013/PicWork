<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<base href="<%=basePath%>">

<title>类别编辑</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<script type="text/javascript">
	function ajax_validate_classname() {
		var classname = $("input[name='name']").val();
		$.ajax({
			type : 'post',
			url : 'imageclass/validateClassName1',
			data : 'classname=' + classname+'&orginClassname=${imageClassify.imageClassifyName}',
			success : function(msg) {
				if (msg != null && msg != "")
					$.messager.alert('警告', msg);
			}
		});
	}
</script>

<body style="background-color: #FBFBFD;">

	<div style="margin: 10px">
		<form action="${pageContext.request.contextPath }/imageclass/editSubmit" method="post">
			<input type="hidden" name="id" value="${imageClassify.imageClassifyId }" />
			<table class="table table-bordered">
				<tr>
					<td>类别名称</td>
					<td>
						<input required="true" value="${imageClassify.imageClassifyName }" class="form-control" name="name" onblur="ajax_validate_classname()" />
						<span style="font-size:15px;color:red;">${clas_error}</span>
					</td>
				</tr>
				<tr>
					<td>类别描述</td>
					<td>
						<input  value="${imageClassify.imageClassifyDespretion}" type="text" class="form-control" name="desp" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center">
						<input class="btn btn-info" type="submit" value="提交修改"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
