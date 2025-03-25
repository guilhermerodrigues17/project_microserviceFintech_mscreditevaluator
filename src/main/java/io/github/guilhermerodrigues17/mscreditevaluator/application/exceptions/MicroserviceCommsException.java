package io.github.guilhermerodrigues17.mscreditevaluator.application.exceptions;

import lombok.Getter;

public class MicroserviceCommsException extends Exception {

    @Getter
    private Integer status;

    public MicroserviceCommsException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
