package rs.ac.singidunum.appbackend.services;

import rs.ac.singidunum.appbackend.entities.OrderEntity;
import rs.ac.singidunum.appbackend.models.OrderModel;

import java.util.List;

public interface iOrderService {

    OrderEntity createOrder(OrderModel orderModel);
    List<OrderEntity> getAllUserOrders(String username);
    List<OrderEntity> getAllOrders();
    OrderEntity updateOrder(OrderModel orderModel);
    List<OrderEntity> getAllCompleteOrders(String username);
    List<OrderEntity> didUserByProduct(String userid, String productid);
    List<OrderEntity> findAllByUser_Id(String userid);
    List<OrderEntity> getAllCompleteOrdersByUserId(String userid);
    void deleteById(String id);
}
