package com.smartbit.config;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
class PreAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String requestid= request.getHeader("request-id");
       if(requestid==null || requestid.isEmpty()){
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           return;
       }
       filterChain.doFilter(request,response);
    }
}
