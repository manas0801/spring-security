//package com.smartbit.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("manas").password("12345").authorities("ROLE_USER")
//                .and().withUser("admin").password("admin").authorities("ROLE_ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
//    }
//
//
//
//}
