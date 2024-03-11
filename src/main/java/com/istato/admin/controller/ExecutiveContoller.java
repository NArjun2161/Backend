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

    @PostMapping(value = EndPointRefer.CREATE_EXECUTIVE_CONTROLLER)
    public BaseResponse createExecutive(@RequestBody Executive executive) {
        log.info(EndPointRefer.CREATE_EXECUTIVE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.createExecutive(executive);
    }

    @GetMapping(EndPointRefer.GET_ALL_EXECUTIVES_CONTROLLER)
    public List<BaseResponse> getAllExecutives() {
        log.info(EndPointRefer.GET_ALL_EXECUTIVES_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.getAllExecutives();
    }

    @GetMapping(value = EndPointRefer.GET_ALL_ACTIVE_EXECUTIVES_CONTROLLER + "/{isActive}")
    public List<Executive> getAllActiveExecutives(@PathVariable String isActive) {
        log.info(EndPointRefer.GET_ALL_ACTIVE_EXECUTIVES_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.getAllActiveExecutives(isActive);
    }

}
