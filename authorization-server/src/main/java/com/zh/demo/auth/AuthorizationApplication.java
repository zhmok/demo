package com.zh.demo.auth;

import com.zh.demo.auth.controller.HelloController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@EnableWebSecurity
@SpringBootApplication
@ComponentScan({"com.zh.demo.simple.c3","com.zh.demo.auth.*"})
public class AuthorizationApplication {


    public static void main(String[] args) {
        ApplicationContext c = SpringApplication.run(AuthorizationApplication.class, args);
        Object aa = c.getBean(HelloController.class);
        System.out.println(aa);
    }

}
