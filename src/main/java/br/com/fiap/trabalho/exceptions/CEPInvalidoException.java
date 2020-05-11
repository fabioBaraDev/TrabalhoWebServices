package br.com.fiap.trabalho.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CEPInvalidoException extends ResponseStatusException {
    public CEPInvalidoException() {
        super(HttpStatus.BAD_REQUEST, "CEP Invalido");
    }
}