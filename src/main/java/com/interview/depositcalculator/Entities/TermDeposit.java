package com.interview.depositcalculator.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TermDeposit {
    //validate the inputs using annotation of validation
    @NotNull(message = "Principal amount cannot be null")
    @DecimalMin(value = "0.00", message = "Principal amount must be non-negative")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal principal;

    @NotNull(message = "Annual Rate cannot be null")
    @DecimalMin(value = "0.00", message = "Annual Rate must be non-negative")
    private BigDecimal annualRate;

    @NotNull(message = "Investment Months cannot be null")
    @DecimalMin(value = "0", inclusive = false, message = "Investment Months must be greater than zero")
    private BigDecimal investmentMonths;

    @NotNull(message = "Interest frequency cannot be null")
    private InterestFrequency interestFrequency;

}
