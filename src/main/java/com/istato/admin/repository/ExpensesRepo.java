package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Expenses;

public interface ExpensesRepo {
    BaseResponse saveExpenses(Expenses expenses);

    BaseResponse update(Expenses expenses);
}

