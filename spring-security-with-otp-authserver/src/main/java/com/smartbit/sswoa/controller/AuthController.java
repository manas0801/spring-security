package com.smartbit.sswoa.controller;

import com.smartbit.sswoa.model.User;
import com.smartbit.sswoa.model.UserOtp;
import com.smartbit.sswoa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/api/v1")
public class AuthController {


    @Autowired
    UserService userService;



    @PostMapping("/user/add")
    public ResponseEntity addUser(@RequestBody User user){

        userService.addUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/user/auth")
    public ResponseEntity authUser(@RequestBody User user) throws UsernameNotFoundException, NoSuchAlgorithmException {

        userService.authUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/otp/check")
    public ResponseEntity<Boolean> checkOtp(@RequestBody UserOtp userOtp){
      Boolean userServiceResponse= userService.checkOtp(userOtp);
      return new ResponseEntity(userServiceResponse,HttpStatus.OK);
    }

}
