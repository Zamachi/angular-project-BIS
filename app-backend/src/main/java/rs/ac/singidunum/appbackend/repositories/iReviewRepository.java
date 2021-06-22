package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import rs.ac.singidunum.appbackend.entities.ReviewEntity;

import java.util.List;

public interface iReviewRepository extends MongoRepository<ReviewEntity, String> {
    // lista svih recenzija odredjenog korisnika
//    @Query(value = " 'user.username' : {$regex: ?0} ") //NOTE: mozda ne treba?
    List<ReviewEntity> findAllByUser_Username(String username);

    // lista svih recenzija za odredjeni proizvod
//    @Query(value = " 'product.id' : ?0 ")
    List<ReviewEntity> findAllByProduct_Id(String id);

    // jedinstvena recenzija korisnika za jedan proizvod
//    @Query(value = " 'product.id' : ?1, 'user.username':?0 ")
    List<ReviewEntity> findByUser_UsernameAndProduct_Id(String Username, String id);
}
