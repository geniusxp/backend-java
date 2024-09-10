package br.com.fiap.sprintjava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public List<User> findAll(@RequestParam(required = false) String email) {
        if(email != null) return service.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Access Denied"));
        return service.findAll();
    }

}
