package com.example.oauth.controller;

import com.example.common.exception.ResultCode;
import com.example.common.response.ResponseResultData;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author yichuan
 */
@Api(value = "/api")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 供OAuth客户端获取认证对象时调用
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
     * http://localhost:9001/auth/role?access_token=9e72121a-f537-4657-87f8-90fcf8555d33
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

    /**
     * @param access_token
     * @return
     */
    @GetMapping(value = "/exit")
    public @ResponseBody
    ResponseResultData<?> revokeToken(String access_token) {
        try {
            if (consumerTokenServices.revokeToken(access_token)) {
                return new ResponseResultData<>(ResultCode.SUCCESS);
            }
            return new ResponseResultData<>(ResultCode.FAIL);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("error:{}", ex.getMessage());
        }
        return null;
    }
}
