package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import rs.ac.singidunum.appbackend.entities.OrderEntity;

import java.util.List;

public interface iOrderRepository extends MongoRepository<OrderEntity, String> {
    // lista svih porudzbina jednog korisnika
    @Query(value = "{ 'user.username': { $regex : ?0}}")
    List<OrderEntity> findAllByUser(String username);

    List<OrderEntity> findAllByUser_Id(String userid);

    @Query(value = "{ 'user._id': ObjectId(?0), 'items.product._id': ObjectId(?1), 'status' : 'complete'  }")
    List<OrderEntity> didUserByProduct(String userid, String productid);

}
