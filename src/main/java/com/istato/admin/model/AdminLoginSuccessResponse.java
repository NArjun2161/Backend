package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginSuccessResponse {

    @JsonProperty("bIsLoginSuccess")
    private boolean isLoginSuccess;

    @JsonProperty("dLoginDate")
    private Date date;
}
