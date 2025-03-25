package io.github.guilhermerodrigues17.mscreditevaluator.application;

import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.ClientDataNotFoundException;
import io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions.MicroserviceCommsException;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientEvaluationResponse;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.ClientSituation;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.EvaluationData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> clientEvaluation(@RequestBody EvaluationData data) {
        try {
            ClientEvaluationResponse evaluation = creditEvaluatorService.clientEvaluation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(evaluation);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommsException e) {
            return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(e.getMessage());
        }
    }
}
