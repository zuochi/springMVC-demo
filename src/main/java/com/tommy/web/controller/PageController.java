package com.tommy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 该Contorller只负责页面转跳，不做数据返回，数据返回 Controller 按模块分
 * (数据返回按DemoController 的 json 示例)
 * 
 * @author Tommy Lau
 */
@Controller
public class PageController {
	
	@RequestMapping("/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("index");
	}

	@RequestMapping("/appData/userData")
	public ModelAndView appDataMobileUserData() throws Exception {
		return new ModelAndView("appData/userData");
	}
}
