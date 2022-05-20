package com.example.crudapplicationsql.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

        @ExceptionHandler(value = {BookServiceException.class})
        public ResponseEntity<Object> handleBookServiceException(BookServiceException bookServiceException)
        {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setTimestamp(new Date());
            errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
            errorMessage.setError(HttpStatus.NOT_FOUND.toString());
            errorMessage.setMessage(bookServiceException.getMessage());
            return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(value = {Exception.class})
        public ResponseEntity<Object> handleException(Exception exception)
        {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setTimestamp(new Date());
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            errorMessage.setError(HttpStatus.BAD_REQUEST.toString());
            errorMessage.setMessage(exception.getMessage());
            return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

}
