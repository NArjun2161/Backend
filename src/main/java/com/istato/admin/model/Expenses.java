package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expenses {

    @JsonProperty("sId")
    private String id;

    @JsonProperty("oAdminExpense")
    private AdminExpense adminExpense;

    @JsonProperty("oExecutiveExpense")
    private ExecutiveExpense executiveExpense;

    @JsonProperty("oOtherExpenses")
    private OtherExpenses otherExpenses;

}
