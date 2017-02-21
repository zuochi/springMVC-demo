package com.tommy.web.auth;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tommy.web.auth.biz.AuthService;
import com.tommy.web.constant.AuthConstant;
import com.tommy.web.model.login.bo.UserLoginBo;

@Controller
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/doLogin")
	public ModelAndView doLogin(HttpServletRequest req, HttpServletResponse resp, UserLoginBo bo) throws Exception {
		boolean result = authService.doLogin(req, resp, bo);
		if(result){
			return new ModelAndView("redirect:index");
		}else{
			return new ModelAndView("redirect:login?msg=loginerror");
		}
	}
	
	@RequestMapping("/doLogout")
	public ModelAndView getLogout(@CookieValue(value = AuthConstant.USESSIONID, defaultValue = "{}") String userMD5,HttpServletRequest request) throws UnsupportedEncodingException {
		authService.doLogout(userMD5, request);
		return new ModelAndView("redirect:index");
	}
}
