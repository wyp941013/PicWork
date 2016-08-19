<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@include file="/WEB-INF/jsp/common/common.jsp" %>
  	<%@include file="/WEB-INF/jsp/common/zoomimage.jsp" %>
  	<link rel="stylesheet" type="text/css" href="css/ace.min.css" />
    <base href="<%=basePath%>">
    <title>检索历史</title>
    
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
		function submitForm(){
			$.messager.confirm("提示","确定要删除吗？",function(r){
				if(r){
					document.getElementById("form-list").submit();
				}
			}).parent().css("top","300px").siblings(".window-shadow").css("top","300px");
		}
		function select_all(){
			var check = $('#cb');
			var ids = $('input[name="ids"]');
			if(check.prop('checked'))
			{
				ids.prop("checked", true);
			}
			else
			{
				ids.prop("checked", false);
				check.prop("checked", false);
			}
		}
	</script>
  </head>
  
  <body style="text-align: left">
  	<c:if test="${user.permission==2}">
   		 <div>
    		<button class="easyui-linkbutton" style="margin-top: 10px;" iconCls="icon-remove" onclick="submitForm()">删除所选项</button>
		</div>
    </c:if>
  	<form style="margin-top:10px;" id="form-list" action="${pageContext.request.contextPath}/compare/multiQueryDelete" method="post">
	   <table  class="table table-striped table-bordered table-hover">
   		<thead>
    	<tr>
    		<c:if test="${user.permission==2}">
    			<th><label><input type='checkbox' id="cb" onchange="select_all()" /></label></th>
    		</c:if>
    		<th>检索ID</th>
    		<th>图像</th>
    		<th>状态</th>
    		<th>检索人ID</th>
    		<th>检索时间</th>
    		<c:if test="${user.permission!=0}">
    			<th>操作</th>
    		</c:if>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach items="${lists}" var="imageQueryInfo">
    		<tr>
    		<c:if test="${user.permission==2}">
    			<td><label><input type='checkbox' name='ids' value="${imageQueryInfo.queryId}" /></label></td>
    		</c:if>
    		<td>${imageQueryInfo.queryId}</td>
    		<td>
    			<a data-fancybox-group="gallery" href="${pageContext.request.contextPath}${imageQueryInfo.imageUploadingAddress}" class="fancybox">
    				<img alt="图片失效" height="70px" src="${pageContext.request.contextPath}${imageQueryInfo.imageUploadingAddress}"></img>
    			</a>
    		</td>
    		<c:choose>
    			<c:when test="${imageQueryInfo.state==1}">
    				<td>有</td>
    			</c:when>
    			<c:otherwise>
    				<td>无</td>
    			</c:otherwise>
    		</c:choose>
    		<td>${imageQueryInfo.editorUserId}</td>
    		<td><fmt:formatDate value="${imageQueryInfo.createTime}" pattern="yyyy-MM-dd"/></td>
    		<c:if test="${user.permission!=0}">
	    		<td>
	    		 <c:if test="${user.permission==2}"> 
	    				<a href="javascript:;" onclick="deleteConfirm('compare/deleteSearchHistory?id=${imageQueryInfo.queryId}')">删除</a>
	    		</c:if> 
	    			<a  href="compare/page?queryId=${imageQueryInfo.queryId}">查看</a>
	    		</td>
    		</c:if>
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
    
    <center>
      <ul class="pagination">
		  <li><a href="compare/searchHistory?page=1">首页</a></li>
		  <c:choose>
		  	<c:when test="${page-2>=1}">
		  		<li><a href="compare/searchHistory?page=${page-2}">${page-2}</a></li>
		  		<li><a href="compare/searchHistory?page=${page-1}">${page-1}</a></li>
		  	</c:when>
		  	<c:when test="${page-2==0}">
		  		<li><a href="compare/searchHistory?page=${page-1}">${page-1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li class="active"><a href="compare/searchHistory?page=${page}">${page}</a></li>
		  <c:choose>
		  	<c:when test="${lastPage-page>=2}">
		  		<li><a href="compare/searchHistory?page=${page+1}">${page+1}</a></li>
		  		<li><a href="compare/searchHistory?page=${page+2}">${page+2}</a></li>
		  	</c:when>
		  	<c:when test="${lastPage-page==1}">
		  		<li><a href="compare/searchHistory?page=${page+1}">${page+1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li><a href="compare/searchHistory?page=${lastPage}">末页</a></li>
		</ul>
	</center>
    </form>
    
  </body>
</html>
