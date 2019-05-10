package com.zh.demo.eureka;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class TestController {

    @RequestMapping("/")
    public String test() {
        return "";
    }
}
