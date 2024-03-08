package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.model.Role;
import com.istato.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(value = EndPointRefer.CREATE_ROLE_CONTROLLER)
    public BaseResponse createRole(@RequestBody Role role) {
        log.info(EndPointRefer.CREATE_ROLE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return roleService.createRole(role);
    }

    @PutMapping(value = EndPointReffer.UPDATE_ROLE_CONTROLLER)
    public BaseResponse updateRole(@RequestBody Role role) {
        log.info(EndPointReffer.UPDATE_ROLE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return roleService.updateRole(role);
    }
}
