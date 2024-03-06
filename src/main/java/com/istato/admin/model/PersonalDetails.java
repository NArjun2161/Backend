package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalDetails {

    @JsonProperty("sName")
    private String name;

    @JsonProperty("sEmail")
    private String email;

    @JsonProperty("sContactNumber")
    private String contactNumber;

    @JsonProperty("sAddress")
    private String address;

    @JsonProperty("sDob")
    private String dob;

    @JsonProperty("sGender")
    private String gender;

    @JsonProperty("sPanNumber")
    private String panNumber;

    @JsonProperty("sAadharNumber")
    private String aadharNumber;

}
