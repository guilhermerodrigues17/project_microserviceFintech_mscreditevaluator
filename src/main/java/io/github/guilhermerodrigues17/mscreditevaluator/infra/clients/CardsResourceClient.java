package io.github.guilhermerodrigues17.mscreditevaluator.infra.clients;

import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientCards;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCards>> getCardsByCpf(@RequestParam String cpf);
}
