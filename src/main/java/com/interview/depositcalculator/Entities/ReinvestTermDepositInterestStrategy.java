package com.interview.depositcalculator.Entities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ReinvestTermDepositInterestStrategy implements ICalculationStrategy{
    public BigDecimal calculate(BigDecimal principal, BigDecimal annualRate, BigDecimal termMonths,
        InterestFrequency compoundingFrequency) {
        if (compoundingFrequency.equals(InterestFrequency.AT_MATURITY)) {
            throw new IllegalArgumentException("Compounding frequency is not supported with At maturity");
        }
        //"A=P * （1+r/n）^ nt"
        BigDecimal rate = annualRate.divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
        int frequencyValue = compoundingFrequency.getCompoundingFrequency();

        // Calculate (1 + rate / compoundingFrequency)
        BigDecimal frequency = BigDecimal.valueOf(frequencyValue);
        BigDecimal divisor = BigDecimal.ONE.add(rate.divide(frequency, MathContext.DECIMAL128));

        // Calculate (1 + rate / compoundingFrequency) ^ (compoundingFrequency * termYears)
        BigDecimal termYears = termMonths.divide(BigDecimal.valueOf(12), MathContext.DECIMAL128);
        BigDecimal exponent = frequency.multiply(termYears);
        BigDecimal finalAmount = principal.multiply(divisor.pow(exponent.intValue(), MathContext.DECIMAL128));

        return finalAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
