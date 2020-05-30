package com.example.oauth.config;

import com.example.oauth.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * RBAC数据模型控制权限
 */
@Component("rbacPermission")
public class RbacPermission {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 是否有权限
     *
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        logger.info("principal:{}", principal);
        logger.info("request.getRequestURI():{}", request.getRequestURI());
        //AtomicBoolean类提供了可以原子读取和写入的底层布尔值的操作，并且还包含高级原子操作
        AtomicBoolean hasPermission = new AtomicBoolean(false);
        if (principal instanceof UserInfo) {
            //读取用户所拥有的权限菜单
            UserInfo userInfo = (UserInfo) principal;
            logger.info("userInfo:{}", userInfo.toString());
            //双层循环先得到角色根据角色获取权限，通过权限匹配URL
            userInfo.getSysRoleList().stream().forEach(sysRole ->
                    sysRole.getPermissions().forEach(sysPermission -> {
                        if (antPathMatcher.match(sysPermission.getUrl(), request.getRequestURI())) {
                            hasPermission.set(true);
                            logger.info("hasPermission:{}", hasPermission.get());
                        }
                    }));
        }
        logger.info("hasPermission:{}", hasPermission);
        return hasPermission.get();
    }
}
