package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetDetails {
    @JsonProperty("sAssetName")
    private String assetName;

    @JsonProperty("sAssetID")
    private String assetID;

    @JsonProperty("sAssetType")
    private String assetType;

    @JsonProperty("dAssetValue")
    private double assetValue;
}
