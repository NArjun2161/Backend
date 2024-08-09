package com.istato.admin.service;


import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Project;

public interface ProjectService {
    BaseResponse saveProject(Project project);
}
