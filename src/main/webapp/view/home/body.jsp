<%@page import="com.tommy.web.version.Version"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="tabbable">
							<ul id="tabNav" class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#home">Home<i class="green ace-icon fa fa-home bigger-120"></i></a></li>
							</ul>
							<div id="tabContent" class="tab-content">
								<div id="home" class="tab-pane fade in active">
									<p>欢迎来到<%=Version.n %>管理系统。
								</div>
							</div>
						</div>
						<!-- PAGE CONTENT ENDS -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</body>
</html>
