package com.istato.admin.service.impl;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.baseclasses.CustomHttpStatus;
import com.istato.admin.baseclasses.ErrorCode;
import com.istato.admin.baseclasses.Errors;
import com.istato.admin.model.MasterDetails;
import com.istato.admin.model.MasterUploadEventLog;
import com.istato.admin.model.MastersData;
import com.istato.admin.model.StateMaster;
import com.istato.admin.repository.MasterRepo;
import com.istato.admin.service.MasterService;
import com.istato.admin.utils.IstatoUtils;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepo masterRepo;

    @Override
    public BaseResponse uploadMaster(MultipartFile file, MasterDetails masterName) throws IOException {
        log.info("Inside upload master service");
        BaseResponse baseResponse = null;
        Collection<Errors> errors = new ArrayList<>();
        MastersData mastersData = new MastersData();
        try {
            if (file.isEmpty() || masterName.getMasterName().isEmpty()) {
                log.error("invalid file or master name");
                errors.add(Errors.builder()
                        .message(ErrorCode.NULL_REQUEST)
                        .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                        .errorType(Errors.ERROR_TYPE.USER.toValue())
                        .level(Errors.SEVERITY.HIGH.name())
                        .build());
                log.error("Null request");
                return IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
            }
            switch (masterName) {
                case ISTATO_STATE_MASTER:
                    log.info("Inside upload state master");
                    List<StateMaster> stateMasterList = parseCsvData(file);
                    mastersData.setMasterName(masterName.getMasterName());
                    mastersData.setStateMaster(stateMasterList);
                    if (!stateMasterList.isEmpty()) {
                        MasterUploadEventLog masterUploadEventLog = masterRepo.getMasterUploadEventLog(masterName.getMasterName());
                        if (masterUploadEventLog == null) {
                            baseResponse = masterRepo.uploadMaster(mastersData);
                        } else if (masterUploadEventLog.isMasterUploaded()) {
                            log.error("master is already uploaded");
                            errors.add(Errors.builder()
                                    .message(ErrorCode.MASTER_ALREADY_UPLOADED)
                                    .errorCode(String.valueOf(Errors.ERROR_TYPE.SYSTEM.toCode()))
                                    .errorType(Errors.ERROR_TYPE.SYSTEM.toValue())
                                    .level(Errors.SEVERITY.HIGH.name())
                                    .build());
                            return IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                        }

                    } else {
                        log.error("Error occurred while parsing csv file");
                        errors.add(Errors.builder()
                                .message(ErrorCode.CSV_PARSING_ERROR)
                                .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                                .errorType(Errors.ERROR_TYPE.USER.toValue())
                                .level(Errors.SEVERITY.HIGH.name())
                                .build());
                        baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                    }
                    break;
                default:
                    errors.add(Errors.builder()
                            .message(ErrorCode.INVALID_MASTER_NAME)
                            .errorCode(String.valueOf(Errors.ERROR_TYPE.USER.toCode()))
                            .errorType(Errors.ERROR_TYPE.USER.toValue())
                            .level(Errors.SEVERITY.HIGH.name())
                            .build());
                    log.error("Invalid master name");
                    baseResponse = IstatoUtils.getBaseResponse(CustomHttpStatus.FAILURE, errors);
                    break;
            }
            if (baseResponse.getStatus().getStatusCode() == 200) {
                MasterUploadEventLog masterUploadEventLog = new MasterUploadEventLog();
                masterUploadEventLog.setMasterName(masterName.getMasterName());
                masterUploadEventLog.setMasterUpdateDate(new Date());
                masterUploadEventLog.setMasterUploaded(true);
                masterRepo.saveMasterUploadEventLog(masterUploadEventLog);
            }
            return baseResponse;
        } catch (IOException e) {
            log.error("Exception occurred while calling upload master api with probable cause {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private List<StateMaster> parseCsvData(MultipartFile file) throws IOException {
        List<StateMaster> stateMasterList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> data = reader.readAll();
            for (String[] row : data) {
                StateMaster stateMaster = new StateMaster();
                stateMaster.setStateName(row[0]);
                stateMasterList.add(stateMaster);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stateMasterList;
    }
}
