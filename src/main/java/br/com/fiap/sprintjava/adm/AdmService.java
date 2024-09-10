package br.com.fiap.sprintjava.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmService {
    @Autowired
    AdmRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Adm> findAll() {
        return repository.findAll();
    }

    public Adm create(Adm adm){
        adm.setDs_password(passwordEncoder.encode(adm.getDs_password()));
        return repository.save(adm);
    }
}
