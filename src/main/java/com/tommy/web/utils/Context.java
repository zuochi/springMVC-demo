package com.tommy.web.utils;

import org.apache.commons.csv.CSVPrinter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Amu 上下文对象
 */
final public class Context {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext servletContext;
	private ApplicationContext applicationContext;
	private CSVPrinter csvPrinter;


	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public CSVPrinter getCsvPrinter() {
		return csvPrinter;
	}

	public void setCsvPrinter(CSVPrinter csvPrinter) {
		this.csvPrinter = csvPrinter;
	}

	/**
	 * 获取bean
	 * 
	 * @param beanName
	 * @return
	 */
	public Object getBean(String beanName) {
		if (beanName == null) {
			throw new IllegalArgumentException("BeanName 不存在");
		}
		return getApplicationContext().getBean(beanName);
	}

	public ApplicationContext getApplicationContext() {
		if (applicationContext == null && servletContext != null) {
			applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
		} else if (null == servletContext) {
			applicationContext = ContextLoader
					.getCurrentWebApplicationContext();
		}
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
