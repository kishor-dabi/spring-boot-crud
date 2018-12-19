package com.test.demo.filter;

import java.security.Key;
import java.util.Base64;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class AuthConfig {
	String key = Base64.getEncoder().encodeToString("secret to craete client token when login request send".getBytes());// Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String getKey() {
		return this.key;
	}
}
