package com.example.oauth.service.permission;


import com.example.oauth.domain.SysPermission;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 权限
 */
public interface SysPermissionService {
    /**
     * 保存权限
     *
     * @param sysPermission
     */
    void saveSysPermission(SysPermission sysPermission);

    /**
     * @param sysPermissions
     */
    void saveSysPermissions(List<SysPermission> sysPermissions);

    /**
     * @param id
     * @return
     */
    SysPermission findById(Long id);

    /**
     * @param permission
     * @return
     */
    SysPermission findSysPermissionByPermission(String permission);

    /**
     * @param ids
     * @return
     */
    List<SysPermission> findByIds(List<Long> ids);

    /**
     * @param sysPermission
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<SysPermission> findAllByPage(SysPermission sysPermission, int pageNumber, int pageSize);


    void deletePermissionById(Long permissionId);
}
