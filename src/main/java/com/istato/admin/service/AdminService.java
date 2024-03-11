package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Admin;
import com.istato.admin.model.AdminUpdatePasswordRequest;

public interface AdminService {
    BaseResponse createAdmin(Admin admin);

    BaseResponse adminLogin(Admin admin);


    BaseResponse updateAdminPassword(AdminUpdatePasswordRequest adminUpdatePasswordRequest);
}
