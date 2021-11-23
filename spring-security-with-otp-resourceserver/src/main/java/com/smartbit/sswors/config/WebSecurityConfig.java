package com.smartbit.sswors.config;

import com.smartbit.sswors.filter.InitialAuthFilter;
import com.smartbit.sswors.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserOtpAuthenticationProvider userOtpAuthenticationProvider;

    @Autowired
    UserNamePasswordAuthenticationProvider userNamePasswordAuthenticationProvider;

    @Autowired
    InitialAuthFilter initialAuthFilter;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(initialAuthFilter, BasicAuthenticationFilter.class).addFilterAfter(jwtFilter,BasicAuthenticationFilter.class).authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userOtpAuthenticationProvider).authenticationProvider(userNamePasswordAuthenticationProvider);
    }


    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
