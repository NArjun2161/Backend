package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetDetails {
    @JsonProperty("assetName")
    private String assetName;

    @JsonProperty("assetID")
    private String assetID;

    @JsonProperty("assetType")
    private String assetType;

    @JsonProperty("assetValue")
    private double assetValue;
}
