package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.appbackend.entities.UserEntity;
import rs.ac.singidunum.appbackend.services.UserService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    @CrossOrigin(origins = "*")
    public UserEntity login() { return null; }

    @PostMapping("register")
    @CrossOrigin(origins = "*")
    public UserEntity register() { return null; }
}
