package rs.ac.singidunum.appbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.appbackend.entities.OrderEntity;

import java.util.List;

public interface iOrderRepository extends MongoRepository<OrderEntity, String> {
    List<OrderEntity> findAllByUserModel_Username();
}
