package com.istato.admin.baseclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.util.List;

@ToString
public class Errors {

    private static final long serialVersionUID = 1L;


    @JsonProperty("sErrorType")
    private String errorType;

    @JsonProperty("sErrorCode")
    private String errorCode;

    @JsonProperty("sMessage")
    private String message;

    @JsonProperty("sLevel")
    private String level;

    @JsonProperty("oValidationError")
    BindingResult validationError;

    public BindingResult getError() {
        return validationError;
    }

    public void setError(BindingResult error) {
        this.validationError = error;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static Builder builder() {
        return new Builder();
    }


    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
        result = prime * result + ((errorType == null) ? 0 : errorType.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((validationError == null) ? 0 : validationError.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Errors other = (Errors) obj;
        if (errorCode == null) {
            if (other.errorCode != null) return false;
        } else if (!errorCode.equals(other.errorCode)) return false;
        if (errorType == null) {
            if (other.errorType != null) return false;
        } else if (!errorType.equals(other.errorType)) return false;
        if (level == null) {
            if (other.level != null) return false;
        } else if (!level.equals(other.level)) return false;
        if (message == null) {
            if (other.message != null) return false;
        }
        if (validationError == null) {
            if (other.validationError != null) return false;
        } else if (!message.equals(other.message)) return false;
        return true;
    }


    public enum SEVERITY {
        CRITICAL, HIGH, MEDIUM, LOW
    }

    public enum ERROR_TYPE {

        SYSTEM("SYSTEM", 1000),
        USER("USER", 2000),
        DATABASE("DATABASE", 3000),

        NETWORK("NETWORK ERROR", 1001),
        AGE_EXCEPTION("Agre is below required ", 20987),

        ASSET_COST_ERROR("ASSET_COST_ERROR", 4000),
        VALIDATION("VALIDATION", 4001);

        private int code;
        private String value;

        private ERROR_TYPE(final String value, final int code) {
            this.code = code;
            this.value = value;
        }

        public String toValue() {
            return value;
        }

        public int toCode() {
            return code;
        }


    }

    public static class Builder {
        private Errors error = new Errors();

        public Errors build() {
            return error;
        }


        public Builder errorType(String errorType) {

            this.error.setErrorType(errorType);

            return this;
        }

        public Builder errorCode(String errorCode) {

            this.error.setErrorCode(errorCode);

            return this;
        }

        public Builder message(String message) {

            this.error.setMessage(message);

            return this;
        }

        public Builder level(String level) {

            this.error.setLevel(level);
            return this;
        }

        public Builder validationError(BindingResult validationError) {
            this.error.setError(validationError);
            return this;
        }

    }

}
