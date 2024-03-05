package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalDetails {
    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("contactNumber")
    private String contactNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("panNumber")
    private String panNumber;

    @JsonProperty("aadharNumber")
    private String aadharNumber;

    @JsonProperty("isIntern")
    private boolean isIntern;

    @JsonProperty("isActive")
    private boolean isActive;
}
