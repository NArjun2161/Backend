package com.istato.admin.utils;

import com.istato.admin.baseclasses.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class IstatoUtils {
    private static final Logger logger = LoggerFactory.getLogger(IstatoUtils.class);

    public static String generateRoleId(String roleName, String boundValue) {
        logger.info("Inside generateRoleId");
        if (roleName == null || roleName.length() <2)
        {
            throw new IllegalArgumentException("RollName must have at least three character");

        }
        //Extract uppercase initials from each word
        String[] words=roleName.split(" ");
        StringBuilder prefix=new StringBuilder();
        for (String word :words){
            if (!word.isEmpty()){
                prefix.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        //Gereate a random number
        Random random = new Random();
        int rValue=random.nextInt(Integer.parseInt(boundValue));
        String randomNumbericPart=String.valueOf(rValue);
        // Logic to generate a unique ID based on the name and some random numeric valueString
        return prefix.toString() + randomNumbericPart;

    }



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
