package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.Role;
import com.istato.admin.service.RoleService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(value = EndPointReffer.CREATE_ROLE_CONTROLLER)
    public BaseResponse createRole(@RequestBody Role role) {
        log.info(EndPointReffer.CREATE_ROLE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return roleService.createRole(role);
    }

    @GetMapping(EndPointReffer.GET_ALL_ROLES)
    public List<Role> getAllRoles() {
        log.info(EndPointReffer.GET_ALL_ROLES + Constants.CONTROLLER_STARTED);
        return roleService.getAllRoles();
    }

    @GetMapping(EndPointReffer.GET_ALL_ROLES + "/{isActive}")
    public List<Role> getAllRolesByStatus(@PathVariable String isActive) {
        log.info(EndPointReffer.GET_ALL_ROLES + Constants.CONTROLLER_STARTED);
        return roleService.getAllRolesByStatus(isActive);
    }
}
