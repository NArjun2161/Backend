package com.istato.admin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceDetails {
    private double salary;
    private double bonus;
    private double incentive;

    private boolean isOnRole;
}
