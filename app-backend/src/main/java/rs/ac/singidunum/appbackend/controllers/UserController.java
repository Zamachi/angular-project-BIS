package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.appbackend.entities.UserEntity;
import rs.ac.singidunum.appbackend.models.UserModel;
import rs.ac.singidunum.appbackend.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    //NOTE: READ, UPDATE i DELETE (? maybe) o podacima korisniku
    @DeleteMapping("deleteuser/{username}")
    @CrossOrigin(origins = "*")
    public void deleteUserByUsername(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
    }

    @GetMapping("finduserbyusername/{username}")
    @CrossOrigin(origins = "*")
    public UserEntity findUserByUsername(@PathVariable("username") String username){
        return userService.findUserByUsername(username);
    }

    @PutMapping("updateuser")
    @CrossOrigin(origins = "*")
    public UserEntity updateUser(@RequestBody UserModel userModel){
        return userService.updateUser(userModel);
    }

}
