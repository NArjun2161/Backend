package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.Expenses;
import com.istato.admin.repository.ExpensesRepo;
import com.istato.admin.service.ExpenseService;

import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpensesRepo expensesRepo;


    @Override
    public BaseResponse addExpense(Expenses expenses) {
        BaseResponse baseResponse = null;
        try {
            if (expenses != null) {
                log.info("Inside addExpense");
                expenses.getAdminExpense().setDate(new Date());
                expenses.getExecutiveExpense().setDate(new Date());
                expenses.getOtherExpenses().setDate(new Date());


                baseResponse = expensesRepo.saveExpenses(expenses);
            } else {
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NULL_REQUEST)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                log.error("Null request");
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("exception occurred while while adding expense:" + e.getMessage());
            throw new RuntimeException();
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateExpenses(Expenses expenses) {
        BaseResponse baseResponse = null;
        try {
            if (expenses != null && expenses.getId() != null) {
                baseResponse = expensesRepo.update(expenses);
            } else {
                log.error("Expense should not be null");
            }
        } catch (Exception e) {
            Collection<Errors> errors = new ArrayList<>();
            errors.add(Errors.builder()
                    .message(ErrorCode.EXCEPTION)
                    .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                    .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                    .level(Errors.SEVERITY.HIGH.name())
                    .build());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.EXPECTATION_FAILED, errors);
        }
        return baseResponse;
    }
}
