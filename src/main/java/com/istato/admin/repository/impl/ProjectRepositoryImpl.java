package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Project;
import com.istato.admin.repository.ProjectRepository;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}
