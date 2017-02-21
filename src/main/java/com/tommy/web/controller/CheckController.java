package com.tommy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tommy.web.model.common.vo.Result;
import com.tommy.web.utils.JsonUtils;


@Controller
@RequestMapping("/check")
public class CheckController {

	private static final Logger logger = LoggerFactory.getLogger(CheckController.class);
	
	@ResponseBody
	@RequestMapping("/info/all")
	public String check() throws Exception{
		logger.info("检查项目中...");
		
		Map<String, String> result = new HashMap<String, String>();
//		try {
//			logger.info("测试邮件发送中...");
//			result.put("测试邮件发送中", "收到说明发送功能正常");
//			throw new RuntimeException("【测试邮件】接收到该邮件说明发送正常!");
//		} catch(Exception e){
//			logger.error(e.getMessage(), e);
//		}

		return JsonUtils.toString(new Result(result));
	}
}
