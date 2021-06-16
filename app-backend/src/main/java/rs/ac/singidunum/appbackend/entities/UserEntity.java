package rs.ac.singidunum.appbackend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import rs.ac.singidunum.appbackend.models.AddressModel;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    @Field("firstName")
    private String firstName;
    @Field("lastName")
    private String lastName;
    @Field("email")
    private String email;
    @Field("phone")
    private String phone;
    @Field("address")
    private AddressModel address;
    @Field("favourites")
    private List<String> favourites; // array of what???
    @Field("username")
    private String username;
    @Field("password")
    private String password;
    @Field("dateCreated")
    private LocalDate dateCreated;
}
