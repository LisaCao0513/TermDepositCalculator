package com.interview.depositcalculator.Entities;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class InterestCalculator {

    public BigDecimal calculateFinalBalance(TermDeposit deposit, ICalculationStrategy strategy) {
        return strategy.calculate(deposit.getPrincipal(), deposit.getAnnualRate(),
            deposit.getInvestmentMonths(), deposit.getInterestFrequency());
    }
}
