package com.example.oauth.domain;

import com.example.oauth.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class UserInfo extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;// 用户id
    //用户名称不能重复
    @Column(unique = true)
    private String username;// 用户名
    private String nickname;//（昵称或者真实姓名，不同系统不同定义）
    private String password;// 密码
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<SysRole> sysRoleList;// 用户权限集合

    public UserInfo() {
    }

    /**
     * 解析用户权限
     *
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<SysRole> roles = this.getSysRoleList();
        roles.forEach(sysRole -> grantedAuthorities.add(new SimpleGrantedAuthority(sysRole.getRole())));
        return grantedAuthorities;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", sysRoleList=" + sysRoleList +
                '}';
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }


    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
