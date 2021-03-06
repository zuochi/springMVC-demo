package com.tommy.web.exception;

import com.tommy.web.constant.BizConstant;

/**
 * 自定义业务异常类
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BizException(String msg) {
		this.msg = msg;
		this.code = BizConstant.Code.FAIL;
	}
	
	public BizException(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
