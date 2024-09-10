package br.com.fiap.sprintjava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User create(User user) {
        user.setDs_password(passwordEncoder.encode(user.getDs_password()));
        user.ds_email = user.getDs_email();
        return repository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
