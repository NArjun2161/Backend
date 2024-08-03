package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.Errors;
import com.istato.admin.model.Executive;
import com.istato.admin.model.SendOtpResponse;
import com.istato.admin.repository.ExecutiveRepository;
import com.istato.admin.utils.IstatoUtils;
import org.apache.catalina.util.ErrorPageSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ExecutiveRepositoryImpl implements ExecutiveRepository {

    static Logger logger = LoggerFactory.getLogger(ExecutiveRepositoryImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaseResponse save(Executive executive) {
        BaseResponse baseResponse = null;
        logger.info("inside saveExecutive in repo");
        try {
            logger.info("inside saveExecutive");
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(executive));
        } catch (Exception e) {
            logger.error("Exception occurred while creating executive: {}", e.getMessage());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.EXPECTATION_FAILED, "Error creating executive.");
        }
        return baseResponse;
    }

    @Override
    public List<BaseResponse> getAllExecutives() {
        List<BaseResponse> baseResponse = null;
        List<Executive> executives = null;
        logger.info("inside getAllProducts");
        try {
            executives = mongoTemplate.findAll(Executive.class);
            baseResponse = Collections.singletonList(IstatoUtils.getBaseResponse(HttpStatus.OK, executives));

        } catch (Exception e) {
            logger.error("Exception occurred while getting product list {}", e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public List<BaseResponse> getAllExecutives(boolean isActive) {
        List<BaseResponse> baseResponse = null;
        List<Executive> executives = null;

        Query query = new Query(Criteria.where("isActive").is(isActive));

        logger.info("Inside getAllExecutives");

        try {
            executives = mongoTemplate.find(query, Executive.class);
            baseResponse = Collections.singletonList(IstatoUtils.getBaseResponse(HttpStatus.OK, executives));
        } catch (Exception e) {
            logger.error("Exception occurred while getting getAllExecutives list: {}", e.getMessage());
            // Handle the exception or log it based on your application's requirements
        }

        return baseResponse;

    }

    @Override
    public BaseResponse findByExecutiveId(String executiveId) {
        BaseResponse baseResponse = null;
        Query query = new Query(Criteria.where("executiveId").is(executiveId));

        try {
            Executive executive = mongoTemplate.findOne(query, Executive.class);

            if (executive != null) {
                return IstatoUtils.getBaseResponse(HttpStatus.OK, executive);
            } else {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message("Null executive...!")
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                return IstatoUtils.getBaseResponse(HttpStatus.NOT_FOUND, errors);
            }
        } catch (Exception e) {
            logger.error("Exception occurred while finding Executive by Id: {}", e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateExecutive(Executive updatedExecutive) {
        BaseResponse baseResponse = null;

        try {
            Query query = new Query();
            Update update = new Update();
            query = query.addCriteria(Criteria.where("executiveId").is(updatedExecutive.getExecutiveId()));
            update.set("personalDetails", updatedExecutive.getPersonalDetails());

            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.upsert(query, update, Executive.class));
        } catch (Exception e) {
            logger.error("An error occurred during upsert: {}", e.getMessage(), e);

            // Assign an error response
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during upsert");
        }
        if (baseResponse == null) {
            // Log a default error message
            logger.error("An unexpected error occurred.");

            // Provide a default error response
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }

        return baseResponse;

    }

    @Override
    public Executive getExecutiveByPan(String encryptedPanNumber) {
        logger.info("inside getExecutiveByPan");
        Executive executive = null;
        Query query = new Query();
        query = query.addCriteria(Criteria.where("personalDetails.panNumber").is(encryptedPanNumber));
        try {
            executive = mongoTemplate.findOne(query, Executive.class);
            logger.info("Executive : {}", executive);
        } catch (Exception e) {
            logger.error("Exception occurred while getting executive by pan number: {}", e.getMessage());
        }
        return executive;
    }

    @Override
    public Executive getExecutiveByUserName(String encryptedUserName) {
        Executive executive = null;
        Query query = new Query();
        query = query.addCriteria(Criteria.where(Constants.USERNAME).is(encryptedUserName));

        try {
            executive = mongoTemplate.findOne(query, Executive.class);
        } catch (Exception e) {

            logger.error("Exception occurred while getting executive by user name: {}", e.getMessage(), e);
        }
        return executive;
    }

    @Override
    public void saveOtpLogs(SendOtpResponse sendOtpResponse) {
        try{
            mongoTemplate.save(sendOtpResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SendOtpResponse getSmsResponse(String mobileNumber) {
        SendOtpResponse sendOtpResponse = null;
        try{
            Query query = new Query();
            query = query.addCriteria(Criteria.where("mobileNumber").is(mobileNumber));
            sendOtpResponse = mongoTemplate.findOne(query, SendOtpResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sendOtpResponse;
    }

}