package br.com.fiap.trabalho.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.entity.Aluno;
import br.com.fiap.trabalho.repository.AlunoRepository;
import br.com.fiap.trabalho.service.AlunoService;
import br.com.fiap.trabalho.service.CreditoService;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CreditoService creditoService;

	public AlunoDTO save(CreditoDTO creditoDTO) throws Exception {
		Aluno aluno = createAluno(creditoDTO.getAluno());
		aluno = alunoRepository.save(aluno);
		creditoDTO.getAluno().setId(aluno.getId());
		
		if(!creditoService.salvar(creditoDTO, true).isPresent()) {
			throw new Exception("Erro ao cadastrar saldo");
		}
		
		return createAlunoDTO(aluno);
	}

	private Aluno createAluno(AlunoDTO alunoDTO) {
		return new Aluno(alunoDTO.getNome(), alunoDTO.getNumeroCartao());
	}
	
	private AlunoDTO createAlunoDTO(Aluno aluno) {
		return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getNumeroCartao());
	}

	public String delete(Integer id) {
		Optional<Aluno> aluno = getAluno(id);
		alunoRepository.delete(aluno.get());
		creditoService.delete(id);
		return "Deletado com sucesso";
	}

	private Optional<Aluno> getAluno(Integer id) {
		return alunoRepository.findById(id);
	}

	public List<AlunoDTO> getAll() { 

		List<Aluno> alunos = alunoRepository.findAll();

		return alunos.stream().map((x) -> {
			return new AlunoDTO(x.getId(), x.getNome(), x.getNumeroCartao());
		}).collect(Collectors.toList());

	}

	public Optional<AlunoDTO> getById(Integer id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		Optional<AlunoDTO> alunoDTO = Optional
				.of(new AlunoDTO(aluno.get().getId(), aluno.get().getNome(), aluno.get().getNumeroCartao()));
		return alunoDTO;
	}

	public List<AlunoDTO> getByName(String nome) {

		List<AlunoDTO> alunoDTO = alunoRepository.findByName(nome.toUpperCase()).stream().map((x) -> {
			return new AlunoDTO(x.getId(), x.getNome(), x.getNumeroCartao());
		}).collect(Collectors.toList());

		return alunoDTO;
	}

}
