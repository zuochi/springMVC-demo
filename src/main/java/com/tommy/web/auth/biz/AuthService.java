package com.tommy.web.auth.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tommy.web.model.login.bo.UserLoginBo;

public interface AuthService {

	/**
	 * 登录
	 */
	public boolean doLogin(HttpServletRequest req, HttpServletResponse resp, UserLoginBo bo);

	/**
	 * 登出
	 */
	public void doLogout(String userMD5, HttpServletRequest request);
}
