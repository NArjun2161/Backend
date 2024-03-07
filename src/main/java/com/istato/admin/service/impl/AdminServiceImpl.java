package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.Admin;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.repository.AdminRepository;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.service.AdminService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ApiConfigRepo apiConfigRepo;

    @Override
    public BaseResponse createAdmin(Admin admin) {
        BaseResponse baseResponse = null;
        log.info("Inside createAdmin Service ");
        try {
            if (admin != null && admin.getPassword().equals(admin.getConfirmedPassword())) {
                ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointReffer.CREATE_ADMIN);
                admin.setPassword(IstatoUtils.encryptString(admin.getPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                admin.setConfirmedPassword(IstatoUtils.encryptString(admin.getConfirmedPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                admin.setCreatedDate(new Date());
                baseResponse = adminRepository.createAdmin(admin);
            } else {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.PASSWORD_MISMATCH)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while creating admin with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }
}
