package io.github.guilhermerodrigues17.mscreditevaluator.application;

import feign.FeignException;
import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.ClientDataNotFoundException;
import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.MicroserviceCommsException;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientCards;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientData;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientSituation;
import io.github.guilhermerodrigues17.mscreditevaluator.infra.clients.CardsResourceClient;
import io.github.guilhermerodrigues17.mscreditevaluator.infra.clients.ClientsResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientsResourceClient clientsResourceClient;
    private final CardsResourceClient cardsResourceClient;

    public ClientSituation getClientSituation(String cpf) throws ClientDataNotFoundException, MicroserviceCommsException {
        try {
            ResponseEntity<ClientData> clientData = clientsResourceClient.getClientByCpf(cpf);
            ResponseEntity<List<ClientCards>> clientCards = cardsResourceClient.getCardsByCpf(cpf);

            return ClientSituation.builder()
                    .client(clientData.getBody())
                    .cards(clientCards.getBody())
                    .build();
        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new ClientDataNotFoundException();
            }
            throw new MicroserviceCommsException(e.getMessage(), status);
        }
    }
}
