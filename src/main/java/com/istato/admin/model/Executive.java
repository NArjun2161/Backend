package com.istato.admin.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "executives")
public class Executive {
    private PersonalDetails personalDetails;
    private FinanceDetails financeDetails;
    private AssetDetails assetDetails;
    private String Role;
    private String username;
    private String password;
}
