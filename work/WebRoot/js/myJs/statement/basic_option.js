var basicOption = {
	title : {
		text : '',
	},
	tooltip : {
		trigger : 'item',
		formatter : "{a} <br/>{b} : {c} ({d}%)"
	},
	legend : {
		data : ''
	},
	xAxis : [ {
		type : 'category',
		data : ''
	} ],
	yAxis : [ {
		type : 'value'
	} ],
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : true,
				readOnly : false
			},
			restore : {
				show : true
			},
			saveAsImage : {
				show : true
			}
		}
	},
	calculable : true,
	series : [ {
		name : "",
		type : "bar",
		itemStyle : {
			normal : {
				color : 'tomato',
				barBorderColor : 'tomato',
				barBorderWidth : 6,
				barBorderRadius : 0,
				label : {
					show : true,
					position : 'insideTop'
				}
			}
		},
		data : '',
		markLine : {
			data : [ 
		         {
		        	 type : 'max'
		         },
		         {
		        	 type : 'min'
		         } 
		    ]
		}
	} ],
};