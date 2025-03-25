package io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions;

public class ClientDataNotFoundException extends Exception {
    public ClientDataNotFoundException() {
        super("Client data not found to this CPF!");
    }
}
