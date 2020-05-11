package br.com.fiap.trabalho.dto;

public class AlunoDTO {
	private String nome;
	private Long numeroCartao;
	private Integer id;
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
