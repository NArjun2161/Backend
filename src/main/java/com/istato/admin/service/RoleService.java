package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Role;

public interface RoleService {
    BaseResponse createRole(Role role);

    BaseResponse updateRole(Role role);
}
