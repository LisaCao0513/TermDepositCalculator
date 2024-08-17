package com.interview.depositcalculator.Entities;

import java.math.BigDecimal;

public interface ICalculationStrategy {
    BigDecimal calculate(BigDecimal principal, BigDecimal annualRate, BigDecimal termMonths, InterestFrequency compoundingFrequency);
}
