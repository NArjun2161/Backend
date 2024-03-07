package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Executive;

import java.util.List;

public interface ExecutiveRepository {
    BaseResponse save(Executive executive);

    List<BaseResponse> getAllExecutives();

    List<Executive> getAllExecutives(boolean b);
}
