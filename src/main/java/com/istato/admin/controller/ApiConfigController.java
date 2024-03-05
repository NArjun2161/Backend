package com.istato.admin.controller;


import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.service.ApiConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ApiConfigController {

    @Autowired
    ApiConfigService apiConfigService;

    @PostMapping(EndPointReffer.SAVE_API_CONFIG)
    public ApiConfig saveApiConfig(@RequestBody ApiConfig apiConfig) {
        log.info(EndPointReffer.SAVE_API_CONFIG + Constants.CONTROLLER_STARTED);
        return apiConfigService.saveApiConfig(apiConfig);
    }

    @GetMapping(EndPointReffer.GETALL_API_CONFIG + "/{isActive}")
    public List<ApiConfig> getApiConfig(@PathVariable Boolean isActive) {
        log.info(EndPointReffer.GETALL_API_CONFIG + Constants.CONTROLLER_STARTED);
        return apiConfigService.getAllApiConfig(isActive);
    }
    @GetMapping(EndPointReffer.GETALL_API_CONFIG+"/{apiName}")
    public ApiConfig getApiConfig(@PathVariable String apiName) {
        log.info(EndPointReffer.GETALL_API_CONFIG + Constants.CONTROLLER_STARTED);
        return apiConfigService.getApiConfig(apiName);
    }
}
