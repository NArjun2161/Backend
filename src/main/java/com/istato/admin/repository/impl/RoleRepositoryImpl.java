package com.istato.admin.repository.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.model.Role;
import com.istato.admin.repository.RoleRepository;
import com.istato.admin.utils.IstatoUtils;
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
public class RoleRepositoryImpl implements RoleRepository {
    static Logger logger= LoggerFactory.getLogger(RoleRepositoryImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public BaseResponse save(Role role) {
        BaseResponse baseResponse=null;
        try {
            logger.info("inside saveRole");
            baseResponse= IstatoUtils.getBaseResponse(HttpStatus.OK, mongoTemplate.save(role));
        }catch (Exception e){
            logger.error("Exception occurred while creating role{}" , e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        Query query = new Query();
        query =query.addCriteria(Criteria.where(Constants.ROLE_NAME).is(roleName));
        List<Role> roles = mongoTemplate.find(query, Role.class);
        for (Role role : roles) {
            if (role.getRoleName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
}
