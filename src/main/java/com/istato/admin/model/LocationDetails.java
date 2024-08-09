package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDetails {
    @JsonProperty("sAddress")
    private String address;

    @JsonProperty("sAreaLocality")
    private String areaLocality;

    @JsonProperty("sCity")
    private String city;

    @JsonProperty("sAccessibility")
    private String accessibility;

    @JsonProperty("sNeighborhoodDescription")
    private String neighborhoodDescription;
}