<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<base href="<%=basePath%>">

<title>单图比对搜索</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery.min.js"></script>

</head>

<body>
	<form id="upload_form" action="image/singleUpload" enctype="multipart/form-data" method="post">
		<input type="file" class="file" multiple="multiple" name="files" accept="image/*">
		<input type="submit">
	</form>
</body>
</html>

