package com.smartbit.sswors.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserOtpAuthentication extends UsernamePasswordAuthenticationToken {
    public UserOtpAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserOtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
