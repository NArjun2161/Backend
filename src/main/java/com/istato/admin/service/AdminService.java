package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Admin;

public interface AdminService {
    BaseResponse createAdmin(Admin admin);

    BaseResponse adminLogin(Admin admin);
}
