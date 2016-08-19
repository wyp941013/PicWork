$(function(){
	$.getJSON("statement/getImageClassStatement",function(data){
		var myChart = echarts.init(document.getElementById('main'));
		var myOptions = basicOption;
		myOptions.title.text= "图像类别数量统计";
		myOptions.legend.data = ["数量"];
		myOptions.xAxis[0].data = data.name;
		myOptions.series[0].data = data.value;
		myOptions.series[0].name = "数量";
		myChart.setOption(myOptions);
		
		$("#title").text(data.total);
	});
});