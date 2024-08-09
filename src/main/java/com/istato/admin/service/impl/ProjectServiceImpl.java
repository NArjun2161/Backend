package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.model.Project;
import com.istato.admin.repository.ApiConfigRepo;
import com.istato.admin.repository.ProjectRepository;
import com.istato.admin.service.ProjectService;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ApiConfigRepo apiConfigRepo;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public BaseResponse saveProject(Project project) {

        BaseResponse baseResponse = null;
        String projectId = FieldSeprators.BLANK.toFaceValue();
        try {

            if (project != null) {
                ApiConfig apiConfig = apiConfigRepo.getApiConfig(EndPointRefer.ONBOARD_NEW_PROJECT);
                log.info("ApiConfig {}", apiConfig);
                projectId = IstatoUtils.generateProjectId(project.getProjectName(), apiConfig.getAdditionalProperties().getRandomNumberBound());
                project.setProjectId(projectId);
                baseResponse = projectRepository.saveProject(project);
            } else {
                log.error(ErrorCode.NO_DATA_FOUND);
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NO_DATA_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(HttpStatus.EXPECTATION_FAILED, errors);
            }

        } catch (Exception e) {
            Collection<Errors> errors = new ArrayList<>();
            errors.add(Errors.builder()
                    .message(ErrorCode.EXCEPTION)
                    .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                    .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                    .level(Errors.SEVERITY.HIGH.name())
                    .build());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.EXPECTATION_FAILED, errors);
        }
        return baseResponse;
    }
}
