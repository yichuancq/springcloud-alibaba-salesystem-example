package com.example.oauth.service.user;

import com.example.oauth.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
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
        log.info("user info:{}", userInfo.toString());
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.isEnabled(),
                true,
                true,
                true,
                userInfo.getAuthorities());
        return user;
    }
}
