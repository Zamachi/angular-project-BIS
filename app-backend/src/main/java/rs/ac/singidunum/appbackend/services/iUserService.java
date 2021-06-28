package rs.ac.singidunum.appbackend.services;

import rs.ac.singidunum.appbackend.entities.UserEntity;
import rs.ac.singidunum.appbackend.models.UserModel;

public interface iUserService {
    void deleteUserByUsername(String username);
    UserEntity findUserByUsername(String username);
    UserEntity updateUser(UserModel userModel);
}
