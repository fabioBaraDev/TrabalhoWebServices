package br.com.fiap.trabalho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.trabalho.dto.EnderecoDTO;
import br.com.fiap.trabalho.exceptions.CEPInvalidoException;
import br.com.fiap.trabalho.service.EnderecoService;

@RestController
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@GetMapping("/endereco/{id}")
	public EnderecoDTO getEnderecoById(@PathVariable Integer id) {
		return service.getEnderecoByID(id);
	}
	
	@PostMapping("/endereco/")
	public ResponseEntity<String> salvaEndereco(@RequestBody EnderecoDTO endereco) throws CEPInvalidoException{
		try {
			service.salvar(endereco, endereco.getId());	
			return new ResponseEntity<String>("Adicionado com sucesso", HttpStatus.ACCEPTED);
		} catch (CEPInvalidoException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao adicionar", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}
