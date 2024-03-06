package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.baseclasses.ErrorCode;
import com.istato.admin.baseclasses.Errors;
import com.istato.admin.controller.ExecutiveContoller;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.model.Executive;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.repository.ExecutiveRepository;
import com.istato.admin.repository.RoleRepository;
import com.istato.admin.service.ExecutiveService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
@Slf4j
public class ExecutiveServiceImpl implements ExecutiveService {

    @Autowired
    private ExecutiveRepository executiveRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ApiConfigRepo apiConfigRepo;

    @Override
    public BaseResponse createExecutive(Executive executive) {
        BaseResponse baseResponse = null;

        try {
            if (checkIfRoleExists(executive.getRole())) {
                // Additional validation or business logic if needed
                ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointReffer.CREATE_EXECUTIVE_CONTROLLER);
                int randomIstNumber = 8900 + new Random().nextInt(100);
                String formattedIstNumber = String.format("%04d", randomIstNumber);
                String executiveId = "IST-" + formattedIstNumber;
                executive.setExecutiveId(executiveId);
                executive.setUsername(IstatoUtils.encryptString(executive.getUsername(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executive.setPassword(IstatoUtils.encryptString(executive.getPassword(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executive.getPersonalDetails().setPanNumber(IstatoUtils.encryptString(executive.getPersonalDetails().getPanNumber(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executive.getPersonalDetails().setAadharNumber(IstatoUtils.encryptString(executive.getPersonalDetails().getAadharNumber(), apiConfig.getEncryptionKey(), apiConfig.getIvKey()));
                executiveRepository.save(executive);

                baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, "Executive created successfully.");
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

    private boolean checkIfRoleExists(String roleName) {
        return roleRepository.existsByRoleName(roleName);
    }
}
