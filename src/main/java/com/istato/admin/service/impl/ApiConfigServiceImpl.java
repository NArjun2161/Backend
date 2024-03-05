package com.istato.admin.service.impl;

import com.istato.admin.model.ApiConfig;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.service.ApiConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApiConfigServiceImpl implements ApiConfigService {
    @Autowired
    ApiConfigRepo apiConfigRepo;

    @Override
    public ApiConfig saveApiConfig(ApiConfig apiConfig) {
        log.info("Inside ApiConfigServiceImpl.saveApiConfig");
        try {
            if (apiConfig.getApiName() != null) {
                log.info("Received apiConfig {}", apiConfig);
                return apiConfigRepo.saveApiConfig(apiConfig);
            } else {
                log.error("apiName is required");
            }
        } catch (Exception e) {
            log.error("Exception occurred while saving api config object {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<ApiConfig> getAllApiConfig(Boolean isActive) {
        log.info("Inside ApiConfigServiceImpl.getAllApiConfig");
        try{
            return apiConfigRepo.getAllApiConfig(isActive);
        }catch (Exception e){
            log.error("Exception occurred while fetching api config object {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }
    @Override
    public ApiConfig getApiConfig(String apiName) {
        log.info("Inside ApiConfigServiceImpl.getApiConfig");
        try{
            return apiConfigRepo.getApiConfig(apiName);
        }catch (Exception e){
            log.error("Exception occurred while fetching api config object {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
