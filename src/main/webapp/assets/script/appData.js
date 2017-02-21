/**
 * Copyright (c) 2016-2017 Tommy Lau
 */
var AppData = {
		
	//初始化方法
	init : function(){
		//初始化日期
		$('#startDate').val(DataUtils.getLastMonthFirstDateStr());
		$('#endDate').val(DataUtils.getTodayDateStr());
		
		AppData.loadUserData();
	},
	
	//获取参数方法
	getParams : function(){
		var params = {};
		
		params.startDate = $('#startDate').val();
		if(!params.startDate){
			Common.msgBox("提示:", "开始时间不能为空");
			return;
		}
		
		params.endDate = $('#endDate').val();
		if(!params.endDate){
			Common.msgBox("提示:", "结束时间不能为空");
			return;
		}
		
		params.type = $('#type').val();
		
		return params;
	},
	
	showTimePicker : function(value){
		if(value == 1){
			$('.timepciker').show();
		}else{
			$('.timepciker').hide();
		}
	},
	
	//读取方法
	loadUserData : function(){
		var params = AppData.getParams();
		
		if(!params) return; 
		
		var url = "/appData";
		if(params.type == "1"){
			url += "/dua"
		}else{
			url += "/mua"
		}
		
		Common.ajax(url, params, function(data){
			AppData.showUserData(data);
		});
	},
	
	//绘图
	showUserData : function(data){
		var recordDateArray = [];
		var mmqArray = [];
		var mmqAndroidArray = [];
		var mmqIosArray = [];
		var yygjArray = [];
		var yygjAndroidArray = [];
		var yygjIosArray = [];
		
		for(var i=0 ; i<data.length ; i++){
			var obj = data[i];
			
			recordDateArray[i] = obj.recordDate;
			
			//分类
			if(obj.appKey.indexOf('mmq_android') != -1){
				mmqAndroidArray[mmqAndroidArray.length] = obj.total;
			}else if(obj.appKey.indexOf('mmq_ios') != -1){
				mmqIosArray[mmqIosArray.length] = obj.total;
			}else if(obj.appKey.indexOf('yygj_android') != -1){
				yygjAndroidArray[yygjAndroidArray.length] = obj.total;
			}else if(obj.appKey.indexOf('yygj_ios') != -1){
				yygjIosArray[yygjIosArray.length] = obj.total;
			}
		}
		
		//去除数组重复元素
		recordDateArray = unique(recordDateArray);
		
		for(var i=0 ; i<recordDateArray.length ; i++){
			mmqArray[i] = mmqAndroidArray[i] + mmqIosArray[i];
			yygjArray[i] = yygjAndroidArray[i] + yygjIosArray[i];
		}
		
		$('#container').highcharts({
			title : {
				text : '用户活跃度',
				x : -20
			},
			xAxis : {
				categories : recordDateArray
			},
			yAxis : {
				min : 0,
				title : {
					text : '数量(个)'
				}
			},
			tooltip : {
				valueSuffix : ' 个'
			},
			legend : {
				borderWidth : 0
			},
			credits: {
				enabled: false
        	},
        	series : [
				{
					name : '妈妈圈(Android+IOS)',
					visible : true,
					data : mmqArray
				},
				{
					name : '妈妈圈(Android)',
					visible : false,
					data : mmqAndroidArray
				},
				{
					name : '妈妈圈(IOS)',
					visible : false,
					data : mmqIosArray
				},
				{
					name : '孕育管家(Android+IOS)',
					visible : true,
					data : yygjArray
				},
				{
					name : '孕育管家(Android)',
					visible : false,
					data : yygjAndroidArray
				},
				{
					name : '孕育管家(IOS)',
					visible : false,
					data : yygjIosArray
				}
			]
		});
	}
}
$(function(){
	AppData.init();
});

function unique(target) {
	var result = [];
	loop: for (var i = 0, n = target.length; i < n; i++) {
		for (var x = i + 1; x < n; x++) {
			if (target[x] === target[i])
				continue loop;
		}
		result.push(target[i]);
	}
	return result;
}