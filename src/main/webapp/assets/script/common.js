/**
 * Copyright (c) 2016-2017 Tommy Lau
 */
var Common = {
	/**
	 * 用于全局url
	 */
	urlPrefix : function(){
		return "/demo";
	},

	/**
	 * 通用ajax请求
	 * @param url      ajax请求连接(必填)
	 * @param data     请求传参(必填)
	 * @param callback 回调函数(必填)
	 * @param showMsg  是否提示返回信息(默认false)
	 */
	ajax : function(url, data, callback, showMsg){
		var param ={};
		
		if(url.indexOf('http') != 0){
			param.url = Common.urlPrefix() + url;
		}else{
			param.url = url;
		}
		
		if(data){
			param.data = data;
		}
		param.type = 'POST';
		param.dataType = 'json';
		param.success = function(json){
			ajaxLoadEnd();
			if (json) {
				if (json.code != null && json.code != ajaxReturnCode.success) {
					Common.msgBox('提示:', json.msg);
					return;
				}
			}
			
			if(showMsg){
				Common.msgBox('提示:', json.msg);
			}
			
			if(callback){
				callback(json.data);
			}
		};
		param.error = function(json){
			ajaxLoadEnd();
			Common.msgBox('提示:', '系统错误 ~ 请联系管理员吧☎');
		};
		ajaxLoading();
		$.ajax(param);
	},

	/**
	 * 通过新窗口打开连接
	 * @param url      请求连接(必填)
	 * @param data     请求传参(必填)
	 */
	openInNewWindow : function(url, data){
		if(url.indexOf('http') != 0){
			url = Common.urlPrefix() + url;
		}else{
			url = url;
		}
		
		for(var key in data){
			if(data[key]){
				if(url.indexOf("?") == -1){
					url += ("?" + key + "=" + data[key]);
				}else{
					url += ("&" + key + "=" + data[key]);
				}
			}
		}
		
		window.open(url);
	},
	
	/**
	 * 在tab显示窗口
	 * @param title 窗口标题(必填)
	 * @param url   窗口连接(必填)
	 */
	showTab : function(title, url){
		var isExistTab = $("#tabNav").find("a[href=#" + title + "]");
		
		//自动点击移动端菜单显示按钮，从而隐藏
		$("#menu-toggler").trigger('click');
		
		if(!!isExistTab && isExistTab.length > 0){
			isExistTab.trigger('click');
		}else{
			var tabNav = '<li>';
			tabNav += '<a data-toggle="tab" href="#' + title + '">';
			tabNav += title;
			tabNav += '<i title="' + title + '" class="ace-icon fa fa-times bigger-150 red" onclick="closeTab(this)"></i>';
			tabNav += '</a>';
			tabNav += '</li>';
			$("#tabNav").append(tabNav);
			
			var tabContent = '<div id="' + title + '" class="tab-pane fade">';
			tabContent += '<iframe scrolling="auto" frameborder="0" width="100%" id="iframepage" src="' + url + '"></iframe>';
			tabContent += '</div>';
			$("#tabContent").append(tabContent);
			
			isExistTab = $("#tabNav").find("a[href=#" + title + "]");
			isExistTab.trigger('click');
		}
		
		//iframe 自适应高度 TODO 待完善
		var iframes = $("#tabContent").find("iframe");
		for(var i=0 ; i<iframes.length ; i++){
			iframes[i].height = document.documentElement.clientHeight - 225;
		}
	},

	/**
	 * 根据参数名获取url传参
	 * @param name  参数名
	 * @return      参数值
	 */
	getUrlParam : function(name) {
		//构造一个含有目标参数的正则表达式对象
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	    
	    //匹配目标参数
	    var r = window.location.search.substr(1).match(reg);
	    
	    //返回参数值
	    if (r != null) return decodeURI(r[2]); 
	    
	    return null;
	},
	
	/**
	 * 普通消息框
	 * @param title      消息标题(必填)
	 * @param msg        消息内容(必填)
	 * @param callback   确认按钮绑定的回调函数(可选)
	 */
	msgBox : function(title, msg, callback){
		if(!$('#msgModal').html()){
			$(document.body).append(getMsgBoxHtml());
		}
		$('#msgModalTitle').html(title);
		$('#msgModalBody').html(msg);
		$('#msgModal').modal('show');
		
		if(!!callback){
			$('#msgModalOkBtn').off('click._bind');
			$('#msgModalOkBtn').one('click._bind', function (){
				callback(true);
			});
		}
	},
	
	/**
	 * 实例化Bootstarp Table, api:http://bootstrap-table.wenzhixin.net.cn/documentation/
	 * @param tableElement 	表格元素(必填,如:$('#table'))
	 * @param url	 		查询地址(必填)
	 * @param params		查询参数(可选,默认{})
	 * @param singleSelect  是否单选(可选,默认true)
	 * @param pageSize		页大小(可选,默认12)
	 * @param pageNumber	页码(可选,默认1)
	 */
	initTable : function(table, url, params, singleSelect, pageSize, pageNumber){
		var reload = true;
		
		if(singleSelect == undefined) singleSelect = true;
		if(!pageSize) pageSize = 12;
		if(!pageNumber) pageNumber = 1;
		
		if(url.indexOf('http') != 0){
			url = Common.urlPrefix() + url;
		}else{
			url = url;
		}
		
		table.bootstrapTable({
			url: url,
			method: "post",
			contentType: "application/x-www-form-urlencoded",
			dataType: "json",
			queryParams:  function(paginatData) {
				//这里会有部分默认传参，封装到自定义传参里面
				if(!params){
					params = {};
				}
                params.pageNumber = pageNumber;
                params.pageSize = pageSize;
                params.order = paginatData.order;
                params.sort = paginatData.sort;
                params.search = paginatData.search;
                return params;
            },
			queryParamsType: "limit",//参数格式
			pagination: true,//启动分页
			pageList: [12, 25, 50],
			sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
			clickToSelect: true,
			pageSize: pageSize,
			pageNumber: pageNumber,
			singleSelect: singleSelect,
			formatNoMatches: function () {
				return '提示:没有找到匹配的记录';
			},
			formatLoadingMessage: function () {
				reload = false;
				ajaxLoading();
				return "提示:请稍等，正在加载中...";
			},
			onLoadSuccess: function (result) {
				ajaxLoadEnd();
				if(!result || result.code != 100){
					return "提示:加载数据错误";
					Common.msgBox("提示:加载数据错误", result.msg);
				}
			},
			onLoadError: function (result) {
				ajaxLoadEnd();
				$(table).bootstrapTable('removeAll');
				return "提示:加载数据错误";
			}
		});
			
		//重新设置url以及查询条件,示例化表格以后,表格示例不会再进行实例化，需要调用下面的方法来重置表格的条件
		if(reload){
			$(table).bootstrapTable('refresh',{url:url, query:params});
		}
	}
}

