package br.com.fiap.sprintjava.repositories;

import br.com.fiap.sprintjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    @Query("from User u where u.email = :email or u.cpf = :cpf")
    User findByEmailOrCpf(String email, String cpf);
}
