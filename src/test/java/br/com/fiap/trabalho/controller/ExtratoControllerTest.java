package br.com.fiap.trabalho.controller;

import static org.junit.Assert.assertNotNull;

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

import br.com.fiap.trabalho.controller.ExtratoController;
import br.com.fiap.trabalho.service.ExtratoService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ExtratoControllerTest {

	@InjectMocks
	private ExtratoController controller;
	
	@Mock
	private ExtratoService extrato;
	
	@Test
	public void getSaldoById() {
		ResponseEntity resEntity = new ResponseEntity("Teste", HttpStatus.OK);
		
		Mockito.when(extrato.getSaldoById(Mockito.anyInt(), Mockito.anyLong())).thenReturn(resEntity);
		ResponseEntity res = controller.getSaldoById(1, 11L);
		
		assertNotNull(res);
	}
}
