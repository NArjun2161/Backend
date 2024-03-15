package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.PlanDetails;
import com.istato.admin.repository.PlanRepo;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class PlanRepoImpl implements PlanRepo {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public BaseResponse createPlan(PlanDetails planDetails) {
        BaseResponse baseResponse = null;
        log.info("Inside createPlan repository");
        try {
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(planDetails));
        } catch (Exception e) {
            log.error("Exception occurred while saving plan with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }
}
