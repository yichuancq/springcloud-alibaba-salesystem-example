package com.example.oauth.application;

import com.example.common.response.ResponseResultData;
import com.example.oauth.domain.SysPermission;
import com.example.oauth.service.permission.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author yichuan
 */
@Service
public class PermissionApplication {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysPermissionService sysPermissionService;


    /**
     * @param sysPermission
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public ResponseResultData findByPage(SysPermission sysPermission, int pageNumber, int pageSize) {

        Page<SysPermission> sysRolePage = sysPermissionService.findAllByPage(sysPermission, pageNumber, pageSize);
        return new ResponseResultData(0, "ok", sysRolePage.getContent());
    }

    /**
     * @param sysPermission
     */
    public void saveSysPermission(SysPermission sysPermission) throws Exception {
        assert (sysPermission != null);
        if (sysPermission != null) {
            sysPermission.setSysRoleList(null);
            sysPermission.setSysPermissionList(null);
            sysPermission.setAvailable(true);
        }
        if (StringUtils.isEmpty(sysPermission.getName())) {
            logger.info("权限名称为空");
            throw new Exception("权限名称为空");
        }
        if (StringUtils.isEmpty(sysPermission.getPermission())) {
            logger.info("权限为空");
            throw new Exception("权限为空");
        }
        sysPermissionService.saveSysPermission(sysPermission);
    }

    public void deleteUserById(Long permissionId) {
        assert (permissionId != null);
        sysPermissionService.deletePermissionById(permissionId);
    }

    /**
     * @param sysPermissionInput
     * @throws Exception
     */
    public void PermissionMod(SysPermission sysPermissionInput) throws Exception {
        if (sysPermissionInput == null || sysPermissionInput.getId() == null) {
            //用户为空或者ID为空
            throw new Exception("用户为空或者ID为空");
        }
        if (StringUtils.isEmpty(sysPermissionInput.getName())) {
            logger.info("权限名称为空");
            throw new Exception("权限名称为空");
        }
        if (StringUtils.isEmpty(sysPermissionInput.getPermission())) {
            logger.info("权限为空");
            throw new Exception("权限为空");
        }
        SysPermission sysPermissionDb = sysPermissionService.findById(sysPermissionInput.getId());
        if (sysPermissionInput != null) {
            sysPermissionDb.setName(sysPermissionInput.getName());
            sysPermissionDb.setPermission(sysPermissionInput.getPermission());
            sysPermissionDb.setUrl(sysPermissionInput.getUrl());
            sysPermissionService.saveSysPermission(sysPermissionDb);
        }
    }
}
