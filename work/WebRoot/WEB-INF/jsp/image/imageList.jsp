<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	
  	<%@include file="/WEB-INF/jsp/common/common.jsp" %>
    <%@include file="/WEB-INF/jsp/common/zoomimage.jsp" %>
    <title>图片列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="css/ace.min.css" />
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
		function open_search_form(){
			$('#searchForm').dialog('open');
		}
		
		function submitForm(){
			$.messager.confirm("警告","确定要删除吗？",function(r){
				if(r){
					document.getElementById("form-list").submit();
				}
			}).parent().css("top","300px").siblings(".window-shadow").css("top","300px");
		}
		
		function edit(id){
			$('#dd').dialog({    
			    title: '编辑图片',    
			    width: 400,    
			    closed: false,    
			    cache: false,    
			    href: 'image/edit?id='+id,
			    doSize:true,
			    modal: true,
			    inline:true,
			    top:200,
			}); 
			
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
  
  <body>
  <div id="dd"></div>
  	<div style="text-align:left;margin-top:10px;margin-bottom: 10px;">
  		  <c:if test="${user.permission==2}">
    			<button class="easyui-linkbutton" iconCls="icon-remove" onclick="submitForm()">删除所选项</button>
   		 </c:if>
  		<button class="easyui-linkbutton" style="margin-left: 200px;" iconCls="icon-search" onclick="open_search_form()">查找图片</button>
  	</div>
  	<form id="form-list" action="${pageContext.request.contextPath}/image/multiDelete" method="post">
	   <table class="table table-striped table-bordered table-hover">
   		<thead>
    	<tr>
    		<c:if test="${user.permission==2}">
    			<th><label><input type="checkbox" id="cb" onchange="select_all()"/></label></th>
    		</c:if>
    		<th>图像</th>
    		<th>类别</th>
    		<th>描述</th>
    		<th>上传人ID</th>
    		<th>上传时间</th>
    		<c:if test="${user.permission!=0}">
    			<th>操作</th>
    		</c:if>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach items="${lists}" var="imageInfo">
    		<tr>
    		<c:if test="${user.permission==2}">
<!--     			<td>${imageInfo.imageId}</td> -->
				<td><label><input type='checkbox' name='ids' value="${imageInfo.imageId}" /></label></td>
    		</c:if>
    		<td>
    			<a data-fancybox-group="gallery" class="fancybox" href="${pageContext.request.contextPath}${imageInfo.imageAddress}" title="${imageInfo.imageId}" class="fancy">
    				<img alt="图片失效" height="70px" src="${pageContext.request.contextPath}${imageInfo.imageAddress}"></img>
    			</a>
    		</td>
    		<td>${imageInfo.imageClassify.imageClassifyName}</td>
    		<td>${imageInfo.imageDespretion}</td>
    		<td>${imageInfo.editorUserId}</td>
    		<td><fmt:formatDate value="${imageInfo.createTime}" pattern="yyyy-MM-dd"/></td>
    		<c:if test="${user.permission!=0}">
	    		<td>
	    			<c:if test="${user.permission==2}">
	    				<a href="javascript:;" onclick="deleteConfirm('image/delete?id=${imageInfo.imageId}')">删除</a>
	    			</c:if>
<!-- 	    			<a  href="javascript:;" onclick="javascript:post('image/edit', {id:${imageInfo.imageId}})">编辑</a> -->
	    			<a  href="javascript:;" onclick="edit(${imageInfo.imageId})">编辑</a>
	    		</td>	
    		</c:if>
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
    
    <center>
      <ul class="pagination">
		  <li><a href="image/list?page=1&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">首页</a></li>
		  <c:choose>
		  	<c:when test="${page-2>=1}">
		  		<li><a href="image/list?page=${page-2}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">${page-2}</a></li>
		  		<li><a href="image/list?page=${page-1}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">${page-1}</a></li>
		  	</c:when>
		  	<c:when test="${page-2==0}">
		  		<li><a href="image/list?page=${page-1}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">${page-1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li class="active"><a href="image/list?page=${page}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">${page}</a></li>
		  <c:choose>
		  	<c:when test="${lastPage-page>=2}">
		  		<li><a href="image/list?page=${page+1}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">${page+1}</a></li>
		  		<li><a href="image/list?page=${page+2}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">${page+2}</a></li>
		  	</c:when>
		  	<c:when test="${lastPage-page==1}">
		  		<li><a href="image/list?page=${page+1}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">${page+1}</a></li>
		  	</c:when>
		  </c:choose>
		  <li><a href="image/list?page=${lastPage}&classId=${classId}&desp=${desp}&editorId=${editorId}&createTime=${createTime}">末页</a></li>
		</ul>
	</center>
    </form>
    
    <div class="easyui-dialog" id="searchForm" style="height:290px;width:380px;top:100px" data-options="title:'查找图片',closed:true">
    	<form action="${pageContext.request.contextPath}/image/list" method="post">
    		<table class="table">
    			<tr>
    				<td width="40%">图像类别</td>
    				<td width="60%">
    					<input class="easyui-combobox" name="classId"   
 						data-options="valueField:'id',textField:'text',url:'image/getAllClass'" />  
    				</td>
    			</tr>
    			<tr>
    				<td>图像描述</td>
    				<td><input type="text" style="width:150px" class="form-control"name="desp"/></td>
    			</tr>
    			<tr>
    				<td>上传人ID</td>
    				<td><input type="text" style="width:150px" class="form-control"name="editorId" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
    			</tr>
    			<tr>
    				<td>上传时间</td>
    				<td><input type="text" name="createTime" data-options="editable:false" class="easyui-datebox"/></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td><input type="submit" class="btn btn-primary" value="查找"/></td>
    			</tr>
    		</table>
    	</form>
    </div>
  </body>
</html>
