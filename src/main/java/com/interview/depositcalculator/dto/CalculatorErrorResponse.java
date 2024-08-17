package com.interview.depositcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculatorErrorResponse {

    private int status;
    private String error;
    private String message;
}
