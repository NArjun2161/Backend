package com.istato.admin.repository;


import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Project;

public interface ProjectRepository {
    BaseResponse saveProject(Project project);
}
