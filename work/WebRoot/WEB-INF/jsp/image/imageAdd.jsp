<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<base href="<%=basePath%>">

<title>图片添加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
.img{
	height:100px;
	max-width:200px;
}
</style>
<script type="text/javascript">
	index = 1;
	$(document).ready(function() {
		//上传图片时，确定按钮的点击事件
		$('#btn_ok').bind('click',function(){
			
			//判断是否选择图片
			var files_array = $('#upload_dialog').find(".file");
			var files = files_array.eq(files_array.length-1).prop("files");
			if(files.length<=0){
				$.messager.alert("警告","请选择图像文件！");
				return false;
			};
			//判断是否选择类别
			var classId = $('#upload_dialog').find('select').val();
			if(classId==null||classId==""){
				$.messager.alert("警告","请选择类别！");
				return false;
			}
			//遍历显示
			for(var i=0;i<files.length;i++){
				var src = window.URL.createObjectURL(files[i]);
				
				$('<tr/>',{
					id:'tr_'+index,
				}).appendTo($('#p').find("tbody"));
				
				$('<img/>',{
					class:'img',
					src: src,
				}).appendTo($('#tr_'+index)).wrap("<td></td>");
				
// 				$("<input class=\"easyui-combobox\" requried=\"true\" name=\"classId\" data-options=\"valueField:'id',textField:'text',url:'image/getAllClass'\" />")
// 				.appendTo($('#tr_'+index)).wrap("<td></td>");
				$('#upload_dialog').find('select').clone(true).val(classId)
					.css('margin-top','40px').appendTo($('#tr_'+index)).wrap("<td></td>");
				
				
				if(i==0){
					var desp_val = $('#upload_dialog').find("input[name='desp']").val();
					$('<input/>',{
						type:'text',
						name:'desp',
						value:desp_val,
						style:'margin-top:40px',
					}).appendTo($('#tr_'+index)).wrap("<td></td>");
				}else{
					$('<input/>',{
						type:'text',
						name:'desp',
						style:'margin-top:40px',
					}).appendTo($('#tr_'+index)).wrap("<td></td>");
				}
// 				$.parser.parse($('#tr_'+index));
				
// 				$('#tr_'+index).find(".easyui-combobox").combobox('select',classId);
				index++;
			}
			//重新clone一个input file元素过来,并把其他的input file元素隐藏
			var obj = $('#file').clone(true);
			obj.appendTo('#select_image').siblings().css('display','none');
			$('#upload_dialog').dialog('close');
			
			$("#content",parent.document).prop("height",$("body").eq(0).height()+100);
		});
		
		//上传图片时，取消按钮的点击事件
		$('#btn_cancel').bind('click',function(){
			$('#upload_dialog').dialog('close');
		});
	});
	function openDialog(){
		$('#upload_dialog').dialog('open');
	}
	function submitForm(){
		$.messager.progress().parent().css("top","260px");
		$('#upload_form').form('submit',{
			onSubmit:function(){
				if($('#image_table').find('tbody>tr').length<=0){
					$.messager.alert('警告','未选择图片!');
					$.messager.progress('close');
					return false;
				}
			},
			success: function(data){
				if(data=='true'){
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
 					$.messager.confirm('提示','上传成功!',function(r){
						top.window.scrollTo(0,0);
						window.location.href='image/list';
 					});
				}
				else{
					$.messager.progress('close');
					$.messager.confirm('提示','上传失败,文件太大！！',function(r){
						top.window.scrollTo(0,0);
						window.location='image/save';
					});
				}
			
			},
			error: function(){
				$.messager.alert('提示','上传失败,文件太大！','error');
			}
			
		});
	}
</script>
</head>
<body>
	<div class="hidden">
		<input type="file" class="file" id="file" multiple="multiple" name="files[]" accept="image/*">
	</div>
	<form id="upload_form" action="image/upload" enctype="multipart/form-data" method="post" style="margin-top:50px;">
		<div id="p" class="easyui-panel" title="" style="min-height:480px;padding:10px;background:#fafafa;"   
	        data-options="iconCls:'icon-save',closable:true,    
	                collapsible:true,minimizable:true,maximizable:true"> 
	        <button class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="openDialog()">添加图片</button> 
	        <button class="easyui-linkbutton" plain="true" iconCls="icon-file-up" onclick="submitForm()">上传</button> <br/>
	        <table id="image_table" class="table">
	        	<thead>
	        		<tr>
	        			<th>图像</th>
	        			<th>类别</th>
	        			<th>描述</th>
	        		</tr>
	        	</thead>
	        	<tbody>
	        	</tbody>
	        </table>
		</div> 
		
		<div id="upload_dialog" class="easyui-dialog" title="图片上传" style="width:420px;height:250px"   
        	data-options="iconCls:'icon-add',inline:true,closed:true,top:'100'">   
        	<table class="table">
        		<tbody>
        		<tr>
        			<td style="width:50%;">选择图片</td>
        			<td style="width:50%;" id="select_image"><input type="file" class="file" multiple="multiple" name="files[]" accept="image/*"></td>
        		</tr>
        		<tr>
        			<td>选择类别</td>
        			<td>
<!--         				<input class="easyui-combobox" id="imgClass" name="classId"    -->
<!--  						data-options="valueField:'id',textField:'text',url:'image/getAllClass'" /> -->
						<select name="classId" style="width:150px">
							<c:forEach items="${classLists}" var="imageClassify">
								<option value="${imageClassify.imageClassifyId}">${imageClassify.imageClassifyName}</option>
							</c:forEach>
						</select>
 					</td>
        		</tr>
        		<tr>
        			<td>输入描述 </td>
        			<td><input name="desp" style="width:150px"></input></td>
        		</tr>
        		</tbody>
        	</table>
        	<div style="padding:5px;text-align:center;">
				<button class="easyui-linkbutton" id="btn_ok">确定</button>
				 &nbsp &nbsp &nbsp &nbsp
        		<button class="easyui-linkbutton" id="btn_cancel">取消</button>
			</div>
		</div>  
	</form>
</body>
</html>
