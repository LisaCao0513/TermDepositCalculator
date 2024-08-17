package com.interview.depositcalculator.controller;

import com.interview.depositcalculator.Entities.TermDeposit;
import com.interview.depositcalculator.dto.TermDepositResponse;
import com.interview.depositcalculator.service.TermDepositService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/term-deposit")
public class TermDepositController {
    private final TermDepositService termDepositService;

    public TermDepositController(TermDepositService termDepositService) {
        this.termDepositService = termDepositService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<TermDepositResponse> calculateTermDeposit(@RequestBody @Valid TermDeposit request) {
        try {
            // Call the service to perform the calculation
            BigDecimal finalBalance = termDepositService.calculateTermDeposit(request);
            // Return the response
            return ResponseEntity.ok(new TermDepositResponse(finalBalance));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new TermDepositResponse(null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TermDepositResponse(null));
        }
    }
}
