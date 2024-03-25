package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.model.PlanDetails;
import com.istato.admin.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(EndPointRefer.GET_All_PLANS)
    public List<PlanDetails> getAllPlans() {
        log.info(EndPointRefer.GET_All_PLANS + Constants.CONTROLLER_STARTED);
        return planService.getAllPlans();
    }
    @GetMapping(EndPointRefer.GET_PLAN_BY_ID + "/{planId}")
    public PlanDetails getPlanById(@PathVariable String planId){
        log.info(EndPointRefer.GET_PLAN_BY_ID + Constants.CONTROLLER_STARTED);
        return  planService.getPlanById(planId);
    }

    @GetMapping(EndPointRefer.GET_PLAN_BY_STATUS +"/{isActive}")
        public List<PlanDetails> getPlanBYStatus(@PathVariable boolean isActive){
            log.info(EndPointRefer.GET_PLAN_BY_STATUS + Constants.CONTROLLER_STARTED);
            return  planService.getPlanByStatus(isActive);
        }

}
