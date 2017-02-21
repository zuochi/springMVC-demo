package com.tommy.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tommy.web.constant.AuthConstant;
import com.tommy.web.exception.BizException;
import com.tommy.web.model.login.vo.UserLoginVo;
import com.tommy.web.utils.JsonUtils;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		return true;
		
//		boolean result = false;
//		String userAccountMD5 = null;
//		
//		// 判断cookie是否存在
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (int i = 0; i < cookies.length; i++) {
//				if(AuthConstant.USESSIONID.equals(cookies[i].getName())){
//					userAccountMD5 = cookies[i].getValue();
//					break;
//				}
//			}
//
//			UserLoginVo user = null;
//			if (userAccountMD5 != null) {
//				String userJson = null;//redisDao.get(AuthConstant.USESSIONID + userAccountMD5);// 去redis取值
//				if (userJson != null) {
//					user = JsonUtils.toBean(userJson, UserLoginVo.class);
//				}
//			}
//			
//			if (user != null) {
//				result = true;
//			}
//		}
//		
//		if (!result) { // 找不到登录信息时
//			String requestWith = request.getHeader("x-requested-with");
//			if ("XMLHttpRequest".equals(requestWith)) { //ajax请求
//				throw new BizException("登录已失效，请重新登录");
//			} else {//其他请求
//				response.sendRedirect("login");
//			} 
//			return false;
//		} else {
//			return true;
//		}
	}
}
