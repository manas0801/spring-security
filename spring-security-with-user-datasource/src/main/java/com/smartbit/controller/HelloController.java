package com.smartbit.controller;

import com.smartbit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    UserDetailsManager userDetailsManager;

    @GetMapping("api/v1/hello")
    public ResponseEntity<String>  hello(){

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
