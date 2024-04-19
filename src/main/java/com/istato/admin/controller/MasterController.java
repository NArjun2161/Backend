package com.istato.admin.controller;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.Constants;
import com.istato.admin.baseclasses.EndPointRefer;
import com.istato.admin.model.MasterDetails;
import com.istato.admin.service.MasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RestController
@Slf4j
@RequestMapping(EndPointRefer.MASTER)
public class MasterController {
    @Autowired
    private MasterService masterService;

    @RequestMapping(value = EndPointRefer.UPLOAD_GENERIC_MASTER)
    public BaseResponse uploadGenericMaster(
            @RequestParam(value = "file", required = true) @NotNull final MultipartFile file,
            @RequestParam @NotBlank MasterDetails masterName) throws Exception {
        log.info(EndPointRefer.UPLOAD_GENERIC_MASTER + Constants.CONTROLLER_STARTED);
        return masterService.uploadMaster(file, masterName);

    }
}
