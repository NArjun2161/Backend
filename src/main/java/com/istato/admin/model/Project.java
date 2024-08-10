package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
public class Project {

    @JsonProperty("sProjectId")
    private String projectId;

    @JsonProperty("sProjectName")
    private String projectName;

    @JsonProperty("lProjectImagesBase64")
    private List<String> projectImagesBase64;

    @JsonProperty("lProjectLayoutBase64")
    private List<String> projectLayoutBase64;

    @JsonProperty("lVideoUrl")
    private List<String> videoUrl;

    @JsonProperty("lOtherDocuments")
    private List<String> otherDocuments;

    @JsonProperty("sProjectAddress")
    private String projectAddress;

    @JsonProperty("sProjectStatus")
    private String projectStatus;

}
