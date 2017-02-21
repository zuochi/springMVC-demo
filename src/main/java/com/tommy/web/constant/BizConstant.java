package com.tommy.web.constant;

/**
 * final常量
 * @author Tommy Lau
 *
 */
public interface BizConstant {

	/** 业务异常返回CODE */
	public interface Code {
		//成功
		public static final int SUCCESS = 0;
		//失败
		public static final int FAIL = 10001;
	}
	
	/**常用编码*/
	public interface Encode {
		public static final String UTF8 = "UTF-8";
		public static final String GBK = "GBK";
	}
	
	/** 登录类型*/
	public interface LoginType {
		//LDAP登录
		public static final int LDAP = 1;
		//普通登录
		public static final int NORMAL = 2;
	}
	
	/** 登录类型*/
	public interface AppKey {
		//妈妈网
		public static final String MMQ = "mmq";
		//孕育管家
		public static final String YYGJ = "yygj";
	}
}
