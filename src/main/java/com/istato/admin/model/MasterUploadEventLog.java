package com.istato.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class MasterUploadEventLog {
    private String masterName;
    private Date masterUpdateDate;
    private boolean isMasterUploaded;
}
