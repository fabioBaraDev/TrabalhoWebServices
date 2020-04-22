package br.com.fiap.trabalho.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import br.com.fiap.trabalho.controller.TransacoesController;
import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.exceptions.SemSaldoCadastrado;
import br.com.fiap.trabalho.service.CreditoService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TransacoesControllerTest {

	@InjectMocks
	private TransacoesController controller;

	@Mock
	private CreditoService credito;

	@Test
	public void debitarCredito() throws Exception {
		
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		ResponseEntity resEntity = new ResponseEntity("Teste", HttpStatus.OK);
		Mockito.when(credito.debitar(Mockito.any(CreditoDTO.class))).thenReturn(resEntity );
		
		ResponseEntity res = controller.debitarCredito(creditoDTO);
		
		assertNotNull(res);
	}
	
	@Test
	public void adicionarCredito() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		ResponseEntity resEntity = new ResponseEntity("Teste", HttpStatus.OK);
		Mockito.when(credito.adicionar(Mockito.any(CreditoDTO.class))).thenReturn(resEntity );
		
		ResponseEntity res = controller.adicionarCredito(creditoDTO);
		
		assertNotNull(res);
	}
	
	@Test
	public void ativarCartao() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		ResponseEntity resEntity = new ResponseEntity("Teste", HttpStatus.OK);
		Mockito.when(credito.ativarCartao(Mockito.any(CreditoDTO.class))).thenReturn(resEntity );
		
		ResponseEntity res = controller.ativarCartao(creditoDTO);
		
		assertNotNull(res);
	}
	
	@Test
	public void desativarCartao() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		ResponseEntity resEntity = new ResponseEntity("Teste", HttpStatus.OK);
		Mockito.when(credito.desativarCartao(Mockito.any(CreditoDTO.class))).thenReturn(resEntity );
		
		ResponseEntity res = controller.desativarCartao(creditoDTO);
		
		assertNotNull(res);
	}
	
	@Test
	public void getSaldoById() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Mockito.when(credito.getSaldoById(Mockito.anyInt())).thenReturn(creditoDTO);
		
		ResponseEntity res = controller.getSaldoById(1);
		
		assertNotNull(res);
	}
	
	@Test
	public void getSaldoByCartao() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Mockito.when(credito.getSaldoByCartao(Mockito.anyLong())).thenReturn(creditoDTO);
		
		ResponseEntity res = controller.getSaldoByCartao(1L);
		
		assertNotNull(res);
	}

	@Test
	public void testException() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Mockito.when(credito.debitar(Mockito.any(CreditoDTO.class))).thenThrow(SemSaldoCadastrado.class);
		Mockito.when(credito.adicionar(Mockito.any(CreditoDTO.class))).thenThrow(SemSaldoCadastrado.class);
		Mockito.when(credito.ativarCartao(Mockito.any(CreditoDTO.class))).thenThrow(SemSaldoCadastrado.class);
		Mockito.when(credito.desativarCartao(Mockito.any(CreditoDTO.class))).thenThrow(SemSaldoCadastrado.class);
		Mockito.when(credito.getSaldoById(Mockito.anyInt())).thenThrow(SemSaldoCadastrado.class);
		Mockito.when(credito.getSaldoByCartao(Mockito.anyLong())).thenThrow(SemSaldoCadastrado.class);
		
		ResponseEntity res = controller.debitarCredito(creditoDTO);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, res.getStatusCode());
		
		res = controller.adicionarCredito(creditoDTO);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, res.getStatusCode());
		
		res = controller.ativarCartao(creditoDTO);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, res.getStatusCode());
		
		res = controller.desativarCartao(creditoDTO);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, res.getStatusCode());
		
		res = controller.getSaldoById(1);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, res.getStatusCode());
		
		res = controller.getSaldoByCartao(1L);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, res.getStatusCode());
		
	}
	
	@Test
	public void testException2() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Mockito.when(credito.debitar(Mockito.any(CreditoDTO.class))).thenThrow(Exception.class);
		Mockito.when(credito.adicionar(Mockito.any(CreditoDTO.class))).thenThrow(Exception.class);
		Mockito.when(credito.ativarCartao(Mockito.any(CreditoDTO.class))).thenThrow(Exception.class);
		Mockito.when(credito.desativarCartao(Mockito.any(CreditoDTO.class))).thenThrow(Exception.class);
		Mockito.when(credito.getSaldoById(Mockito.anyInt())).thenThrow(Exception.class);
		Mockito.when(credito.getSaldoByCartao(Mockito.anyLong())).thenThrow(Exception.class);
		
		ResponseEntity res = controller.debitarCredito(creditoDTO);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
		
		res = controller.adicionarCredito(creditoDTO);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
		
		res = controller.ativarCartao(creditoDTO);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
		
		res = controller.desativarCartao(creditoDTO);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
		
		res = controller.getSaldoById(1);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
		
		res = controller.getSaldoByCartao(1L);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
		
	}
}
