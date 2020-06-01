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
//    @ApiOperation(value = "login", notes = "login")
//    @PostMapping("/login")
//    public ResponseResultData login(UserInfo userInfo, HttpServletRequest request) {
//        logger.info("登录=>login");
//        logger.info("userInfo:{}", userInfo);
//        HttpSession httpSession = request.getSession();
//        logger.info("sessionId:{}", httpSession.getId());
//        return new ResponseResultData(200, "登录成功");
//    }

    /**
     * @return
     */
//    @ApiOperation(value = "logout", notes = "logout")
//    @GetMapping("/logout")
//    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
//        logger.error("登录退出=>loginOut");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        request.setAttribute("msg", "logout");
//        return "logout";
//    }
//
//    @ApiOperation(value = "loginOutSuccess", notes = "loginOutSuccess")
//    @GetMapping("/loginOutSuccess")
//    public String loginOutSuccess(HttpServletRequest request, HttpServletResponse response) {
//        logger.error("登录退出成功=>loginOutSuccess");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        logger.info("用户user:{}", auth.getName());
//        request.setAttribute("msg", auth.getName() + ",登录退出成功");
//        return "logoutOutSuccess";
//    }

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
    @PreAuthorize("hasAnyAuthority('hello')")
    public String hello() {
        return "hello";
    }

    @GetMapping("query")
    @PreAuthorize("hasAnyAuthority('query')")
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
