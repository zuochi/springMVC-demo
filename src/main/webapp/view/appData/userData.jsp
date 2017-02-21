<%@page import="com.tommy.web.version.Version"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户数据</title>
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
	
	<link rel="stylesheet" href="/<%=Version.p%>/assets/highcharts/css/highcharts.css" class="ace-main-stylesheet" id="main-ace-style" />
	
	<!-- jquery -->
	<script src="/<%=Version.p%>/assets/ace/js/jquery-2.1.4.min.js"></script>
	
	<!-- 页面基本脚本 -->
	<script src="/<%=Version.p%>/assets/ace/js/bootstrap.min.js"></script>
	<script src="/<%=Version.p%>/assets/ace/js/ace.min.js"></script>
	<script src="/<%=Version.p%>/assets/ace/js/bootstrap-table.min.js"></script>
	<script src="/<%=Version.p%>/assets/ace/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	
	<script src="/<%=Version.p%>/assets/highcharts/js/highcharts.js"></script>
	
	<!-- 全局js -->
	<script src="/<%=Version.p%>/assets/script/common.js?v=<%=Version.v%>"></script>
	<script src="/<%=Version.p%>/assets/script/dataUtils.js?v=<%=Version.v%>"></script>
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
							<div class="col-xs-12 col-sm-2">
								<label class="control-label">活跃度类型:</label>
								<select id="type" class="form-control" onchange="AppData.showTimePicker(this.value)">
									<option value="1" selected="selected">日活跃度</option>
									<option value="2">月活跃度</option>
								</select>
							</div>
						
							<div class="col-xs-12 col-sm-2 timepciker">
								<label class="control-label">开始时间:</label>
								<div class="input-group date form_datetime">
									<input id="startDate" class="form-control" size="10" type="text" value="" readonly> 
									<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<%-- <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span> --%>
								</div>
							</div>
							<div class="col-xs-12 col-sm-2 timepciker">
								<label class="control-label">结束时间:</label>
								<div class="input-group date form_datetime">
									<input id="endDate" class="form-control" size="10" type="text" value="" readonly> 
									<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								</div>
							</div>
							
							<div class="col-xs-12 col-sm-2">
								<button type="button" class="btn btn-primary no-border btn-sm" onclick="AppData.loadUserData()">
									<i class="ace-icon fa fa-search icon-on-right bigger-120"></i>
									查询
								</button>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->
					<div class="row">
						<div class="col-xs-12">
							<div id="container"></div>
						</div>
					</div>
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->
	</div>
	<script src="/<%=Version.p%>/assets/script/appData.js?v=<%=Version.v%>"></script>
</body>
</html>