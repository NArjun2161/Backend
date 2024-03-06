package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.ExecutiveApplication;

public interface ExecutiveApplicationService {
    BaseResponse saveExecutiveApplication(ExecutiveApplication executiveApplication);
}
