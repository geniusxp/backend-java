package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.user.UserDetailsDTO;
import br.com.fiap.sprintjava.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserDetailsDTO> getMyProfile(){
        return ResponseEntity.ok(new UserDetailsDTO((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @PutMapping("/me")
    public ResponseEntity<Object> updateProfile(){
        // TODO: atualizar perfil do usuário logado
        return ResponseEntity.ok().build();
    }
}
