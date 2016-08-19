<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>

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

<title>图片添加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript">
	function ajax_validate_username(){
		var username = $("input[name='username']").val();
		$.ajax({
			type:'post',
			url:'user/validateUsername',
			data:'username='+username,
			success:function(msg){
				if(msg!=null&&msg!=""){
					$.messager.alert('警告',msg); 
					document.getElementById('msg').innerHTML=msg;
					$("#msg").parent().removeClass("hidden");
				}else{
					$("#msg").parent().addClass("hidden");
				}
			}
		});
	}
</script>
</head>
<body>
<div class="content" style="margin-left:0px;">
  <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3 style="font-weight: bold;">用户添加</h3>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form action="${pageContext.request.contextPath }/user/saveSubmit" method="post" class="new_user_form ">
                                <div class="span12 field-box">
                                    <label>用户名:</label>
                                    <input required="true" class="span9" type="text" name="username" onblur="ajax_validate_username()"/>
                                </div>
                                <div class="span12 field-box hidden">
                                	<label></label>
                                	<span class="span9" style="font-size:15px;color:red;" id="msg"></span>
                                </div>
                                <div class="span12 field-box">
                                    <label>密码:</label>
                                    <input required="true" class="span9" type="text" name="password"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>邮箱:</label>
                                    <input class="span9" type="email" name="email" />
                                </div>
                                <div class="span12 field-box">
                                    <label>权限:</label>
                                    <div class="ui-select span5">
                                        <select name="permission">
                                            <option value="0">operator</option>
                                            <option value="1">manager</option>
                                            <option value="2">admin</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="span12 field-box textarea">
                                    <label>自我描述:</label>
                                    <textarea name="desp" class="span9"></textarea>
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="submit" class="btn-glow primary" value="创建用户" />
                                    <span>&nbsp</span>
                                    <input type="reset" value="取消" class="reset" />
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
