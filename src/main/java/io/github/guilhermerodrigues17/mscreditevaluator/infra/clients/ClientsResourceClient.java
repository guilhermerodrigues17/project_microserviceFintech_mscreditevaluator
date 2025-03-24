package io.github.guilhermerodrigues17.mscreditevaluator.infra.clients;

import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8080", path = "/clients", name = "clientsResourceClient")
public interface ClientsResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> getClientByCpf(@RequestParam String cpf);
}
