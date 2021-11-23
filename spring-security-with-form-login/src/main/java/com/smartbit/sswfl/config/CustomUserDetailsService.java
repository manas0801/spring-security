package com.smartbit.sswfl.config;

import com.smartbit.sswfl.model.CustomerUserDetails;
import com.smartbit.sswfl.model.User;
import com.smartbit.sswfl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User Not found"));
       return new CustomerUserDetails(user);
    }
}
