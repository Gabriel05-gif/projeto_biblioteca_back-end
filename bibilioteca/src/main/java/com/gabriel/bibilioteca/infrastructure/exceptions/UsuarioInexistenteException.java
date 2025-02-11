package com.gabriel.bibilioteca.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioInexistenteException extends RuntimeException {
    public UsuarioInexistenteException() {
        super();
    }
}
