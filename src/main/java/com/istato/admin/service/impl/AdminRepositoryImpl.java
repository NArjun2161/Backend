package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Admin;
import com.istato.admin.repository.AdminRepository;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public BaseResponse createAdmin(Admin admin) {
        BaseResponse baseResponse = null;
        log.info("Inside createAdmin repository");
        try {
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(admin));
        } catch (Exception e) {
            log.error("Exception occurred while saving admin with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }
}
