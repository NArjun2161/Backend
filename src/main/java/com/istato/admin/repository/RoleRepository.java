package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Role;

public interface RoleRepository {

    BaseResponse save(Role role);
}
