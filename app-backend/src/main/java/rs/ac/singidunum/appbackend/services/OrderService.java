package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.repositories.iOrderRepository;

@Service
public class OrderService implements iOrderService {

    @Autowired
    private iOrderRepository orderRepository;
}
