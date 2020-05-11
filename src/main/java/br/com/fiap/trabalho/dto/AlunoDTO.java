package br.com.fiap.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlunoDTO {
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("numero_cartao")
	private Long numeroCartao;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("endereco")
	private EnderecoDTO endereco;
	
	public AlunoDTO(Integer id, String nome, Long numeroCartao, EnderecoDTO endereco) {
		this.id = id;
		this.nome = nome.toUpperCase();
		this.setNumeroCartao(numeroCartao);
		this.setEndereco(endereco);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public Long getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
}
