package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "executives")
public class Executive {
    @JsonProperty("personalDetails")
    private PersonalDetails personalDetails;

    @JsonProperty("financeDetails")
    private FinanceDetails financeDetails;

    @JsonProperty("assetDetails")
    private AssetDetails assetDetails;

    @JsonProperty("Role")
    private String role;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
