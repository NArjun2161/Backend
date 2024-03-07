package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Admin;
import org.springframework.validation.BindingResult;

public interface AdminService {
    BaseResponse createAdmin(Admin admin);
}
