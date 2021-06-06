package com.product.price.calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PCException extends RuntimeException {
    private ErrorCodes errorCode;

    public PCException(ErrorCodes errorCode, String message) {
        this.errorCode = errorCode;
        if (message != null && errorCode.getDescription().contains("%s")) {
            errorCode.setDescription(String.format(errorCode.getDescription(), message));
        }
    }
}
