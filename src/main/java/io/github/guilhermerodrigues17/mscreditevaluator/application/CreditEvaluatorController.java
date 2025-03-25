package io.github.guilhermerodrigues17.mscreditevaluator.application;

import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.ClientDataNotFoundException;
import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.MicroserviceCommsException;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> getClientSituation(@RequestParam String cpf) {
        try {
            ClientSituation clientSituation = creditEvaluatorService.getClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommsException e) {
            return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(e.getMessage());
        }
    }
}
