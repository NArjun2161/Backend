package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Expenses;

public interface ExpenseService {
    BaseResponse addExpense(Expenses expenses);

    BaseResponse updateExpenses(Expenses expenses);
}
