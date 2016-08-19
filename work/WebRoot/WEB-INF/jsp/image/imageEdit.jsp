<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<base href="<%=basePath%>">

<title>图片编辑</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body style="background-color: #FBFBFD;">
	<div style="margin: 10px">
		<div class="text-center">
			<a href="${pageContext.request.contextPath}${imageInfo.imageAddress}" target="blank">
    			<img height="100px" src="${pageContext.request.contextPath}${imageInfo.imageAddress}"></img>
    		</a>
		</div>
		<form action="${pageContext.request.contextPath }/image/editSubmit" method="post">
			<input type="hidden" name="id" value="${imageInfo.imageId}" />
			<table class="table table-bordered">
				<tr>
					<td>图像类别 </td>
					<td>
						<select style="width:100%;" name="classId">
							<c:forEach items="${classLists}" var="imageClassify">
								<c:choose>
									<c:when test="${imageInfo.imageClassify.imageClassifyName==imageClassify.imageClassifyName}">
										<option value="${imageClassify.imageClassifyId}" selected="selected">${imageClassify.imageClassifyName}</option>
									</c:when>
									<c:otherwise>
										<option value="${imageClassify.imageClassifyId}">${imageClassify.imageClassifyName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>图像描述</td>
					<td>
						<input name="desp" value="${imageInfo.imageDespretion}" class="form-control"/>
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
