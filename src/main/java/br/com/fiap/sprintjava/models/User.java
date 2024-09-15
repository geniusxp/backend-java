package br.com.fiap.sprintjava.models;

import br.com.fiap.sprintjava.dtos.user.UpdateUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="T_GXP_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name="id_user")
    private Long id;

    @Column(name="nm_nome", nullable = false, length = 150)
    private String name;

    @Column(name="ds_email", unique = true, nullable = false, length = 320)
    private String email;

    @Column(name="ds_password", nullable = false)
    private String password;

    @Column(name="nr_cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name="dt_birth", nullable = false)
    private LocalDate birthDate;

    @Column(name="url_avatar")
    private String avatarUrl;

    @Column(name="tx_description", length = 500)
    private String description;

    @Column(name="tx_interests", length = 500)
    private String interests;

    public User(String name, String email, String password, String cpf, LocalDate birthDate, String avatarUrl, String description, String interests) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.avatarUrl = avatarUrl;
        this.description = description;
        this.interests = interests;
    }

    public void update(UpdateUserDTO userDTO){
        if(userDTO.name() != null) this.name = userDTO.name();
        if(userDTO.email() != null) this.email = userDTO.email();
        if(userDTO.password() != null) this.password = userDTO.password();
        if(userDTO.cpf() != null) this.cpf = userDTO.cpf();
        if(userDTO.birthDate() != null) this.birthDate = userDTO.birthDate();
        if(userDTO.avatarUrl() != null) this.avatarUrl = userDTO.avatarUrl();
        if(userDTO.description() != null) this.description = userDTO.description();
        if(userDTO.interests() != null) this.interests = userDTO.interests();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
