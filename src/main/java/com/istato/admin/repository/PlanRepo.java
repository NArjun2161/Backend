package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.PlanDetails;

import java.util.List;

public interface PlanRepo {
    BaseResponse createPlan(PlanDetails planDetails);

    List<PlanDetails> getAllPlans();

    PlanDetails getPlanById(String planId);


    List<PlanDetails> getPlanByStatus(boolean isActive);
}
