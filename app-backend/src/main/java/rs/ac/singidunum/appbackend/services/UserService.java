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

    // login metoda koja vraca UserEntity (model) korisnika ukoliko korisnik postoji u bazi i ako se poklope lozinke
    // u suprotnom vraca null
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

    // registruje korisnika - upisuje u bazu ukoliko poslat username ne postoji u bazi
    // ako je username zauzet, vraca null
    public UserEntity register(UserModel userModel) {
        if (userModel != null && this.userRepository.findByUsername(userModel.getUsername()) == null ) {
            userModel.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
            userModel.setDateCreated(LocalDate.now());
            return this.userRepository.insert(this.autoMapperService.map(userModel, UserEntity.class));
        }
        return null;
    }

    @Override
    public void deleteUserByUsername(String username) {

        var user_to_delete = userRepository
                .findByUsername(username);

        userRepository.delete(user_to_delete);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity updateUser(UserModel userModel) {
        //NOTE: trebalo bi da sacuva ceo model korisnika sa update panela
        var user = autoMapperService.map(userModel, UserEntity.class);

        return userRepository.save(user);
    }
}
