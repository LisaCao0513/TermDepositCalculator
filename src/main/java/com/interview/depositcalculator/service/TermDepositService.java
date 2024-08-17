package com.interview.depositcalculator.service;

import com.interview.depositcalculator.Entities.ICalculationStrategy;
import com.interview.depositcalculator.Entities.InterestCalculator;
import com.interview.depositcalculator.Entities.InterestStrategyFactory;
import com.interview.depositcalculator.Entities.TermDeposit;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class TermDepositService {

    private final InterestCalculator interestCalculator;
    // to determine which equations to use
    private final InterestStrategyFactory interestStrategyFactory;

    public TermDepositService(InterestCalculator interestCalculator,
        InterestStrategyFactory interestStrategyFactory) {
        this.interestCalculator = interestCalculator;
        this.interestStrategyFactory = interestStrategyFactory;
    }
    public BigDecimal calculateTermDeposit(TermDeposit termDeposit) {
        if (termDeposit == null) {
            throw new IllegalArgumentException("TermDeposit cannot be null");
        }
        ICalculationStrategy strategy = interestStrategyFactory.getStrategy(termDeposit.getInterestFrequency());
        return interestCalculator.calculateFinalBalance(termDeposit, strategy);
    }
}
