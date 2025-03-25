package io.github.guilhermerodrigues17.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {

    private String card;
    private String brand;
    private BigDecimal approvedCreditLimit;
}
