package com.istato.admin.controller;


import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.service.ApiConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //Delete by ApiNAme & Isactive
    @DeleteMapping(EndPointReffer.DELETE_API_NAME_OR_ACTIVE_STATUS + "/{apiName}/{isactive}")
    public BaseResponse deleteApiNameOrActiveStaus(@PathVariable String apiName,@PathVariable boolean isactive) {
      log.info(EndPointReffer.DELETE_API_NAME_OR_ACTIVE_STATUS + Constants.CONTROLLER_STARTED);
        return apiConfigService.deleteApiNameOrActiveStatus(apiName,isactive);
    }
    //Delete by Api Name
    @DeleteMapping(EndPointReffer.DELETE_API_NAME_OR_ACTIVE_STATUS + "/{apiName}")
    public BaseResponse deleteApiName(@PathVariable String apiName ) {
        log.info(EndPointReffer.DELETE_API_NAME_OR_ACTIVE_STATUS + Constants.CONTROLLER_STARTED);
        return apiConfigService.deleteByApiName(apiName);
    }

    //delete by isactive only true or false
    @DeleteMapping(EndPointReffer.DELETE_API_ISACTIVE_STATUS + "/{isactive}")
    public BaseResponse deleteApiNameOrActiveStaus(@PathVariable boolean isactive) {
        log.info(EndPointReffer.DELETE_API_ISACTIVE_STATUS + Constants.CONTROLLER_STARTED);
        return apiConfigService.deleteApiNameOrActiveStatus(null,isactive);
    }
    //All delete Api
    @DeleteMapping(EndPointReffer.DELETE_API_NAME_OR_ACTIVE_STATUS)
    public BaseResponse deleteApiAll(){
        log.info(EndPointReffer.DELETE_API_NAME_OR_ACTIVE_STATUS + Constants.CONTROLLER_STARTED);
        return apiConfigService.deleteApiAll();
    }



}
