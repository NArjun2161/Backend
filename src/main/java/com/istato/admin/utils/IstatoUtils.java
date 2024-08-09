package com.istato.admin.utils;

import com.istato.admin.baseclasses.*;
import com.istato.admin.model.Executive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

import static com.istato.admin.baseclasses.Constants.*;


@Slf4j
public class IstatoUtils {

    public static String generateRoleId(String roleName, String boundValue) {
        log.info("Inside generateRoleId");
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
    public static String generatePlanId(String planType,String propertyType, String boundValue) {
        log.info("Inside generateProductId");
        Random random = new Random();
        int rValue = random.nextInt(Integer.parseInt(boundValue));
        // Logic to generate a unique ID based on the name and some random numeric value
        String firstPrefix = planType.substring(0, Math.min(planType.length(), 2)).toUpperCase();
        String secPrefix = propertyType.substring(0, Math.min(propertyType.length(), 2)).toUpperCase();
        String randomNumericPart = String.format("%02d", rValue);

        return firstPrefix + secPrefix +FieldSeprators.HYPEN.toFaceValue()+ randomNumericPart;
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

    public static String encryptString(String data, String key, String iv) throws Exception {
        log.info("Inside encryptString");
        try {
            if (data == null) {
                log.warn("Attempting to encrypt null data. Returning null.");
                return null;
            }

            byte[] byteKey = key.getBytes();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec secretKeySpec = new SecretKeySpec(byteKey, ALGORITHM);
            byte[] byteIv = iv.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(byteIv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            log.error("Exception occurred while encrypting data {} is {}", data, e.getMessage());
            throw new Exception(e);
        }
    }


    public static String decryptString(String encryptedData, String key, String iv) throws Exception {
        log.info("Inside decryptString");
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] byteKey = key.getBytes();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec secretKeySpec = new SecretKeySpec(byteKey, ALGORITHM);
            byte[] byteIv = iv.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(byteIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            log.error("Exception occurred while decrypting encryptedData {} is {}", encryptedData, e.getMessage());
            throw new Exception(e);
        }
    }

    public static String generateProjectId(String projectName, int boundValue) {
        log.info("Inside generateProductId");
        Random random = new Random();
        int rValue = random.nextInt((int) Math.pow(10, boundValue));
        String Prefix = projectName.substring(0, Math.min(projectName.length(), 4)).toUpperCase();
        return Prefix +FieldSeprators.HYPEN.toFaceValue()+ rValue;

    }
}
