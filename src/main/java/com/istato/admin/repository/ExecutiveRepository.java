package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Executive;

public interface ExecutiveRepository {
    BaseResponse save(Executive executive);
}
