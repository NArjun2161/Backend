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
public class ExecutiveApplication {

    @JsonProperty("sExecutiveId")
    private String executiveId;

    @JsonProperty("oExecutive")
    private Executive executive;

}
