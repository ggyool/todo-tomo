package com.todotomo.todotomo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;

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

    public Claims getClaims(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return claims;
    }


}
