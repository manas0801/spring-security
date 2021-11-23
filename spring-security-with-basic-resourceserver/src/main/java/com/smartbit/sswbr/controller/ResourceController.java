package com.smartbit.sswbr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {


    @GetMapping("/api/v1/resource")
    public ResponseEntity<String> getResource(){

        return new ResponseEntity<>("Hello from Resource Controller", HttpStatus.OK);
    }
}
