package com.example.oauth.application;

import com.example.common.response.ResponseResultData;
import com.example.oauth.domain.UserInfo;
import com.example.oauth.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author yichuan
 */
@Service
public class UserApplication {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @param userInfo
     */
    public void saveUser(UserInfo userInfo) {
        assert (userInfo != null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!StringUtils.isEmpty(userInfo.getPassword())) {
            //加密用户信息
            userInfo.setPassword(encoder.encode(userInfo.getPassword().trim()));
        }
        userInfoService.saveUser(userInfo);
    }

    /**
     * @param userInfo
     */
    public void userMod(UserInfo userInfo) {
        if (userInfo == null || userInfo.getId() == null) {
            //用户为空或者ID为空
            return;
        }
        if (!StringUtils.isEmpty(userInfo.getPassword())) {
            //密码进行加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        }
        UserInfo userInfoDb = userInfoService.findUserById(userInfo.getId());
        if (userInfoDb != null) {
            byte state = 1;
            userInfoDb.setState(state);
            userInfoDb.setNickname(userInfo.getNickname());
            userInfoDb.setUsername(userInfo.getUsername());
            userInfoService.saveUser(userInfoDb);
        }
    }

    /**
     * @return
     */
    public List<UserInfo> findAll() {
        return userInfoService.findAll();
    }

    public void deleteUserById(Long userId) {
        assert (userId != null);
        userInfoService.deleteUserById(userId);
    }

    /**
     * @param userInfo
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public ResponseResultData findByPage(UserInfo userInfo, int pageNumber, int pageSize) {
        Page<UserInfo> userInfoPage = userInfoService.findAllByPage(userInfo, pageNumber, pageSize);
        return new ResponseResultData(0, "ok", userInfoPage.getContent());
    }
}
