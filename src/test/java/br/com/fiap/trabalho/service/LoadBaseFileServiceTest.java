package br.com.fiap.trabalho.service;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.trabalho.entity.Aluno;
import br.com.fiap.trabalho.entity.Credito;
import br.com.fiap.trabalho.repository.AlunoRepository;
import br.com.fiap.trabalho.repository.CreditoRepository;
import br.com.fiap.trabalho.service.impl.LoadBaseFileServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LoadBaseFileServiceTest {

	@InjectMocks
	private LoadBaseFileServiceImpl service;

	@Mock
	private AlunoRepository alunos;

	@Mock
	private CreditoRepository credito;

	@Test
	public void load() throws IOException {
		
		Aluno aluno = new Aluno();
		aluno.setId(1);
		aluno.setNome("Joao");
		aluno.setNumeroCartao(11111L);

		//PowerMockito.mockStatic(FilterFile.class);
		//PowerMockito.when(FilterFile.filterFromResource("lista_alunos.txt")).thenReturn(stream);
		
		Mockito.when(alunos.save(Mockito.any(Aluno.class))).thenReturn(aluno);
		Mockito.when(credito.save(Mockito.any(Credito.class))).thenReturn(new Credito());
		
		service.load();

	}
}
