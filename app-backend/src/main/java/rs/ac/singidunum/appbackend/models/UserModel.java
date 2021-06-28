package rs.ac.singidunum.appbackend.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserModel {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressModel address;
    private List<String> favourites; //NOTE: niz kategorija koje su omiljene korisniku
    private String username;
    private String password;
    private LocalDate dateCreated;
}
