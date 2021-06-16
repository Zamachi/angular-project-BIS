package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import rs.ac.singidunum.appbackend.entities.OrderEntity;

import java.util.List;

public interface iOrderRepository extends MongoRepository<OrderEntity, String> {
    // lista svih porudzbina jednog korisnika
    List<OrderEntity> findAllByUser_Username();

    // treba napisati query koji proverava da li je korisnik kupio neki item
    // treba pronaci sve orders korisnika i proveriti da li lista itema sadrzi zadati product id
    // items je niz
    // u tom nizu order items objekti
    // ti objekti se sastoje od product modela i quantity
    // u tom modelu proveriti id product-a
//    @Query(value = "{ 'user.username': {$regex: ?0}, 'items': { $in: [] } }")
    List<OrderEntity> findAllByUsernameAndProductId(String username, String itemId);
}
