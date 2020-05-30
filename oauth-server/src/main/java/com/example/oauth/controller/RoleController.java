package com.example.oauth.controller;

import com.example.common.response.ResponseResultData;
import com.example.oauth.application.RoleApplication;
import com.example.oauth.domain.SysRole;
import com.example.oauth.domain.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yichuan
 */
@Api(value = "roleController")
@Controller
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleApplication roleApplication;

    /**
     * @return
     */
    @ApiOperation(value = "addRole", notes = "addRole")
    @PostMapping("/addRole")
    @ResponseBody
    public ResponseEntity addRole(SysRole sysRole) {
        assert (sysRole != null);
        ModelAndView modelAndView = new ModelAndView();
        roleApplication.addSysRole(sysRole);
        modelAndView.setViewName("roleList");
        ModelAndView mav = new ModelAndView();

        mav.addObject("msg", "OK");
        return ResponseEntity.ok(mav);
    }

    @GetMapping("/roleData")
    @ResponseBody
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity roleData(SysRole sysRole, int page, int limit)
            throws Exception {
        logger.info("page{},limit{}", page, limit);
        logger.info("roleData");
        ResponseResultData resultDTO = roleApplication.findByPage(sysRole, page, limit);
        return ResponseEntity.ok(resultDTO);
    }

    /**
     * @return
     */
    @ApiOperation(value = "roleMod", notes = "roleMod")
    @PostMapping("/roleMod")
    public ResponseEntity roleMod(SysRole sysRoleInput) {
        assert (sysRoleInput.getId() != null);
        logger.info("修改");
        ModelAndView mav = new ModelAndView();
        logger.info("sysRoleInput:{}", sysRoleInput);
        mav.addObject("msg", "OK");
        roleApplication.roleMod(sysRoleInput);
        return ResponseEntity.ok(mav);
    }

    /**
     * @param roleId
     * @return
     */
    @PostMapping("/deleteRole")
    @ResponseBody
    public void deleteRole(Long roleId) {
        logger.info("roleId:{}", roleId);
        roleApplication.deleteRoleById(roleId);
    }

    /**
     * @param request
     * @return
     */
    @GetMapping("/roleList")
    public String toRoleListPage(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("userInfo", userInfo);
        return "roleList";
    }
}
