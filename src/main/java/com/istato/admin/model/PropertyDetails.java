package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDetails {
    @JsonProperty("sType")
    private String type;

    @JsonProperty("dSize")
    private double size;

    @JsonProperty("iBedrooms")
    private int bedrooms;

    @JsonProperty("iBathrooms")
    private int bathrooms;

    @JsonProperty("iAge")
    private int age;

    @JsonProperty("sCondition")
    private String condition;

    @JsonProperty("lAmenities")
    private List<String> amenities;

    @JsonProperty("sDescription")
    private String description;
}
