<%@page import="com.tommy.web.version.Version"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>妈妈网搜索管理系统</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	
	<!-- css -->
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/font-awesome/4.5.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/fonts.googleapis.com.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/datetimepicker/css/bootstrap-datetimepicker.min.css" />
	
	<!-- script -->
	<script src="/<%=Version.p%>/assets/ace/js/jquery-2.1.4.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<link rel="stylesheet" href="/<%=Version.p%>/assets/css/common.css?v=<%=Version.v%>" />
	<script src="/<%=Version.p%>/assets/script/common.js?v=<%=Version.v%>"></script>
</head>
<body class="no-skin">
	<!-- #include file = "myfile.inc" --> 
	<!-- /.header -->
	<jsp:include page="home/header.jsp"></jsp:include>
	
	<!-- /.main-container -->
	<div id="main-container" class="main-container ace-save-state">
		<script type="text/javascript">
			try{ace.settings.loadState('main-container')}catch(e){}
		</script>
		
		<jsp:include page="home/menu.jsp"></jsp:include>
		<jsp:include page="home/body.jsp"></jsp:include>
		<jsp:include page="home/footer.jsp"></jsp:include>
	</div>
	
	<!-- script -->
	<script src="/<%=Version.p%>/assets/ace/js/bootstrap.min.js"></script>
	<script src="/<%=Version.p%>/assets/ace/js/ace.min.js"></script>
</body>
</html>