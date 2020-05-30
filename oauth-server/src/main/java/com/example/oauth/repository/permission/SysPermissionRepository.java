package com.example.oauth.repository.permission;

import com.example.oauth.domain.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysPermissionRepository extends JpaRepository<SysPermission,Long>,
        JpaSpecificationExecutor<SysPermission> {

    /**
     *
     * @param permission
     * @return
     */
    SysPermission findByPermission(String permission);
}
