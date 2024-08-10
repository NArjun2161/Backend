package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.ApiConfig;
import com.istato.admin.model.Inventory;
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
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
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

    @Override
    public BaseResponse addInventory(Inventory inventory) {
        log.info("Inside ProjectServiceImpl.addInventory()");
        BaseResponse baseResponse = null;
        try {
            if (inventory != null) {
                log.info("Project Id: {}", inventory.getProjectId());
                Project project = projectRepository.getProjectById(inventory.getProjectId());
                if (project != null) {
                    Inventory inventoryFromDb = projectRepository.getInventoryByGetPlotNoAndProjectId(inventory.getProjectId(), inventory.getPlotNo());
                    if (inventoryFromDb == null) {
                        if (project.getProjectStatus().equalsIgnoreCase(Constants.AVAILABLE)) {
                            baseResponse = projectRepository.addInventory(inventory);
                        } else {
                            log.error(ErrorCode.PROJECT_NOT_AVAILABLE);
                            Collection<Errors> errors = new ArrayList<>();
                            errors.add(Errors.builder()
                                    .message(ErrorCode.PROJECT_NOT_AVAILABLE)
                                    .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                                    .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                                    .level(Errors.SEVERITY.HIGH.name())
                                    .build());
                            baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                        }
                    } else {
                        log.error(ErrorCode.PLOT_ALREADY_EXISTS);
                        Collection<Errors> errors = new ArrayList<>();
                        errors.add(Errors.builder()
                                .message(ErrorCode.PLOT_ALREADY_EXISTS)
                                .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                                .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                                .level(Errors.SEVERITY.HIGH.name())
                                .build());
                        baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                    }
                } else {
                    log.error(ErrorCode.PROJECT_DOSENT_EXIST);
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.PROJECT_DOSENT_EXIST)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                            .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                }
            } else {
                log.error(ErrorCode.NO_DATA_FOUND);
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NO_DATA_FOUND)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while adding inventory against project created with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public Project getProjectById(String projectId) {
        log.info("Inside ProjectServiceImpl.getProjectById()");
        Project project = null;
        try {
            if (!projectId.isEmpty()) {
                project = projectRepository.getProjectById(projectId);
                if (project != null) {
                    return project;
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching project by ID with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public BaseResponse updateProject(Project project) {
        BaseResponse baseResponse = null;
        log.info("Inside ProjectServiceImpl.updateProject()");
        try {
            if (project != null) {
                Project projectFromDb = projectRepository.getProjectById(project.getProjectId());
                if (projectFromDb != null) {
                    if (project.getProjectStatus() == null) {
                        project.setProjectStatus(projectFromDb.getProjectStatus());
                    }
                    if (project.getProjectImagesBase64() == null) {
                        project.setProjectImagesBase64(projectFromDb.getProjectImagesBase64());
                        log.info("inside project.getProjectImagesBase64() == null");
                    }
                    if (project.getVideoUrl() == null) {
                        project.setVideoUrl(projectFromDb.getVideoUrl());
                    }
                    if (project.getOtherDocuments() == null) {
                        project.setOtherDocuments(projectFromDb.getOtherDocuments());
                    }
                    baseResponse = projectRepository.updateProject(project);
                } else {
                    log.error(ErrorCode.PROJECT_DOSENT_EXIST);
                    Collection<Errors> errors = new ArrayList<>();
                    errors.add(Errors.builder()
                            .message(ErrorCode.PROJECT_DOSENT_EXIST)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                            .errorType(Errors.ERROR_TYPE.USER.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                }
            } else {
                log.error(ErrorCode.NULL_REQUEST);
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NULL_REQUEST)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while updating project with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateInventory(Inventory inventory) {
        BaseResponse baseResponse = null;
        log.info("Inside ProjectServiceImpl.updateInventory()");
        try {
            if (inventory.getPlotNo() != null && inventory.getProjectId() != null) {
                Inventory inventoryFromDb = projectRepository.getInventoryByGetPlotNoAndProjectId(inventory.getProjectId(), inventory.getPlotNo());
                if (inventory.getCost() == null) {
                    inventory.setCost(inventoryFromDb.getCost());
                }
                if (inventory.getPlotSize() == null) {
                    inventory.setPlotSize(inventoryFromDb.getPlotSize());
                }
                if (inventory.getPlotStatus() == null) {
                    inventory.setPlotStatus(inventoryFromDb.getPlotStatus());
                }
                baseResponse = projectRepository.updateInventory(inventory);
            } else {
                log.error(ErrorCode.NULL_REQUEST);
                Collection<Errors> errors = new ArrayList<>();
                errors.add(Errors.builder()
                        .message(ErrorCode.NULL_REQUEST)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
        } catch (Exception e) {
            log.error("Exception occurred while updating inventory with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }
}
