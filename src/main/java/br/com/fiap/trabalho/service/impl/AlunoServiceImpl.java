package br.com.fiap.trabalho.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.StatusDTO;
import br.com.fiap.trabalho.entity.Aluno;
import br.com.fiap.trabalho.repository.AlunoRepository;
import br.com.fiap.trabalho.service.AlunoService;
import br.com.fiap.trabalho.service.EnderecoService;
import br.com.fiap.trabalho.service.ValidaCEP;

import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ValidaCEP validaCEP;

	public AlunoDTO save(AlunoDTO alunoDTO) throws Exception {
		
		String cep = alunoDTO.getEndereco().getCep();
		
		if(!validaCEP.validar(cep)) {
			return null;
		}
		
		Aluno aluno = createAluno(alunoDTO);
		aluno = alunoRepository.save(aluno);
		alunoDTO.setId(aluno.getId());

		enderecoService.salvar(alunoDTO.getEndereco(), aluno.getId());

		return alunoDTO;
	}

	private Aluno createAluno(AlunoDTO alunoDTO) {
		return new Aluno(alunoDTO.getNome(), alunoDTO.getNumeroCartao());
	}

	public String delete(Integer id) {
		Optional<Aluno> aluno = getAluno(id);
		alunoRepository.delete(aluno.get());
		return "Deletado com sucesso";
	}

	private Optional<Aluno> getAluno(Integer id) {

		return Optional.ofNullable(
				alunoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	public List<AlunoDTO> getAll() {

		List<Aluno> alunos = alunoRepository.findAll();

		return alunos.stream().map((x) -> {
			return new AlunoDTO(x.getId(), x.getNome(), x.getNumeroCartao(),
					enderecoService.getEnderecoByID(x.getId()));
		}).collect(Collectors.toList());

	}

	public Optional<AlunoDTO> getById(Integer id) {
		Optional<Aluno> aluno = Optional.ofNullable(
				alunoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		Optional<AlunoDTO> alunoDTO = Optional.of(new AlunoDTO(	aluno.get().getId(), 
																aluno.get().getNome(),
																aluno.get().getNumeroCartao(),
																enderecoService.getEnderecoByID(aluno.get().getId())));
		return alunoDTO;
	}

	public List<AlunoDTO> getByName(String nome) {

		List<AlunoDTO> alunoDTO = alunoRepository.findByName(nome.toUpperCase()).stream().map((x) -> {
			return new AlunoDTO(x.getId(), x.getNome(), x.getNumeroCartao(), enderecoService.getEnderecoByID(x.getId()));
		}).collect(Collectors.toList());

		return alunoDTO;
	}

	@Override
	public StatusDTO getAlunoStatus(Integer id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		StatusDTO status = new StatusDTO();
		status.setStatusAluno(aluno.get().getStatusAluno());
		return status;
	}
}
