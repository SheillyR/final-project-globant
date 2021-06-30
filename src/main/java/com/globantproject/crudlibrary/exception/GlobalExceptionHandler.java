package com.globantproject.crudlibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFound (BookNotFoundException bookNotFoundException,
                                                 WebRequest webRequest) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(),
                                                bookNotFoundException.getMessage(),
                                                webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookBadRequestException.class)
    public ResponseEntity<?> handleBookBadRequestException (BookBadRequestException bookBadRequestException,
                                                 WebRequest webRequest) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(),
                bookBadRequestException.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
