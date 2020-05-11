package br.com.fiap.trabalho.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.dto.StatusDTO;
import br.com.fiap.trabalho.entity.Aluno;
import br.com.fiap.trabalho.repository.AlunoRepository;
import br.com.fiap.trabalho.service.AlunoService;
import br.com.fiap.trabalho.service.CreditoService;
import br.com.fiap.trabalho.service.EnderecoService;
import br.com.fiap.trabalho.service.ValidaCEP;

import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CreditoService creditoService;

	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ValidaCEP validaCEP;

	public AlunoDTO save(CreditoDTO creditoDTO) throws Exception {
		
		String cep = creditoDTO.getAluno().getEndereco().getCep();
		
		if(!validaCEP.validar(cep)) {
			return null;
		}
		
		Aluno aluno = createAluno(creditoDTO.getAluno());
		aluno = alunoRepository.save(aluno);
		creditoDTO.getAluno().setId(aluno.getId());

		enderecoService.salvar(creditoDTO.getAluno().getEndereco(), aluno.getId());

		if (!creditoService.salvar(creditoDTO, true).isPresent()) {
			throw new Exception("Erro ao cadastrar saldo");
		}

		return createAlunoDTO(aluno);
	}

	private Aluno createAluno(AlunoDTO alunoDTO) {
		return new Aluno(alunoDTO.getNome(), alunoDTO.getNumeroCartao());
	}

	private AlunoDTO createAlunoDTO(Aluno aluno) {
		return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getNumeroCartao(),
				enderecoService.getEnderecoByID(aluno.getId()));
	}

	public String delete(Integer id) {
		Optional<Aluno> aluno = getAluno(id);
		alunoRepository.delete(aluno.get());
		creditoService.delete(id);
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

	public StatusDTO getAlunoStatus(Integer id) {
		return creditoService.getStatusCredito(id);
	}
}
