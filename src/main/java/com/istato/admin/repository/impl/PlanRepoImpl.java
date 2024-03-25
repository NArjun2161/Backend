package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.model.PlanDetails;
import com.istato.admin.repository.PlanRepo;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<PlanDetails> getAllPlans() {
        log.info("Inside get all plans in repiImpl");
        List<PlanDetails> planDetailsList=null;
        try {
            planDetailsList =  mongoTemplate.findAll(PlanDetails.class);
        }catch (Exception e){
            log.info("Exception occurred while getting all plans {}", e.getMessage());
        }
        return planDetailsList;
    }

    @Override
    public PlanDetails getPlanById(String planId) {
        PlanDetails planDetails=null;
          try {
              log.info("inside get plan by id in repoimpl");
              Query query=new Query(Criteria.where(Constants.PLAN_ID).is(planId));
              log.info("query {}",query);
              planDetails=mongoTemplate.findOne(query, PlanDetails.class);
          }catch (Exception e){
              log.info("Exception occurred while getting plan by id {}", e.getMessage());

        }

        return planDetails;
    }

    @Override
    public List<PlanDetails> getPlanByStatus(boolean isActive) {
        List<PlanDetails> planDetails=null;
        try {
            log.info("inside get by plan status in repoimpl");
            Query query=new Query(Criteria.where(Constants.IS_ACTIVE).is(isActive));
            log.info("query {}", query);
            planDetails=mongoTemplate.find(query, PlanDetails.class);
        }catch (Exception e){
            log.info("Exception occurred while getting plan by status {}", e.getMessage());
        }
        return planDetails;
    }
}
