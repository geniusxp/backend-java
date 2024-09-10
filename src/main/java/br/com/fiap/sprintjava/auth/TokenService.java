package br.com.fiap.sprintjava.auth;

import br.com.fiap.sprintjava.user.User;
import br.com.fiap.sprintjava.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final UserRepository userRepository;
    Algorithm algorithm = Algorithm.HMAC256("assinatura");

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Token create(User user){
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

        String token = JWT.create()
                .withIssuer("genius")
                .withSubject(user.getDs_email())
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token, user.getId_user(), user.getTx_description());
    }

    public User getUserFromToken(String token) {
        var email =JWT.require(algorithm)
                .withIssuer("genius")
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
