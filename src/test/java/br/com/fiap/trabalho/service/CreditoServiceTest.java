package br.com.fiap.trabalho.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.entity.Credito;
import br.com.fiap.trabalho.entity.Extrato;
import br.com.fiap.trabalho.enumerator.Operacao;
import br.com.fiap.trabalho.exceptions.SemSaldoCadastrado;
import br.com.fiap.trabalho.repository.CreditoRepository;
import br.com.fiap.trabalho.repository.ExtratoRepository;
import br.com.fiap.trabalho.service.impl.CreditoServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CreditoServiceTest {

	@InjectMocks
	private CreditoServiceImpl service;

	@Mock
	CreditoRepository creditoRepository;

	@Mock
	AlunoService alunoService;

	@Mock
	ExtratoRepository extratoRepository;

	@Test
	public void debitarAlunoContaInativa() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);

		Credito credito = new Credito(creditoDTO);
		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(creditoDTO.getAluno()));
		Mockito.when(creditoRepository.findSaldoById(Mockito.anyInt())).thenReturn(Optional.of(credito));

		ResponseEntity res = service.debitar(creditoDTO);

		assertEquals(HttpStatus.FORBIDDEN, res.getStatusCode());
	}

	@Test
	public void debitar() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);

		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);
		Extrato extrato = new Extrato(credito, 1.1, Operacao.DEBITO);
		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(creditoDTO.getAluno()));
		Mockito.when(creditoRepository.findSaldoById(Mockito.anyInt())).thenReturn(Optional.of(credito));
		Mockito.when(creditoRepository.save(Mockito.any(Credito.class))).thenReturn(credito);
		Mockito.when(extratoRepository.save(Mockito.any(Extrato.class))).thenReturn(extrato);

		ResponseEntity res = service.debitar(creditoDTO);

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	public void adicionar() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);

		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);
		Extrato extrato = new Extrato(credito, 1.1, Operacao.DEBITO);
		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(creditoDTO.getAluno()));
		Mockito.when(creditoRepository.findSaldoById(Mockito.anyInt())).thenReturn(Optional.of(credito));
		Mockito.when(creditoRepository.save(Mockito.any(Credito.class))).thenReturn(credito);
		Mockito.when(extratoRepository.save(Mockito.any(Extrato.class))).thenReturn(extrato);

		ResponseEntity res = service.adicionar(creditoDTO);

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	public void alunoInativo() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);

		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);
		AlunoDTO aluno = new AlunoDTO(122, "Pedor", 22222L);

		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(aluno));
		Mockito.when(creditoRepository.findSaldoById(Mockito.anyInt())).thenReturn(Optional.of(credito));
		ResponseEntity res = service.adicionar(creditoDTO);

		assertEquals(HttpStatus.FORBIDDEN, res.getStatusCode());
	}

	@Test
	public void alunoNaoEncontrado() throws Exception {

		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);

		ResponseEntity res = service.adicionar(creditoDTO);

		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
	}

	@Test
	public void saldoNaoEncontrado() throws Exception {

		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);

		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);
		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(creditoDTO.getAluno()));
		Mockito.when(creditoRepository.findSaldoById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));

		boolean tryC = false;
		try {
			service.adicionar(creditoDTO);
		} catch (SemSaldoCadastrado e) {
			tryC = true;
		}

		assertTrue(tryC);
	}

	@Test
	public void ativarCartao() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);

		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(creditoDTO.getAluno()));
		Mockito.when(creditoRepository.findSaldoById(Mockito.anyInt())).thenReturn(Optional.of(credito));
		Mockito.when(creditoRepository.save(Mockito.any(Credito.class))).thenReturn(credito);

		ResponseEntity res = service.ativarCartao(creditoDTO);
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	public void desativarCartao() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);

		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(creditoDTO.getAluno()));
		Mockito.when(creditoRepository.findSaldoById(Mockito.anyInt())).thenReturn(Optional.of(credito));
		Mockito.when(creditoRepository.save(Mockito.any(Credito.class))).thenReturn(credito);

		ResponseEntity res = service.desativarCartao(creditoDTO);
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	public void getSaldoByCartao() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);
		Mockito.when(creditoRepository.findSaldoByCartao(Mockito.anyLong())).thenReturn(Optional.of(credito));
		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(Optional.of(creditoDTO.getAluno()));

		CreditoDTO res = service.getSaldoByCartao(1111L);
		assertNotNull(res);
	}

	@Test
	public void getSaldoByCartaoErro() throws Exception {
		Mockito.when(creditoRepository.findSaldoByCartao(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

		boolean tryC = false;
		try {
			service.getSaldoByCartao(1111L);
		} catch (SemSaldoCadastrado e) {
			tryC = true;
		}

		assertTrue(tryC);
	}

	@Test
	public void salvar() {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);
		Mockito.when(creditoRepository.save(Mockito.any(Credito.class))).thenReturn(credito);

		Optional res = service.salvar(creditoDTO, true);

		assertTrue(res.isPresent());

	}

	@Test
	public void delete() {
		Mockito.doNothing().when(creditoRepository).deleteById(Mockito.anyInt());
		service.delete(1);
	}

}
