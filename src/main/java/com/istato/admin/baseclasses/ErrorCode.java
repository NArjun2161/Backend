package com.istato.admin.baseclasses;


public class ErrorCode {
    public static final String NO_DATA_FOUND = "No data found against provided request.";
    public static final String NULL_REQUEST = "Null request from user";
    public static final String EXCEPTION = "Exception occurred.";
    public static final String API_CONFIG_NOT_FOUND = "ApiConfig not found against provided apiName";
    public static final String NO_ID_FOUND = "Product ID is required";
    public static final String PRODUCT_NAME_OR_ID_REQUIRED = "Product Id is wrong or missing";

    private ErrorCode() {

    }
}