package br.com.fiap.trabalho.controller;

import br.com.fiap.trabalho.dto.AuthDTO;
import br.com.fiap.trabalho.dto.CreateUserDTO;
import br.com.fiap.trabalho.dto.JwtDTO;
import br.com.fiap.trabalho.dto.UserDTO;

public interface UserService {

    UserDTO create(CreateUserDTO createUserDTO);

    JwtDTO login(AuthDTO authDTO);
}