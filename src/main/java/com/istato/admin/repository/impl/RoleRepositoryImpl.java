package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.model.Role;
import com.istato.admin.repository.RoleRepository;
import com.istato.admin.utils.IstatoUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Slf4j
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaseResponse save(Role role) {
        BaseResponse baseResponse = null;
        try {
            log.info("inside saveRole");
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(role));
        } catch (Exception e) {
            log.error("Exception occurd while creating role{}", e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        Query query = new Query();
        query = query.addCriteria(Criteria.where("roleName").is(roleName));
        List<Role> roles = mongoTemplate.find(query, Role.class);
        for (Role role : roles) {
            if (role.getRoleName().equals(roleName) && role.getIsActive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Role> getAllRoles() {
        log.info("Inside getAllRoles repo");
        List<Role> roles = null;
        try {
            roles = mongoTemplate.findAll(Role.class);
        } catch (Exception e) {
            log.error("Exception occurred while fetching all roles {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return roles;
    }

    @Override
    public List<Role> getAllRolesByStatus(Boolean isActive) {
        log.info("Inside getAllRolesByStatus repo");
        List<Role> roles = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.IS_ACTIVE).is(isActive));
            roles = mongoTemplate.find(query, Role.class);
            log.info("Roles by status {}", roles);
        } catch (Exception e) {
            log.error("Exception occurred while getAllRolesByStatus {}", e.getMessage());
        }
        return roles;
    }


}
