package com.example.oauth.controller;

import com.example.common.response.ResponseResultData;
import com.example.oauth.application.PermissionApplication;
import com.example.oauth.domain.SysPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "permissionController")
@Controller
public class PermissionController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PermissionApplication permissionApplication;

    /**
     * @param sysPermission
     * @return
     */
    @ApiOperation(value = "permissionAdd", notes = "permissionAdd")
    @PostMapping("/permissionAdd")
    @ResponseBody
    public ResponseEntity permissionAdd(SysPermission sysPermission) {
        try {
            assert (sysPermission != null);
            assert (!StringUtils.isEmpty(sysPermission.getPermission()));
            assert (!StringUtils.isEmpty(sysPermission.getUrl()));
            assert (!StringUtils.isEmpty(sysPermission.getName()));
            permissionApplication.saveSysPermission(sysPermission);
            ModelAndView mav = new ModelAndView();
            logger.info("sysPermission:{}", sysPermission);
            logger.info("添加权限");
            mav.addObject("msg", "OK");
            return ResponseEntity.ok(mav);
        } catch (Exception e) {
            logger.error("error:{}", e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    /**
     * @return
     */
    @ApiOperation(value = "permissionMod", notes = "permissionMod")
    @PostMapping("/permissionMod")
    public ResponseEntity permissionMod(SysPermission sysPermissionInput) {
        try {
            assert (sysPermissionInput != null);
            assert (!StringUtils.isEmpty(sysPermissionInput.getPermission()));
            assert (!StringUtils.isEmpty(sysPermissionInput.getUrl()));
            assert (!StringUtils.isEmpty(sysPermissionInput.getName()));
            logger.info("permission修改");
            ModelAndView mav = new ModelAndView();
            logger.info("sysPermissionInput:{}", sysPermissionInput);
            mav.addObject("msg", "OK");
            permissionApplication.PermissionMod(sysPermissionInput);
            return ResponseEntity.ok(mav);
        } catch (Exception e) {
            logger.error("error:{}", e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }

    }


    @GetMapping("/permissionData")
    @ResponseBody
    public ResponseEntity roleData(SysPermission sysPermission, int page, int limit)
            throws Exception {
        logger.info("page{},limit{}", page, limit);
        logger.info("roleData");
        ResponseResultData resultDTO = permissionApplication.findByPage(sysPermission, page, limit);
        return ResponseEntity.ok(resultDTO);
    }


    @GetMapping("/permissionList")
    public String permissionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("permissionList");
        return "permissionList";
    }

    /**
     * 提示无权限
     *
     * @return
     */
    @ApiOperation(value = "permissionDelete", notes = "permissionDelete")
    @PostMapping("/permissionDelete")
    @ResponseBody
    public void permissionDelete(Long permissionId) {
        permissionApplication.deleteUserById(permissionId);
        logger.info("用户删除");
    }


}
