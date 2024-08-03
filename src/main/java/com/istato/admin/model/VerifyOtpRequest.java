package com.istato.admin.model;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String mobileNumber;
    private String otp;
}
