package com.interview.depositcalculator.Exceptions;

import com.interview.depositcalculator.dto.CalculatorErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CalculatorErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        CalculatorErrorResponse calculatorErrorResponse = new CalculatorErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            errors.toString()
        );

        return new ResponseEntity<>(calculatorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CalculatorErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        CalculatorErrorResponse calculatorErrorResponse = new CalculatorErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Argument",
            ex.getMessage()
        );

        return new ResponseEntity<>(calculatorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CalculatorErrorResponse> handleHttpMessageNotReadableException() {
        CalculatorErrorResponse calculatorErrorResponse = new CalculatorErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Argument",
            "Malformed JSON request body"
        );

        return new ResponseEntity<>(calculatorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CalculatorErrorResponse> handleGlobalException(Exception ex) {
        CalculatorErrorResponse calculatorErrorResponse = new CalculatorErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage()
        );

        return new ResponseEntity<>(calculatorErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
