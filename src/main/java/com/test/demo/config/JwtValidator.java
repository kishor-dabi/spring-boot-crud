package com.test.demo.config;

import org.springframework.stereotype.Component;

import com.test.demo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Component
public class JwtValidator {

    private String secret = "youtube";

    public User validate(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();

            user.setName(body.getSubject());
            user.setPassword((String)body.get("password"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return user;
    }
}
