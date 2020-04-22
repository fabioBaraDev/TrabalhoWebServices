package br.com.fiap.trabalho.service;

import org.springframework.http.ResponseEntity;

public interface ExtratoService {

	public ResponseEntity getSaldoById(Integer idCreditoAluno, Long numeroCartao);
	
	
}
