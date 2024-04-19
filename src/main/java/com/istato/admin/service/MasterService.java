package com.istato.admin.service;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.MasterDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface MasterService {
    BaseResponse uploadMaster(MultipartFile file, MasterDetails masterName) throws IOException;
}
