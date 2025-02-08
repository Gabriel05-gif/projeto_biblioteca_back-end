package com.gabriel.bibilioteca.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LivroDuplicadoException extends RuntimeException {
    public LivroDuplicadoException(String message) {
        super(message);
    }
}
