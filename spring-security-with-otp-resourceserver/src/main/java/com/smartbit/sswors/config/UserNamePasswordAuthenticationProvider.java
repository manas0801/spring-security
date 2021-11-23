package com.smartbit.sswors.config;

import com.smartbit.sswors.model.User;
import com.smartbit.sswors.proxy.AuthServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AuthServerProxy authServerProxy;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user= new User();
        user.setUsername(authentication.getName());
        user.setPassword((String) authentication.getCredentials());
        authServerProxy.authUser(user);

        return new UserPasswordAuthentication(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UserPasswordAuthentication.class);
    }
}
