package com.smartbit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //If this Provider Supports the input Authentication Or Not
        //if any issue in Authentication Throw AuthenticationException
        //if Authentication is fine return an Authentication with isAuthenticated true.
        UserDetails user=userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        if(passwordEncoder.matches((CharSequence) authentication.getCredentials(),user.getPassword())){
            return new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
        }
        else{
            throw new BadCredentialsException("UserName Password Doesn't match");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
       return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
