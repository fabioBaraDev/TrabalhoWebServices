package br.com.fiap.trabalho.dto;

import br.com.fiap.trabalho.entity.Endereco;

public class EnderecoDTO {

	private String logradouro;	
	private Integer id;
	private String numero;
	private String complemento;
	private String cep;

	public EnderecoDTO() {}
	
	public EnderecoDTO(Endereco save) {
		this.id = save.getId();
		this.numero = save.getNumero();
		this.complemento = save.getComplemento();
		this.logradouro = save.getLogradouro();
		this.cep = save.getCep();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
}
