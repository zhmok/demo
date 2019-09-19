package com.zh.demo.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {


//    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired(required = false)
    SessionRegistry sessionRegister;

    @GetMapping
    public String get(Authentication auth) throws IOException, InterruptedException {
        log.info("auth : {}",auth);
//        sessionRegistry.getAllPrincipals();
        Thread.sleep(2000);
        HashMap hashMap = new HashMap();
        hashMap.put("1","get hello");
        return objectMapper.writeValueAsString(hashMap);
    }
    @PostMapping
    public String post(){


//        AnnotationUtils.findAnnotation("","");
        return "post hello";
    }
    @PutMapping
    public String put(){
        return "put hello";
    }
    @DeleteMapping
    public String delete(){
        return "delete hello";
    }
}
