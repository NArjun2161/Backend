package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.Executive;
import com.istato.admin.model.ExecutiveApplication;
import com.istato.admin.service.ExecutiveApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExecutiveApplicationController {
    @Autowired
    ExecutiveApplicationService executiveApplicationService;
    @PostMapping(EndPointReffer.SAVE_EXECUTIVE_CONTROLLER)
    public BaseResponse saveExecutiveApplication(@RequestBody ExecutiveApplication executiveApplication){
        log.info(EndPointReffer.SAVE_EXECUTIVE_CONTROLLER + Constants.CONTROLLER_STARTED);
       return executiveApplicationService.saveExecutiveApplication(executiveApplication);
    }
}
