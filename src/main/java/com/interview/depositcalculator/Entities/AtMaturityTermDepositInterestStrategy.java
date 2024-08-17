package com.interview.depositcalculator.Entities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class AtMaturityTermDepositInterestStrategy implements ICalculationStrategy {

    @Override
    public BigDecimal calculate(BigDecimal principal, BigDecimal annualRate, BigDecimal termMonths,
        InterestFrequency compoundingFrequency) {
        BigDecimal rate = annualRate.divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
        BigDecimal finalAmount =  principal.multiply(rate.multiply(termMonths.divide(BigDecimal.valueOf(12), MathContext.DECIMAL128)));
        return finalAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
