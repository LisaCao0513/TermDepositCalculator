package com.interview.depositcalculator.Entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class AtMaturityTermDepositInterestStrategyTest {

    private final AtMaturityTermDepositInterestStrategy strategy = new AtMaturityTermDepositInterestStrategy();

    @Test
    public void testCalculate_withValidInputs() {
        // Given
        BigDecimal principal = BigDecimal.valueOf(10000);
        BigDecimal annualRate = BigDecimal.valueOf(1.10);
        BigDecimal termMonths = BigDecimal.valueOf(36);
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY; // Assuming this is used but not required in the method

        // When
        BigDecimal result = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        // Then
        BigDecimal expected = BigDecimal.valueOf(10000)
            .multiply(BigDecimal.valueOf(1.10).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128))
            .multiply(BigDecimal.valueOf(36).divide(BigDecimal.valueOf(12), MathContext.DECIMAL128));
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP), result, "The calculated interest should match the expected value");
    }

    @Test
    public void testCalculate_withZeroPrincipal() {
        // Given
        BigDecimal principal = BigDecimal.ZERO;
        BigDecimal annualRate = BigDecimal.valueOf(1.10);
        BigDecimal termMonths = BigDecimal.valueOf(36);
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        // When
        BigDecimal result = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        // Then
        assertEquals(new BigDecimal("0.00"), result, "The result should be zero when principal is zero");
    }

    @Test
    public void testCalculate_withZeroAnnualRate() {
        // Given
        BigDecimal principal = BigDecimal.valueOf(10000);
        BigDecimal annualRate = BigDecimal.ZERO;
        BigDecimal termMonths = BigDecimal.valueOf(36);
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        // When
        BigDecimal result = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        // Then
        assertEquals(new BigDecimal("0.00"), result, "The result should be zero when annual rate is zero");
    }

    @Test
    public void testCalculate_withZeroTermMonths() {
        // Given
        BigDecimal principal = BigDecimal.valueOf(10000);
        BigDecimal annualRate = BigDecimal.valueOf(1.10);
        BigDecimal termMonths = BigDecimal.ZERO;
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        // When
        BigDecimal result = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        // Then
        assertEquals(new BigDecimal("0.00"), result, "The result should be zero when term months is zero");
    }

    @Test
    public void testCalculate_withNegativePrincipal() {
        // Given
        BigDecimal principal = BigDecimal.valueOf(-10000);
        BigDecimal annualRate = BigDecimal.valueOf(1.10);
        BigDecimal termMonths = BigDecimal.valueOf(36);
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        // When
        BigDecimal result = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        // Then
        BigDecimal expected = BigDecimal.valueOf(-10000)
            .multiply(BigDecimal.valueOf(1.10).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128))
            .multiply(BigDecimal.valueOf(36).divide(BigDecimal.valueOf(12), MathContext.DECIMAL128));
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP), result, "The result should be calculated correctly with negative principal");
    }

    @Test
    public void testCalculate_withNegativeAnnualRate() {
        // Given
        BigDecimal principal = BigDecimal.valueOf(10000);
        BigDecimal annualRate = BigDecimal.valueOf(-1.10);
        BigDecimal termMonths = BigDecimal.valueOf(36);
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        // When
        BigDecimal result = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        // Then
        BigDecimal expected = BigDecimal.valueOf(10000)
            .multiply(BigDecimal.valueOf(-1.10).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128))
            .multiply(BigDecimal.valueOf(36).divide(BigDecimal.valueOf(12), MathContext.DECIMAL128));
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP), result, "The result should be calculated correctly with negative annual rate");
    }

    @Test
    public void testCalculate_withNegativeTermMonths() {
        // Given
        BigDecimal principal = BigDecimal.valueOf(10000);
        BigDecimal annualRate = BigDecimal.valueOf(1.10);
        BigDecimal termMonths = BigDecimal.valueOf(-36);
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        // When
        BigDecimal result = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        // Then
        BigDecimal expected = BigDecimal.valueOf(10000)
            .multiply(BigDecimal.valueOf(1.10).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128))
            .multiply(BigDecimal.valueOf(-36).divide(BigDecimal.valueOf(12), MathContext.DECIMAL128));
        assertEquals(expected.setScale(2, RoundingMode.HALF_UP), result, "The result should be calculated correctly with negative term months");
    }

}