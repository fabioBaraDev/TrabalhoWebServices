package br.com.fiap.trabalho.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.trabalho.dto.EnderecoDTO;
import br.com.fiap.trabalho.entity.Endereco;
import br.com.fiap.trabalho.exceptions.CEPInvalidoException;
import br.com.fiap.trabalho.repository.EnderecoRepository;
import br.com.fiap.trabalho.service.EnderecoService;
import br.com.fiap.trabalho.service.ValidaCEP;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private ValidaCEP validaCEP;
	
	public EnderecoDTO salvar(EnderecoDTO endereco, Integer id) throws CEPInvalidoException {

		if(!validaCEP.validar(endereco.getCep())) {
			throw new CEPInvalidoException();
		}
		
		Endereco enderecoEntity = new Endereco(id, endereco.getLogradouro(), endereco.getNumero(),
				endereco.getComplemento(), endereco.getCep());

		return new EnderecoDTO(repository.save(enderecoEntity));
	}

	public EnderecoDTO getEnderecoByID(Integer id) {
		Optional<Endereco> endereco = repository.findById(id);
		return new EnderecoDTO(endereco.get());
	}
}
