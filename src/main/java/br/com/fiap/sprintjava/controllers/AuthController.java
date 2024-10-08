package br.com.fiap.sprintjava.controllers;

import br.com.fiap.sprintjava.dtos.auth.RecoverPasswordDTO;
import br.com.fiap.sprintjava.dtos.auth.SignInCredentialsDTO;
import br.com.fiap.sprintjava.dtos.auth.ResponseTokenDTO;
import br.com.fiap.sprintjava.dtos.errors.ValidationErrorDTO;
import br.com.fiap.sprintjava.dtos.user.CreateUserDTO;
import br.com.fiap.sprintjava.dtos.user.UserDetailsDTO;
import br.com.fiap.sprintjava.models.User;
import br.com.fiap.sprintjava.repositories.UserRepository;
import br.com.fiap.sprintjava.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Operações relacionadas à autenticação de usuários.")
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
    @Operation(summary = "Autenticar um usuário", description = "Autentica um usuário na aplicação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso.", content = @Content(schema = @Schema(implementation = ResponseTokenDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado.", content = @Content(schema = @Schema(hidden = true)))
    })
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
    @Transactional
    @Operation(summary = "Cadastrar um novo usuário", description = "Cadastra um novo usuário na aplicação.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.", content = @Content(schema = @Schema(implementation = UserDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.")
    })
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody @Valid CreateUserDTO userDTO, UriComponentsBuilder uri) {
        var userAlreadyExists = userRepository.findByEmailOrCpf(userDTO.email(), userDTO.cpf());

        if(userAlreadyExists != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

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

    @PostMapping("/recover-password")
    @Operation(summary = "Recuperar senha", description = "Envia um e-mail com instruções para recuperação de senha.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "E-mail enviado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.", content = @Content(schema = @Schema(implementation = ValidationErrorDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public ResponseEntity<Void> recoverPassword(@RequestBody @Valid RecoverPasswordDTO recoverPasswordDTO) {
        var user = userRepository.findByEmail(recoverPasswordDTO.email());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // TODO: Send email with password recovery instructions

        return ResponseEntity.ok().build();
    }
}