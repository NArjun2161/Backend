package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.model.Admin;
import com.istato.admin.model.AdminUpdatePasswordRequest;
import com.istato.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(EndPointRefer.CREATE_ADMIN)
    @Valid
    public BaseResponse createAdmin(@RequestBody Admin admin) {
        log.info(EndPointRefer.CREATE_ADMIN + Constants.CONTROLLER_STARTED);
        return adminService.createAdmin(admin);
    }

    @PostMapping(EndPointRefer.ADMIN_LOGIN)
    @Valid
    public BaseResponse adminLogin(@RequestBody Admin admin) {
        log.info(EndPointRefer.ADMIN_LOGIN + Constants.CONTROLLER_STARTED);
        return adminService.adminLogin(admin);
    }
    @PutMapping(EndPointRefer.UPDATE_ADMIN_PASSWORD)
    @Valid
    public BaseResponse updateAdminPassword(@RequestBody AdminUpdatePasswordRequest adminUpdatePasswordRequest) {
        log.info(EndPointRefer.UPDATE_ADMIN_PASSWORD + Constants.CONTROLLER_STARTED);
        return adminService.updateAdminPassword(adminUpdatePasswordRequest);
    }

}
