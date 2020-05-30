package com.example.oauth.service.role;


import com.example.oauth.domain.SysRole;
import org.springframework.data.domain.Page;

/**
 * 角色
 */
public interface SysRoleService {
    /**
     * 保存角色
     *
     * @param sysRole
     */
    void saveRole(SysRole sysRole);

    /**
     * @param id
     * @return
     */
    SysRole findRoleById(Long id);

    /**
     * @param roleName
     * @return
     */
    SysRole findRoleByRoleName(String roleName);

    void deleteRoleById(Long id);

    /**
     * @return
     */

    Page<SysRole> findAllByPage(SysRole sysRole, int pageNumber, int pageSize);
}
