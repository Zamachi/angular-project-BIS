package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.appbackend.entities.ReviewEntity;

import java.util.List;

public interface iReviewRepository extends MongoRepository<ReviewEntity, String> {
    List<ReviewEntity>  findAllByUserModel_Username();
}
