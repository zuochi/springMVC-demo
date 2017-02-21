package com.tommy.web.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 上下文过滤器
 */
public class ContextFilter implements Filter {
	private ServletContext servletContext;
    @Override
    public void destroy() {
        servletContext = null;
    }
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	 servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        final Context env = ContextUtils.getContext();
        try {
        	HttpServletRequest req = (HttpServletRequest) request;
        	String uri = req.getRequestURL().toString();
        	if (uri.endsWith("jsp") || uri.endsWith("js") || uri.endsWith("css") || uri.endsWith("html")) {
        	    chain.doFilter(request, response);
                return;
            }
            env.setServletContext(servletContext);
        	HttpServletResponse resp = (HttpServletResponse) response;
    	    env.setRequest(req);
            env.setResponse(resp);
            chain.doFilter(request, response);
		} finally {
            ContextUtils.removeContext();
        }
    }

}
