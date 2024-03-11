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
public class AdminUpdatePasswordRequest {

    @JsonProperty("sUserName")
    private String userName;

    @JsonProperty("sPassword")
    private String password;

    @JsonProperty("sNewPassword")
    private String newPassword;
}
