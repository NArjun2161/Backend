package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.Executive;
import com.istato.admin.service.ExecutiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExecutiveContoller {
    @Autowired
    ExecutiveService executiveService;
    @PostMapping(value = EndPointReffer.CREATE_EXECUTIVE_CONTROLLER)
    public BaseResponse createExecutive(@RequestBody Executive executive) {
        log.info(EndPointReffer.CREATE_EXECUTIVE_CONTROLLER + Constants.CONTROLLER_STARTED);
        return executiveService.createExecutive(executive);
    }


}
