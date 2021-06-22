package rs.ac.singidunum.appbackend.services;

import rs.ac.singidunum.appbackend.entities.ProductEntity;
import rs.ac.singidunum.appbackend.models.ProductModel;

import java.util.List;

public interface iProductService {
    ProductEntity createProduct(ProductModel productModel);
    List<ProductEntity> findAll();
}
