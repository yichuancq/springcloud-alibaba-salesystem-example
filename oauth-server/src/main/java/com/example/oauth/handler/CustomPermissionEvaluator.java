package com.example.oauth.handler;

import com.example.oauth.domain.SysPermission;
import com.example.oauth.domain.SysRole;
import com.example.oauth.domain.UserInfo;
import com.example.oauth.service.permission.SysPermissionService;
import com.example.oauth.service.role.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义权限控制
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private SysRoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        Object principal = authentication.getPrincipal();
        UserInfo userInfo = (UserInfo) principal;
        List<SysRole> roles = userInfo.getSysRoleList();
        // 遍历用户所有角色
        for (SysRole sysRole : roles) {
            for (SysPermission sysPermission : sysRole.getPermissions()) {
                // 如果访问的Url和权限用户符合的话，返回true
                if (antPathMatcher.match(String.valueOf(targetUrl), sysPermission.getUrl()) &&
                        antPathMatcher.match(sysPermission.getPermission(),
                                String.valueOf(targetPermission))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
