<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<base href="<%=basePath%>">

<title>多图比对搜索</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery.min.js"></script>

<style>
.picup {
	margin: 0 auto;
	margin-top: 50px;
	width: 600px;
	min-height: 270px;
	border: 2px solid #ccc;
	overflow: auto;
	box-shadow: 10px 10px 5px #888888;
}

.picleft {
	border: 1px dashed #E6E6E6;
	width: 220px;
	height: 180px;
	margin: 10px;
	float: left;
}

.picright {
	border: 1px dashed #E6E6E6;
	width: 320px;
	height: 180px;
	margin: 10px;
	float: left;
}

.filePicker {
	background: #00B7EE none repeat scroll 0px 0px;
	border-radius: 3px;
	box-shadow: 0px 1px 1px rgba(0, 0, 0, 0.1);
	color: #FFF;
	cursor: pointer;
	display: inline-block;
	font-size: 18px;
	height: 44px;
	line-height: 44px;
	width: 90%;
	min-width: 120px;
	margin: 0px auto;
	overflow: hidden;
	transition: #000 0.2s ease 0s;
}

#mainPic {
	width: 100%;
	float: left;
	margin: 5px auto;
	border-width: 1px 0;
	border-style: solid;
	border-color: #ccc;
}

#mainPic .bigDiv {
	width: 135px;
	height: 150px;
	margin: 5px;
	float: left;
	background: url('./img/bg.png') #CCC;
	padding: 2%;
	position: relative;
}

/* 每个图片上的操作bar */
.file_bar {
	margin: 0;
	left: 0px;
	right: 0px;
	position: absolute;
	top: 0px;
	height: 0px;
	padding: 0px;
	margin: 0;
	opacity: 0.8;
	color: #fff;
	background: none repeat scroll 0 0 #000000;
	transition: all 0.5s;
	-moz-transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-o-transition: all 0.5s;
	overflow: hidden;
	z-index: 9;
	text-align: center;
}

.file_hover {
	height: 30px;
	cursor: pointer;
}

.previewPic {
	width: 96%;
	height: 96%;
	position: relative;
	z-index: 5;
}

.file_bar img {
	height: 12px;
	width: 12px;
	position: relative;
	left: 10px;
}

.formIn {
	float: left;
	width: 220px;
	height: 180px;
	padding-left: 22px;
	margin-left: 60px;
	margin-top: 10px;
	border: 1px dashed #E6E6E6;
	overflow: hidden;
}

#Errors{
	width: 100%;
	float: left;
	margin: 5px auto;
	border-width: 1px 0;
	border-style: solid;
	border-color: #ccc;
}
</style>

<script>
	//浮层效果
	function addHoverHandle(div) {
		$(div).hover(function() {
			$(this).find(".file_bar").addClass("file_hover");
		}, function(e) {
			$(this).find(".file_bar").removeClass("file_hover");
		});
	}

	//图片选择
	$(document).ready(function() {
		$("#filePicker").bind("click", function(e) {
			$("#fileSelect").click();
			$("#fileSelect").hide();
		});
		
		$("#mainPic").bind("DOMNodeInserted",function(e){
			$("#content",parent.document).prop("height",$("body").eq(0).height()+100);
		});
		//图片预览  
		$("#fileSelect").on("change", function() {
			var files = !!this.files ? this.files : [];
			if (!files.length || !window.FileReader)
				return;
			$("#Errors").html("");	
			var ireg = /image\/.*/i, p = document.getElementById('Preview');

			var div = document.getElementById('Errors');
			for ( var i = 0, f; f = files[i]; i++) {
				if (!f.type.match(ireg)) {
					//设置错误信息  
					var p = document.createElement('p');
					p.innerHTML = '<p style="color:red;text-align:center;line-height:20px;">' + f.name + '不是图片文件.</p>';

					div.appendChild(p);

					continue;
				}

				var reader = new FileReader();
				$("#mainPic").html("");
				reader.onload = (function(file) {
					return function() {
						//每张图片最外边的div
						var div = document.createElement('div');
						div.setAttribute('class', 'bigDiv');
						//div里的图片
						var img = document.createElement('img');
						img.setAttribute('src', this.result);
						img.setAttribute('class', 'previewPic');
						var div1 = document.createElement('div');
						div1.setAttribute('class', 'file_bar');
						var textNode = document.createTextNode(file.name);
						div1.appendChild(textNode);
						// var imgX=document.createElement('img');
						// imgX.setAttribute('src','./img/delete_blue.png');
						// div1.appendChild(imgX);
						div.appendChild(div1);
						div.appendChild(img);
						document.getElementById('mainPic').appendChild(div);
						addHoverHandle(div);//绑定浮层效果
					};
				})(f);
				//读取文件内容  
				reader.readAsDataURL(f);
			}
		});
		
	
	$("#fileUpload").bind("click", function() {
		
	  var files = !! $("input[type=file]").get(0).files ? $("input[type=file]").get(0).files : [];
			if (!files.length || !window.FileReader)
			{
				$("#Errors").html('<p style="color:red;text-align:center;line-height:20px;">您还未选择任何图片...</p>');		
				return false;
			}
			var ireg = /image\/.*/i;
			var div = document.getElementById('Errors');
	  	for ( var i = 0, f; f = files[i]; i++) {
				if (!f.type.match(ireg)) {
					div.innerhtml="";
					//设置错误信息  
					var p = document.createElement('p');
					
					p.innerHTML = '<p style="color:red;text-align:center;line-height:20px;">' + f.name + '不是图片文件,请重新选择.</p>';

					div.appendChild(p);
					return false;
				}
			}	
		return true;
	});
	});
	
</script>


</head>

<body style="background-color:#F7F7F7;">
	<div id="picup" class="picup">
		<h1 align="center">多图上传</h1>
		<div class="picleft">
			<div style="width:100%;padding-top:20px;text-align:center;">
				<img src="./img/add_img.png" style="width:120px;" />
				<button id="filePicker" class="filePicker">选择图片</button>
			</div>
		</div>
		<div class="formIn">
			<form action="${pageContext.request.contextPath }/compare/mulsearch"
				method="post" enctype="multipart/form-data" onsubmit="return checkImgs();">
				<font
					style="display:block;width:110px;position:relative;left:90px;top:30px;">支持jpg、jpeg、png等图片格式,请正确上传！</font>
				<img src="img/jpg.png"
					style="width:100px;height:100px;position:relative;left:-20px;top:-52px;" />
				<input type="submit" id="fileUpload" class="filePicker"
					style="position:relative;left:0px;top:-38px;" value="上传图片" /> <input
					type="file" id="fileSelect" style="opacity:0;" name="files[]" accept="image/*"
					multiple />
			</form>
		</div>
		<div id="Errors"></div>
		<div id="mainPic"></div>
	</div>
</body>
</html>

