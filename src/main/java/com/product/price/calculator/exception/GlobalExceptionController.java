package com.product.price.calculator.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PCException.class)
    public final ResponseEntity<?> handleFUException(PCException exception, HttpServletRequest request) {
        ErrorCodes errorCode = exception.getErrorCode();
        return buildResponse(errorCode.getErrorCode(), errorCode.getDescription(), errorCode.getHttpStatus(), errorCode.getText(), request);
    }

    private ResponseEntity<?> buildResponse(int errorCode, String description, HttpStatus httpStatus, String error, HttpServletRequest request) {
        ExceptionResponse errorResponse = new ExceptionResponse(getTimeStamp(), errorCode, error, description, request.getServletPath());
        log.error("Error response build: request-{} : errorCode-{}", request.getServletPath(), errorCode);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> exception(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), request);
    }

    private String getTimeStamp() {
        String pattern = "yyyy-MM-dd HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}