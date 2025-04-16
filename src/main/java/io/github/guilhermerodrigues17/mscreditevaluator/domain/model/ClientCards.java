package io.github.guilhermerodrigues17.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCards {

    private String name;
    private String brand;
    private BigDecimal approvedCreditLimit;
}
