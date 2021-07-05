package rs.ac.singidunum.appbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.singidunum.appbackend.entities.ProductEntity;
import rs.ac.singidunum.appbackend.models.ProductModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface iProductService {
    ProductEntity createProduct(ProductModel productModel, MultipartFile[] file);
    List<ProductEntity> findAll();
    ResponseEntity<Resource> download(String fileName, HttpServletRequest request);
    List<String> findAllCategories();
}
