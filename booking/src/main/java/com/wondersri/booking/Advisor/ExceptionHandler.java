package com.wondersri.booking.Advisor;

import com.wondersri.booking.Exception.NotFoundException;
import com.wondersri.booking.util.enums.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> notFoundExceptionHandler(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error Occurred",e.getMessage()), HttpStatus.NOT_FOUND
        );
    }
}