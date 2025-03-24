package io.github.guilhermerodrigues17.mscreditevaluator.application;

import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-evaluator")
@RequiredArgsConstructor
public class CreditEvaluatorController {

    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "client-situation", params = "cpf")
    public ResponseEntity<ClientSituation> getClientSituation(@RequestParam String cpf) {
        ClientSituation clientSituation = creditEvaluatorService.getClientSituation(cpf);
        return ResponseEntity.ok(clientSituation);
    }
}
