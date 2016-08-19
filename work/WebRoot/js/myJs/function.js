/**
 * 将a标签的GET提交方式变为POST提交
 */
function post(url, params) {
	var temp = document.createElement('form');
	temp.action = url;
	temp.method = 'post';
	temp.style.display = 'none';
	for (var x in params) {
	var opt = document.createElement('textarea');
	opt.name = x;
	opt.value = params[x];
	temp.appendChild(opt);
	}
	document.body.appendChild(temp);
	temp.submit();
	return temp;
}

/**
 * 删除时进行确认
 * @param url
 */
function deleteConfirm(url){
	$.messager.confirm('警告','确定要删除吗？',function(r){
		if(r){
			window.location=url;
		}
	});
}