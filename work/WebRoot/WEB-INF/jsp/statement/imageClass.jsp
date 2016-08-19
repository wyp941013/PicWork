<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<title>类别统计</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript" src="js/echarts/echarts-all.js"></script>
<script type="text/javascript" src="js/myJs/statement/basic_option.js"></script>
<script type="text/javascript" src="js/myJs/statement/imageClass.js"></script>
<script type="text/javascript">

</script>

</head>
<body>
	<h3 class="text-center">
		共 <span id="title" class="text-danger"></span> 张图片
	</h3>
	<div id="main" style="height:400px"></div>
</body>
</html>
