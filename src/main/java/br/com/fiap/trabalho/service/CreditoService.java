package br.com.fiap.trabalho.service;


import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.fiap.trabalho.dto.CreditoDTO;

public interface CreditoService {
	public ResponseEntity debitar(CreditoDTO creditoDTO) throws Exception;
	public ResponseEntity adicionar(CreditoDTO creditoDTO) throws Exception;
	public CreditoDTO getSaldoById(Integer id) throws Exception;
	public CreditoDTO getSaldoByCartao(Long numeroCartao) throws Exception;
	public ResponseEntity ativarCartao(CreditoDTO creditDTO) throws Exception;
	public ResponseEntity desativarCartao(CreditoDTO creditDTO) throws Exception;
	public Optional salvar(CreditoDTO creditDTO, Boolean ativo);
	public void delete(Integer id);
}
