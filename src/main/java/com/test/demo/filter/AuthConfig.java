package com.test.demo.filter;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class AuthConfig {
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public Key getKey() {
		return this.key;
	}
}
