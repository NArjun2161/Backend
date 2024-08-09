package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.model.Project;
import com.istato.admin.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping(EndPointRefer.ONBOARD_NEW_PROJECT)
    public BaseResponse saveProject(@RequestBody Project project){
        log.info(EndPointRefer.ONBOARD_NEW_PROJECT + Constants.CONTROLLER_STARTED);
        return projectService.saveProject(project);
    }
}
