package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.appbackend.entities.ReviewEntity;

import java.util.List;

public interface iReviewRepository extends MongoRepository<ReviewEntity, String> {
    // lista svih recenzija odredjenog korisnika
    List<ReviewEntity> findAllByUser_Username();

    // lista svih recenzija za odredjeni proizvod
    List<ReviewEntity> findAllByProduct_Id();

    // jedinstvena recenzija korisnika za jedan proizvod
    ReviewEntity findByUser_UsernameAndProduct_Id();
}
