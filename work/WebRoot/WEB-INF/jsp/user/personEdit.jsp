<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!-- this page specific styles -->
<link href="css/new-user.css" rel="stylesheet" type="text/css">
<!-- bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />
<base href="<%=basePath%>">

<title>个人信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	$(document).ready(function(){
		$('#ff').form({
			url : '${pageContext.request.contextPath }/user/personEditSubmit',
			success:function(data){
				var dataObj = eval("("+data+")");
				console.log(dataObj);
				$.messager.alert('提示',dataObj.msg);
			}
		});
	});
	
	function ajax_validate_username(){
		var username = $("input[name='username']").val();
		$.ajax({
			type:'post',
			url:'user/validateUsername1',
			data:'username='+username,
			success:function(msg){
				if(msg!=null&&msg!=""){
					$.messager.alert('提示',msg); 
					document.getElementById('msg').innerHTML=msg;
					$("#msg").parent().removeClass("hidden");
				}else{
					$("#msg").parent().addClass("hidden");
				}
			}
		});
	}
	
	function submitForm(){
		document.getElementById("ff").submit();
	}
</script>
</head>
<body>
<div class="content" style="margin-left:0px;">
  <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3 style="font-weight: bold;">个人信息</h3>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form id="ff" method="post" class="new_user_form ">
                            <input type="hidden" name="id" value="${user.userId}">
                                <div class="span12 field-box">
                                    <label>用户名:</label>
                                    <input required="true" class="span9" type="text" onblur="ajax_validate_username()" name="username" value="${user.userName}"/>
                                </div>
                                <div class="span12 field-box hidden">
                                	<label></label>
                                	<span class="span9" style="font-size:15px;color:red;" id="msg"></span>
                                </div>
                                <div class="span12 field-box">
                                    <label>密码:</label>
                                    <input required="true" class="span9" readonly="readonly"  type="password" value="${user.userPassword}"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>邮箱:</label>
                                    <input class="span9" type="email" name="email" value="${user.email}"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>权限:</label>
                                        <c:choose> 
										<c:when test="${user.permission==0}">  
											<input class="span9" type="text" readonly="readonly" value="operator"/>  
										</c:when>
										<c:when test="${user.permission==1}">
											<input class="span9" type="text" readonly="readonly" value="manager"/>
										</c:when>
										<c:otherwise>
											<input class="span9" type="text" readonly="readonly"  value="admin"/>
										</c:otherwise>
				 					</c:choose> 
                                </div>
                                <div class="span12 field-box textarea">
                                    <label>自我描述:</label>
                                    <textarea name="desp" class="span9">${user.description}</textarea>
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="submit" class="btn-glow primary" value="修改" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
       </div>
</body>
</html>
