package com.istato.admin.utils;

import com.istato.admin.baseclasses.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

import static com.istato.admin.baseclasses.Constants.*;



@Slf4j
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

    public static String encryptString(String data, String key, String iv) throws Exception {
        log.info("Inside encryptString");
        try {
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
}
