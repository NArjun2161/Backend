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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        ApiConfig apiConfig=null;
        log.info("Inside ApiConfigRepoImpl.getApiConfig");
        try{

            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.API_NAME).is(apiName)
                    .and(Constants.IS_ACTIVE).is(true));
            apiConfig = mongoTemplate.findOne(query, ApiConfig.class);
        } catch (Exception e) {
            log.error("Exception occurred while fetching apiConfig from DB with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return apiConfig;
    }
    @Override
    public List<ApiConfig> getAllApiConfig(Boolean isActive) {
        List<ApiConfig> apiConfigList ;
        try{
            if(isActive!=null){
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.IS_ACTIVE).is(isActive));
            apiConfigList = mongoTemplate.find(query, ApiConfig.class);
            }else {
            apiConfigList = mongoTemplate.findAll(ApiConfig.class);
            }
            return apiConfigList;
        } catch (Exception e) {
            log.error("Exception occurred while fetching apiConfig from DB with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseResponse updateApiConfig(ApiConfig apiConfig) {
        BaseResponse baseResponse= null;
        Query query = new Query();
        Update update = new Update();
        log.info("Inside ApiConfigRepoImpl.updateApiConfig");
        try{
            query = query.addCriteria(Criteria.where(Constants.API_NAME).is(apiConfig.getApiName())
                    .and(Constants.IS_ACTIVE).is(true));
            update.set(Constants.ENCRYPTION_KEY, apiConfig.getEncryptionKey());
            update.set(Constants.IV_KEY, apiConfig.getIvKey());
            update.set(Constants.IS_ACTIVE, apiConfig.isActive());
            update.set(Constants.SAND_BOX_NO, apiConfig.getSandboxNo());
            update.set(Constants.API_URL, apiConfig.getApiUrl());
            update.set(Constants.USERNAME, apiConfig.getUserName());
            update.set(Constants.PASSWORD, apiConfig.getPassword());
            update.set(Constants.API_KEY, apiConfig.getApiKey());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.upsert(query, update, ApiConfig.class));
        } catch (Exception e) {
            log.error("Exception occurred while updating APIConfig with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }
}
