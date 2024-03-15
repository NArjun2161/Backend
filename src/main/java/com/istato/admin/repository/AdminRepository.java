package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Admin;
import com.istato.admin.model.PlanDetails;

import java.util.List;

public interface AdminRepository {
    BaseResponse createAdmin(Admin admin);

    List<Admin> getAdminByUsername(String userName);


    BaseResponse updateAdminPassword(Admin admin);

}
