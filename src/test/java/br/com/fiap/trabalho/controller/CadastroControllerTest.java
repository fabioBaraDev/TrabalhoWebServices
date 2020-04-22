package br.com.fiap.trabalho.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import br.com.fiap.trabalho.controller.CadastroAlunoController;
import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.service.AlunoService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CadastroControllerTest {

	@InjectMocks
	private CadastroAlunoController controller;

	@Mock
	private AlunoService alunoService;

	@Test
	public void save() throws Exception {

		CreditoDTO creditoDTO = new CreditoDTO();
		creditoDTO.setAlunoDTO(new AlunoDTO(1, "Joao", 1111111L));
		creditoDTO.setSaldo(1.1);

		Mockito.when(alunoService.save(Mockito.any(CreditoDTO.class))).thenReturn(new AlunoDTO(1, "Joao", 1111111L));

		ResponseEntity res = controller.save(creditoDTO);

		assertNotNull(res);
	}

	@Test
	public void saveException() throws Exception {

		CreditoDTO creditoDTO = new CreditoDTO();
		creditoDTO.setAlunoDTO(new AlunoDTO(1, "Joao", 1111111L));
		creditoDTO.setSaldo(1.1);

		Mockito.when(alunoService.save(Mockito.any(CreditoDTO.class))).thenThrow(NullPointerException.class);

		ResponseEntity res = controller.save(creditoDTO);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
	}

	@Test
	public void getAll() {
		List<AlunoDTO> list = new ArrayList<AlunoDTO>();
		list.add((new AlunoDTO(1, "Joao", 1111111L)));
		Mockito.when(alunoService.getAll()).thenReturn(list);

		List<AlunoDTO> res = controller.getAll();
		assertNotNull(res);
	}

	@Test
	public void getById() {
		Optional<AlunoDTO> aluno = Optional.of(new AlunoDTO(1, "Joao", 1111111L));

		Mockito.when(alunoService.getById(Mockito.anyInt())).thenReturn(aluno);

		Optional<AlunoDTO> res = controller.getById(1);
		assertEquals(1, res.get().getId());

	}

	@Test
	public void getByName() {
		List<AlunoDTO> list = new ArrayList<AlunoDTO>();
		list.add((new AlunoDTO(1, "Joao", 1111111L)));
		Mockito.when(alunoService.getByName(Mockito.anyString())).thenReturn(list);

		List<AlunoDTO> res = controller.getByName("Joao");
		assertNotNull(res);
	}

	@Test
	public void delete() {
		
		Mockito.when(alunoService.delete(Mockito.anyInt())).thenReturn("Sucesso");
		ResponseEntity res = controller.delete(1);
		assertNotNull(res);
	}
}
