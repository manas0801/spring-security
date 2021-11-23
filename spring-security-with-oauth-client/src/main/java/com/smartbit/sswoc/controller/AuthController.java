package com.smartbit.sswoc.controller;

import com.smartbit.sswoc.model.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Controller
public class AuthController {

    @Value("${spring.security.oauth2.client.provider.myprovider.token-uri}")
    String tokenUri;
    @Value("${spring.security.oauth2.client.registration.myprovider.authorizationGrantType}")
    String grant_type;
    @Value("${spring.security.oauth2.client.registration.myprovider.redirectUri}")
    String redirectUri;
    @Value("${resource.uri}")
    String resourceUri;

    @GetMapping("/")
    public String hello(){
        return "hello.html";
    }

    @GetMapping("/home")
    public String home(@RequestParam("code") String code, Model model){
        HttpHeaders httpHeaders  = new HttpHeaders();
        httpHeaders.add("content_type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        MultiValueMap<String,String> map= new LinkedMultiValueMap<>();
        map.add("code",code);
        map.add("grant_type",grant_type);
        map.add("scope","webclient");
        map.add("redirect_uri",redirectUri);
        String plainCreds = "app3:sec3";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        httpHeaders.add("Authorization","Basic "+ base64Creds);
        HttpEntity<MultiValueMap<String,String>> entity= new HttpEntity<>(map,httpHeaders);

       AuthResponse authResponse=  new RestTemplate().postForEntity(tokenUri,entity, AuthResponse.class).getBody();

       HttpHeaders headers = new HttpHeaders();
       headers.add("authorization",authResponse.getToken_type()+" "+authResponse.getAccess_token());
       HttpEntity entity1 = new HttpEntity(headers);
       String message= new RestTemplate().exchange(resourceUri, HttpMethod.GET,entity1,String.class).getBody();
       model.addAttribute("message",message);
       return "home.html";

    }
}
