package br.com.fiap.trabalho.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlunoNaoEncontrado extends ResponseStatusException {
    public AlunoNaoEncontrado() {
        super(HttpStatus.NOT_FOUND, "Aluno não encontrado");
    }
}
