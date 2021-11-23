package com.smartbit.sswors.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserPasswordAuthentication extends UsernamePasswordAuthenticationToken {
    public UserPasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserPasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
