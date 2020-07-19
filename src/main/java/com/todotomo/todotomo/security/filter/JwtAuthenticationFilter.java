package com.todotomo.todotomo.security.filter;

import com.todotomo.todotomo.domain.user.Role;
import com.todotomo.todotomo.security.jwt.JwtFactory;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtFactory jwtFactory;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtFactory jwtFactory) {
        super(authenticationManager);
        this.jwtFactory = jwtFactory;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);
        if(authentication != null){
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token==null) return null;
            Claims claims = jwtFactory.getClaims(token.substring("Bearer ".length()));
        System.out.println(claims);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, "", authorities);
        return authentication;
    }

}
