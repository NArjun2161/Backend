package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.ErrorCode;
import com.istato.admin.baseclasses.Errors;
import com.istato.admin.model.Role;
import com.istato.admin.repository.RoleRepository;
import com.istato.admin.service.RoleService;
import com.istato.admin.utils.IstatoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseResponse createRole(Role role) {
        BaseResponse baseResponse = null;
        try {
            role.setRoleId(IstatoUtils.generateRoleId(role.getRoleName(), "1000"));
            baseResponse = roleRepository.save(role);
        } catch (Exception e) {
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
    public BaseResponse updateRole(Role role) {
        BaseResponse baseResponse = null;
        try {
            if (role.getRoleId() == null) {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NULL_ROLE_ID)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                                .level(Errors.SEVERITY.HIGH.name())
                        .build());
                return IstatoUtils.getBaseResponse(HttpStatus.BAD_REQUEST, errors);
            }
            baseResponse = roleRepository.update(role);
        } catch (Exception e) {
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
}
