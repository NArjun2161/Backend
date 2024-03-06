package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.ApiConfig;

public interface ApiConfigService {
    ApiConfig saveApiConfig(ApiConfig apiConfig);

    BaseResponse updateApiConfig(ApiConfig apiConfig);
}
