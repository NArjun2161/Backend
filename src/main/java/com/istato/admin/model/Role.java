package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "role")
public class Role {
    @JsonProperty("sRoleId")
    private String roleId;

    @JsonProperty("sRoleName")
    private String roleName;

    @JsonProperty("sRoleDescription")
    private String roleDescription;

    @JsonProperty("bActive")
    private Boolean active;

    @JsonProperty("lAuthorizedActions")
    private List<String> authorizedActions;
}
