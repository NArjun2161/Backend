package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdditionalInformation {
    @JsonProperty("sPhotos")
    private String photos;

    @JsonProperty("sFloorPlans")
    private String floorPlans;

    @JsonProperty("sVideoWalkthrough")
    private String videoWalkthrough;

    @JsonProperty("sListingAgentContact")
    private String listingAgentContact;
}
