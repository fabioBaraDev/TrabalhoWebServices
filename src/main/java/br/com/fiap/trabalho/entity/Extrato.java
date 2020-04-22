package br.com.fiap.trabalho.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.trabalho.enumerator.Operacao;

@Entity
@Table(name = "tb_extrato")
public class Extrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="id_credito", nullable = false)
	private Integer idCredito;
	
	@Column(name="valor_operacao", nullable = false)
	private Double valorOperacao;
	
	@Column(name="numeroCartao", nullable = false)
	private Long numeroCartao;
	
	@Column(name="tipo_operacao", nullable = false)
	private String tipoOperacao;
	
	@Column(name="data_operacao", nullable = false)
	private LocalDateTime dataOperacao = LocalDateTime.now();

	public Extrato() {}
	public Extrato(Credito credito, Double valorOperacao, Operacao operacao) {
		this.idCredito = credito.getId();
		this.numeroCartao = credito.getNumeroCartao();
		this.valorOperacao = valorOperacao;
		this.tipoOperacao = operacao.toString();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(Double valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

	public Long getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public LocalDateTime getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(LocalDateTime dataOperacao) {
		this.dataOperacao = dataOperacao;
	}	
}
