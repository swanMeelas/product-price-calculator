package com.product.price.calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExceptionResponse {
    private String code;
    private int description;
    private String error;
    private String message;
    private String path;
}
