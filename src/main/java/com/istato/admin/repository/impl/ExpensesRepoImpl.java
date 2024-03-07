package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Expenses;
import com.istato.admin.repository.ExpensesRepo;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ExpensesRepoImpl implements ExpensesRepo {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public BaseResponse saveExpenses(Expenses expenses) {
        try {
            log.info("Inside saveExpenses");
            return IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(expenses));
        } catch (Exception e) {
            log.error("Exception occurred while saving Expenses to DB {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
