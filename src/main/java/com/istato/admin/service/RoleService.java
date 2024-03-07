package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Role;

import java.util.List;

public interface RoleService {
    BaseResponse createRole(Role role);

    List<Role> getAllRoles();

    List<Role> getAllRolesByStatus(String isActive);
}
