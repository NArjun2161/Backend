package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.repository.ApiConfigRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
}