function closeTab(obj){
	var node = $(obj).parents("a").parents("li");
	var prev = node.prev();
	
	node.remove();
	$("#tabContent").find("div[id="+obj.title+"]").remove();
	
	if (!prev.hasClass('active')) {
		prev.find('a').trigger('click');
	}
};

function ajaxLoading(){
	//正在读取
	$(document.body).append('<div class="theme-popover-mask"><div class="progress progress-striped active"><div class="progress-bar" style="width: 100%;">正在加载...</div></div></div>');
	$('.theme-popover-mask').fadeIn(100);
};

function ajaxLoadEnd(){
	//读取完毕
	$('.theme-popover-mask').remove();
};

function getMsgBoxHtml(){
	var html = '<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="msgModalTitle" aria-hidden="true">';
	html += '<div class="modal-dialog">';
	html += '<div class="modal-content">';
	html += '<div class="modal-header">';
	html += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>';
	html += '<h4 class="modal-title" id="msgModalTitle">模态框（Modal）标题</h4>';
	html += '</div>';
	html += '<div class="modal-body" id="msgModalBody">按下 ESC 按钮退出。</div>';
	html += '<div class="modal-footer">';
	html += '<button type="button" id="msgModalOkBtn" class="btn btn-primary" data-dismiss="modal">确认</button>';
	html += '<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	return html;
};

var ajaxReturnCode = {
	"success" : 0,
	"bizError" : 10001
};

$(function(){
	//初始化日历空间
	$(".form_datetime").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        todayBtn: true,
        minView: "month",
        language: 'zh-CN',
    });
	
	//初始化mySelection
	$(".mySelection").each(function(){
		var selection = $(this);
		var url = selection.attr("data-url");
		var valueName = selection.attr("data-value");
		var textName = selection.attr("data-text");
		var checkedValue = selection.attr("data-value-checked");
		Common.ajax(url, {}, function(data){
			for(var i=0 ; i<data.length ; i++){
				var obj = data[i];
				selection.append("<option value='" + obj[valueName] + "'>" +  obj[textName] + "</option>");
			}
		});
	});
});