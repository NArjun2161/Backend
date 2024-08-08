package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.ErrorCode;
import com.istato.admin.baseclasses.Errors;
import com.istato.admin.model.Role;
import com.istato.admin.repository.RoleRepository;
import com.istato.admin.service.RoleService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
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

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = null;
        log.info("Inside getAllRoles ");
        try {
            roles = roleRepository.getAllRoles();
        } catch (Exception e) {
            log.error("Exception occurred while getting all roles {}", e.getMessage());
        }
        return roles;
    }

    @Override
    public List<Role> getAllRolesByStatus(String isActive) {
        List<Role> roles = null;
        log.info("Inside getAllRolesByStatus ");
        try {
            roles = roleRepository.getAllRolesByStatus(Boolean.parseBoolean(isActive));
        } catch (Exception e) {
            log.error("Exception occurred while getting all roles by status {} ", e.getMessage());
        }
        return roles;
    }

    @Override
    public Role getRoleById(String roleId) {
        Role role = null;
        try {
            if (roleId != null) {
                role = roleRepository.getRoleById(roleId);
            } else {
                log.error("RoleId null in request");
            }
        } catch (Exception e) {
            log.error("Exception occurred while getting role by id {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return role;
    }
}
