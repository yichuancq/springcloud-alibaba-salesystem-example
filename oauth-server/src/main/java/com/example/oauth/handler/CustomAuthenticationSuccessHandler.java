package com.example.oauth.handler;

import com.example.oauth.domain.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        UserInfo userDetails = (UserInfo) authentication.getPrincipal();

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter()
                .write(objectMapper.writeValueAsString(userDetails.getSysRoleList()));
        logger.info("登录用户user:{},getContextPath:{}",
                userDetails.getUsername()
                , httpServletRequest.getContextPath());

    }
}

