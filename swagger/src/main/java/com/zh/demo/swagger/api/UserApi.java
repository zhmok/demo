package com.zh.demo.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户api")
@RestController
public class UserApi {

    @ApiOperation("获取用户信息")
    @RequestMapping("/user")
    public String getUser() {

        return "user";
    }

    @GetMapping({"/csrf","/"})
    public String getUaser() {

        return "user";
    }
}
