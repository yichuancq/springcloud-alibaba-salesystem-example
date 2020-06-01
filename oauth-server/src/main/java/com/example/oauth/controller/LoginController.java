package com.example.oauth.controller;

import com.example.oauth.domain.UserInfo;
import com.example.oauth.service.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author yichuan
 */
@RestController
@RequestMapping("/api")
@Api(value = "api")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * @return
     */
    @ApiOperation(value = "findUserByName", notes = "findUserByName")
    @GetMapping("/findUserByName")
    @PreAuthorize("hasRole('游客') or hasRole('管理员')")
    public ResponseEntity findUserByName(String userName) {
        UserInfo userInfo = userInfoService.findUserByName(userName);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("hello")
    @ApiOperation(value = "hello", notes = "hello")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String hello() {
        return "hello";
    }

    @GetMapping("query")
    @ApiOperation(value = "query", notes = "query")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String query() {
        return "query";
    }


    @GetMapping("/member")
    public Principal user(Principal member) {
        return member;
    }

    @DeleteMapping(value = "/exit")
    public boolean revokeToken(String access_token) {
        return consumerTokenServices.revokeToken(access_token);
    }

}
