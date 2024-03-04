package com.istato.admin.repository;

import com.istato.admin.model.ApiConfig;

public interface ApiConfigRepo {
    ApiConfig saveApiConfig(ApiConfig apiConfig);

    ApiConfig getApiConfig(String apiName);
}
