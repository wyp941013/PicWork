<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<base href="<%=basePath%>">

<title>图片对比结果</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
<style>
* {
	margin: 0;
	padding: 0;
}

#searchResult {
	list-style: none;
	
}

#searchResult li {
	height: 120px;
	line-height:120px;
	margin-top:5px;
}

#searchResult li div {
	float: left;
	width: 16%;
	height: 100%;
	text-align:center;
}
</style>
<script>
function button_click(obj){
	var button = obj.value;
	var linkstr = "compare/page?queryId="+button;
	location.href=linkstr;
}
</script>
</head>
<body>
	<h1 style="text-align:center;">检索结果</h1>
	<ul id="searchResult">
		<li><div>检索ID</div>
			<div>查询图像</div>
			<div>匹配结果</div>
			<div>检索人员ID</div>
			<div>检索时间</div>
			<div>操作</div>
		</li>
		<c:forEach items="${lists}" var="temp">
			<li><div>${temp.queryId}</div>
				<div><img src="${pageContext.request.contextPath}${temp.imageUploadingAddress}" style="width:100px;height:120px;"/></div>
				<div>
					<c:choose>
			       <c:when test="${temp.state==1}">
			   					    有
			       </c:when>
			       <c:otherwise>
			       				   无
			       </c:otherwise>
					</c:choose>
				</div>
				<div>${temp.editorUserId}</div>
				<div><fmt:formatDate value="${temp.createTime}" pattern="yyyy-MM-dd"/></div>
				<div>
				<c:choose>
			       <c:when test="${temp.state==1}">
			   		<button value="${temp.queryId}" style="width:130px;height:40px;line-height:30px;font:normal;" onclick="button_click(this);">查看</button>
			       </c:when>
				</c:choose>
				</div>
			</li>
		</c:forEach>
	</ul>
</body>
</html>
