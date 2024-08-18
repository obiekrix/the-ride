package com.example.ridesharing.exception;

import com.example.ridesharing.constant.ErrorCode;
import com.example.ridesharing.model.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorCode error = ErrorCode.valueOf(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(error.getErrorCode(), error.getErrorDescription());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorCode error = ErrorCode.valueOf(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(error.getErrorCode(), error.getErrorDescription());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorCode error = ErrorCode.ALREADY_EXISTS;
        ErrorResponse errorResponse = new ErrorResponse(error.getErrorCode(), error.getErrorDescription());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
