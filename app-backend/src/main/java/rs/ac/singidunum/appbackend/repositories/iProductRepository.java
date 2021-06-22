package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.appbackend.entities.ProductEntity;

import java.util.List;

public interface iProductRepository extends MongoRepository<ProductEntity, String> {

}
