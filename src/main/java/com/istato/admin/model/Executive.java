package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "executives")
public class Executive {

    @JsonProperty("sExecutiveId")
    private String executiveId;

    @JsonProperty("sRole")
    private String role;

    @JsonProperty("sUsername")
    private String username;

    @JsonProperty("sPassword")
    private String password;

    @JsonProperty("bIsOnRole")
    private boolean isOnRole;

    @JsonProperty("bIsIntern")
    private boolean isIntern;

    @JsonProperty("bIsActive")
    private boolean isActive;

    @JsonProperty("oPersonalDetails")
    private PersonalDetails personalDetails;

    @JsonProperty("oFinanceDetails")
    private FinanceDetails financeDetails;

    @JsonProperty("oAssetDetails")
    private AssetDetails assetDetails;


}
