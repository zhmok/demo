package com.zh.demo.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@ComponentScan({"com.zh.demo.swagger","com.zh.demo.common"})
@RestController
@Api
public class SwaggerApplication {

    @ApiOperation("测试2 2222")
    @RequestMapping("/testt")
    public void t(){}

    public static void main(String[] args) {

        ApplicationContext run = SpringApplication.run(SwaggerApplication.class, args);
        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
