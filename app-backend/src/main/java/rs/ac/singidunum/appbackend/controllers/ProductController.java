package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.appbackend.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //NOTE: READovi sa fronta(pretrage), CREATE za laksi insert
}
