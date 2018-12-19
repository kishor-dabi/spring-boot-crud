package com.test.demo.controller;

import java.security.Key;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.exception.CustomeException;
import com.test.demo.filter.AppFilter;
import com.test.demo.filter.AuthConfig;
import com.test.demo.model.Token;
import com.test.demo.model.User;
import com.test.demo.service.TokenService;
import com.test.demo.service.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
public class TokenController {
	private static final Logger logger = LoggerFactory.getLogger(AppFilter.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	AuthConfig authconfig;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@PostMapping(path = "/token")
	public String getToken(@RequestBody User user) throws ServletException {

		logger.info("+++++++++++++++++token Get");
		
		String jwtToken = "";

	    if (user.getEmail()== null || user.getPassword() == null) {
	        throw new ServletException("Please fill in username and password");
	    }

//	    PasswordEncoder pswEncode = new BCryptPasswordEncoder();
//	    
//	    String passwd = pswEncode.encode("123456");
//
//	    System.out.println(passwd +"-=-=-=-=-=- "+ pswEncode.matches("123456", passwd));
	    String email = user.getEmail();
//	    String password = user.getPassword();

	    User user1 = userRepository.findByEmail(email);   //findByName(email);
	    
	    if (user1 == null) {
			return "User not found";
		}
	    
	    Boolean isUser = encoder.matches(user.getPassword(), user1.getPassword());
	    
	    if (!isUser) {
		    logger.info("User not found");
			return "please check your username & password";
		}
	    
	    Token existToken = tokenService.findByUserid(user1.getId());
	    
	    if (existToken != null) {
			return existToken.getToken();
		}
	    

	    logger.info(user1.toString());
        
		final String SECRET = Base64.getEncoder().encodeToString("secret".getBytes());

		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		System.out.println(SECRET + "-------------------------------");
		

	    ObjectMapper mapper = new ObjectMapper();
        try
        {
            String json = mapper.writeValueAsString(user1);
            System.out.println(json);
            
            User usrString = mapper.readValue(json, User.class);
            
            System.out.println(usrString.toString() + ".....................................................");
            
        }catch (Exception e) {
			// TODO: handle exception
		}

		
		jwtToken = Jwts.builder().setSubject(user1.getEmail()).setId(user1.getId().toString())
					.setExpiration(new Date(new Date().getTime()+7*24*60*60*1000)).signWith(SignatureAlgorithm.HS256 ,authconfig.getKey()).compact();
//		String claim = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken).getBody().getSubject();//.equals("Joe");

		logger.info(jwtToken + "           " );
		Token token = new Token(jwtToken, user1.getId());
		token.setUser_id(user1.getId());
		
		tokenService.save(token);
		
		return jwtToken;

		
//		decode token --------->  assert Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");
		
//		jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
//	            .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
	}
	
	@PostMapping("/create_user")
	public User createUser(@RequestBody User user,HttpServletRequest request, HttpServletResponse response) throws CustomeException {
		System.out.println(user.toString() +"- --  -- -- - -- --- - create - user ");
		if (user.getPassword()==null) {
			throw new CustomeException(500,"Password not filled");
//			return response.sendError(500, "Password not filled.");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		
		User u1 = userRepository.save(user);

		return u1;

	}
}
