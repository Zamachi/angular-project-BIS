package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.appbackend.entities.OrderEntity;
import rs.ac.singidunum.appbackend.models.OrderModel;
import rs.ac.singidunum.appbackend.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //NOTE: CREATE, READ, UPDATE
    @PostMapping("createorder")
    @CrossOrigin(origins = "*")
    public OrderEntity createOrder(@RequestBody OrderModel orderModel){
        return orderService.createOrder(orderModel);
    }

    @GetMapping("getalluserorders/{username}")
    @CrossOrigin(origins = "*")
    public List<OrderEntity> getAllUserOrders(@PathVariable("username") String username){
        return orderService.getAllUserOrders(username);
    }

    @GetMapping("getallorders")
    @CrossOrigin(origins = "*")
    public List<OrderEntity> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("getallfinisheduserorders/{username}")
    @CrossOrigin(origins = "*")
    public List<OrderEntity> getAllFinishedUserOrders(@PathVariable("username") String username){
        return orderService.getAllCompleteOrders(username);
    }

    @GetMapping("findallbyusernameandproductid/{username}/{id}")
    @CrossOrigin(origins = "*")
    public List<OrderEntity> findAllByUsernameAndProductId(@PathVariable("username")String username, @PathVariable("id")String id){
        return orderService.findAllByUsernameAndProductId(username,id);
    }

    @PutMapping("updateorder")
    @CrossOrigin(origins = "*")
    public OrderEntity updateOrder(@RequestBody OrderModel orderModel){
        return orderService.updateOrder(orderModel);
    }
}
