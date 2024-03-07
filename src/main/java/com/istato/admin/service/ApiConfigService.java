package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.ApiConfig;

public interface ApiConfigService {
    ApiConfig saveApiConfig(ApiConfig apiConfig);

    BaseResponse deleteApiNameOrActiveStatus(String apiName, boolean isactive);

    BaseResponse deleteByApiName(String apiName);

    BaseResponse deleteApiAll();



}
