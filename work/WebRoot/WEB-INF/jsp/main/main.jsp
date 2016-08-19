<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="js/myJs/main.js"></script>

<!-- bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

<!-- libraries -->
<link href="css/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet" />
    
<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/main.css" type="text/css" media="screen" /> 

<title>主页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<div id="dd"></div>
    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="brand" style="font-weight:bold;" href="javascript:void(0)">图像检索系统</a>

            <ul class="nav pull-right">               
                <li>
                    <a href="javascript:void(0)" class="dropdown-toggle hidden-phone">
                        ${user.userName}
                    </a>
                </li>
                <li class="settings hidden-phone">
                    <a contenteditable="false" onclick="contentRefresh('user/personEdit')" href="javascript:void(0)" role="button" data-toggle="tooltip" data-placement="bottom" title="个人信息">
                        <i class="icon-cog"></i>
                    </a>
                </li>
                <li class="settings hidden-phone">
                    <a href="logout" role="button" data-toggle="tooltip" data-placement="bottom" title="退出">
                        <i class="icon-share-alt"></i>
                    </a>
                </li>
            </ul>            
        </div>
    </div>
    <!-- end navbar -->

    <!-- sidebar -->
    <div class="hidden">
         <div class='pointer'>
                <div class='arrow'></div>
                <div class='arrow_border'></div>
         </div>
     </div>
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <li>
                <a class="dropdown-toggle" href="javascript:void(0)">
                    <i class="icon-picture"></i>
                    <span>图像管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="javascript:void(0)" onclick="contentRefresh('image/list')">图片显示</a></li>
                    <li><a href="javascript:void(0)" onclick="contentRefresh('image/save')">图片添加</a></li>
                </ul>
            </li>            
            <li>
                <a class="dropdown-toggle" href="javascript:void(0)">
                    <i class="icon-bar-chart"></i>
                    <span>类别管理</span>
                     <i class="icon-chevron-down"></i>
                </a>
                 <ul class="submenu">
                    <li><a href="javascript:void(0)" onclick="contentRefresh('imageclass/list')">类别显示</a></li>
                    <li><a href="javascript:void(0)" onclick="contentRefresh('imageclass/save')">类别添加</a></li>
                </ul>
            </li>
            <li>
                <a href="javascript:void(0)" onclick="contentRefresh('index/update')">
                    <i class="icon-refresh"></i>
                    <span>索引更新</span>
                </a>
            </li>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-search"></i>
                    <span>图像检索</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="javascript:void(0)" onclick="contentRefresh('compare/compareSingle')">单幅检索</a></li>
                    <li><a href="javascript:void(0)" onclick="contentRefresh('compare/compare')">批量检索</a></li>
                    <li><a href="javascript:void(0)" onclick="contentRefresh('compare/searchHistory')">检索历史</a></li>
                </ul>
            </li>
            <li>
                <a class="dropdown-toggle" href="javascript:void(0)">
                    <i class="icon-cog"></i>
                    <span>系统设置</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                	<c:if test="${user.permission!=0}"> 
                		<li><a href="javascript:void(0)" onclick="contentRefresh('user/list')">用户显示</a></li>
						<c:if test="${user.permission==2}"> 
							<li><a href="javascript:void(0)" onclick="contentRefresh('user/save')">用户添加</a></li>
	 					</c:if> 
 					</c:if>
                    <li><a href="javascript:void(0)" onclick="contentRefresh('user/personEdit')">个人信息</a></li>
                    <li><a href="javascript:void(0)" onclick="contentRefresh('user/passwordEdit')">修改密码</a></li>
                </ul>
            </li>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-calendar-empty"></i>
                    <span>样例库统计</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="javascript:void(0)" onclick="contentRefresh('statement/imageClass')">类别统计</a></li>
                    <li><a href="javascript:void(0)" onclick="contentRefresh('statement/imageUpload')">操作人员统计</a></li>
                </ul>
            </li>
            <c:if test="${user.permission!=0}"> 
            <li>
                <a href="javascript:void(0)" onclick="contentRefresh('imagelog/list')">
                    <i class="icon-book"></i>
                    <span>日志记录</span>
                </a>
            </li>
            </c:if>
        </ul>
    </div>
    <!-- end sidebar -->


	<!-- main container -->
    <div class="content" style="background-color: #fff;">
		<div class="container-fluid">
       <iframe src="statement/imageClass" style="min-height:565px" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%" id="content" name="content" onLoad="iFrameHeight()" ></iframe>
		<script type="text/javascript" language="javascript">
		    function iFrameHeight() {
		        var ifm= document.getElementById("content");
				ifm.height="100%";
		        var subWeb = document.frames ? document.frames["content"].document :ifm.contentDocument;
		            if(ifm != null && subWeb != null) {
		            ifm.height = subWeb.body.scrollHeight;
		            }
		    }
		</script> 
     </div>
    </div>

	<!-- scripts -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui-1.10.2.custom.min.js"></script>
    
    <script src="js/theme.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#dashboard-menu li a").click(function(){
                $(this).parent().prepend($(".pointer"));
            });
        });
    </script>

</body>
</body>
</html>
