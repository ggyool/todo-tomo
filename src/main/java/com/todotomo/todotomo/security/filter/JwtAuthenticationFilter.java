package com.todotomo.todotomo.security.filter;

import com.todotomo.todotomo.security.jwt.JwtFactory;
import com.todotomo.todotomo.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtFactory jwtFactory;
    private final CustomUserDetailService customUserDetailService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtFactory jwtFactory, CustomUserDetailService customUserDetailService) {
        super(authenticationManager);
        this.jwtFactory = jwtFactory;;
        this.customUserDetailService = customUserDetailService;
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
        Long id = claims.get("id", Long.class);
        UserDetails userDetails = customUserDetailService.loadUserById(id);
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, "", userDetails.getAuthorities());
        return authentication;
    }

}
