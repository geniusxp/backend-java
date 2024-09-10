package br.com.fiap.sprintjava.auth;

import br.com.fiap.sprintjava.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials){

        var user = userRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new RuntimeException("Access Denied"));

        if ( !passwordEncoder.matches(credentials.password(), user.getDs_password()) )
            throw new RuntimeException("Access Denied");

        return tokenService.create(user);
    }
}

