package br.com.fiap.trabalho.service;

import br.com.fiap.trabalho.dto.EnderecoDTO;

public interface EnderecoService {
	public EnderecoDTO salvar(EnderecoDTO endereco, Integer id) ;
	public EnderecoDTO getEnderecoByID(Integer id);
}
