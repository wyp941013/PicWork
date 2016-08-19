<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单图检索</title>
<link href="shearphoto_common/css/ShearPhoto.css" rel="stylesheet" type="text/css" media="all"><!--CSS文件，有点废话-->
<script  type="text/javascript" src="shearphoto_common/js/ShearPhoto.js" ></script><!--ShearPhoto的核心文件 截取，拖拽，HTML5切图，数据交互等都是由这个文件处理，全部由明哥先生汗水交织而成-->
<script  type="text/javascript"  src="shearphoto_common/js/webcam_ShearPhoto.js" ></script> <!--在线拍照那个FLASH的接口，非技术性文件-->
<script  type="text/javascript"  src="shearphoto_common/js/alloyimage.js" ></script>   <!--图片特效处理,他只负责特效，其他功能与这个文件完全无关，这个JS从腾讯AI文件  如你不要特效的话，顺带删除这个文件，在hendle.js设置关闭特效-->
<script  type="text/javascript"  src="shearphoto_common/js/handle.js" ></script>    <!--设置和处理对象方法的JS文件，要修改设置，请进入这个文件 --> 
</head>
<body style="background-image: url('img/bg.jpg');">
<!--主功能部份-->
<!--主功能部份的标签请勿随意删除，除非你对shearphoto的原理了如指掌，否则JS找不到DOM对象，会给你抱出错误-->
 <div style="margin-top:30px;" class="shearphoto_main">
<!--效果开始.............如果你不要特效，可以直接删了........-->
<div class="Effects" id="shearphoto_Effects">
  <strong class="EffectsStrong">截图效果</strong>
  <a href="javascript:;" StrEvent="原图" class="Aclick">原图</a>
  <a href="javascript:;" StrEvent="美肤">美肤效果</a>
  <a href="javascript:;" StrEvent="素描">素描效果</a>
  <a href="javascript:;" StrEvent="自然增强">自然增强</a>
  <a href="javascript:;" StrEvent="紫调">紫调效果</a>
  <a href="javascript:;" StrEvent="柔焦">柔焦效果</a>
  <a href="javascript:;" StrEvent="复古">复古效果</a>
  <a href="javascript:;" StrEvent="黑白">黑白效果</a>
  <a href="javascript:;" StrEvent="仿lomo">仿lomo</a>
  <a href="javascript:;" StrEvent="亮白增强">亮白增强</a>
  <a href="javascript:;" StrEvent="灰白">灰白效果</a>
  <a href="javascript:;" StrEvent="灰色">灰色效果</a>
  <a href="javascript:;" StrEvent="暖秋">暖秋效果</a>
  <a href="javascript:;" StrEvent="木雕">木雕效果</a>
  <a href="javascript:;" StrEvent="粗糙">粗糙效果</a>
</div>
<!--效果结束...........................如果你不要特效，可以直接删了.....................................................-->
<!--primary范围开始-->
   <div class="primary"> 
     <!--main范围开始-->
        <div id="main">
           <div class="point">
                </div>
                <!--没加载方法前-->
                <div id="SelectBox">
                <form  id="ShearPhotoForm" enctype="multipart/form-data" method="post"  target="POSTiframe"> 
                <input name="shearphoto" type="hidden" value="compare/singleUpload"> <!--示例传参数到服务端，后端文件UPLOAD.php用$_POST['shearphoto']接收-->
                        <a href="javascript:;" id="selectImage"><input type="file"  name="UpFile" accept="image/*"/></a>
                 </form>           
<!--                         <a href="javascript:;" id="PhotoLoading"></a> -->
                        <div style="display: none"><a href="javascript:;" id="camerasImage"></a></div>
                </div>
                <!--没加载方法前结束-->
                <div id="relat">
                        <div id="black">
                        </div>
                        <div id="movebox">
                                <div id="smallbox">
                                        <img src="img/default.gif" class="MoveImg" /><!--截框上的小图-->
                                </div>
                                <!--动态边框开始-->
                                 <i id="borderTop">
                                </i>
                                
                                <i id="borderLeft">
                                </i>
                                
                                <i id="borderRight">
                                </i>
                                
                                <i id="borderBottom">
                                </i>
                                <!--动态边框结束-->
                                <i id="BottomRight">
                                </i>
                                <i id="TopRight">
                                </i>
                                <i id="Bottomleft">
                                </i>
                                <i id="Topleft">
                                </i>
                                <i id="Topmiddle">
                                </i>
                                <i id="leftmiddle">
                                </i>
                                <i id="Rightmiddle">
                                </i>
                                <i id="Bottommiddle">
                                </i>
                        </div>
                        <img src="img/default.gif" class="BigImg" /><!--MAIN上的大图-->
                </div>
        </div>
         <!--main范围结束-->  
          <div style="clear: both"></div>
        <!--工具条开始-->
        <div id="Shearbar">
                <a id="LeftRotate" href="javascript:;">
                        <em>
                        </em>
                        向左旋转
                </a>
                <em class="hint L">
                </em>
                <div class="ZoomDist" id="ZoomDist">
                        <div id="Zoomcentre">
                        </div>
                        <div id="ZoomBar">
                        </div>
                        <span class="progress">
                        </span>
                </div>
                <em class="hint R">
                </em>
                <a id="RightRotate" href="javascript:;">
                        向右旋转
                        <em>
                        </em>
                </a>
                <p class="Psava">
                        <a id="againIMG"  href="javascript:;">重新选择</a>
                        <a id="saveShear" href="javascript:;">开始检索</a>
                </p>
        </div>
        <!--工具条结束-->
    </div>   
     <!--primary范围结束-->
        <div style="clear: both"></div>
        </div>
		<!--shearphoto_main范围结束-->
			<!--拍照-->
			<div id="CamBox">
			<!--假如你不要这个拍照功能。把拍照标签删除了，JS会抱出一个console.log()给你，注意查收，console.log的内容是告诉，某个DOM对象找不到-->
			<p class="lens"></p>
			<div id="CamFlash"></div>
			<p class="cambar">
			<a href="javascript:;" id="CamOk"  >拍照</a>
			<a href="javascript:;" id="setCam">设置</a>
			<a href="javascript:;" id="camClose">关闭</a>
			<span style="clear:both;"></span>
			</p>
			<div id="timing"></div>
			</div>
			<!--拍照-->

  
</body>
</html>
