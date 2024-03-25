package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDetails {

    @JsonProperty("sPlanType")
    private String planType;

    @JsonProperty("sPropertyType")
    private String propertyType;

    @JsonProperty("sPlanId")
    private String planId;

    @JsonProperty("oPricingPeriod")
    private PricingPeriod pricingPeriod;

    @JsonProperty("bIsActive")
    private boolean isActive;

    @JsonProperty("dCreatedDate")
    private Date createdDate;

    @JsonProperty("dUpdatedDate")
    private Date updatedDate;

    @JsonProperty("isError")
    private boolean isError;
}
