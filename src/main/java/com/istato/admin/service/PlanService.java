package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.PlanDetails;

public interface PlanService {
    BaseResponse createPlan(PlanDetails planDetails);
}
