package br.com.fiap.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.trabalho.entity.Endereco;

public interface EnderecoRepository  extends JpaRepository<Endereco, Integer> {

}
