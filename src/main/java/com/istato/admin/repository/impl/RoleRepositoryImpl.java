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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Slf4j
public class RoleRepositoryImpl implements RoleRepository {
    static Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaseResponse save(Role role) {
        BaseResponse baseResponse = null;
        try {
            logger.info("inside saveRole");
            baseResponse = IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(role));
        } catch (Exception e) {
            logger.error("Exception occurred while creating role{}", e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        Query query = new Query();
        logger.info("Inside existsByRoleName repo");
        try {
            query = query.addCriteria(Criteria.where(Constants.ROLE_NAME).is(roleName));
            List<Role> roles = mongoTemplate.find(query, Role.class);
            for (Role role : roles) {
                if (role.getRoleName().equals(roleName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("Exception occurred {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseResponse update(Role role) {
        BaseResponse baseResponse = null;
        try {
            logger.info("inside updateRole..");
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.ROLE_ID).is(role.getRoleId()));
            Update update = new Update();
            update.set(Constants.ROLE_NAME, role.getRoleName());
            update.set(Constants.ROLE_DESCRIPTION, role.getRoleDescription());
            update.set(Constants.IS_ACTIVE, role.getIsActive());

            baseResponse = IstatoUtils.getBaseResponse(
                    HttpStatus.OK,
                    mongoTemplate.upsert(query, update, Role.class));
        } catch (Exception e) {
            logger.error("Exception occurred while updating role{}", e.getMessage());
        }
        return baseResponse;
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
    public List<String> getAllRolesByStatus(Boolean isActive) {
        log.info("Inside getAllRolesByStatus repo");
        List<String> roleNames = new ArrayList<>();
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.IS_ACTIVE).is(isActive));
            List<Role> roles = mongoTemplate.find(query, Role.class);
            for (int i=0; i < roles.size() ; i++){
                roleNames.add(roles.get(i).getRoleName());
            }
            log.info("Roles by status {}", roles);
        } catch (Exception e) {
            log.error("Exception occurred while getAllRolesByStatus {}", e.getMessage());
        }
        return roleNames;
    }

    @Override
    public Role getRoleById(String roleId) {
        Role role = null;
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where(Constants.ROLE_ID).is(roleId));
            role = mongoTemplate.findOne(query, Role.class);
        } catch (Exception e) {
            log.error("Exception occurred while getting role from database with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return role;
    }

}
