package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.repositories.iUserRepository;

@Service
public class UserService implements iUserService{

    @Autowired
    private iUserRepository userRepository;

    

}
