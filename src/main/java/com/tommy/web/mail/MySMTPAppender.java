package com.tommy.web.mail;

import com.tommy.web.version.Version;

import ch.qos.logback.classic.net.SMTPAppender;

public class MySMTPAppender extends SMTPAppender {
	
	private String environment = System.getenv("ENVIRONMENT_MAIN");
	
	@Override
	public void setSubject(String subject) {
		if("product".equals(environment)){
			super.setSubject("【ERROR】" + Version.p + "：错误报警-线上");
		}else{
			super.setSubject("【ERROR】" + Version.p + "：错误报警-测试");
		}
	}

	@Override
	public void start() {
		if("product".equals(environment)){
			super.start();
		} else {
			super.stop();
		}
	}

}
