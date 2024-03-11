package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.model.Admin;
import com.istato.admin.repository.AdminRepository;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Admin> getAdminByUsername(String userName) {
        List<Admin> admin = null;
        log.info("Inside getAdminByUsername repository");
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.USERNAME).is(userName));
            admin = mongoTemplate.find(query, Admin.class);
        } catch (Exception e) {
            log.error("Exception occurred while getAdminByUsername with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return admin;
    }

    @Override
    public BaseResponse updateAdminPassword(Admin admin) {
        BaseResponse baseResponse = null;
        Query query = new Query();
        Update update = new Update();
        query= query.addCriteria(Criteria.where(Constants.USERNAME).is(admin.getUserName()));
        update.set(Constants.PASSWORD, admin.getPassword());
        baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.updateFirst(query, update, Admin.class));
        return baseResponse;
    }

}
