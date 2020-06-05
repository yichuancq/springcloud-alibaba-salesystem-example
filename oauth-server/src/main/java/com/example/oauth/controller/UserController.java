package com.example.oauth.controller;

import com.example.oauth.domain.base.BaseUser;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichuan
 */
@Api(value = "/api")
@RestController
public class UserController {

    @GetMapping("/user")
    public BaseUser user(BaseUser user) {
        return user;
    }

    @GetMapping("/test")
    public String test() {
        return "call test";
    }

    @GetMapping("/query")
    public String query() {
        return "call query";
    }
}
