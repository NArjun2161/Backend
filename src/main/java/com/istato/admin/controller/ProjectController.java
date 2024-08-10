package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.model.Inventory;
import com.istato.admin.model.Project;
import com.istato.admin.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping(EndPointRefer.ONBOARD_NEW_PROJECT)
    public BaseResponse saveProject(@RequestBody Project project) {
        log.info(EndPointRefer.ONBOARD_NEW_PROJECT + Constants.CONTROLLER_STARTED);
        return projectService.saveProject(project);
    }

    @GetMapping(EndPointRefer.GET_PROJECT_BY_ID + "/{projectId}")
    public Project getProjectById(@RequestParam String projectId) {
        log.info(EndPointRefer.GET_PROJECT_BY_ID + Constants.CONTROLLER_STARTED);
        return projectService.getProjectById(projectId);
    }

    @PutMapping(EndPointRefer.UPDATE_PROJECT)
    public BaseResponse updateProject(@RequestBody Project project) {
        log.info(EndPointRefer.UPDATE_PROJECT + Constants.CONTROLLER_STARTED);
        return projectService.updateProject(project);
    }

    @PostMapping(EndPointRefer.ADD_INVENTORY)
    public BaseResponse saveProject(@RequestBody Inventory inventory) {
        log.info(EndPointRefer.ADD_INVENTORY + Constants.CONTROLLER_STARTED);
        return projectService.addInventory(inventory);
    }

    @PutMapping(EndPointRefer.UPDATE_INVENTORY)
    public BaseResponse updateInventory(@RequestBody Inventory inventory) {
        log.info(EndPointRefer.UPDATE_INVENTORY + Constants.CONTROLLER_STARTED);
        return projectService.updateInventory(inventory);
    }
}
