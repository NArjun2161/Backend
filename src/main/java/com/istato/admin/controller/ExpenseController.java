package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.Expenses;
import com.istato.admin.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(EndPointRefer.EXPENSE_API)
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping(EndPointRefer.ADD_EXPENSE)
    public BaseResponse addExpenses(@RequestBody Expenses expenses) {
        log.info(EndPointRefer.EXPENSE_API + EndPointRefer.ADD_EXPENSE + Constants.CONTROLLER_STARTED);
        return expenseService.addExpense(expenses);
    }

    @PutMapping(value = EndPointReffer.UPDATE_EXPENSES)
    public BaseResponse updateExpenses(@RequestBody Expenses expenses) {
        log.info(EndPointReffer.UPDATE_EXPENSES + Constants.CONTROLLER_STARTED);
        return expenseService.updateExpenses(expenses);
    }
}
