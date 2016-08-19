<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<%@include file="/WEB-INF/jsp/common/zoomimage.jsp"%>
<base href="<%=basePath%>">

<title>图片对比结果</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	$(document).ready(function(){
		 $(".fancybox").fancybox({
				wrapCSS    : 'fancybox-custom',
				closeClick : true,
				openEffect : 'none',

				helpers : {
					title : {
						type : 'inside'
					},
					overlay : {
						css : {
							'background' : 'rgba(238,238,238,0.85)'
						}
					}
				}
			});
	});
	function goBack(){
		history.go(-1);
	}
</script>

</head>
<body style="background-color:#F7F7F7;">
		<div class="text-center" style="float:left;width:20%;margin-top:20px;margin-right:20px;">
		<span class="label label-primary">查询图像</span>
<!-- 		<p style="text-align:center;">查询图像</p> -->
		<a href="${imageUrl}" class="fancybox"><img alt="图片失效" src="${imageUrl}" style="width:220px;height:250px;margin:15px;" alt="原图" title="原图" /></a>
		<div style="text-align:center;"><a href="javascript:;" onclick="goBack()">返回</a></div>
		</div>
		<div style="float:left;width:78%;border-left:2px solid rgb(254, 254, 255);">
		<div style="margin-top:20px;margin-bottom: 20px;"><span class="label label-success">检索结果</span></div>
		<c:forEach items="${imageInfoList}" var="temp1">
			<div style="width:20%;height:230px;float:left;margin:0 auto;">
				<div style="width:90%;margin: 0 auto;border:1px solid rgb(236, 224, 224);box-shadow: 1px 1px 5px #888888;background-color:white;">
				<a data-fancybox-group="gallery" href="${pageContext.request.contextPath}${temp1.imageAddress}" class="fancybox">
				<img alt="图片失效" src="${pageContext.request.contextPath}${temp1.imageAddress}" style="width:100%;height:80%;" />
				</a>
				<div style="width:100%;">
				类别：${temp1.imageClassify.imageClassifyName}<br/>
				描述：${temp1.imageDespretion}
				</div>
				</div>
			</div>
		</c:forEach>
		<div style="clear:both;"></div>
		<div class="text-center" style="margin-top:20px;">
	      <ul class="pagination">
			  <li><a href="compare/page?queryId=${queryId}&pageNow=1">首页</a></li>
			  <c:choose>
			  	<c:when test="${page-2>=1}">
			  		<li><a href="compare/page?queryId=${queryId}&pageNow=${page-2}">${page-2}</a></li>
			  		<li><a href="compare/page?queryId=${queryId}&pageNow=${page-1}">${page-1}</a></li>
			  	</c:when>
			  	<c:when test="${page-2==0}">
			  		<li><a href="compare/page?queryId=${queryId}&pageNow=${page-1}">${page-1}</a></li>
			  	</c:when>
			  </c:choose>
			  <li class="active"><a href="compare/page?queryId=${queryId}&pageNow=${page}">${page}</a></li>
			  <c:choose>
			  	<c:when test="${lastPage-page>=2}">
			  		<li><a href="compare/page?queryId=${queryId}&pageNow=${page+1}">${page+1}</a></li>
			  		<li><a href="compare/page?queryId=${queryId}&pageNow=${page+2}">${page+2}</a></li>
			  	</c:when>
			  	<c:when test="${lastPage-page==1}">
			  		<li><a href="compare/page?queryId=${queryId}&pageNow=${page+1}">${page+1}</a></li>
			  	</c:when>
			  </c:choose>
				<c:choose>
				<c:when test="${lastPage<=1}">
			 		 <li><a href="compare/page?queryId=${queryId}&pageNow=${lastPage}">末页</a></li>
			   </c:when>
			   <c:otherwise>
			   		 <li><a href="compare/page?queryId=${queryId}&pageNow=${lastPage}">末页</a></li>
			   </c:otherwise>
			   </c:choose>
		</ul>
	</div>
</div>
</body>
</html>