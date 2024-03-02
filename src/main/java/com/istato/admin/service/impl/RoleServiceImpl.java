package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.Role;
import com.istato.admin.repository.RoleRepository;
import com.istato.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseResponse createRole(Role role) {
        try {
            Role saveRole=roleRepository.save(role);
            return  new BaseResponse(true,"Role create Successfully",saveRole);
        }catch (Exception e){
            String erroeMessage=(e.getMessage() != null) ? e.getMessage() :"Unknown error";
            return new BaseResponse(false,"Failed to create role: " + erroeMessage,null);
        }
    }
}
