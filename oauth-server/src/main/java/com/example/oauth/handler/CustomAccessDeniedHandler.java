package com.example.oauth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 访问权限失败
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e)
            throws IOException, ServletException {
        //
        boolean isAjax = ControllerTools.isAjaxRequest(httpServletRequest);
        if (!httpServletResponse.isCommitted()) {
            if (isAjax) {
                String accessDenyMsg = "{\"code\":\"403\",\"msg\":\"没有权限\"}";
                String msg = e.getMessage();
                logger.info("accessDeniedException.message:" + msg);
                ControllerTools.print(httpServletResponse, accessDenyMsg);
            } else {
                httpServletRequest.setAttribute(WebAttributes.ACCESS_DENIED_403, e);
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/403");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }
        }
    }

    public static class ControllerTools {
        /**
         * 是否是ajax请求
         *
         * @param request
         * @return
         */
        public static boolean isAjaxRequest(HttpServletRequest request) {
            return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        }

        public static void print(HttpServletResponse response, String msg) throws IOException {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(msg);
            writer.flush();
            writer.close();
        }
    }
}
