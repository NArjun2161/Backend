package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ApiConfigRepoImpl implements ApiConfigRepo {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ApiConfig saveApiConfig(ApiConfig apiConfig) {
        log.info("Inside ApiConfigRepoImpl.saveApiConfig");
        try {
            return mongoTemplate.save(apiConfig);
        } catch (Exception e) {
            log.error("Exception occurred while saving API config to DB {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApiConfig getApiConfig(String apiName) {
        log.info("Inside getApiConfig");
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.API_NAME).is(apiName)
                    .and(Constants.IS_ACTIVE).is(true));
            return mongoTemplate.findOne(query, ApiConfig.class);
        } catch (Exception e) {
            log.error("Exception occurred while fetching apiConfig from DB with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseResponse deleteApiNameOrActiveStatus(String apiName, boolean isactive) {
        BaseResponse baseResponse = null;

        try {
            log.info("inside delete Api By name By is active status by only active status by only not active status by only name");
            Query query = new Query();

            if (apiName != null && isactive) {
                log.info("Api name not null and isactive status true");
                query.addCriteria(Criteria.where(Constants.API_NAME).is(apiName).and("isActive").is(isactive));
            } else if (apiName != null && !isactive) {
                log.info("Api name not null and isactive status false");
                query.addCriteria(Criteria.where(Constants.API_NAME).is(apiName).and("isActive").is(isactive));
            }  else if (isactive) {
                log.info("Deleting all records with isActive status true");
                query.addCriteria(Criteria.where("isActive").is(true));
            } else if (!isactive) {
                log.info("Deleting all records with isActive status false");
                query.addCriteria(Criteria.where("isActive").is(false));
            }
            log.info("query: {}", query);
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.remove(query, ApiConfig.class));

        } catch (Exception e) {
            log.error("Exception occurred while deleting Api by name", e);
            // Provide meaningful error response
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteByApiName(String apiName) {
        BaseResponse baseResponse = null;
        try {
            log.info("inside delete by api name only:{}");
            Query query = new Query();
            if (apiName != null) {
                log.info("inside api name not null");
                query.addCriteria(Criteria.where(Constants.API_NAME).is(apiName));
            }
            log.info("query: {}", query);
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.remove(query, ApiConfig.class));
        } catch (Exception e) {
            log.info("Exception accurred while delete by api name only");
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteAll() {
        BaseResponse baseResponse;
        log.info("Deleting all API configurations");
        try {
            if (mongoTemplate != null) {
                baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.remove(new Query(), ApiConfig.class));
            } else {
                log.error("MongoTemplate is null");
                baseResponse = IstatoUtils.getBaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "MongoTemplate is null");
            }
        } catch (Exception e) {
            log.error("Exception occurred while deleting API configurations: {}", e.getMessage());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return baseResponse;

    }
}