package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceDetails {
    @JsonProperty("salary")
    private double salary;

    @JsonProperty("bonus")
    private double bonus;

    @JsonProperty("incentive")
    private double incentive;

    @JsonProperty("isOnRole")
    private boolean isOnRole;
}
