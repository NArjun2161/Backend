package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.*;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.repository.ExecutiveRepository;
import com.istato.admin.repository.RoleRepository;
import com.istato.admin.service.ExecutiveApplicationService;
import com.istato.admin.service.ExecutiveService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ExecutiveServiceImpl implements ExecutiveService {

    @Autowired
    private ExecutiveRepository executiveRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ApiConfigRepo apiConfigRepo;

    @Autowired
    ExecutiveApplicationService executiveApplicationService;

    @Override
    public BaseResponse createExecutive(Executive executive) throws Exception {
        BaseResponse baseResponse;
        ExecutiveApplication executiveApplication = new ExecutiveApplication();
        try {
            ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_EXECUTIVE_CONTROLLER);
            log.info("ApiConfig : {}", apiConfig);
            Executive executiveWithPan = executiveRepository.getExecutiveByPan(IstatoUtils.encryptString(executive.getPersonalDetails().getPanNumber(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
            Executive executiveWithUsername = executiveRepository.getExecutiveByUserName(IstatoUtils.encryptString(executive.getUserName(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
            if (executiveWithPan != null) {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message("Executive already exists..!")
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                return IstatoUtils.getBaseResponse(HttpStatus.BAD_REQUEST, errors);
            }
            if (executiveWithUsername != null) {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message("userName already exists try another userName..!")
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                return IstatoUtils.getBaseResponse(HttpStatus.BAD_REQUEST, errors);
            }
            if (checkIfRoleExists(executive.getRole())) {
                int randomIstNumber = 8900 + new Random().nextInt(100);
                String formattedIstNumber = String.format("%04d", randomIstNumber);
                String executiveId = "IST-" + formattedIstNumber;
                executive.setExecutiveId(executiveId);
                executiveApplication.setExecutiveId(executiveId);
                executive.setUserName(IstatoUtils.encryptString(executive.getUserName(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executive.setPassword(IstatoUtils.encryptString(executive.getPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executive.getPersonalDetails().setPanNumber(IstatoUtils.encryptString(executive.getPersonalDetails().getPanNumber(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executive.getPersonalDetails().setAadharNumber(IstatoUtils.encryptString(executive.getPersonalDetails().getAadharNumber(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executiveApplicationService.saveExecutiveApplication(executiveApplication);
                baseResponse = executiveRepository.save(executive);
            } else {
                // Role doesn't exist, handle accordingly
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message("Role not found")
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.VALIDATION.toCode()))
                        .errorType(Errors.ERROR_TYPE.VALIDATION.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(HttpStatus.BAD_REQUEST, errors);
            }
        } catch (Exception e) {
            // Exception during processing
            Collection<Errors> errors = new ArrayList<>();
            errors.add(Errors.builder()
                    .message(ErrorCode.EXCEPTION)
                    .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                    .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                    .level(Errors.SEVERITY.HIGH.name())
                    .build());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.EXPECTATION_FAILED, errors);
        }
        return baseResponse;
    }

    @Override
    public List<BaseResponse> getAllExecutives() {

        List<BaseResponse> baseResponse = null;
        log.info("inside getAllExecutives");
        try {
            baseResponse = executiveRepository.getAllExecutives();
            log.info("baseResponse in service {}", baseResponse);
        } catch (Exception e) {
            log.error("Exception occurred while getting product list {}", e.getMessage());
        }
        return baseResponse;


    }

    @Override
    public List<BaseResponse> getAllActiveExecutives(String isActive) {
        try {
            if (isActive.isEmpty()) {
                return executiveRepository.getAllExecutives(true);
            } else if (isActive.equals("false") || isActive.equals("true")) {
                Boolean isActiveBoolean = Boolean.parseBoolean(isActive);
                return executiveRepository.getAllExecutives(isActiveBoolean);
            } else {
                throw new RuntimeException("isActive can only be true or false");
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching active executives", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseResponse updateExeutive(Executive updatedExecutive) {
        BaseResponse baseResponse;
        ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_EXECUTIVE_CONTROLLER);
        try {
            if (updatedExecutive != null && updatedExecutive.getExecutiveId() != null) {
                updatedExecutive.setUserName(IstatoUtils.encryptString(updatedExecutive.getUserName(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                // Save the updated executive back to the database
                baseResponse = executiveRepository.updateExecutive(updatedExecutive);
            } else {
                // Handle the case where the executive with the given ID does not exist
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message("Executive not found with ID: " + updatedExecutive.getExecutiveId())
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.VALIDATION.toCode()))
                        .errorType(Errors.ERROR_TYPE.VALIDATION.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(HttpStatus.BAD_REQUEST, errors);
            }
        } catch (Exception e) {
            // Exception during processing
            Collection<Errors> errors = new ArrayList<>();
            errors.add(Errors.builder()
                    .message(ErrorCode.EXCEPTION)
                    .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                    .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                    .level(Errors.SEVERITY.HIGH.name())
                    .build());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.EXPECTATION_FAILED, errors);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse executiveLogin(Executive executive) {
        log.info("Inside executiveLogin");
        BaseResponse baseResponse = null;
        try {
            if (executive.getUserName() != null && executive.getPassword() != null) {
                ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_EXECUTIVE_CONTROLLER);
                Executive executiveFromDB = executiveRepository.getExecutiveByUserName(IstatoUtils.encryptString(executive.getUserName(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                if (executiveFromDB != null) {
                    if (IstatoUtils.decryptString(executiveFromDB.getPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()).equals(executive.getPassword())) {
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
                            .message(ErrorCode.EXECUTIVE_NOT_EXISTS)
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
            log.error("Exception occurred while login trying to login as executive {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;

    }

    @Override
    public BaseResponse executiveResetPassword(Executive executive) {
        BaseResponse baseResponse = null;
        try {
            if (executive.getUserName() != null) {
                ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.CREATE_EXECUTIVE_CONTROLLER);
                Executive executiveFromDb = executiveRepository.getExecutiveByUserName(IstatoUtils.encryptString(executive.getUserName(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                if (executiveFromDb != null) {
                    SendOtpResponse sendOtpResponse = sendOtpResponseMethod(IstatoUtils.decryptString(executiveFromDb.getPersonalDetails().getContactNumber(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                    baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, sendOtpResponse);
                    executiveRepository.saveOtpLogs(sendOtpResponse);
                } else {
                    log.error("wrong username");
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.USERNAME_DOSE_NOT_EXISTS)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                            .errorType(Errors.ERROR_TYPE.USER.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                }
            } else {
                log.error("Null request");
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NO_DATA_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        BaseResponse baseResponse = null;
        try {
            if (verifyOtpRequest != null) {
                SendOtpResponse sendOtpResponseFromDb = executiveRepository.getSmsResponse(verifyOtpRequest.getMobileNumber());
                if (verifyOtpRequest.getOtp().equals(sendOtpResponseFromDb.getOtp())) {
                    VerifyOtpResponse verifyOtpResponse = VerifyOtpResponse.builder()
                            .isOtpVerified(true)
                            .verifiedDate(new Date())
                            .mobileNumber(sendOtpResponseFromDb.getMobileNumber())
                            .build();
                    baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, verifyOtpResponse);
                } else {
                    log.error("Wrong OTP ");
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.WRONG_OTP)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                            .errorType(Errors.ERROR_TYPE.USER.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                }
            } else {
                log.error("Null request");
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NO_DATA_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public Executive getExecutiveById(String executiveId) {
        Executive executive = null;
        try{
            if (executiveId != null){
                executive = executiveRepository.getExecutiveById(executiveId);
            }else {
                log.error("ExecutiveId is null");
            }

        } catch (Exception e) {
            log.error("Exception occurred while calling getExecutiveById with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return executive;
    }

    private boolean checkIfRoleExists(String roleName) {
        log.info("inside checkIfRoleExists");
        try {
            return roleRepository.existsByRoleName(roleName);
        } catch (Exception e) {
            log.error("Exception occurred {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private SendOtpResponse sendOtpResponseMethod(String mobileNumber) {
        SendOtpResponse sendOtpResponse = null;
        try {
            if (mobileNumber != null) {
                String otp = generateOTP(6);
                sendOtpResponse = SendOtpResponse.builder()
                        .isOtpSent(true)
                        .mobileNumber(mobileNumber)
                        .sentDate(new Date())
                        .otp(otp)
                        .message("Message sent successfully")
                        .build();
            } else {
                sendOtpResponse = SendOtpResponse.builder()
                        .isOtpSent(false)
                        .mobileNumber(mobileNumber)
                        .sentDate(new Date())
                        .otp(null)
                        .message("Message sent failed")
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sendOtpResponse;
    }

    public static String generateOTP(int length) {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(numbers.length());
            otp.append(numbers.charAt(index));
        }
        return otp.toString();
    }
}
