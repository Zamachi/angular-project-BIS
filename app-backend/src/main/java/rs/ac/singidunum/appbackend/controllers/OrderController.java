package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.appbackend.services.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
}
