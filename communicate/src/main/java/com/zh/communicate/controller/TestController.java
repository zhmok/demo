package com.zh.communicate.controller;

import com.zh.communicate.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 测试接口
 * @author zh
 */
@RestController
@RequestMapping("/api/${api.version:v1}/test")
public class TestController {

    String test(){
        return "nothing";
    }


    @GetMapping("")
    ResponseEntity test1(){
        User u = new User();
        u.setUsername("uname");
        u.setPassword("pwd");
        return ResponseEntity.of(Optional.of(u));
    }
}
