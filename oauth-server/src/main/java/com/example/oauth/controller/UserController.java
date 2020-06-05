package com.example.oauth.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author yichuan
 */
@Api(value = "/api")
@RestController
@Slf4j
public class UserController {


    /**
     * 用于获取当前token的用户信息
     *
     * @param principal
     * @return
     */
    @GetMapping(value = "/user")
    public Principal getUser(Principal principal) {
        if (principal != null) {
            log.info(principal.toString());
        }
        return principal;
    }

    /**
     * 请求方式
     * http://localhost:9001/auth/test?access_token=9e72121a-f537-4657-87f8-90fcf8555d33
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        log.info("call test");
        return "call test";
    }

    /**
     * http://localhost:9001/auth/query?access_token=9e72121a-f537-4657-87f8-90fcf8555d33
     *
     * @return
     */
    @GetMapping("/query")
    public String query() {
        log.info("call query");
        return "call query";
    }

    /**
     * @return
     */
    @GetMapping("/role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String role() {
        log.info("call role");
        return "call role";
    }

    /**
     * @return
     */
    @GetMapping("/showUser")
    @PreAuthorize("hasAuthority('sys:user:show')")
    public String showUser() {
        log.info("call showUser");
        return "call showUser";
    }
}
