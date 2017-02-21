<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
		<script type="text/javascript">
			//加载菜单 TODO 以后从数据源加载菜单，改这里的url
			$(function(){
				Common.ajax("/assets/menu.json",{},function(menus){
					appendMenuUl(menus);
				});
			});
			
			function appendMenuUl(menus, parentId){
				for(var i=0 ; i<menus.length ; i++){
					var menu = menus[i];
					
					appendMenuLi(menu, parentId)
					
					if(!!menu.children && menu.children.length>0){
						$("#menu" + menu.id).append('<ul id="menuTree' + menu.id + '" class="submenu"></ul>');
						appendMenuUl(menu.children, menu.id);//递归
					}
				}
			};
			
			function appendMenuLi(menu, parentId){
				if(!parentId){
					$("#menuTree").append("<li id='menu" + menu.id + "' class='active open'></li>");
				}else{
					$("#menuTree" + parentId).append("<li id='menu" + menu.id + "' class='active open'></li>");
				}
				
				var menuLi = $("#menu" + menu.id);
				
				var menuHtml;
				
				if(!!menu.attributes && !!menu.attributes.url){
					if(menu.attributes.url.indexOf('http') != 0) {
						menu.attributes.url = Common.urlPrefix() + menu.attributes.url;
					}
					
					menuHtml = "<a href='javascript:;' onclick='Common.showTab(\"" + menu.text + "\",\"" + menu.attributes.url + "\")' class='dropdown-toggle'>";
				}else{
					menuHtml = '<a href="javascript:;" class="dropdown-toggle">';	
				}
				
				if(!!menu.children && menu.children.length>0){
					menuHtml += '<i class="menu-icon fa fa-caret-right"></i>';
				}
				
				menuHtml += menu.text;
				menuHtml += '</a>';
				menuHtml += '<b class="arrow"></b>';
				
				menuLi.append(menuHtml);
			};
		</script>
		<div id="sidebar" class="sidebar responsive ace-save-state">
			<script type="text/javascript">
				try{ace.settings.loadState('sidebar')}catch(e){}
			</script>
			<ul id="menuTree" class="nav nav-list"></ul>
			<!-- <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div> -->
		</div>
	</body>
</html>
