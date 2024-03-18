package com.istato.admin.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PricingPeriod {

    @JsonProperty("iMonthlyPackage")
    private int monthlyPrice;

    @JsonProperty("iQuarterlyPackage")
    private int quarterlyPrice;

    @JsonProperty("iSixMonthPackage")
    private int sixMonthPrice;

    @JsonProperty("iAnnualPackage")
    private int annualPrice;

}
