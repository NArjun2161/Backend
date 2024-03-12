package com.istato.admin.baseclasses;


public class ErrorCode {
    public static final String NO_DATA_FOUND = "No data found against provided request.";
    public static final String NULL_REQUEST = "Null request from user";
    public static final String ADMIN_NOT_EXISTS = "The requested admin doesn't exists";
    public static final String WRONG_PASSWORD = "Password you entered is wrong, please try again...!";
    public static final String EXCEPTION = "Exception occurred.";
    public static final String API_CONFIG_NOT_FOUND = "ApiConfig not found against provided apiName";
    public static final String NO_ID_FOUND = "Product ID is required";
    public static final String PRODUCT_NAME_OR_ID_REQUIRED = "Product Id is wrong or missing";
    public static final String PASSWORD_MISMATCH = "Password and confirm password entered dose not match, please try again...!";
    public static final String NULL_ROLE_ID = "Role ID should not be null, please check!";
    private ErrorCode() {

    }
}