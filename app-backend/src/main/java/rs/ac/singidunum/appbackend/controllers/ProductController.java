package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.singidunum.appbackend.entities.ProductEntity;
import rs.ac.singidunum.appbackend.models.ProductModel;
import rs.ac.singidunum.appbackend.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //NOTE: READovi sa fronta(pretrage), CREATE za laksi insert

    @PostMapping("createproduct")
    @CrossOrigin(origins = "*")
    public ProductEntity createProduct(@RequestPart("model") ProductModel productModel, @RequestParam("file") MultipartFile[] file){
        return productService.createProduct(productModel, file);
    }

    @GetMapping("download")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Resource> download(@RequestParam("fileName") String fileName, HttpServletRequest request) {
        return this.productService.download(fileName, request);
    }

    @GetMapping("findallproducts")
    @CrossOrigin(origins = "*")
    public List<ProductEntity> findAllProducts(){
        return productService.findAll();
    }

}
