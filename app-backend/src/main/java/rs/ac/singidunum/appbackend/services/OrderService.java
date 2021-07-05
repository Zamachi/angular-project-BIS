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
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private iReviewService reviewService;

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public OrderEntity createOrder(OrderModel orderModel) {

        orderModel.setDateCreated(LocalDate.now());
        orderModel.setStatus("ongoing");
        orderModel.setMinEta( this.getRandomNumber(1,4) );

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
        //NOTE: trebalo bi da radi ovakav search
        return orderRepository.findAllByUser(username);
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
    public OrderEntity updateOrderItems(OrderModel orderModel) {

        var order = orderRepository.
                findById(orderModel.getId()).get();

        // new items to put in the order model
        var inc = orderModel.getItems();

        // current items in non-updated order model
        var current = (order.getItems());

        // list of differeces between two items lists
        List<OrderItem> dif = current.stream().filter(elem -> !inc.contains(elem)).collect(Collectors.toList());

        if (dif.size() > 0) {
            for(OrderItem item : dif) {
                var p = this.productRepository
                        .findById(item.getProduct().getId())
                        .get();

                p.setLeftInStock(p.getLeftInStock() + item.getQuantity());

                this.productRepository.save(p);
            }
        }


        order.setItems(inc);

        double newPrice = 0.;

        for (OrderItem orderItem: order.getItems()) {
            newPrice += (orderItem.getQuantity() * orderItem.getProduct().getPrice());
        }

        order.setTotalPrice(newPrice);

        return this.orderRepository.save(order);
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

    // find all orders by user id
    @Override
    public List<OrderEntity> findAllByUser_Id(String userid) {
        return this.orderRepository.findAllByUser_Id(userid);
    };

    @Override
    public List<OrderEntity> getAllCompleteOrdersByUserId(String userid) {

        var user = iUserRepository.
                findById(userid);

        var orders = this.findAllByUser_Id(userid);

        return orders
                .stream()
                .filter(
                        orderEntity -> orderEntity
                                .getStatus()
                                .equalsIgnoreCase("complete"))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(String id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public List<OrderEntity> didUserByProduct(String userid, String productid) {
        return this.orderRepository.didUserByProduct(userid, productid);
    }
}
