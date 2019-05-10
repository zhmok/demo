package com.zh.demo.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication()
//@SpringBootApplication
@Controller
@Api("api")
public class AuthorazationApplication {

    @ApiOperation("test")
    @GetMapping("/")
    public String test(){
        return "/webjars/springfox-swagger-ui/swagger-ui";
    }
    @PostMapping("test1")
    public String testa(){
        return "/webjars/springfox-swagger-ui/swagger-ui";
    }

    public String t4esta(){
        return "/webjars/springfox-swagger-ui/swagger-ui";
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthorazationApplication.class, args);
        System.out.println(Package.getPackage("org.springframework.web"));
    }

}
