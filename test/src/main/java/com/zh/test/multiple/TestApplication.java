package com.zh.test.multiple;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
@Slf4j
@RestController
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }


    @GetMapping("/test")
    public Callable<String> test() throws InterruptedException {
        log.info("start p");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                LoggerFactory.getLogger(TestApplication.class).info("start");
//        log.info("test");
                System.out.println(log);
                Thread.sleep(1000);
//                LoggerFactory.getLogger(com.zh.test.controller.TestController.class).info("end");
                return "aaa";
            }
        };
        log.info("start end");
        return callable;
    }
}
