package br.com.fiap.trabalho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.trabalho.dto.CreditoDTO;

@Entity
@Table(name = "tb_credito")
@EntityListeners(AuditingEntityListener.class)
public class Credito {

	public Credito() {}
	public Credito(CreditoDTO creditoDTO) {
		this.id = creditoDTO.getAluno().getId();
		this.numeroCartao = creditoDTO.getAluno().getNumeroCartao();
		this.saldo = creditoDTO.getSaldo();
	}
	
	@Id
	private Integer id;
	
	@Column(name="saldo", nullable = false)
	private Double saldo;
	
	@Column(name="numeroCartao", nullable = false)
	private Long numeroCartao;
	
	@Column(name="alunoCreditoAtivo", nullable = false)
	private Boolean alunoCreditoAtivo = false;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Long getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Boolean getAlunoCreditoAtivo() {
		return alunoCreditoAtivo;
	}
	public void setAlunoCreditoAtivo(Boolean alunoCreditoAtivo) {
		this.alunoCreditoAtivo = alunoCreditoAtivo;
	}
	
}
