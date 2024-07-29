package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.*;
import com.istato.admin.repository.AdminRepository;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.service.AdminService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
                List<Admin> adminList = adminRepository.getAdminByUsername(admin.getUserName());
                if (adminList.isEmpty()) {
                    ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_ADMIN);
                    admin.setPassword(IstatoUtils.encryptString(admin.getPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                    admin.setConfirmedPassword(null);
                    admin.setCreatedDate(new Date());
                    baseResponse = adminRepository.createAdmin(admin);
                }else {
                    log.error("Username already exists");
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.USERNAME_EXISTS)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                            .errorType(Errors.ERROR_TYPE.USER.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                }
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

    @Override
    public BaseResponse adminLogin(Admin admin) {
        log.info("Inside Admin login");
        BaseResponse baseResponse = null;
        try {
            if (admin.getUserName() != null && admin.getPassword() != null) {
                List<Admin> adminList = adminRepository.getAdminByUsername(admin.getUserName());
                if (!adminList.isEmpty()) {
                    ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_ADMIN);
                    if (IstatoUtils.decryptString(adminList.get(0).getPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()).equals(admin.getPassword())) {
                        AdminLoginSuccessResponse adminLoginSuccessResponse = AdminLoginSuccessResponse.builder()
                                .isLoginSuccess(true)
                                .date(new Date())
                                .build();
                        baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, adminLoginSuccessResponse);
                    } else {
                        Collection<Errors> errors = new ArrayList<>();
                        errors.add(Errors.builder()
                                .message(ErrorCode.WRONG_PASSWORD)
                                .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                                .errorType(Errors.ERROR_TYPE.USER.toValue())
                                .level(Errors.SEVERITY.HIGH.name())
                                .build());
                        baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                    }
                } else {
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.ADMIN_NOT_EXISTS)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.SYSTEM.toCode()))
                            .errorType(Errors.ERROR_TYPE.SYSTEM.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                }
            } else {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NULL_REQUEST)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while login trying to login as admin {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateAdminPassword(AdminUpdatePasswordRequest adminUpdatePasswordRequest) {
     BaseResponse baseResponse=null;
       try {
           if (adminUpdatePasswordRequest != null && adminUpdatePasswordRequest.getPassword() != null &&adminUpdatePasswordRequest.getNewPassword()!= null) {
               log.info("Inside updateAdminPassword {}", adminUpdatePasswordRequest);
               List<Admin> adminList = adminRepository.getAdminByUsername(adminUpdatePasswordRequest.getUserName());
               Admin admin = adminList.get(0);
               if (admin != null) {
                   ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_ADMIN);
                   String decryptedPassword = IstatoUtils.decryptString(admin.getPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey());
                   if (adminUpdatePasswordRequest.getPassword().equals(decryptedPassword)) {
                       admin.setPassword(IstatoUtils.encryptString(adminUpdatePasswordRequest.getNewPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                       baseResponse = adminRepository.updateAdminPassword(admin);
                   }else{
                       log.error("Wrong password ");
                       Collection<Errors> errors = new ArrayList<>();
                       errors.add(Errors.builder()
                               .message(ErrorCode.WRONG_PASSWORD)
                               .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                               .errorType(Errors.ERROR_TYPE.USER.toValue())
                               .level(Errors.SEVERITY.HIGH.name())
                               .build());
                       baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                   }
               }else {
                   log.error("Wrong username");
                   Collection<Errors> errors = new ArrayList<>();
                   errors.add(Errors.builder()
                           .message(ErrorCode.WRONG_USERNAME)
                           .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                           .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                           .level(Errors.SEVERITY.HIGH.name())
                           .build());
                   baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
               }
           } else {
               Collection<Errors> errors = new ArrayList<>();
               errors.add(Errors.builder()
                       .message(ErrorCode.NULL_REQUEST)
                       .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                       .errorType(Errors.ERROR_TYPE.USER.toValue())
                       .level(Errors.SEVERITY.HIGH.name())
                       .build());
               baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
           }
       } catch (Exception e) {
           log.error("Exception occurred while updating admin password with probable cause {}", e.getMessage());
           throw new RuntimeException(e);
       }
       return baseResponse;
    }



}
