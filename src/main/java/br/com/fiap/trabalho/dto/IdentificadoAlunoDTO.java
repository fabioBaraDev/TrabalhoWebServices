package br.com.fiap.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdentificadoAlunoDTO {
	
	@JsonProperty("id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
