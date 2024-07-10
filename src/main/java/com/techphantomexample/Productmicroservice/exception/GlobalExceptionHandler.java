package com.techphantomexample.Productmicroservice.exception;

import com.techphantomexample.Productmicroservice.controller.CreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlantException.class)
    public ResponseEntity<CreateResponse> handleUserOperationException(PlantException ex) {
        CreateResponse createResponse = new CreateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlanterException.class)
    public ResponseEntity<CreateResponse> handleUserOperationException(PlanterException ex) {
        CreateResponse createResponse = new CreateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeedException.class)
    public ResponseEntity<CreateResponse> handleUserOperationException(SeedException ex) {
        CreateResponse createResponse = new CreateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CreateResponse> handleGlobalException(Exception ex, WebRequest request) {
        CreateResponse createResponse = new CreateResponse("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(createResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
