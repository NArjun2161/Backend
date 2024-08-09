package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellDetails {
    @JsonProperty("dPrice")
    private double price;
    @JsonProperty("dTaxes")
    private double taxes;
    @JsonProperty("dtSellAvailability")
    private Date sellAvailability;
}
