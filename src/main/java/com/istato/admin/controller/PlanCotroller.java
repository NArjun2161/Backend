package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.model.PlanDetails;
import com.istato.admin.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PlanCotroller {

    @Autowired
    PlanService planService;
    @PostMapping(EndPointRefer.CREATE_PLAN)
    public BaseResponse createPlan(@RequestBody PlanDetails planDetails) {
        log.info(EndPointRefer.CREATE_PLAN + Constants.CONTROLLER_STARTED+planDetails.toString());
        return planService.createPlan(planDetails);
    }
}
