package com.istato.admin.repository.impl;

import com.istato.admin.model.ExecutiveApplication;
import com.istato.admin.repository.ExecutiveApplicationRepo;
import com.istato.admin.utils.IstatoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
public class ExecutiveApplicationRepoImpl implements ExecutiveApplicationRepo {
    static Logger logger= LoggerFactory.getLogger(ExecutiveApplicationRepoImpl.class);
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public ExecutiveApplication saveExecutiveApplication(ExecutiveApplication executiveApplication) {

        try {
            logger.info("inside saveExecutive");
            IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(executiveApplication));
        } catch (Exception e) {
            logger.error("Exception occurred while creating executive: {}", e.getMessage());
        }
        return executiveApplication;
    }
}
