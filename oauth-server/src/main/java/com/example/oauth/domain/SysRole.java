package com.example.oauth.domain;

import com.example.oauth.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class SysRole extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    /**
     * 角色标识程序中判断使用,如"admin",这个是唯一的:
     **/
    private String role;
    /***角色描述,UI界面显示使用***/
    private String description;
    /***是否可用,如果不可用将不会添加给用户***/
    private Boolean available = Boolean.FALSE;

    /***懒加载 不会查询role表***/
    @ManyToMany(mappedBy = "sysRoleList", fetch = FetchType.LAZY)
    private List<UserInfo> userInfoList;
    /***角色权限关系：多对多关系***/
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
    @ManyToMany(mappedBy = "sysRoleList", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<SysPermission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }
}
