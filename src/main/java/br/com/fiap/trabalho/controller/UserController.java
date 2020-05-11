package br.com.fiap.trabalho.controller;

import br.com.fiap.trabalho.dto.AuthDTO;
import br.com.fiap.trabalho.dto.CreateUserDTO;
import br.com.fiap.trabalho.dto.JwtDTO;
import br.com.fiap.trabalho.dto.UserDTO;
import br.com.fiap.trabalho.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserDTO create(@RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

    @PostMapping("/token")
    public JwtDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }

}