package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceDetails {
    @JsonProperty("dSalary")
    private double salary;

    @JsonProperty("dBonus")
    private double bonus;

    @JsonProperty("dIncentive")
    private double incentive;

    @JsonProperty("bIsOnRole")
    private boolean isOnRole;
}
