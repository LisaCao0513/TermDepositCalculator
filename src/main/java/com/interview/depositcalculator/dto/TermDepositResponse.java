package com.interview.depositcalculator.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TermDepositResponse {
    private BigDecimal finalBalance;

    public TermDepositResponse(BigDecimal finalBalance) {
        this.finalBalance = finalBalance;
    }
}
