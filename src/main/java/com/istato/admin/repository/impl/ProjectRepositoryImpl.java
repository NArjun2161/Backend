package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.model.Inventory;
import com.istato.admin.model.Project;
import com.istato.admin.repository.ProjectRepository;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public BaseResponse saveProject(Project project) {
        BaseResponse baseResponse = null;
        try {
            log.info("Saving project");
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(project));
        } catch (Exception e) {
            log.info("Error saving project {}", e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public Project getProjectById(String projectId) {
        Project project = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.PROJECT_ID).is(projectId));
            project = mongoTemplate.findOne(query, Project.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public BaseResponse addInventory(Inventory inventory) {
        BaseResponse baseResponse = null;
        try {
            mongoTemplate.save(inventory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateProject(Project project) {
        BaseResponse baseResponse = null;
        try {
            Query query = new Query();
            Update update = new Update();
            query.addCriteria(Criteria.where("projectId").is(project.getProjectId()));
            update.set("projectStatus", project.getProjectStatus());
            update.set("projectImagesBase64", project.getProjectImagesBase64());
            update.set("videoUrl", project.getVideoUrl());
            update.set("otherDocuments", project.getOtherDocuments());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.updateMulti(query, update, Project.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseResponse;
    }

    @Override
    public Inventory getInventoryByGetPlotNoAndProjectId(String projectId, String plotNo) {
        Inventory inventory = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("projectId").is(projectId).and("plotNo").is(plotNo));
            inventory = mongoTemplate.findOne(query, Inventory.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }

    @Override
    public BaseResponse updateInventory(Inventory inventory) {
        BaseResponse baseResponse = null;
        try {
            Query query = new Query();
            Update update = new Update();
            query.addCriteria(Criteria.where("projectId").is(inventory.getProjectId()).and("plotNo").is(inventory.getPlotNo()));
            update.set("cost", inventory.getCost());
            update.set("plotSize", inventory.getCost());
            update.set("plotStatus", inventory.getPlotStatus());
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.updateFirst(query, update, Inventory.class));
        } catch (Exception e) {
            log.error("Exception occurred while updating inventory with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return baseResponse;
    }
}
