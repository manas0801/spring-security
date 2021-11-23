package com.smartbit.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {

    private List<? extends UserDetails> users;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
          return users.stream().filter(user->user.getUsername().equals(s)).findFirst().orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }

    public InMemoryUserDetailsService(List<? extends UserDetails> users) {
        this.users = users;
    }
}
