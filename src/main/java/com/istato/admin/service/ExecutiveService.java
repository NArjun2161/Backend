package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Executive;
import com.istato.admin.model.VerifyOtpRequest;

import java.util.List;

public interface ExecutiveService {

    BaseResponse createExecutive(Executive executive) throws Exception;

    List<BaseResponse> getAllExecutives();

    List<BaseResponse> getAllActiveExecutives(String isActive);

    BaseResponse updateExeutive(Executive updatedExecutive);

    BaseResponse executiveLogin(Executive executive);

    BaseResponse executiveResetPassword(Executive executive);

    BaseResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);

    Executive getExecutiveById(String executiveId);
}
