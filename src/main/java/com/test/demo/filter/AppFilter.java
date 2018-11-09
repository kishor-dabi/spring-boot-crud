package com.test.demo.filter;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.demo.model.Token;
import com.test.demo.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
@WebFilter(urlPatterns="/v3/*")
public class AppFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(AppFilter.class);

	@Autowired
	AuthConfig authconfig;
	
	@Autowired
	TokenService tokenservice;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter----------init----");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String auth = ((HttpServletRequest)request).getHeader("authorization") ;
		System.out.println(" : : : : : : ;:filter call"+ auth+ "=========" + ((HttpServletRequest)request).getRequestURI());
		
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		final String SECRET = Base64.getEncoder().encodeToString("secret".getBytes());

		
		Claims claim ;
		String email;
		int id;
		Date date;
		try {
			claim = Jwts.parser().setSigningKey(authconfig.getKey()).parseClaimsJws(auth).getBody();//.getSubject();//.equals("Joe");
			id = (Integer.parseInt(claim.getId()));
			date = claim.getExpiration();
			
			Token userToken = tokenservice.findByUserid((long) id);
			
			logger.info("claim : : : :"+ claim);
			//logger.info(auth+ "========="+ auth.length() );
			if (((HttpServletRequest)request).getHeader("authorization") != null) {
				if (auth.equals(userToken.getToken())) {
					chain.doFilter(request, response);	
				}else {
					((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Unauthorized");
//					.setStatus(HttpServletResponse.SC_BAD_REQUEST).write(convertObjectToJson("unauthorized"));
				}
			}else {
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Unauthorized");	
			}
			
			
		} catch (JwtException e) {
			// TODO: handle exception
			System.out.println(e);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error in server");	
			
		}
		
		
		
//		
		
        
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("filter----------distroy----");

	}

}
