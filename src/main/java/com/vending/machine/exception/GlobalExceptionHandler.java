package com.vending.machine.exception;

import com.vending.machine.model.generic.ErrorServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

import static com.vending.machine.exception.ExceptionConstants.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorServiceResponse> handleGlobalException(Exception ex, WebRequest request) {
        // Log the exception (optional)
        ex.printStackTrace();
        // Create the custom error response
        ErrorServiceResponse errorResponse =
                buildErrorResponse(OPERATION_NOT_AVAILABLE_CODE,OPERATION_NOT_AVAILABLE,HttpStatus.INTERNAL_SERVER_ERROR.toString());
        // Return a generic error message
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorServiceResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorServiceResponse errorResponse = buildErrorResponse(BAD_REQUEST_CODE,BAD_REQUEST,HttpStatus.BAD_REQUEST.getReasonPhrase().toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorServiceResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        ErrorServiceResponse errorResponse = buildErrorResponse(BAD_REQUEST_CODE,BAD_REQUEST,HttpStatus.BAD_REQUEST.getReasonPhrase().toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorServiceResponse buildErrorResponse(String code, String description,  String english) {
        ErrorServiceResponse response = new ErrorServiceResponse();
        response.setReference(UUID.randomUUID().toString());
        response.setCode(code);
        response.setDescription(description);
        response.setEnglishMessage(english);
        return response;
    }
}