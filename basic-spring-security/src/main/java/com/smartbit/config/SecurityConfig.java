package com.smartbit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService(){

        UserDetailsService userDetailsService= new InMemoryUserDetailsManager();
        UserDetails userDetails= User.builder().username("Suvechha").password("12345").authorities("READ").build();

        ((InMemoryUserDetailsManager) userDetailsService).createUser(userDetails);

        return userDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
