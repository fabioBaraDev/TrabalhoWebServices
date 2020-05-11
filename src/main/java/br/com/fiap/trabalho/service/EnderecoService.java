package br.com.fiap.trabalho.service;

import br.com.fiap.trabalho.dto.EnderecoDTO;

public interface EnderecoService {
	public EnderecoDTO salvar(EnderecoDTO endereco, Integer id) throws Exception ;
	public EnderecoDTO getEnderecoByID(Integer id);
}
