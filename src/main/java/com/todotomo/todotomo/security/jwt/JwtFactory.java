package com.todotomo.todotomo.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;

@NoArgsConstructor
public class JwtFactory {

    private Key key;

    public JwtFactory(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());;
    }

    public String generateToken(Long id, String name){
        String token = Jwts.builder()
                .claim("id", id)
                .claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

}
