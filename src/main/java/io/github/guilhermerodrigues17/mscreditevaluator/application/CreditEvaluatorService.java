package io.github.guilhermerodrigues17.mscreditevaluator.application;

import feign.FeignException;
import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.ClientDataNotFoundException;
import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.MicroserviceCommsException;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.*;
import io.github.guilhermerodrigues17.mscreditevaluator.infra.clients.CardsResourceClient;
import io.github.guilhermerodrigues17.mscreditevaluator.infra.clients.ClientsResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    public ClientEvaluationResponse clientEvaluation(String cpf, Long income)
            throws ClientDataNotFoundException, MicroserviceCommsException {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientsResourceClient.getClientByCpf(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsResourceClient.getCardsByIncome(income);

            List<Card> cards = cardsResponse.getBody();
            ClientData clientData = clientDataResponse.getBody();

            List<ApprovedCard> approvedCards = cards.stream().map(card -> {


                BigDecimal creditLimitBasis = card.getCreditLimit();
                BigDecimal age = BigDecimal.valueOf(clientData.getAge());
                BigDecimal factor = age.divide(BigDecimal.TEN);
                BigDecimal approvedCreditLimit = factor.multiply(creditLimitBasis);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setBrand(card.getBrand());
                approvedCard.setApprovedCreditLimit(approvedCreditLimit);

                return approvedCard;
            }).toList();

            return new ClientEvaluationResponse(approvedCards);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new ClientDataNotFoundException();
            }
            throw new MicroserviceCommsException(e.getMessage(), status);
        }
    }
}
