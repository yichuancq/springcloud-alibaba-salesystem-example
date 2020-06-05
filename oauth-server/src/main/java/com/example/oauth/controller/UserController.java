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

    /**
     * 请求方式
     * http://localhost:9001/auth/test?access_token=9e72121a-f537-4657-87f8-90fcf8555d33
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "call test";
    }

    /**
     * http://localhost:9001/auth/query?access_token=9e72121a-f537-4657-87f8-90fcf8555d33
     *
     * @return
     */
    @GetMapping("/query")
    public String query() {
        return "call query";
    }
}
