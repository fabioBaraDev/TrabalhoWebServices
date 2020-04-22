package br.com.fiap.trabalho.service;

import java.util.List;
import java.util.Optional;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;

public interface AlunoService {

	public AlunoDTO save(CreditoDTO creditoDTO) throws Exception;

	public String delete(Integer id);

	public List<AlunoDTO> getAll();
	
	public Optional<AlunoDTO> getById(Integer id);
	
	public List<AlunoDTO> getByName(String nome);

}
