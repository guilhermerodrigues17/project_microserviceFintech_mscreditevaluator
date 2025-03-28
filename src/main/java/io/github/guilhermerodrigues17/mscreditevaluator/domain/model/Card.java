package io.github.guilhermerodrigues17.mscreditevaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Card {

    private UUID id;
    private String name;
    private String brand;
    private BigDecimal creditLimit;
}
