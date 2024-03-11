package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.ExecutiveApplication;
import com.istato.admin.repository.ExecutiveApplicationRepo;
import com.istato.admin.service.ExecutiveApplicationService;
import com.istato.admin.utils.IstatoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service

public class ExecutiveApplicationServiceImpl implements ExecutiveApplicationService {
    @Autowired
    ExecutiveApplicationRepo executiveApplicationRepo;
    private static final Logger logger = LoggerFactory.getLogger(ExecutiveApplicationServiceImpl.class);

    @Override
    public BaseResponse saveExecutiveApplication(ExecutiveApplication executiveApplication) {
        BaseResponse baseResponse = null;
        ExecutiveApplication executiveApplication1;
        try {
            if (executiveApplication != null) {
                if (executiveApplication.getExecutiveId() != null) {
                    logger.info("ExecutiveApplicationServiceImpl: saveExecutiveApplication: {}", executiveApplication);
                    executiveApplicationRepo.saveExecutiveApplication(executiveApplication);
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
            logger.error("Exception Occurs while saving", e.getMessage());
            throw new RuntimeException("Exception occurred while saving");

        }
        return baseResponse;
    }
}
