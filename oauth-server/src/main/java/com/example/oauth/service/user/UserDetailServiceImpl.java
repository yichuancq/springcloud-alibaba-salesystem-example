package com.example.oauth.service.user;

import com.example.oauth.domain.UserInfo;
import com.example.oauth.domain.base.BaseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author yichuan
 */
@Service("userDetailsService")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 通过用户姓名查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.findUserByName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名称不存在");
        }
        List<Long> roleIds = new ArrayList<>();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        BaseUser account = new BaseUser(userInfo.getUsername(),
                userInfo.getPassword(), enabled,
                accountNonExpired,
                credentialsNonExpired, accountNonLocked,
                userInfo.getAuthorities());
        log.info("user :{}", userInfo.toString());
        //添加roleIds
        userInfo.getSysRoleList().forEach(sysRole -> roleIds.add(sysRole.getId()));
        //
        BaseUser user = new BaseUser(userInfo.getUsername(), userInfo.getPassword(),
                enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, userInfo.getAuthorities());
        user.setClientId("test");
        user.setUserId(userInfo.getId());
        user.setRoleIds(roleIds);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("aa", "aa");
        user.setParams(params);
        //
        userInfo.getAuthorities().forEach(role->log.info("权限名称:{}",role.getAuthority()));
        roleIds.forEach(idItem -> log.info("idItem :{}", idItem));
        return account;
    }
}
