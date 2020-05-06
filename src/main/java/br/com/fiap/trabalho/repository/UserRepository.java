package br.com.fiap.trabalho.repository;

import br.com.fiap.trabalho.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByUsername(String userName);

}