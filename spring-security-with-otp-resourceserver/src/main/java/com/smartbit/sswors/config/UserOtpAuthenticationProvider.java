package com.smartbit.sswors.config;

import com.smartbit.sswors.model.User;
import com.smartbit.sswors.proxy.AuthServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserOtpAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    AuthServerProxy authServerProxy;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = new User();
    user.setUsername(authentication.getName());
    user.setCode((String)authentication.getCredentials());
    if(authServerProxy.checkOTP(user)){
        return new UserOtpAuthentication(user.getUsername(),user.getPassword());
    }
    else{
        throw new BadCredentialsException("Bad Credential");
    }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UserOtpAuthentication.class);
    }
}
