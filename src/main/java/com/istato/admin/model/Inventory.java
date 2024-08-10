package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Inventory {

    @JsonProperty("sProjectId")
    private String projectId;

    @JsonProperty("sPlotNo")
    private String plotNo;

    @JsonProperty("sPlotSize")
    private String plotSize;

    @JsonProperty("sCost")
    private String cost;

    @JsonProperty("sPlotStatus")
    private String plotStatus;
}
