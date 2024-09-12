package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.auth.SignInCredentialsDTO;
import br.com.fiap.sprintjava.dtos.auth.ResponseTokenDTO;
import br.com.fiap.sprintjava.dtos.user.CreateUserDTO;
import br.com.fiap.sprintjava.dtos.user.UserDetailsDTO;
import br.com.fiap.sprintjava.models.User;
import br.com.fiap.sprintjava.repositories.UserRepository;
import br.com.fiap.sprintjava.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDTO> loginUser(@RequestBody @Valid SignInCredentialsDTO credentialsDTO){
        try {
            var token = new UsernamePasswordAuthenticationToken(credentialsDTO.email(), credentialsDTO.password());
            var authentication = manager.authenticate(token);

            var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new ResponseTokenDTO(tokenJwt));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody @Valid CreateUserDTO userDTO, UriComponentsBuilder uri){
        var user = new User(
                userDTO.name(),
                userDTO.email(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.cpf(),
                userDTO.birthDate(), userDTO.avatarUrl(),
                userDTO.description(), userDTO.interests());

        userRepository.save(user);

        var uriBuilder = uri.path("users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uriBuilder).body(new UserDetailsDTO(user));
    }

}