package com.example.oauth.service.user;


import com.example.oauth.domain.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 用户信息
 */
public interface UserInfoService {
    /**
     * findUserByName
     *
     * @param username
     * @return
     */
    UserInfo findUserByName(String username);

    /**
     * save
     *
     * @param userInfo
     */
    void saveUser(UserInfo userInfo);

    /**
     * @return
     */
    List<UserInfo> findAll();

    /**
     * @param id
     */
    void deleteUserById(Long id);

    /**
     * @param id
     * @return
     */
    UserInfo findUserById(Long id);

    /**
     * @param
     * @return
     */
    Page<UserInfo> findAllByPage(UserInfo userInfo, int pageNumber, int pageSize);

}
