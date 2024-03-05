package com.istato.admin.service;

import com.istato.admin.model.ApiConfig;

import java.util.List;

public interface ApiConfigService {
    ApiConfig saveApiConfig(ApiConfig apiConfig);
   List<ApiConfig> getAllApiConfig(Boolean isActive);
    ApiConfig getApiConfig(String apiName);
}
