package com.istato.admin.repository;

import com.istato.admin.baseclasses.BaseResponse;
import com.istato.admin.model.MasterUploadEventLog;
import com.istato.admin.model.MastersData;


public interface MasterRepo {
    BaseResponse uploadMaster(MastersData mastersData);

    void saveMasterUploadEventLog(MasterUploadEventLog masterUploadEventLog);

    MasterUploadEventLog getMasterUploadEventLog(String masterName);
}
