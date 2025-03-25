package io.github.guilhermerodrigues17.mscreditevaluator.application;

import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientCards;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientData;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientSituation;
import io.github.guilhermerodrigues17.mscreditevaluator.infra.clients.CardsResourceClient;
import io.github.guilhermerodrigues17.mscreditevaluator.infra.clients.ClientsResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientsResourceClient clientsResourceClient;
    private final CardsResourceClient cardsResourceClient;

    public ClientSituation getClientSituation(String cpf) {
        ResponseEntity<ClientData> clientData = clientsResourceClient.getClientByCpf(cpf);
        ResponseEntity<List<ClientCards>> clientCards = cardsResourceClient.getCardsByCpf(cpf);

        return ClientSituation.builder()
                .client(clientData.getBody())
                .cards(clientCards.getBody())
                .build();
    }
}
