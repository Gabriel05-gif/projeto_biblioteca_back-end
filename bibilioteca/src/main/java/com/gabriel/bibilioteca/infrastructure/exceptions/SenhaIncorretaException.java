package com.gabriel.bibilioteca.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SenhaIncorretaException extends RuntimeException {
    public SenhaIncorretaException() {
        super();
    }
}
