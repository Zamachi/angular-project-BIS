package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.appbackend.entities.UserEntity;
import rs.ac.singidunum.appbackend.models.UserModel;
import rs.ac.singidunum.appbackend.services.UserService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    @CrossOrigin(origins = "*")
    public UserEntity login(@RequestBody UserModel userModel) { return this.userService.login(userModel); }

    @PostMapping("register")
    @CrossOrigin(origins = "*")
    public UserEntity register(@RequestBody UserModel userModel) { return this.userService.register(userModel); }
}
