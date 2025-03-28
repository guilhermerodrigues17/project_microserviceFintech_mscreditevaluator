package io.github.guilhermerodrigues17.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CardIssuanceSolicitationData {
    private UUID cardId;
    private String cpf;
    private String address;
    private BigDecimal approvedCreditLimit;
}
