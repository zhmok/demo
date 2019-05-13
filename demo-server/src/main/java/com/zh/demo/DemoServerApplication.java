package com.zh.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoServerApplication.class, args);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test ok2..");
    }
}
