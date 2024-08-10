package com.istato.admin.repository;


import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Inventory;
import com.istato.admin.model.Project;

public interface ProjectRepository {
    BaseResponse saveProject(Project project);

    Project getProjectById(String projectId);

    BaseResponse addInventory(Inventory inventory);

    BaseResponse updateProject(Project project);

    Inventory getInventoryByGetPlotNoAndProjectId(String projectId, String plotNo);

    BaseResponse updateInventory(Inventory inventory);
}
