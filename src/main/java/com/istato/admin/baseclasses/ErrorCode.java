package com.istato.admin.baseclasses;


public class ErrorCode {
    public static final String NO_DATA_FOUND = "No data found against provided request.";
    public static final String NULL_REQUEST = "Null request from user";
    public static final String INVALID_MASTER_NAME = "Invalid master name entered";
    public static final String CSV_PARSING_ERROR = "Error occurred while parsing csv file";
    public static final String MASTER_ALREADY_UPLOADED = "master already uploaded";
    public static final String ADMIN_NOT_EXISTS = "The requested admin doesn't exists";
    public static final String EXECUTIVE_NOT_EXISTS = "The requested executive doesn't exists";
    public static final String WRONG_PASSWORD = "Password you entered is wrong, please try again...!";
    public static final String WRONG_USERNAME = "username you entered is doesn't exists, please try again...!";
    public static final String EXCEPTION = "Exception occurred.";
    public static final String API_CONFIG_NOT_FOUND = "ApiConfig not found against provided apiName";
    public static final String PASSWORD_MISMATCH = "Password and confirm password entered dose not match, please try again...!";
    public static final String USERNAME_EXISTS = "Username already exists please try with another username";
    public static final String NO_NAME_FOUND = "No name found against provided request.";
    public static final String WRONG_PLAN_TYPE_OR_PROPERTY_TYPE = "Wrong plan type or property type";
    public static final String PLAN_ID_REQUIRED = "pLanId is required";
    public static final String PLAN_STATUS_REQUIRED = "pLanStatus is required";
    ;;


    public static final String NULL_ROLE_ID = "Role ID should not be null, please check!";

    private ErrorCode() {

    }
}