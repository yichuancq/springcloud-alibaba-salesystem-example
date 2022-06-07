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

    /**
     * 删除角色
     * @param id
     */
    void deleteRoleById(Long id);

    /**分页查询
     * @return
     */

    Page<SysRole> findAllByPage(SysRole sysRole, int pageNumber, int pageSize);
}
