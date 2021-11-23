package com.smartbit.sswors.proxy;

import com.smartbit.sswors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthServerProxy {

    @Autowired
    RestTemplate restTemplate;

    @Value("${auth.url}")
    String authUrl;

    public void authUser(User user){
        HttpEntity<User> httpEntity= new HttpEntity<>(user);
        restTemplate.postForEntity(authUrl+"/user/auth",httpEntity,Void.class);
    }


    public Boolean checkOTP(User user){
        HttpEntity<User> httpEntity= new HttpEntity<>(user);
       return (restTemplate.postForEntity(authUrl+"/otp/check",httpEntity,Boolean.class)).getStatusCode().equals(HttpStatus.OK);
    }
}
