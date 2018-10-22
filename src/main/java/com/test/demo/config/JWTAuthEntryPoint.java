package com.test.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpReq, HttpServletResponse httpRes, AuthenticationException e)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		httpRes.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
	}

}
