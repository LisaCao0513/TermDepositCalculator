package com.interview.depositcalculator.Entities;

import org.springframework.stereotype.Component;

@Component
public class InterestStrategyFactory {

    public ICalculationStrategy getStrategy(InterestFrequency frequency) {
        switch (frequency) {
            case AT_MATURITY:
                return new AtMaturityTermDepositInterestStrategy();
            case MONTHLY:
            case QUARTERLY:
            case ANNUALLY:
                return new ReinvestTermDepositInterestStrategy();
            default:
                throw new IllegalArgumentException("Unsupported interest frequency: " + frequency);
        }
    }

}
