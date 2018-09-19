package com.test.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter(urlPatterns="/*")
public class AppFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(AppFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter----------init----");

	}

	@Override 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println(" : : : : : : ;:filter call");
		logger.info("filter:"+ ((HttpServletRequest)request).getRequestURL());
        chain.doFilter(request, response);
	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("filter----------distroy----");

	}

}
