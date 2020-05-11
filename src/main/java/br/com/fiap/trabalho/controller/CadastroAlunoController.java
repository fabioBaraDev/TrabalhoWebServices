package br.com.fiap.trabalho.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.EnderecoDTO;
import br.com.fiap.trabalho.dto.IdentificadoAlunoDTO;
import br.com.fiap.trabalho.dto.StatusDTO;
import br.com.fiap.trabalho.service.AlunoService;
import br.com.fiap.trabalho.service.EnderecoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("cadastro")
public class CadastroAlunoController {
	
	@Autowired
	private EnderecoService service;
	
	@Autowired
	private AlunoService alunoService;

	@GetMapping("/endereco/{id}")
	public EnderecoDTO getEnderecoById(@PathVariable Integer id) {
		return service.getEnderecoByID(id);
	}
	
	@PostMapping("/alunos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> save(@RequestBody AlunoDTO alunoDTO) {
		try {
			AlunoDTO res = alunoService.save(alunoDTO);
			
			if(res == null) {
				return new ResponseEntity<Object>("CEP Invalido", HttpStatus.NOT_ACCEPTABLE);
			}
			return new ResponseEntity<Object>(alunoService.save(alunoDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		alunoService.delete(id);
		return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping("/alunos/nome/{nome}")
	public List<AlunoDTO> getByName(@PathVariable String nome) {
		return alunoService.getByName(nome);
	}
	
	@GetMapping("alunos/status/{id}")
	public StatusDTO getStatusById(@PathVariable Integer id) {
		return alunoService.getAlunoStatus(id);
	}

	@PostMapping("/alunos/ativar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> ativarAluno(@RequestBody IdentificadoAlunoDTO idAluno){
		return alunoService.setStatusAluno(idAluno.getId(), true);
	}
	
	@PostMapping("/alunos/desativar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> desativarAluno(@RequestBody IdentificadoAlunoDTO idAluno){
		return alunoService.setStatusAluno(idAluno.getId(), false);
	}
	
}
