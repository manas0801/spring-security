package com.smartbit.sswbr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   @Value("${token.uri}")
   String tokenIntroSpecUri;
    @Value("${client.id}")
   String clientId;
    @Value("${client.secret}")
   String clientSecret;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().anyRequest().authenticated().and()
               .oauth2ResourceServer(oauth->oauth.opaqueToken(c->{c.introspectionUri(tokenIntroSpecUri);
               c.introspectionClientCredentials(clientId,clientSecret);}));
    }
}
