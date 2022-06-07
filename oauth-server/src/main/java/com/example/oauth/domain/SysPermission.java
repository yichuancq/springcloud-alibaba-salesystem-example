package com.example.oauth.domain;

import com.example.oauth.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class SysPermission extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 资源类型，[menu|button]
     */
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;

    /**
     * 资源路径
     */
    private String url;
    private String permission;
    private Boolean available = Boolean.FALSE;
    /**
     * 父编码
     **/
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_Id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
    @JsonIgnore
    private SysPermission parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
    private List<SysPermission> sysPermissionList;
    /***懒加载***/
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<SysRole> sysRoleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public SysPermission getParent() {
        return parent;
    }

    public void setParent(SysPermission parent) {
        this.parent = parent;
    }

    public List<SysPermission> getSysPermissionList() {
        return sysPermissionList;
    }

    public void setSysPermissionList(List<SysPermission> sysPermissionList) {
        this.sysPermissionList = sysPermissionList;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }
}
