package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.PlanDetails;

public interface PlanRepo {
    BaseResponse createPlan(PlanDetails planDetails);
}
