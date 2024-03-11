package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.baseclasses.*;
import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.ErrorCodes;
import com.istato.admin.baseclasses.Errors;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.service.ApiConfigService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Slf4j
public class ApiConfigServiceImpl implements ApiConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ApiConfigServiceImpl.class);
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
    public List<ApiConfig> getAllApiConfig(String isActive) {
        try {
            if (isActive.isEmpty()) {
                return apiConfigRepo.getAllApiConfig(null);
            } else if (isActive.equals("false") || isActive.equals("true")) {
                Boolean isActiveBoolean = Boolean.parseBoolean(isActive);
                return apiConfigRepo.getAllApiConfig(isActiveBoolean);
            } else {
                throw new RuntimeException("isActive can only be true or false ");
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching api config object {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public BaseResponse getApiConfig(String apiName) {
        ApiConfig apiConfig;
        BaseResponse baseResponse = null;
        log.info("Inside ApiConfigServiceImpl.getApiConfig");
        try {
            if (apiName != null) {
                apiConfig = apiConfigRepo.getApiConfig(apiName);
                if (apiConfig != null) {
                    baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, apiConfig);
                } else {
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.API_CONFIG_NOT_FOUND)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                            .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, errors);
                }
            } else {
                logger.error(Constants.NULL_REQUEST);
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NO_DATA_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                logger.error(ErrorCodes.ER00P2.toFaceValue());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }

        } catch (Exception e) {
            log.error("Exception occurred while fetching api config object {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateApiConfig(ApiConfig apiConfig) {
        BaseResponse baseResponse = null;
        log.info("Inside ApiConfigServiceImpl.updateApiConfig");
        try {
            if (apiConfig.getApiName() != null) {
                baseResponse = apiConfigRepo.updateApiConfig(apiConfig);
            } else {
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

    @Override
    public BaseResponse deleteApiNameOrActiveStatus(String apiName, boolean isactive) {
        BaseResponse baseResponse = new BaseResponse();
        log.info("Inside delete apiName or active status: apiName={}, isactive={}", apiName, isactive);
        try {
            if (apiName != null || (isactive != true || isactive != false)) {
                log.info("ApiConfigServiceImpl.deleteApiNameOrActiveStatus: apiName={}, isactive={}", apiName, isactive);
                baseResponse = apiConfigRepo.deleteApiNameOrActiveStatus(apiName, isactive);
            } else {
                log.error(Constants.NULL_REQUEST);
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NO_NAME_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                log.info(ErrorCodes.ER00P2.toFaceValue());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while deleting api config object: {}", e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deleteByApiName(String apiName) {
        BaseResponse baseResponse = new BaseResponse();
        log.info("inside delete by api Name only: {}", apiName);
        try {
            if (apiName != null) {
                log.info("ApiConfigServiceImpl.deleteApiNAme:{} ", apiName);
                baseResponse = apiConfigRepo.deleteByApiName(apiName);
            } else {
                log.error(Constants.NULL_REQUEST);
                Collection<Errors> erroes = new ArrayList<>();
                erroes.add(Errors.builder()
                        .message(ErrorCode.NO_NAME_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                log.info(ErrorCodes.ER00P2.toFaceValue());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, erroes);
            }
        } catch (Exception e) {
            log.info("Exception occurred delete api config object {}", e.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse deleteApiAll() {
        BaseResponse baseResponse = new BaseResponse();
        log.info("Inside delete all API configurations");
        try {
            // Check if the condition for deleting all APIs is satisfied
            boolean isSafeToDeleteAll = true; // Set this variable based on your condition

            if (isSafeToDeleteAll) {
                log.info("Deleting all API configurations");
                baseResponse = apiConfigRepo.deleteAll(); // Call your repository method to delete all APIs
            } else {
                log.error(Constants.NULL_REQUEST);
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NO_NAME_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                log.info(ErrorCodes.ER00P2.toFaceValue());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while deleting API configurations: {}", e.getMessage());
        }
        return baseResponse;
    }
}
