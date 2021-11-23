package com.smartbit.config;

import com.smartbit.model.SecurityUser;
import com.smartbit.model.User;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAsync
public class SecurityConfig {



    @Bean
    public UserDetailsService userDetailsService(){

        User user= new User("manas","3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb3abb9e3a6df2ac2c20fe23436311d678564dc8d305930575f60e2d3d048184d79","read");
        SecurityUser securityUser= new SecurityUser(user);
        List<SecurityUser> userList= Arrays.asList(securityUser);
        return new InMemoryUserDetailsService(userList);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){

        return new CustomPasswordEncoder();
    }


    @Bean
    public InitializingBean initializingBean(){

      return  ()-> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }



}
