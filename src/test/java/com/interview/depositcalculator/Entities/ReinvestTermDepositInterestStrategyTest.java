package com.interview.depositcalculator.Entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ReinvestTermDepositInterestStrategyTest {

    private final ReinvestTermDepositInterestStrategy strategy = new ReinvestTermDepositInterestStrategy();

    @Test
    public void testCalculateMonthlyCompounding() {
        BigDecimal principal = new BigDecimal("10000");
        BigDecimal annualRate = new BigDecimal("1.10"); // 1.10%
        BigDecimal termMonths = new BigDecimal("36");
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        BigDecimal expected = new BigDecimal("10335.35"); // Expected result
        BigDecimal actual = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        assertEquals(expected, actual, "The calculated amount should match the expected value.");
    }

    @Test
    public void testCalculateQuarterlyCompounding() {
        BigDecimal principal = new BigDecimal("10000");
        BigDecimal annualRate = new BigDecimal("1.10"); // 1.10%
        BigDecimal termMonths = new BigDecimal("36");
        InterestFrequency compoundingFrequency = InterestFrequency.QUARTERLY;

        BigDecimal expected = new BigDecimal("10335.04"); // Expected result
        BigDecimal actual = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        assertEquals(expected, actual, "The calculated amount should match the expected value.");
    }

    @Test
    public void testCalculateAnnualCompounding() {
        BigDecimal principal = new BigDecimal("10000");
        BigDecimal annualRate = new BigDecimal("1.10"); // 1.10%
        BigDecimal termMonths = new BigDecimal("36");
        InterestFrequency compoundingFrequency = InterestFrequency.ANNUALLY;

        BigDecimal expected = new BigDecimal("10333.64"); // Expected result
        BigDecimal actual = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        assertEquals(expected, actual, "The calculated amount should match the expected value.");
    }

    @Test
    public void testCalculateZeroPrincipal() {
        BigDecimal principal = BigDecimal.ZERO;
        BigDecimal annualRate = new BigDecimal("1.10"); // 1.10%
        BigDecimal termMonths = new BigDecimal("36");
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal actual = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        assertEquals(expected, actual, "The calculated amount should be zero when the principal is zero.");
    }

    @Test
    public void testCalculateZeroRate() {
        BigDecimal principal = new BigDecimal("10000");
        BigDecimal annualRate = BigDecimal.ZERO; // 0%
        BigDecimal termMonths = new BigDecimal("36");
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        BigDecimal expected = new BigDecimal("10000.00"); // Principal remains unchanged
        BigDecimal actual = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        assertEquals(expected, actual, "The calculated amount should be the same as the principal when the rate is zero.");
    }

    @Test
    public void testCalculateZerotermMonths() {
        BigDecimal principal = new BigDecimal("10000");
        BigDecimal annualRate = new BigDecimal("1.10"); // 1.10%
        BigDecimal termMonths = BigDecimal.ZERO;
        InterestFrequency compoundingFrequency = InterestFrequency.MONTHLY;

        BigDecimal expected = new BigDecimal("10000.00"); // Principal remains unchanged
        BigDecimal actual = strategy.calculate(principal, annualRate, termMonths, compoundingFrequency);

        assertEquals(expected, actual, "The calculated amount should be the same as the principal when the term is zero.");
    }

    @Test
    public void testCalculate_withAtMaturityFrequency_throwsException() {
        // Given
        BigDecimal principal = BigDecimal.valueOf(10000);
        BigDecimal annualRate = BigDecimal.valueOf(1.10);
        BigDecimal termMonths = BigDecimal.valueOf(3);
        InterestFrequency compoundingFrequency = InterestFrequency.AT_MATURITY;

        // When & Then
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> strategy.calculate(principal, annualRate, termMonths, compoundingFrequency),
            "Expected calculate() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Compounding frequency is not supported with At maturity"));
    }
}