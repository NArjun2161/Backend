package com.istato.admin.repository.impl;

import ch.qos.logback.classic.Logger;
import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Expenses;
import com.istato.admin.repository.ExpensesRepo;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Override
    public BaseResponse update(Expenses expenses) {
        BaseResponse baseResponse = null;
        try {
            log.info("Inside update");
            Query query = new Query();
            query = query.addCriteria(Criteria.where("_id").is(expenses.getId()));

            Update update = new Update();
            update.set("adminExpense", expenses.getAdminExpense());
            update.set("executiveExpense", expenses.getExecutiveExpense());
            update.set("otherExpenses", expenses.getOtherExpenses());


            baseResponse = IstatoUtils.getBaseResponse(
                    HttpStatus.OK,
                    mongoTemplate.upsert(query, update, Expenses.class));
        } catch (Exception e) {
            log.error("Exception occurred while updating expenses{}", e.getMessage());
        }
        return baseResponse;

    }
}
