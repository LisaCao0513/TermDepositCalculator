package com.interview.depositcalculator.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.interview.depositcalculator.Entities.AtMaturityTermDepositInterestStrategy;
import com.interview.depositcalculator.Entities.ICalculationStrategy;
import com.interview.depositcalculator.Entities.InterestCalculator;
import com.interview.depositcalculator.Entities.InterestFrequency;
import com.interview.depositcalculator.Entities.InterestStrategyFactory;
import com.interview.depositcalculator.Entities.ReinvestTermDepositInterestStrategy;
import com.interview.depositcalculator.Entities.TermDeposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TermDepositServiceTest {

    @Mock
    private InterestCalculator mockInterestCalculator;

    @InjectMocks
    private TermDepositService termDepositService;

    @Mock
    private InterestStrategyFactory mockInterestStrategyFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateTermDeposit_withValidInputs() {
        // Given
        TermDeposit termDeposit = new TermDeposit(
            BigDecimal.valueOf(10000),
            BigDecimal.valueOf(1.10),
            BigDecimal.valueOf(36),
            InterestFrequency.MONTHLY
        );
        ICalculationStrategy mockStrategy = mock(ICalculationStrategy.class);
        BigDecimal expectedFinalBalance = BigDecimal.valueOf(10330);

        when(mockInterestStrategyFactory.getStrategy(termDeposit.getInterestFrequency())).thenReturn(mockStrategy);
        when(mockInterestCalculator.calculateFinalBalance(termDeposit, mockStrategy)).thenReturn(expectedFinalBalance);

        // When
        BigDecimal result = termDepositService.calculateTermDeposit(termDeposit);

        // Then
        assertEquals(expectedFinalBalance, result, "The final balance should be as expected");
        verify(mockInterestStrategyFactory, times(1)).getStrategy(termDeposit.getInterestFrequency());
        verify(mockInterestCalculator, times(1)).calculateFinalBalance(termDeposit, mockStrategy);
    }

    @Test
    public void testCalculateTermDeposit_withAtMaturity() {
        // Given
        TermDeposit termDeposit = new TermDeposit(
            BigDecimal.valueOf(10000),
            BigDecimal.valueOf(1.10),
            BigDecimal.valueOf(36),
            InterestFrequency.AT_MATURITY // This should be handled appropriately
        );
        ICalculationStrategy mockStrategy = mock(ICalculationStrategy.class);
        BigDecimal expectedFinalBalance = BigDecimal.valueOf(10330);

        when(mockInterestStrategyFactory.getStrategy(termDeposit.getInterestFrequency())).thenReturn(mockStrategy);
        when(mockInterestCalculator.calculateFinalBalance(termDeposit, mockStrategy)).thenReturn(expectedFinalBalance);

        // When
        BigDecimal result = termDepositService.calculateTermDeposit(termDeposit);

        // Then
        assertEquals(expectedFinalBalance, result, "The final balance should be as expected");
        verify(mockInterestStrategyFactory, times(1)).getStrategy(termDeposit.getInterestFrequency());
        verify(mockInterestCalculator, times(1)).calculateFinalBalance(termDeposit, mockStrategy);
    }

    @Test
    public void testCalculateTermDeposit_withNullTermDeposit() {
        // Given
        TermDeposit termDeposit = null;

        // When
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
            () -> termDepositService.calculateTermDeposit(termDeposit),
            "TermDeposit cannot be null"
        );
        assertEquals("TermDeposit cannot be null", thrown.getMessage());
    }
}
