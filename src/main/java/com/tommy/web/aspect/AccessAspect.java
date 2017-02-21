package com.tommy.web.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AccessAspect {

	private static final Logger logger = LoggerFactory.getLogger(AccessAspect.class);
	
	@Around(value="execution(public * com.tommy.web.controller.*.*(..))")
	public Object actionLog(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		Object[] args = joinPoint.getArgs();
		logger.info(request.getRequestURI() + "接口。客户端请求信息：" + getParamsData(request));
		
		Object obj = joinPoint.proceed(args);
		logger.info(request.getRequestURI() + "接口。服务端响应信息:{}，耗时:{}ms", new Object[] { obj, System.currentTimeMillis() - start });
		return obj;
	}
	
	public String getParamsData(HttpServletRequest request){
		StringBuilder data = new StringBuilder();
		Map<String, String[]> map = request.getParameterMap();
		if(map != null && map.size() != 0){
			for(Map.Entry<String, String[]> entry : map.entrySet()){
				data.append(entry.getKey() + "=" + entry.getValue()[0] + ",");
			}
			data.deleteCharAt(data.length()-1);
		}
		return data.toString();
	}
	
}
