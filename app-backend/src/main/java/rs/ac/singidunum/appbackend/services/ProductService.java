package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.repositories.iProductRepository;

@Service
public class ProductService implements iProductService {
    @Autowired
    private iProductRepository productRepository;
}
