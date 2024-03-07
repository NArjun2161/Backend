package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointReffer;
import com.istato.admin.model.Expenses;
import com.istato.admin.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(EndPointReffer.EXPENSE_API)
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping(EndPointReffer.ADD_EXPENSE)
    public BaseResponse addExpenses(@RequestBody Expenses expenses) {
        log.info(EndPointReffer.EXPENSE_API + EndPointReffer.ADD_EXPENSE + Constants.CONTROLLER_STARTED);
        return expenseService.addExpense(expenses);
    }
}
