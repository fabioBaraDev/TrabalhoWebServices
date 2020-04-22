package br.com.fiap.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditoDTO {

	@JsonProperty("aluno")
	private AlunoDTO aluno;

	@JsonProperty("saldo")
	private Double saldo;
	
	public CreditoDTO(AlunoDTO aluno, Double saldo) {
		this.aluno = aluno;
		this.saldo = saldo;
	}
	public CreditoDTO() {}
	
	public AlunoDTO getAluno() {
		return aluno;
	}
	
	public void setAlunoDTO(AlunoDTO aluno) {
		this.aluno = aluno;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
