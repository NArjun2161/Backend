package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.ApiConfig;

import java.util.List;

public interface ApiConfigService {
    ApiConfig saveApiConfig(ApiConfig apiConfig);
   List<ApiConfig> getAllApiConfig(String isActive);
    BaseResponse getApiConfig(String apiName);

    BaseResponse updateApiConfig(ApiConfig apiConfig);
}
