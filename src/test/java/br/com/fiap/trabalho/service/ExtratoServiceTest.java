package br.com.fiap.trabalho.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import br.com.fiap.trabalho.repository.ExtratoRepository;
import br.com.fiap.trabalho.service.impl.ExtratoServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ExtratoServiceTest {
	
	@InjectMocks
	private ExtratoServiceImpl service;
	
	@Mock
	private ExtratoRepository extrato;
	
	@Test
	public void retirarExtrato() {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);

		Credito credito = new Credito(creditoDTO);
		credito.setAlunoCreditoAtivo(true);
		
		Extrato ex = new Extrato(credito, 1.1, Operacao.CREDITO);
		List<Extrato> listEx =  new ArrayList<>();
		listEx.add(ex);
		
		Mockito.when(extrato.getById(Mockito.anyInt(), Mockito.anyLong())).thenReturn(listEx);
		ResponseEntity res = service.getSaldoById(1, 1L);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}
}
