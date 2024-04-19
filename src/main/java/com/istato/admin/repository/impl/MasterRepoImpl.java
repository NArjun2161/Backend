package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.MasterUploadEventLog;
import com.istato.admin.model.MastersData;
import com.istato.admin.repository.MasterRepo;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class MasterRepoImpl implements MasterRepo {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaseResponse uploadMaster(MastersData mastersData) {
        try {
            return IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(mastersData));
        } catch (Exception e) {
            log.error("Exception occurred while performing DB insert operation with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveMasterUploadEventLog(MasterUploadEventLog masterUploadEventLog) {
        log.info("Inside save masterUploadEventLog");
        mongoTemplate.save(masterUploadEventLog);
    }

    @Override
    public MasterUploadEventLog getMasterUploadEventLog(String masterName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("masterName").is(masterName).and("isMasterUploaded").is(true));
        return mongoTemplate.findOne(query, MasterUploadEventLog.class);
    }
}
