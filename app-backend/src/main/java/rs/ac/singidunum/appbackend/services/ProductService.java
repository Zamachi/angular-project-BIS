package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.entities.ProductEntity;
import rs.ac.singidunum.appbackend.models.ProductModel;
import rs.ac.singidunum.appbackend.repositories.iProductRepository;

import java.util.List;

@Service
public class ProductService implements iProductService {
    @Autowired
    private iProductRepository productRepository;
    @Autowired
    private AutoMapperService autoMapperService;


    @Override
    public ProductEntity createProduct(ProductModel productModel) {
        return productRepository
                .insert(autoMapperService
                        .map(productModel, ProductEntity.class));
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}
