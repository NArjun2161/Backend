package com.istato.admin.utils;

import com.istato.admin.baseclasses.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class IstatoUtils {

    public static BaseResponse getBaseResponse(CustomHttpStatus httpStatus, Collection<Errors> errors) {

        return BaseResponse.builder()
                .status(Status.builder()
                        .statusCode(httpStatus.value()).statusValue(httpStatus.name()).build())
                .errors(errors)
                .build();
    }

    public static BaseResponse getBaseResponse(HttpStatus httpStatus, Object buzResponse) {
        if (null == buzResponse)
            buzResponse = Collections.emptyMap();

        return BaseResponse.builder()
                .payload(new Payload<>(buzResponse))
                .status(
                        Status.builder()
                                .statusCode(httpStatus.value())
                                .statusValue(httpStatus.name()).build())
                .build();
    }

    public static Collection<Errors> getNoContentErrorList() {
        Collection<Errors> errors = new ArrayList<>();
        errors.add(Errors.builder()
                .message(ErrorCode.NO_DATA_FOUND)
                .errorCode(String.valueOf(Errors.ERROR_TYPE.DATABASE.toCode()))
                .errorType(Errors.ERROR_TYPE.DATABASE.toValue())
                .level(Errors.SEVERITY.LOW.name())
                .build());
        return errors;
    }


}
