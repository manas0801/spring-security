package com.smartbit.sswors.filter;

import com.smartbit.sswors.config.UserPasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.signing.key}")
    String jwtSignKey;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader= request.getHeader("authentication");
        SecretKey key= Keys.hmacShaKeyFor(jwtSignKey.getBytes(StandardCharsets.UTF_8));
        String username=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authHeader).getBody().get("username",String.class);
        UserPasswordAuthentication userPasswordAuthentication= new UserPasswordAuthentication(username,null, Arrays.asList(new SimpleGrantedAuthority("User")));
        SecurityContextHolder.getContext().setAuthentication(userPasswordAuthentication);
        filterChain.doFilter(request,response);

    }
}
