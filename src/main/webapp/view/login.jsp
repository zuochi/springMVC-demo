<%@page import="com.tommy.web.version.Version"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="User login page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/font-awesome/4.5.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/fonts.googleapis.com.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/ace/css/ace.min.css" />
	<link rel="stylesheet" href="/<%=Version.p%>/assets/datetimepicker/css/bootstrap-datetimepicker.min.css" />
	
	<script src="/<%=Version.p%>/assets/ace/js/jquery-2.1.4.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/<%=Version.p%>/assets/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script src="/<%=Version.p%>/assets/script/common.js"></script>
</head>
<body class="login-layout">
	<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container"    style="padding-top: 100px">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red"><%=Version.n %></span>
									<span class="white" id="id-text2">管理系统</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; <%=Version.c %></h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												<span id="loginMsg">请输入您的账户信息</span>
											</h4>

											<div class="space-6"></div>

											<form action="doLogin" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="username" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<div class="radio">
															<label>
																<input name="type" value="1" type="radio" class="ace" checked="checked"/>
																<span class="lbl">LDAP</span>
															</label>
															<!-- <label>
																<input name="type" name="type" value="2" type="radio" class="ace"/>
																<span class="lbl">本系统账户</span>
															</label> -->
														</div>
														<!-- <label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 记住我</span>
														</label> -->

														<button type="submit" onclick="doLogin" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-120">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
							</div>
							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		<script type="text/javascript">
			jQuery(function($) {
				$('#btn-login-dark').on('click', function(e) {
					$('body').attr('class', 'login-layout');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'blue');

					e.preventDefault();
				});
				$('#btn-login-light').on('click', function(e) {
					$('body').attr('class', 'login-layout light-login');
					$('#id-text2').attr('class', 'grey');
					$('#id-company-text').attr('class', 'blue');

					e.preventDefault();
				});
				$('#btn-login-blur').on('click', function(e) {
					$('body').attr('class', 'login-layout blur-login');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'light-blue');

					e.preventDefault();
				});
				
				if(Common.getUrlParam('msg') == 'loginerror'){
					$("#loginMsg").html('登录失败,账号或密码有误').attr("style","color:red");
				}
			});
		</script>
	</body>
</html>