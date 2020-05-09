package br.com.fiap.trabalho.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.dto.StatusDTO;
import br.com.fiap.trabalho.service.AlunoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("cadastro")
public class CadastroAlunoController {
	
	@Autowired
	private AlunoService alunoService;

	@PostMapping("/alunos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity save(@RequestBody CreditoDTO creditoDTO) {
		try {
			return new ResponseEntity(alunoService.save(creditoDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/alunos")
	public List<AlunoDTO> getAll() {
		return alunoService.getAll();
	}

	@GetMapping("/alunos/{id}")
	public Optional<AlunoDTO> getById(@PathVariable Integer id) {
		return alunoService.getById(id);
	}

	@DeleteMapping("/alunos/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		alunoService.delete(id);
		return new ResponseEntity("Deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/alunos/nome/{nome}")
	public List<AlunoDTO> getByName(@PathVariable String nome) {
		return alunoService.getByName(nome);
	}
	
	
}
