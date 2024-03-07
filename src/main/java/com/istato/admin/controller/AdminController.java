package com.istato.admin.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.baseclasses.ErrorCode;
import com.istato.admin.model.Admin;
import com.istato.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(EndPointReffer.CREATE_ADMIN)
    @Valid
    public BaseResponse createAdmin(@RequestBody Admin admin) {
        log.info(EndPointReffer.CREATE_ADMIN + Constants.CONTROLLER_STARTED);
        return adminService.createAdmin(admin);
    }
}
