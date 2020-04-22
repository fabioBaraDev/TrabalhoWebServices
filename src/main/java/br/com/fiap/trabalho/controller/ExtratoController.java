package br.com.fiap.trabalho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.trabalho.service.ExtratoService;

@RestController
@RequestMapping("transacoes")
public class ExtratoController {

	@Autowired
	private ExtratoService extrato;
	
	@GetMapping("/extrato/{id}/{numeroCartao}")
	public ResponseEntity getSaldoById(@PathVariable Integer id, @PathVariable Long numeroCartao) {
		return extrato.getSaldoById(id, numeroCartao);
	}
}
