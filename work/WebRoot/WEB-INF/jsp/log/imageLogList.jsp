<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@include file="/WEB-INF/jsp/common/common.jsp" %>
  	<link rel="stylesheet" type="text/css" href="css/ace.min.css" />
    <base href="<%=basePath%>">
    
    <title>图片日志信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function open_search_form(){
			$('#searchForm').dialog('open');
		}
		
		function submitForm(){
			document.getElementById("form").submit();
		}
	</script>
  </head>
  <body>
  	<div style="padding:10 50px;">
  	<div class="text-left" style="margin-top:40px;margin-bottom: 30px;">
  		<button class="easyui-linkbutton" iconCls="icon-search" onclick="open_search_form()">查找记录</button>
  	</div>
    <table class="table table-striped table-bordered table-hover">
    	<thead>
    	<tr>
    		<th><label>日志ID</label></th>
    		<th><label>操作类别</label></th>
    		<th><label>操作人ID</label></th>
    		<th><label>操作时间</label></th>
    	</tr>
    	</thead>
    	<c:forEach items="${lists}" var="imageLog">
    		<tr>
    		<td>${imageLog.imageLogId}</td>
    		<td>
    			<c:choose>
    				<c:when test="${imageLog.content==0}">
    					添加图像
    				</c:when>
    				<c:when test="${imageLog.content==1}">
    					删除图像
    				</c:when>
    				<c:when test="${imageLog.content==2}">
    					修改图像
    				</c:when>
    				<c:otherwise>
    					索引更新
    				</c:otherwise>
    			</c:choose>
    		</td>
    		<td>${imageLog.editorUserId}</td>
    		<td><fmt:formatDate value="${imageLog.createTime}" pattern="yyyy-MM-dd"/></td>
    		</tr>
    	</c:forEach>
    </table>
    </div>
    <center>
      <ul class="pagination">
		  <li><a href="imagelog/list?page=1&content=${content}&editorId=${editorId}&createTime=${createTime}">首页</a></li>
		  <c:choose>
		  	<c:when test="${page-2>=1}">
		  		<li><a href="imagelog/list?page=${page-2}&content=${content}&editorId=${editorId}&createTime=${createTime}">${page-2}</a></li>
		  		<li><a href="imagelog/list?page=${page-1}&content=${content}&editorId=${editorId}&createTime=${createTime}">${page-1}</a></li>
		  	</c:when>
		  	<c:when test="${page-2==0}">
		  		<li><a href="imagelog/list?page=${page-1}&content=${content}&editorId=${editorId}&createTime=${createTime}">${page-1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li class="active"><a href="imagelog/list?page=${page}&content=${content}&editorId=${editorId}&createTime=${createTime}">${page}</a></li>
		  <c:choose>
		  	<c:when test="${lastPage-page>=2}">
		  		<li><a href="imagelog/list?page=${page+1}&content=${content}&editorId=${editorId}&createTime=${createTime}">${page+1}</a></li>
		  		<li><a href="imagelog/list?page=${page+2}&content=${content}&editorId=${editorId}&createTime=${createTime}">${page+2}</a></li>
		  	</c:when>
		  	<c:when test="${lastPage-page==1}">
		  		<li><a href="imagelog/list?page=${page+1}&content=${content}&editorId=${editorId}&createTime=${createTime}">${page+1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li><a href="imagelog/list?page=${lastPage}&content=${content}&editorId=${editorId}&createTime=${createTime}">末页</a></li>
		</ul>
	</center>
    <div class="easyui-dialog" id="searchForm" style="height:240px;width:300px;top:100px" data-options="title:'查找日志',closed:true">
    	<form id="form" action="${pageContext.request.contextPath}/imagelog/list" method="post">
    		<table class="table">
    			<tr>
    				<td width="40%">操作类别</td>
    				<td width="60%">
    					<select name="content" style="width:100%;">
    						<option></option>
    						<option value="0">图像添加</option>
    						<option value="1">图像删除</option>
    						<option value="2">图像修改</option>
    						<option value="3">索引更新</option>
    					</select> 
    				</td>
    			</tr>
    			<tr>
    				<td>操作人ID</td>
    				<td><input type="text" class="form-control"name="editorId" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
    			</tr>
    			<tr>
    				<td>操作日期</td>
    				<td><input type="text" name="createTime" data-options="editable:false" class="easyui-datebox"/></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td>
						<button class="easyui-linkbutton" iconCls="icon-search" onclick="submitForm()">查询</button>
					</td>
    			</tr>
    		</table>
    	</form>
    </div>
  </body>
</html>
