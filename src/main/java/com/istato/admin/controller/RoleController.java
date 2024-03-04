package com.istato.admin.controller;
import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.Role;
import com.istato.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(value = EndPointReffer.CREATE_ROLE_CONTROLLER)
    public BaseResponse createRole(@RequestBody Role role) {
        log.info(EndPointReffer.CREATE_ROLE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return roleService.createRole(role);
    }
}
