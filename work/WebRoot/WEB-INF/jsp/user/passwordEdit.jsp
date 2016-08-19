<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

<title>修改密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	$(document).ready(function(){
		$('#ff').form({
			url : '${pageContext.request.contextPath }/user/passwordEditSubmit',
			onSubmit:function(){
				var isValid =$(this).form('validate');
				var psw1 = $('#psw1');
				var psw2 = $('#psw2');
				if(psw1.val()!=psw2.val())
					{
						$.messager.alert('提示','两次密码输入不一致!','info',function(){
							psw2.val('');
							psw2.focus();
						});
						isValid=false;
					}
				return isValid;
			},
			success:function(data){
				var dataObj = eval("("+data+")");
				$.messager.alert('提示',dataObj.msg,'info');
				$('#ff').form('clear');
			}
		});
	});
	
	function submitForm(){
		document.getElementById("form").submit();
	}
</script>
</head>

<body>
	<div class="content" style="margin-left:0px;">
  <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3 style="font-weight: bold;">密码修改</h3>
                </div>
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form id="ff" method="post" class="new_user_form ">
                            <input type="hidden" name="id" value="${user.userId}">
                                <div class="span12 field-box">
                                    <label>原密码:</label>
                                    <input required="true" class="span9" type="password" id="oldpsw" name="oldpsw" />
                                </div>
                                <div class="span12 field-box">
                                    <label>新密码:</label>
                                    <input required="true" class="span9" type="password" id="psw1" name="psw1"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>新密码确认:</label>
                                    <input required="true" class="span9" type="password" id="psw2" name="psw2" />
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
