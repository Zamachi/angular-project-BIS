package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.appbackend.entities.ProductEntity;
import rs.ac.singidunum.appbackend.models.ProductModel;
import rs.ac.singidunum.appbackend.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //NOTE: READovi sa fronta(pretrage), CREATE za laksi insert

    @PostMapping("createproduct")
    @CrossOrigin(origins = "*")
    public ProductEntity createProduct(@RequestBody ProductModel productModel){
        return productService.createProduct(productModel);
    }

    @GetMapping("findallproducts")
    @CrossOrigin(origins = "*")
    public List<ProductEntity> findAllProducts(){
        return productService.findAll();
    }

}
