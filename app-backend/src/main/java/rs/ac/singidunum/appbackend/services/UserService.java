package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.entities.UserEntity;
import rs.ac.singidunum.appbackend.models.UserModel;
import rs.ac.singidunum.appbackend.repositories.iUserRepository;

@Service
public class UserService implements iUserService{

    @Autowired
    private iUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity login(UserModel userModel) {

        return null;
    }

    public UserEntity register(UserModel userModel) {

        return null;
    }

}
