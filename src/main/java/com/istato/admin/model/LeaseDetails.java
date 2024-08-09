package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaseDetails {
    @JsonProperty("dRent")
    private double rent;

    @JsonProperty("dSecurityDeposit")
    private double securityDeposit;

    @JsonProperty("iLeaseTerm")
    private int leaseTerm;

    @JsonProperty("bPetPolicyAllowed")
    private boolean petPolicyAllowed;

    @JsonProperty("dtAvailability")
    private Date availability;
}
