package com.istato.admin.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VerifyOtpResponse {
    private boolean isOtpVerified;
    private String mobileNumber;
    private Date verifiedDate;
}
