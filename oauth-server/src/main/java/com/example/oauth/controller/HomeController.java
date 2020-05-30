package com.example.oauth.controller;

import com.example.oauth.domain.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yichuan
 */
@Controller
@Api(value = "HomeController")
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 欢迎界面
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "welcome", notes = "welcome")
    @GetMapping("/welcome")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView welcome() throws Exception {
        //获取登录的用户名
        ModelAndView mav = new ModelAndView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            logger.error("principal == null");
            throw new Exception("principal == nul");
        }
        UserInfo userInfo = (UserInfo) principal;
        String username = null;
        if (principal instanceof UserDetails) {
            username = userInfo.getUsername();
        } else {
            username = principal.toString();
        }
        logger.info("user name:{}", username);
        mav.setViewName("welcome");
        mav.addObject("userInfo", userInfo);
        mav.addObject("username", username);
        return mav;
    }


    /**
     * 错误页面
     *
     * @return
     */
    @GetMapping("/error")
    public String toErrorPage() {
        return "error";
    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    @PostAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String index() {
        return "index";
    }

    /**
     * 无权限处理页面
     *
     * @return
     */
    @GetMapping("/403")
    public String page403() {
        return "403";
    }
}
