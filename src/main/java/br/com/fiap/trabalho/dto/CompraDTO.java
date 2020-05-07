package br.com.fiap.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class CompraDTO extends CreditoDTO  {
    @JsonProperty("codigoValidador")
    private String codigoValidador;

    private ZonedDateTime dataValidade;

    public String getCodigoValidador() {
        return codigoValidador;
    }

    public CompraDTO(AlunoDTO aluno, Double saldo, String codigoValidador, ZonedDateTime dataValidade) {
        setAlunoDTO(aluno);
        setSaldo(saldo);
        setCodigoValidador(codigoValidador);
        setDataValidade(dataValidade);
    }

    public CompraDTO() {}

    public void setCodigoValidador(String codigoValidador) {
        this.codigoValidador = codigoValidador;
    }

    public ZonedDateTime getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(ZonedDateTime dataValidade) {
        this.dataValidade = dataValidade;
    }
}
