package com.istato.admin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetDetails {

    private String assetName;
    private String assetID;
    private String assetType;
    private double assetValue;
}
