package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {

    @JsonProperty("sUserName")
    @Pattern(regexp = "^[a-z]+$", message = "userName must contain only lowercase letters")
    private String userName;

    @JsonProperty("sPassword")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters with at least one lowercase letter, one digit, and one symbol")
    private String password;

    @JsonProperty("sConfirmedPassword")
    private String confirmedPassword;

    @JsonProperty("dCreatedDate")
    private Date createdDate;

}
