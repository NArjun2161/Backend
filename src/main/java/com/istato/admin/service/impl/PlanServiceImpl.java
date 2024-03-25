package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.model.PlanDetails;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.repository.PlanRepo;
import com.istato.admin.service.PlanService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {

    @Autowired
    ApiConfigRepo apiConfigRepo;
    @Autowired
    PlanRepo planRepo;

    @Override
    public BaseResponse createPlan(PlanDetails planDetails) {
        BaseResponse baseResponse = null;
        String planId = FieldSeprators.BLANK.toFaceValue();
        String boundValue = FieldSeprators.BLANK.toFaceValue();
        try {
            if (planDetails != null) {
                log.info("Inside createPlan {}", planDetails);
                if ((planDetails.getPlanType().equalsIgnoreCase(Constants.RENTAL) || planDetails.getPlanType().equalsIgnoreCase(Constants.SALE)) &&
                        (planDetails.getPropertyType().equalsIgnoreCase(Constants.LAND) || planDetails.getPropertyType().equalsIgnoreCase(Constants.FLAT))) {
                    ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_PLAN);
                    boundValue = apiConfig.getAdditionalProperties().getRandomNumberBound();
                    log.info("ApiConfig {}", apiConfig);
                    planId = IstatoUtils.generatePlanId(planDetails.getPlanType(), planDetails.getPropertyType(), boundValue);
                    planDetails.setPlanId(planId);
                    planDetails.setCreatedDate(new Date());
                    return planRepo.createPlan(planDetails);
                } else {
                    log.error("Wrong plan type or property type");
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.WRONG_PLAN_TYPE_OR_PROPERTY_TYPE)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                            .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                }
            } else {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NULL_REQUEST)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }

        } catch (Exception e) {
            log.error("Exception occurred while creating plan {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public List<PlanDetails> getAllPlans() {
        List<PlanDetails> planDetailsList=null;
        try {
            planDetailsList=planRepo.getAllPlans();
            log.info("PlanseDetails in service {}", planDetailsList);
        }
        catch (Exception e){
            log.error("Exception occurred while getting all plans {}", e.getMessage());
        }
        return planDetailsList;
    }

    @Override
    public PlanDetails getPlanById(String planId) {
        PlanDetails planDetails = null;
        BaseResponse baseResponse = null;
        try {
           if (planId != null){
               planDetails=planRepo.getPlanById(planId);
           }else {
               planDetails.setError(true);
               Collection<Errors> errors=new ArrayList<>();
                  errors.add(Errors.builder()
                          .message(ErrorCode.PLAN_ID_REQUIRED)
                          .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                          .errorType(Errors.ERROR_TYPE.USER.toValue())
                          .level(Errors.SEVERITY.HIGH.name())
                          .build());
                  log.info("Id is not found" ,ErrorCodes.ER00P2.toFaceValue());
                  baseResponse=IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
           }

        }catch (Exception e){
            log.info("Exception occurred while getting plan by id {}", e.getMessage());
        }

        return planDetails;
    }

    @Override
    public List<PlanDetails> getPlanByStatus(boolean isActive) {
        List<PlanDetails> planDetails=null;
        BaseResponse baseResponse=null;
        try {
            if (isActive || !isActive) {
                planDetails = planRepo.getPlanByStatus(isActive);
            } else {

                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.PLAN_STATUS_REQUIRED)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                log.info("Id is not found", ErrorCodes.ER00P2.toFaceValue());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);

            }
        }catch (Exception e){
            log.info("Exception occurred while getting plan by status {}", e.getMessage());
        }
        return  planDetails;
    }
}
