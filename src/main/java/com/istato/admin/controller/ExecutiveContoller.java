package com.istato.admin.controller;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.Executive;
import com.istato.admin.model.VerifyOtpRequest;
import com.istato.admin.service.ExecutiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ExecutiveContoller {
    @Autowired
    ExecutiveService executiveService;

    @PostMapping(value = EndPointRefer.CREATE_EXECUTIVE_CONTROLLER)
    public BaseResponse createExecutive(@RequestBody Executive executive) throws Exception {
        log.info(EndPointRefer.CREATE_EXECUTIVE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.createExecutive(executive);
    }

    @PostMapping(value = EndPointRefer.EXECUTIVE_LOGIN)
    public BaseResponse executiveLogin(@RequestBody Executive executive) throws Exception {
        log.info(EndPointRefer.EXECUTIVE_LOGIN + Constants.CONTROLLER_STARTED);
        return executiveService.executiveLogin(executive);
    }

    @PostMapping(value = EndPointRefer.EXECUTIVE_RESET_PASSWORD)
    public BaseResponse executiveResetPassword(@RequestBody Executive executive) throws Exception {
        log.info(EndPointRefer.EXECUTIVE_RESET_PASSWORD + Constants.CONTROLLER_STARTED);
        return executiveService.executiveResetPassword(executive);
    }

    @PostMapping(value = EndPointRefer.VERIFY_OTP)
    public BaseResponse verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) throws Exception {
        log.info(EndPointRefer.VERIFY_OTP + Constants.CONTROLLER_STARTED);
        return executiveService.verifyOtp(verifyOtpRequest);
    }


    @GetMapping(EndPointRefer.GET_ALL_EXECUTIVES_CONTROLLER)
    public List<BaseResponse> getAllExecutives() {
        log.info(EndPointRefer.GET_ALL_EXECUTIVES_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.getAllExecutives();
    }

    @GetMapping(value = EndPointRefer.GET_ALL_ACTIVE_EXECUTIVES_CONTROLLER + "/{isActive}")
    public List<BaseResponse> getAllActiveExecutives(@PathVariable String isActive) {
        log.info(EndPointRefer.GET_ALL_ACTIVE_EXECUTIVES_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.getAllActiveExecutives(isActive);
    }

    @PutMapping(value = EndPointRefer.UPDATE_EXECUTIVE_CONTROLLER)
    public BaseResponse updateExecutive(@RequestBody Executive updatedExecutive) {
        log.info(EndPointRefer.UPDATE_EXECUTIVE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.updateExeutive(updatedExecutive);
    }

}
