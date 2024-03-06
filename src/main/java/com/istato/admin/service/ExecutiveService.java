package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.controller.ExecutiveContoller;
import com.istato.admin.model.Executive;

public interface ExecutiveService {

    BaseResponse createExecutive(Executive executive);
}
