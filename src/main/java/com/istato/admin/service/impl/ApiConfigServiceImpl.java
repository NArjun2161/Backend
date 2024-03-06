package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.ErrorCodes;
import com.istato.admin.baseclasses.Errors;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.service.ApiConfigService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

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
    public BaseResponse updateApiConfig(ApiConfig apiConfig) {
        BaseResponse baseResponse = null;
        log.info("Inside ApiConfigServiceImpl.updateApiConfig");
        try{
            if(apiConfig.getApiName() != null){
                baseResponse = apiConfigRepo.updateApiConfig(apiConfig);
            }else {
                log.error("ApiName is null");
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCodes.ER00P2.toFaceValue())
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(HttpStatus.BAD_REQUEST, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while updating api config object {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }
}
