package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.entities.OrderEntity;
import rs.ac.singidunum.appbackend.entities.ProductEntity;
import rs.ac.singidunum.appbackend.models.OrderItem;
import rs.ac.singidunum.appbackend.models.OrderModel;
import rs.ac.singidunum.appbackend.repositories.iOrderRepository;
import rs.ac.singidunum.appbackend.repositories.iProductRepository;
import rs.ac.singidunum.appbackend.repositories.iUserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements iOrderService {

    @Autowired
    private iOrderRepository orderRepository;
    @Autowired
    private AutoMapperService autoMapperService;
    @Autowired
    private iUserRepository iUserRepository;
    @Autowired
    private iProductRepository productRepository;

    @Override
    public OrderEntity createOrder(OrderModel orderModel) {

        orderModel.setDateCreated(LocalDate.now());
        orderModel.setStatus("ongoing");

        //NOTE: trebalo bi mozda proveriti dostupnost pre nego sto bude upisano u bazu

        for(OrderItem product : orderModel.getItems()){
            var p = productRepository
                    .findById( product.getProduct().getId() )
                    .get();

            p.setLeftInStock( p.getLeftInStock() - product.getQuantity() );

            productRepository.save(p);
        }

        var order = autoMapperService.map(orderModel, OrderEntity.class);

        return orderRepository.insert(order);
    }

    @Override
    public List<OrderEntity> getAllUserOrders(String username) {

        var user = iUserRepository.findByUsername(username);
        //NOTE: trebalo bi da radi ovakav search
        return orderRepository.findAllByUser(user);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity updateOrder(OrderModel orderModel) {

        var order = orderRepository.
                findById(orderModel.getId()).get();

        order.setStatus(orderModel.getStatus());

        if(order.getStatus().equalsIgnoreCase("cancelled")){
            for(OrderItem product : order.getItems()){
                var p = productRepository
                        .findById( product.getProduct().getId() )
                        .get();

                p.setLeftInStock( p.getLeftInStock() + product.getQuantity() );

                productRepository.save(p);
            }
        }

        //NOTE: azurirati neophodne atribute
        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getAllCompleteOrders(String username) {

        var user = iUserRepository.
                findByUsername(username);

        var orders = this.getAllUserOrders(username);

       return orders
               .stream()
               .filter(
                orderEntity -> orderEntity
                        .getStatus()
                        .equalsIgnoreCase("finished"))
               .collect(Collectors.toList());

    }

//    @Override
//    public List<OrderEntity> findAllByUsernameAndProductId(String username, String id) {
//        return orderRepository.findAllByUsernameAndProductId(username,id);
//    }
}
