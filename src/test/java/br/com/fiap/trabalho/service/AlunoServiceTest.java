package br.com.fiap.trabalho.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.trabalho.dto.AlunoDTO;
import br.com.fiap.trabalho.dto.CreditoDTO;
import br.com.fiap.trabalho.entity.Aluno;
import br.com.fiap.trabalho.repository.AlunoRepository;
import br.com.fiap.trabalho.service.impl.AlunoServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AlunoServiceTest {

	
	@InjectMocks
	private AlunoServiceImpl service;
	
	@Mock
	private AlunoRepository alunoRepository;
	
	@Mock
	private CreditoService creditoService;
	
	@Test
	public void save() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Aluno aluno = new Aluno("Joao", 1L);
		aluno.setId(1);
		
		Mockito.when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(aluno);
		Mockito.when(creditoService.salvar(creditoDTO, true)).thenReturn(Optional.of("Teste"));
		
		AlunoDTO res = service.save(creditoDTO);
		
		assertEquals(1, res.getId());
	}
	
	@Test
	public void saveException() throws Exception {
		CreditoDTO creditoDTO = new CreditoDTO(new AlunoDTO(1, "Joao", 1111L), 1.1);
		Aluno aluno = new Aluno("Joao", 1L);
		aluno.setId(1);
		
		Mockito.when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(aluno);
		Mockito.when(creditoService.salvar(creditoDTO, true)).thenReturn(Optional.ofNullable(null));
		
		boolean tryC = false;
		try {
			service.save(creditoDTO);
		} catch (Exception e) {
			tryC = true;
		}
		
		assertTrue(tryC);
	}
	
	@Test
	public void delete() {
		Aluno aluno = new Aluno("Joao", 1L);
		aluno.setId(1);
		
		Mockito.when(alunoRepository.findById(1)).thenReturn(Optional.of(aluno));
		Mockito.doNothing().when(alunoRepository).delete(Mockito.any(Aluno.class));
		Mockito.doNothing().when(creditoService).delete(Mockito.anyInt());
		
		
		String res = service.delete(1);
		
		assertEquals("Deletado com sucesso", res);
		
	}
	
	@Test
	public void getAll() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = new Aluno("Joao", 1L);
		aluno.setId(1);
		
		alunos.add(aluno);
		
		Mockito.when(alunoRepository.findAll()).thenReturn(alunos);
		
		List<AlunoDTO> res = service.getAll();
		
		assertNotNull(res);
	}
	
	@Test
	public void getById() {
		Aluno aluno = new Aluno("Joao", 1L);
		aluno.setId(1);
		
		Mockito.when(alunoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(aluno));
		
		Optional<AlunoDTO> res = service.getById(1);
		
		assertTrue(res.isPresent());
	}
	
	@Test
	public void getByName() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = new Aluno("Joao", 1L);
		aluno.setId(1);
		
		alunos.add(aluno);
		
		Mockito.when(alunoRepository.findByName(Mockito.anyString())).thenReturn(alunos);
		
		List<AlunoDTO> res = service.getByName("Joao");
		
		assertNotNull(res);
	}
}
