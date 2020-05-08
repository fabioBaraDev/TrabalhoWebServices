package br.com.fiap.trabalho.controller;

import br.com.fiap.trabalho.dto.CompraDTO;
import br.com.fiap.trabalho.exceptions.AlunoNaoEncontrado;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.exceptions.SemSaldoCadastrado;
import br.com.fiap.trabalho.service.CreditoService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("transacoes")
public class TransacoesController {

	@Autowired
	private CreditoService credito;
	
	@PostMapping("/credito/debitar")
	public ResponseEntity<String> debitarCredito(@RequestBody CreditoDTO creditoDTO) {
		try {
			return credito.debitar(creditoDTO);
		} catch (SemSaldoCadastrado e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/credito/comprar")
	public ResponseEntity<String> comprarCredito(@RequestBody CompraDTO compraDTO) {
		try {
			return credito.comprarCredito(compraDTO);
		} catch (ResponseStatusException e) {
			return new ResponseEntity(e.getMessage(), e.getStatus());
		} catch (Exception e) {
			return new ResponseEntity("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/credito/adicionar")
	public ResponseEntity<String> adicionarCredito(@RequestBody CreditoDTO creditoDTO) {
		try {
			return credito.adicionar(creditoDTO);
		} catch (SemSaldoCadastrado e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/credito/ativar")
	public ResponseEntity<String> ativarCartao(@RequestBody CreditoDTO creditoDTO) {
		try {
			return credito.ativarCartao(creditoDTO);
		} catch (SemSaldoCadastrado e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/credito/desativar")
	public ResponseEntity<String> desativarCartao(@RequestBody CreditoDTO creditoDTO) {
		try {
			return credito.desativarCartao(creditoDTO);
		} catch (SemSaldoCadastrado e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/credito/saldo/id/{id}")
	public ResponseEntity getSaldoById(@PathVariable Integer id) {
		try {
			return new ResponseEntity(credito.getSaldoById(id), HttpStatus.OK);
		} catch (SemSaldoCadastrado e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/credito/saldo/cartao/{cartao}")
	public ResponseEntity getSaldoByCartao(@PathVariable Long cartao) {
		try {
			return new ResponseEntity(credito.getSaldoByCartao(cartao), HttpStatus.OK);
		} catch (SemSaldoCadastrado e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity("Ocorreu um erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
