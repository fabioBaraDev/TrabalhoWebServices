package br.com.fiap.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusDTO {

	@JsonProperty("status_aluno")
	private Boolean statusAluno;

	public Boolean getStatusAluno() {
		return statusAluno;
	}

	public void setStatusAluno(Boolean statusAluno) {
		this.statusAluno = statusAluno;
	}
	
}
