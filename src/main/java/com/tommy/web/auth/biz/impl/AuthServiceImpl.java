package com.tommy.web.auth.biz.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tommy.web.auth.biz.AuthService;
import com.tommy.web.model.login.bo.UserLoginBo;

@Service
public class AuthServiceImpl implements AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Override
	public boolean doLogin(HttpServletRequest request, HttpServletResponse response, UserLoginBo bo) {
		return true;
	}

	@Override
	public void doLogout(String userMD5, HttpServletRequest request) {
	}
}
