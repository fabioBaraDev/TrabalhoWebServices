package br.com.fiap.trabalho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.trabalho.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	@Query(value = "SELECT t.id, t.nome, t.numero_cartao FROM tb_aluno t WHERE t.nome LIKE %:nome% ", nativeQuery = true)
	List<Aluno> findByName(@Param("nome") String nome);
}
