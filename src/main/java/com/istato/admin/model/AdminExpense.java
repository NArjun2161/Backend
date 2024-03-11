package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminExpense {
    @JsonProperty("sAdminName")
    private String name;

    @JsonProperty("dAdminTaxes")
    private double taxes;

    @JsonProperty("dAdminRent")
    private double rent;

    @JsonProperty("dAdminOtherExpenses")
    private double otherExpenses;

    @JsonProperty("iAdminDate")
    private Date date;

}