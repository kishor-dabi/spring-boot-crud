package com.test.demo.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.test.demo.model.JwtAuthenticationToken;

import io.jsonwebtoken.Jwts;

public class JWTfilter extends AbstractAuthenticationProcessingFilter {

	protected JWTfilter() {
		super("v3/**");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		String header = req.getHeader("Authorisation");

        System.out.println(header);

        if (header == null || !header.startsWith("Token ")) {
            throw new RuntimeException("JWT Token is missing");
        }
        

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
	}

	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
	


}
