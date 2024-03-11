package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Executive;
import com.istato.admin.repository.ExecutiveRepository;
import com.istato.admin.utils.IstatoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

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
    public List<Executive> getAllExecutives(boolean isActive) {
        Query query = new Query(Criteria.where("isActive").is(isActive));
        List<Executive> executives = mongoTemplate.find(query, Executive.class);
        return executives;
    }
}