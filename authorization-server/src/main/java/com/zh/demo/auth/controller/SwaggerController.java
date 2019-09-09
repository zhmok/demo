package com.zh.demo.auth.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@Api("api")
public class SwaggerController {

//    @ApiOperation("test")
    @GetMapping("/")
    public String test() {
        return "/webjars/springfox-swagger-ui/swagger-ui";
    }

    @PostMapping("test1")
    public String testa() {
        return "/webjars/springfox-swagger-ui/swagger-ui";
    }
    public String t4esta(){
        return "/webjars/springfox-swagger-ui/swagger-ui";
    }
}
