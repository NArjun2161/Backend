package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.ApiConfig;

import java.util.List;

public interface ApiConfigRepo {
    ApiConfig saveApiConfig(ApiConfig apiConfig);

    ApiConfig getApiConfig(String apiName);

    BaseResponse deleteApiNameOrActiveStatus(String apiName, boolean isactive);

    BaseResponse deleteByApiName(String apiName);

    BaseResponse deleteAll();

    List<ApiConfig> getAllApiConfig(Boolean isActive);

    BaseResponse updateApiConfig(ApiConfig apiConfig);
}
