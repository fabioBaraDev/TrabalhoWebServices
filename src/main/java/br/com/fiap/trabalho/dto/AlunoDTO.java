package br.com.fiap.trabalho.dto;

public class AlunoDTO {
	private String nome;
	private Long numeroCartao;
	private Integer id;
	
	public AlunoDTO(Integer id, String nome, Long numeroCartao) {
		this.id = id;
		this.nome = nome.toUpperCase();
		this.setNumeroCartao(numeroCartao);
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
}
