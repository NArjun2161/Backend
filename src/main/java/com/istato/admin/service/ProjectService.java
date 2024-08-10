package com.istato.admin.service;


import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Inventory;
import com.istato.admin.model.Project;

public interface ProjectService {
    BaseResponse saveProject(Project project);

    BaseResponse addInventory(Inventory inventory);

    BaseResponse getProjectById(String projectId);

    BaseResponse updateProject(Project project);
}
