package br.com.fiap.trabalho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.StatusDTO;

public interface AlunoService {

	public AlunoDTO save(AlunoDTO alunoDTO) throws Exception;

	public String delete(Integer id);

	public List<AlunoDTO> getAll();
	
	public Optional<AlunoDTO> getById(Integer id);
	
	public List<AlunoDTO> getByName(String nome);
	
	public StatusDTO getAlunoStatus(Integer id);
	
	public ResponseEntity<String> setStatusAluno(Integer id, Boolean status);

}
