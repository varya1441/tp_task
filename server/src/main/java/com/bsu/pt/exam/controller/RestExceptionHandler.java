package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.exception.StudentValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(StudentValidationException.class)
    protected ResponseEntity<ExceptionEntity> handleUserValidationException(final StudentValidationException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    static class ExceptionEntity {
        private String message;
    }

    public static String createExceptionMessage(List<ObjectError> errors) {
        StringBuilder builder = new StringBuilder();
        errors.forEach((error) -> {
            FieldError err = (FieldError) error;
            builder.append("Field - ")
                    .append(err.getField())
                    .append(" : ")
                    .append(err.getDefaultMessage())
                    .append("\n");
        });
        return builder.toString();
    }
}
