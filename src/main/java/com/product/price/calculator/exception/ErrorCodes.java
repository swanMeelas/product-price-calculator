package com.product.price.calculator.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {

    BAD_REQUEST(400, HttpStatus.BAD_REQUEST,"Bad Request","%s"),
    FORBIDDEN(403, HttpStatus.FORBIDDEN,"Unauthorized","Accessing to the resources without proper permissions for it"),
    NOT_FOUND(404, HttpStatus.NOT_FOUND, "Not Found","Product not found"),
    DATABASE_EXCEPTION(500,HttpStatus.INTERNAL_SERVER_ERROR,"Database Server Error","%s"),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error","%s");

    private int errorCode;
    private HttpStatus httpStatus;
    private String text;
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    ErrorCodes(int errorCode, HttpStatus httpStatus, String text, String description) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.text = text;
        this.description = description;
    }
}
