package com.smartbit.sswoa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
@Configuration
public class JwtConfig {
    @Value("${jks.location}")
    String jksLocation;
    @Value("${jks.pass.key}")
    String passKey;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter= new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory= new KeyStoreKeyFactory(new ClassPathResource(jksLocation),passKey.toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("ssia"));
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){
        JwtTokenStore jwtTokenStore= new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }
}
