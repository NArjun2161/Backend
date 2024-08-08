package com.istato.admin.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SendOtpResponse {
    private String mobileNumber;
    private String otp;
    private boolean isOtpSent;
    private Date sentDate;
    private String message;
    private String error;
}
