package com.interview.depositcalculator.Entities;

import lombok.Getter;

@Getter
public enum InterestFrequency {
    MONTHLY(12),
    QUARTERLY(4),
    ANNUALLY(1),
    AT_MATURITY(0);

    private final int compoundingFrequency;

    InterestFrequency(int compoundingFrequency) {
        this.compoundingFrequency = compoundingFrequency;
    }

}
