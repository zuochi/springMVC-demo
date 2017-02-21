package com.tommy.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.tommy.web.constant.BizConstant;
import com.tommy.web.exception.BizException;
import com.tommy.web.model.common.vo.Result;
import com.tommy.web.utils.JsonUtils;

/**
 * 全局异常拦截器
 */
@Component
public class GlobalExceptionInterceptor implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		// 初始化日志信息，决定用哪一份配置来进行日志管理
		Result result = new Result();
		if (ex instanceof BizException) {
			// 自定义异常，只打出message信息
			logger.info("业务异常：" + ((BizException) ex).getMsg());
			result.setCode(((BizException) ex).getCode());
			result.setMsg(((BizException) ex).getMsg());
		} else{
			// 系统异常，打出堆栈信息
			StringBuilder err = new StringBuilder();
			err.append(ex.getMessage()).append("<br/>");
			err.append(request.getRequestURI());
			err.append(paramsData(request));
			result.setCode(BizConstant.Code.FAIL);
			result.setMsg("系统繁忙!");
			logger.error(ex.getMessage(), ex);
		}
		try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(JsonUtils.toString(result));
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ModelAndView();
	}
	
	/**
	 * 通过HttpServletRequest请求，获取所有入参
	 */
	public String paramsData(HttpServletRequest request){
		
		String result = "";
		StringBuilder data = new StringBuilder();
		Map<String, String[]> map = request.getParameterMap();
		if(map != null && map.size() != 0){
			for(Map.Entry<String, String[]> entry : map.entrySet()){
				data.append(entry.getKey() + "=" + entry.getValue()[0] + "&");
			}
			data.deleteCharAt(data.length()-1);
		}
		if(data.length() != 0) {
			result = "?" + data.toString();
		}
		return result;
	}
	
}
