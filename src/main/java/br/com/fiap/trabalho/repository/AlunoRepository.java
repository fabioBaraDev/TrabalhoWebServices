package br.com.fiap.trabalho.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.trabalho.dto.StatusDTO;
import br.com.fiap.trabalho.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	@Query(value = "SELECT t.id, t.nome, t.numero_cartao, t.status_aluno FROM tb_aluno t WHERE t.nome LIKE %:nome% ", nativeQuery = true)
	List<Aluno> findByName(@Param("nome") String nome);
	
	@Query(value = "SELECT t.status_aluno FROM tb_aluno t WHERE t.id = :id ", nativeQuery = true)
	Optional<StatusDTO> findStatusAluno(@Param("id") Integer id);
	
}
