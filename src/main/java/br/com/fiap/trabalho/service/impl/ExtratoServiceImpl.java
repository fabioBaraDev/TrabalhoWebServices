package br.com.fiap.trabalho.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.repository.ExtratoRepository;
import br.com.fiap.trabalho.service.ExtratoService;

@Service
public class ExtratoServiceImpl implements ExtratoService{
	
	@Autowired
	private ExtratoRepository extrato;

	public ResponseEntity getSaldoById(Integer idCreditoAluno, Long numeroCartao) {
	
		return new ResponseEntity(extrato.getById(idCreditoAluno, numeroCartao), HttpStatus.OK);
	}

}
