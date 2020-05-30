package com.example.oauth.controller;

import com.example.common.response.ResponseResultData;
import com.example.oauth.application.UserApplication;
import com.example.oauth.domain.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yichuan
 */
@Api(value = "userController")
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserApplication userApplication;

    /**
     * @return
     */
    @ApiOperation(value = "userAdd", notes = "userAdd")
    @PostMapping("/userAdd")
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission('/userAdd','sys:user:add')")
    public ResponseEntity userAdd(UserInfo userInfo, HttpServletRequest request) {
        userApplication.saveUser(userInfo);
        ModelAndView mav = new ModelAndView();
        logger.info("userData:{}", userInfo);
        logger.info("用户添加");
        mav.addObject("msg", "OK");
        return ResponseEntity.ok(mav);
    }

    /**
     * 提示无权限
     *
     * @return
     */
    @ApiOperation(value = "userDelete", notes = "userDelete")
    @GetMapping("/userDelete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void userDelete(Long userId) {
        userApplication.deleteUserById(userId);
        logger.info("用户删除:{}", userId);
    }

    /**
     * @return
     */
    @ApiOperation(value = "userMod", notes = "userMod")
    @PostMapping("/userMod")
    @PostAuthorize("hasRole('ROLE_ADMIN') and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity userMod(UserInfo userInfoInput, HttpServletRequest request, HttpServletResponse response) {
        logger.info("用户修改");
        ModelAndView mav = new ModelAndView();
        logger.info("userData:{}", userInfoInput);
        mav.addObject("msg", "OK");
        userApplication.userMod(userInfoInput);
        return ResponseEntity.ok(mav);
    }

    @GetMapping("/userData")
    public ResponseEntity userData(UserInfo userInfo, int page, int limit)
            throws Exception {
        logger.info("page{},limit{}", page, limit);
        logger.info("userData");
        ResponseResultData resultDTO = userApplication.findByPage(userInfo, page, limit);
        return ResponseEntity.ok(resultDTO);
    }
}
