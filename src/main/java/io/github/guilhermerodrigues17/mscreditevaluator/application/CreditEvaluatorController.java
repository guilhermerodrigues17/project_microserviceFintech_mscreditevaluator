package io.github.guilhermerodrigues17.mscreditevaluator.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-evaluator")
public class CreditEvaluatorController {

    @GetMapping
    public String status() {
        return "ok";
    }
}
