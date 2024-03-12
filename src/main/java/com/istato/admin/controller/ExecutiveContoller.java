package com.istato.admin.controller;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.Executive;
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

    @PostMapping(value = EndPointReffer.CREATE_EXECUTIVE_CONTROLLER)
    public BaseResponse createExecutive(@RequestBody Executive executive) throws Exception {
        log.info(EndPointReffer.CREATE_EXECUTIVE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.createExecutive(executive);
    }

    @GetMapping(EndPointReffer.GET_ALL_EXECUTIVES_CONTROLLER)
    public List<BaseResponse> getAllExecutives() {
        log.info(EndPointReffer.GET_ALL_EXECUTIVES_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.getAllExecutives();
    }

    @GetMapping(value = EndPointReffer.GET_ALL_ACTIVE_EXECUTIVES_CONTROLLER + "/{isActive}")
    public List<BaseResponse> getAllActiveExecutives(@PathVariable String isActive) {
        log.info(EndPointReffer.GET_ALL_ACTIVE_EXECUTIVES_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.getAllActiveExecutives(isActive);
    }

    @PutMapping(value = EndPointReffer.UPDATE_EXCUTIVE_CONTROLLER)
    public BaseResponse updateExecutive(@RequestBody Executive updatedExecutive) {
        log.info(EndPointReffer.UPDATE_EXCUTIVE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.updateExeutive(updatedExecutive);
    }

}
