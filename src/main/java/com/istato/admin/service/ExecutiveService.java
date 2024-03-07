package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Executive;

import java.util.List;

public interface ExecutiveService {

    BaseResponse createExecutive(Executive executive);

    List<BaseResponse> getAllExecutives();

    List<Executive> getAllActiveExecutives(String isActive);
}
