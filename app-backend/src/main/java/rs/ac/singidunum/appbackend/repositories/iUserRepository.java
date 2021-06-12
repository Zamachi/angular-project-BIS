package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.appbackend.entities.UserEntity;

public interface iUserRepository extends MongoRepository<UserEntity,String> {
}
