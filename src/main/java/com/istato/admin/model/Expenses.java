package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expenses {

    @JsonProperty("oAdminExpense")
    private AdminExpense adminExpense;

    @JsonProperty("oExecutiveExpense")
    private ExecutiveExpense executiveExpense;

    @JsonProperty("oOtherExpenses")
    private OtherExpenses otherExpenses;
}
