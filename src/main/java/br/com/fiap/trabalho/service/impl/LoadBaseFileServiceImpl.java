package br.com.fiap.trabalho.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.entity.Aluno;
import br.com.fiap.trabalho.entity.Credito;
import br.com.fiap.trabalho.repository.AlunoRepository;
import br.com.fiap.trabalho.repository.CreditoRepository;
import br.com.fiap.trabalho.service.LoadBaseFileService;
import br.com.fiap.trabalho.util.FilterFile;

@Service
public class LoadBaseFileServiceImpl implements LoadBaseFileService {

	@Autowired
	private AlunoRepository alunos;

	@Autowired
	private CreditoRepository credito;
	
	public void load() throws IOException {
		try {
			List<String> file = FilterFile.filterFromResource("lista_alunos.txt");

			file.forEach((linha) -> {
				setData(linha.toString());
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void setData(String entry) {

		Long numeroCartao = Long.parseLong(entry.substring(41, 55).replace("-", "").replace(" ", ""));
		String nome = entry.substring(0, 41);

		Aluno aluno = new Aluno(nome, numeroCartao);

		alunos.save(aluno);
		setSaldo(aluno);
	}
	
	private void setSaldo(Aluno aluno) {
		
		Credito creditoEntity = new Credito();
		creditoEntity.setId(aluno.getId());
		creditoEntity.setNumeroCartao(aluno.getNumeroCartao());
		creditoEntity.setSaldo(getSaldoRandom());
		credito.save(creditoEntity);
	}
	
	private Double getSaldoRandom() {
		return Math.floor((Math.random()* 1000) * 100) / 100;
	}
}