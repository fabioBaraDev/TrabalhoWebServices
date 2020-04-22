package br.com.fiap.trabalho.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.entity.Credito;
import br.com.fiap.trabalho.entity.Extrato;
import br.com.fiap.trabalho.enumerator.Operacao;
import br.com.fiap.trabalho.exceptions.SemSaldoCadastrado;
import br.com.fiap.trabalho.repository.CreditoRepository;
import br.com.fiap.trabalho.repository.ExtratoRepository;
import br.com.fiap.trabalho.service.AlunoService;
import br.com.fiap.trabalho.service.CreditoService;

@Service
public class CreditoServiceImpl implements CreditoService {

	@Autowired
	CreditoRepository creditoRepository;

	@Autowired
	AlunoService alunoService;

	@Autowired
	ExtratoRepository extratoRepository;

	public ResponseEntity debitar(CreditoDTO creditoRequest) throws Exception {
		return persistir(creditoRequest, Operacao.DEBITO);
	}

	public ResponseEntity adicionar(CreditoDTO creditoRequest) throws Exception {
		return persistir(creditoRequest, Operacao.CREDITO);
	}

	public ResponseEntity ativarCartao(CreditoDTO creditDTO) throws Exception {
		return persistirClienteAtivo(creditDTO, true);
	}

	public ResponseEntity desativarCartao(CreditoDTO creditDTO) throws Exception {
		return persistirClienteAtivo(creditDTO, false);
	}

	public CreditoDTO getSaldoById(Integer id) throws Exception {
		Credito saldo = getCredito(id);

		Optional<AlunoDTO> aluno = alunoService.getById(saldo.getId());

		CreditoDTO credito = new CreditoDTO(aluno.get(), saldo.getSaldo());
		return credito;
	}

	public CreditoDTO getSaldoByCartao(Long numeroCartao) throws Exception {

		Credito saldo = getCreditoByCartao(numeroCartao);
		Optional<AlunoDTO> aluno = alunoService.getById(saldo.getId());

		CreditoDTO credito = new CreditoDTO(aluno.get(), saldo.getSaldo());
		return credito;
	}

	public Optional salvar(CreditoDTO creditDTO, Boolean ativo) {

		Credito credito = new Credito(creditDTO);
		credito.setAlunoCreditoAtivo(ativo);

		return Optional.of(creditoRepository.save(credito));

	}

	public void delete(Integer id) {
		creditoRepository.deleteById(id);
	}

	private ResponseEntity persistir(CreditoDTO creditoRequest, Operacao operacao) throws Exception {

		Optional<AlunoDTO> aluno = alunoService.getById(creditoRequest.getAluno().getId());

		if (!aluno.isPresent()) {
			return new ResponseEntity<>("Aluno não encontrado", HttpStatus.NOT_FOUND);
		}

		Integer idAluno = creditoRequest.getAluno().getId();
		CreditoDTO creditoDataBase = getSaldoById(idAluno);
		
		if (!getCredito(idAluno).getAlunoCreditoAtivo()) {
			return new ResponseEntity<>("Aluno com conta inativa", HttpStatus.FORBIDDEN);
		}

		if (creditoDataBase.getAluno().getNumeroCartao().equals(creditoRequest.getAluno().getNumeroCartao())) {

			Double saldoOperacao = creditoRequest.getSaldo();
			Double resultado = null;
			if (operacao.equals(Operacao.DEBITO)) {
				resultado = (creditoDataBase.getSaldo() - saldoOperacao);
			} else {
				resultado = (creditoDataBase.getSaldo() + saldoOperacao);
			}

			creditoRequest.setSaldo(resultado);

			Credito creditoInput = new Credito(creditoRequest);
			creditoInput.setAlunoCreditoAtivo(true);

			creditoRepository.save(creditoInput);
			salvaExtrato(creditoInput, saldoOperacao, operacao);

		} else {
			return new ResponseEntity<>("Esse aluno não possui esse cartão", HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(operacao.toString() + " com sucesso!", HttpStatus.OK);
	}

	private void salvaExtrato(Credito credito, Double valorOperacao, Operacao operacao) {
		Extrato extrato = new Extrato(credito, valorOperacao, operacao);
		extratoRepository.save(extrato);
	}

	private Credito getCredito(Integer id) throws SemSaldoCadastrado {
		Optional<Credito> credito = creditoRepository.findSaldoById(id);

		if (!credito.isPresent()) {
			throw new SemSaldoCadastrado("Este cliente não possui saldo cadastrado!");
		}

		return credito.get();
	}

	private Credito getCreditoByCartao(Long numeroCartao) throws SemSaldoCadastrado {
		Optional<Credito> credito = creditoRepository.findSaldoByCartao(numeroCartao);

		if (!credito.isPresent()) {
			throw new SemSaldoCadastrado("Este cliente não possui saldo cadastrado!");
		}

		return credito.get();
	}

	private ResponseEntity persistirClienteAtivo(CreditoDTO creditoRequest, Boolean ativo) throws Exception {

		Optional<AlunoDTO> aluno = alunoService.getById(creditoRequest.getAluno().getId());

		if (!aluno.isPresent()) {
			return new ResponseEntity<>("Aluno não encontrado", HttpStatus.NOT_FOUND);
		}

		Credito creditoDataBase = getCredito(creditoRequest.getAluno().getId());

		creditoDataBase.setAlunoCreditoAtivo(ativo);

		creditoRepository.save(creditoDataBase);

		String resposta = ativo ? "Ativado" : "Desativado";
		return new ResponseEntity<>("Cliente " + resposta + " com sucesso!", HttpStatus.OK);
	}
}