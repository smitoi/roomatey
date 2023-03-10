package com.smitoi.roomatey.controller;

import com.smitoi.roomatey.exceptions.NotOwnerOfObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<String> handleException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({NotOwnerOfObject.class})
    public ResponseEntity<String> handleException(NotOwnerOfObject exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getClass() + ": " + exception.getMessage());
    }

}
