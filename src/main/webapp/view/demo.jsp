<%@page import="com.tommy.web.version.Version"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>demo</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	
	<!-- 页面基本样式 -->
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/font-awesome/4.5.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/bootstrap-table.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/datetimepicker/css/bootstrap-datetimepicker.min.css" />
	
	<!-- text fonts -->
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/fonts.googleapis.com.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
	
	<!-- jquery -->
	<script src="/<%=Version.p%>/assets/ace/js/jquery-2.1.4.min.js"></script>
	
	<!-- 页面基本脚本 -->
	<script src="/<%=Version.p%>/assets/ace/js/bootstrap.min.js"></script>
	<script src="/<%=Version.p%>/assets/ace/js/ace.min.js"></script>
	<script src="/<%=Version.p%>/assets/ace/js/bootstrap-table.min.js"></script>
	<script src="/<%=Version.p%>/assets/ace/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	
	<!-- 全局js -->
	<script src="/<%=Version.p%>/assets/script/common.js?v=<%=Version.v%>"></script>
	
	<!-- 全局样式 -->
	<link rel="stylesheet" href="/<%=Version.p%>/assets/css/common.css?v=<%=Version.v%>" />
</head>
<body class="no-skin">
	<!-- /.main-container -->
	<div id="main-container" class="main-container ace-save-state">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
								<!-- 消息框 -->
								<button class="btn btn-primary btn-sm" onclick="Common.msgBox('heheda','拜拜拜')">
									开始演示普通消息框
								</button>
								<button class="btn btn-primary btn-sm" onclick="testA()">
									消息框绑定确认回调函数测试
								</button>
								<script type="text/javascript">
									function testA(){
										var params = {};
										params.name = "name";
										params.pswd = "pswd";
										Common.msgBox('testA', '确认按钮函数回调', function(result){
											if(result){
												console.log("do something");
											}
										});
									}
									function testB(params){
										console.log(params);
									}
								</script>
								
								<button class="btn btn-primary btn-sm" onclick="refresh()">
									刷新页面
								</button>
								<script type="text/javascript">
									function refresh(){
										location.reload(true);
									}
								</script>
								
								<button class="btn btn-primary btn-sm" id="search" onclick="query()">
									搜索
								</button>
								
								<div class="form-group">
									<label class="col-sm-1 control-label no-padding-right" for="form-field-1">表格传参</label>
									<div class="col-sm-4">
										<input type="text" id="name" name="name" value="ssd" placeholder="name" class="col-xs-10 col-sm-5" />
									</div>
								</div>
								
								<!-- 表格展示 api:http://bootstrap-table.wenzhixin.net.cn/documentation/-->
								<table id="reportTable" data-toggle="table"
														<%-- data-height="500" --%>
														data-search="false"      <%-- 显示搜索框 --%>
												        data-show-refresh="true" <%-- 显示刷新按钮 --%>
												        data-show-toggle="true"  <%-- 显示切换按钮 --%>
												        data-show-columns="true"><%-- 显示列隐藏显示器 --%>
								    <thead>
								        <tr>
								            <th data-field="state" data-checkbox="true"></th>
								            <th data-field="id" data-width="10%">Item ID</th>
								            <th data-field="name" data-sortable="true">Item Name</th>
								            <th data-field="price" data-formatter="fieldFormatter">Item Price</th>
								            <th data-field="price" data-visible="false">Hidden</th>
								        </tr>
								    </thead>
								</table>
								<script type="text/javascript">
								//表格四部曲：
									//1.这样调用因为表格需要页面加载的时候初始化
									Common.initTable($('#reportTable'), '/demo/page');
								
									$(function(){
										//初始化表格目前不能写在jquery的初始化加载办法里面，会出问题，这是bug
									});
										
									//2.定义查询方法,并调用第3部获取自定义参数的办法来获得传参
									function query(){
										var params = getParams();
										
										if(!params){
											Common.msgBox('提示', '表格传参不能为空');
											return;
										}
										
										//再次调用初始化的方法，传入自定义参数查询
										Common.initTable($('#reportTable'), '/demo/page', params);
									}
								
									//3.定义获取参数方法
									function getParams(){
										var params = {};
										
										params.name = $("#name").val();
										if(!params.name){
											return;
										}
										
										return params;
									}
								
									//4.自定义表格,这是一个示例:格式化列方法,详情参考api
									function fieldFormatter(value, row, index){
										return "<a href='#'>" + value + "</a>";
									}
								</script>
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->
	</div>
</body>
</html>