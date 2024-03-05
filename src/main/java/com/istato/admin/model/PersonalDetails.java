package com.istato.admin.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalDetails {
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private String dob;
    private String gender;
    private String panNumber;
    private String aadharNumber;
    private boolean isIntern;
    private boolean isActive;
}
