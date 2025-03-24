package io.github.guilhermerodrigues17.mscreditevaluator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSituation {

    private ClientData client;
    private List<ClientCards> cards;
}
