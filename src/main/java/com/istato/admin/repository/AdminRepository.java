package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Admin;

public interface AdminRepository {
    BaseResponse createAdmin(Admin admin);
}
