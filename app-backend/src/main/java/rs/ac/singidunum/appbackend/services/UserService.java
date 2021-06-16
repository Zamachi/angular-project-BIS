package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.entities.UserEntity;
import rs.ac.singidunum.appbackend.models.UserModel;
import rs.ac.singidunum.appbackend.repositories.iUserRepository;

import java.time.LocalDate;

@Service
public class UserService implements iUserService{

    @Autowired
    private iUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AutoMapperService autoMapperService;

    // mozda zameniti if(s) sa try-catch
    public UserEntity login(UserModel userModel) {
        if (userModel != null) {
            UserEntity userInDatabase = this.userRepository.findByUsername(userModel.getUsername());
            if (userInDatabase != null) {
                if (this.passwordEncoder.matches(userModel.getPassword(), userInDatabase.getPassword())) {
                    return userInDatabase;
                }
            }
        }
        return null;
    }

    public UserEntity register(UserModel userModel) {
        if (userModel != null && this.userRepository.findByUsername(userModel.getUsername()) == null ) {
            userModel.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
            userModel.setDateCreated(LocalDate.now());
            return this.userRepository.insert(this.autoMapperService.map(userModel, UserEntity.class));
        }
        return null;
    }

}
