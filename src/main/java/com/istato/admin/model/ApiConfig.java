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
public class ApiConfig {

    @JsonProperty("sApiName")
    private String apiName;

    @JsonProperty("sEncryptionKey")
    private String encryptionKey;

    @JsonProperty("sIvKey")
    private String ivKey;

    @JsonProperty("bIsActive")
    private boolean isActive;

    @JsonProperty("sSandboxNo")
    private String sandboxNo;

    @JsonProperty("sApiUrl")
    private String apiUrl;

    @JsonProperty("sUsername")
    private String userName;

    @JsonProperty("sPassword")
    private String password;

    @JsonProperty("sApiKey")
    private String apiKey;


}
