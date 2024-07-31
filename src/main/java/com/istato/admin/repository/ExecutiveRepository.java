package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Executive;

import java.util.List;

public interface ExecutiveRepository {
    BaseResponse save(Executive executive);

    List<BaseResponse> getAllExecutives();

    List<BaseResponse> getAllExecutives(boolean b);

    BaseResponse findByExecutiveId(String executiveId);

    BaseResponse updateExecutive(Executive updatedExecutive);

    Executive getExecutiveByPan(String encryptedPanNumber);

    Executive getExecutiveByUserName(String encryptedUserName);

}
