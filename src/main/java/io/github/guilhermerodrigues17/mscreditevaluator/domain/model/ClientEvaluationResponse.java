package io.github.guilhermerodrigues17.mscreditevaluator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientEvaluationResponse {

    private List<ApprovedCard> cards;
}
