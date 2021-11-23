package com.smartbit.sswors.filter;

import com.smartbit.sswors.config.UserNamePasswordAuthenticationProvider;
import com.smartbit.sswors.config.UserOtpAuthentication;
import com.smartbit.sswors.config.UserPasswordAuthentication;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@Component
public class InitialAuthFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${jwt.signing.key}")
    String jwtSignKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username=request.getHeader("username");
        String password=request.getHeader("password");
        String code= request.getHeader("code");

        if(code==null){
            UserPasswordAuthentication  userPasswordAuthentication= new UserPasswordAuthentication(username,password);
            authenticationManager.authenticate(userPasswordAuthentication);


        }
        else{
            UserOtpAuthentication userOtpAuthentication= new UserOtpAuthentication(username,code);
            authenticationManager.authenticate(userOtpAuthentication);

            SecretKey key= Keys.hmacShaKeyFor(jwtSignKey.getBytes(StandardCharsets.UTF_8));
            Map<String,Object>  authMap= new HashMap<>();
            authMap.put("username",username);
            String jwtToken= Jwts.builder().addClaims(authMap).signWith(key).compact();
            response.addHeader("authentication",jwtToken);

            ///Add one JWT Token in Authentication Response Header
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equalsIgnoreCase("/login");
    }
}
