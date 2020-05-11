package br.com.fiap.trabalho.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.dto.CreateUserDTO;
import br.com.fiap.trabalho.dto.EnderecoDTO;
import br.com.fiap.trabalho.entity.Aluno;
import br.com.fiap.trabalho.repository.AlunoRepository;
import br.com.fiap.trabalho.service.EnderecoService;
import br.com.fiap.trabalho.service.LoadBaseFileService;
import br.com.fiap.trabalho.service.UserService;
import br.com.fiap.trabalho.util.FilterFile;

@Service
public class LoadBaseFileServiceImpl implements LoadBaseFileService {

	@Autowired
	private AlunoRepository alunos;

	@Autowired
	private UserService userService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	public void load() throws IOException {
		try {
			
			//cria usuario padrao de admin
			CreateUserDTO usuario = new CreateUserDTO();
			usuario.setUsername("admin@fiap.com.br");
			usuario.setPassword("admin");
			
			userService.create(usuario);
			
			List<String> file = FilterFile.filterFromResource("lista_alunos.txt");

			file.forEach((linha) -> {
				try {
					setData(linha.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void setData(String entry) throws Exception {

		Long numeroCartao = Long.parseLong(entry.substring(41, 55).replace("-", "").replace(" ", ""));
		String nome = entry.substring(0, 41);

		Aluno aluno = new Aluno(nome, numeroCartao);
		aluno.setStatusAluno(true);
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setId(aluno.getId());
		endereco.setLogradouro("Avenida Capitão-Mor Aguiar");
		endereco.setNumero("123");
		endereco.setComplemento("13");
		endereco.setCep("11310201");
		
		alunos.save(aluno);
		enderecoService.salvar(endereco, aluno.getId());

	}
}