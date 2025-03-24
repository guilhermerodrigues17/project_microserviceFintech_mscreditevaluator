package io.github.guilhermerodrigues17.mscreditevaluator.application;

import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientData;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientSituation;
import io.github.guilhermerodrigues17.mscreditevaluator.infra.clients.ClientsResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientsResourceClient clientsResourceClient;

    public ClientSituation getClientSituation(String cpf) {
        ResponseEntity<ClientData> clientData = clientsResourceClient.getClientByCpf(cpf);

        return ClientSituation.builder()
                .client(clientData.getBody())
                .build();
    }
}
