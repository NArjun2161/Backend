package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Role;

import java.util.List;

public interface RoleRepository {

    BaseResponse save(Role role);

    boolean existsByRoleName(String roleName);

    BaseResponse update(Role role);

    List<Role> getAllRoles();

    List<String> getAllRolesByStatus(Boolean isActive);
    Role getRoleById(String roleId);
}
