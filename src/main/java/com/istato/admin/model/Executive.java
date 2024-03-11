package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;

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

    @Pattern(regexp = "^[a-z]+$", message = "userName must contain only lowercase letters")
    @JsonProperty("sUsername")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters with at least one lowercase letter, one digit, and one symbol")
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
