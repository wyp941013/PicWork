<%@ page language="java" import="java.util.*" pageEncoding="UTF8"%>
<% if(session.getAttribute("user")!=null){
	request.getRequestDispatcher("main/main").forward(request, response);
}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/signin.css" >
<base href="<%=basePath%>">
<title>登陆界面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<script type="text/javascript">
	$(document).ready(function(){
		if (window != top)
		top.location.href = location.href;
		 
	 	$('#login_form').form({
			url : 'login.action',
			success : function(msg){
				if(msg==null||msg=="")
				window.location="main/main";
				else{
					$.messager.show({
					title : '提示',
					msg : msg,
					showType:'show',
					timeout : 2000,
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
					$('#login_form').form('clear');
				}
			}
		}); 
	});
</script>
</head>
<body>
<div class="signin">
	<div class="signin-head">
		<div class="sigin-title text-center">
			<font class="sigin-font">图像检索系统</font>
		</div>
	</div>
	<form id="login_form" method="post" class="form-signin" role="form">
		<input type="text" name="username" class="form-control" placeholder="请输入用户名" required autofocus />
		<input type="password" name="password" class="form-control" placeholder="请输入密码" required />
		<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
	</form>
</div>
</body>
</html>
