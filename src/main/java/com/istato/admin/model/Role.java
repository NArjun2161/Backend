package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "role")
public class Role {
    @Id
    private String roleId;
    @JsonProperty("roleName")
    private String roleName;
    @JsonProperty("roleDescription")
    private String roleDescription;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("authorizedActions")
    private List<String> authorizedActions;
}
