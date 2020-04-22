package br.com.fiap.trabalho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.trabalho.entity.Extrato;

public interface ExtratoRepository extends JpaRepository<Extrato, Integer> {

	@Query(value = "SELECT e.id, e.id_credito, e.valor_operacao, e.numero_cartao, e.tipo_operacao, e.data_operacao FROM tb_extrato e WHERE e.id_credito = :idCreditoAluno AND e.numero_cartao = :numeroCartao", nativeQuery = true)
	public List<Extrato> getById(Integer idCreditoAluno, Long numeroCartao);

}
